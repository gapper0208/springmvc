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
		
		// 由我们程序猿，来获取请求头中的Accept，在根据Accpet的内容，来有选择地给客户端发响应数据
		String accept = request.getHeader("Accept");
		
		System.out.println("客户端说它想要：" + accept);
		
		if("image/gif".equals(accept)) {
			
			// 服务器告诉浏览器, 注意，以下代码，利用ajax请求，无法完成文件下载。 必须通过同步请求才能完成文件下载
			//  大家只要搞懂，并不是浏览器的accept要什么数据，服务器就一定会给什么数据，最终给什么数据还是由程序猿自己写代码决定！！
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
		user.setName("令狐翠花");
		user.setBirthday(new Date());
		return user;  
	}
	
}
