package com.lab2.server.models;

public class LengthModel {
    private double value;
    private String unitOfMeasurement;

    public LengthModel(double value, String unitOfMeasurement) {
        this.value = value;
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public double getValue() {
        return value;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value + " " + unitOfMeasurement;
    }
}
