package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

  private final StudentRepository studentRepository;

  public StudentServiceImpl(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @Override
  public List<Student> listAll() {
    return studentRepository.findAll();
  }


  @Override
  public List<Student> searchByNameOrSurname(String text) {
    return studentRepository.findAllByNameOrSurname(text, text); // dodadeno
  }

  @Override
  public Student findByName(String text) {
    return studentRepository.findDistinctFirstByName(text);  //jpa
  }


  @Override
  public void save(String username, String password, String name, String surname) {
    this.studentRepository.save(new Student(username, password, name, surname));
  }
}
