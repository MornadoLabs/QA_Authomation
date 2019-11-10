package com.lavieennoir.qa.lab2.dataProviders;

import org.testng.annotations.DataProvider;

public class DecToHexControllerDataProvider {
    @DataProvider
    public static  Object[][] getTestControllerData() {
        return new Object[][]{
                {0, "0"},
                {1, "1"},
                {15, "f"},
                {16, "10"},
                {100, "64"},
                {255, "ff"},
                {-1, "ffffffff"},
                {-256, "ffffff00"},
                {2147483647, "7fffffff"},
                {-2147483648, "80000000"},
        };
    }
}
