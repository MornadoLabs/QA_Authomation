package com.lavieennoir.qa.lab2.dataProviders;

import com.lavieennoir.qa.lab2.models.RandomParameters;
import org.testng.annotations.DataProvider;

public class SimpleCalculatorControllerDataProvider {
    @DataProvider
    public static  Object[][] getTestControllerData() {
        return new Object[][]{
                {"sum", new double[]{1,2,3,4,5}, 15},
                {"Sum", new double[]{1,2,3,4,5}, 15},
                {"SUM", new double[]{1,2,3,4,5}, 15},
                {"subtract", new double[]{10,1,2,3}, 4},
                {"multiply", new double[]{100,200,0,400}, 0},
                {"multiply", new double[]{1,2,3,4,5}, 120},
                {"divide", new double[]{128,2,2,2,2}, 8},
                {"divide", new double[]{-1,0.5,0.25}, -8},
        };
    }
    @DataProvider
    public static  Object[][] getTestExceptionsControllerData() {
        return new Object[][]{
                {"subtract", new double[]{}, 400,},
                {"subtract", new double[]{1}, 400},
                {"divide", new double[]{}, 400},
                {"divide", new double[]{1,2,3,0,5}, 400},
                {"divide", new double[]{1,2,3,0,5}, 400},
                {"custom-operation", new double[]{1,2,3,0,5}, 404},
        };

    }
}
