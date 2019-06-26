package com.woniuxy.e_simple;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("car")
public class CarController {
	
	@RequestMapping("save")
	public ModelAndView save() {
		System.out.println("car save");
		ModelAndView mav = new ModelAndView("e");
		return mav;
	}
	@RequestMapping("delete")
	public ModelAndView f2() {
		System.out.println("car delete");
		return null;
	}
}
