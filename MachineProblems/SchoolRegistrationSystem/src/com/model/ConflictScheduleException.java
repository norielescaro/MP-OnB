package com.model;

public class ConflictScheduleException extends Exception {
  public ConflictScheduleException(Throwable cause) {
    super(cause);
  }

  public ConflictScheduleException(String message, Throwable cause) {
    super(message, cause);
  }

  public ConflictScheduleException() {
    super();
  }

  ConflictScheduleException(String message) {
    super(message);
  }
}
