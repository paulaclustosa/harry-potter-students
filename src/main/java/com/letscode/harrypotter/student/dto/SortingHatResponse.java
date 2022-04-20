package com.letscode.harrypotter.student.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.UUID;

@Getter
public class SortingHatResponse {
  @JsonProperty("sorting-hat-choice")
  UUID sortingHatChoice;
}
