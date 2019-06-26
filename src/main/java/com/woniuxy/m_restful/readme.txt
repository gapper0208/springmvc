springmvc对restful风格的支持

0. 预备知识：
    a. 默认Servlet

    b. 资源 什么是资源？
        1). 图片
        2). 文本
        3). 歌曲
        4). 视频
        所谓“资源”，就是网络上的一个实体，或者说是网络上的一个具体信息。
   “但凡有利用价值的，都可以称之为资源”
        
    c. http协议复习  
    	略

1. 什么是rest
	a. representational state transfer 表现性状态转换
	b. rest是一种设计风格，是一种思想，而不是一种标准。
	c. rest的核心是面向资源
	d. rest架构的主要原则
		1) 网络上的所有事物都被抽象为资源
		2) 每一个资源都有唯一的资源标识，对资源的操作不会改变这些标识。
           	比如，你根据资源标识删除了某个资源以后，这个资源标识还是有效的，只不过该资源标识代表的资源不存在而已。
		3) 对资源做不同的操作时，总是通过相同的资源标识符来访问资源的
		4) 所有操作都是无状态的
			因为rest是基于http协议的一种设计风格，http就是无状态的连接协议
		5) 同一个资源具有多种表现形式(xml, json)
		6) 符合rest原则的架构方式即可称为RESTFUL

2. 为什么使用restful
	不符合RESTFUL的URL
	增	http://127.0.0.1:8080/user/save.do		  	请求方式:post
	删	http://127.0.0.1:8080/user/delete.do?id=1	请求方式:post
	改	http://127.0.0.1:8080/user/update.do		请求方式:post
	查	http://127.0.0.1:8080/user/findOne.do?id=1	请求方式:post
	查	http://127.0.0.1:8080/user/findAll.do		请求方式:post
	
	符合RESTFUL的URL（资源+谓词）
	增	http://127.0.0.1:8080/users			请求方式:post
	删	http://127.0.0.1:8080/users			请求方式:delete
	改	http://127.0.0.1:8080/users			请求方式:put
	查	http://127.0.0.1:8080/users/1		请求方式:get
	查	http://127.0.0.1:8080/users			请求方式:get

	其实，不符合RESTFUL的URL也是没有什么问题的，但是大神认为是有问题的，有什么问题呢？
	非RESTFUL的URL，查询的时候用了findOne或findAll，新增的时候用了save，其实完全
	没有这个必要! 这样会导致api显得很杂乱。

	因为http协议中已经提供了post、delete、put、get请求方式，程序员使
	用不同请求方式的意图很明显，完全没有必要做额外的描述，这就是为什么有了RESTFUL

3. springmvc中如何使用restful
	a. 前端控制器DispatcherServlet的映射路径改为/
		<servlet>
			<servlet-name>springDispatcherServlet</servlet-name>
			<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
			<init-param>
				<param-name>contextConfigLocation</param-name>
				<param-value>classpath:com/feicui/m_restful/applicationContext.xml</param-value>
			</init-param>
			<load-on-startup>1</load-on-startup>
		</servlet>
		<servlet-mapping>
			<servlet-name>springDispatcherServlet</servlet-name>
			<url-pattern>/</url-pattern>
		</servlet-mapping>
		
		把前段控制器的映射路径该成“/”的目的是，保证请求地址中，没有后缀！ 这恰恰是restful最佳实践所要求。

		注意/*与/的
		1）区别：/ 不拦截对jsp资源发起的请求，而 /* 会拦截对jsp发起的请求
		2）相同：除了对jsp资源的访问外，/* 与 / 都会拦截所有请求，这样，当访问静态资源时，它们都会把静态资源当做映射来处理的。
		   比如在访问http://localhost:8080/images/2.jpg时，springmvc框架会直接把images/2.jpg交给映射器处理器，如果映射器处理器根据这个url找不到
		   对应的handler，则也不会把这个请求交给默认Serlvet，导致最终找不到这个资源。
		3）注意，因为/*也会拦截jsp资源，所以此时springmvc框架也会把jsp资源当做映射来处理的..

        在使用/*的大前提下：
        1）访问jsp：拦截，且当做映射，404
        2）访问jpg：拦截，且当做映射，404
        3）访问users（get方式），拦截，且当做映射，200

        在使用/的大前提下：
        1）访问jsp：不拦截，交给servlet容器处理，200
        2）访问jpg：拦截，且当做映射，404
        3）访问users（get方式），拦截，且当做映射，200

        在使用*.do的大前提下：
        1）访问jsp：不拦截，200
        2）访问jpg：不拦截，交给默认拦截器处理，200
        3）访问users（get方式），拦截，且当做映射，200

	b. 静态资源映射（经常搭配前端控制器DispatcherServlet的映射路径为/）
		<mvc:resources mapping="/js/**" location="/js/" ></mvc:resources>
	       或者直接配置默认servlet，该配置会自动找name为default的servlet作为默认servlet
	    <mvc:default-servlet-handler />

	c. 编写controller，注意：
		1) 在findOne方法中@PathVariable注解的使用，
		2) 且为了响应json格式的数据给客户端，需要完成以下两个步骤：
			a.导入jackjson-databind.jar
			b.在控制器方法加上@ResponseBody注解!

	d. 先完成一个简单的restful风格的crud（只要能利用“资源标识+http谓词”分发请求即可）
	   1) 控制器的方法上需要使用RequestMapping的method属性来指定处理的http请求

       2) 本来form只支持get和post请求方式，并不支持put和delete请求方式
          幸好springmvc框架提供了 "HiddenHttpMethodFilter过滤器" 来间接让表单支持put/delete请求
    	     <filter>
    			<filter-name>HiddenHttpMethodFilter</filter-name>
    			<filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
    		</filter>

    		<filter-mapping>
    			<filter-name>HiddenHttpMethodFilter</filter-name>
    			<url-pattern>/*</url-pattern>
    		</filter-mapping>
          此时，需要在表单中添加一个隐藏域，名字必须叫 "_method" ,其值可以是PUT/DELETE
          如此，form表单就可以发起get、post、put、delete请求了!

          表单代码，见m.jsp

    e. 利用"RESTful API设计6要素"改善api接口
        1). 资源路径（URI）
            在RESTful架构中，每个网址都代表一个资源，所以网址中不能有动词，只能有名词。
            API中的名词应该使用复数。

            eg. 有一个API提供动物园（zoo）的信息，还包括各种动物和雇员的信息，则它的路径应该设计成下面这样：
            动物园资源：http://www.gao.com/zoos
            动物资源：http://www.gao.com/animals
            雇员资源：http://www.gao.com/employees

        2). HTTP动词
            对于资源的操作（CRUD），是由HTTP动词（谓词）表示的。
            GET：获取资源（一项或多项）
            POST：新建一个资源
            PUT：更新资源（整体）
            PATCH：更新资源（部分）
            DELETE：删除资源

            eg.
            新建一个动物园：POST /zoos
            获取某个动物园：GET /zoos/ID
            更新某个动物园：PUT /zoos/ID
            删除某个动物园：DELETE /zoos/ID

        3). 过滤信息
            如果记录数量很多，服务器不能都将它们返回给用户。
            API应该提供参数，过滤返回结果。

            使用URL查询串来过滤数据是很好的方式，但不应该用于定位资源名称。如/posts/23，而不是/api?type=posts&id=23

            eg.
            ?offset=10：指定返回记录的开始位置。
            ?page=2&per_page=100：指定当前页，和每页显示的行数
            ?sort=-hiredate|sal：指定排序规则(有-前缀的表示降序)
            ?filter="name::gao|city::xian|gender::male"：指定筛选条件


        4). 错误处理
            如果状态码是4xx或者5xx，就应该向用户返回出错信息。
            一般来说，返回的信息中将error作为键名，错误信息作为键值即可。
            {
                "error": "错误信息"
            }

        5). 返回结果
            针对不同操作，服务器向用户返回的结果应该符合以下规范：
            GET /collections：返回资源对象的列表（数组）  code: 200
            GET /collections/identity：返回单个资源对象   code: 200
            POST /collections：返回新生成的资源对象       code: 201
            PUT /collections/identity：返回完整的资源对象 code: 201
            PATCH /collections/identity：返回被修改的属性 code: 201
            DELETE /collections/identity：返回一个空文档  code: 204

        6). 状态码
                                   服务器向用户返回的状态码和提示信息，使用标准HTTP状态码

            200 OK 服务器成功返回用户请求的数据，该操作是幂等的
                什么是幂等性
                HTTP/1.1中对幂等性的定义是：一次和多次请求某一个资源对于资源本身应该具有同样的结果（网络超时等问题除外）。
                也就是说，其任意多次执行对资源本身所产生的影响均与一次执行的影响相同。
            201 CREATED 新建或修改数据成功
            204 NO CONTENT 删除数据成功

            400 BAD REQUEST 用户发出的请求有错误，该操作是幂等的
            401 Unauthorized 表示用户没有认证，无法进行当前操作
            403 Forbidden 表示用户通过认证了，但是当前访问是被禁止的
            405 用来访问本页面的 HTTP 谓词不被允许（方法不被允许）
            415 不支持的媒体类型(Unsupported media type)
            422 Unprocesable Entity 当创建一个对象时，发生一个验证错误

            500 INTERNAL SERVER ERROR 服务器发生错误， 用户将无法判断发出的请求是否成功

4. RESTful架构与SOAP架构的区别
    a. WebService
        1). WebService是一种跨编程语言和跨操作系统平台的远程调用技术。
        2). WebService通过Http协议发送请求和接收结果时采用XML格式封装，并增加了一些特定的HTTP消息头，
            这些特定的HTTP消息头和XML内容格式就是soap协议。

    b. 难易度对比：
       SOAP由于各种需求不断扩充其本身协议的内容，导致在SOAP处理方面的性能有所下降。
       同时在易用性方面以及学习成本上也有所增加。（复杂）

       RESTful由于其面向资源来进行接口设计，从而简化了api设计的过程。
       同时也最大限度地利用Http最初的应用协议设计理念。（简单）

    c. 安全性对比：
       RESTful对于资源型服务接口来说很合适，特别适合于对效率要求很高，但是对于安全性要求不高的场景。
       SOAP的成熟性更适合于跨多语言的，对于安全性要求较高的接口设计。

    d. 综上：纯粹地说一个架构会占据主导地位是没有意义的，关键还是要看应用场景。

========================================================================================================================

练习：
    资源路径：  /users   /articles
    HTTP动词：  GET POST DELETE PUT
    过滤信息：  文章的分页筛选
    状态码：    200 404 422 403

    数据库设计：
    用户表：ID、用户名、密码、注册时间
    文章表：文章ID、标题、内容、发表时间、用户ID

    完成用户的CRUD
    完成文章的CRUD

参考文献：https://www.cnblogs.com/jaxu/p/7908111.html

==========================================================================================================
springmvc还有什么知识点：
springmvc标签库, spel 
