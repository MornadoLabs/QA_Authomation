package com.lab2.server.controllers;

import com.lab2.server.models.NumbersOperationModel;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddingController {
     private static final Logger log = Logger.getLogger(AddingController.class);

     @RequestMapping("/add-numbers")
     public double Add(NumbersOperationModel model)
     {
         log.info("first value = " + model.getFirstNumber() + "; second value = " + model.getSecondNumber());
         var result = model.getFirstNumber() + model.getSecondNumber();
         log.info("result = " + result);
         return result;
     }
}
