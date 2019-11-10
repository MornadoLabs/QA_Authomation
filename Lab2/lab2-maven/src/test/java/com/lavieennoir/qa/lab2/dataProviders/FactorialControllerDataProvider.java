package com.lavieennoir.qa.lab2.dataProviders;

import com.lavieennoir.qa.lab2.models.RandomParameters;
import org.testng.annotations.DataProvider;

public class FactorialControllerDataProvider {
    @DataProvider
    public static  Object[][] getTestControllerData() {
        return new Object[][]{
                {-1, 1},
                {0, 1},
                {1, 1},
                {3, 6},
                {20, 2432902008176640000L},
        };
    }
}
