package com.lab2.server.dataProviders;

import org.testng.annotations.DataProvider;

public class LogDataProvider {
    @DataProvider
    public static  Object[][] getNumbersLogTestData() {
        return new Object[][]{
                {10, 1.0},
                {145, 2.161368},
                {25004530, 7.398},
        };
    }
}
