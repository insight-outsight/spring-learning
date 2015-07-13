package org.springlearning.model;


public enum ErrorCodeEnum {

	SystemError(1),
	IllegalArgument(100),MissingRequestParameter(101);
	
	private final int code;
	
	private ErrorCodeEnum (int code){
		this.code = code;
	}

	public int code() {
		return code;
	}
	
	
}
