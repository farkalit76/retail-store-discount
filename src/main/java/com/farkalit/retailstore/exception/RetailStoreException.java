/**
 * 
 */
package com.farkalit.retailstore.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * @File name: RetailStoreException.java
 * This class .....
 *
 * @author name: Farkalit Usman (S785410)
 * @Created on: 26 May 2019
 */
public class RetailStoreException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RetailStoreException() {
		super();
	}

	public RetailStoreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RetailStoreException(String message, Throwable cause) {
		super(message, cause);
	}

	public RetailStoreException(String message) {
		super(message);
	}

	public RetailStoreException(Throwable cause) {
		super(cause);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

	@Override
	public String getLocalizedMessage() {
		return super.getLocalizedMessage();
	}

	@Override
	public synchronized Throwable getCause() {
		return super.getCause();
	}

	@Override
	public synchronized Throwable initCause(Throwable cause) {
		return super.initCause(cause);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public void printStackTrace() {
		super.printStackTrace();
	}

	@Override
	public void printStackTrace(PrintStream s) {
		super.printStackTrace(s);
	}

	@Override
	public void printStackTrace(PrintWriter s) {
		super.printStackTrace(s);
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return super.fillInStackTrace();
	}

	@Override
	public StackTraceElement[] getStackTrace() {
		return super.getStackTrace();
	}

	@Override
	public void setStackTrace(StackTraceElement[] stackTrace) {
		super.setStackTrace(stackTrace);
	}
}
