package com.woniuxy.m_restful;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("users")
public class UserController {

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public User save() {
		System.out.println("save...");
		return new User(1,"张无忌", new Date());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<User> findAll() {
		System.out.println("findAll...");
		List<User> list = new ArrayList<>();
		list.add(new User(1,"张无忌", new Date()));
		list.add(new User(2,"张学友", new Date()));
		list.add(new User(3,"张辽", new Date()));
		return list;  
	}
	
	@RequestMapping(value="{uid}", method = RequestMethod.GET)
	public String findOne(@PathVariable int uid) {
		System.out.println("findOne: " + uid);
		return "m";
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody 
	public void update(String abc) {
		System.out.println("update: " + abc);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	@ResponseBody 
	public void delete(Integer uid) {
		System.out.println("delete:" + uid);
	}
	
}
