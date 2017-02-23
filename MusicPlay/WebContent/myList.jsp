<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>음악목록</title>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/style.css" rel="stylesheet">
<style type="text/css">
body {
	padding: 50px;
}
h1 {
	font-weight:bold;
	color : #A0B0DB;
}
.ranking {
	text-align: center;
	font-size: 28pt;
}

.spanTitle {
	font-size: 18pt;
	font-weight: bold;
	margin-right: 10px;
}

.pAlbum {
	color: gray;
	margin-left: 5px;
}

.imgAlbum {
	width: 80px;
	height: 80px;
	margin-right: 10px;
}

.btnPlay {
	margin-top:40%
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<h1>${userName } 님의 Music List</h1>
				<div style="text-align: right;">
					<a class="btn btn-xs btn-default" href="list.do">뮤직차트</a>
				</div><br>
				<table class="table table-hover table-condensed">
					<colgroup>
						<col width="80" align="center">
						<col width="*">
						<col width="70">
					</colgroup>
					<thead>
						<tr>
							<th>No</th>
							<th>곡정보/곡명</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
						 	<c:when test="${musics eq null || empty musics }">
						 		<tr>
									<td colspan="6" align="center">등록된 음악이 없습니다.</td>
								</tr>
						 	</c:when>
						 	<c:otherwise>
						 		<c:forEach items="${musics }" var="music" varStatus="status">
			                        <tr>
			                            <td class="ranking">${music.id }</td>
			                            <td>
			                            <table>
			                            <tr><td rowspan="2"><img class="imgAlbum" src="resources/img/${music.image }" ></td>
			                            <td><span class="spanTitle">${music.name }</span><a class="btn btn-xs btn-default" href="detail.do?musicId=${music.id }"><b>i</b></a></td>
			                            </tr>
			                            <tr><td><p class="pAlbum">${music.album }</p></td></tr>
			                            </table>
			                            <td><a class="btn btn-xs btn-danger btnPlay" href="deleteFavorite.do?musicId=${music.id }"><b> X </b></a></td>
			                        </tr>
		                        </c:forEach>
                        	</c:otherwise>
                        </c:choose>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>