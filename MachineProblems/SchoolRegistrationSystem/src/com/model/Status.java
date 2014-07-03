package com.model;

public enum Status {  
  FRESHMAN(15,18), JUNIOR(18,24), SENIOR(0,21);
                                  
  private int minimumUnits;
  private int maximumUnits;
  
  private Status(int minimumUnits, int maximumUnits) {
    this.minimumUnits = minimumUnits;
    this.maximumUnits = maximumUnits;
  }

  public int getMinimumUnits() {
    return minimumUnits;
  }

  public int getMaximumUnits() {
    return maximumUnits;
  }
}
