1类型转换器
	a. 创建一个类，该类必须实现org.springframework.core.convert.converter.Converter接口
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
	b. 还需要在spring的配置文件中，进行配置：
	<mvc:annotation-driven conversion-service="conversionService" />
	
	<!-- 配置类型转换器 -->
	<bean id="conversionService" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
		<property name="converters">
			<list>
				<bean class="com.woniuxy.f_parameter.MyDateConverter"></bean>
			</list>
		</property>
	</bean>