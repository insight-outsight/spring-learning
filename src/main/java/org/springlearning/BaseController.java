package org.springlearning;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springlearning.model.BizException;
import org.springlearning.model.ErrorCodeEnum;




public class BaseController {

	private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);
	
	//public static final String _KEY = "";
	
	
	@ExceptionHandler
	@ResponseBody
	public BaseResponse<?> exp(Exception ex, HttpServletRequest request, HttpServletResponse response) {
		
		int errorCode = ErrorCodeEnum.SystemError.code();
		String errorMssage = ErrorCodeEnum.SystemError.name();
		
		if (ex instanceof HttpMessageNotReadableException) {// json format is not match the @RequestBody type
			LOG.error("spring mvc parse json error. request URL:{}",request.getRequestURI());		
		} else if (ex instanceof MissingServletRequestParameterException) {// param-missing
			LOG.error("spring mvc params missing. request URL:{}",request.getRequestURI());
			errorCode = ErrorCodeEnum.MissingRequestParameter.code();
			errorMssage = ErrorCodeEnum.MissingRequestParameter.name();
		} else if (ex instanceof HttpMediaTypeNotAcceptableException) {// mime TYPE NOT MATCH
			LOG.error("spring mvc mime type not match. request URL:{}",request.getRequestURI());
		} else if (ex instanceof BizException) {// 处理BizException
			LOG.error("coupon module biz exception. request URL:{}",request.getRequestURI());
			BizException e = (BizException) ex;
			errorCode = e.getErrorCode().code();
			errorMssage = e.getErrorCode().name();
		} else{

		}
		LOG.error("",ex);

		return generateErrorActivityResponse(errorCode,errorMssage); 
		
	}
	

	@ExceptionHandler(TypeMismatchException.class)
	@ResponseBody
	public BaseResponse<?> expAwardCoupon(Exception ex, HttpServletRequest request, HttpServletResponse response) {
		
		int errorCode = ErrorCodeEnum.IllegalArgument.code();
		String errorMssage = ErrorCodeEnum.IllegalArgument.name();

		LOG.error("http request parameter format not match. request URL:"+request.getRequestURI(),ex);

		return generateErrorActivityResponse(errorCode,errorMssage); 
		
	}
	
	protected BaseResponse<?> generateErrorActivityResponse(int errCode,String errMsg){
		ActivityResponse activityResponse = new ActivityResponse(BaseResponse.Status.Failure);
		activityResponse.setErrCode(errCode);
		activityResponse.setErrMsg(errMsg);
		return activityResponse;
	}
	
}
