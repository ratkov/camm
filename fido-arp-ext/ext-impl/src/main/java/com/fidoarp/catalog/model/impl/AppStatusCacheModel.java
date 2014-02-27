package com.fidoarp.catalog.model.impl;

import com.fidoarp.catalog.model.AppStatus;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing AppStatus in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AppStatus
 * @generated
 */
public class AppStatusCacheModel implements CacheModel<AppStatus>, Serializable {
    public long appStatusId;
    public String appStatusCode;
    public String name;
    public String description;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{appStatusId=");
        sb.append(appStatusId);
        sb.append(", appStatusCode=");
        sb.append(appStatusCode);
        sb.append(", name=");
        sb.append(name);
        sb.append(", description=");
        sb.append(description);
        sb.append("}");

        return sb.toString();
    }

    public AppStatus toEntityModel() {
        AppStatusImpl appStatusImpl = new AppStatusImpl();

        appStatusImpl.setAppStatusId(appStatusId);

        if (appStatusCode == null) {
            appStatusImpl.setAppStatusCode(StringPool.BLANK);
        } else {
            appStatusImpl.setAppStatusCode(appStatusCode);
        }

        if (name == null) {
            appStatusImpl.setName(StringPool.BLANK);
        } else {
            appStatusImpl.setName(name);
        }

        if (description == null) {
            appStatusImpl.setDescription(StringPool.BLANK);
        } else {
            appStatusImpl.setDescription(description);
        }

        appStatusImpl.resetOriginalValues();

        return appStatusImpl;
    }
}
