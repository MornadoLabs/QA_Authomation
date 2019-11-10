package com.lab2.server.models;

import java.util.ArrayList;
import java.util.Date;

public class DescriptionModel {
    private ArrayList<String> controllersList;
    private String madeBy;
    private Date madeDate;

    public DescriptionModel() {
        this.controllersList = new ArrayList<String>();
        controllersList.add("Adding");
        controllersList.add("Dividing");
        controllersList.add("Log");
        controllersList.add("Multiplying");
        controllersList.add("Power");
        controllersList.add("Subtraction");
        controllersList.add("Description");

        this.madeBy = "Mykola Pavlenchyk";
        this.madeDate = new Date(2019, 10, 16);
    }

    public ArrayList<String> getControllersList() {
        return controllersList;
    }

    public String getMadeBy() {
        return madeBy;
    }

    public Date getMadeDate() {
        return madeDate;
    }

    public void setMadeBy(String madeBy) {
        this.madeBy = madeBy;
    }

    public void setMadeDate(Date madeDate) {
        this.madeDate = madeDate;
    }
}
