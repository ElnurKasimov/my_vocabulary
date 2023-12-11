package example.myVocabulary.exception;

public class InvalidDeletionException extends RuntimeException {

    public InvalidDeletionException() {
        super();
    }

    public InvalidDeletionException(String message) {
        super(message);
    }
}
