package com.lavieennoir.qa.lab2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Can not evaluate expression!")
public class EvaluateException extends RuntimeException {
}
