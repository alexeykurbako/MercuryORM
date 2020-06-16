package DAO.exception;

public class ConnectionException extends RuntimeException {

    private static final long serialVersionUID = -3093893470684238040L;

    public ConnectionException() {
        super ();
    }

    public ConnectionException(String message) {
        super (message);
    }

    public ConnectionException(String message, Throwable cause) {
        super (message, cause);
    }

    public ConnectionException(Throwable cause) {
        super (cause);
    }

    public ConnectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super (message, cause, enableSuppression, writableStackTrace);
    }
}
