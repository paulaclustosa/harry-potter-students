package com.letscode.harrypotter.student.controller;

import com.letscode.harrypotter.student.dto.*;
import com.letscode.harrypotter.student.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api")
@Api(value = "API REST Harry Potter Students")
@CrossOrigin(origins = "*")
public class StudentController {

  final CreateStudent createStudent;
  final GetStudent getStudent;

  @Autowired
  public StudentController(CreateStudent createStudent, GetStudent getStudent) {
    this.createStudent = createStudent;
    this.getStudent = getStudent;
  }

  @PostMapping("/students")
  @ApiOperation(value = "Creates a new student.")
  public ResponseEntity<CreateStudentResponse> createStudent(
      @RequestBody CreateStudentRequest createStudentRequest,
      UriComponentsBuilder uriComponentsBuilder
  ) {
    CreateStudentResponse createStudentResponse = createStudent.execute(createStudentRequest);
    URI uri = uriComponentsBuilder.path("/api/students/{id}").buildAndExpand(createStudentResponse.getId()).toUri();
    return ResponseEntity.created(uri).body(createStudentResponse);
  }

  @GetMapping("/students/{id}")
  @ApiOperation(value = "Retrieves a created student and its information (id, name, grade, and it's house name - from Harry Potter movie).")
  public ResponseEntity<GetStudentResponse> getStudent(
      @PathVariable(value = "id") Integer studentId
  ) throws ChangeSetPersister.NotFoundException {
    return ResponseEntity.ok().body(getStudent.execute(studentId));
  }

}
