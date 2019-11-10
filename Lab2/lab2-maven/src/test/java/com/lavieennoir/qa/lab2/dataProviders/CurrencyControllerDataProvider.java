package com.lavieennoir.qa.lab2.dataProviders;

import com.lavieennoir.qa.lab2.models.Currency;
import org.testng.annotations.DataProvider;

public class CurrencyControllerDataProvider {
    @DataProvider
    public static  Object[][] getTestControllerData() {
        return new Object[][]{
                {new Currency("EUR", "UAH", 0), 0F},
                {new Currency("EUR", "UAH", 1), 27.027027F},
                {new Currency("UAH", "EUR", 1), 0.037F},
                {new Currency("USD", "RUB", 0.5F), 32.5F},
                {new Currency("UAH", "USD", 1000F), 40.65934F},
        };
    }
    @DataProvider
    public static  Object[][] getTestExceptionsControllerData() {
        return new Object[][]{
                {new Currency("EUR", "UAH", 1), 200},
                {new Currency("USD", "RUB", 1), 200},
                {new Currency("REU", "UAH", 1), 400},
                {new Currency("USD", "UHA", 1), 400},
                {new Currency("ZLT", "UHA", 1), 400},
        };
    }
}
