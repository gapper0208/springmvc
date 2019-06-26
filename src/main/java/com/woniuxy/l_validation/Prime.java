package com.woniuxy.l_validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

// @Primeע�⣬ֻ����������
@Target(ElementType.FIELD)
// @Primeע�⣬�ܱ���������ʱ����������������ʱ��ͨ�������������ȡ����ע�����Ϣ
@Retention(RetentionPolicy.RUNTIME)
// ���µ�ע�⣬�ܰ�@Primeע�⣬���뵽Springmvc��У������С�
@Constraint(validatedBy = {PrimeValidator.class})
public @interface Prime {
	
	// ��֤ʧ�ܵĴ�����ʾ��Ϣ
	String message() default "��Ҫһ������";
	
	// ���ڷ��������
	Class<?>[] groups() default{};
	
	// �̶�д�������ò��ϵģ����ǻ�����ӣ���
	Class<? extends Payload>[] payload() default{};

	public int min() default 10;
	public int max() default 100;
	
}
