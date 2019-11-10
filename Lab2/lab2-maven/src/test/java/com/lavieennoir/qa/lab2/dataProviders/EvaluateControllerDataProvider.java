package com.lavieennoir.qa.lab2.dataProviders;

import com.lavieennoir.qa.lab2.models.RandomParameters;
import org.testng.annotations.DataProvider;

public class EvaluateControllerDataProvider {
    @DataProvider
    public static  Object[][] getTestControllerData() {
        return new Object[][]{
                {"1+1-1",1D},
                {"2*2-3*3",-5D},
                {"2*(5-3)/0.5",8.0D},
                {"Math.pow(2,3*3+1)",1024D},
                {"Math.sin(Math.PI*3/2)",-1D},
        };
    }
    @DataProvider
    public static  Object[][] getTestExceptionsControllerData() {
        return new Object[][]{
                {"abc", 400},
                {"1 1 1 1", 400},
                {"1++1", 400},
                {"1\\1+1\\1", 400},
        };
    }
}
