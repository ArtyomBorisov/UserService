package it.academy.controller.advice;

import it.academy.exception.EmailExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

import static it.academy.constants.MessagesForUsers.EMAIL_EXISTS;
import static it.academy.constants.MessagesForUsers.NOT_VALID_DATA;

@ControllerAdvice
public class AdviceController {
    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<?> emailExistsExceptionHandler(EmailExistsException e) {
        return ResponseEntity.badRequest()
                .body(new SingleResponseError(EMAIL_EXISTS));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> methodArgumentNotValidExceptionHandler(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest()
                .body(new SingleResponseError(NOT_VALID_DATA));
}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<Error> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ex -> new Error(ex.getField(), ex.getDefaultMessage()))
                .toList();

        return ResponseEntity.badRequest()
                .body(new MultipleResponseError(errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception e) {
        return ResponseEntity.internalServerError()
                .body(new SingleResponseError(""));
    }
}
