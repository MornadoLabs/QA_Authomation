package com.lab2.server.controllers;

import com.lab2.server.models.NumbersOperationModel;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {
    private static final Logger log = Logger.getLogger(LogController.class);

    @RequestMapping("/lg")
    public double GetLg(double number)
    {
        log.info("Value = " + number);
        var result = Math.log10(number);
        log.info("Result = " + result);
        return result;
    }
}
