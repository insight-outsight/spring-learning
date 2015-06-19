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
@RequestMapping("/tset2")
public class TestReturnPageController extends BaseController {
	
	private static final Logger LOG = LoggerFactory.getLogger(TestReturnPageController.class);
	
	/**
	 * 
	 * 1.使用 String,ModelAndView作为请求处理方法的返回视图名称，返回视图名称可以不受请求的url绑定，
	 * 这样返回的逻辑视图名不会和请求 URL绑定，String和ModelAndView可以设置返回的视图名称。
	 * 具有很大的灵活性，而模型数据又可以通过 ModelMap控制。
	 * 2.使用void,map,Model时，返回对应的逻辑视图名称真实url为：prefix前缀+视图名称 +suffix后缀组成。
	 * 其中prefix前缀和suffix后缀通过如下ViewResolver定义。
	 * 	<bean id="viewResolver" class="org.springframework.web.servlet.view.UrlBasedViewResolver">
	 *		<propety name="viewClass" value="org.springframework.web.servlet.view.JstlView" />
	 *		<propety name="prefix" value="/WEB-INF/views/" />
	 *		<propety name="suffix" value=".jsp" />
	 *	</bean>
	 * 3.使用@ResponseBody可指定返回类型为application/json。
	 */
	

	@RequestMapping(value="/testReturnJSON.do", method=RequestMethod.GET)
	@ResponseBody
    public BaseResponse testReturnJSON(HttpServletRequest request,
    		HttpServletResponse response){
		
		ActivityResponse activityResponse = new ActivityResponse(BaseResponse.Status.success);
		activityResponse.setErrCode(0);

		try{
			System.out.println("i m okKyyy,bn");
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
	  

	/**
	 * 返回页面映射到/WEB-INF/views/tset/returnMap.jsp. Reason: 
	 * */
	@RequestMapping(value="/returnMap.do", method=RequestMethod.GET)
    public Map<String,String> testReturnMap(HttpServletRequest request,
    		HttpServletResponse response,Model model){
		 Map<String, String> m = new HashMap<String, String>(); 
		 m.put("key1","value-1");
		 System.out.println("bing22345.qnmd gyy4hhh");
		 return m;
	}

	/**
	 * 返回页面映射到/WEB-INF/views/tset/returnMap.jsp. Reason: 
	 * */
	@RequestMapping(value="/returnVoidPage.do", method=RequestMethod.GET)
    public void testReturnVoid(HttpServletRequest request,
    		HttpServletResponse response,Model model){
		
		model.addAttribute("hello","world");

	}

}
