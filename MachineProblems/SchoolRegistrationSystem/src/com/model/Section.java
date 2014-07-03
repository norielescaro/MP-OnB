package com.model;

import java.util.HashSet;
import java.util.Set;

public class Section {
  private Subject subject;
  private Schedule schedule;
  private Teacher teacher;
  private Set<Student> studentSet;
  
  public Section(Subject subject, Schedule schedule, Teacher teacher) throws ConflictScheduleException {
    this.subject = subject;
    this.schedule = schedule;
    setTeacher(teacher);
    studentSet = new HashSet<Student>();
  }

  public void setSubject(Subject subject) {
    this.subject = subject;
  }

  public Subject getSubject() {
    return subject;
  }

  public void setSchedule(Schedule schedule) {
    this.schedule = schedule;
  }

  public Schedule getSchedule() {
    return schedule;
  }

  public void setTeacher(Teacher teacher) throws ConflictScheduleException {
    if(!isConflictWithSchedule(teacher)){
      teacher.getScheduleSet().add(schedule);
      this.teacher = teacher;
    }
    else{
      throw new ConflictScheduleException("There's a conflict in the schedule of this teacher");
    }
  }

  public Teacher getTeacher() {
    return teacher;
  }
  
  public int size(){
    return this.studentSet.size();
  }

  public void setStudentSet(Set<Student> studentSet) {
    this.studentSet = studentSet;
  }

  public Set<Student> getStudentSet() {
    return studentSet;
  }

  private boolean isConflictWithSchedule(Teacher teacher) {
    return teacher.getScheduleSet().contains(schedule);
  }
}
