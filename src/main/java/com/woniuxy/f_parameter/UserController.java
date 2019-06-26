package com.woniuxy.f_parameter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("user")
public class UserController {
	
	
	@RequestMapping("f1")
	public ModelAndView f1(byte b, short s, int i, long l, float f, double d, boolean bb, char c) {
		System.out.println(b);
		System.out.println(s);
		System.out.println(i);
		System.out.println(l);
		System.out.println(f);
		System.out.println(d);
		System.out.println(bb);
		System.out.println(c);
		return null;
	}
	
	@RequestMapping("f2")
	public ModelAndView f2(String username, String password) {
		System.out.println(username + " : " + password);
		return null;
	}
	
	@RequestMapping("f3")
	public ModelAndView f3(User user) {
		System.out.println(user);
		return null;
	}
	
	// List集合类型的参数，默认情况下，不会去接收请求参数的！
	// 为了让List集合类型的参数，能接收请求参数，需要在前面加上一个注解：@RequestParam
	@RequestMapping("f4")
	public ModelAndView f4(@RequestParam List<String> hobby) {
		System.out.println(hobby);
		System.out.println(hobby.getClass());
		return null;
	}
	
	// Set集合类型的参数，默认情况下，不会去接收请求参数的！
	// 为了让Set集合类型的参数，能接收请求参数，需要在前面加上一个注解：@RequestParam
	@RequestMapping("f5")
	public ModelAndView f5(@RequestParam Set<String> food) {
		System.out.println(food);
		System.out.println(food.getClass());
		return null;
	}
	
	// Map集合类型的参数，默认情况下，不会去接收请求参数的！
	// 为了让Map集合类型的参数，能接收请求参数，需要在前面加上一个注解：@RequestParam
	// 此时，Map类型的参数名字，就不用与请求参数的名字一致了！
	@RequestMapping("f6")
	public ModelAndView f6(@RequestParam Map<String, String> foo) {
		System.out.println(foo);
		System.out.println(foo.getClass());
		return null;
	}
	
	// String[]数组，天生就能接受请求参数，不需要在前面额外地加@RequestParam
	@RequestMapping("f7")
	public ModelAndView f7(String[] strs) {
		System.out.println(Arrays.deepToString(strs));
		System.out.println(strs.getClass());
		return null;
	}
	
	
}
