package com.lab2.server.controllers;

import com.lab2.server.models.LengthModel;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LengthController {
    private static final Logger log = Logger.getLogger(LengthController.class);

    @RequestMapping("/parse-centimeters-inches")
    public LengthModel CentimetersToInches(LengthModel model)
    {
        log.info("Centimeters = " + model.toString());
        var result = new LengthModel(model.getValue() * 0.393700787, "inch");
        log.info("Inches = " + result.toString());
        return result;
    }
}
