package com.lab2.server.controllers;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PowerController {
    private static final Logger log = Logger.getLogger(PowerController.class);

    @RequestMapping("/power")
    public double Power(double number, double power)
    {
        log.info("Value = " + number);
        var result = Math.pow(number, power);
        log.info("Result = " + result);
        return result;
    }
}
