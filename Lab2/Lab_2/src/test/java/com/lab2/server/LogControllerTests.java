package com.lab2.server;

import com.lab2.server.controllers.LogController;
import com.lab2.server.dataProviders.LogDataProvider;
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
@WebMvcTest(LogController.class)
public class LogControllerTests extends AbstractTestNGSpringContextTests {
    private static final Logger log = Logger.getLogger(LogController.class);

    @BeforeClass
    private void onStartTests() {
        log.info("LogControllerTests started.");
    }

    @Autowired
    private MockMvc mvc;

    @Test(dataProvider = "getNumbersLogTestData", dataProviderClass = LogDataProvider.class)
    void testLog(double number, double result) throws Exception {
        final String requestURI = String.format(Locale.US,"/lg?number=%f", number);

        final String response = mvc.perform(get(requestURI)).andReturn().getResponse().getContentAsString().replace("\"", "");
        var numbResp = Math.round(Double.parseDouble(response));

        Assert.assertEquals(numbResp, Math.round(result));
    }

    @AfterClass
    private void onEndTests() {
        log.info("LogControllerTests finished.");
    }
}
