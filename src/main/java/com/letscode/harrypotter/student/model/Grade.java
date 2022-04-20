package com.letscode.harrypotter.student.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Grade {
  FIRST("1st"),
  SECOND("2nd"),
  THIRD("3rd"),
  FOURTH("4th"),
  FIFTH("5th"),
  SIXTH("6th"),
  SEVENTH("7th"),
  EIGHTH("8th"),
  NINTH("9th");

  private String label;
}
