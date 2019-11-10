package com.lab2.server.models;

public class ComplexNumber {
    private double real;
    private double imagine;

    public ComplexNumber(double real, double imagine) {
        this.real = real;
        this.imagine = imagine;
    }

    public double getReal() {
        return real;
    }

    public double getImagine() {
        return imagine;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public void setImagine(double imagine) {
        this.imagine = imagine;
    }

    @Override
    public String toString() {
        return real + " " + (imagine < 0 ? "-" : "+") + " " + imagine + "i";
    }
}
