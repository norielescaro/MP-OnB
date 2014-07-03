package com.model;

import java.util.HashSet;
import java.util.Set;

public class Subject {
  private String subjectCode;
  private SubjectType subjectType;
  private Set<Subject> preRequisiteSubjects;
  
  public Subject(String subjectCode, SubjectType subjectType) {
    this.subjectCode = subjectCode;
    this.subjectType = subjectType;
    preRequisiteSubjects = new HashSet<Subject>();
  }

  public void setSubjectCode(String subjectCode) {
    this.subjectCode = subjectCode;
  }

  public String getSubjectCode() {
    return subjectCode;
  }

  public void setPreRequisiteSubjects(Set<Subject> preRequisiteSubjects) {
    this.preRequisiteSubjects = preRequisiteSubjects;
  }

  public Set<Subject> getPreRequisiteSubjects() {
    return preRequisiteSubjects;
  }


  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof Subject)) {
      return false;
    }
    final Subject other = (Subject)object;
    if (!(subjectCode == null ? other.subjectCode == null : subjectCode.equals(other.subjectCode))) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    final int PRIME = 37;
    int result = 1;
    result = PRIME * result + ((subjectCode == null) ? 0 : subjectCode.hashCode());
    return result;
  }

  public SubjectType getSubjectType() {
    return subjectType;
  }
}
