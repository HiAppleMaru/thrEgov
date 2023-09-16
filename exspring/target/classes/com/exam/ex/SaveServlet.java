package com.exam.ex;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//다수의 서블릿들이 공유하는 데이터를 저장하는 공간
//1.서버(톰캣)에 저장
// (1)요청 객체 
//	  - 요청 1개마다 1개의 요청객체 생성, 요청처리가 끝나면 소멸
//    - 하나의 요청을 처리하기 위해 사용되는 서블릿들간의 데이터 공유 (forward,include)
// (2)세션 객체
//    - 클라이언트(웹브라우저) 1개당 1개의 세션 객체 생성
//    - 클라이언트 종료시, 서버 종료시, 일정시간동안 요청이 없을 때 세션 객체 소멸
//    - 클라이언트(사용자,웹브라우저)별로 각각 유지해야하는 데이터 공유
// (3)서블릿컨텍스트 객체
//	  - 웹 애플리케이션 전체에서 1개의 서블릿컨텍스트 객체만 생성
//    - 서버(톰캣)가 시작될 때 생성, 서버가 종료될 때 소멸
//	  - 모든 사용자와 모든 서블릿들이 데이터 공유
//* 요청객체, 세션객체, 서블릿컨텍스트객체 모두 동일한 메서드로 데이터 저장
// - 객체.setAttribute("속성명", 속성값) : 속성값 저장
// - 객체.getAttribute("속성명") : 속성값 읽기
// - 객체.removeAttribute("속성명") : 속성 삭제
//2.클라이언트(웹브라우저)에 저장
// (1)쿠키
//    - 웹브라우저에 데이터를 이름-값 쌍으로 저장
//    - 기본적으로 쿠키를 저장한 웹사이트(도메인)와 
//      동일한 웹사이트로 요청을 보낸때 쿠키를 요청헤더에 자동으로 포함(변경 가능)
//    - 만료기간을 설정하면, 웹브라우저가 종료되더라도 쿠키 값 유지 가능
//    - 웹브라우저에서 접근하여 사용 가능하기 때문에 보안상 위험 존재
//    - 쿠키의 이름과 값은 쉼표,세미콜론,공백 등 특수문자와 한글 등 비영어권 문자 사용 불가
//		일반적으로 쿠키이름은 영문자와 숫자만 사용, 쿠키값은 인코딩/디코딩하여 사용
// (2)HTML5에서는 sessionStorage,localStorage,indexedDB도 사용 가능(다 웹브라우저에 저장하는것.)

@WebServlet("/save")
public class SaveServlet extends HttpServlet {

	@Override                      
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nval = req.getParameter("nn");  
		String rval = req.getParameter("rem");
		
		//현재 요청을 보낸 사용자의 세션객체 가져오기 (없으면 생성)
		HttpSession session = req.getSession(); //Ctrl+1
		session.setAttribute("nick", nval); //세션객체에 "nick"라는 이름으로 nval 변수값을 저장
		
		//현재 웹 애플리케이션의 서블릿컨텍스트 객체 가져오기
		ServletContext context = getServletContext();
		context.setAttribute("nick", nval); //서블릿 컨텍스트객체에 "nick"라는 이름을 저장
		//체크한 경우에만 쿠키에 저장을 하기
		//java 에서 문자열은 ==등호 말고 equals로 비교해야됨.
		if("on".equals(rval)) {
			String enval = URLEncoder.encode(nval, "UTF-8"); //nval값을 인코딩하여 enval로 지정, 한글,특수문자 포함시 인코딩 필요
			Cookie c = new Cookie("nick", enval); //데이터 이름-값 을 담은 쿠키 생성
			//응답객체에 위에 쿠키를 받아서 저장해야 되기 때문에...
			//쿠키는 서버가 만료되도 저장이 되어있는지 확인해보겠음.
			c.setMaxAge(60*5); //쿠키 유효기간(초) 설정 (0은 즉시 삭제, 음수는 웹브라우저 종료시 삭제(-1)) //브라우저 다 끄고 톰캣도 끄고...
//			c.setDomain("도메인"); //지정한 도메인과 하위 도메인으로 요청을 전송할 때만 쿠키 포함, google.com -> www.google.com, mail.google.com
//								 //daum.net, qwert.daum.net, aa234.daum.net <> dawm.net, daum.net.kr은 다른 사이트.
//								 //      daum.net 도메인을 소유한 회사꺼           <>  전혀 다른 도메인 소유자
//			c.setPath("경로");    //지정한 경로의 하위 경로로 요청을 전송할 때만 쿠키 포함, 설정안하면 지금 접속한 경로로만... "/exweb/~"
//			c.setHttpOnly(true); //true로 설정하면 자바스크립트로 쿠키 접근 불가, 기본값은(false)로 되어있음.
//			c.setSecure(true);   //true로 설정하면 https:// 와 같은 보안프로토콜 사용시에만 쿠키 포함
//			c.setComment("부가설명");//쿠키에 대한 부가설명
//			c.setVersion(0); // 0->인터넷 초창기에 맞춘 쿠키설정, 1->요새 인터넷 설정에 맞춘 쿠키설정, 아직은 대부분 0을 씀
			resp.addCookie(c); //웹브라우저가 쿠키를 저장하도록 응답에 포함 
		} 
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();//응답객체에 내용을 쓸 수 있는 Writer 가져오기
		
		out.println("<!DOCTYPE html>       ");
		out.println("<html>                ");
		out.println("<head>                ");
		out.println("<meta charset='UTF-8'>");
		out.println("<title>HELLO</title>  ");
		out.println("</head>               ");
		out.println("<body>                ");
		out.println("	<h1>저장 완료</h1>");
		out.println("</body>               ");
		out.println("</html>               ");
	}
	
}