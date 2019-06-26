package com.woniuxy.h_controller;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private Integer uid;
	private String name;
	private Date birthday;
	public User() {
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", name=" + name + ", birthday=" + birthday + "]";
	}
}
