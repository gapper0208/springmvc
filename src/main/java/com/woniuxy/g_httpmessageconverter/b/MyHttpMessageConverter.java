package com.woniuxy.g_httpmessageconverter.b;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MyHttpMessageConverter extends AbstractHttpMessageConverter{

	@Override
	protected boolean supports(Class clazz) {
		return User.class == clazz;
	}

	@Override
	protected Object readInternal(Class clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		
		
		// 请求参数，已经被封装在inputMessage对象中了。
		InputStream in = inputMessage.getBody();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while(in.available() > 0) {
			int b = in.read();
			bos.write(b);
		}
		byte[] bb = bos.toByteArray();
		String str = new String(bb);


		String[] ss = str.split("---"); // id::1 name::haha  
		
		User user = new User();
		user.setUid(Integer.parseInt(ss[0].split("::")[1]));
		user.setName(ss[1].split("::")[1]);
		return user;
	}

	@Override
	protected void writeInternal(Object t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		
		User u = (User) t;
		OutputStream out = outputMessage.getBody();
		ObjectMapper om = new ObjectMapper();
		String str =  "uid::" + u.getUid() + "---"+"name::"+u.getName() + "---" + "birthday::"+u.getBirthday();
		out.write(str.getBytes("utf-8"));
		out.close();
	}

}
