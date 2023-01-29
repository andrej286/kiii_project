package mk.ukim.finki.wp.lab.repository.jpa;


import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

  List<Student> findAllByNameOrSurname(String name, String surname);

  Student findDistinctFirstByName(String name);

  Student findFirstByUsername(String username);

  Student findStudentByUsername(String username);
}
