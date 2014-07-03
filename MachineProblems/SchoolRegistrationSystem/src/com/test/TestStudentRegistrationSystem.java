package com.test;

import com.model.ConflictScheduleException;
import com.model.Day;
import com.model.Enrollment;
import com.model.EnrollmentLoadException;
import com.model.Period;
import com.model.Schedule;
import com.model.Section;
import com.model.SectionOverflowException;
import com.model.Status;
import com.model.Student;
import com.model.Subject;
import com.model.StudentType;

import com.model.SubjectConflictException;
import com.model.SubjectType;

import com.model.Teacher;
import static org.junit.Assert.*;
import org.junit.Test;
public class TestStudentRegistrationSystem {
  @Test
  public void testStudentEnrollment1() throws ConflictScheduleException,
                                              SectionOverflowException,
                                              SubjectConflictException,
                                              EnrollmentLoadException {
    Student student1 = new Student("S001",Status.FRESHMAN, StudentType.REGULAR);
    
    Subject subject1 = new Subject("Math", SubjectType.UNDERGRADUATE);
    Schedule sched1 = new Schedule(Day.ONE, Period.ONE);
    Teacher teacher1 = new Teacher("T001");
    
    Subject subject2 = new Subject("English", SubjectType.UNDERGRADUATE);
    Schedule sched2 = new Schedule(Day.TWO, Period.ONE);
    
    Section section1 = new Section(subject1, sched1, teacher1);
    Section section2 = new Section(subject2, sched2, teacher1);
    
    student1.enroll(section1);
    student1.enroll(section2);
  }
  
  @Test(expected = ConflictScheduleException.class)
  public void testStudentEnrollment2() throws ConflictScheduleException,
                                              SectionOverflowException,
                                              SubjectConflictException,
                                              EnrollmentLoadException {
    Student student1 = new Student("S001",Status.FRESHMAN, StudentType.REGULAR);
    
    Subject subject1 = new Subject("Math", SubjectType.UNDERGRADUATE);
    Schedule sched1 = new Schedule(Day.ONE, Period.ONE);
    Teacher teacher1 = new Teacher("T001");
    
    Subject subject2 = new Subject("Math", SubjectType.UNDERGRADUATE);
    Schedule sched2 = new Schedule(Day.TWO, Period.ONE);
    
    Section section1 = new Section(subject1, sched1, teacher1);
    Section section2 = new Section(subject2, sched2, teacher1);
    
    student1.enroll(section1);
    student1.enroll(section2);
  }
  
  @Test(expected = ConflictScheduleException.class)
  public void testStudentEnrollment3() throws ConflictScheduleException,
                                              SectionOverflowException,
                                              SubjectConflictException,
                                              EnrollmentLoadException {
    Student student1 = new Student("S001",Status.FRESHMAN, StudentType.REGULAR);
    
    Subject subject1 = new Subject("Math", SubjectType.UNDERGRADUATE);
    Schedule sched1 = new Schedule(Day.TWO, Period.ONE);
    Teacher teacher1 = new Teacher("T001");
    
    Subject subject2 = new Subject("English", SubjectType.UNDERGRADUATE);
    Schedule sched2 = new Schedule(Day.TWO, Period.ONE);
    
    Section section1 = new Section(subject1, sched1, teacher1);
    Section section2 = new Section(subject2, sched2, teacher1);
    
    student1.enroll(section1);
    student1.enroll(section2);
  }
  
  @Test
  public void testStudentEnrollmentFullCapacity() throws ConflictScheduleException,
                                                          SectionOverflowException,
                                                         SubjectConflictException,
                                                         EnrollmentLoadException {
    
    Subject subject1 = new Subject("Math", SubjectType.UNDERGRADUATE);
    Schedule sched1 = new Schedule(Day.TWO, Period.ONE);
    Teacher teacher1 = new Teacher("T001");
    
    Section section1 = new Section(subject1, sched1, teacher1);
    
    for(int i = 0; i < 40; i++){
      Student student = new Student("S00"+i,Status.FRESHMAN, StudentType.REGULAR);
      student.enroll(section1);
    }
    assertEquals(section1.size(),40);
  }
  
  @Test(expected = SectionOverflowException.class)
  public void testStudentEnrollmentBeyondCapacity() throws ConflictScheduleException,
                                                          SectionOverflowException,
                                                           SubjectConflictException,
                                                           EnrollmentLoadException {
    
    Subject subject1 = new Subject("Math", SubjectType.UNDERGRADUATE);
    Schedule sched1 = new Schedule(Day.TWO, Period.ONE);
    Teacher teacher1 = new Teacher("T001");
    
    Section section1 = new Section(subject1, sched1, teacher1);
    
    for(int i = 0; i < 41; i++){
      Student student = new Student("S00"+i,Status.FRESHMAN, StudentType.REGULAR);
      student.enroll(section1);
    }
  }
  
  @Test(expected = SubjectConflictException.class)
  public void testStudentEnrollmentCheckPrerequisite1() throws ConflictScheduleException,
                                                          SectionOverflowException,
                                                           SubjectConflictException,
                                                               EnrollmentLoadException {
    
    Subject subject1 = new Subject("Math02", SubjectType.UNDERGRADUATE);
    subject1.getPreRequisiteSubjects().add(new Subject("Math01", SubjectType.UNDERGRADUATE));
    Schedule sched1 = new Schedule(Day.TWO, Period.ONE);
    Teacher teacher1 = new Teacher("T001");
    
    Section section1 = new Section(subject1, sched1, teacher1);
    
    Student student1 = new Student("S001",Status.FRESHMAN, StudentType.REGULAR);
    
    student1.enroll(section1);
  }
  
  @Test
  public void testStudentEnrollmentCheckPrerequisite2() throws ConflictScheduleException,
                                                          SectionOverflowException,
                                                           SubjectConflictException,
                                                               EnrollmentLoadException {
    
    Subject subject1 = new Subject("Math03", SubjectType.UNDERGRADUATE);
    subject1.getPreRequisiteSubjects().add(new Subject("Math01", SubjectType.UNDERGRADUATE));
    subject1.getPreRequisiteSubjects().add(new Subject("Math02", SubjectType.UNDERGRADUATE));
    Schedule sched1 = new Schedule(Day.TWO, Period.ONE);
    Teacher teacher1 = new Teacher("T001");
    
    Section section1 = new Section(subject1, sched1, teacher1);
    
    Student student1 = new Student("S001",Status.FRESHMAN, StudentType.REGULAR);
    Enrollment enrollment1 = new Enrollment();
    
    Subject subject2 = new Subject("Math01", SubjectType.UNDERGRADUATE);
    Schedule sched2 = new Schedule(Day.TWO, Period.ONE);
    Teacher teacher2 = new Teacher("T001");
    Section section2 = new Section(subject2, sched2, teacher2);
    
    enrollment1.getSectionSet().add(section2);
    
    Enrollment enrollment2 = new Enrollment();

    Schedule sched3 = new Schedule(Day.ONE, Period.ONE);
    Section section3 = new Section(subject2, sched3, teacher2);
    
    enrollment2.getSectionSet().add(section3);
    student1.getEnrollmentList().add(enrollment1);
    student1.getEnrollmentList().add(enrollment2);
    
    student1.enroll(section1);
    
    assertEquals(1,section1.size());
  }
  
  @Test(expected = EnrollmentLoadException.class)
  public void testStudentEnrollmentOverloadExceptionFreshman() throws ConflictScheduleException,
                                                          SectionOverflowException,
                                                           SubjectConflictException,
                                                               EnrollmentLoadException {
    
    Subject subject1 = new Subject("Math01", SubjectType.UNDERGRADUATE);
    Schedule sched1 = new Schedule(Day.TWO, Period.ONE);
    Subject subject2 = new Subject("Math02", SubjectType.UNDERGRADUATE);
    Schedule sched2 = new Schedule(Day.TWO, Period.TWO);
    Subject subject3 = new Subject("Math03", SubjectType.UNDERGRADUATE);
    Schedule sched3 = new Schedule(Day.TWO, Period.THREE);
    Subject subject4 = new Subject("Math04", SubjectType.UNDERGRADUATE);
    Schedule sched4 = new Schedule(Day.TWO, Period.FOUR);
    Subject subject5 = new Subject("Math05", SubjectType.UNDERGRADUATE);
    Schedule sched5 = new Schedule(Day.TWO, Period.FIVE);
    Subject subject6 = new Subject("Math06", SubjectType.UNDERGRADUATE);
    Schedule sched6 = new Schedule(Day.TWO, Period.SIX);
    Subject subject7 = new Subject("Math07", SubjectType.UNDERGRADUATE);
    Schedule sched7 = new Schedule(Day.ONE, Period.ONE);
    Teacher teacher1 = new Teacher("T001");
    
    Section section1 = new Section(subject1, sched1, teacher1);
    Section section2 = new Section(subject2, sched2, teacher1);
    Section section3 = new Section(subject3, sched3, teacher1);
    Section section4 = new Section(subject4, sched4, teacher1);
    Section section5 = new Section(subject5, sched5, teacher1);
    Section section6 = new Section(subject6, sched6, teacher1);
    Section section7 = new Section(subject7, sched7, teacher1);
    
    Student student1 = new Student("S001",Status.FRESHMAN, StudentType.REGULAR);
    
    student1.enroll(section1);
    student1.enroll(section2);
    student1.enroll(section3);
    student1.enroll(section4);
    student1.enroll(section5);
    student1.enroll(section6);
    student1.enroll(section7);
  }
  
  @Test
  public void testStudentEnrollmentOverloadExceptionJunior() throws ConflictScheduleException,
                                                          SectionOverflowException,
                                                           SubjectConflictException,
                                                               EnrollmentLoadException {
    
    Subject subject1 = new Subject("Math01", SubjectType.UNDERGRADUATE);
    Schedule sched1 = new Schedule(Day.TWO, Period.ONE);
    Subject subject2 = new Subject("Math02", SubjectType.UNDERGRADUATE);
    Schedule sched2 = new Schedule(Day.TWO, Period.TWO);
    Subject subject3 = new Subject("Math03", SubjectType.UNDERGRADUATE);
    Schedule sched3 = new Schedule(Day.TWO, Period.THREE);
    Subject subject4 = new Subject("Math04", SubjectType.UNDERGRADUATE);
    Schedule sched4 = new Schedule(Day.TWO, Period.FOUR);
    Subject subject5 = new Subject("Math05", SubjectType.UNDERGRADUATE);
    Schedule sched5 = new Schedule(Day.TWO, Period.FIVE);
    Subject subject6 = new Subject("Math06", SubjectType.UNDERGRADUATE);
    Schedule sched6 = new Schedule(Day.TWO, Period.SIX);
    Subject subject7 = new Subject("Math07", SubjectType.UNDERGRADUATE);
    Schedule sched7 = new Schedule(Day.ONE, Period.ONE);
    Teacher teacher1 = new Teacher("T001");
    
    Section section1 = new Section(subject1, sched1, teacher1);
    Section section2 = new Section(subject2, sched2, teacher1);
    Section section3 = new Section(subject3, sched3, teacher1);
    Section section4 = new Section(subject4, sched4, teacher1);
    Section section5 = new Section(subject5, sched5, teacher1);
    Section section6 = new Section(subject6, sched6, teacher1);
    Section section7 = new Section(subject7, sched7, teacher1);
    
    Student student1 = new Student("S001",Status.JUNIOR, StudentType.REGULAR);
    
    student1.enroll(section1);
    student1.enroll(section2);
    student1.enroll(section3);
    student1.enroll(section4);
    student1.enroll(section5);
    student1.enroll(section6);
    student1.enroll(section7);
    
    assertEquals(21,student1.getEnrollment().getTotalNumberOfUnits());
  }
  
  @Test(expected = ConflictScheduleException.class)
  public void testTeacherScheduleConflict() throws ConflictScheduleException,
                                                   SectionOverflowException,
                                                   SubjectConflictException,
                                                   EnrollmentLoadException {
    Subject subject1 = new Subject("Math02", SubjectType.UNDERGRADUATE);
    Schedule sched1 = new Schedule(Day.TWO, Period.ONE);
    Teacher teacher1 = new Teacher("T001");
    
    Subject subject2 = new Subject("Math03", SubjectType.UNDERGRADUATE);
    
    Section section1 = new Section(subject1, sched1, teacher1);
    Section section2 = new Section(subject2, sched1, teacher1);
    
    Student student = new Student("S001", Status.FRESHMAN, StudentType.REGULAR);
    student.enroll(section1);
    student.enroll(section2);
  }
  
  @Test(expected = EnrollmentLoadException.class)
  public void testComputeTuitionUnderload() throws ConflictScheduleException,
                                          SectionOverflowException,
                                          SubjectConflictException,
                                          EnrollmentLoadException {
    Subject subject1 = new Subject("Math02", SubjectType.UNDERGRADUATE);
    Schedule sched1 = new Schedule(Day.TWO, Period.ONE);
    Teacher teacher1 = new Teacher("T001");
    
    Subject subject2 = new Subject("Math03", SubjectType.GRADUATE);
    Schedule sched2 = new Schedule(Day.ONE, Period.ONE);
    
    Section section1 = new Section(subject1, sched1, teacher1);
    Section section2 = new Section(subject2, sched2, teacher1);
    
    Student student = new Student("S001",Status.FRESHMAN,StudentType.HALF);
    student.enroll(section1);
    student.enroll(section2);
    
    assertEquals(5000,student.computeTuitionFee());
  }
  
  @Test
  public void testComputeTuition() throws ConflictScheduleException,
                                          SectionOverflowException,
                                          SubjectConflictException,
                                          EnrollmentLoadException {
    Subject subject1 = new Subject("Math02", SubjectType.UNDERGRADUATE);
    Schedule sched1 = new Schedule(Day.TWO, Period.ONE);
    Teacher teacher1 = new Teacher("T001");
    
    Subject subject2 = new Subject("Math03", SubjectType.GRADUATE);
    Schedule sched2 = new Schedule(Day.ONE, Period.ONE);
    
    Section section1 = new Section(subject1, sched1, teacher1);
    Section section2 = new Section(subject2, sched2, teacher1);
    
    Student student = new Student("S001",Status.SENIOR,StudentType.HALF);
    student.enroll(section1);
    student.enroll(section2);
    
    assertEquals(5000,student.computeTuitionFee());
  }
}
