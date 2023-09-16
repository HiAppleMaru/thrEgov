<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <title><tiles:getAsString name="title"/></title>  -->
<title><tiles:insertAttribute name="title"/></title>

<script src="${pageContext.request.contextPath}/resources/js/code.jquery.com_jquery-3.7.0.js"></script>
</head>
<body>
	<!-- tiles:insertAttribute 태그를 사용해서 -->
	<!-- name 속성에 지정한 이름으로 타일즈를 통해 채워넣을 공간을 배치 -->
	<!-- attribute value가 타일즈 화면이름과 일치하는 경우 해당 화면을 주입 -->
	<!-- attribute value가 /로 시작하는 경우 해당 경로의 템플릿(JSP파일)을 주입 -->
	<!-- 그 밖의 경우에는, attribute value 내용(문자열)을 그대로 주입 -->
  <tiles:insertAttribute name="menu" />	
  
  <tiles:insertAttribute name="body" />
    
  <tiles:insertAttribute name="footer" />

</body>
</html>