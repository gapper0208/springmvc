package com.woniuxy.l_validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

// @Prime注解，只能用在类上
@Target(ElementType.FIELD)
// @Prime注解，能保留到运行时，这样才能在运行时，通过反射机制来获取到该注解的信息
@Retention(RetentionPolicy.RUNTIME)
// 以下的注解，能把@Prime注解，融入到Springmvc的校验规则中。
@Constraint(validatedBy = {PrimeValidator.class})
public @interface Prime {
	
	// 验证失败的错误提示信息
	String message() default "需要一个质数";
	
	// 用于分组的属性
	Class<?>[] groups() default{};
	
	// 固定写法！！用不上的，但是还必须加！！
	Class<? extends Payload>[] payload() default{};

	public int min() default 10;
	public int max() default 100;
	
}
