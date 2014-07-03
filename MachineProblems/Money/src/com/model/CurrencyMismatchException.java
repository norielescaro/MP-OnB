package com.model;

public class CurrencyMismatchException extends Exception {
  public CurrencyMismatchException(Throwable cause) {
    super(cause);
  }

  public CurrencyMismatchException(String message, Throwable cause) {
    super(message, cause);
  }

  public CurrencyMismatchException() {
    super();
  }

  public CurrencyMismatchException(String message) {
    super(message);
  }
}
