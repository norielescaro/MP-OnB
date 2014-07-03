package com.model;

public class SubjectConflictException extends Exception{
  public SubjectConflictException(Throwable cause) {
    super(cause);
  }

  public SubjectConflictException(String message, Throwable cause) {
    super(message, cause);
  }

  public SubjectConflictException() {
    super();
  }

  SubjectConflictException(String message) {
  }
}
