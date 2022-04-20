package com.letscode.harrypotter.student.dto;

import com.letscode.harrypotter.student.model.Student;

public class StudentMapper {

  private StudentMapper() {}

  public static Student toDomain(CreateStudentRequest createStudentRequest) {
    return Student.builder()
        .name(createStudentRequest.getName())
        .grade(createStudentRequest.getGrade())
        .build();
  }

  public static CreateStudentResponse toDto(Student student) {
    return CreateStudentResponse.builder()
        .id(student.getId())
        .name(student.getName())
        .grade(student.getGrade())
        .sortingHatChoice(student.getSortingHatChoice())
        .build();
  }

}
