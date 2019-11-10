package com.lab2.server.dataProviders;

import com.lab2.server.models.NumbersOperationModel;
import org.testng.annotations.DataProvider;

public class MultiplyingDataProvider {
    @DataProvider
    public static  Object[][] getNumbersMultiplyingTestData() {
        return new Object[][]{
                {new NumbersOperationModel(10, 5), 50},
                {new NumbersOperationModel(-77, -3568), 274736},
                {new NumbersOperationModel(-3, 39), -117},
        };
    }
}
