package com.lab2.server.dataProviders;

import com.lab2.server.models.LengthModel;
import org.testng.annotations.DataProvider;

public class LengthDataProvider {
    @DataProvider
    public static  Object[][] getLengthTestData() {
        return new Object[][]{
                {new LengthModel(10, "cm"), 3.937008},
                {new LengthModel(87.59, "cm"), 34.48425},
                {new LengthModel(998461.3, "cm"), 393095},
        };
    }
}
