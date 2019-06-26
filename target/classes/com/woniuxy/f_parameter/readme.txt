1����ת����
	a. ����һ���࣬�������ʵ��org.springframework.core.convert.converter.Converter�ӿ�
	// Converter<String, Date> 
	// String��ʾԴ���ͣ�Date��ʾĿ������
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
	b. ����Ҫ��spring�������ļ��У��������ã�
	<mvc:annotation-driven conversion-service="conversionService" />
	
	<!-- ��������ת���� -->
	<bean id="conversionService" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
		<property name="converters">
			<list>
				<bean class="com.woniuxy.f_parameter.MyDateConverter"></bean>
			</list>
		</property>
	</bean>