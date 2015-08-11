package org.springlearning.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录逻辑
 *
 */
@Controller
@RequestMapping("/tvr")
public class TestViewResolverController {

    @RequestMapping("/hello")
    private String hello() {
        System.out.println("-------hello!------");
        return "hello";
    }
    
    /*
     * @RequestMapping配置请求地址
     * @RequestParam将请求中的参数注入
     */
     @RequestMapping(value="/register")
     public ModelAndView register(@RequestParam("username") String username,
    		 @RequestParam("password") String password) {
    	 System.out.println("register a new user");
	     ModelAndView modelView = new ModelAndView();
	     Map<String, Object> modelMap = new HashMap<String, Object>();
	     boolean status = true;//userService.addUser(username, password);
	     /*if(status) {
	         modelMap.put("status", true);
	     } else {
	         modelMap.put("status", false);
	     }*/
	     modelMap.put("status",status);
	     modelMap.put("date", "2011-08-25");
	     modelView.addAllObjects(modelMap);
	     return modelView;
     }
     
    @RequestMapping("/yy")
    private ModelMap mp() {
        System.out.println("-------ModelMap!------");
        ModelMap map = new ModelMap();
        map.put("mobile_number", "13800138000");
        map.put("as1", "sadfas");
        map.put("as2", "sadfas");
        return map;
    }
    
    @RequestMapping("/ye")
    private ModelAndView mp2() {
        System.out.println("-------ModelMap!------");
        ModelAndView mv = new ModelAndView("jackson2JsonView");
        ModelMap map = mv.getModelMap();
        map.put("as1", "sadfas1");
        map.put("as2", "sadfas2");
        mv.setViewName("mp2");
        return mv;
    }
    
}