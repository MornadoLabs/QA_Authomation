package com.lavieennoir.qa.lab2.models;

public class Parameter {

    private final String name;
    private final String type;
    private final Object value;

    public Parameter(String name, String type, Object value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }
    public Object getValue() {return value;}
    public Object getName() {return name;}
    public Object getType() {return type;}
}
