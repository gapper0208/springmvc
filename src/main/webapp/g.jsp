<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	<h3>g.jsp</h3>
	
	<script>
		$(function() {
			$("#btn").click(function() {
				$.ajax({
					type:"post",
					url:"user/f3.do",
					data:"{\"uid\":12, \"name\":\"kkkk\",\"birthday\":\"2012/12/21\"}",
					contentType:"application/json"
				});
			});
		});
	</script>
	<button id="btn">ajax go</button>
	
	<hr />
	<script>
		$(function() {
			$("#btn2").click(function() {
				$.ajax({
					type:"post",
					url:"user/f4.do",
					headers: {
						"Accept":"a/b"
					}
				});
			});
		});
	</script>
	<button id="btn2">ajax go2</button>
	
	
	<hr />
	<script>
		$(function() {
			$("#btn3").click(function() {
				$.ajax({
					type:"post",
					url:"user/f5.do",
					headers: {
						Accept:"woniu/gao"
					},
					success:function(data) {
						alert(data + "~~");
					}
				});
			});
		});
	</script>
	<button id="btn3">ajax go3</button>
</body>
</html>