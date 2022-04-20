package com.letscode.harrypotter.student.dto;

import com.letscode.harrypotter.student.model.Grade;
import lombok.Getter;

@Getter
public class CreateStudentRequest {
  String name;
  Grade grade;
}
