package org.springlearning;


public class BaseResponse<T> {

	private String status;
	private int busy;
	private T data;
	
	private Integer errorCode;
	private String errorMessage;
	
	
	public BaseResponse() {
		super();
	}

	public BaseResponse(String status) {
		super();
		this.status = status;
	}
	
	public BaseResponse(Status status) {
		super();
		this.status = status.name();
	}
	
	public BaseResponse(Status status, int busy) {
		super();
		this.status = status.name();
		this.busy = busy;
	}
	
	public BaseResponse(Status status, T data) {
		super();
		this.status = status.name();
		this.data = data;
	}
	
	public BaseResponse(Status status, int busy, T data) {
		super();
		this.status = status.name();
		this.busy = busy;
		this.data = data;
	}
		
	public BaseResponse(int errorCode, String errorMessage){
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getBusy() {
		return busy;
	}

	public void setBusy(int busy) {
		this.busy = busy;
	}

	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	public static enum Status{
		Success,Failure;
	}
	
}
