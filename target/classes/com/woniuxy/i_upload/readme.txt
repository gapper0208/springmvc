文件上传：

1. 导入依赖：fileupload

2. 制作表单
	<form action="upload.do" enctype="multipart/form-data" method="post">
		photo:<input type="file" name="photo" /><br />
		<button type="submit">提交</button>
	</form>
	
3. 在spring-servlet.xml配置文件中，添加一个文件上传解析器
	<!-- 
		文件上传解析器 
		id必须为multipartResolver!!!
		id必须为multipartResolver!!!
		id必须为multipartResolver!!!
	-->
	<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
	</bean>

4. 编写Controller方法