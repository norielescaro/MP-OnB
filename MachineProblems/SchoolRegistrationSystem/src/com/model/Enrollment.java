package com.model;

import java.util.HashSet;
import java.util.Set;

public class Enrollment {
  private Set<Section> sectionSet;
  private int totalNumberOfUnits;
  
  public Enrollment(){
    sectionSet = new HashSet<Section>();
  }
  public void setSectionSet(Set<Section> sectionSet) {
    this.sectionSet = sectionSet;
  }

  public Set<Section> getSectionSet() {
    return sectionSet;
  }

  public void setTotalNumberOfUnits(int totalNumberOfUnits) {
    this.totalNumberOfUnits = totalNumberOfUnits;
  }

  public int getTotalNumberOfUnits() {
    return totalNumberOfUnits;
  }
  public void addUnit(int unit) {
    totalNumberOfUnits += unit;
  }
}
