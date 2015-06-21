package org.springlearning;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springlearning.model.User;


/**
 * 测试Controller中方法返回类型对响应视图的内容的差异
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

@Controller
@RequestMapping("/testReturnPage")
public class TestReturnPageController extends BaseController {
	
	private static final Logger LOG = LoggerFactory.getLogger(TestReturnPageController.class);
	

	@RequestMapping(value="/json.do", method=RequestMethod.GET)
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
	 * 返回页面映射到/WEB-INF/views/testReturnPage/returnMap.jsp.
	 * */
	@RequestMapping(value="/map.do", method=RequestMethod.GET)
    public Map<String,String> testReturnMap(HttpServletRequest request,
    		HttpServletResponse response,Model model){
		 Map<String, String> m = new HashMap<String, String>(); 
		 m.put("key1","value-1");
		 System.out.println("bing22345.qnmd gyy4hhh");
		 return m;
	}

	/**
	 * 当返回类型为void的时候，则响应的视图页面为对应着的访问地址,页面无跳转,即仍然为/testReturnPage/void.do
	 * 这个时候我们一般是将返回结果写在了HttpServletResponse 中了，如果没写的话，spring就会利用
	 * RequestToViewNameTranslator来返回一个对应的视图名称，找不到的话会是空白页面。
	 * 
	 * 适用于ajax的请求
	 * */
	@RequestMapping(value="/void.do", method=RequestMethod.GET)
    public void testReturnVoid(HttpServletRequest request,
    		HttpServletResponse response){
		//model.addAttribute("hello","world");,Model model
		request.setAttribute("k2", "风度mx6");
		try {
			response.getWriter().write("hello,world.");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * ModelMap 返回值为modelMap时，响应页面映射到/WEB-INF/views/testReturnPage/modelMap.jsp.。
	 * 只是存储在MpdelMap中的数据可以在jsp页面中取出。
	 * 
	 */
	@RequestMapping(value="/modelMap.do",params="type=modelMap")
	public ModelMap resultModelMap(HttpServletRequest request,
    		HttpServletResponse response,ModelMap map){
		map.put("msg", "这里是modleMap中的数据2222");
		return map;
	}

	@RequestMapping(value="list.do",params="type=list_string")
	public List<User> resultList_String(){
		List<User> ls = new ArrayList<User>();
		ls.add(new User("张三"));
		ls.add(new User("李四"));
		return ls;
	}
	
}
