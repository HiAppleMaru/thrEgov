<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- <!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>게시판</title>
</head>
<body> -->
<%-- <jsp:include page="/WEB-INF/views/menu.jsp" /> --%>
<h1>게시글정보변경</h1>
<form action='${pageContext.request.contextPath}/bbs/edit.do' method='post'>
	<input type='hidden' name='bbsNo' value="${bbsVo.bbsNo}"  />
	<c:set value='${bbsVo.bbsWriter!=loginUser.memId}' var="isMine" /> 
	<c:set value='${pageContext.request.contextPath}' var="contextPath" /> 
	<!-- 같은 조건내용이 반복될 때 c:set을 이용하여 줄여 쓰는게 이득... -->
	제목: <input <c:if test="${bbsVo.bbsWriter!=loginUser.memId}"> readonly</c:if>
	type='text' name='bbsTitle' value='<c:out value="${bbsVo.bbsTitle}" />' /><br>
	내용: <textarea ${bbsVo.bbsWriter!=loginUser.memId ? 'readonly' : ''} 
	 name="bbsContent" rows="5" cols="30" ><c:out value="${bbsVo.bbsContent}" /></textarea><br>
	작성자: <c:out value="${bbsVo.bbsWriter}" /> <br>
	등록일: <fmt:formatDate value="${bbsVo.bbsRegDate}" pattern="yyyy/MM/dd  HH:mm:ss"/> <br>
	<!-- 첨부파일들이 출력되도록 구현 -->
	<c:forEach var="vo" items="${bbsVo.attachList}">
	첨부파일: <a href="${pageContext.request.contextPath}/bbs/down.do?attNo=${vo.attNo}"><c:out value="${vo.attOrgName}" /></a> <br>
	</c:forEach>
	
	<!-- 자신이 작성한 글이 아닌 경우, 제목과 내용을 키보드로 입력할 수 없도록 구현 -->
	<c:if test="${bbsVo.bbsWriter==loginUser.memId }">  	
		<input type='submit' value="저장" />
		<a id="delLink" href='${pageContext.request.contextPath}/bbs/del.do?bbsNo=${bbsVo.bbsNo}' ><button type='button'>삭제</button></a>
	</c:if>
</form>

<hr>
<h3>댓글</h3>
<form id="replyForm" action="${pageContext.request.contextPath}/reply/add.do" method="post">
	<input type="hidden" name="repBbsNo" value="${bbsVo.bbsNo}" />
	내용<textarea name="repContent" rows="3" cols="30"></textarea>
	<input id="repSaveBtn" type="button" value="저장" />
</form>

<hr>

<div id="replyList" ></div>
<!--     <c:forEach var="rvo" items="${repVo}">
		작성자: <c:out value="${repVo.repWriter}" />
		내용: <c:out value="${repVo.repContent}" />
		작성일: <c:out value="${repVo.repRegDate}" />    
    </c:forEach>  -->

    
<template id="replyTemp">
	<div class="repContent"></div>    
	<div class="repWriter"></div>    
	<div class="repRegDate"></div>    
	<input type="button" value="삭제" class="delBtn" data-no="" />
	<hr>
</template>
 
<!-- jsp 관련을 layout에 넣으면 모든 페이지에서 설정없이 그냥 쓸 수 있음. -->
<script type="text/javascript">
	$('#delLink').on('click', function(ev) {
		var ok = confirm('정말 삭제?');
		if(!ok) {
//			ev.preventDefault();
			return false; //이벤트 전파를 중단하고, 이벤트에대한 브라우저의 기본동작을 취소
		} 
	});


//	<template> 엘리먼트의 내용은 content 속성을 사용하여 접근
	var $repTemp = $(document.querySelector('#replyTemp').content);
//	document.getElementById('replyTemp') html5이전 사용방식



	function refreshReplyList() {
	$.ajax({
	  url: "${pageContext.request.contextPath}/reply/list.do", //요청주소
	  method: "GET", //요청방식
	  data: { repBbsNo : ${bbsVo.bbsNo} }, //요청파라미터(현재 보고있는 게시글의 글번호)
	  dataType: "json" //응답데이터타입
	}).done(function(data) { //요청 전송 후 성공적으로 응답을 받았을 때 실행
	  console.log(data); // 콘솔창에 출력해보겠음.
/* 	  var listHtml = '';
	  for(var i=0; i<data.length; i++) {
	  	var repVo = data[i];
	  	console.log(repVo.repContent, repVo.repWriter, repVo.repRegDate);
	  	listHtml += '<div>' + repVo.repContent +'</div>';
	  	listHtml += '<div> 작성자: ' + repVo.repWriter +'</div>';
	  	listHtml += '<div> 작성일: ' + repVo.repRegDate +'</div>';
	  	if (repVo.repWriter == '${loginUser.memId}') {
	  	listHtml += '<div><input data-no="'+repVo.repNo+'" class="delBtn" type="button" value="삭제"></div>';
	  	}
	  	listHtml += '<hr>';
	 }
	  console.log(listHtml);
	  // listHtml 값을 id="replyList"인 div 엘리먼트의 내용으로 출력
	  $('#replyList').html(listHtml);
	   */
	  var listHtml = [];
	  for(var i=0; i<data.length; i++) {
	  	var repVo = data[i];
	  	//console.log(repVo.repContent, repVo.repWriter, repVo.repRegDate);
/*	  	listHtml.push( $('<div>').text(repVo.repContent) ); //<div>repVo.repContent</div> 태그포함.html, 태그미포함.text
	  	listHtml.push( $('<div>').text(repVo.repWriter) );   //<div>repVo.repWriter</div>
	  	listHtml.push( $('<div>').text(repVo.repRegDate) );//<div>repVo.repRegDate</div>
	  	if (repVo.repWriter == '${loginUser.memId}') {
	  		listHtml.push( $('<input>')//.attr({'data-no': repVo.repNo, type: 'button'})
	  											 .attr('data-no', repVo.repNo)
								  		  		 .attr('type', "button")
								  			 	 .addClass('delBtn')     //.attr('class', "delBtn")
								  				 .val('삭제')                 //.attr('value', "삭제") 
								  				 );
	  	}
	  	listHtml.push( $('<hr>') ); */
	  	
	  	var $newRep = $repTemp.clone();
	  	$newRep.find('.repContent').text(repVo.repContent);
	  	$newRep.find('.repWriter').text(repVo.repWriter);
	  	$newRep.find('.repRegDate').text(repVo.repRegDate);
	  	if(repVo.repWriter == '${loginUser.memId}'){
	  		$newRep.find('.delBtn').attr('data-no', repVo.repNo);
	  	}else{
	  		$newRep.find('.delBtn').remove();
	  	}
	  	
	  	listHtml.push($newRep);
//	  	$('div', $newRep)
//  	<div class="repContent"></div>    
//	    <div class="repWriter"></div>    
//  	<div class="repRegDate"></div>  
//		<input type="button" value="삭제" class="delBtn" data-no="" />
//		<hr>
	 }
//	  console.log(listHtml);
	  // listHtml 값을 id="replyList"인 div 엘리먼트의 내용으로 출력
	  $('#replyList').empty().append(listHtml);
	  
	}).fail(function( jqXHR, textStatus ) { //요청 처리에 오류가 발생했을때 실행
	  alert( "Request failed: " + textStatus );
	});
}

//댓글 삭제 기능
//삭제버튼을 클릭하면 해당 댓글이 삭제되도록,
//ReplyController.java, ReplyService.java, ReplyServiceImpl.java, ReplyDao.java
//ReplyMapper.xml. 파일을 변경
	$('#replyList').on('click', '.delBtn', function() {
		var ok = confirm('댓글 삭제합니다?'); //확인누르면 true, 취소누스면 false
//alert(확인), confirm(선택), prompt(문자열입력)	
		if (!ok) return;
		$.ajax({
			  url: "${pageContext.request.contextPath}/reply/del.do", 
			  method: "GET", 
			  data: { repNo : $(this).attr('data-no')}, 
			  dataType: "json"
			}).done(function(msg) { 
			refreshReplyList();
				alert(msg.result + "개의 댓글 삭제");
				}).fail(function( jqXHR, textStatus ) {
			  alert( "Request failed: " + textStatus );
			});		
	});
	
	refreshReplyList();

//저장버튼을 클릭했을 때, AJAX로 댓글 저장 요청을 전송
//AJAX
//(1) MxlHttpRequest 객체 사용
//(2) fetch() 함수 사용
//(3) $.ajax() 메서드 사용
$('#repSaveBtn').on('click', function() {	
	$.ajax({
	  url: "${pageContext.request.contextPath}/reply/add.do", //요청주소
	  method: "POST", //요청방식
//	  3가지 방법이 있음
// data : 'repBbsNo=${bbsVo.bbsNo}&repContent='+$('[name="repContent"]').val()' //1. 기초적인방법	  
	  data: { repBbsNo : ${bbsVo.bbsNo},
			   repContent : $('[name="repContent"]').val() }, //2. 많이 쓰는 방법. 요청파라미터
//	  data: $('#replyForm').serialize(), //3. 입력요소가 많을때...
	  dataType: "json" //응답데이터타입
	  //"json"으로 설정하면, 응답으로 받은 JSON 문자열을 자바스크립트 객체로 변환하여
	  //응답처리함수(done())에게 인자로 전달
	}).done(function(msg) { //요청 전송 후 성공적으로 응답을 받았을 때 실행
		// msg == '{"result":1,"ok":true}';
//		var data = JSON.parse(msg); //JSON(문자열)을 자바스크립트 객체로 변환
		// msg == {"result":1,"ok":true}; msg.result -> 1
		refreshReplyList();
		alert(msg.result + "개의 댓글 저장");// "1개의 댓글 저장" 이라고 출력하고 싶을때
		$('[name="repContent"]').val('');
		}).fail(function( jqXHR, textStatus ) { //요청 처리에 오류가 발생했을때 실행
	  alert( "Request failed: " + textStatus );
	});
});


</script>

<!-- </body>
</html> -->




 