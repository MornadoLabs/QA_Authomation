package com.lavieennoir.qa.lab2.controllers;

import com.lavieennoir.qa.lab2.models.ComplexNumber;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SqrComplexController {
    private static final Logger log = Logger.getLogger(SqrComplexController.class);

    @RequestMapping("/sqr")
    public String index(ComplexNumber parameter) {
        final ComplexNumber result = new ComplexNumber(
            parameter.getReal() * parameter.getReal(),
            parameter.getImagine() * parameter.getImagine()
        );

        log.info("Returning sqr of " + parameter.getFormatted());
        return result.getFormatted();
    }
}
