package ru.bell.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class IncomeInvoiceGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<IncomeInvoiceIncorrectData> handleException(NoSuchIncomeInvoiceException exception) {
        IncomeInvoiceIncorrectData incorrectData = new IncomeInvoiceIncorrectData();
        incorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<IncomeInvoiceIncorrectData> handleException(Exception exception) {
        IncomeInvoiceIncorrectData incorrectData = new IncomeInvoiceIncorrectData();
        incorrectData.setInfo(String.format("%s - %s.", exception.getClass().getName(), exception.getMessage()));
        return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
    }
}