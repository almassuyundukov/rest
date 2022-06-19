package ru.easyum.rest.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.easyum.rest.exception.EmployeeNotFoundException;
import ru.easyum.rest.payload.response.MessageError;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<MessageError> exceptionHandler(EmployeeNotFoundException exception){
        MessageError message = new MessageError();
        message.setInfo(exception.getMessage());
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler
    public ResponseEntity<MessageError> exceptionHandler(NumberFormatException exception){
        MessageError message = new MessageError();
        message.setInfo("Invalid URL parameter id. Please enter a number to URL parameter");
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> validateExceptionHandler(MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();
        BindingResult bindingResult = exception.getBindingResult();
        bindingResult.getAllErrors()
                .forEach((error) -> {
                    String fieldName = ((FieldError) error).getField();
                    String defaultMessage = error.getDefaultMessage();
                    errors.put(fieldName, defaultMessage);
                });
        return ResponseEntity.badRequest().body(errors);
    }
}
