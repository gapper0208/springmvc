1. �ع�MVC�ܹ�

M mode 		  ģ��
V view 		  ��ͼ
C controller ������

2. MVC��ܣ�
	struts1  strut2(webwork) springmvc ...  
	  ���е�MVC��ܶ���һ��ǰ�ο�������ǰ�˿��������������������󣬲��ҷַ�����
	  ����struts2��ǰ�˿�������StrutsPrepareAndExecuteFilter
	  ����springmvc��ǰ�˿�������DispatcherServlet
	  
3. �Ȱ����г�����������
	a. ��������
		spring-webmvc
		servlet-api
		jstl
		junit
	b. ����springmvc��ǰ�˿�������DispatcherServlt
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
	c. ����һ������������springmvc�еĿ���������ʱ����ʵ��org.springframework.web.servlet.mvc.Controller�ӿڣ�
		public class UserController implements Controller {
			@Override
			public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
				System.out.println("hahahaha...");
				return null;
			}
		}
	d. ����spring�����ļ���spring-servlet.xml  (�Աȣ�֮ǰ��spring�����ļ�����applicationContext.xml������ר������service,dao)
	
	e. ��spring�������ļ���,д���������ã�
		<!-- ���ô�����ӳ���� -->
		<bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping" />
		
		<!-- ���ô����������� -->
		<bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter" />
		
		<!-- ��ͼ������ -->
		<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
			<property name="prefix" value="/"></property>
			<property name="suffix" value=".jsp"></property>
		</bean>
		
			
		<!-- ��spring ioc�����������������bean -->
		<bean id="/uc.do" class="com.woniuxy.a_hello.UserController"></bean>
		
	
		
	
	
	