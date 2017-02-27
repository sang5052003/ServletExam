<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:set var="ctx">${pageContext.request.contextPath }</c:set>
<html>
<head>
<meta charset="UTF-8">
<title>직원목록</title>
<link rel="stylesheet" href="${ctx }/style/css/reset.css">
<link rel="stylesheet" href="${ctx }/style/css/style.css">
</head>
<body>
	<header>
		<%@ include file="header.jspf"%>
	</header>
	<nav>
		<%@ include file="sideMenu.jspf"%>
	</nav>
	<div class="contents-wrap">
		<h2 class="page-title">직원목록</h2>
		<div class="contents">
			<table border="1">
				<colgroup>
					<col width="90">
					<col width="*">
					<col width="*">
					<col width="*">
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>사번</th>
						<th>이름</th>
						<th>소속</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${empList }" var="emp" varStatus="sts">
						<tr>
							<td>${sts.count }</td>
							<td>${emp.no }</td>
							<td>${emp.name }</td>
							
							<c:set var="deptName">없음</c:set>
							<c:forEach items="${deptList }" var="dept">
							<c:choose>
								<c:when test="${dept.no eq emp.deptNo }">
									<c:set var="deptName">${dept.name }</c:set>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
								<%-- <td>${deptName }</td> --%>
							</c:forEach>
							<td>${deptName }</td>
							
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<footer>
		<%@ include file="footer.jspf"%>
	</footer>
</body>
</html>








