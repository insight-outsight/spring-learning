package org.springlearning;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * 
 * 	<bean class="org.springframework.web.servlet.mvc.support.ControllerClassNameHandlerMapping" >
		<property name="order" value="1" /> 
	</bean>
	测试ControllerClassNameHandlerMapping未成功
 * @author zcx
 *
 */
@Controller
public class TestControllerClassNameHandlerMappingController extends AbstractController{
	
	public ModelAndView insert(HttpServletRequest req, HttpServletResponse res)
			throws ServletRequestBindingException {
		System.out.println("im in");
		String helloWorld = (String) req.getParameter("helloWorld");
		Map model = new HashMap();
		model.put("helloWorld", "您执行的是新增动作：" + helloWorld);
		return new ModelAndView("list", model);
	}
	
	
	@RequestMapping(value="/v2.do", method=RequestMethod.GET)
    public void testReturnVoid(HttpServletRequest request,
    		HttpServletResponse response){
		System.out.println("im in 2");
		try {
			response.getWriter().write("hello,world.");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("im in 444");
		ModelAndView model = new ModelAndView("HelloWorldPage");
		model.addObject("msg", "WelcomeController");
		
		return model;
	}


}
