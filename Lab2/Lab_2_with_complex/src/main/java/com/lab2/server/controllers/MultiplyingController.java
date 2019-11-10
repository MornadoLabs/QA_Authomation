package com.lab2.server.controllers;

import com.lab2.server.models.ComplexNumber;
import com.lab2.server.models.NumbersOperationModel;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultiplyingController {
    private static final Logger log = Logger.getLogger(MultiplyingController.class);

    @RequestMapping("/multiply-numbers")
    public double Multiply(NumbersOperationModel model)
    {
        log.info("first value = " + model.getFirstNumber() + "; second value = " + model.getSecondNumber());
        var result = model.getFirstNumber() * model.getSecondNumber();
        log.info("result = " + result);
        return result;
    }

    @RequestMapping("/multiply-complex")
    public ComplexNumber Multiply(ComplexNumber complex1, ComplexNumber complex2)
    {
        log.info("first value = " + complex1.toString() + "; second value = " + complex2.toString());

        var result = new ComplexNumber(
                complex1.getReal() * complex2.getReal() - complex1.getImagine() * complex2.getImagine(),
                complex1.getReal() * complex2.getImagine() + complex1.getImagine() * complex2.getReal());

        log.info("result = " + result.toString());
        return result;
    }
}
