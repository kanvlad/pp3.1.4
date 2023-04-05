package tech.itmentors.crud.exception;

public class NotAcceptableException extends RuntimeException{

    public NotAcceptableException() {
    }

    public NotAcceptableException(String message) {
        super(message);
    }

}
