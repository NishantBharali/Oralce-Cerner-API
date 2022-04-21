package com.backend.dev.errors;

/**
 * The Class SuccessResponse.
 */
public class SuccessResponse {

    private int status;
    
    private String message;

    /**
     * Instantiates a new success response.
     */
    public SuccessResponse() {
    }

    /**
     * Instantiates a new success response.
     *
     * @param status the status
     * @param message the message
     */
    public SuccessResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param message the new message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}