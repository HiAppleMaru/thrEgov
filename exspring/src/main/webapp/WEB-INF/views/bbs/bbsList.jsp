<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>                
<head>               
<meta charset='UTF-8'>
<title>게시판</title>  
</head>               
<body>

<%-- <jsp:include page="/WEB-INF/views/menu.jsp" /> --%>
<!-- <c:if test="${loginUser == null}">
	<jsp:forward page="/WEB-INF/views/member/login.jsp"></jsp:forward>
</c:if> -->

<h1>게시글목록</h1>
<a href="${pageContext.request.contextPath}/bbs/add.do">글쓰기</a>

<table>
	<thead>
		<tr><th>번호</th><th>제목</th><th>작성자</th><th>등록일시</th></tr>
	</thead>
	<tbody>
		<c:forEach var="vo" items="${bbsList}"> 
			<tr>
				<td>${vo.bbsNo}</td>
				<!-- 문자열로 출력하는곳은 이상한 script가 들어있을 수 있으니 보안상 이유로 c:out으로 출력 -->
				<!-- 제목을 클릭했을때 이동하게 하기 위해서 링크 추가 -->
				<td>
				<a href="${pageContext.request.contextPath}/bbs/edit.do?bbsNo=${vo.bbsNo}">
				<c:out value="${vo.bbsTitle}" /></td>
				</a>				
				<td><c:out value="${vo.bbsWriter}" /></td>
				<!-- 등록일시가 2023/06/29 14:00:12 형식으로 출력되도록 변경 -->
				<td><fmt:formatDate value="${vo.bbsRegDate}" pattern="yyyy/MM/dd  HH:mm:ss"/></td>
			</tr> 
		</c:forEach>
	</tbody>
</table>

<form id="searchForm" action="${pageContext.request.contextPath}/bbs/list.do">
	<select name="searchType">
	<!-- c:if 또는 : ? 사용하여 검색탭 유지하기 -->
<%-- 		<option value='title' ${searchInfo.searchType == 'title'?'selected':''}>제목</option>
		<option value='content' ${searchInfo.searchType == 'content'?'selected':''}>내용</option>
		<option value='total' ${searchInfo.searchType == 'total'?'selected':''}>제목+내용</option> --%>
		<option value='title'>제목</option>
		<option value='content'>내용</option>
		<option value='total'>제목+내용</option>
	</select>
	<script type="text/javascript">
		if ('${searchInfo.searchType}') {
		document.querySelector('[name="searchType"]').value = '${searchInfo.searchType}';
//		$('[name="searchType"]').val('${searchInfo.searchType}');
			
		}
	</script>
	<input type="text" name="searchWord" value="${searchInfo.searchWord}"/>
	<input type="hidden" name="currentPageNo" value="1" /> <!-- 이렇게 붙여주면 검색한것도 페이징 됨 -->
	<input type="submit" value="검색" />
</form>

${searchInfo.pageHtml}
<script>
	function goPage(n) {
		document.querySelector('[name="currentPageNo"]').value = n; 
		document.querySelector('#searchForm').submit(); 
		
	}
</script>

</body>               
</html>               	
