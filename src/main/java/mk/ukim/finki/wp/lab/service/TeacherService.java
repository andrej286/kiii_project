package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Teacher;

import java.util.List;

public interface TeacherService {
  public List<Teacher> findAll();

  public Teacher findById(Long id);

  void addTeacher(String name, String surname);

  void addTeacher(Teacher teacher);
}
