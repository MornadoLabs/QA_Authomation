package com.lavieennoir.qa.lab2.controllers;

import com.lavieennoir.qa.lab2.models.Page;
import com.lavieennoir.qa.lab2.models.Parameter;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class HomeController {
    private static final Logger log = Logger.getLogger(HomeController.class);

    @RequestMapping("/")
    public Page[] index(@RequestParam(defaultValue = "") String nameFilter) {
        final Page[] pages = new Page[] {
                new Page("Home","/", new Parameter[]{
                        new Parameter("nameFilter", "String", "")
                }, new Parameter("Available Pages", "Page[]", null)),
                new Page("Random Number Generator","/rng", new Parameter[]{
                        new Parameter("seed", "String", ""),
                        new Parameter("count", "int", 1),
                        new Parameter("skip", "int", 0),
                }, new Parameter("Result", "int[]", null)),
                new Page("Calculate: Simple operation","/calculate", new Parameter[]{
                        new Parameter("operationName", "Sum|Subtract|Multiply|Divide", null),
                        new Parameter("values", "float[]", null),
                }, new Parameter("Result", "float", null)),
                new Page("Calculate: Factorial","/factorial", new Parameter[]{
                        new Parameter("value", "int", null),
                }, new Parameter("Result", "float", null)),
                new Page("Calculate: Power","/pow", new Parameter[]{
                        new Parameter("value", "float", null),
                        new Parameter("power", "float", null),
                }, new Parameter("Result", "double", null)),
                new Page("Calculate: Log","/log", new Parameter[]{
                        new Parameter("value", "float", null),
                        new Parameter("base", "float", null),
                }, new Parameter("Result", "double", null)),
                new Page("Calculate: Sqr complex number","/sqr", new Parameter[]{
                        new Parameter("real", "float", null),
                        new Parameter("imagine", "float", null),
                }, new Parameter("Result", "String", null)),
                new Page("Decimal to hexadecimal","/hex", new Parameter[]{
                        new Parameter("value", "int", null),
                }, new Parameter("Result", "String", null)),
                new Page("Evaluate expression","/eval", new Parameter[]{
                        new Parameter("expression", "String", null),
                }, new Parameter("Result", "String", null)),
                new Page("Currency converter","/currency", new Parameter[]{
                        new Parameter("currencyCode", "EUR|USD|UAH|RUB", null),
                        new Parameter("resultCurrencyCode", "EUR|USD|UAH|RUB", null),
                        new Parameter("value", "float", null),
                }, new Parameter("Result", "float", null)),
        };
        log.info("Page list generated");
        final Page[] filteredPages = Arrays.stream(pages).filter(page -> page.getName().contains(nameFilter)).toArray(Page[]::new);
        if(nameFilter != "") {
            log.info(String.format("Page list filtered with '%s' string", nameFilter));
        }
        return filteredPages;
    }
}
