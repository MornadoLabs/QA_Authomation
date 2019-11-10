package com.lavieennoir.qa.lab2.models;

public class Currency {

    private String currencyCode;
    private String resultCurrencyCode;
    private float value;

    public Currency() {
    }

    public Currency(String currencyCode, String resultCurrencyCode, float value){
        this.currencyCode = currencyCode;
        this.resultCurrencyCode = resultCurrencyCode;
        this.value = value;
    }

    public float getValue() {return value;}
    public void setValue(float value) {this.value = value;}
    public String getCurrencyCode() {return currencyCode.toUpperCase();}
    public void setCurrencyCode(String value) {this.currencyCode = value;}
    public String getResultCurrencyCode() {return resultCurrencyCode.toUpperCase();}
    public void setResultCurrencyCode(String value) {this.resultCurrencyCode = value;}
}
