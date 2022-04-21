package com.letscode.harrypotter.student.dto;

import com.letscode.harrypotter.student.model.Grade;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetStudentResponse {
  Integer id;
  String name;
  Grade grade;
  String harryPotterHouseName;
}
