package main.exception;

public class BehaviourDoesNotExistException extends RuntimeException {
    public BehaviourDoesNotExistException(String behaviour) {
        super("The following behaviour you inserted does not exist " + behaviour);
    }
}
