package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryCourseRepository {

    public List<Course> findAll(){
        return DataHolder.courses;
    }
    public Course findById(Long courseId){
       if(DataHolder.courses.stream().anyMatch(i->i.getCourseId().equals(courseId))){
           return DataHolder.courses.stream().filter(i->i.getCourseId().equals(courseId)).findFirst().get();
       }
       return null;
    }
    public List<Student> findAllStudentsByCourse(Long courseId){
        if(DataHolder.courses.stream().anyMatch(i->i.getCourseId().equals(courseId))){
            return DataHolder.courses.stream().filter(i->i.getCourseId().equals(courseId)).findFirst().get().getStudents();
        }
        return null;
    }
    public Course addStudentToCourse(Student student, Course course){
        DataHolder.courses.removeIf(i->i.getCourseId().equals(course.getCourseId()));
        course.getStudents().add(student);
        DataHolder.courses.add(course);
        return course;
    }
    public void removeCourse(Long courseId){
        DataHolder.courses.remove(findById(courseId));
    }

   //za error poraka
    public void updateExistingCourse(String courseName, String description, Long teacherId, Long id){
       Course c = findAll().stream().filter(i->i.getCourseId().equals(id)).findFirst().get();
       c.setTeacher(DataHolder.teachers.stream().filter(i->i.getId().equals(teacherId)).findFirst().get());
       c.setName(courseName);
       c.setDescription(description);
    }
//    //za eror poraka
    public boolean hasCourseWithNameAndId(String courseName, Long id) {
        return DataHolder.courses.stream().anyMatch(i->(i.getName().equals(courseName) && !i.getCourseId().equals(id)));

    }
    public boolean hasCourseWithName(String courseName) {
        return DataHolder.courses.stream().anyMatch(i->(i.getName().equals(courseName)));
    }

    public void addNew(String courseName, String description,List<Student> students, Teacher teacher){
        DataHolder.courses.add(new Course(courseName, description, students, teacher));
    }


}