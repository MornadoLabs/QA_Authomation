package com.lavieennoir.qa.lab2;

import com.lavieennoir.qa.lab2.controllers.CalculatePowerController;
import com.lavieennoir.qa.lab2.controllers.FactorialController;
import com.lavieennoir.qa.lab2.dataProviders.CalculatePowerControllerDataProvider;
import com.lavieennoir.qa.lab2.dataProviders.FactorialControllerDataProvider;
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
@WebMvcTest(CalculatePowerController.class)
public class CalculatePowerControllerTest extends AbstractTestNGSpringContextTests {

    private static final Logger log = Logger.getLogger(CalculatePowerController.class);

    @BeforeClass
    private void setUp() {
        log.info("Start execution of tests for CalculatePowerController class.");
    }
    @AfterClass
    private void tearDown() {
        log.info("Execution of tests for CalculatePowerController class is finished.");
    }
    @Autowired
    private MockMvc mvc;

    @Test(dataProvider = "getTestControllerData", dataProviderClass = CalculatePowerControllerDataProvider.class)
    void testCalculatePowerController(float value, float power, double result) throws Exception {
        final String requestURI = String.format(Locale.US,"/pow?value=%f&power=%f", value, power);

        final String response = mvc.perform(get(requestURI)).andReturn().getResponse().getContentAsString().replace("\"", "");
        final String stringResult = String.valueOf(result);

        Assert.assertEquals(stringResult, response);
    }
}
