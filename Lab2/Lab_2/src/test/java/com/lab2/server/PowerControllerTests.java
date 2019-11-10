package com.lab2.server;

import com.lab2.server.controllers.PowerController;
import com.lab2.server.dataProviders.PowerDataProvider;
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
@WebMvcTest(PowerController.class)
public class PowerControllerTests extends AbstractTestNGSpringContextTests {
    private static final Logger log = Logger.getLogger(PowerController.class);

    @BeforeClass
    private void onStartTests() {
        log.info("PowerControllerTests started.");
    }

    @Autowired
    private MockMvc mvc;

    @Test(dataProvider = "getNumbersPowerTestData", dataProviderClass = PowerDataProvider.class)
    void testPower(double number, double power, double result) throws Exception {
        final String requestURI = String.format(Locale.US,"/power?number=%f&power=%f", number, power);

        final String response = mvc.perform(get(requestURI)).andReturn().getResponse().getContentAsString().replace("\"", "");
        var numbResp = Math.round(Double.parseDouble(response));

        Assert.assertEquals(numbResp, Math.round(result));
    }

    @AfterClass
    private void onEndTests() {
        log.info("PowerControllerTests finished.");
    }
}
