package com.model;

public class SectionOverflowException extends Exception {
  public SectionOverflowException(Throwable cause) {
    super(cause);
  }

  public SectionOverflowException(String message, Throwable cause) {
    super(message, cause);
  }

  public SectionOverflowException() {
    super();
  }

  SectionOverflowException(String message) {
  }
}
