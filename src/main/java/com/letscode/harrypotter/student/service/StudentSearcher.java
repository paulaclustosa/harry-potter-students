package com.letscode.harrypotter.student.service;

import com.letscode.harrypotter.student.dto.GetStudentResponse;
import com.letscode.harrypotter.student.dto.HouseResponse;
import com.letscode.harrypotter.student.model.Student;
import com.letscode.harrypotter.student.repository.StudentRepository;
import com.letscode.harrypotter.student.service.clients.HouseClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class StudentSearcher extends StudentService {

  final HouseClient houseClient;

  @Autowired
  public StudentSearcher(StudentRepository studentRepository, HouseClient houseClient) {
    super(studentRepository);
    this.houseClient = houseClient;
  }

  public GetStudentResponse execute(Integer id) throws ChangeSetPersister.NotFoundException {
    Optional<Student> student = Optional.ofNullable(studentRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new));
    Student studentFound = student.get();
    String sortingHatChoice = studentFound.getSortingHatChoice().toString();
    HouseResponse houseResponse = houseClient.execute(sortingHatChoice);

    return GetStudentResponse.builder()
        .id(studentFound.getId())
        .name(studentFound.getName())
        .grade(studentFound.getGrade())
        .houseName(houseResponse.getName())
        .build();
    }
}

