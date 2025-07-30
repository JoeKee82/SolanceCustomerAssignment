package com.solance.workflow.exceptionhandlers;

import com.solance.workflow.exceptions.AccountStatusException;
import com.solance.workflow.exceptions.CustomerNotFoundException;
import com.solance.workflow.exceptions.InsufficientFundsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomerNotFoundException.class)
    public void handleCustomerNotFoundException(CustomerNotFoundException ex) {
        log.error("Requested Customer account not found! ", ex);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InsufficientFundsException.class)
    public void handleInsufficientFundsException(InsufficientFundsException ex) {
        log.error("Requested Customer does not have sufficient funds! ", ex);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(AccountStatusException.class)
    public void handleAccountStatusException(AccountStatusException ex) {
        log.error("Requested Customer account not active! ", ex);
    }

}