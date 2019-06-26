package com.woniuxy.test;

import org.junit.Test;

public class AppTest {
	@Test
	public void test() throws Exception {
		int i = 2147483647;
		int i2 = 2147483632;
		
		System.out.println(i);
		System.out.println(i2);
		
		System.out.println(i == i2);
		
		float f = i;
		float f2 = i2;
		
		System.out.println(f);
		System.out.println(f2);
		
		System.out.println(f == f2);
		
	}
}
