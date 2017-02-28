<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!-- 서버에서 우클릭에서 edit으로 변경 해줘야 된다 -->
<!-- pageContext.request.contextPath context path는 request에서 받아올 수 있다 -->
<c:set var="ctx">${pageContext.request.contextPath }</c:set>
<html>
<head>
    <meta charset="utf-8">
    <title>로그인</title>
    <!-- ../ -> 상위폴더 경로  -->
    <!-- 위에 거는 잘안쓰고 자신(app)의 context-path를 사용 -->
    <!-- hr -> ${ctx } -->
    <link rel="stylesheet" href="${ctx }/style/css/reset.css">
    <link rel="stylesheet" href="${ctx }/style/css/style.css">
    <script type="text/javascript">
    //로그인 시 아이디, 비번을 입력했는지 확인
    //입력하지 않은 항목에 대한 사용자 알림
    //전역함수(window..)로 되기 때문에 이런 식으로 사용 x
    	function loginValidate(){
    		var loginId = document.getElementById("iptLoginId").value;
    		if(loginId === ""){
    			alert("아이디를 입력하세요");
    			document.getElementById("iptLoginId").focus();
    			return false;
    		}
    		
    		//패스워드 검사
    		
    		return true;
    	}
    </script>
</head>
<body>
<div class="login-wrap">
    <h3>로그인</h3>
    <form action="${ctx }/login.do" method="post" onsubmit="return loginValidate();">
        <label>아이디</label>
        <input type="text" id="iptLoginId" name="loginId" placeholder="아이디를 입력하세요.">
        <label>비밀번호</label>
        <input type="password" name="passwd" placeholder="패스워드를 입력하세요.">
        <div class="login-btn">
            <input type="reset" value="취소">
            <input type="submit" value="로그인">
        </div>
    </form>
</div>
</body>
</html>



