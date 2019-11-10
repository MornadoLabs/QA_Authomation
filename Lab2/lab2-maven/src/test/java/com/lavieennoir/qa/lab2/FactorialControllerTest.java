package com.lavieennoir.qa.lab2;

import com.lavieennoir.qa.lab2.controllers.FactorialController;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@Test
@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
@WebMvcTest(FactorialController.class)
public class FactorialControllerTest extends AbstractTestNGSpringContextTests {

    private static final Logger log = Logger.getLogger(FactorialController.class);

    @BeforeClass
    private void setUp() {
        log.info("Start execution of tests for FactorialController class.");
    }
    @AfterClass
    private void tearDown() {
        log.info("Execution of tests for FactorialController class is finished.");
    }
    @Autowired
    private MockMvc mvc;

    @Test(dataProvider = "getTestControllerData", dataProviderClass = FactorialControllerDataProvider.class)
    void testFactorialController(int value, long result) throws Exception {
        final String requestURI = String.format("/factorial?value=%d", value);

        final String rawResponse = mvc.perform(get(requestURI)).andReturn().getResponse().getContentAsString();
        long response = Long.parseLong(rawResponse);

        Assert.assertEquals(result, response);
    }
}
