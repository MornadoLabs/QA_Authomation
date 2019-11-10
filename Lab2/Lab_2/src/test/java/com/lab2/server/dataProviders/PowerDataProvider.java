package com.lab2.server.dataProviders;

import org.testng.annotations.DataProvider;

public class PowerDataProvider {
    @DataProvider
    public static  Object[][] getNumbersPowerTestData() {
        return new Object[][]{
                {2, 2, 4},
                {-5, 5, -3125},
                {7.398, 7.89, 7199660.8665},
        };
    }
}
