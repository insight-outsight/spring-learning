package org.springlearning.model;


public class BizException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5325329159894952800L;

	//返回给客户的响应状态码
	private final ErrorCodeEnum errorCode;
	
	//内部标识错误信息的响应码
	private String innerErrorCode;

	public BizException(ErrorCodeEnum errorCode) {
		super();
		this.errorCode = errorCode;
	}
	
	public BizException(ErrorCodeEnum errorCode,
			String message) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public BizException(ErrorCodeEnum errorCode,
			String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}
	
	public BizException(ErrorCodeEnum errorCode,
			String innerErrorCode,String message) {
		super(message);
		this.errorCode = errorCode;
		this.innerErrorCode = innerErrorCode;
	}
	
	public BizException(ErrorCodeEnum errorCode,
			String innerErrorCode,String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
		this.innerErrorCode = innerErrorCode;
	}

	public String getInnerErrorCode() {
		return innerErrorCode;
	}

	public void setInnerErrorCode(String innerErrorCode) {
		this.innerErrorCode = innerErrorCode;
	}

	public ErrorCodeEnum getErrorCode() {
		return errorCode;
	}

	
}
