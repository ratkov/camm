package com.fidoarp.util;

import com.fidoarp.dictionary.Dictionaries;
import com.fidoarp.model.questionnaire.DetailsPair;
import com.fidoarp.model.questionnaire.Field;
import com.fidoarp.model.questionnaire.Option;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.ibm.icu.text.Transliterator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portlet.dynamicdatamapping.StructureFieldException;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.storage.FieldConstants;
import com.liferay.portlet.dynamicdatamapping.util.DDMImpl;
import com.liferay.portlet.dynamicdatamapping.util.DDMXSDUtil;
import org.apache.commons.lang.StringUtils;

import javax.portlet.PortletRequest;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.*;

public class FieldsUtil {
    /**
     * The Constant LOG.
     */
    private static final Log log = LogFactoryUtil.getLog(FieldsUtil.class);
    private final Dictionaries dictionaries = new Dictionaries();

    public List<Field> getFields(long ddmTemplateId, String languageId, String defaultLanguageId, String jsonObjectStr, PortletRequest resourceRequest){
        try{
            JSONArray jsonArray = getJSONTemplate(ddmTemplateId);

            Locale defaultLocale = LocaleUtil.fromLanguageId(defaultLanguageId);

            if(jsonObjectStr.length() < 2){
                jsonObjectStr = "{}";
            }
            JSONObject jsonObject = JSONFactoryUtil.createJSONObject(jsonObjectStr);

            return getListFields(jsonArray, defaultLocale, languageId, jsonObject, resourceRequest);
        }catch (Exception e){
            log.error(e.getStackTrace(), e);
        }
        return Collections.emptyList();
    }


    public boolean hasTransliterationFields(long templateId){
        JSONArray jsonArray = getJSONTemplate(templateId);
        return jsonArray != null && !containsInName(jsonArray).isEmpty();
    }

    private List<String> containsInName(JSONArray jsonArray) {
        List<String>  names = new ArrayList<String>();
        for(int i=0; i< jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if(jsonObject.getString("name").contains("transliteration")){
                names.add(jsonObject.getString("name"));
            }
            if(jsonObject.has("fields") && jsonObject.getJSONArray("fields").length() > 0){
                names.addAll(containsInName(jsonObject.getJSONArray("fields")));
            }
        }
        return names;
    }

    public List<Field> updateFields(long templateId, List<Field> fields, String prevStepJsonStr) {
        try {
            JSONArray jsonArray = getJSONTemplate(templateId);
            String id = "Ukrainian-Latin/BGN; NFD; [^\\p{Alnum}] Remove";
            JSONObject prevStepJson = JSONFactoryUtil.createJSONObject(prevStepJsonStr);

            List<String> names = containsInName(jsonArray);
            for(String name: names){
                String nameFind = name.split("_")[1];
                if(!StringUtils.isEmpty(nameFind)){
                    String valueTransform = prevStepJson.getString(nameFind);

                    Transliterator transliterator = Transliterator.getInstance(id);

                    String rules = "я > ia; ю > iu; ь > ; Ь > ; є > ie; ї > i; й > i; ы > y;";
                    String firstTransform = Transliterator.createFromRules(id, rules, Transliterator.FORWARD).transform(valueTransform);
                    String value = transliterator.transform(firstTransform);
                    updateFieldValue(fields, name, value);
                }
            }
        } catch (Exception e) {
            log.error(e.getStackTrace(), e);
        }
        return fields;
    }

    private void updateFieldValue(List<Field> fields, String name, String value) {
        for(Field field : fields){
            if(field.getName().equals(name) && (field.getValue() == null || field.getValue() == "")){
                field.setValue(value);
            }
            if(field.hasChildren()){
                updateFieldValue(field.getChildren(), name, value);
            }
        }
    }

    private List<Field> getListFields(JSONArray jsonArray, Locale defaultLocale, String languageId, JSONObject valuesObj, PortletRequest resourceRequest){
        List<Field> fields = new LinkedList<Field>();
        for(int i=0; i< jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Field field = new Field();
            String fieldType =jsonObject.getString("type");
            field.setDefaultLocale(defaultLocale);
            field.setId(jsonObject.getString("id"));
            field.setName(jsonObject.getString("name"));
            field.setType(fieldType);
            String dataType = jsonObject.getString("dataType");
            field.setDataType(dataType);
            field.setWidth(jsonObject.getInt("width"));
            field.setPredefinedValue(jsonObject.getString("predefinedValue"));
            field.setLabel(jsonObject.getString("label"));
            field.setTip(jsonObject.getString("tip"));
            field.setShowLabel(jsonObject.getBoolean("showLabel"));
            field.setRequired(jsonObject.getBoolean("required"));
            String customStyleClass = jsonObject.getString("customStyleClass");
            field.setCustomStyleClass(customStyleClass);
            String maxsize = jsonObject.getString("maxsize");
            if(StringUtils.isNotEmpty(maxsize) && StringUtils.isNotBlank(maxsize)){
                field.setMaxsize(maxsize);
            }
            String minsize = jsonObject.getString("minsize");
            if(StringUtils.isNotEmpty(minsize) && StringUtils.isNotBlank(minsize)){
                field.setMinsize(minsize);
            }
            String showIf = jsonObject.getString("showIf");
            if(StringUtils.isNotEmpty(showIf) && StringUtils.isNotBlank(showIf)){
                field.setShowIf(showIf);
            }
            if(DDMImpl.TYPE_CHECKBOX.equals(fieldType)){
                setValue(valuesObj, field, "false");
            }else{
                setValue(valuesObj, field, "");
            }

            if(jsonObject.getJSONObject("localizationMap").length() > 0){
                if(jsonObject.getJSONObject("localizationMap").getJSONObject(languageId).length() > 0){
                    JSONObject jsonLocale = jsonObject.getJSONObject("localizationMap").getJSONObject(languageId);
                    if(jsonLocale.has("label")){
                        field.setLabel(jsonLocale.getString("label"));
                    }
                    if(jsonLocale.has("tip")){
                        field.setTip(jsonLocale.getString("tip"));
                    }
                    if(jsonLocale.has("showLabel")){
                        field.setShowLabel(jsonLocale.getBoolean("showLabel"));
                    }
                    if(jsonLocale.has("predefinedValue")){
                        field.setPredefinedValue(jsonLocale.getString("predefinedValue"));
                    }
                }
            }

            if(DDMImpl.TYPE_RADIO.equals(fieldType) || DDMImpl.TYPE_SELECT.equals(fieldType)){
                JSONArray jsonArrayOptions = jsonObject.getJSONArray("options");
                List<Option> options = getOptions(jsonArrayOptions, languageId, defaultLocale.getLanguage());
                if(options.size() == 1){
                    String dictionary = options.get(0).getValue();
                    List<DetailsPair> detailsPairs = dictionaries.get(dictionary).execute(resourceRequest);
                    if(detailsPairs != null && !detailsPairs.isEmpty()){
                        List<Option> optionList = new ArrayList<Option>();
                        for(DetailsPair pair : detailsPairs){
                            Option option = new Option();
                            option.setId(pair.getLeft());
                            option.setValue(pair.getLeft());
                            option.setLabel(pair.getRight());
                            option.setName(pair.getLeft());
                            option.setType("");
                            optionList.add(option);
                        }
                        options = optionList;
                    }else{
                        field.setCustomStyleClass(field.getCustomStyleClass() + " autoload");
                    }
                }
                field.setOptions(options);
            }

            if(DDMImpl.TYPE_SELECT.equals(fieldType)){
                field.setMultiple(jsonObject.getBoolean("multiple"));
            }

            if(FieldConstants.DATE.equals(dataType)){
                field.setIsToday(Boolean.valueOf(jsonObject.getString("isToday")));
            }

            if(jsonObject.has("fields") && jsonObject.getJSONArray("fields").length() > 0){
                JSONArray jsonArrayFields = jsonObject.getJSONArray("fields");
                field.setChildren(getListFields(jsonArrayFields, defaultLocale, languageId, valuesObj, resourceRequest));
                boolean showIfFullContent = jsonObject.getBoolean("showIfFullContent");
                field.setShowIfFullContent(showIfFullContent);
            }
            fields.add(field);
        }

        return fields;
    }


    private List<Option> getOptions(JSONArray jsonArray, String languageId, String defaultLanguageId){
        List<Option> options = new LinkedList<Option>();
        for(int i=0; i< jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Option option = new Option();
            String fieldType =jsonObject.getString("type");
            option.setId(jsonObject.getString("id"));
            option.setName(jsonObject.getString("name"));
            option.setType(fieldType);
            option.setValue(jsonObject.getString("value"));
            option.setLabel(jsonObject.getString("label"));

            if(jsonObject.getJSONObject("localizationMap").length() > 0){
                if(jsonObject.getJSONObject("localizationMap").getJSONObject(languageId) != null && jsonObject.getJSONObject("localizationMap").getJSONObject(languageId).length() > 0){
                    JSONObject jsonLocale = jsonObject.getJSONObject("localizationMap").getJSONObject(languageId);
                    if(jsonLocale.has("label")){
                        option.setLabel(jsonLocale.getString("label"));
                    }
                } else if(jsonObject.getJSONObject("localizationMap").getJSONObject(defaultLanguageId) != null && jsonObject.getJSONObject("localizationMap").getJSONObject(defaultLanguageId).length() > 0){
                    String language = jsonObject.getJSONObject("localizationMap").keys().next();
                    if(StringUtils.isNotBlank(language) && StringUtils.isNotEmpty(language)){
                        JSONObject jsonLocale = jsonObject.getJSONObject("localizationMap").getJSONObject(language);
                        if(jsonLocale.has("label")){
                            option.setLabel(jsonLocale.getString("label"));
                        }
                    }
                } else {
                    String language = jsonObject.getJSONObject("localizationMap").keys().next();
                    if(StringUtils.isNotBlank(language) && StringUtils.isNotEmpty(language)){
                        JSONObject jsonLocale = jsonObject.getJSONObject("localizationMap").getJSONObject(language);
                        if(jsonLocale.has("label")){
                            option.setLabel(jsonLocale.getString("label"));
                        }
                    }
                }
            }

            options.add(option);
        }
        return options;
    }

    private void setValue(JSONObject valuesObj, Field field, String defaultVal){
        if(valuesObj != null && valuesObj.length() > 0){
            if(StringUtils.isEmpty(valuesObj.getString(field.getName()))){
                if(StringUtils.isEmpty(field.getPredefinedValue())){
                    field.setValue(defaultVal);
                }else{
                    field.setValue(field.getPredefinedValue());
                }
            }else{
                field.setValue(valuesObj.getString(field.getName()));
            }
        }else{
            if(StringUtils.isEmpty(field.getPredefinedValue())){
                field.setValue(defaultVal);
            }else{
                field.setValue(field.getPredefinedValue());
            }
        }
    }

    public JSONArray getJSONTemplate(long ddmTemplateId){
        try{
            if(ddmTemplateId != 0){
                DDMTemplate ddmTemplate = DDMTemplateLocalServiceUtil.getDDMTemplate(ddmTemplateId);

                return DDMXSDUtil.getJSONArray(ddmTemplate.getScript());
            }
        }catch (Exception e){
            log.error(e.getStackTrace(), e);
        }

        return null;
    }

    private List<String> getSkippedFields(JSONArray jsonArray, JSONObject jsonValues){
        List<String> fieldNames = new ArrayList<String>();
        try{

            for(int i=0; i< jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String fieldType = jsonObject.getString("type");
                String showIf = jsonObject.getString("showIf");
                String label = jsonObject.getString("label");

                if(jsonObject.has("fields") && jsonObject.getJSONArray("fields").length() > 0){
                    if(DDMImpl.TYPE_CHECKBOX.equals(fieldType)){
                        if(!jsonValues.getBoolean(jsonObject.getString("name"))){
                            fieldNames.addAll(getFieldNames(jsonObject.getJSONArray("fields")));
                        }
                    }
                    fieldNames.addAll(getSkippedFields(jsonObject.getJSONArray("fields"), jsonValues));
                }

                if(StringUtils.isNotEmpty(showIf) && StringUtils.isNotBlank(showIf) && !label.contains("||")){
                    boolean hideField = getHiddenField(showIf, jsonValues, fieldNames);
                    if(!hideField){
                        fieldNames.add(jsonObject.getString("name"));
                    }
                }
            }
        }catch (Exception e){
            log.error(e.getStackTrace(), e);
        }
        return fieldNames;
    }

    private boolean getHiddenField(String showIf, JSONObject jsonValues, List<String> fieldNames) {
        boolean hide = false;
        String[] arrayField = new String[]{showIf};
        if(showIf.contains(",")){
            arrayField = showIf.split(",");
            hide = true;
            for(String field : arrayField){
                hide = showIf(jsonValues, field, hide, false, fieldNames);
            }
        }else{
            if(showIf.contains("|")){
                arrayField = showIf.split("\\|");
            }
            for(String field : arrayField){
                hide = showIf(jsonValues, field, !hide, true, fieldNames);
            }
        }

        return hide;
    }

    private boolean showIf(JSONObject jsonValues, String field, boolean hide, boolean retVal, List<String> fieldNames) {
        String[] params = field.split(":");

        if(hide && params.length > 0) {
            String key = params[0].trim();
            if(fieldNames.contains(key)){
                return false;
            }
            String value = params[1].trim();
            String keyParam = jsonValues.getString(key);
            if(value.trim().equals("not-empty")){
                return StringUtils.isNotEmpty(keyParam) && StringUtils.isNotBlank(keyParam);
            }else if(value.trim().indexOf("!") == 0){
                String anObject = value.split("!")[1];
                return StringUtils.isNotEmpty(keyParam) && StringUtils.isNotBlank(keyParam) && !keyParam.equals(anObject);
            }else{
                return StringUtils.isNotEmpty(keyParam) && StringUtils.isNotBlank(keyParam) && keyParam.equals(value);
            }
        }

        return retVal;
    }

    private List<String> getFieldNames(JSONArray fields) {
        List<String> fieldNames = new ArrayList<String>();

        for(int i=0; i< fields.length(); i++){
            JSONObject jsonObject = fields.getJSONObject(i);
            fieldNames.add(jsonObject.getString("name"));
        }
        return fieldNames;
    }

    public String validateTemplate(JSONObject json, long ddmTemplateId, boolean isPensionerProduct){
        try{
            DDMTemplate ddmTemplate = DDMTemplateLocalServiceUtil.getDDMTemplate(ddmTemplateId);

            DDMStructure structure = DDMStructureLocalServiceUtil.getDDMStructure(ddmTemplate.getStructureId());

            structure.setXsd(ddmTemplate.getScript());

            Set<String> fieldNames = structure.getFieldNames();
            List<String> skipNames = getSkippedFields(getJSONTemplate(ddmTemplateId), json);

            Date dateBirth = null;

            for (String fieldName : fieldNames){
                String fieldValue = "";
                String fieldDataType = structure.getFieldDataType(fieldName);
                String fieldClass = structure.getFieldProperty(fieldName, "customStyleClass");

                if((structure.getFieldRequired(fieldName) || fieldClass.contains("required")
                        || fieldClass.contains("requiredIf")) && !skipNames.contains(fieldName)){
                    if (fieldDataType.equals(FieldConstants.DATE)) {
                        int fieldValueMonth = GetterUtil.getInteger(json.getString(fieldName + "Month"));
                        int fieldValueYear = GetterUtil.getInteger(json.getString(fieldName + "Year"));
                        int fieldValueDay = GetterUtil.getInteger(json.getString(fieldName + "Day"));

                        Date fieldValueDate = getValueDate(fieldValueMonth, fieldValueYear, fieldValueDay);

                        if (fieldValueDate != null) {
                            fieldValue = String.valueOf(getGregorianDate(fieldValueDate));
                            if(fieldName.equals("dateBirth")){
                                dateBirth = fieldValueDate;
                            }
                        }else{
                            fieldValue = json.getString(fieldName);
                        }
                    }else{
                        fieldValue = json.getString(fieldName);
                    }

                    if(StringUtils.isEmpty(fieldValue)){
                        log.error("FieldsUtil.validateTemplate() error: " + fieldName + " is empty");
                        return "checkout.error.data.is.wrong";
                    }
                }
            }

            return null;
        }catch (Exception e){
            log.error(e.getStackTrace(), e);
        }

        return "checkout.error.data.is.wrong";
    }

    private boolean checkPensioner(Date dateBirth, int age){
        Calendar birthdate = Calendar.getInstance();
        birthdate.setTime(dateBirth);
        Calendar now = Calendar.getInstance();

        if(now.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR) > age){
            return true;
        }else if(now.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR) == age){
            if(now.get(Calendar.MONTH) > birthdate.get(Calendar.MONTH)){
                return true;
            }else if(now.get(Calendar.MONTH) == birthdate.get(Calendar.MONTH)){
                if(now.get(Calendar.DAY_OF_MONTH) >= birthdate.get(Calendar.DAY_OF_MONTH)){
                    return true;
                }
            }
        }
        return false;
    }


    public JSONObject mergeStructure(JSONObject json, long ddmTemplateId, long userId, boolean client){
        try{

            DDMTemplate ddmTemplate = DDMTemplateLocalServiceUtil.getDDMTemplate(ddmTemplateId);

            DDMStructure ddmStructure = DDMStructureLocalServiceUtil.getDDMStructure(ddmTemplate.getStructureId());
            Set<String> fieldNames = ddmStructure.getFieldNames();

            DDMStructure ddmStructureTemplate = DDMStructureLocalServiceUtil.getDDMStructure(ddmTemplate.getStructureId());
            ddmStructureTemplate.setXsd(ddmTemplate.getScript());

            List<String> skipNames = getSkippedFields(getJSONTemplate(ddmTemplateId), json);

            JSONObject jsonObject = JSONFactoryUtil.getJSONFactory().createJSONObject();

            JSONObject oldQuestionnaire = getJsonQuestionary(userId, client);

            for (String fieldName : fieldNames){
                String fieldValue = "";
                String fieldClass = "";
                if(ddmStructureTemplate.hasField(fieldName)){
                    fieldClass = ddmStructureTemplate.getFieldProperty(fieldName, "customStyleClass");
                }

                if(skipNames.contains(fieldName) && !fieldClass.contains("requiredIf")){
                    jsonObject.put(fieldName, fieldValue);
                }else{
                    String fieldDataType = ddmStructure.getFieldDataType(fieldName);

                    if (fieldDataType.equals(FieldConstants.DATE)) {
                        int fieldValueMonth = GetterUtil.getInteger(json.getString(fieldName + "Month"));
                        int fieldValueYear = GetterUtil.getInteger(json.getString(fieldName + "Year"));
                        int fieldValueDay = GetterUtil.getInteger(json.getString(fieldName + "Day"));

                        if(fieldValueMonth != 0 && fieldValueYear !=0 && fieldValueDay != 0){
                            Date fieldValueDate = getValueDate(fieldValueMonth, fieldValueYear, fieldValueDay);

                            if (fieldValueDate != null) {
                                fieldValue = String.valueOf(getGregorianDate(fieldValueDate));
                            }
                        }else{
                            fieldValue = json.getString(fieldName);
                        }
                    }else{
                        if(fieldName.toLowerCase().contains("phone")){
                            fieldValue = transformPhone(json.getString(fieldName));
                        }else{
                            fieldValue = json.getString(fieldName);
                        }
                    }

                    if(ddmStructureTemplate.hasField(fieldName)){
                        fieldClass = ddmStructureTemplate.getFieldProperty(fieldName, "customStyleClass");
                        if(StringUtils.isNotEmpty(fieldClass) && StringUtils.isNotBlank(fieldClass) && fieldClass.contains("to-uppercase")){
                            fieldValue = fieldValue.toUpperCase();
                        }
                    }

                    if(StringUtils.isNotEmpty(fieldValue) && StringUtils.isNotBlank(fieldValue)){
                        jsonObject.put(fieldName, fieldValue);
                    }else if(!json.has(fieldName) && oldQuestionnaire != null){
                        jsonObject.put(fieldName, oldQuestionnaire.getString(fieldName));
                    }else{
                        jsonObject.put(fieldName, fieldValue);
                    }
                }
            }

            return jsonObject;


        } catch (SystemException e) {
            log.error(e.getStackTrace(), e);
        } catch (StructureFieldException e) {
            log.error(e.getStackTrace(), e);
        } catch (PortalException e) {
            log.error(e.getStackTrace(), e);
        }

        return null;
    }

    private JSONObject getJsonQuestionary(long userId, boolean client) throws SystemException, JSONException {
        return null;
    }

    public Date getValueDate(int fieldValueMonth, int fieldValueYear, int fieldValueDay) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, fieldValueYear);
        cal.set(Calendar.MONTH, fieldValueMonth - 1);
        cal.set(Calendar.DAY_OF_MONTH, fieldValueDay);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    private XMLGregorianCalendar getGregorianDate(Date date) {
        GregorianCalendar c = new GregorianCalendar();
        if (date != null) {
            c.setTime(date);
            try {
                return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
            } catch (DatatypeConfigurationException e) {
                log.error(e);
            }
        }
        return null;
    }


    public String transformPhone(String phone) {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            if(StringUtils.isNotEmpty(phone) && StringUtils.isNotBlank(phone)){
                String[] phoneArr = phone.split("\\+38");
                if(phoneArr.length > 1){
                    phone = phoneArr[1];
                }
                Phonenumber.PhoneNumber phoneProto = phoneUtil.parse(PhoneNumberUtil.normalizeDigitsOnly(phone), "UA");
                return phoneUtil.format(phoneProto, PhoneNumberUtil.PhoneNumberFormat.E164);
            }
        } catch (Exception e) {
            log.error("NumberParseException was thrown: " + e.toString());
        }
        return "";
    }

    public void hideSteps(List<Field> fields, Long ddmTemplateId, String jsonObjectStr) {
        try{
            JSONObject jsonObject = JSONFactoryUtil.createJSONObject(jsonObjectStr);
            List<String> skipNames = getSkippedFields(getJSONTemplate(ddmTemplateId), jsonObject);
            for(Field field : fields){
                if(field.getType().equals("fieldset") && !field.isShowIfFullContent()){
                    boolean isHidden = true;
                    for(Field childField: field.getChildren()){
                        String name = childField.getName();
                        if(childField.isRequired() || childField.getCustomStyleClass().contains("required")
                                || childField.getCustomStyleClass().contains("requiredIf") && !skipNames.contains(name)){
                            String val = jsonObject.getString(name);
                            if(StringUtils.isEmpty(val) || StringUtils.isBlank(val)){
                                isHidden = false;
                            }
                        }
                        if(childField.getType().equals("fieldset")){
                            boolean multiChecked = false;
                            for(Field multiCheckField: childField.getChildren()){
                                String value = jsonObject.getString(multiCheckField.getName());
                                if(DDMImpl.TYPE_CHECKBOX.equals(multiCheckField.getType()) && StringUtils.isNotEmpty(value) && StringUtils.isNotBlank(value)){
                                    multiChecked = true;
                                }
                            }
                            if(!multiChecked){
                                isHidden = false;
                            }
                        }
                    }

                    field.setHideStep(isHidden);
                }
            }
        }catch (Exception e){
            log.error(e.toString());
        }
    }

    public boolean isNotHiddenField( String fieldName, Long templateId, JSONObject json ) {
        List< String > skippedFieldNames = getSkippedFields( getJSONTemplate( templateId ), json );
        return !skippedFieldNames.contains( fieldName );
    }
}
