package com.lab2.server;

import com.lab2.server.controllers.LengthController;
import com.lab2.server.dataProviders.LengthDataProvider;
import com.lab2.server.models.LengthModel;
import com.lab2.server.models.NumbersOperationModel;
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
@WebMvcTest(LengthController.class)
public class LengthControllerTests extends AbstractTestNGSpringContextTests {
    private static final Logger log = Logger.getLogger(LengthController.class);

    @BeforeClass
    private void onStartTests() {
        log.info("LengthControllerTests started.");
    }

    @Autowired
    private MockMvc mvc;

    @Test(dataProvider = "getLengthTestData", dataProviderClass = LengthDataProvider.class)
    void testLengthConverter(LengthModel model, double result) throws Exception {
        final String requestURI = String.format(Locale.US,"/parse-centimeters-inches?value=%f&unitOfMeasurement=%s", model.getValue(), model.getUnitOfMeasurement());

        final String response = mvc.perform(get(requestURI)).andReturn().getResponse().getContentAsString().replace("\"", "");
        var resp = Math.round(Double.parseDouble(response));

        Assert.assertEquals(resp, Math.round(result));
    }

    @AfterClass
    private void onEndTests() {
        log.info("LengthControllerTests finished.");
    }
}
