package pl.comp.view.exceptions;

import java.io.IOException;

public class LanguageException extends IOException {

    public LanguageException(Throwable cause) {
        super(cause);
    }

    public LanguageException(String message) {
        super(message);
    }

    public LanguageException(String message, Throwable cause) {
        super(message, cause);
    }


}
