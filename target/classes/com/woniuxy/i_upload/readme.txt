�ļ��ϴ���

1. ����������fileupload

2. ������
	<form action="upload.do" enctype="multipart/form-data" method="post">
		photo:<input type="file" name="photo" /><br />
		<button type="submit">�ύ</button>
	</form>
	
3. ��spring-servlet.xml�����ļ��У����һ���ļ��ϴ�������
	<!-- 
		�ļ��ϴ������� 
		id����ΪmultipartResolver!!!
		id����ΪmultipartResolver!!!
		id����ΪmultipartResolver!!!
	-->
	<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
	</bean>

4. ��дController����