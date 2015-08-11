package org.springlearning.model.result;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActivityResponse extends BaseResponse<Map<String,ActivityDataResponse>>{

	
	public ActivityResponse() {
		super();
	}
	
	public ActivityResponse(Status status) {
		super(status);
	}
	
	public ActivityResponse(int errorCode, String errorMessage) {
		super(errorCode,errorMessage);
	}
	


}
