package io.malachai.homebar.presentation;

import io.malachai.homebar.domain.AccountNotFoundException;
import io.malachai.homebar.domain.DuplicatedEmailException;
import io.malachai.homebar.domain.WrongPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(DuplicatedEmailException.class)
    public ResponseEntity<ErrorResponse> handleDuplicatedEmailException(
            DuplicatedEmailException e) {
        final HttpStatus status = HttpStatus.CONFLICT;

        ErrorResponse errorResponse = ErrorResponse.create(e, status, e.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAccountNotFoundException(
            AccountNotFoundException e) {
        final HttpStatus status = HttpStatus.FORBIDDEN;

        ErrorResponse errorResponse = ErrorResponse.create(e, status, e.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<ErrorResponse> handleWrongPasswordException(WrongPasswordException e) {
        final HttpStatus status = HttpStatus.FORBIDDEN;

        ErrorResponse errorResponse = ErrorResponse.create(e, status, e.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }
}
