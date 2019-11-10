package com.lavieennoir.qa.lab2;

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
@WebMvcTest(SimpleCalculatorControllerTest.class)
public class SimpleCalculatorControllerTest extends AbstractTestNGSpringContextTests {

    private static final Logger log = Logger.getLogger(SimpleCalculatorControllerTest.class);

    @BeforeClass
    private void setUp() {
        log.info("Start execution of tests for SimpleCalculatorControllerTest class.");
    }
    @AfterClass
    private void tearDown() {
        log.info("Execution of tests for SimpleCalculatorControllerTest class is finished.");
    }
    @Autowired
    private MockMvc mvc;

    @Test(dataProvider = "getTestControllerData", dataProviderClass = SimpleCalculatorControllerDataProvider.class)
    void testSimpleCalculatorController(String operationName, double[] values, double result) throws Exception {
        final String valuesUriParam = Arrays.stream(values)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));
        final String requestURI = String.format("/calculate?operationName=%s&values=%s", operationName, valuesUriParam);

        final String rawResponse = mvc.perform(get(requestURI)).andReturn().getResponse().getContentAsString();
        double response = Double.parseDouble(rawResponse);

        Assert.assertEquals(result, response);
    }
    @Test(dataProvider = "getTestExceptionsControllerData", dataProviderClass = SimpleCalculatorControllerDataProvider.class)
    void testSimpleCalculatorControllerExceptions(String operationName, double[] values, int expectedStatus) throws Exception {
        final String valuesUriParam = Arrays.stream(values)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));
        final String requestURI = String.format("/calculate?operationName=%s&values=%s", operationName, valuesUriParam);

        mvc.perform(get(requestURI)).andExpect(status().is(expectedStatus));
    }
}
