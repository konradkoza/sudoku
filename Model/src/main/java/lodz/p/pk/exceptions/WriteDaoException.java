package lodz.p.pk.exceptions;

import java.io.IOException;

public class WriteDaoException extends IOException {
    public WriteDaoException(String message) {
        super(message);
    }

    public WriteDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public WriteDaoException(Throwable cause) {
        super(cause);
    }
}
