package exceptions;

public class OrderDetailException extends RuntimeException {
    public OrderDetailException(String message) {
        super(message);
    }
}
