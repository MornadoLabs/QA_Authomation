package com.lab2.server;

import com.lab2.server.controllers.SubtractionController;
import com.lab2.server.dataProviders.SubtractionDataProvider;
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
@WebMvcTest(SubtractionController.class)
public class SubtractionControllerTests extends AbstractTestNGSpringContextTests {
    private static final Logger log = Logger.getLogger(SubtractionController.class);

    @BeforeClass
    private void onStartTests() {
        log.info("SubtractionControllerTests started.");
    }

    @Autowired
    private MockMvc mvc;

    @Test(dataProvider = "getNumbersSubtractionTestData", dataProviderClass = SubtractionDataProvider.class)
    void testSubtractNumbers(NumbersOperationModel model, double result) throws Exception {
        final String requestURI = String.format(Locale.US,"/subtract-numbers?firstNumber=%f&secondNumber=%f", model.getFirstNumber(), model.getSecondNumber());

        final String response = mvc.perform(get(requestURI)).andReturn().getResponse().getContentAsString().replace("\"", "");
        final String stringResult = String.valueOf(result);

        Assert.assertEquals(response, stringResult);
    }

    @AfterClass
    private void onEndTests() {
        log.info("SubtractionControllerTests finished.");
    }
}
