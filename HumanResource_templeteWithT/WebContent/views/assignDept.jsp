<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
%>
<!DOCTYPE html>
<c:set var="ctx">${pageContext.request.contextPath }</c:set>
<html>
<head>
<meta charset="UTF-8">
<title>부서배정</title>
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
		<h2 class="page-title">부서배정</h2>
		<div class="contents">
			<form action="${ctx }/dept/assign.do" method="post">
			<input type="hidden" name="empNo" value="${employee.no }">
				<table>
						<tr>
							<td>사번 : ${employee.no }</td>
						</tr>
						<tr>	
							<td>이름 : ${employee.name }</td>
						</tr>
						<tr>
							<td><select name="deptNo">
									<option>== 부서선택 ==</option>
									<c:forEach items="${deptList }" var="dept">
										<option value="${dept.no }">${dept.name }</option>
									</c:forEach>
							</select>
							</td>
						</tr>
				</table>
				<br>
				<div class="alignRight">
					<input type="submit" value="배정">
				</div>
			</form>
		</div>
	</div>
	<footer>
		<%@ include file="footer.jspf"%>
	</footer>
</body>
</html>






