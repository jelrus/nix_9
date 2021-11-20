package mathset.exceptions;

public class CannotBeAppliedToEmptyCollectionException extends RuntimeException {

    public CannotBeAppliedToEmptyCollectionException(String message) {
        super(message);
    }
}