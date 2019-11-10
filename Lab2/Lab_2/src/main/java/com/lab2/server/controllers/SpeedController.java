package com.lab2.server.controllers;

import com.lab2.server.models.SpeedModel;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpeedController {
    private static final Logger log = Logger.getLogger(SpeedController.class);

    @RequestMapping("/parse-kmh-mph")
    public double KmhToMph(SpeedModel model)
    {
        log.info("Km per Hour = " + model.toString());
        var result = new SpeedModel(model.getValue() * 0.621371192, "MPH");
        log.info("Miles per Hour = " + result.toString());
        return result.getValue();
    }
}
