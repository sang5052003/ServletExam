<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
html, body {
	height: 100%;
	margin: 0;
	padding: 0;
}

#map {
	height: 100%;
}
</style>
</head>
<body>
	<div id="map"></div>
	<script type="text/javascript">
		var map;
		function initMap() {
			map = new google.maps.Map(document.getElementById('map'), {
				center : {
					lat : -34.397,
					lng : 150.644
				},
				zoom : 8
			});
		}
	</script>
	 <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDQkrXckIcXlsw9x8IomhXm0iMv2UWwLGQ&callback=initMap"
  type="text/javascript"></script>
</body>
</html>
<!-- AIzaSyDQkrXckIcXlsw9x8IomhXm0iMv2UWwLGQ -->