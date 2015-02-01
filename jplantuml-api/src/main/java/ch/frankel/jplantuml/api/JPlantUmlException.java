package ch.frankel.jplantuml.api;

public class JPlantUmlException extends RuntimeException {

    public JPlantUmlException(String message) {
        super(message);
    }

    public JPlantUmlException(String message, Throwable cause) {
        super(message, cause);
    }

    public JPlantUmlException(Throwable cause) {
        super(cause);
    }
}
