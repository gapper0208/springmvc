package com.woniuxy.g_httpmessageconverter.b;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {
	
	@RequestMapping("f1")
	public void f1(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// �����ǳ���Գ������ȡ����ͷ�е�Accept���ڸ���Accpet�����ݣ�����ѡ��ظ��ͻ��˷���Ӧ����
		String accept = request.getHeader("Accept");
		
		System.out.println("�ͻ���˵����Ҫ��" + accept);
		
		if("image/gif".equals(accept)) {
			
			// ���������������, ע�⣬���´��룬����ajax�����޷�����ļ����ء� ����ͨ��ͬ�������������ļ�����
			//  ���ֻҪ�㶮���������������acceptҪʲô���ݣ���������һ�����ʲô���ݣ����ո�ʲô���ݻ����ɳ���Գ�Լ�д�����������
//			response.setHeader("Content-disposition","attachment;filename=abc.gif");
//			String path = request.getRealPath("/images/1.gif");
//			File file = new File(path);
//			InputStream in = new FileInputStream(file);
//			OutputStream out = response.getOutputStream();
//			byte[] buf = new byte[1024];
//			int len;
//			while((len = in.read(buf)) > 0) {
//				out.write(buf,0,len);
//			}
//			in.close();
			
			 response.getWriter().println("happy birthday");
			
		} else {
			response.getWriter().println("money come");
		}
	}
	
	
	@RequestMapping("f2")
	public void f2(@RequestBody String str) {
		System.out.println("UserController.f2():" + str);
	}
	
	
	// application/json: StringHttpMessageConverter MappingJackSon2HttpMessagerConverter
	// supports              false                           true
	
	@RequestMapping("f3")
	public void f3(@RequestBody User user) {
		System.out.println("UserController.f3():" + user);
	}
	
	
	@RequestMapping("f4")
	@ResponseBody
	public String f4() {
		System.out.println("UserController.f4()");
		return "g";
	}
	
	// Accept: woniu/gao  String My 
	// supports: My
	// My : write
	@RequestMapping("f5")
	@ResponseBody
	public User f5() {
		System.out.println("UserController.f5()");
		User user = new User();
		user.setUid(100);
		user.setName("����仨");
		user.setBirthday(new Date());
		return user;  
	}
	
}
