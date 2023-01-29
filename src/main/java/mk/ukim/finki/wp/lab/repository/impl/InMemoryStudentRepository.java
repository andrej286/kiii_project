package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryStudentRepository {
    public List<Student> findAll(){
        return DataHolder.students;
    }
    public List<Student> findAllByNameOrSurname(String text , String text2){//text 2 e nepotrebno samo za Jpa da testiram
        return DataHolder.students.stream()
                .filter(i->i.getName().contains(text) || i.getSurname().contains(text)).collect(Collectors.toList());
    }
    public Student findByName(String text){
        return DataHolder.students.stream()
                .filter(i->i.getName().equals(text)).findFirst().get();
    }

}
