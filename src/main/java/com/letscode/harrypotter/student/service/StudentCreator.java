package com.letscode.harrypotter.student.service;

import com.letscode.harrypotter.student.service.clients.SortingHatClient;
import com.letscode.harrypotter.student.dto.SortingHatResponse;
import com.letscode.harrypotter.student.dto.StudentMapper;
import com.letscode.harrypotter.student.dto.CreateStudentRequest;
import com.letscode.harrypotter.student.dto.CreateStudentResponse;
import com.letscode.harrypotter.student.model.Student;
import com.letscode.harrypotter.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StudentCreator extends StudentService {

  final SortingHatClient sortingHatClient;

  @Autowired
  public StudentCreator(StudentRepository studentRepository, SortingHatClient sortingHatClient) {
    super(studentRepository);
    this.sortingHatClient = sortingHatClient;
  }

  public CreateStudentResponse execute(CreateStudentRequest createStudentRequest) {
    Student student = StudentMapper.toDomain(createStudentRequest);
    SortingHatResponse sortingHatResponse = sortingHatClient.execute();
    UUID sortingHatChoice = sortingHatResponse.getSortingHatChoice();
    student.setSortingHatChoice(sortingHatChoice);
    return StudentMapper.toDto(studentRepository.save(student));
  }
}
