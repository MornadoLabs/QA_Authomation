package com.lavieennoir.qa.lab2.dataProviders;

import org.testng.annotations.DataProvider;

public class HomeControllerDataProvider {
    @DataProvider
    public static  Object[][] getTestHomeControllerFilterData() {
        return new Object[][]{
                {"", 10},
                {"Random", 1},
                {"Home", 1},
                {"Calculate", 5},
                {"a", 8},
                {"qwerty", 0}
        };
    }
}
