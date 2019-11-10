package com.lavieennoir.qa.lab2.controllers;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatePowerController {
    private static final Logger log = Logger.getLogger(CalculatePowerController.class);

    @RequestMapping("/pow")
    public double index(@RequestParam(defaultValue = "1") float value, @RequestParam(defaultValue = "1") float power) {
        log.info("Returning " + value + '^' + power);
        return Math.pow(value, power);
    }
}
