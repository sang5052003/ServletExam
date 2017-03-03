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
	<div>

		<table border="1">
			<tr>
				<td>레시피 이름 : ${recipe.name }</td>
				<td>조리시간 : ${recipe.time }</td>
				<td>레벨 : ${recipe.level }</td>
				<td>재료 : ${recipe.ingredients }</td>
			</tr>

			<c:forEach items="${recipe.procedures }" var="procedure"
				varStatus="sts">
				<tr>
					<td>${sts.count }번${procedure.procedure }</td>
				</tr>
			</c:forEach>

		</table>

	</div>
</body>
</html>