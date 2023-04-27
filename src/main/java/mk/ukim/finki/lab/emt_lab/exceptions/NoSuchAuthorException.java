package mk.ukim.finki.lab.emt_lab.exceptions;

public class NoSuchAuthorException extends Exception{
    public NoSuchAuthorException(String name, String surname) {
        System.out.println("No author named "+name+" "+surname);
    }
}
