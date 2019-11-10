package com.lavieennoir.qa.lab2.controllers;

import com.lavieennoir.qa.lab2.exceptions.InvalidParametersException;
import com.lavieennoir.qa.lab2.models.Currency;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class CurrencyController {
    private static final Logger log = Logger.getLogger(CurrencyController.class);

    @RequestMapping("/currency")
    public float index(Currency parameter) {
        final HashMap<String, Float> currencyRatesEUR = new HashMap<String, Float>(4);
        currencyRatesEUR.put("EUR", 1f);
        currencyRatesEUR.put("USD", 0.91f);
        currencyRatesEUR.put("UAH", 0.037f);
        currencyRatesEUR.put("RUB", 0.014f);
        if(!currencyRatesEUR.containsKey(parameter.getCurrencyCode())
                || !currencyRatesEUR.containsKey(parameter.getResultCurrencyCode())) {
            log.error("Exception thrown: invalid currency name!");
            throw new InvalidParametersException();
        }

        final float inputRate = currencyRatesEUR.get(parameter.getCurrencyCode());
        final float outputRate = currencyRatesEUR.get(parameter.getResultCurrencyCode());
        final float resultRate = inputRate/outputRate;
        log.info("Converting "+parameter.getCurrencyCode()+" to " + parameter.getResultCurrencyCode());
        return resultRate * parameter.getValue();
    }
}
