0. ע�⣬��Ҫʹ��tomcat7�����и�У����ʾ��������jar����ͻ����:��˵�Ҳ���ELManager�࣡
   �Ƽ�ʹ��tomcat9

1. ����������ע��ֻ��Ҫ��������ĵ�һ���������ɰѺ�����������Ҳһ���뵽��Ŀ��
	<dependency>
		<groupId>org.hibernate.validator</groupId>
		<artifactId>hibernate-validator</artifactId> 
		<version>6.0.7.Final</version>
	</dependency>

	<dependency>
		<groupId>org.jboss.logging</groupId>
		<artifactId>jboss-logging</artifactId>
		<version>3.3.1.Final</version>
	</dependency>

	<dependency>
		<groupId>javax.validation</groupId>
		<artifactId>validation-api</artifactId>
		<version>2.0.1.Final</version>
	</dependency>
	
2. ����HibernateУ����
	<mvc:annotation-driven validator="validator" />

	<bean name="validator" class="org.springframework.validation.beanvalidation.LocalValidatorFactoryBean">
		<property name="providerClass" value="org.hibernate.validator.HibernateValidator"></property>
	</bean>	
	
3. ��POJO�����У�����
	public class User implements Serializable {
		private Integer id;
		@Size(min = 2, max = 4, message = "���ֱ�����2��4��֮��")
		private String name;
		private Date birthday;
		private Double money;
		private String cellphone;
		
		/* getter and setter */
		...
	}
	
4. ��controller�У�����ҪУ���POJOǰ����@Validatedע�⣬ͬʱ����ҪУ���POJO�����Errors
   ע�⣬����ʱ��Ҫ������Ҳ���ϣ�����birthday�ֶλᱨ��
	@Controller
	@RequestMapping("/user")
	public class UserController  {
		@RequestMapping("/save")
		public String save(Model model, @Validated User user, Errors errors) {
			String path = "";
			if(bindingResult.hasErrors()) {
				List<FieldError> list = errors.getFieldErrors();
				for (FieldError fieldError : list) {
					model.addAttribute(fieldError.getField(), fieldError.getDefaultMessage());
				}
				path = "h";
				System.out.println("error: "+user);
			} else {
				path = "index";
				System.out.println("success: "+user);
			}
			return path;
		}
	}
	
5. ��jspҳ������ʾ������Ϣ
	<form action="${pageContext.request.contextPath }/user/save.do" method="post">
		name:<input type="text" name="name" />${name } <br />
		birthday:<input type="text" name="birthday" />${birthday } <br />
		money: <input type="text" name="money" />${money } <br />
		<button type="submit">GO</button>
	</form>
	
6. ���ݻ���
		a. �������@Validatedע���pojo�������ᱻspringmvc����Զ�����request��Χ�У�key��������ĸСд������
		����ͨ��@ModelAttributeע���ֶ�ָ������request��Χ��key
		
		�������ݻ��Ե�form�����������������
		<form action="${pageContext.request.contextPath }/user/save.do" method="post">
			name:<input type="text" name="name" value="${user.name }" />${name } <br />
			birthday:<input type="text" name="birthday" value="<fmt:formatDate value="${user.birthday }" pattern="yyyy/MM/dd" />" />${birthday } <br />
			money: <input type="text" name="money" value="${user.money }" />${money } <br />
			<button type="submit">GO</button>
		</form>
		
		b.@ModelAttributeע�⻹������ӵ������ϣ���ʱ��ʾ���÷����ķ���ֵ��ӵ�request��Χ�У�ע���value��������ָ��key
		c.@ModelAttribute�������ڼ����ͣ� ���Լ����͵����ݻ��ԣ�����ʹ��Model��

7. ���ϵ����ݻ��Է�ʽ�ǡ�Ӳ���롱ʵ�ֵģ����潲����Ρ�����롱
	a. applicationContext.xml���������������:
		<context:component-scan base-package="com.feicui.n_validation" />
		<mvc:annotation-driven validator="validator" />
		<!-- ����У���� -->
		<bean name="validator" class="org.springframework.validation.beanvalidation.LocalValidatorFactoryBean">
			<property name="providerClass" value="org.hibernate.validator.HibernateValidator"></property>
			<!-- ע����Դ�ļ� -->
			<property name="validationMessageSource" ref="messageSource"></property>
		</bean>
		<!-- ���ù��ʻ� -->
		<bean name="messageSource" class="org.springframework.context.support.ReloadableResourceBundleMessageSource">
			<!-- ע�⣬����ֻ������Դ�ļ��Ļ������ƣ������ڻ�������֮�����򲻿ɼ�properties�����׺ -->
			<property name="basename" value="classpath:com/feicui/n_validation/ValidationMessages"></property>
		</bean>
		<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
			<property name="prefix" value="/"></property>
			<property name="suffix" value=".jsp"></property>
		</bean>
	b. ��n_validation���´���ValidationMessages.properties�ļ�,��д��ֵ�ԣ�validation.name.size = ����������{min}��{max}��֮��
	c. ����Ҫ��֤��POJO�������ϣ���ӵ���֤������ʾ��Ϣ������{}�������ſ���ʹ����Դ�ļ��е���Ϣ��
		@Size(min = 2, max = 4, message = "{validation.name.size}")
		private String name;

8. ʵ�ֹ��ʻ����ܣ���������������Դ�ļ������ֱ�д����Ӧ�Ĵ�����ʾ��Ϣ
	ValidationMessages_en_US.properties
	ValidationMessages_zh_CN.properties
    �޸�����������ԣ�����������ڷ���http�����ʱ�򣬾ͻὫ������Ϣͨ������ͷ��֪��������
	
9. JSR-303У����� (jsr��Java Specification Requests����д����˼��Java �淶�᰸)  jsr300
	@NotNull					��ע������Բ���ΪNull(�������ǿմ��򴿿ո�)
	@NotBlank					��ע������Բ���Ϊ�մ���Ҳ����Ϊ���ո�
	@Min(value)					��ע������Ա�����һ�����֣���ֵ������ڵ���ָ������Сֵ
	@Max(value)					��ע������Ա�����һ�����֣���ֵ������ڵ���ָ������Сֵ
	@Digits(integer,fraction)	��ע������Ա�����һ�����֣���ֵ������С�����ֱ�����ָ���ľ���(������ռinteger�У� С����ռfrcation��)
	@Past						��ע������ڱ�����һ����ȥ������
	@Future						��ע������ڱ�����һ��δ��������
	@Pattern(regexp)			��ע������Ա������ָ����������ʽ

10. hibernate validator���ӵ�constraint
	@Email						��ע������Ա����ǵ��������ʽ
	@Legnth						��ע������Եĳ��ȱ�����ָ����Χ��
	@NotEmpty					��ע����ַ�������Ϊ�մ����������Ǵ��ո�
	@Range						��ע������ֱ�����ָ���ķ�Χ��
	
11. У�����
	a. �������ӿڣ�һ���ӿھʹ���һ��У�����
	b. ��Щ�ӿ��в���Ҫ�����κη�������Щ�ӿڽ�����������У�������з����
		interface A {}
		interface B {}
	c. Ϊ��֤����˷���󣬾ͱ���ʹ�ã�����Ĭ������²���ֱ��ʹ�÷����е�У����� 
	   POJO��: 
	   	@Size(min = 2, max = 4, message = "{validation.size}", groups = {A.class})
		@Size(min = 3, max = 6, message = "{validation.size}", groups = {B.class})
		private String name;
		
	   Controller��: 
	   public String save(Model model, @Validated(value = A.class) User user, BindingResult bindingResult) {
			// ...
	   }

12. ��չ��֤����
	a. �Զ���һ��ע����, ����ע����Ҫ��ע������ֱ�����min��max֮���һ������
	@Target(value = {ElementType.FIELD})
	@Retention(value = RetentionPolicy.RUNTIME)
	@Constraint(validatedBy = {PrimeValidator.class})
	public @interface Prime {
		
		// ��֤ʧ�ܵĴ�����ʾ��Ϣ
		String message() default "��Ҫһ������";
		
		// ���ڷ��������
		Class<?>[] groups() default{};
		
		Class<? extends Payload>[] payload() default{};
		
		int min() default 0;
		int max() default 100;
	}
	
	b. �Զ���һ��У����
	public class PrimeValidator implements ConstraintValidator<Prime, Integer> {
		
		private int min;
		private int max;
		
		@Override
		public void initialize(Prime constraintAnnotation) {
			this.min = constraintAnnotation.min();
			this.max = constraintAnnotation.max();
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
	
	c. ʹ���Զ������֤����
		POJO���У�(ע��ʹ�õ���У�����)
		@Prime(min = 10, max = 20, message = "{validation.prime}", groups = {A.class})
		private Integer id;
		Controller�У�
		@RequestMapping("/save")
		public String save(Model model, @Validated(value = A.class) User user, BindingResult bindingResult) {
			// ...
		}