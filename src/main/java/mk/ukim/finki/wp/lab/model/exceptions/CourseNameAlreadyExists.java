package mk.ukim.finki.wp.lab.model.exceptions;

public class CourseNameAlreadyExists  extends RuntimeException{
    public CourseNameAlreadyExists(String course) {
        super(String.format("Course name: %s already exists",course));
    }
}
