package demo.src.exception;

public class ServiceException extends Exception {

    private static final long serialVersionUID = -4419182900921781655L;

    public ServiceException() {
        super ();
    }

    public ServiceException(String message) {
        super (message);
    }

    public ServiceException(String message, Throwable cause) {
        super (message, cause);
    }

    public ServiceException(Throwable cause) {
        super (cause);
    }

    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super (message, cause, enableSuppression, writableStackTrace);
    }
}