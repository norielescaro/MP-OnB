package com.model;

public enum SubjectType {
    UNDERGRADUATE(2000),GRADUATE(4000);
                        
    private int tuitionFee;
    
    private SubjectType(int tuitionFee) {
      this.tuitionFee = tuitionFee;
    }

  public int getTuitionFee() {
    return tuitionFee;
  }
}
