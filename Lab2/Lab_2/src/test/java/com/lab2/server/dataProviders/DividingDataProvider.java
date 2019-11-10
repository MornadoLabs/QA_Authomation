package com.lab2.server.dataProviders;

import com.lab2.server.models.NumbersOperationModel;
import org.testng.annotations.DataProvider;

public class DividingDataProvider {
    @DataProvider
    public static  Object[][] getNumbersDividingTestData() {
        return new Object[][]{
                {new NumbersOperationModel(10, 5), 2},
                {new NumbersOperationModel(5, 5), 1},
                {new NumbersOperationModel(-5, 10), -0.5},
        };
    }
}
