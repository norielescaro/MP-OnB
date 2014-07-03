package com.model;

import java.util.HashSet;
import java.util.Set;

public class Teacher {
  private String facultyID;
  private Set<Schedule> scheduleSet;
  
  public Teacher(String facultyID) {
    this.facultyID = facultyID;
    scheduleSet = new HashSet<Schedule>();
  }

  public String getFacultyID() {
    return facultyID;
  }

  public void setScheduleSet(Set<Schedule> scheduleSet) {
    this.scheduleSet = scheduleSet;
  }

  public Set<Schedule> getScheduleSet() {
    return scheduleSet;
  }
}
