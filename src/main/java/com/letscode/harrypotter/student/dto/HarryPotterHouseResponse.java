package com.letscode.harrypotter.student.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class HarryPotterHouseResponse {
  @JsonProperty("name")
  String name;
}
