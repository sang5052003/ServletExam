<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 가입</title>
<!-- Google CDN -->
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#id").keyup(function(){
				
				if($("#id").val().length > 5){
					//서버에 ajax로 쿼리 날려줌
					var id = $(this).val();
					$.ajax({
						type: 'POST',
						url: 'checkId.do',
						data:
							{
								id : id //id라는 이름으로 id(var id)를 넣는다
							},
						success: function(result){ //result를 문자열로 받았다(원래는 서버에서 객체로 보내주는게 기본 json)
							if($.trim(result) == 'ok'){
								$("#idCheckResult").html("사용 가능한 ID입니다.");
							}else{
								$("#idCheckResult").html("사용 중인 ID입니다.");
							}
						}
					});
					
				}else{
					$("#idCheckResult").html("ID는 5자 이상입니다");
				}
			});
		});
	</script>
</head>
<body>
	<input type="text" id="id" name="idCheck" placeholder="ID 입력">
	<span id="idCheckResult"></span>
</body>
</html>