package com.fidoarp.model.questionnaire;

import com.fidoarp.storage.FieldRenderer;
import com.fidoarp.storage.FieldRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalServiceUtil;

import java.io.Serializable;
import java.util.*;

public class Field {

    private static Log _log = LogFactoryUtil.getLog(Field.class);

    private long _ddmStructureId;

    private String _id;

    private Locale _defaultLocale;

    private String _name;

    private String _predefinedValue;

    private String _tip;

    private String _label;

    private String _type;

    private String _dataType;

    private String _customStyleClass;

    private Serializable _value;

    private List<Field> _children;

    private int _width;

    private boolean _showLabel;

    private boolean _required;

    private String _maxsize;

    private String _minsize;

    private String _showIf;

    private boolean _multiple;

    private boolean _isToday;

    private boolean _showIfFullContent;

    private boolean _hideStep;

    private List<Option> _options;

    private Map<Locale, List<Serializable>> _valuesMap = new HashMap<Locale, List<Serializable>>();

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public Field() {
    }

    public  Field(long ddmStructureId, String name, List<Serializable> values,Locale locale) {
        _ddmStructureId = ddmStructureId;
        _name = name;
        _valuesMap.put(locale, values);
    }

    public Field(long ddmStructureId, String name, Map<Locale, List<Serializable>> valuesMap, Locale defaultLocale) {
        _ddmStructureId = ddmStructureId;
        _defaultLocale = defaultLocale;
        _name = name;
        _valuesMap = valuesMap;
    }

    public void addValue(Locale locale, Serializable value) {
        List<Serializable> values = _valuesMap.get(locale);

        if (values == null) {
            values = new ArrayList<Serializable>();

            _valuesMap.put(locale, values);
        }

        values.add(value);
    }

    public void addValues(Locale locale, List<Serializable> values) {
        for (Serializable value : values) {
            addValue(locale, value);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Field)) {
            return false;
        }

        Field field = (Field)obj;

        if ((_ddmStructureId == field._ddmStructureId) &&
                Validator.equals(_name, field._name) &&
                Validator.equals(_valuesMap, field._valuesMap)) {

            return true;
        }

        return false;
    }

    public Set<Locale> getAvailableLocales() {
        return _valuesMap.keySet();
    }

    public String getDataType() throws PortalException, SystemException {
        DDMStructure ddmStructure = getDDMStructure();

        return ddmStructure.getFieldDataType(_name);
    }

    public DDMStructure getDDMStructure() throws SystemException {
        return DDMStructureLocalServiceUtil.fetchStructure(_ddmStructureId);
    }

    public long getDDMStructureId() {
        return _ddmStructureId;
    }

    public Locale getDefaultLocale() {
        return _defaultLocale;
    }

    public void setValue(Serializable value) {
        this._value = value;
    }

    public Serializable getValue() {
        return _value;
    }

    public String getName() {
        return _name;
    }

    public boolean isMultiple() {
        return _multiple;
    }

    public void setMultiple(boolean multiple) {
        this._multiple = multiple;
    }

    public String getRenderedValue(Locale locale) throws PortalException, SystemException {

        FieldRenderer fieldRenderer = getFieldRenderer();

        return fieldRenderer.render(this, locale);
    }

    public String getRenderedValue(Locale locale, int valueIndex)
            throws PortalException, SystemException {

        FieldRenderer fieldRenderer = getFieldRenderer();

        return fieldRenderer.render(this, locale, valueIndex);
    }

    public String getType(){
        return _type;
    }

    public String getCustomStyleClass() {
        return _customStyleClass;
    }

    public void setCustomStyleClass(String customStyleClass) {
        this._customStyleClass = customStyleClass;
    }

    protected FieldRenderer getFieldRenderer()
            throws PortalException, SystemException {

        DDMStructure ddmStructure = getDDMStructure();

        String dataTypeField = null;

        if (ddmStructure != null) {
            dataTypeField = getDataType();
        }

        return FieldRendererFactory.getFieldRenderer(dataTypeField);
    }

    public List<Serializable> getValues(Locale locale) {
        return _getValues(locale);
    }

    public Map<Locale, List<Serializable>> getValuesMap() {
        return _valuesMap;
    }

    public void setDDMStructureId(long ddmStructureId) {
        _ddmStructureId = ddmStructureId;
    }

    public void setDefaultLocale(Locale defaultLocale) {
        _defaultLocale = defaultLocale;
    }

    public void setName(String name) {
        _name = name;
    }

    public void setValues(Locale locale, List<Serializable> values) {
        _valuesMap.put(locale, values);
    }

    public void setValuesMap(Map<Locale, List<Serializable>> valuesMap) {
        _valuesMap = valuesMap;
    }

    private List<Serializable> _getValues(Locale locale) {
        Set<Locale> availableLocales = getAvailableLocales();

        if (!availableLocales.contains(locale)) {
            locale = getDefaultLocale();
        }

        if (locale == null) {
            locale = LocaleUtil.getDefault();
        }

        List<Serializable> values = _valuesMap.get(locale);

        if (values == null) {
            return Collections.emptyList();
        }

        return values;
    }

    public String getPredefinedValue() {
        return _predefinedValue;
    }

    public void setPredefinedValue(String predefinedValue) {
        this._predefinedValue = predefinedValue;
    }

    public String getTip() {
        return _tip;
    }

    public String getLabel() {
        return _label;
    }

    public void setLabel(String label) {
        this._label = label;
    }

    public int getWidth() {
        return _width;
    }

    public void setWidth(int width) {
        this._width = width;
    }

    public boolean isShowLabel() {
        return _showLabel;
    }

    public void setShowLabel(boolean showLabel) {
        this._showLabel = showLabel;
    }

    public boolean isRequired() {
        return _required;
    }

    public void setRequired(boolean required) {
        this._required = required;
    }

    public void setTip(String tip) {
        this._tip = tip;
    }

    public List<Field> getChildren() {
        return _children;
    }

    public boolean hasChildren() {
        return _children != null && !getChildren().isEmpty();
    }

    public void setChildren(List<Field> children) {
        this._children = children;
    }

    public void setType(String type) {
        this._type = type;
    }

    public void setDataType(String dataType) {
        this._dataType = dataType;
    }

    public List<Option> getOptions() {
        return _options;
    }

    public boolean hasOptions() {
        return _options != null && !getOptions().isEmpty();
    }

    public int getOptionsSize() {
        if(_options != null)
            return getOptions().size();
        return 0;
    }

    public void setOptions(List<Option> options) {
        this._options = options;
    }

    public String getMaxsize() {
        return _maxsize;
    }

    public void setMaxsize(String maxsize) {
        this._maxsize = maxsize;
    }

    public String getMinsize() {
        return _minsize;
    }

    public void setMinsize(String minsize) {
        this._minsize = minsize;
    }

    public String getShowIf() {
        return _showIf;
    }

    public void setShowIf(String showIf) {
        this._showIf = showIf;
    }

    public boolean isIsToday() {
        return _isToday;
    }

    public void setIsToday(boolean isToday) {
        this._isToday = isToday;
    }

    public boolean isShowIfFullContent() {
        return _showIfFullContent;
    }

    public void setShowIfFullContent(boolean showIfFullContent) {
        this._showIfFullContent = showIfFullContent;
    }

    public boolean isHideStep() {
        return _hideStep;
    }

    public void setHideStep(boolean hideStep) {
        this._hideStep = hideStep;
    }
}
