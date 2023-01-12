package pl.comp.view.exceptions;

public class SaveFileException extends Exception {
    public SaveFileException(Throwable cause) {
        super(cause);
    }

    public SaveFileException(String message) {
        super(message);
    }

    public SaveFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
