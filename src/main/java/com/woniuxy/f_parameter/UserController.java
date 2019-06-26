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
	
	// List�������͵Ĳ�����Ĭ������£�����ȥ������������ģ�
	// Ϊ����List�������͵Ĳ������ܽ��������������Ҫ��ǰ�����һ��ע�⣺@RequestParam
	@RequestMapping("f4")
	public ModelAndView f4(@RequestParam List<String> hobby) {
		System.out.println(hobby);
		System.out.println(hobby.getClass());
		return null;
	}
	
	// Set�������͵Ĳ�����Ĭ������£�����ȥ������������ģ�
	// Ϊ����Set�������͵Ĳ������ܽ��������������Ҫ��ǰ�����һ��ע�⣺@RequestParam
	@RequestMapping("f5")
	public ModelAndView f5(@RequestParam Set<String> food) {
		System.out.println(food);
		System.out.println(food.getClass());
		return null;
	}
	
	// Map�������͵Ĳ�����Ĭ������£�����ȥ������������ģ�
	// Ϊ����Map�������͵Ĳ������ܽ��������������Ҫ��ǰ�����һ��ע�⣺@RequestParam
	// ��ʱ��Map���͵Ĳ������֣��Ͳ������������������һ���ˣ�
	@RequestMapping("f6")
	public ModelAndView f6(@RequestParam Map<String, String> foo) {
		System.out.println(foo);
		System.out.println(foo.getClass());
		return null;
	}
	
	// String[]���飬�������ܽ����������������Ҫ��ǰ�����ؼ�@RequestParam
	@RequestMapping("f7")
	public ModelAndView f7(String[] strs) {
		System.out.println(Arrays.deepToString(strs));
		System.out.println(strs.getClass());
		return null;
	}
	
	
}
