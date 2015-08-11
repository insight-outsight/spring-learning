package org.springlearning.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springlearning.aop.Eat;
import org.springlearning.model.User;
import org.springlearning.model.result.ActivityResponse;
import org.springlearning.model.result.BaseResponse;

/**
 * 测试在请求参数传入枚举值，由mvc:annotation-driven的conversion-service="conversionService"定义
 * 的org.springlearning.converters.UserLevelEnumConverter来
 * 执行从数字（比如1，2）到 UserLevelEnum的转换
 * 否则报错：
 * org.springframework.beans.TypeMismatchException: 
 * Failed to convert value of type 'java.lang.String' to required type 'UserLevelEnum'; 
 *
 * @author xuzhengchao
 *
 */
@Controller
@RequestMapping("/testRequestBodyContainEnum")
public class TestRequestBodyContainEnumController extends BaseController {
	
	private static final Logger LOG = LoggerFactory.getLogger(TestRequestBodyContainEnumController.class);
	

	@RequestMapping(value="/test.do", method=RequestMethod.GET)
	@ResponseBody
    public BaseResponse test(User user,HttpServletRequest request,
    		HttpServletResponse response){
		
		ActivityResponse activityResponse = new ActivityResponse(BaseResponse.Status.Success);
		activityResponse.setErrorCode(0);

		try{
			System.out.println(user);
			System.out.println(user.getUserLevel().name());
			//response.setContentType("text/html");		
		} catch (Exception e) {
			LOG.error("",e);
		} finally {

		}

		return activityResponse;	
		
    }

}
