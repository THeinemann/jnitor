package com.github.theinemann.jnitor.exceptions;

public class InitializationException extends JnitorException {

	/**
	 * UID for serialization format
	 */
	private static final long serialVersionUID = 6804564306354093526L;

	public InitializationException() {
		super();
	}

	public InitializationException(String what) {
		super(what);
	}

	public InitializationException(Throwable cause) {
		super(cause);
	}

	public InitializationException(String what, Throwable cause) {
		super(what, cause);
	}

	public InitializationException(String what, Throwable cause, boolean arg2, boolean arg3) {
		super(what, cause, arg2, arg3);
	}

}
