<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!-- 
<!DOCTYPE html>
<html>                
<head>               
<meta charset='UTF-8'>
<title>회원관리</title>  
</head>               
<body> -->

<%-- <jsp:include page="/WEB-INF/views/menu.jsp" />  --%>
<!-- <c:if test="${loginUser == null}">
	<jsp:forward page="/WEB-INF/views/member/login.jsp"></jsp:forward>
</c:if> -->

<h1>회원목록</h1>

<c:forEach var="vo" items="${memberList}"> 
	<p>
	<a href='${pageContext.request.contextPath}/member/edit.do?memId=<c:out value="${vo.memId}"/>'><c:out value="${vo.memId}" /></a> :
	<c:out value=" ${vo.memName}" /> :
	${vo.memPoint}
	<a href='${pageContext.request.contextPath}/member/del.do?memId=<c:out value="${vo.memId}" />' ><button type='button'>삭제</button></a>
<!-- 	JSTL 태그의 scope와 var 속성을 사용하면,
	JSTL 태그 실행 결과를 현재 위치에 출력하지 않고, 
	지정한 scope에 지정한 이름(var)의 속성을 저장한 후,
	EL에서 읽어서 사용가능  -->
	<c:url value="/member/del.do" var="delUrl" >
		<c:param name="memId">${vo.memId}</c:param>
	</c:url>
	<a href='${delUrl}' ><button type='button'>삭제</button></a>
	</p> 
</c:forEach>
<!-- </body>               
</html>               	
 -->