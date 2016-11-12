/**
 * 
 */
package com.wiseboys.mmhackathon.exceptions;

/**
 * Common exception to log all invalid string
 * @author Irudaya Raj
 * Nov 10, 2016 2016
 */
public class InvalidInputException extends Exception {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Public constructor for webs service input exception
     * @param msg
     */
    public InvalidInputException(String msg) {
       super(msg);
    }
}
