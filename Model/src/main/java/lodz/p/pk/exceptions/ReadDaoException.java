package lodz.p.pk.exceptions;

import java.io.IOException;

public class ReadDaoException extends IOException {
    public ReadDaoException(String message) {
        super(message);
    }

    public ReadDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReadDaoException(Throwable cause) {
        super(cause);
    }
}
