package com.lavieennoir.qa.lab2.dataProviders;

import com.lavieennoir.qa.lab2.models.RandomParameters;
import org.testng.annotations.DataProvider;

public class RNGControllerDataProvider {
    @DataProvider
    public static  Object[][] getTestControllerData() {
        return new Object[][]{
                {new RandomParameters(0,1,0), new int[]{-1155484576}},
                {new RandomParameters(0,-1,0), new int[]{-1155484576}},
                {new RandomParameters(10,5, 0), new int[]{-1157793070, 1913984760, 1107254586, 1773446580, 254270492}},
                {new RandomParameters(10,5, -1), new int[]{-1157793070, 1913984760, 1107254586, 1773446580, 254270492}},
                {new RandomParameters(10,2, 3), new int[]{1773446580, 254270492}},
        };
    }
}
