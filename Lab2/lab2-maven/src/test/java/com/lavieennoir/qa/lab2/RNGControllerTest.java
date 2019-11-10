package com.lavieennoir.qa.lab2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lavieennoir.qa.lab2.controllers.HomeController;
import com.lavieennoir.qa.lab2.controllers.RNGController;
import com.lavieennoir.qa.lab2.dataProviders.HomeControllerDataProvider;
import com.lavieennoir.qa.lab2.dataProviders.RNGControllerDataProvider;
import com.lavieennoir.qa.lab2.models.Page;
import com.lavieennoir.qa.lab2.models.RandomParameters;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@Test
@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
@WebMvcTest(RNGController.class)
public class RNGControllerTest extends AbstractTestNGSpringContextTests {

    private static final Logger log = Logger.getLogger(RNGController.class);

    @BeforeClass
    private void setUp() {
        log.info("Start execution of tests for RNGController class.");
    }
    @AfterClass
    private void tearDown() {
        log.info("Execution of tests for RNGController class is finished.");
    }
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test(dataProvider = "getTestControllerData", dataProviderClass = RNGControllerDataProvider.class)
    void testRNGController(RandomParameters parameters, int[] results) throws Exception {
        final String requestURI = String.format("/rng?seed=%s&count=%s&skip=%s", parameters.getSeed(),parameters.getCount(), parameters.getSkip());

        final String rawResponse = mvc.perform(get(requestURI)).andReturn().getResponse().getContentAsString();
        int[] response = objectMapper.readValue(rawResponse, int[].class);

        Assert.assertEquals(results, response);
    }
}
