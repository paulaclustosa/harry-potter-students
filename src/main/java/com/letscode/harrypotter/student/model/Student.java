package com.letscode.harrypotter.student.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "student")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;
  @Column(name = "name")
  String name;
  @Column(name = "grade")
  Grade grade;
  @Column(name = "sorting_hat_choice")
  UUID sortingHatChoice;

  @Builder
  public Student(String name, Grade grade) {
    this.name = name;
    this.grade = grade;
  }

}
