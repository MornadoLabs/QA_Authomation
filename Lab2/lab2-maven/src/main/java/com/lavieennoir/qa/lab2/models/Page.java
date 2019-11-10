package com.lavieennoir.qa.lab2.models;

public class Page {

    private final String name;
    private final String route;
    private final Parameter[] parameters;
    private final Parameter response;

    public Page(String name, String route, Parameter[] parameters, Parameter response){
        this.name = name;
        this.route = route;
        this.parameters = parameters;
        this.response = response;
    }

    public String getName() {return name;}
    public String getRoute() {return route;}
    public Parameter[] getParameters() {return parameters;}
    public Parameter getResponse() {return response;}
}
