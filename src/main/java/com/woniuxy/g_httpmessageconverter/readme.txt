0.http请求由几部分组成？
	a. 请求行
	b. 请求头
	c. 请求体

1. http协议中的请求头：
    content-type，客户端告诉服务器，我客户端给你发送的是什么格式的数据。
    accept，客户端告诉服务器，我客户端想要什么格式的数据。

2. 客户端要什么格式的数据，服务器未必就会给什么格式的数据，因为这只是一个http规范而已，
   服务器最终返回什么格式的数据，取决于你所编写的代码。
   
3. 我们都知道，springmvc框架是如何为控制器的参数注入值的：按照名称注入值（也就是拿着请求参数的名字，与目标方法的参数名匹配）。

4. 当我们在控制器的方法参数上添加了@RequestBody注解，springmvc框架就不再按照名称自动注入参数的值，
    而是使用HttpMessageConverter来为参数注入值。

5. SpringMVC中HttpMessageConverter的工作原理：
    a. 客户端发起请求，并携带请求参数
    b. SpringMVC根据映射器找到对应的handler，再把handler交给适配器
    c. 适配器检测方法的参数上是否有@RequestBody 
        如果没有，则按照名称注入参数的值，然后流程就直接结束了。
        如果有，则使用HttpMessageConverter注入参数的值，进入第4步
    d. SpringMVC框架读取出请求头中的content-type的值，比如为text/html（也就是客户端告诉服务器我给你发的是html格式的数据）
       SpringMVC根据获取到的content-type的值，去找出所有与此content-type的值匹配的HttpMessageConverter对象。
       
        如果一个都找不到，则抛出异常：HttpMediaTypeNotSupportedException
        如果找到一个或一个以上，则进入第5步 (多个HttpMessageConveter的顺序就是配置的顺序)
        
    e. SpringMVC拿着已经找到的一个或多个HttpMessageConverter对象，一一调用其canRead方法（canRead方法又回调了supports方法），
       canRead方法中判断目标参数的类型是否归当前HttpMessageConverter管理：
       
       
        如果不是，则继续判断下一个；
        如果是，则就确定了这唯一的一个HttpMessageConverter对象，然后进入第6步；（第一匹配者优先）
        如果最终一个都找不到，则抛出异常: HttpMediaTypeNotSupportedException
        
    f. SpringMVC调用HttpMessageConverter的read方法：   StringHttpMessageConverter
        // 参数clazz是要返回的对象的类型，也就是目标参数的类型
        // 参数inputMessage中封装了请求参数，可以通过该inputMessage获取到请求中的参数信息
        public final T read(Class<? extends T> clazz, HttpInputMessage inputMessage) throws IOException
        
    g. HttpMessageConverter的read方法执行完毕，把返回的对象注入给目标参数。
        完成！

    h. 自定义HttpMessageConverter， 需要继承AbstractHttpMessageConverter，并实现相关方法。
       然后需要在spring配置文件中，添加以下配置:
       <mvc:message-converters register-defaults="true">
           <bean class="com.gao.g_httpmessageconverter.MyHttpMessageConverter">
               <property name="supportedMediaTypes">
                   <list>
                       <value>text/user</value>
                   </list>
               </property>
           </bean>
       </mvc:message-converters>
        其中register-defaults用于告诉springmvc框架是否加载默认的HttpMessageConverter（包括StringHttpMessageConverter）

    i. 可以查看AnnotationDrivenBeanDefinitionParser源码来分析HttpMessageConveter的底层原理。

    j. 通过阅读AnnotationDrivenBeanDefinitionParser源码,我们得知,如果register-defaults的取值为ture，且当前项目的classpath中有
            com.fasterxml.jackson.databind.ObjectMapper
            com.fasterxml.jackson.core.JsonGenerator
       这两个类的话，springmvc就会自动加载用于处理json格式的HttpMessageConverter。 只需要导入jackson-databind jar包即可

     k. 参数配置代码如下:
        <mvc:annotation-driven>
            <mvc:message-converters register-defaults="true">
                <bean class="com.gao.g_httpmessageconverter.MyHttpMessageConverter">
                    <property name="supportedMediaTypes">
                        <list>
                            <value>text/user</value>
                        </list>
                    </property>
                </bean>
                <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
                    <property name="objectMapper">
                        <bean class="com.fasterxml.jackson.databind.ObjectMapper">
                            <property name="dateFormat">
                                <bean class="java.text.SimpleDateFormat">
                                    <constructor-arg type="java.lang.String" value="yyyy/MM/dd" />
                                </bean>
                            </property>
                            <!-- 指定时区 -->
                            <property name="timeZone" value="GMT+8" />
                        </bean>
                    </property>
                </bean>
            </mvc:message-converters>
        </mvc:annotation-driven>

============================================================================================================================

1. 我们已经知道，控制器的返回值是String的时候，视图解析器会把这个返回值当做逻辑视图，然后再把逻辑视图解析为物理视图。

2. 当我们在控制器的方法上添加@ResponseBody的时候，视图解析器就不会出来工作了。也就是说此时就没有视图解析器的什么事情了。

3. 当我们在控制器的方法上添加@ResponseBody的时候，SpringMVC框架会先根据客户端请求头中的 "返回值类型"，再根据 "Accept的值" 来寻找一个最合适的HttpMessageConverter对象!

	accept: "a/b": StringHttpMessageConverter
	
	supports: String.class = c  
	
	write, 最终write方法中的输入流写了什么，就会给客户端响应什么
	

4. SpringMVC会调用最合适的HttpMessageConverter对象的write方法:
    public final void write(final T t, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException

    完成。

============================================================================================================================

注意
    1.并不是使用了某一个HttpMessageConverter对象的read方法后，就一定会使用同一个HttpMessageConverter对象的write方法。
    2.注意本例的Jackson-databind的使用，以及其中的ObjectMapper的使用（在Test类中有演示）