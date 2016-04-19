package by.vonotirah.linkscutter.service.exceptions;

public class LinkNotFoundException extends RuntimeException {

	public LinkNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public LinkNotFoundException(String message) {
		super(message);
	}

	public LinkNotFoundException() {
	}
}