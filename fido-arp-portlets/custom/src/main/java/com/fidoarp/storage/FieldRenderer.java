package com.fidoarp.storage;

import com.fidoarp.model.questionnaire.Field;

import java.util.Locale;

public interface FieldRenderer {
    public String render(Field field, Locale locale);

    public String render(Field field, Locale locale, int valueIndex);

}
