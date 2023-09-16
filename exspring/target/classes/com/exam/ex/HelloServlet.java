package com.exam.ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//서블릿은 일반적으로 HttpServlet 클래스를 상속(확장)하여 구현
//서블릿 맵핑 : 어떤 경로(주소)로 요청이 왔을때 서블릿을 실행시키고 싶은지
//			요청경로와 서블릿을 연결
//서블릿 맵핑 방법 2가지 
// (1) web.xml 파일에 <servlet><servlet-mapping>태그를 사용하여 설정 (새로나온 방식, 소스파일 필요)
// (2) 서블릿 클래스에 @WebServlet("요청주소")를 적용하여 설정 (전통적인 방식 class파일만으로도 가능)

//"/hello.do" 파일을 달라는 요청이 오면 이 서블릿 클래스를 실행하라
@WebServlet("/hello.do")
//서블릿 URL패턴(주소,경로) 지정 규칙
// - 반드시 "/"또는 "*." 으로 시작 "foo/*" or "foo/*.do"등 다양한 형태 존재
// - "*"은 0개 이상의 모든 문자열과 일치
// - /hello.do형태와 /*.do형태 존재시 hello.do 주소 넣으면 *.do보다 hello.do가 우선순위(최대한 많이 일치하는것)
public class HelloServlet extends HttpServlet {
	
	//클라이언트(웹브라우저)의 요청을 받아서 서블릿이 실행될 때마다
	//서블릿의 service() 매서드가 한번씩 실행
	@Override                      
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //톰캣이 service() 메서드 실행시에 인자로 요청객체와 응답객체를 전달
	//요청객체(HttpServletRequest) : 클라이언트(웹브라우저)가 보낸 요청에 대한 모든 정보를 담고 있는 객체
	//응답객체(HttpservletResponse) : 요청에 대한 응답으로 클라이언트(웹브라우저)에게 전송할 모든 정보를 담는 객체
	//객체 : 변수들과 함수들의 집합체
		System.out.println("HelloServlet 실행! 근데 금요일은 좀 많이 힘들어 ㅠ");
		//요청주소 뒤에 "?파라미터명=파라미터값&파라미터명=파라미터값&..."
		//형태로 추가로 전달할 파라미터들을 지정 가능
		//x=2 & y=3, 구별문자는 = &
		//서블릿에서는 요청객체.getParameter("파라미터명") 명령문으로
		//원하는 파라미터의 값을 사용 가능
		String aval = req.getParameter("a");  
		// req.getParameter("a");하고 Ctrl+1, aval로 변경
		String bval = req.getParameter("b");
		
		resp.setCharacterEncoding("UTF-8");//응답내용을 쓸 때 사용할 문자인코딩 방식 지정
		resp.setContentType("text/html");//응답내용의 데이터타입을 설정(웹브라우저에게 정보제공)
	//	resp.setContentType("text/html; charset=UTF-8"); 문자인코딩과 데이터타입을 한번에 설정가능
	//                                                 	Encoding+type
		PrintWriter out = resp.getWriter();//응답객체에 내용을 쓸 수 있는 Writer 가져오기
		//out.println("Hello SERVLET"); //응답객체에 문자열을 출력
		//응답객체에 출력한 내용은 클라이언트(웹브라우저)로 전송된다
		
		out.println("<!DOCTYPE html>       ");
		out.println("<html>                ");
		out.println("<head>                ");
		out.println("<meta charset='UTF-8'>");
		out.println("<title>HELLO</title>  ");
		out.println("</head>               ");
		out.println("<body>                ");
		out.println("	<h1>서블릿이 출력한 HTML</h1>");
		out.println("	<h2>a : " + aval + "</h2>"); //aval 긁어서 Ctrl+1, pick out
		out.println("	<h2>b : " + bval + "</h2>");
		out.println("	<p>역시 금요일은 좀 많이 힘들어</p>");
		out.println("	<p>그래도 " + bval + "시간 남았다</p>");
		out.println("	<p>점심먹고는 " + aval + "시간 남았네</p>");
		out.println("</body>               ");
		out.println("</html>               ");
	}
	
}


//이클립스의 다이나믹웹프로젝트(톰캣)이 실행 중인 상태에서
// *.java 파일을 변경하면, 이클립스가 톰캣을 자동 재시작
// src/main/webapp 폴더의 정적 리소스(*.html, *.css...) 파일들을 변경하면
//  즉시 톰캣에 반영되므로 톰캣 재시작 없이 웹브라우저에서 새로고침만 필요
// web.xml 등 설정파일 변경시에는, 수동으로 톰캣 재시작 필요