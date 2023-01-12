package pl.comp.view.exceptions;

import java.io.IOException;

public class FileException extends IOException {
    public FileException(Throwable cause) {
        super(cause);
    }

    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }
}
