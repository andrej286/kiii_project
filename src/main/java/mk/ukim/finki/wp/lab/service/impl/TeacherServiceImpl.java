package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepository;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

  private final TeacherRepository teacherRepository;

  public TeacherServiceImpl(TeacherRepository inMemoryTeacherRepository) {

    this.teacherRepository = inMemoryTeacherRepository;
  }

  @Override
  public List<Teacher> findAll() {
    return teacherRepository.findAll();
  }

  @Override
  public Teacher findById(Long id) {
    return teacherRepository.findTeacherById(id);
  }

  @Override
  public void addTeacher(String name, String surname) {

    Teacher teacher = new Teacher(name, surname);

    teacherRepository.save(teacher);
  }

  @Override
  public void addTeacher(Teacher teacher) {

    teacherRepository.save(teacher);
  }
}
