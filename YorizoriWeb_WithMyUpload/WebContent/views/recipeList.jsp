<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<%@ include file="./header.jspf" %>

<h1>레시피 목록</h1>
<h2>${cookbook.name }</h2>
<span>${cookbook.description }</span>

<button type="button" onclick="javascript:location.href='${ctx }/recipe/register.do?cbId=${cookbook.id }';" class="btn">레시피 등록</button>
<br><br><br>
<c:forEach items="${cookbook.recipes }" var="recipe">
	<table class="section">
	<!-- 레시피 이미지들 -->
		<tr>
			<td>
			<!-- image.do -> 이미지를 불러오는 file io 컨트롤러 -->
				<img src="${ctx }/recipe/image.do?recipeId=${recipe.id}" width="128" height="128">
			</td>
		</tr>
		
	<!-- 레시피 정보들(디테일 버튼) -->
		<tr>
			<td>
				<a href="${ctx }/recipe/detail.do?recipeId=${recipe.id}">${recipe.name }</a>
				<br>
				(${recipe.time }분/난이도:${recipe.level })
			</td>
		</tr>
		
	</table>
</c:forEach>

<c:if test="${empty cookbook.recipes }">
	<h2>등록된 레시피가 없다</h2>
</c:if>

</body>
</html>