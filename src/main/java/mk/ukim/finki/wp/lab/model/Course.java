package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long courseId;

  private String name;

  private String description;

  @ManyToMany
  private List<Student> students;

  @ManyToOne
  private Teacher teacher;

  @Enumerated(EnumType.STRING)
  private Type type;

  public Course(String name, String description, List<Student> students, Teacher teacher) {

    this.name = name;
    this.description = description;
    this.students = students;
    this.teacher = teacher;
  }

  public Course() {
  }
}
