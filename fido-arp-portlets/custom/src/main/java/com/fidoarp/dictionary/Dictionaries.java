package com.fidoarp.dictionary;

import com.fidoarp.dictionary.impl.*;

import java.util.HashMap;
import java.util.Map;

public class Dictionaries {

    private Map<String, Dictionary> _dictionaryMap = new HashMap<String, Dictionary>(){{
        this.put("fidoRegion", new RegionDictionaryImpl());
        this.put("fidoDistrict", new DistrictDictionaryImpl());
        this.put("fidoLocalityType", new LocalityTypeDictionaryImpl());
        this.put("fidoLocalityName", new LocalityNameDictionaryImpl());
        this.put("fidoStreetType", new StreetTypeDictionaryImpl());
        this.put("fidoStreet", new StreetDictionaryImpl());
        this.put("fidoAllStreet", new AllStreetDictionaryImpl());
        this.put("fidoZipCode", new ZipCodeDictionaryImpl());
        this.put("fidoApartmentType", new ApartmentTypeDictionaryImpl());
        this.put("fidoStayingTime", new StayingTimeDictionaryImpl());
        this.put("fidoRelationContactPerson", new RelationContactPersonDictionaryImpl());
        this.put("fidoEmploymentType", new EmploymentTypeDictionaryImpl());
        this.put("fidoProperty", new PropertyDictionaryImpl());
        this.put("fidoSizeSalary", new SizeSalaryDictionaryImpl());
        this.put("fidoSizeMonthlyIncome", new SizeMonthlyIncomeDictionaryImpl());
        this.put("fidoServicesToUse", new ServicesToUseDictionaryImpl());
        this.put("fidoTypeOrganization", new TypeOrganizationDictionaryImpl());
        this.put("fidoScopeActivity", new ScopeActivityDictionaryImpl());
        this.put("fidoNumberWorkers", new NumberWorkersDictionaryImpl());
        this.put("fidoJobTitle", new JobTitleDictionaryImpl());
        this.put("fidoConditionsEmployment", new ConditionsEmploymentDictionaryImpl());
        this.put("fidoSeniority", new SeniorityDictionaryImpl());
        this.put("fidoFamilyStatus", new FamilyStatusDictionaryImpl());
        this.put("fidoEducation", new EducationDictionaryImpl());
        this.put("fidoAddressInsuranceObject", new AddressInsuranceObjectDictionaryImpl());
        this.put("fidoDepartmentsCities", new DepartmentCitiesImpl());
    }};

    public Dictionary get(String key){
        if(_dictionaryMap.get(key) != null){
            return _dictionaryMap.get(key);
        }
        return null;
    }
}