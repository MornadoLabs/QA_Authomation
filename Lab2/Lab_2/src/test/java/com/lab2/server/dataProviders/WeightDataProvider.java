package com.lab2.server.dataProviders;

import com.lab2.server.models.WeightModel;
import org.testng.annotations.DataProvider;

public class WeightDataProvider {
    @DataProvider
    public static  Object[][] getWeightTestData() {
        return new Object[][]{
                {new WeightModel(10, "kg"), 22.04623},
                {new WeightModel(2789, "kg"), 6148.692},
                {new WeightModel(487647.2465, "kg"), 1075078.15111617},
        };
    }
}
