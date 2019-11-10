package com.lavieennoir.qa.lab2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lavieennoir.qa.lab2.controllers.HomeController;
import com.lavieennoir.qa.lab2.dataProviders.HomeControllerDataProvider;
import com.lavieennoir.qa.lab2.models.Page;
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
@WebMvcTest(HomeController.class)
public class HomeControllerTest extends AbstractTestNGSpringContextTests {

    private static final Logger log = Logger.getLogger(HomeController.class);

    @BeforeClass
    private void setUp() {
        log.info("Start execution of tests for HomeController class.");
    }
    @AfterClass
    private void tearDown() {
        log.info("Execution of tests for HomeController class is finished.");
    }
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test(dataProvider = "getTestHomeControllerFilterData", dataProviderClass = HomeControllerDataProvider.class)
    void testHomeControllerFilter(String nameFilter, int responsePagesCount) throws Exception {
        final String requestURI = String.format("/?nameFilter=%s", nameFilter);

        final String rawResponse = mvc.perform(get(requestURI)).andReturn().getResponse().getContentAsString();
        Page[] pagesResponse = objectMapper.readValue(rawResponse, Page[].class);

        Assert.assertEquals(responsePagesCount, pagesResponse.length);
    }
}
