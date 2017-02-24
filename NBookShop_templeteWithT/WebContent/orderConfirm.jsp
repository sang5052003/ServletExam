<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <title>상품목록</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link href="css/namoosori.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="desc">
        <p class="bg-info">[${order.customer.name } 님] 로그인 되었습니다.</p>
    </div>
    <form action="orderComplete.do" method="post">
    <article>
        <h2>주문 상품</h2>
        <!-- input tag사용 대신에 -->
        <!-- session에 order가 들어간다 -->
        <!-- session을 새로 만들기(invalidate로 지우고), 정보가 크니까 -->
        <c:set var="order" value="${order }" scope="session"/>
        <table class="table table-bordered">
            <colgroup>
                <col width="100"/>
                <col width="*"/>
                <col width="200"/>
            </colgroup>
            <thead>
                <tr>
                    <th class="text-center">상품번호</th>
                    <th class="text-center">상품명</th>
                    <th class="text-center">가격</th>
                </tr>
            </thead>
            <tbody>
            	<c:forEach items="${order.products }" var="product">
	                <tr>
	                    <td class="text-center">${product.serialNo }</td>
	                    <td>${product.name }</td>
	                    <td class="text-right">${product.priceStr }원</td>
	                </tr>
                </c:forEach>
                
            </tbody>
        </table>
        <div class="total-amount">주문금액 : ${order.totalPrice }원</div>
    </article>
    <article>
        <h2>주문 정보 확인</h2>
        <table class="table table-bordered">
            <colgroup>
                <col width="200"/>
                <col width="*"/>
            </colgroup>
            <tbody>
            <tr>
                <th class="text-center">결재방법</th>
                <td>${order.payment.name }</td>
            </tr>
            <tr>
                <th class="text-center">배송지 주소</th>
                <td>${order.shipAddress }</td>
            </tr>
            </tbody>
        </table>
        <div class="btn-wrap">
            <a class="btn btn-default" href="productList.do">수정</a>
            <button class="btn btn-primary" type="submit">신청완료</button>
        </div>
    </article>
    </form>
</div>
</body>
</html>