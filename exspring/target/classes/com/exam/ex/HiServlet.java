package com.exam.ex;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/hi.do")

public class HiServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.setContentType("text/html; charset=UTF-8"); //html형식과 UTF-8형식으로 지정함
	String 공룡 = req.getParameter("user"); // 문자열 '공룡' 지정, user=??? 할때 호출함	
		
	PrintWriter out = resp.getWriter(); //이렇게 호출자 화면에 보여주기로 지정
	//여기서부터 /html까지는 html형식을 지정해놓은 것임.	
	out.println("<!DOCTYPE html>       ");
	out.println("<html>                ");
	out.println("<head>                ");
	out.println("<meta charset='UTF-8'>");
	out.println("<title>HELLO</title>  ");
	out.println("</head>               ");
	out.println("<body>                ");
	out.println("<h1>" + 공룡 + "님 환영합니다.</h1>"); // user=???->공룡에 대입, 문자열 호출
	out.println("</body>               ");
	out.println("</html>               ");
	
	//"/hi.do?user=둘리" 로 요청을 보내면, 화면에 "둘리님 환영합니다."라고 출력되고
	//"/hi.do?user=고길동" 으로 요청을 보내면, 화면에 "고길동님 환영합니다.라고 출력되게
	//HiServlet의 내용을 변경하세요.
	}
}
