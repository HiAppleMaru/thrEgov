<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <a href='<%=request.getContextPath() %>/member/add.do' >회원추가</a> --%>

<!-- 로그인이 된 경우, 로그인한 사용자 이름과 로그아웃 링크를 출력  -->
<c:if test="${loginUser != null}">
	${loginUser.memName}
	<a href='${pageContext.request.contextPath}/member/list.do' >회원관리</a> |
	<a href='${pageContext.request.contextPath}/bbs/list.do' >게시판</a> |
	<a href='${pageContext.request.contextPath}/member/logout.do' >로그아웃</a>
</c:if>
<!-- 로그인이 되지 않은 경우, 로그인과 회원가입(추가) 링크를 출력  -->
<c:if test="${loginUser == null}">
	<a href='${pageContext.request.contextPath}/member/login.do' >로그인</a> |
	<a href='${pageContext.request.contextPath}/member/add.do' >회원추가</a> |
	<a href='<c:url value="/member/add.do" />' >회원추가</a>
</c:if>

<hr>
