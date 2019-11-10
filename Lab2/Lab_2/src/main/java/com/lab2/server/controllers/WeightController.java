package com.lab2.server.controllers;

import com.lab2.server.models.WeightModel;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeightController {
    private static final Logger log = Logger.getLogger(WeightController.class);

    @RequestMapping("/parse-kg-pounds")
    public double KgToPounds(WeightModel model)
    {
        log.info("Kilograms = " + model.toString());
        var result = new WeightModel(model.getValue() * 2.20462262, "lbs");
        log.info("Pounds = " + result.toString());
        return result.getValue();
    }
}
