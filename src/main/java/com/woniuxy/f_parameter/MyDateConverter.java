package com.woniuxy.f_parameter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
// Converter<String, Date> 
// String表示源类型，Date表示目表类型
public class MyDateConverter implements Converter<String, Date> {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public Date convert(String source) { 
		try {
			Date date = sdf.parse(source);
			return date;
		} catch (ParseException e) {
			try {
				Date date = sdf2.parse(source);
				return date;
			} catch (ParseException e1) {
				throw new RuntimeException(e1);
			}
		}
		
	}

}
