package com.lavieennoir.qa.lab2;

import com.lavieennoir.qa.lab2.controllers.DecToHexController;
import com.lavieennoir.qa.lab2.controllers.FactorialController;
import com.lavieennoir.qa.lab2.dataProviders.DecToHexControllerDataProvider;
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
@WebMvcTest(DecToHexController.class)
public class DecToHexControllerTest extends AbstractTestNGSpringContextTests {

    private static final Logger log = Logger.getLogger(DecToHexController.class);

    @BeforeClass
    private void setUp() {
        log.info("Start execution of tests for DecToHexController class.");
    }
    @AfterClass
    private void tearDown() { log.info("Execution of tests for DecToHexController class is finished."); }
    @Autowired
    private MockMvc mvc;

    @Test(dataProvider = "getTestControllerData", dataProviderClass = DecToHexControllerDataProvider.class)
    void testDexToHexController(int value, String result) throws Exception {
        final String requestURI = String.format("/hex?value=%d", value);

        final String response = mvc.perform(get(requestURI)).andReturn().getResponse().getContentAsString();

        Assert.assertEquals(response,result);
    }
}
