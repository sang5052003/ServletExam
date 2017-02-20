<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- taglib를 붙인다 prefix="c" 이름 = core-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 도메인명:포트/웹app이름/이곳에 url(list.do)추가 없이  -->
<!-- 최초페이지에 들어오면 list.do servlet으로 redirect.. -->
<!-- 최초페이지(web.xml -> welcome-file-list -> index.jsp)-->
<c:redirect url="list.do"/>

</body>
</html>