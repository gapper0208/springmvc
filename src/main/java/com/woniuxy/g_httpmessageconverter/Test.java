package com.woniuxy.g_httpmessageconverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.
woniuxy.f_parameter.User;

public class Test {
	public static void main(String[] args) throws Exception {
		
		User user = new User();
		user.setUid(111);
		user.setName("abc");
		user.setBirthday(new Date());
		user.setMoney(666d);
		
		List<User> list = new ArrayList<>();
		list.add(user);
		list.add(user);
		list.add(user);
		
		ObjectMapper om = new ObjectMapper();
		
		String json = om.writeValueAsString(list);
		System.out.println(json);
		
		System.out.println("============================");
		
		String str = "{\"uid\":123,\"name\":\"уенч╪и\",\"birthday\":0}";
		User u = om.readValue(str, User.class);
		System.out.println(u);
	}
}
