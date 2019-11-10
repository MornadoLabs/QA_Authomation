package com.lavieennoir.qa.lab2.dataProviders;

import com.lavieennoir.qa.lab2.models.ComplexNumber;
import org.testng.annotations.DataProvider;

public class SqrComplexControllerDataProvider {
    @DataProvider
    public static  Object[][] getTestControllerData() {
        return new Object[][]{
                {new ComplexNumber(1,1), new ComplexNumber(1,1).getFormatted()},
                {new ComplexNumber(-1,0), new ComplexNumber(1,0).getFormatted()},
                {new ComplexNumber(0,0), new ComplexNumber(0,0).getFormatted()},
                {new ComplexNumber(-5,3), new ComplexNumber(25,9).getFormatted()},
                {new ComplexNumber(10,20), new ComplexNumber(100,400).getFormatted()},
        };
    }
}
