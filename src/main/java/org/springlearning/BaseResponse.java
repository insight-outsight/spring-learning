package org.springlearning;

public class BaseResponse {

	private String status;
	private int busy;
	private Object data;
	
	
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
	
	public BaseResponse(Status status, Object data) {
		super();
		this.status = status.name();
		this.data = data;
	}
	
	public BaseResponse(Status status, int busy, Object data) {
		super();
		this.status = status.name();
		this.busy = busy;
		this.data = data;
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

	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public static enum Status{
		success,failure;
	}
	
}
