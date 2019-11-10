package com.lab2.server.controllers;

import com.lab2.server.models.DescriptionModel;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DescriptionController {
    private static final Logger log = Logger.getLogger(DescriptionController.class);
    private DescriptionModel descriptionModel = new DescriptionModel();

    @RequestMapping("/description")
    public String GetDescription()
    {
        var controllersList = descriptionModel.getControllersList();
        String result = "controllers: ";
        for (var controller : controllersList) {
            result += controller + "\n";
        }
        result += "\nMade by: " + descriptionModel.getMadeBy() + "\tMade date: " + descriptionModel.getMadeDate().toString();

        log.info("description = " + result);
        return result;
    }
}
