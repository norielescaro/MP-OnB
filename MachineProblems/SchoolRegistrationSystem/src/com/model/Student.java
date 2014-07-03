package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Student {
  private final String studentID;
  private Status status;
  private StudentType type;
  private Enrollment enrollment;
  private List<Enrollment> enrollmentList;
  
  public Student(String studentID, Status status, StudentType type) {
    this.studentID = studentID;
    this.status = status;
    this.type = type;
    enrollment = new Enrollment();
    enrollmentList = new ArrayList<Enrollment>();
  }

  public String getStudentID() {
    return studentID;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Status getStatus() {
    return status;
  }

  public void setType(StudentType type) {
    this.type = type;
  }

  public StudentType getType() {
    return type;
  }

  public void setEnrollment(Enrollment enrollment) {
    this.enrollment = enrollment;
  }

  public Enrollment getEnrollment() {
    return enrollment;
  }

  public void enroll(Section section) throws ConflictScheduleException,
                                             SectionOverflowException,
                                             SubjectConflictException,
                                             EnrollmentLoadException {
    final int SUBJECT_UNIT = 3;
    
    if(!(enrollment.getTotalNumberOfUnits() < status.getMaximumUnits())){
      throw new EnrollmentLoadException("You are not allowed to take more than " + status.getMaximumUnits() + " units");
    }
    
    if(isConflictWithSchedule(section)){
      throw new ConflictScheduleException("You have conflict with this schedule.");
    }
    
    if(!hasTakenPrerequisiteSubjects(section)){
      throw new SubjectConflictException("You haven't taken the Prerequisite subjects of this Subject.");
    }
    
    addToSection(section);
    enrollment.addUnit(SUBJECT_UNIT);
  }

  private boolean isConflictWithSchedule(Section section) throws ConflictScheduleException{
    final Set<Section> sectionSet = enrollment.getSectionSet();

    for(Section sectionItem : sectionSet){
       if(section.getSubject().getSubjectCode().equals(sectionItem.getSubject().getSubjectCode())){
        throw new ConflictScheduleException("You've already enroll in this subject " + section.getSubject().getSubjectCode());
      }else{
        if(section.getSchedule().getDay().equals(sectionItem.getSchedule().getDay()) && section.getSchedule().getPeriod().equals(sectionItem.getSchedule().getPeriod())){
          return true;
        }
      }
    }
    return false;
  }

  public void setEnrollmentList(List<Enrollment> enrollmentList) {
    this.enrollmentList = enrollmentList;
  }

  public List<Enrollment> getEnrollmentList() {
    return enrollmentList;
  }

  private void addToSection(Section section) throws SectionOverflowException {
    final int SECTION_CAPACITY = 40;
    enrollment.getSectionSet().add(section);
    if(section.size() < SECTION_CAPACITY){
      section.getStudentSet().add(this);
    }
    else{
      throw new SectionOverflowException("This Section is Full");
    }
  }

  private boolean hasTakenPrerequisiteSubjects(Section section) {
    final Set<Subject> prerequisiteSubject = section.getSubject().getPreRequisiteSubjects();
    int counter = 0;
    for(Enrollment enrollment : enrollmentList){
      final Set<Section> sectionSet = enrollment.getSectionSet();
      for(Section sectionItem : sectionSet) {
        if(prerequisiteSubject.contains(sectionItem.getSubject())){
          counter++;
        }
      }
    }
    return counter == prerequisiteSubject.size();
  }

  public int computeTuitionFee() throws EnrollmentLoadException {
    if(!hasMinimumLoad()){
      throw new EnrollmentLoadException("You haven't enroll the minimum unit requirement: " + status.getMinimumUnits());
    }
    final int MISCELLANEOUS_FEE = 2000;
    int total = 0;
    for(Section section : enrollment.getSectionSet()){
      total += section.getSubject().getSubjectType().getTuitionFee();
    }
    switch(type){
      case FULL : total = 0;
      break;
      case HALF : total /= 2;
      break;
    }
    
    return total + MISCELLANEOUS_FEE;
  }

  private boolean hasMinimumLoad() {
    return enrollment.getTotalNumberOfUnits() >= status.getMinimumUnits();
  }
}
