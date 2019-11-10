package com.lab2.server;

import com.lab2.server.controllers.DescriptionController;
import com.lab2.server.dataProviders.DescriptionDataProvider;
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

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@Test
@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
@WebMvcTest(DescriptionController.class)
public class DescriptionControllerTests extends AbstractTestNGSpringContextTests {
    private static final Logger log = Logger.getLogger(DescriptionController.class);

    @BeforeClass
    private void onStartTests() {
        log.info("DescriptionControllerTests started.");
    }

    @Autowired
    private MockMvc mvc;

    @Test(dataProvider = "getDescriptionTestData", dataProviderClass = DescriptionDataProvider.class)
    void testDescription(String madeBy, Date madeDate, String result) throws Exception {
        final String requestURI = "/description?madeBy=" + madeBy + "&madeDate=" + madeDate.toLocaleString();
        final String response = mvc.perform(get(requestURI)).andReturn().getResponse().getContentAsString().replace("\"", "");

        Assert.assertTrue(response.contains(result));
    }

    @AfterClass
    private void onEndTests() {
        log.info("DescriptionControllerTests finished.");
    }
}
