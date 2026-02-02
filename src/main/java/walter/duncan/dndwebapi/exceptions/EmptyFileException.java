package walter.duncan.dndwebapi.exceptions;

public class EmptyFileException extends RuntimeException {
    private static final String defaultMessage = "File may not be empty.";

    public EmptyFileException() {
        this(defaultMessage);
    }

    public EmptyFileException(String message) {
        super(message != null ? message : defaultMessage);
    }
}