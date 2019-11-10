package com.lavieennoir.qa.lab2.controllers;

import com.lavieennoir.qa.lab2.exceptions.InvalidParametersException;
import com.lavieennoir.qa.lab2.exceptions.OperationNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.stream.DoubleStream;

@RestController
public class SimpleCalculatorController {
    private static final Logger log = Logger.getLogger(SimpleCalculatorController.class);

    @RequestMapping("/calculate")
    public double index(@RequestParam(defaultValue = "sum") String operationName, double[] values) {
        switch (operationName.toLowerCase()){
            case "sum" :
                log.info("Returning sum of numbers");
                return Arrays.stream(values).reduce(0, (aggregator, elem) -> aggregator + elem);
            case "subtract" :
                if(values.length < 2) {
                    log.error("Exception thrown: Minimum 2 args required for subtract operation!");
                    throw new InvalidParametersException();
                }
                log.info("Returning result of subtraction all of numbers from the first one");
                return values[0] -
                        Arrays.stream(Arrays.copyOfRange(values,1,values.length))
                        .reduce(0, (aggregator, elem) -> aggregator + elem);
            case "multiply" :
                log.info("Returning multiplied numbers");
                return Arrays.stream(values).reduce(1, (aggregator, elem) -> aggregator * elem);
            case "divide" :
                if(values.length < 2) {
                    log.error("Minimum 2 args required for divide operation!");
                    throw new InvalidParametersException();
                }
                if(DoubleStream.of(values).anyMatch(value -> value == 0)) {
                    log.error("Can not divide by 0!");
                    throw new InvalidParametersException();
                }
                log.info("Returning first number divided by other");
                return Arrays.stream(Arrays.copyOfRange(values,1,values.length))
                        .reduce(values[0], (aggregator, elem) -> aggregator / elem);
            default:
                throw new OperationNotFoundException();
        }
    }
}
