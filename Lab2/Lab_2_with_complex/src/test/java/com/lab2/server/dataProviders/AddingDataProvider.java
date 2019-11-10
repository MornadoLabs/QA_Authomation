package com.lab2.server.dataProviders;

import com.lab2.server.models.ComplexNumber;
import com.lab2.server.models.NumbersOperationModel;
import org.testng.annotations.DataProvider;

public class AddingDataProvider {
    @DataProvider
    public static  Object[][] getNumbersAddingTestData() {
        return new Object[][]{
                {new NumbersOperationModel(10, 5), 15},
                {new NumbersOperationModel(5, 10), 15},
                {new NumbersOperationModel(-5, 10), 5},
        };
    }

    @DataProvider
    public static  Object[][] getComplexAddingTestData() {
        return new Object[][]{
                {new ComplexNumber(10, 5), new ComplexNumber(5, 5), new ComplexNumber(15, 10)},
        };
    }
}
