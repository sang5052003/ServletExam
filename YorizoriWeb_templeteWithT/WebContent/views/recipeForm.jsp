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
	<table id="recipeTable" border="1">
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
			<td>이미지 등록</td>
			<td>
				<input type="file" name="imageFile" accept="image/*" size="50">
			</td>
		</tr>
		
		
	</table>
	<table id="procedureTable" border="1">
		<%-- <c:set var="addTextNum" value="${addTextNum + 1 }"/>
		<c:if test="${addText ne null }">${addTextNum = addTextNum + 1 }</c:if> --%>
		<tr>
			<td>조리절차</td>
			<td>
				
				<%-- <c:set var="addText" value="addText"/>
				<c:set var="addTextName" value="javascript:getStr()"/> --%>
				<input type="button" name="addButton" style="cursor:hand" onclick="addTextBox()" value="추가">
			</td>
			<!-- <td>
				<textarea name="recipeProcedure" cols="30" rows="5"></textarea>
			</td> -->
			
		</tr>
		<tr>
			<td>
				<input type="text" name="addText[]" style="width:350px; height:20px;"> 
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
	
	var textNum = 0;
	var textBoxName;
	var targetTable;
	function addTextBox(){
		targetTable = document.getElementById("procedureTable");
		var textBox = targetTable.insertRow();
		textBox.onmouseover = function(){
			targetTable.clickedRowIndex = this.rowIndex;
		};
		var cell = textBox.insertCell();
		textBoxName = "addText" + textNum++;
		var frmTag = "<input type='text' name='" + textNum + "' style='width:350px; height:20px;'>";
		frmTag += "<input type='button' value='삭제' onclick='removeTextBox()' style='cursor:hand'>";
		cell.innerHTML = frmTag;
	}
	
	function getStr(){
		return textBoxName;
	}
	
	
	function removeTextBox(){
		targetTable.deleteRow(targetTable.clickedRowIndex);
	}
	

</script>


</body>
</html>