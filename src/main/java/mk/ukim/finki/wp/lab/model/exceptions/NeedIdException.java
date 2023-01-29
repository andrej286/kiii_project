package mk.ukim.finki.wp.lab.model.exceptions;

public class NeedIdException extends RuntimeException{
    public NeedIdException() {
        super("Invalid arguments exception");
    }
}
