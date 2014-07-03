package com.model;

public enum Period {
  ONE("8:30AM-10:00AM"), TWO("10:00AM-11:30AM"), THREE("11:30AM-1:00PM"),
  FOUR("1:00PM-2:30PM"), FIVE("2:30PM-4:00PM"), SIX("4:00PM-5:300M");
                                                
  private String periodStringValue;
  
  private Period(String value) {
    this.periodStringValue = value;
  }

  public String getPeriodStringValue() {
    return periodStringValue;
  }
}
