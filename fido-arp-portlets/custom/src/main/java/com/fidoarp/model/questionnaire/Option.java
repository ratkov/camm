package com.fidoarp.model.questionnaire;

public class Option {

   private String _id;

   private String _name;

   private String _value;

   private String _label;

   private String _type;

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getValue() {
        return _value;
    }

    public void setValue(String value) {
        this._value = value;
    }

    public String getLabel() {
        return _label;
    }

    public void setLabel(String label) {
        this._label = label;
    }

    public String getType() {
        return _type;
    }

    public void setType(String type) {
        this._type = type;
    }
}