package mk.ukim.finki.wp.lab.bootstrap;

import lombok.Getter;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//del od DATA slojot
@Component // za da bide singleton , za da se instancira na pocetokot
@Getter
public class DataHolder {


    public static List<Student> students = new ArrayList<>();
    public static List<Student> students2 = new ArrayList<>();
    public static List<Student> students3 = new ArrayList<>();
    public static List<Course> courses = new ArrayList<>();
    public static List<Teacher> teachers = new ArrayList<>();


//    @PostConstruct // ova e potrebno za da se izvrshuva na pocetokot
//    public void init() {
//
////        teachers.add( new Teacher(
////               // 1L,
////                "Boban1","Joksimoski"));
////        teachers.add( new Teacher(
////             //   2L,
////                "Boban2","Joksimoski"));
////        teachers.add( new Teacher(
////              //  3L,
////                "Boban3","Joksimoski"));
////        teachers.add( new Teacher(
////             //   4L,
////                "Boban4","Joksimoski"));
////        teachers.add( new Teacher(
////              //  5L,
////                "Boban5","Joksimoski"));
//
//
//        //Za konstruktor so se
//        teachers.add( new Teacher(1L, "Boban1","Joksimoski"));
//        teachers.add( new Teacher(2L, "Boban2","Joksimoski"));
//        teachers.add( new Teacher(3L, "Boban3","Joksimoski"));
//        teachers.add( new Teacher(4L, "Boban4","Joksimoski" ));
//        teachers.add( new Teacher(5L, "Boban5","Joksimoski"));
//
//
//
//        students2.add(new Student("aa", "aa", "PROBA ", "Stojanovski"));
//
//        students3.add(new Student("aa5", "aa5", "Andrej5", "PROBA"));
//
//
//        students.add(new Student("aa", "aa", "Andrej", "Stojanovski"));
//        students.add(new Student("aa2", "aa2", "Andrej2", "Stojanovski2"));
//        students.add(new Student("aa3", "aa3", "Andrej3", "Stojanovski3"));
//        students.add(new Student("aa4", "aa4", "Andrej4", "Stojanovski4"));
//        students.add(new Student("aa5", "aa5", "Andrej5", "Stojanovski5"));
//
//        courses.add(new Course("veb", "progamiranje", students, teachers.get(0)));
//        courses.add(new Course( "veb2", "progamiranje2", students2, teachers.get(1)));
//        courses.add(new Course("veb3", "progamiranje3", students, teachers.get(2)));
//        courses.add(new Course( "veb4", "progamiranje4", students, teachers.get(3)));
//        courses.add(new Course("veb5", "progamiranje5", students3, teachers.get(4)));
//
//    }
}
