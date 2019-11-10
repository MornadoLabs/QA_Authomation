package com.lab2.server.controllers;

import com.lab2.server.models.ComplexNumber;
import com.lab2.server.models.NumbersOperationModel;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/divide-complex")
    public ComplexNumber Divide(ComplexNumber complex1, ComplexNumber complex2)
    {
        log.info("first value = " + complex1.toString() + "; second value = " + complex2.toString());

        if (complex2.getReal() == 0 || complex2.getImagine() == 0) {
            log.error("Divide by zero error.");
            throw new RuntimeException("Divide by zero is not allowed!");
        }

        var result = new ComplexNumber(
                complex1.getReal() / complex2.getReal() - complex1.getImagine() / complex2.getImagine(),
                complex1.getReal() / complex2.getImagine() + complex1.getImagine() / complex2.getReal());

        log.info("result = " + result.toString());
        return result;
    }
}
