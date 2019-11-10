package com.lavieennoir.qa.lab2;

import com.lavieennoir.qa.lab2.controllers.EvaluateController;
import com.lavieennoir.qa.lab2.controllers.FactorialController;
import com.lavieennoir.qa.lab2.dataProviders.EvaluateControllerDataProvider;
import com.lavieennoir.qa.lab2.dataProviders.FactorialControllerDataProvider;
import com.lavieennoir.qa.lab2.dataProviders.SimpleCalculatorControllerDataProvider;
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

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Test
@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
@WebMvcTest(EvaluateController.class)
public class EvaluateControllerTest extends AbstractTestNGSpringContextTests {

    private static final Logger log = Logger.getLogger(EvaluateController.class);

    @BeforeClass
    private void setUp() {
        log.info("Start execution of tests for EvaluateController class.");
    }
    @AfterClass
    private void tearDown() {
        log.info("Execution of tests for EvaluateController class is finished.");
    }
    @Autowired
    private MockMvc mvc;

    @Test(dataProvider = "getTestControllerData", dataProviderClass = EvaluateControllerDataProvider.class)
    void testEvaluateController(String expression, Double result) throws Exception {
        final String requestURI = String.format("/eval?expression=%s", expression);

        final String rawResponse = mvc.perform(get(requestURI)).andReturn().getResponse().getContentAsString();
        final double response = Double.parseDouble(rawResponse);
        Assert.assertEquals(response, result);
    }
    @Test(dataProvider = "getTestExceptionsControllerData", dataProviderClass = EvaluateControllerDataProvider.class)
    void testEvaluateControllerExceptions(String expression, int expectedStatus) throws Exception {
        final String requestURI = String.format("/eval?expression=%s", expression);

        mvc.perform(get(requestURI)).andExpect(status().is(expectedStatus));
    }
}
