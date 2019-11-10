package com.lab2.server;

import com.lab2.server.controllers.SpeedController;
import com.lab2.server.dataProviders.SpeedDataProvider;
import com.lab2.server.models.SpeedModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Locale;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@Test
@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
@WebMvcTest(SpeedController.class)
public class SpeedControllerTests extends AbstractTestNGSpringContextTests {
    private static final Logger log = Logger.getLogger(SpeedController.class);

    @BeforeClass
    private void onStartTests() {
        log.info("SpeedControllerTests started.");
    }

    @Autowired
    private MockMvc mvc;

    @Test(dataProvider = "getSpeedTestData", dataProviderClass = SpeedDataProvider.class)
    void testSpeedConverter(SpeedModel model, double result) throws Exception {
        final String requestURI = String.format(Locale.US, "/parse-kmh-mph?value=%f&unitOfMeasurement=%s", model.getValue(), model.getUnitOfMeasurement());

        final String response = mvc.perform(get(requestURI)).andReturn().getResponse().getContentAsString().replace("\"", "");
        var resp = Math.round(Double.parseDouble(response));

        Assert.assertEquals(resp, Math.round(result));
    }

    @AfterClass
    private void onEndTests() {
        log.info("SpeedControllerTests finished.");
    }
}