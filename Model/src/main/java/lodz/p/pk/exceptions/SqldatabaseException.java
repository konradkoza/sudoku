package lodz.p.pk.exceptions;

import java.io.IOException;

public class SqldatabaseException extends IOException {
    public SqldatabaseException(String message) {
        super(message);
    }

    public SqldatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public SqldatabaseException(Throwable cause) {
        super(cause);
    }
}

