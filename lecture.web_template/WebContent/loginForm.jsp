<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/style.css" rel="stylesheet">
</head>
<body>
	<h3>로그인</h3>
	<br>
	<form action="login.do" method="post">
		<table class="table">
			<tr>
				<td>
					<input id="loginId" name="loginId" class="form-control"
					 type="text" value="" placeholder="ID를 입력해 주세요">
				</td>
			</tr>
			
			<tr>
				<td>
					<input id="password" name="password" class="form-control"
					 type="password" value="" placeholder="password를 입력해 주세요">
				</td>
			</tr>
		
		</table>
		<br>
		<div align="center">
			<input class="btn" type="reset" value="취소">
			<input class="btn btn-success" type="submit" value="로그인">
		</div>
	
	</form>
</body>
</html>