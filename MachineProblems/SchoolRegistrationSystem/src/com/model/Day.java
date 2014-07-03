package com.model;

public enum Day {
  ONE("MON/THU"), TWO("TUE/FRI"), THREE("WED/SAT");
  
  private String dayStringValue;
                                
  private Day(String value) {
    this.dayStringValue = value;
  }

  public String getDayStringValue() {
    return dayStringValue;
  }
}
