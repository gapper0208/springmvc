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

	// 1. �������ķ���������ֵ���Ϳ�����java.lang.String
	//  ��ʱ�����ص��ַ����ͱ�ʾ�߼���ͼ�����֣�
	// ��ʱ��Ҫ��ҳ�洫�ݲ������Ϳ���ͨ��request�����ݣ�����Ȼ�������ڷ��������б��У�дһ��HttpServletRequest���͵Ĳ�����
	// Ҳ����ʹ��Model����������ͼ�����ݣ�����дһ��Model���͵Ĳ�����
	@RequestMapping("f1")
	public String f1(Model model) {
		System.out.println("f1()");
		model.addAttribute("abc", "�ǺǺ�");
		Date dd = new Date();
		// ��ֵĬ��Ϊdd�����������������ĸСд��date
		model.addAttribute(dd);
		User u = new User();
		u.setUid(123);
		u.setName("������");
		model.addAttribute(u);
		return "h";  // /h.jsp
	}
	
	// 2. �������ķ��������ʵ���ض���
	@RequestMapping("f2")
	public String f2() {
		System.out.println("f2()");
		// �ض���ʱ�������Լ��ֶ���ǰ׺�ͺ�׺
		return "redirect:/h.jsp";  
	}
	
	// 3. �������ķ���������servlet������ȡwebԪ�أ�����ֻ�ܻ�ȡ��request��response��session��������
	@RequestMapping("f3")
	public String f3(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		System.out.println("f3()");
		System.out.println(request);
		System.out.println(response);
		System.out.println(session);
		return "h";  
	}
	
}
