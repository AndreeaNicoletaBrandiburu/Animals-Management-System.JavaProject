package main.exception;

public class GenderDoesNotExistException extends RuntimeException{
    public GenderDoesNotExistException(String gender){
        super("The following gender you inserted does not exist " + gender);
    }
}
