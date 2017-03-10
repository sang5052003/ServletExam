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
		$("#btn").click(function(){
			$.ajax({
				url: "customer.do",
				type: "get",
				dataType: "xml",
				success:function(xml){
					var id = $(xml).find("id").text();
					var name = $(xml).find("name").text();
					
					var div = $("<div>");
					var p1 = $("<p>").html(id);
					var p2 = $("<p>").html(name);
					
					div.append(p1).append(p2);
					
					$("#result").append(div);
				}
			});
		});
	});
</script>

</head>
<body>
	<input type="button" id="btn" value="read xml">
	<hr>
	<div id="result"></div>
</body>
</html>