package org.springlearning;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;




public class BaseController {

	private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);
	
	//public static final String _KEY = "";
	
	
	@ExceptionHandler
	@ResponseBody
	public BaseResponse exp(Exception ex, HttpServletRequest request, HttpServletResponse response) {
		
		if (ex instanceof HttpMessageNotReadableException) {// json format is not match the @RequestBody type
			LOG.error("spring mvc parse json error. request URL:{}",request.getRequestURI());		
		} else if (ex instanceof MissingServletRequestParameterException) {// param-missing
			LOG.error("spring mvc params missing. request URL:{}",request.getRequestURI());	
		} else if (ex instanceof HttpMediaTypeNotAcceptableException) {// mime TYPE NOT MATCH
			LOG.error("spring mvc mime type not match. request URL:{}",request.getRequestURI());	
		} else{
    		LOG.error("spring mvc error:");
		}
		System.out.println("er");
		LOG.error("",ex);

		return generateErrorActivityResponse(-1,"system error"); 
		
	}
	

	protected BaseResponse generateErrorActivityResponse(int errCode,String errMsg){
		ActivityResponse activityResponse = new ActivityResponse(BaseResponse.Status.failure);
		activityResponse.setErrCode(errCode);
		activityResponse.setErrMsg(errMsg);
		return activityResponse;
	}
	
}
