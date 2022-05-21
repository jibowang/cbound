package com.becky.common.advice;

import static com.becky.common.Constants.FAILED;
import static com.becky.common.Constants.MESSAGE;
import static com.becky.common.Constants.RESULT;
import static com.becky.common.Constants.SUCCESS;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdviceController {
  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<Object> exception(Exception e) {
    Map<String, Object> results = new HashMap<>();
    results.put(RESULT, FAILED);
    results.put(MESSAGE, e.getMessage());

    return new ResponseEntity<>(results, HttpStatus.BAD_REQUEST);
  }
}
