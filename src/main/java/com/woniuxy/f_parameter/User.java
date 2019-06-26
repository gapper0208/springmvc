package com.woniuxy.f_parameter;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private Integer uid;
	private String name;
	private Date birthday;
	private Double money;
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
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", name=" + name + ", birthday=" + birthday + ", money=" + money + "]";
	}
}
