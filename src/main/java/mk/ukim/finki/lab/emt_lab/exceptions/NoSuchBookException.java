package mk.ukim.finki.lab.emt_lab.exceptions;

public class NoSuchBookException extends Exception{
    public NoSuchBookException(Long id) {
        System.out.println("No book with id " + id);
    }
}
