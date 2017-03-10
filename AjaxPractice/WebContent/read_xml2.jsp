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
					
					var xmlData = $(xml).find("customer");
					var listLength = xml.length;
					if(listLength){
						$("#result").empty();
						var contentStr = "";
						
						$(xmlData).each(function(){
							contentStr += $(this).find("id").text()
										+ $(this).find("name").text() + "<br>"
						});
						
						$("#result").append(contentStr);
					}
				}
			});
		});
	});

</script>

</head>
<body>
<input type="button" id="btn" value="readXml">
<hr>
<div id="result"></div>
</body>
</html>