package com.ksyzt.gwt.shared.exception;

public class AdminLoginException extends Exception  implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	public static final int AE_MSG = 2;
	private int exceptionType = 0;
	public static final int AE_FILEERROR = 0;
	public static final int AE_NOFILE = 1;

	public AdminLoginException()
	{
		
	}
	public AdminLoginException(String msg) {
		super(msg);
		exceptionType = AE_FILEERROR;
	}

	public AdminLoginException(int type, String msg) {
		super(msg);
		exceptionType = type;
	}

	public int getType() {
		return exceptionType;
	}
}
