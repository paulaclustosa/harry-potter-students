package com.letscode.harrypotter.student.service;

import com.letscode.harrypotter.student.dto.GetStudentResponse;
import com.letscode.harrypotter.student.dto.HarryPotterHouseResponse;
import com.letscode.harrypotter.student.model.Student;
import com.letscode.harrypotter.student.repository.StudentRepository;
import com.letscode.harrypotter.student.service.clients.HarryPotterHouseClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class GetStudent extends StudentService {

  final HarryPotterHouseClient harryPotterHouseClient;

  @Autowired
  public GetStudent(StudentRepository studentRepository, HarryPotterHouseClient harryPotterHouseClient) {
    super(studentRepository);
    this.harryPotterHouseClient = harryPotterHouseClient;
  }

  public GetStudentResponse execute(Integer id) throws ChangeSetPersister.NotFoundException {
    Optional<Student> student = Optional.ofNullable(studentRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new));
    Student studentFound = student.get();
    String sortingHatChoice = studentFound.getSortingHatChoice().toString();
    HarryPotterHouseResponse harryPotterHouseResponse = harryPotterHouseClient.getHarryPotterHouse(sortingHatChoice);

    return GetStudentResponse.builder()
        .id(studentFound.getId())
        .name(studentFound.getName())
        .grade(studentFound.getGrade())
        .harryPotterHouseName(harryPotterHouseResponse.getName())
        .build();
    }
}

