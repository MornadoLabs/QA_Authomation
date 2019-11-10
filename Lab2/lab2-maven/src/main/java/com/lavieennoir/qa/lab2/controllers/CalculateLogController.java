package com.lavieennoir.qa.lab2.controllers;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculateLogController {
    private static final Logger log = Logger.getLogger(CalculateLogController.class);

    @RequestMapping("/log")
    public double index(@RequestParam(defaultValue = "1") float value, @RequestParam(defaultValue = "1") float base) {
        log.info("Returning log " + value + " base " + base);
        return Math.log(value) / Math.log(base);
    }
}
