<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.oreilly.servlet.MultipartRequest,
                      com.oreilly.servlet.multipart.DefaultFileRenamePolicy,
                      java.util.*,
                      java.io.*"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="ctx" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${ctx }/css/yorizori.css">
<title>요리조리 쿡북</title>
</head>
<body>
<h1>레시피 등록</h1>
<form action="${ctx }/recipe/register.do" method="post" enctype="multipart/form-data">
	<table border="1">
		<tr>
			<td>레시피명</td>
			<td>
				<input type="text" name="recipeName" value="">
			</td>
		</tr>
		<tr>
			<td>등록자</td>
			<td>
				<span>${loginUser.name }(${loginUser.userId })</span>
			</td>
		</tr>
		
		<tr>
			<td>조리시간</td>
			<td>
				<input type="text" name="recipeTime" value="">
			</td>
		</tr>
		
		<tr>
			<td>조리등급</td>
			<td>
				<input type="text" name="recipeLevel" value="">
			</td>
		</tr>
		
		<tr>
			<td>재료</td>
			<td>
				<input type="text" name="recipeIngredients" value="">
			</td>
		</tr>
		
		<tr>
			<td>이미지 확장자</td>
			<td>
				<input type="text" name="imageCont" value="">
			</td>
		</tr>
		
		<tr>
			<td>이미지 이름</td>
			<td>
				<input type="file" name="imageFile" accept="image/*" size="50">
			</td>
		</tr>
		
		<tr>
			<td>조리절차</td>
			<td>
				<textarea name="recipeProcedure" cols="30" rows="5"></textarea>
			</td>
		</tr>
	</table>
	
	<!-- button태그는 default타입이 submit  -->
	<!-- type="button" 을 추가하는 이유 -->
	<button type="button" onclick="javascript:gotoMain();" class="btn">목록</button>
	<input type="hidden" name="cbId" value="${cbId }"/>
	<button type="submit" class="btn">등록</button>
	
</form>
<script type="text/javascript">
	function gotoMain(){
		window.location.href="${ctx}/"; //el태그는 스크립트에서도 쓸 수 있다
		return false;
	}

</script>


</body>
</html>