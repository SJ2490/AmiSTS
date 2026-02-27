package com.mycompany.finalsubmission;

/**
 * Thrown when a slip file record has an invalid format or unknown slip type.
 */
public class InvalidSlipFormatException extends Exception {

    public InvalidSlipFormatException(String message) {
        super(message);
    }
}
