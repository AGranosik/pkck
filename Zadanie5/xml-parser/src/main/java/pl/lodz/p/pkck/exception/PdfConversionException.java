package pl.lodz.p.pkck.exception;

public class PdfConversionException extends Exception {

    public PdfConversionException() {
        super();
    }

    public PdfConversionException(String message) {
        super(message);
    }

    public PdfConversionException(String message, Throwable cause) {
        super(message == null ? cause.getCause().getMessage() : message, cause);
    }

}
