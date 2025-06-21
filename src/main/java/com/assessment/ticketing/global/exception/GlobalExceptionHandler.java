package com.assessment.ticketing.global.exception;

import com.assessment.ticketing.global.constants.ErrorCodeConstants;
import com.assessment.ticketing.global.domain.ErrorResponse;
import com.assessment.ticketing.global.exception.model.*;
import com.assessment.ticketing.global.utils.MessageBundle;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    private final MessageBundle messageBundle;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        return new ResponseEntity<>(
                createErrorResponse(ErrorCodeConstants.ERR_COM001, messageBundle.getMessage(ErrorCodeConstants.ERR_COM001), LocalDateTime.now()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(io.jsonwebtoken.MalformedJwtException.class)
    public ResponseEntity<ErrorResponse> handleMalformedJwtException(io.jsonwebtoken.MalformedJwtException ex) {
        return new ResponseEntity<>(
                createErrorResponse(ErrorCodeConstants.ERR_JWT004, messageBundle.getMessage(ErrorCodeConstants.ERR_JWT004), LocalDateTime.now()),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(io.jsonwebtoken.security.SignatureException.class)
    public ResponseEntity<ErrorResponse> handleSignatureException(io.jsonwebtoken.security.SignatureException ex) {
        return new ResponseEntity<>(
                createErrorResponse(ErrorCodeConstants.ERR_JWT005, messageBundle.getMessage(ErrorCodeConstants.ERR_JWT005), LocalDateTime.now()),
                HttpStatus.UNAUTHORIZED
        );
    }



    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponse> handleExpiredJwtException(ExpiredJwtException ex) {
        return new ResponseEntity<>(
                createErrorResponse(ErrorCodeConstants.ERR_JWT001, messageBundle.getMessage(ErrorCodeConstants.ERR_JWT001), LocalDateTime.now()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UnsupportedJwtException.class)
    public ResponseEntity<ErrorResponse> handleUnsupportedJwtException(UnsupportedJwtException ex) {
        return new ResponseEntity<>(
                createErrorResponse(ErrorCodeConstants.ERR_JWT002, messageBundle.getMessage(ErrorCodeConstants.ERR_JWT002), LocalDateTime.now()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> UsernameAlreadyExistsException(UsernameAlreadyExistsException ex) {
        return new ResponseEntity<>(
                createErrorResponse(ex.getErrorCode(), ex.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException ex) {
        return new ResponseEntity<>(
                createErrorResponse(ex.getErrorCode(), ex.getMessage(), LocalDateTime.now()),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> BadCredentialsException(BadCredentialsException ex) {
        return new ResponseEntity<>(
                createErrorResponse(ex.getErrorCode(), ex.getMessage(), LocalDateTime.now()),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTicketNotFound(TicketNotFoundException ex) {
        return new ResponseEntity<>(
                createErrorResponse(ex.getErrorCode(), ex.getMessage(), LocalDateTime.now()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(TicketCreationException.class)
    public ResponseEntity<ErrorResponse> handleTicketCreationException(TicketCreationException ex) {
        return new ResponseEntity<>(
                createErrorResponse(ex.getErrorCode(), ex.getMessage(), LocalDateTime.now()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }


    private ErrorResponse createErrorResponse(String errorCode, String errorMessage, LocalDateTime timeStamp){
        return ErrorResponse.builder()
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .timestamp(timeStamp)
                .build();
    }
}
