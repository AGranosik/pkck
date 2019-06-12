package pl.lodz.p.pkck.exception;

public class XmlConversionException extends Exception {

    public XmlConversionException() {
        super();
    }

    public XmlConversionException(String message) {
        super(message);
    }

    public XmlConversionException(String message, Throwable cause) {

        super(message == null ? cause.getCause().getMessage() : message, cause);
        System.out.println("tutaj");
    }

}
