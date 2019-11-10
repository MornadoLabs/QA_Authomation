package com.lavieennoir.qa.lab2.controllers;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.stream.DoubleStream;

@RestController
public class FactorialController {
    private static final Logger log = Logger.getLogger(FactorialController.class);

    @RequestMapping("/factorial")
    public long index(@RequestParam(defaultValue = "1") int value) {
        if(value <= 0) {
            value = 1;
        }
        long factorial = 1;
        for (int i = 1; i <= value; i++) {
            factorial *= i;
        }
        log.info("Returning factorial of " + value);
        return factorial;
    }
}
