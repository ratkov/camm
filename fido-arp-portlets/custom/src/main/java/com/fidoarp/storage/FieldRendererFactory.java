package com.fidoarp.storage;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.Map;

public class FieldRendererFactory {
    public static FieldRenderer getFieldRenderer(String dataType) {
        FieldRenderer fieldRenderer = _fieldRenderers.get(dataType);

        if (fieldRenderer == null) {
            fieldRenderer = _fieldRenderers.get(FieldConstants.STRING);
        }

        return fieldRenderer;
    }

    public void setFieldRenderers(Map<String, FieldRenderer> fieldRenderers) {
        PortalRuntimePermission.checkSetBeanProperty(getClass());

        _fieldRenderers = fieldRenderers;
    }

    private static Map<String, FieldRenderer> _fieldRenderers;
}
