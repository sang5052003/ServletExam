<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		var sport = ${sport};//여기서는 $ -> el표현식
		var team = ${team};
		
		for(var i = 0; i < sport.length; i++){
			$("#sport").append("<option value='" + sport[i] + "'>" + sport[i] + "</option>");
		}
		
		for(var i = 0; i < team.length; i++){
			$("#team").append("<option value='" + team[i] + "'>" + team[i] + "</option>");
		}
	});
	
</script>
</head>
<body>

	<select id="sport">
		<option value="empty">선택없음</option>
	</select>
	
	<select id="team">
		<option value="empty">선택없음</option>
	</select>
	
</body>
</html>