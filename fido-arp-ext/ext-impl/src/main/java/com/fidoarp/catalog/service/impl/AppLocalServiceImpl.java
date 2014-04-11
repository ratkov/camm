package com.fidoarp.catalog.service.impl;

import com.fidoarp.catalog.model.App;
import com.fidoarp.catalog.service.base.AppLocalServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import org.apache.commons.lang.StringUtils;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The implementation of the app local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.fidoarp.catalog.service.AppLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.fidoarp.catalog.service.base.AppLocalServiceBaseImpl
 * @see com.fidoarp.catalog.service.AppLocalServiceUtil
 */
public class AppLocalServiceImpl extends AppLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.fidoarp.catalog.service.AppLocalServiceUtil} to access the app local service.
     */

    public List<App> getSearchResult(long id, Date startDate, Date endDate, String name, String okpo, String phone,
                                     double creditAmount, long statusId, String comment, long userId, int start, int end){
        try {
            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(App.class);
            if(id != 0){
                Criterion idCriterion = PropertyFactoryUtil.forName("appId").eq(id);
                dynamicQuery.add(idCriterion);
            }
            if(startDate != null && endDate != null){
                Criterion dateCriterion = PropertyFactoryUtil.forName("createdDate").between(startDate, endDate);
                dynamicQuery.add(dateCriterion);
            }
            if(StringUtils.isNotBlank(name) && StringUtils.isNotEmpty(name))  {
                Criterion nameCriterion = PropertyFactoryUtil.forName("clientName").like(name);
                dynamicQuery.add(nameCriterion);
            }
            if(StringUtils.isNotBlank(okpo) && StringUtils.isNotEmpty(okpo))  {
                Criterion okpoCriterion = PropertyFactoryUtil.forName("clientOkpo").like(okpo);
                dynamicQuery.add(okpoCriterion);
            }
            if(StringUtils.isNotBlank(phone) && StringUtils.isNotEmpty(phone))  {
                Criterion phoneCriterion = PropertyFactoryUtil.forName("contactPhone").like(phone);
                dynamicQuery.add(phoneCriterion);
            }
            if(creditAmount != 0)  {
                Criterion creditAmountCriterion = PropertyFactoryUtil.forName("creditAmount").ge(creditAmount);
                dynamicQuery.add(creditAmountCriterion);
            }
            if(statusId != 0)  {
                Criterion statusIdCriterion = PropertyFactoryUtil.forName("statusId").eq(statusId);
                dynamicQuery.add(statusIdCriterion);
            }
            if(StringUtils.isNotBlank(comment) && StringUtils.isNotEmpty(comment))  {
                Criterion commentCriterion = PropertyFactoryUtil.forName("clientName").like(comment);
                dynamicQuery.add(commentCriterion);
            }
            if(userId != 0)  {
                Criterion userIdCriterion = PropertyFactoryUtil.forName("userId").eq(userId);
                dynamicQuery.add(userIdCriterion);
            }
            dynamicQuery.setLimit(start, end);

            return appLocalService.dynamicQuery(dynamicQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Integer getSearchResultCount(long id, Date startDate, Date endDate, String name, String okpo, String phone,
                                     double creditAmount, long statusId, String comment, long userId){
        try {
            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(App.class);
            if(id != 0){
                Criterion idCriterion = PropertyFactoryUtil.forName("appId").eq(id);
                dynamicQuery.add(idCriterion);
            }
            if(startDate != null && endDate != null){
                Criterion dateCriterion = PropertyFactoryUtil.forName("createdDate").between(startDate, endDate);
                dynamicQuery.add(dateCriterion);
            }
            if(StringUtils.isNotBlank(name) && StringUtils.isNotEmpty(name))  {
                Criterion nameCriterion = PropertyFactoryUtil.forName("clientName").like(name);
                dynamicQuery.add(nameCriterion);
            }
            if(StringUtils.isNotBlank(okpo) && StringUtils.isNotEmpty(okpo))  {
                Criterion okpoCriterion = PropertyFactoryUtil.forName("clientOkpo").like(okpo);
                dynamicQuery.add(okpoCriterion);
            }
            if(StringUtils.isNotBlank(phone) && StringUtils.isNotEmpty(phone))  {
                Criterion phoneCriterion = PropertyFactoryUtil.forName("contactPhone").like(phone);
                dynamicQuery.add(phoneCriterion);
            }
            if(creditAmount != 0)  {
                Criterion creditAmountCriterion = PropertyFactoryUtil.forName("creditAmount").ge(creditAmount);
                dynamicQuery.add(creditAmountCriterion);
            }
            if(statusId != 0)  {
                Criterion statusIdCriterion = PropertyFactoryUtil.forName("statusId").eq(statusId);
                dynamicQuery.add(statusIdCriterion);
            }
            if(StringUtils.isNotBlank(comment) && StringUtils.isNotEmpty(comment))  {
                Criterion commentCriterion = PropertyFactoryUtil.forName("clientName").like(comment);
                dynamicQuery.add(commentCriterion);
            }
            if(userId != 0)  {
                Criterion userIdCriterion = PropertyFactoryUtil.forName("userId").eq(userId);
                dynamicQuery.add(userIdCriterion);
            }
            return ((Long)appLocalService.dynamicQueryCount(dynamicQuery)).intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
