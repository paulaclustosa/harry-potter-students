package com.letscode.harrypotter.student.service;

import com.letscode.harrypotter.student.service.clients.SortingHatClient;
import com.letscode.harrypotter.student.dto.SortingHatChoiceResponse;
import com.letscode.harrypotter.student.dto.StudentMapper;
import com.letscode.harrypotter.student.dto.CreateStudentRequest;
import com.letscode.harrypotter.student.dto.CreateStudentResponse;
import com.letscode.harrypotter.student.model.Student;
import com.letscode.harrypotter.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateStudent extends StudentService {

  final SortingHatClient sortingHatClient;

  @Autowired
  public CreateStudent(StudentRepository studentRepository, SortingHatClient sortingHatClient) {
    super(studentRepository);
    this.sortingHatClient = sortingHatClient;
  }

  public CreateStudentResponse execute(CreateStudentRequest createStudentRequest) {
    Student student = StudentMapper.toStudent(createStudentRequest);
    SortingHatChoiceResponse sortingHatChoiceResponse = sortingHatClient.getSortingHatChoice();
    UUID sortingHatChoice = sortingHatChoiceResponse.getSortingHatChoice();
    student.setSortingHatChoice(sortingHatChoice);
    return StudentMapper.toCreateStudentResponse(studentRepository.save(student));
  }
}
