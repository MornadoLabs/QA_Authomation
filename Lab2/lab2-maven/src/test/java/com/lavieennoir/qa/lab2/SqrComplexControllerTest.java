package com.lavieennoir.qa.lab2;

import com.lavieennoir.qa.lab2.controllers.CalculateLogController;
import com.lavieennoir.qa.lab2.controllers.SqrComplexController;
import com.lavieennoir.qa.lab2.dataProviders.CalculateLogControllerDataProvider;
import com.lavieennoir.qa.lab2.dataProviders.SqrComplexControllerDataProvider;
import com.lavieennoir.qa.lab2.models.ComplexNumber;
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
@WebMvcTest(SqrComplexController.class)
public class SqrComplexControllerTest extends AbstractTestNGSpringContextTests {

    private static final Logger log = Logger.getLogger(SqrComplexController.class);

    @BeforeClass
    private void setUp() {
        log.info("Start execution of tests for SqrComplexController class.");
    }
    @AfterClass
    private void tearDown() {
        log.info("Execution of tests for SqrComplexController class is finished.");
    }
    @Autowired
    private MockMvc mvc;

    @Test(dataProvider = "getTestControllerData", dataProviderClass = SqrComplexControllerDataProvider.class)
    void testSqrComplexController(ComplexNumber parameter, String result) throws Exception {
        final String requestURI = String.format(Locale.US,"/sqr?real=%f&imagine=%f", parameter.getReal(), parameter.getImagine());

        final String response = mvc.perform(get(requestURI)).andReturn().getResponse().getContentAsString().replace("\"", "");

        Assert.assertEquals(response,result);
    }
}
