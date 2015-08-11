package org.springlearning.model.result;

import java.util.Date;
import java.util.List;

import org.springlearning.model.result.BaseResponse.Status;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActivityDataResponse{

	private String status;
	private Integer errorCode;
	private String errorMessage;
	private Date startDate;
	private Date endDate;
	private List<String> coupons;
	
	public ActivityDataResponse() {
		super();
	}
	public ActivityDataResponse(Status status) {
		super();
		this.status = status.name();
	}
	public ActivityDataResponse(Status status, Integer errorCode, String errorMessage) {
		super();
		this.status = status.name();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status.name();
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public List<String> getCoupons() {
		return coupons;
	}
	public void setCoupons(List<String> coupons) {
		this.coupons = coupons;
	}
		
}