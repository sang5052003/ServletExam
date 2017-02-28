<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- pageContext 객체는 현재 JSP 페이지의 컨텍스트(Context)를 나타내며
, 주로 다른 내장 객체를 구하거나 페이지의 흐름 제어 그리고 에러 데이터를 얻어낼 때 사용 -->
<!-- pageContext객체 -> getRequest() -> getContextPath() -->
<c:set var="ctx">${pageContext.request.contextPath }</c:set>
<html>
<head>
    <meta charset="UTF-8">
    <title>인사관리 시스템</title>
    <link rel="stylesheet" href="${ctx }/style/css/reset.css">
    <link rel="stylesheet" href="${ctx }/style/css/style.css">
</head>
<body>
<header>
    <%@ include file="header.jspf" %>
</header>
<nav>
    <%@ include file="sideMenu.jspf" %>
</nav>
<div class="contents-wrap">
    <h2 class="page-title"> 인사관리 시스템 MENU </h2>

</div>
<footer>
    <%@ include file="footer.jspf" %>
</footer>
</body>
</html>














