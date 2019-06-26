package com.woniuxy.d_annotation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("car")
public class CarController {
	
	@RequestMapping("save")
	public ModelAndView save() {
		return null;
	}
	@RequestMapping("delete")
	public ModelAndView f2() {
		return null;
	}
}
