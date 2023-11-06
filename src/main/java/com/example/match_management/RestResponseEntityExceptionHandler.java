package com.example.match_management;

import com.example.match_management.exception.ResourceNotFoundException;
import com.example.match_management.exception.SportNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { ResourceNotFoundException.class, IllegalArgumentException.class, SportNotFoundException.class })
    protected ResponseEntity<Object> handleResourceNotFound(Exception ex) {
	Map<String, Object> body = new HashMap<>();
	body.put("message", ex.getMessage());
	body.put("code", HttpStatus.NOT_FOUND.value());
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}
