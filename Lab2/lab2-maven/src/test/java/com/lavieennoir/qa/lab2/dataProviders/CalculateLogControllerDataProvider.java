package com.lavieennoir.qa.lab2.dataProviders;

import org.testng.annotations.DataProvider;

public class CalculateLogControllerDataProvider {
    @DataProvider
    public static  Object[][] getTestControllerData() {
        return new Object[][]{
                {5F, 25F, 0.5D},
                {1.6487212707F, 2.71828182846F, 0.49999979482304957D},
                {10000F, 10F, 4D},
                {10F, 10F, 1D},
                {10F, 1F, Double.POSITIVE_INFINITY},
        };
    }
}
