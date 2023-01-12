package pl.comp.view.exceptions;

public class InsertNumberException extends Exception {
    public InsertNumberException(Throwable cause) {
        super(cause);
    }

    public InsertNumberException(String message) {
        super(message);
    }

    public InsertNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
