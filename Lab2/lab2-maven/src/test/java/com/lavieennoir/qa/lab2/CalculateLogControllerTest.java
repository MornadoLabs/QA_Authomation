package com.lavieennoir.qa.lab2;

import com.lavieennoir.qa.lab2.controllers.CalculateLogController;
import com.lavieennoir.qa.lab2.controllers.CalculatePowerController;
import com.lavieennoir.qa.lab2.dataProviders.CalculateLogControllerDataProvider;
import com.lavieennoir.qa.lab2.dataProviders.CalculatePowerControllerDataProvider;
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
@WebMvcTest(CalculateLogController.class)
public class CalculateLogControllerTest extends AbstractTestNGSpringContextTests {

    private static final Logger log = Logger.getLogger(CalculateLogController.class);

    @BeforeClass
    private void setUp() {
        log.info("Start execution of tests for CalculateLogController class.");
    }
    @AfterClass
    private void tearDown() {
        log.info("Execution of tests for CalculateLogController class is finished.");
    }
    @Autowired
    private MockMvc mvc;

    @Test(dataProvider = "getTestControllerData", dataProviderClass = CalculateLogControllerDataProvider.class)
    void testCalculateLogController(float value, float base, double result) throws Exception {
        final String requestURI = String.format(Locale.US,"/log?value=%f&base=%f", value, base);

        final String response = mvc.perform(get(requestURI)).andReturn().getResponse().getContentAsString().replace("\"", "");
        final String stringResult = String.valueOf(result);

        Assert.assertEquals(response,stringResult);
    }
}
