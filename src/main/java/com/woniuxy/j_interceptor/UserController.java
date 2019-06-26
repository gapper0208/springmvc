package com.woniuxy.j_interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("user")
public class UserController {

	@RequestMapping("save")
	public String save() {
		System.out.println("UserController.save()");
		return "j";
	}
	
}
