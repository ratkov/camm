package com.fidoarp.util;

import com.fidoarp.catalog.model.Dictionary;
import com.fidoarp.catalog.service.DictionaryLocalServiceUtil;
import com.fidoarp.model.questionnaire.DetailsPair;
import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import org.apache.commons.lang.StringUtils;

import java.util.*;


public class QuestionnaryDictionaryUtil {

    /** The Constant LOG. */
    private final static Log LOG = LogFactoryUtil.getLog(QuestionnaryDictionaryUtil.class);

    public static List<DetailsPair> getSex(Locale locale) {
        return getList(locale, "SEX_MF");
    }

    public static List<DetailsPair> getLivingTerms(Locale locale) {
        return getList(locale, "PERIOD_RESIDENCE_ADDRESS");
    }
    
    public static List<DetailsPair> getOrganizationTypes(Locale locale) {
        return getList(locale, "VCR_ORGANIZATION_TYPE");
    }
    
    public static List<DetailsPair> getSettlementTypes(Locale locale) {
        // return getList(locale, 4, "settlement.type.");
        return getList(locale, "CITY_TYPE");
    }

    public static List<DetailsPair> getPropertyTypes(Locale locale) {
        return getList(locale, "TYPE_PROPERTY_OWNERSHIP");
    }

    public static List<DetailsPair> getContactPersonTypes(Locale locale) {
        return getList(locale, "VPC_CONT_PERSON_TYPE");
    }

    public static List<DetailsPair> getMaritalStatuses(Locale locale) {
        return getList(locale, "MARITAL_STATUS");
    }

    public static List<DetailsPair> getEducations(Locale locale) {
        return getList(locale, "FINS_CLM_IN_ACC_EDU_MLOV");
    }

    public static List<DetailsPair> getEmploymentTypes(Locale locale) {
        return getList(locale, "EMPLOYMENT_TYPE");
    }

    public static List<DetailsPair> getEmploymentConditions(Locale locale) {
        return getList(locale, "FIN_CON_EMPLOYMENT_TYPE");
    }

    public static List<DetailsPair> getExperiensTerms(Locale locale) {
        return getList(locale, "VCR_PERIOD_OF_RESIDENCE");
    }

    public static List<DetailsPair> getActivityFields(Locale locale) {
        return getList(locale, "SECTOR_CODE");
    }

    public static List<DetailsPair> getPositionLevels(Locale locale) {
        return getList(locale, "VCR_WORK_POST");
    }

    public static List<DetailsPair> getEmployeesCounts(Locale locale) {
        return getList(locale, "NUMBER_OF_EMPLOYEES");
    }

    //todo update
    public static List<DetailsPair> getPropertyKinds(Locale locale) {
        return getList(locale, 6, "property.kind.");
    }

    //todo update
    public static List<DetailsPair> getWorkStatuses(Locale locale) {
        return getList(locale, 6, "work.status.");
    }

    public static List<DetailsPair> getYesNo(Locale locale) {
        return getList(locale, "LOY_TASK_YES_NO");
    }

    public static List<DetailsPair> getRegions(Locale locale) {
        List<DetailsPair> obl = getList(locale, "OBL");
        for(int i=0; i < obl.size(); i++){
            if(obl.get(i).getLeft().equals("103")){
                Collections.swap(obl, i, 0);
            }
        }
        return obl;
    }

    public static List<DetailsPair> getDistricts(Locale locale, String regionId) {
        return getList(locale, "DISTRICT", regionId);
    }

    public static List<DetailsPair> getSettlementTypesByAvailableSettlements(Locale locale, String districtId) {
        List<DetailsPair> settlementType = new ArrayList<DetailsPair>();
        try {
            DynamicQuery q = DynamicQueryFactoryUtil.forClass(Dictionary.class, PortalClassLoaderUtil.getClassLoader())
                    .add(PropertyFactoryUtil.forName("code").eq("CITY_TYPE"))
                    .add(PropertyFactoryUtil.forName("id")
                            .in(DynamicQueryFactoryUtil.forClass(Dictionary.class, PortalClassLoaderUtil.getClassLoader())
                                    .add(PropertyFactoryUtil.forName("code").eq("CITY"))
                                    .add(PropertyFactoryUtil.forName("parentId").eq(districtId))
                                    .setProjection(ProjectionFactoryUtil.property("otherParentId"))
                            )
                    ).addOrder(OrderFactoryUtil.asc("sort"));

            List<Dictionary> dictionaryList = DictionaryLocalServiceUtil.dynamicQuery(q);
            settlementType = convertToPairList(locale, dictionaryList);
        } catch (SystemException e) {
            LOG.error(e);
        }
        return settlementType;
    }

    private static String getDistrictEmptyId(Locale locale) throws SystemException {
        List<Dictionary> districts = DictionaryLocalServiceUtil.getDictionariesByCode("DISTRICT");
        for(Dictionary dictionary : districts){
            if(dictionary.getName(locale).equals("")){
                return  dictionary.getId();
            }
        }
        return null;
    }

    public static List<DetailsPair> getSettlementNames(Locale locale, String districtId, String settlementTypeId) {
        return getList(locale, "CITY", districtId, settlementTypeId);
    }

    public static List<DetailsPair> getStreetTypes(Locale locale) {
        return getList(locale, "STREET_TYPE");
    }

    public static List<DetailsPair> getStreetTypesByAvailableStreets(Locale locale, String cityId) {
        List<DetailsPair> streetType = new ArrayList<DetailsPair>();
        try {
            Dictionary dictionary = DictionaryLocalServiceUtil.getDictionary("empty_street_type");
            streetType.add(getDetailPair(locale, dictionary));
            DynamicQuery q = DynamicQueryFactoryUtil.forClass(Dictionary.class, PortalClassLoaderUtil.getClassLoader())
                .add(PropertyFactoryUtil.forName("code").eq("STREET_TYPE"))
                .add(PropertyFactoryUtil.forName("id")
                        .in(DynamicQueryFactoryUtil.forClass(Dictionary.class, PortalClassLoaderUtil.getClassLoader())
                                .add(PropertyFactoryUtil.forName("code").eq("STREET"))
                                .add(PropertyFactoryUtil.forName("parentId").eq(cityId))
                                .setProjection(ProjectionFactoryUtil.property("otherParentId"))
                        )
                ).addOrder(OrderFactoryUtil.asc("sort"));
            List<Dictionary> streetTypeList = DictionaryLocalServiceUtil.dynamicQuery(q);
            streetType.addAll(convertToPairList(locale, streetTypeList));
        } catch (Exception e) {
            LOG.error(e);
        }
        return streetType;
    }

    public static List<DetailsPair> getStreets(Locale locale, String cityId, String typeId) {
        List<DetailsPair> street = getList(locale, "STREET", cityId, typeId);
        try{
            Dictionary dictionary = DictionaryLocalServiceUtil.getDictionary("empty_street");
            street.add(getDetailPair(locale, dictionary));
            Collections.swap(street, street.size() - 1, 0);
        }catch (Exception e){
            LOG.error(e);
        }

        return street;
    }


    public static List<DetailsPair> getAllStreets(Locale locale, String cityId) {
        List<DetailsPair> street = getList(locale, "STREET", cityId);
        try{
            Dictionary dictionary = DictionaryLocalServiceUtil.getDictionary("empty_street");
            street.add(getDetailPair(locale, dictionary));
            Collections.swap(street, street.size() - 1, 0);
        }catch (Exception e){
            LOG.error(e);
        }

        return street;
    }
    public static List<DetailsPair> getZipcodes(Locale locale, String streetId) {
        if(StringUtils.isEmpty(streetId) || StringUtils.isBlank(streetId)){
            streetId = "empty_street";
        }
        return getList(locale, "INDEX", streetId);
    }

    private static List<DetailsPair> getList(Locale locale, int max, String prefix) {
        List<DetailsPair> retValue = new ArrayList<DetailsPair>(0);
        ResourceBundle res = ResourceBundle.getBundle("i18n.dictionary.DictionaryResource", locale);
        for (int i = 1; i < max; i++) {
            DetailsPair pair = new DetailsPair();
            pair.setLeft(String.valueOf(i));
            pair.setRight(res.getString(prefix + i));
            retValue.add(pair);
        }
        return retValue;
    }

    private static List<DetailsPair> getList(Locale locale, String code, String parentId) {
        List<Dictionary> list = null;
        try {
            list = DictionaryLocalServiceUtil.getDictionariesByCodeParent(code, parentId);
        } catch (SystemException e) {
            LOG.error(e);
        }
        return convertToPairList(locale, list);
    }

    public static List<DetailsPair> getUkraineAsList(Locale locale) {
        List<DetailsPair> retValue = new ArrayList<DetailsPair>(1);
        Dictionary dict = null;
        try {
            dict = DictionaryLocalServiceUtil.getDictionary("804");
        } catch (PortalException e) {
            LOG.error(e.getMessage());
        } catch (SystemException e) {
            LOG.error(e.getMessage());
        }
        if (dict != null) {
            retValue.add(new DetailsPair(locale, dict.getId(), dict.getName(locale)));
        }
        return retValue;
    }

    private static List<DetailsPair> getList(Locale locale, String code, String parentId, String otherParentId) {
        List<Dictionary> list = null;
        try {
            list = DictionaryLocalServiceUtil.getDictionariesByCodeParents(code, parentId, otherParentId);
        } catch (SystemException e) {
            LOG.error(e);
        }
        return convertToPairList(locale, list);
    }

    private static List<DetailsPair> convertToPairList(Locale locale, List<Dictionary> list) {
        List<DetailsPair> retValue = new ArrayList<DetailsPair>(0);
        if (list != null) {
            for (Dictionary dictionary : list) {
                retValue.add(getDetailPair(locale, dictionary));
            }
        }
        return retValue;
    }

    private static DetailsPair getDetailPair(Locale locale, Dictionary dictionary) {
        DetailsPair pair = new DetailsPair();
        pair.setLocale(locale);
        if(dictionary.getId().contains("empty"))
            pair.setLeft("");
        else
            pair.setLeft(dictionary.getId());
        pair.setRight(dictionary.getName(locale));
        return pair;
    }

    private static List<DetailsPair> getList(Locale locale, String code) {
        List<Dictionary> list = null;
        try {
            list = DictionaryLocalServiceUtil.getDictionariesByCode(code);
        } catch (SystemException e) {
            LOG.error(e);
        }
        return convertToPairList(locale, list);
    }

    public static List<DetailsPair> getCountries(Locale locale) {
        return getList(locale, "COUNTRY");
    }

    public static List<DetailsPair> getDocumentTypes(Locale locale) {
        return getList(locale, "PUB_CREDENTIAL_CATEGORY_TYPE");
    }

    public static List<DetailsPair> getAvgAmounts(Locale locale) {
        return getList(locale, "AVG_AMOUNT");
    }
    public static List<DetailsPair> getInsuranceAddress(Locale locale) {
        return getList(locale, "INSURANCE_ADDRESS_MATCH");
    }

    public static List<DetailsPair> getFidoAims(Locale locale) {
        List<DetailsPair> retValue = getList(locale, "FIDO_AIM");
        ResourceBundle res = ResourceBundle.getBundle("i18n.checkout.Resource", locale);
        retValue.add(new DetailsPair("0", res.getString("checkout.other")));
        return retValue;
    }
}

