package by.epam.lab.exception;
/**
 * This class represents an exception in the DAO configuration which cannot be
 * resolved at runtime, such as a missing resource in the class path, a missing
 * property in the properties file, etc.
 */
public class DaoConfigException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DaoConfigException(String message) {
		super(message);
	}
	public DaoConfigException(Throwable cause) {
		super(cause);
	}

	public DaoConfigException(String message, Throwable cause) {
		super(message, cause);
	}

}