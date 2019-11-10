package com.lavieennoir.qa.lab2.dataProviders;

import org.testng.annotations.DataProvider;

public class CalculatePowerControllerDataProvider {
    @DataProvider
    public static  Object[][] getTestControllerData() {
        return new Object[][]{
                {-2F, -2F, 0.25D},
                {25F, 0.5F, 5D},
                {10F, 10F, 1E10D},
                {2F, 64F, 1.8446744073709552E19D},
                {2F, -64F, 5.421010862427522E-20D},
                {0F, 0F, 1D},
                {0F, -1F, Double.POSITIVE_INFINITY},
        };
    }
}
