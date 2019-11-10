package com.lavieennoir.qa.lab2.controllers;

import com.lavieennoir.qa.lab2.exceptions.EvaluateException;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

@RestController
public class EvaluateController {
    private static final Logger log = Logger.getLogger(EvaluateController.class);

    @RequestMapping("/eval")
    public String index(@RequestParam(defaultValue = "") String expression) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        try {
            final String result = engine.eval(expression).toString();
            log.info("Returning result of evaluating " + expression);
            return result;
        } catch (ScriptException e) {
            log.error("Exception thrown: " + e.getMessage());
            throw new EvaluateException();
        }
    }
}
