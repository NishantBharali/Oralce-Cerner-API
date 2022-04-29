package com.backend.dev.errors;

/**
 * The Class InvalidEndpointException.
 */
public class InvalidEndpointException extends RuntimeException {
    
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new invalid endpoint exception.
	 *
	 * @param message the message
	 */
	public InvalidEndpointException(String message) {
        super(message);
    }

    /**
     * Instantiates a new invalid endpoint exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public InvalidEndpointException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new invalid end point exception.
     *
     * @param cause the cause
     */
    public InvalidEndpointException(Throwable cause) {
        super(cause);
    }
}