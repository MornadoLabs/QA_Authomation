package com.lavieennoir.qa.lab2;

import com.lavieennoir.qa.lab2.controllers.CalculateLogController;
import com.lavieennoir.qa.lab2.controllers.CurrencyController;
import com.lavieennoir.qa.lab2.dataProviders.CalculateLogControllerDataProvider;
import com.lavieennoir.qa.lab2.dataProviders.CurrencyControllerDataProvider;
import com.lavieennoir.qa.lab2.dataProviders.EvaluateControllerDataProvider;
import com.lavieennoir.qa.lab2.models.Currency;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Test
@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
@WebMvcTest(CurrencyController.class)
public class CurrencyControllerTest extends AbstractTestNGSpringContextTests {

    private static final Logger log = Logger.getLogger(CurrencyController.class);

    @BeforeClass
    private void setUp() {
        log.info("Start execution of tests for CurrencyController class.");
    }
    @AfterClass
    private void tearDown() {
        log.info("Execution of tests for CurrencyController class is finished.");
    }
    @Autowired
    private MockMvc mvc;


    @Test(dataProvider = "getTestControllerData", dataProviderClass = CurrencyControllerDataProvider.class)
    void testCurrencyController(Currency parameter, float result) throws Exception {
        final String requestURI = String.format(Locale.US,"/currency?currencyCode=%s&resultCurrencyCode=%s&value=%f",
                parameter.getCurrencyCode(), parameter.getResultCurrencyCode(), parameter.getValue());

        final String rawResponse = mvc.perform(get(requestURI)).andReturn().getResponse().getContentAsString();
        final float response = Float.parseFloat(rawResponse);
        Assert.assertEquals(response, result);
    }
    @Test(dataProvider = "getTestExceptionsControllerData", dataProviderClass = CurrencyControllerDataProvider.class)
    void testCurrencyControllerExceptions(Currency parameter,  int expectedStatus) throws Exception {
        final String requestURI = String.format(Locale.US,"/currency?currencyCode=%s&resultCurrencyCode=%s&value=%f",
                parameter.getCurrencyCode(), parameter.getResultCurrencyCode(), parameter.getValue());

        mvc.perform(get(requestURI)).andExpect(status().is(expectedStatus));
    }
}
