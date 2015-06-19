package org.springlearning;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;




@Controller
@RequestMapping("/tset")
public class TestController extends BaseController {
	
	private static final Logger LOG = LoggerFactory.getLogger(TestController.class);
	

	@RequestMapping(value="/getActivities.do", method=RequestMethod.GET)
	@ResponseBody
    public BaseResponse handleImportExcel(HttpServletRequest request,
    		HttpServletResponse response){
		
		ActivityResponse activityResponse = new ActivityResponse(BaseResponse.Status.success);
		activityResponse.setErrCode(0);

		try{
			System.out.println("i m okKyyy,，ooim00012356789,10u");
			//response.setContentType("text/html");
			

		} catch (Exception e) {
			LOG.error("",e);

		} finally {

		}
		if(System.currentTimeMillis()>1)
		throw new RuntimeException("guai le");
//		return "koyo-PS_2";
		return activityResponse;
		
		
    }
	  

	
}
