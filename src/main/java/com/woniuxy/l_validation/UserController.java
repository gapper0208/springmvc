package com.woniuxy.l_validation;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {
	@RequestMapping("save")
	public String save(@Validated(A.class) User user, Errors errors, Model model) {
		if(errors.hasErrors()) {
			List<FieldError> list = errors.getFieldErrors();
			for (FieldError fe : list) {
				model.addAttribute(fe.getField()+"Error", fe.getDefaultMessage());
			}
			return "l";
		} else {
			return "l_success";
		}
	}
	
	
	@RequestMapping("update")
	public String update(@Validated(B.class) User user, Errors errors, Model model) {
		if(errors.hasErrors()) {
			List<FieldError> list = errors.getFieldErrors();
			for (FieldError fe : list) {
				model.addAttribute(fe.getField()+"Error", fe.getDefaultMessage());
			}
			return "l";
		} else {
			return "l_success";
		}
	}
}