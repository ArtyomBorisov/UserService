package it.academy.exception;

public class ServerException extends RuntimeException {
    public ServerException() {
    }

    public ServerException(String message) {
        super(message);
    }

    public ServerException(Causes causes) {
        super(causes.getMessage());
    }

    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerException(Throwable cause) {
        super(cause);
    }

    public enum Causes {
        CONVERSION("Null has been got after conversion"),
        NOT_KNOWN_SORT("There is no sort realization");

        private final String message;

        Causes(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
