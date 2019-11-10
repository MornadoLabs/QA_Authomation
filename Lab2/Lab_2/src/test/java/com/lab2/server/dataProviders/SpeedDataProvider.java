package com.lab2.server.dataProviders;

import com.lab2.server.models.SpeedModel;
import org.testng.annotations.DataProvider;

public class SpeedDataProvider {
    @DataProvider
    public static  Object[][] getSpeedTestData() {
        return new Object[][]{
                {new SpeedModel(10, "KPH"), 6.214268},
                {new SpeedModel(88.5, "KPH"), 55},
                {new SpeedModel(348.22, "KPH"), 216.3932},
        };
    }
}
