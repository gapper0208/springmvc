package com.woniuxy.k_exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

	@RequestMapping("save")
	public String save() {
		// �����±�Խ���쳣
		int[] a = new int[10];
		a[999] = 1;
		// ��ָ���쳣
		String s = null;
		System.out.println(s.charAt(0));
		// ��ѧ�쳣
		System.out.println(8 / 0);
		
		System.out.println("UserController.save()");
		return null;
	}

}
