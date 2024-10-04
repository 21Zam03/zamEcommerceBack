package com.zam.zamMarket.config;

import com.zam.zamMarket.exceptions.*;
import com.zam.zamMarket.payload.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    //Validation to verify the type of JSON is correct
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, WebRequest request) {
        ExceptionResponse response = ExceptionResponse.builder()
                .localDateTime(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Invalid data type or malformed JSON: "+ex.getMessage())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    //Validation to use the annotation @valid on the controller
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ExceptionResponse response = ExceptionResponse.builder()
                .localDateTime(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .errors(errors)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    //Validation to verify if an entity exists
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> notFoundException(NotFoundException ex, WebRequest request) {
        ExceptionResponse response = ExceptionResponse.builder()
                .localDateTime(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    //Validation to verify the uniqueness of an entity
    @ExceptionHandler(DuplicateException.class)
    public  ResponseEntity<ExceptionResponse> duplicateException(DuplicateException ex, WebRequest request) {
        ExceptionResponse response = ExceptionResponse.builder()
                .localDateTime(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    //Validation to verify the list is not empty
    @ExceptionHandler(NotContentException.class)
    public  ResponseEntity<ExceptionResponse> notContentException(NotContentException ex, WebRequest request) {
        ExceptionResponse response = ExceptionResponse.builder()
                .localDateTime(LocalDateTime.now())
                .status(HttpStatus.NO_CONTENT.value())
                .error(HttpStatus.NO_CONTENT.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    /****Optionals Exceptions****/
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> badRequestException(BadRequestException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> illegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        ExceptionResponse response = ExceptionResponse.builder()
                .localDateTime(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public  ResponseEntity<ExceptionResponse> UnauthorizedException(UnauthorizedException ex, WebRequest request) {
        ExceptionResponse response = ExceptionResponse.builder()
                .localDateTime(LocalDateTime.now())
                .status(HttpStatus.UNAUTHORIZED.value())
                .error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ForbiddenException.class)
    public  ResponseEntity<ExceptionResponse> forbiddenException(ForbiddenException ex, WebRequest request) {
        ExceptionResponse response = ExceptionResponse.builder()
                .localDateTime(LocalDateTime.now())
                .status(HttpStatus.FORBIDDEN.value())
                .error(HttpStatus.FORBIDDEN.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

}
