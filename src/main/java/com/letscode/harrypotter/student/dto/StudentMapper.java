package com.letscode.harrypotter.student.dto;

import com.letscode.harrypotter.student.model.Student;

public class StudentMapper {

  private StudentMapper() {}

  public static Student toStudent(CreateStudentRequest createStudentRequest) {
    return Student.builder()
        .name(createStudentRequest.getName())
        .grade(createStudentRequest.getGrade())
        .build();
  }

  public static CreateStudentResponse toCreateStudentResponse(Student student) {
    return CreateStudentResponse.builder()
        .id(student.getId())
        .name(student.getName())
        .grade(student.getGrade())
        .sortingHatChoice(student.getSortingHatChoice())
        .build();
  }

}
