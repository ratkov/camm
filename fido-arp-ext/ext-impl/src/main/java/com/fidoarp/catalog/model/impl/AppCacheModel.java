package com.fidoarp.catalog.model.impl;

import com.fidoarp.catalog.model.App;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing App in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see App
 * @generated
 */
public class AppCacheModel implements CacheModel<App>, Serializable {
    public long appId;
    public long userId;
    public long organizationId;
    public long createdDate;
    public String description;
    public long statusId;
    public long statusChangeDate;
    public String clientName;
    public String clientOkpo;
    public String contactPhone;
    public int creditAmount;
    public String comments;
    public String questionnaire;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(27);

        sb.append("{appId=");
        sb.append(appId);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", organizationId=");
        sb.append(organizationId);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", description=");
        sb.append(description);
        sb.append(", statusId=");
        sb.append(statusId);
        sb.append(", statusChangeDate=");
        sb.append(statusChangeDate);
        sb.append(", clientName=");
        sb.append(clientName);
        sb.append(", clientOkpo=");
        sb.append(clientOkpo);
        sb.append(", contactPhone=");
        sb.append(contactPhone);
        sb.append(", creditAmount=");
        sb.append(creditAmount);
        sb.append(", comments=");
        sb.append(comments);
        sb.append(", questionnaire=");
        sb.append(questionnaire);
        sb.append("}");

        return sb.toString();
    }

    public App toEntityModel() {
        AppImpl appImpl = new AppImpl();

        appImpl.setAppId(appId);
        appImpl.setUserId(userId);
        appImpl.setOrganizationId(organizationId);

        if (createdDate == Long.MIN_VALUE) {
            appImpl.setCreatedDate(null);
        } else {
            appImpl.setCreatedDate(new Date(createdDate));
        }

        if (description == null) {
            appImpl.setDescription(StringPool.BLANK);
        } else {
            appImpl.setDescription(description);
        }

        appImpl.setStatusId(statusId);

        if (statusChangeDate == Long.MIN_VALUE) {
            appImpl.setStatusChangeDate(null);
        } else {
            appImpl.setStatusChangeDate(new Date(statusChangeDate));
        }

        if (clientName == null) {
            appImpl.setClientName(StringPool.BLANK);
        } else {
            appImpl.setClientName(clientName);
        }

        if (clientOkpo == null) {
            appImpl.setClientOkpo(StringPool.BLANK);
        } else {
            appImpl.setClientOkpo(clientOkpo);
        }

        if (contactPhone == null) {
            appImpl.setContactPhone(StringPool.BLANK);
        } else {
            appImpl.setContactPhone(contactPhone);
        }

        appImpl.setCreditAmount(creditAmount);

        if (comments == null) {
            appImpl.setComments(StringPool.BLANK);
        } else {
            appImpl.setComments(comments);
        }

        if (questionnaire == null) {
            appImpl.setQuestionnaire(StringPool.BLANK);
        } else {
            appImpl.setQuestionnaire(questionnaire);
        }

        appImpl.resetOriginalValues();

        return appImpl;
    }
}
