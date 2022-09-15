package main.exception;

public class ParrotMustTalkException extends RuntimeException {
    public ParrotMustTalkException() {
        super("Parrot must talk");
    }
}
