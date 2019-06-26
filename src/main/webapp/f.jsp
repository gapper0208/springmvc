<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>f.jsp</h3>
	
	
	<fieldset>
		<legend>8种基本类型</legend>
		<form action="user/f1.do">
			byte:<input name="b" /> <br />
			short:<input name="s" /> <br />
			int:<input name="i" /> <br />
			long:<input name="l" /> <br />
			float:<input name="f" /> <br />
			double:<input name="d" /> <br />
			boolean:<input name="bb" /> <br /> 
			char:<input name="c" /> <br />
			<button type="submit">GO</button>
		</form>
	</fieldset>
	
	
	<fieldset>
		<legend>String类型</legend>
		<form action="user/f2.do">
			username:<input name="username" /> <br />
			password:<input name="password" /> <br />
			<button type="submit">GO</button>
		</form>
	</fieldset>
	
	<fieldset>
		<legend>User类型</legend>
		<form action="user/f3.do">
			uid:<input name="uid" /> <br />
			name:<input name="name" /> <br />
			birthday:<input name="birthday" /> <br />
			money:<input name="money" /> <br />
			<button type="submit">GO</button>
		</form>
	</fieldset>
	
	<fieldset>
		<legend>List类型</legend>
		<form action="user/f4.do">
			hobby:
				<label>
					<input type="checkbox" name="hobby" value="足球" />足球
				</label> 
				<label>
					<input type="checkbox" name="hobby" value="篮球" />篮球
				</label>
				<label>
					<input type="checkbox" name="hobby" value="排球" />排球
				</label>
			<button type="submit">GO</button>
		</form>
	</fieldset>
	
	
	<fieldset>
		<legend>Set类型</legend>
		<form action="user/f5.do">
			food:
				<label>
					<input type="checkbox" name="food" value="冒菜" />冒菜
				</label> 
				<label>
					<input type="checkbox" name="food" value="火锅" />火锅
				</label>
				<label>
					<input type="checkbox" name="food" value="串串" />串串
				</label>
			<button type="submit">GO</button>
		</form>
	</fieldset>
	
	<fieldset>
		<legend>Map类型</legend>
		<form action="user/f6.do">
			foo:
				<label>
					<input type="checkbox" name="a" value="老滚5" />老滚5
				</label> 
				<label>
					<input type="checkbox" name="b" value="辐射4" />辐射4
				</label>
				<label>
					<input type="checkbox" name="c" value="巫师3" />巫师3
				</label>
			<button type="submit">GO</button>
		</form>
	</fieldset>
	
	
	<fieldset>
		<legend>String数组类型</legend>
		<form action="user/f7.do">
			strs:
				<label>
					<input type="checkbox" name="strs" value="全战：三国" />全战：三国
				</label> 
				<label>
					<input type="checkbox" name="strs" value="刺激战场" />刺激战场
				</label>
				<label>
					<input type="checkbox" name="strs" value="王者荣耀" />王者荣耀
				</label>
			<button type="submit">GO</button>
		</form>
	</fieldset>
	
</body>
</html>