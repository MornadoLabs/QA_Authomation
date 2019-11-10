package com.lab2.server.dataProviders;

import org.testng.annotations.DataProvider;

import java.util.Date;

public class DescriptionDataProvider {
    @DataProvider
    public static  Object[][] getDescriptionTestData() {
        return new Object[][]{
                {"Test", new Date(2000, 12, 12), "Made by: Test"},
                {"Test", new Date(2000, 12, 12), "Made date: " + new Date(2000, 12, 12).toLocaleString()},
                {"Andy", new Date(2015, 10, 10), "Made by: Andy\tMade date: " + new Date(2015, 10, 10).toLocaleString()},
        };
    }
}
