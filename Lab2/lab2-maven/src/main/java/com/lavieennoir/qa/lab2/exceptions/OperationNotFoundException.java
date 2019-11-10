package com.lavieennoir.qa.lab2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Operation not found!")
public class OperationNotFoundException extends RuntimeException {}
