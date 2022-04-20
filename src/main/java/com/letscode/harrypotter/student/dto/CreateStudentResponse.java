package com.letscode.harrypotter.student.dto;

import com.letscode.harrypotter.student.model.Grade;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class CreateStudentResponse {
  Integer id;
  String name;
  Grade grade;
  UUID sortingHatChoice;
}
