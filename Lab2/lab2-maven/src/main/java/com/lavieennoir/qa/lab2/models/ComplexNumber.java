package com.lavieennoir.qa.lab2.models;

public class ComplexNumber {

    private float real;
    private float imagine;

    public ComplexNumber() {}

    public ComplexNumber(float real, float imagine){
        this.real = real;
        this.imagine = imagine;
    }

    public float getReal() {return real;}
    public float getImagine() {return imagine;}
    public void setReal(float value) {real = value;}
    public void setImagine(float value) {imagine = value;}
    public String getFormatted() {return Float.toString(real) + '+'+ imagine +'i';}
}
