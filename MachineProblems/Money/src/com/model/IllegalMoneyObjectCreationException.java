package com.model;

public class IllegalMoneyObjectCreationException extends RuntimeException{
  public IllegalMoneyObjectCreationException(Throwable cause) {
    super(cause);
  }

  public IllegalMoneyObjectCreationException(String message, Throwable cause) {
    super(message, cause);
  }

  public IllegalMoneyObjectCreationException() {
    super();
  }

  public IllegalMoneyObjectCreationException(String message) {
    super(message);
  }
}
