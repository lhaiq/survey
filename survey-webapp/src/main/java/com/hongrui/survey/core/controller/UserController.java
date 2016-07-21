package com.hongrui.survey.core.controller;


import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/user")
public class UserController{


	/**
	 * 查询用户
	 * 
	 * @paramsession
	 * @return
	 */

	@RequestMapping(value = "/selectuser", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView selectUser(@Validated User user, HttpServletRequest request) {
        Map map= request.getParameterMap();
		int pages = request.getParameter("page")==null?1: Integer.parseInt(request.getParameter("page")); // 取得当前页数
		int rows = request.getParameter("rows")==null?1: Integer.parseInt(request.getParameter("rows")); // 取得每页显示行数

		List<User> users=new ArrayList<>();
		for (int i=0;i<20;i++){
			User u=new User();
			u.setChinaName("川a"+i);
			u.setId(i+"");
			u.setUserPhone("132456789");
			users.add(u);
		}
		MappingJackson2JsonView jjv=new MappingJackson2JsonView();
		Map attributes = new HashMap();
		attributes.put("total", 20);//总页数
		attributes.put("page", 5);//页码
		attributes.put("records", 1000);//总条数
		attributes.put("rows", users);
		jjv.setAttributesMap(attributes);

		ModelAndView mav = new ModelAndView();
		mav.setView(jjv);
		return mav;
	}

	/**
	 * 更新用户
	 * 
	 * @param
	 * @return
	 */

	@RequestMapping(value = "/updateuser", method = RequestMethod.POST)
	@ResponseBody
	public void updateUser(User user, HttpServletRequest request) {
		String flag = request.getParameter("oper");
		if("add".equals(flag)){
			System.out.println("添加");
		}else if("edit".equals(flag)) {

		}else if("del".equals(flag)) {

		}
		
	}

}
