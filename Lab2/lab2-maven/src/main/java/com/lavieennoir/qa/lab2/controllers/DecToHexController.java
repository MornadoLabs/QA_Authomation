package com.lavieennoir.qa.lab2.controllers;

import com.lavieennoir.qa.lab2.models.ComplexNumber;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DecToHexController {
    private static final Logger log = Logger.getLogger(DecToHexController.class);

    @RequestMapping("/hex")
    public String index(@RequestParam(defaultValue = "0") int value) {
        final String result = Integer.toHexString(value);
        log.info("Returning hex value of " + value);
        return result;
    }
}
