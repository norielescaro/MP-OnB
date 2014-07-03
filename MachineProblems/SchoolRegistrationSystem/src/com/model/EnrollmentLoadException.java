package com.model;

public class EnrollmentLoadException extends Exception{
  public EnrollmentLoadException(Throwable cause) {
    super(cause);
  }

  public EnrollmentLoadException(String message, Throwable cause) {
    super(message, cause);
  }

  public EnrollmentLoadException() {
    super();
  }

  EnrollmentLoadException(String message) {
    super(message);
  }
}
