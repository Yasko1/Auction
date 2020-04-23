package by.epam.lab.exception;

public class ServiceException extends Exception {

	/**
	 * Designed for signal that an service access object exception of some sort has
	 * occurred.
	 */
	private static final long serialVersionUID = 1L;

	public ServiceException() {
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
}
