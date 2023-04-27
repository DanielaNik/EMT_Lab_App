package mk.ukim.finki.lab.emt_lab.exceptions;

public class BookNotAvailableException extends Exception {
    public BookNotAvailableException(Long id) {
        System.out.println("There are no available copies of the book with id " + id);
    }
}
