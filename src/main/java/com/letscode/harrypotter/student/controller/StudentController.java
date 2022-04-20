package com.letscode.harrypotter.student.controller;

import com.letscode.harrypotter.student.dto.CreateStudentRequest;
import com.letscode.harrypotter.student.dto.CreateStudentResponse;
import com.letscode.harrypotter.student.dto.GetStudentResponse;
import com.letscode.harrypotter.student.dto.HouseResponse;
import com.letscode.harrypotter.student.service.StudentCreator;
import com.letscode.harrypotter.student.service.StudentSearcher;
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

  final StudentCreator studentCreator;
  final StudentSearcher studentSearcher;

  @Autowired
  public StudentController(StudentCreator studentCreator, StudentSearcher studentSearcher) {
    this.studentCreator = studentCreator;
    this.studentSearcher = studentSearcher;
  }

  @PostMapping("/students")
  @ApiOperation(value = "Creates a new student.")
  public ResponseEntity<CreateStudentResponse> createStudent(
      @RequestBody CreateStudentRequest createStudentRequest,
      UriComponentsBuilder uriComponentsBuilder
  ) {
    CreateStudentResponse createStudentResponse = studentCreator.execute(createStudentRequest);
    URI uri = uriComponentsBuilder.path("/api/students/{sortingHatChoice}").buildAndExpand(createStudentResponse.getSortingHatChoice()).toUri();
    return ResponseEntity.created(uri).body(createStudentResponse);
  }

  @GetMapping("/students/{id}")
  @ApiOperation(value = "Retrieves a created student and its information.")
  public ResponseEntity<GetStudentResponse> getStudent(
      @PathVariable(value = "id") Integer studentId
  ) throws ChangeSetPersister.NotFoundException {
    return ResponseEntity.ok().body(studentSearcher.execute(studentId));
  }

}
