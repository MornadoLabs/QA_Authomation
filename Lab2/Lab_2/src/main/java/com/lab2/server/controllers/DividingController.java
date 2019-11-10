package com.lab2.server.controllers;

import com.lab2.server.models.NumbersOperationModel;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DividingController {
    private static final Logger log = Logger.getLogger(DividingController.class);

    @RequestMapping("/divide-numbers")
    public double Divide(NumbersOperationModel model)
    {
        log.info("first value = " + model.getFirstNumber() + "; second value = " + model.getSecondNumber());

        if (model.getSecondNumber() == 0) {
            log.error("Divide by zero error.");
            throw new RuntimeException("Divide by zero is not allowed!");
        }

        var result = model.getFirstNumber() / model.getSecondNumber();
        log.info("result = " + result);
        return result;
    }
}
