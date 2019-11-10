package com.lavieennoir.qa.lab2.controllers;

import com.lavieennoir.qa.lab2.models.RandomParameters;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Random;

@RestController
public class RNGController {
    private static final Logger log = Logger.getLogger(RNGController.class);

    @RequestMapping("/rng")
    public int[] index(RandomParameters parameters) {
        if(parameters.getCount() <= 0) {
            parameters.setCount(1);
        }

        final Random random = new Random(parameters.getSeed());
        final int skip = parameters.getSkip();
        for (int i = 0; i < skip; i++) {
            random.nextInt();
        }
        final int count = parameters.getCount();
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = random.nextInt();
        }
        log.info(String.format("Generated random sequence: %s", Arrays.toString(result)));
        return result;
    }
}
