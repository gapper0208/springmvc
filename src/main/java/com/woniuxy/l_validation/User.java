package com.woniuxy.l_validation;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User implements Serializable {
	
	@Prime(min = 100, max = 200, message = "uid必须为{min}到{max}之间的一个质数",groups = {A.class})
	private Integer uid;
	@Size(min = 2, max = 4, message = "{errors.name.size}", groups = {A.class})
	@Size(min = 3, max = 6, message = "{errors.name.size}", groups = {B.class})
	private String name;
	private Date birthday;
	private Double money;
	@Pattern(regexp = "1[358]\\d{9}" , message = "{errors.cellphone.pattern}", groups = {A.class})
	private String cellphone;
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
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", name=" + name + ", birthday=" + birthday + ", money=" + money + ", cellphone="
				+ cellphone + "]";
	}
}
