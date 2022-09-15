package main.exception;

public class AnimalDoesNotExistException extends RuntimeException{
    public AnimalDoesNotExistException(String message) {
        super(message);
    }
}
