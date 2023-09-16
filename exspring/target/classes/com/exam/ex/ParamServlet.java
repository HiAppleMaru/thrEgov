package com.exam.ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/param.do")
public class ParamServlet extends HttpServlet{
@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//GET 방식으로 전송된 요청파라미터값을 읽을 때 사용할 문자인코딩 방식은
	//톰캣의 server.xml 파일에 있는 <Connector protocol="HTTP/1.1"> 태그에
	//URIEncoding="UTF-8" 속성을 추가하여 지정 가능
	
	//POST 방식으로 전송된 요청파라미터값을 읽을 때 사용할 문자인코딩 방식 설정
	//혼동되면 2가지 방법 다 해버리자
	//POST 방식으로 전송하면, 보안상 유리하고, 파라미터의 길이제한이 없는 장점 존재 
		req.setCharacterEncoding("UTF-8");
		String xval = req.getParameter("x");  
		String yval = req.getParameter("y");
		
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		out.println("<!DOCTYPE html>       ");
		out.println("<html>                ");
		out.println("<head>                ");
		out.println("<meta charset='UTF-8'>");
		out.println("<title>HELLO</title>  ");
		out.println("</head>               ");
		out.println("<body>                ");
		out.println("<h1>x 파라미터값 : " + xval + "</h1>");
		out.println("<h1>y 파라미터값 : " + yval + "</h1>");
		out.println("</body>               ");
		out.println("</html>               ");
	}
}


//"param.do?x=포로리&y=너부리"로 요청을 보내면,
//화면에
//"x 파라미터값 : 포로리"
//"y 파라미터값 : 너부리"
//라고 출력되도록 ParamServlet을 완성하세요.