package ru.bell.exception;

@SuppressWarnings("serial") //для подавления предупреждений компилятора для аннотированного элемента
public class NoSuchIncomeInvoiceException extends RuntimeException {

    public NoSuchIncomeInvoiceException(String message) {
        super(message);
    }
}