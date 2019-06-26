package com.woniuxy.e_simple;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("user")
public class UserController {
	
	@RequestMapping("save")
	public ModelAndView save() {
		System.out.println("save...");
		return null;
	}
	@RequestMapping("delete")
	public ModelAndView delete() {
		System.out.println("delete...");
		return null;
	}
	@RequestMapping("update")
	public ModelAndView update() {
		System.out.println("update...");
		return null;
	}
	
}
