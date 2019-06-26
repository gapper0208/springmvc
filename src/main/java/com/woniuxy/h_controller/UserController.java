package com.woniuxy.h_controller;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

	// 1. 控制器的方法，返回值类型可以是java.lang.String
	//  此时，返回的字符串就表示逻辑视图的名字！
	// 此时，要给页面传递参数，就可以通过request来传递！（当然，必须在方法参数列表中，写一个HttpServletRequest类型的参数）
	// 也可以使用Model对象，来给视图传数据（必须写一个Model类型的参数）
	@RequestMapping("f1")
	public String f1(Model model) {
		System.out.println("f1()");
		model.addAttribute("abc", "呵呵呵");
		Date dd = new Date();
		// 键值默认为dd的所属类的类名首字母小写：date
		model.addAttribute(dd);
		User u = new User();
		u.setUid(123);
		u.setName("嘻嘻嘻");
		model.addAttribute(u);
		return "h";  // /h.jsp
	}
	
	// 2. 控制器的方法中如何实现重定向
	@RequestMapping("f2")
	public String f2() {
		System.out.println("f2()");
		// 重定向时，必须自己手动加前缀和后缀
		return "redirect:/h.jsp";  
	}
	
	// 3. 控制器的方法可以向servlet容器索取web元素，但是只能获取：request、response、session这三个！
	@RequestMapping("f3")
	public String f3(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		System.out.println("f3()");
		System.out.println(request);
		System.out.println(response);
		System.out.println(session);
		return "h";  
	}
	
}
