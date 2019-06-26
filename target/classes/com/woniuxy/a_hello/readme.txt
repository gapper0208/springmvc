1. 回顾MVC架构

M mode 		  模型
V view 		  视图
C controller 控制器

2. MVC框架：
	struts1  strut2(webwork) springmvc ...  
	  所有的MVC框架都有一个前段控制器。前端控制器负责拦截所有请求，并且分发请求！
	  比如struts2的前端控制器是StrutsPrepareAndExecuteFilter
	  比如springmvc的前端控制器是DispatcherServlet
	  
3. 先把自行车骑起来！！
	a. 导入依赖
		spring-webmvc
		servlet-api
		jstl
		junit
	b. 配置springmvc的前端控制器：DispatcherServlt
			<servlet>
				<servlet-name>DispatcherServlet</servlet-name>
				<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
				<init-param>
					<param-name>contextConfigLocation</param-name>
					<param-value>classpath:com/woniuxy/a_hello/spring-servlet.xml</param-value>
				</init-param>
			</servlet>
			<servlet-mapping>
				<servlet-name>DispatcherServlet</servlet-name>
				<url-pattern>*.do</url-pattern>  
			</servlet-mapping>
	c. 创建一个控制器，在springmvc中的控制器，暂时必须实现org.springframework.web.servlet.mvc.Controller接口！
		public class UserController implements Controller {
			@Override
			public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
				System.out.println("hahahaha...");
				return null;
			}
		}
	d. 创建spring配置文件，spring-servlet.xml  (对比，之前的spring配置文件叫做applicationContext.xml，其中专门配置service,dao)
	
	e. 在spring的配置文件中,写出如下配置：
		<!-- 配置处理器映射器 -->
		<bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping" />
		
		<!-- 配置处理器设配器 -->
		<bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter" />
		
		<!-- 视图解析器 -->
		<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
			<property name="prefix" value="/"></property>
			<property name="suffix" value=".jsp"></property>
		</bean>
		
			
		<!-- 让spring ioc容器来管理控制器的bean -->
		<bean id="/uc.do" class="com.woniuxy.a_hello.UserController"></bean>
		
	
		
	
	
	