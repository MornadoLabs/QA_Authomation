package com.lab2.server.dataProviders;

import com.lab2.server.models.NumbersOperationModel;
import org.testng.annotations.DataProvider;

public class SubtractionDataProvider {
    @DataProvider
    public static  Object[][] getNumbersSubtractionTestData() {
        return new Object[][]{
                {new NumbersOperationModel(10, 5), 5},
                {new NumbersOperationModel(5, 10), -5},
                {new NumbersOperationModel(-5, 10), -15},
        };
    }
}
