0. 注意，不要使用tomcat7来运行该校验框架示例，会有jar包冲突问题:会说找不到ELManager类！
   推荐使用tomcat9

1. 导入依赖，注意只需要导入下面的第一个依赖即可把后面两个依赖也一起导入到项目中
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
	
2. 配置Hibernate校验器
	<mvc:annotation-driven validator="validator" />

	<bean name="validator" class="org.springframework.validation.beanvalidation.LocalValidatorFactoryBean">
		<property name="providerClass" value="org.hibernate.validator.HibernateValidator"></property>
	</bean>	
	
3. 在POJO中添加校验规则
	public class User implements Serializable {
		private Integer id;
		@Size(min = 2, max = 4, message = "名字必须在2到4列之间")
		private String name;
		private Date birthday;
		private Double money;
		private String cellphone;
		
		/* getter and setter */
		...
	}
	
4. 在controller中，在需要校验的POJO前加上@Validated注解，同时在需要校验的POJO后紧跟Errors
   注意，测试时，要把日期也填上，否则birthday字段会报错！
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
	
5. 在jsp页面上显示错误信息
	<form action="${pageContext.request.contextPath }/user/save.do" method="post">
		name:<input type="text" name="name" />${name } <br />
		birthday:<input type="text" name="birthday" />${birthday } <br />
		money: <input type="text" name="money" />${money } <br />
		<button type="submit">GO</button>
	</form>
	
6. 数据回显
		a. 被添加上@Validated注解的pojo参数，会被springmvc框架自动加入request范围中，key就是首字母小写的类名
		可以通过@ModelAttribute注解手动指定加入request范围的key
		
		有了数据回显的form表单看起来是这个样子
		<form action="${pageContext.request.contextPath }/user/save.do" method="post">
			name:<input type="text" name="name" value="${user.name }" />${name } <br />
			birthday:<input type="text" name="birthday" value="<fmt:formatDate value="${user.birthday }" pattern="yyyy/MM/dd" />" />${birthday } <br />
			money: <input type="text" name="money" value="${user.money }" />${money } <br />
			<button type="submit">GO</button>
		</form>
		
		b.@ModelAttribute注解还可以添加到方法上，此时表示将该方法的返回值添加到request范围中，注解的value属性用于指明key
		c.@ModelAttribute不能用于简单类型， 所以简单类型的数据回显，必须使用Model。

7. 以上的数据回显方式是“硬编码”实现的，下面讲解如何“软编码”
	a. applicationContext.xml看起来是这个样子:
		<context:component-scan base-package="com.feicui.n_validation" />
		<mvc:annotation-driven validator="validator" />
		<!-- 配置校验框架 -->
		<bean name="validator" class="org.springframework.validation.beanvalidation.LocalValidatorFactoryBean">
			<property name="providerClass" value="org.hibernate.validator.HibernateValidator"></property>
			<!-- 注入资源文件 -->
			<property name="validationMessageSource" ref="messageSource"></property>
		</bean>
		<!-- 配置国际化 -->
		<bean name="messageSource" class="org.springframework.context.support.ReloadableResourceBundleMessageSource">
			<!-- 注意，这里只配置资源文件的基本名称，所以在基本名称之后万万不可加properties这个后缀 -->
			<property name="basename" value="classpath:com/feicui/n_validation/ValidationMessages"></property>
		</bean>
		<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
			<property name="prefix" value="/"></property>
			<property name="suffix" value=".jsp"></property>
		</bean>
	b. 在n_validation包下创建ValidationMessages.properties文件,编写键值对：validation.name.size = 姓名必须在{min}和{max}列之间
	c. 在需要验证的POJO的属性上，添加的验证错误提示信息必须用{}括起来才可以使用资源文件中的信息。
		@Size(min = 2, max = 4, message = "{validation.name.size}")
		private String name;

8. 实现国际化功能，继续创建以下资源文件，并分别写出对应的错误提示信息
	ValidationMessages_en_US.properties
	ValidationMessages_zh_CN.properties
    修改浏览器的语言，这样浏览器在发起http请求的时候，就会将语言信息通过请求头告知服务器。
	
9. JSR-303校验规则 (jsr是Java Specification Requests的缩写，意思是Java 规范提案)  jsr300
	@NotNull					被注解的属性不能为Null(但可以是空串或纯空格)
	@NotBlank					被注解的属性不能为空串，也不能为纯空格
	@Min(value)					被注解的属性必须是一个数字，其值必须大于等于指定的最小值
	@Max(value)					被注解的属性必须是一个数字，其值必须大于等于指定的最小值
	@Digits(integer,fraction)	被注解的属性必须是一个数字，其值整数和小数部分必须是指定的精度(整数就占integer列， 小数就占frcation列)
	@Past						被注解的日期必须是一个过去的日期
	@Future						被注解的日期必须是一个未来的日期
	@Pattern(regexp)			被注解的属性必须符合指定的正则表达式

10. hibernate validator附加的constraint
	@Email						被注解的属性必须是电子邮箱格式
	@Legnth						被注解的属性的长度必须在指定范围内
	@NotEmpty					被注解的字符串不能为空串，但可以是纯空格
	@Range						被注解的数字必须在指定的范围内
	
11. 校验分组
	a. 定义多个接口，一个接口就代表一个校验分组
	b. 这些接口中不需要定义任何方法，这些接口仅仅是用来对校验规则进行分组的
		interface A {}
		interface B {}
	c. 为验证添加了分组后，就必须使用，否则默认情况下不会直接使用分组中的校验规则 
	   POJO中: 
	   	@Size(min = 2, max = 4, message = "{validation.size}", groups = {A.class})
		@Size(min = 3, max = 6, message = "{validation.size}", groups = {B.class})
		private String name;
		
	   Controller中: 
	   public String save(Model model, @Validated(value = A.class) User user, BindingResult bindingResult) {
			// ...
	   }

12. 扩展验证规则
	a. 自定义一个注解类, 以下注解类要求被注解的数字必须是min和max之间的一个质数
	@Target(value = {ElementType.FIELD})
	@Retention(value = RetentionPolicy.RUNTIME)
	@Constraint(validatedBy = {PrimeValidator.class})
	public @interface Prime {
		
		// 验证失败的错误提示信息
		String message() default "需要一个质数";
		
		// 用于分组的属性
		Class<?>[] groups() default{};
		
		Class<? extends Payload>[] payload() default{};
		
		int min() default 0;
		int max() default 100;
	}
	
	b. 自定义一个校验类
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
	
	c. 使用自定义的验证规则
		POJO类中：(注意使用到了校验分组)
		@Prime(min = 10, max = 20, message = "{validation.prime}", groups = {A.class})
		private Integer id;
		Controller中：
		@RequestMapping("/save")
		public String save(Model model, @Validated(value = A.class) User user, BindingResult bindingResult) {
			// ...
		}