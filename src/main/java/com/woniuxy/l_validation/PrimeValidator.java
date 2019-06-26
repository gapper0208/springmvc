package com.woniuxy.l_validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PrimeValidator implements ConstraintValidator<Prime, Integer> {
	
	private int min;
	private int max;
	
	@Override
	public void initialize(Prime p) {
		this.min = p.min();
		this.max = p.max();
	}
	
	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		
		if(value == null) {
			return false;
		}
	
		if(value >= min && value <= max) {
			
			int i;
			for(i = 2; i <= value/2; i++) {
				if(value % i == 0) {
					break;
				}
			}
			return i >= value/2;
		}
		
		return false;
	}

}