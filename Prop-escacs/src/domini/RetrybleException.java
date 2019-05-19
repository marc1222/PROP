package domini;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RetrybleException extends Exception {
    private final static Logger LOGGER = Logger.getLogger("Chess retryable excpetions");
    public RetrybleException() {
        LOGGER.log(Level.INFO, "PRIMITIVE CREATOR OF RETRYABLE EXCEPTION HANDLER");
    }
    public RetrybleException(String s) {
        LOGGER.log(Level.INFO, s);
    }
}
