package local.jnitor.exceptions;

public class JnitorException extends Exception {

	/**
	 * UID for serialization format
	 */
	private static final long serialVersionUID = 8622566731817330917L;

	public JnitorException() {
		super();
	}

	public JnitorException(String what) {
		super(what);
	}

	public JnitorException(Throwable cause) {
		super(cause);
	}

	public JnitorException(String what, Throwable cause) {
		super(what, cause);
	}

	public JnitorException(String what, Throwable cause, boolean arg2, boolean arg3) {
		super(what, cause, arg2, arg3);
	}

}
