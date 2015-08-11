package org.springlearning.web.controller;
/*package org.springlearning.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gmobi.bean.Page;
import com.gmobi.bean.report.AppDataBean;
import com.gmobi.bean.report.ReportResBean;
import com.gmobi.bean.user.UserApp;
import com.gmobi.persistence.report.ReportMapper;
import com.gmobi.service.report.ReportService;

*//**
 * 测试各种json返回情况
 * @author gongsy
 *
 *//*
@Controller
public class TestJosnController {
	private static final Logger logger = Logger.getLogger(TestJosnController.class);

	*//**
	 * 分页查询
	 *//*
	@RequestMapping(value = "/testjson/findByPage")
	public ModelAndView findByPage(
			@RequestParam(value = "start", required = true)String start, 
			@RequestParam(value="limit",required=true)String limit) {
		ModelMap modelMap = new ModelMap();
		
		Page<UserApp> page = new Page<UserApp>();

		//page.setConditions();
		page.setStart(Integer.parseInt(start));
		page.setLimit(Integer.parseInt(limit));
		
		UserApp u = new UserApp();
		u.setApp_name("name123");
		u.setApp_sn(123);
		u.setLang("CN");
		
		List<UserApp> list = new ArrayList<UserApp>();
		list.add(u);
		
		page.setList(list);
		
		page.setTotalProperty(1000);
		try {
			modelMap.put("totalProperty", page.getTotalProperty());
			modelMap.put("root", page.getList());
			modelMap.put("success", true);
			return new ModelAndView("jacksonJsonView", modelMap);
		} catch (Exception e) {
			logger.error("Exception",e);
			modelMap.put("success", false);
			return new ModelAndView("jacksonJsonView", modelMap);
		}
	}
	*//**
	 * 返回list
	 * @param funcPid
	 * @param session
	 * @return
	 *//*
	@RequestMapping(value = "/testjson/loadFunction")
	public @ResponseBody List<UserApp> loadFunction(@RequestParam("funcPid") String funcPid,HttpSession session) {
		UserApp u = new UserApp();
		u.setApp_name("name123");
		u.setApp_sn(123);
		u.setLang("CN");
		
		List<UserApp> list = new ArrayList<UserApp>();
		list.add(u);
//		
//		List<String> list = new ArrayList<String>();
//		list.add("a");
//		list.add("b");
//		list.add("c");
//		list.add("d");
//		list.add("e");
		return list;
	}
	*//**
	 * 使用自定义JSON view
	 * @param request
	 * @return
	 *//*
	@RequestMapping(value = "/testjson/getGson")
	public ModelAndView loginVld(HttpServletRequest request) {
		ModelAndView modelMap  = new ModelAndView("gsonView");
		modelMap.addObject("success", true);
		modelMap.addObject("msg", "使用自定义JSON View");

		return modelMap;
	}
	
	
	*//**
	 * @param request
	 * @return
	 *//*
	@RequestMapping(value = "/testjson/login")
	public @ResponseBody ModelMap login(HttpServletRequest request) {
		ModelMap modelMap = new ModelMap();
		modelMap.put("success", false);
		modelMap.put("msg", "用户名或密码错误");

		return modelMap;
	}
	
	@Resource ReportMapper reportMapper;
	@RequestMapping(value = "/testjson/getAdvCountry")
	public ModelAndView getAdvCountry(
			@RequestParam(value = "user_id", required = true) String user_id,
			@RequestParam(value = "sess_id", required = true) String sess_id,
			@RequestParam(value = "app_sn", required = true) String app_sn,
			@RequestParam(value = "date_start", required = true) String date_start,
			@RequestParam(value = "date_end", required = true) String date_end,
			@RequestParam(value = "period", required = true) String period) {
		ModelMap modelMap = new ModelMap();
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("user_id", user_id);
		m.put("app_sn",  app_sn);
		m.put("date_start", date_start);
		m.put("date_end", date_end);
		m.put("period", period);

		try {

			Map<String,AppDataBean> apps = reportService.getAdvPaidInstall(user_id, app_sn, date_start, date_end, period);
			
			modelMap.put("status", "succeed");
			modelMap.put("report", apps);
			
			return new ModelAndView("gsonView", modelMap);
		} catch (Exception e) {
			logger.error("Exception",e);
			modelMap.put("status", "error");
			modelMap.put("error_msg", "Failed to get data, please try again!");
			return new ModelAndView("jacksonJsonView", modelMap);
		}
	}
	

}
*/