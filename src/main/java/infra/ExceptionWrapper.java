package infra;

public class ExceptionWrapper extends RuntimeException {

    public ExceptionWrapper(String message) {
        super(message);
    }

    public ExceptionWrapper(String message, Exception e) {
        super(String.format("%s: %s", message, e.getMessage()), e);
    }
}
