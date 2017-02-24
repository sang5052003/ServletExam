<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품목록</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link href="css/namoosori.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="desc">
        <p class="bg-info">[${customer.name } 님] 로그인 되었습니다.</p>
    </div>
    <form action="orderConfirm.do" method="post">
    <article>
        <h2>주문 상품</h2>
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
            	<c:forEach items="${products }" var="product">
            	<input type="hidden" name="serials" value="${product.serialNo }"/>
                <tr>
                    <td class="text-center">${product.serialNo }</td>
                    <td>${product.name }</td>
                    <td class="text-right">${product.priceStr }원</td>
                </tr>
                <!-- 중간에 변수선언 -->
                <c:set var="total" value="${total + product.price }"/>
                <c:set var="totalStr" value="${totalStr = product.sumPriceStr(total) }"/>
                </c:forEach>
                
            </tbody>
        </table>
        <div class="total-amount">주문금액 : <c:out value="${totalStr }원"/></div>
    </article>
    <article>
        <h2>주문 정보 입력</h2>
        <table class="table table-bordered">
            <colgroup>
                <col width="200"/>
                <col width="*"/>
            </colgroup>
            <tbody>
            <tr>
                <th class="text-center">결재방법</th>
                <td>
                    <input type="radio" name="approval" id="CreditCard" value="C" />
                    <label for="CreditCard" style="margin-right:30px;">신용카드</label>
                    <input type="radio" name="approval" id="Transfer" value="R"/>
                    <label for="Transfer">실시간이체</label>
                </td>
            </tr>
            <tr>
                <th class="text-center">배송지 주소</th>
                <td><input type="text" size="80" /></td>
            </tr>
            </tbody>
        </table>
        <div class="btn-wrap">
            <button class="btn btn-primary" type="submit">주문신청</button>
        </div>
    </article>
    </form>
</div>
</body>
</html>