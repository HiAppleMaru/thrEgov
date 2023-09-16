package com.exam.ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/form.do")
public class FormServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String pro = req.getParameter("prod");
		String fru = req.getParameter("fruit");
		String[] dri = req.getParameterValues("drink");
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter(); //응답객체에 내용을 쓸 수 있는 Writer 가져오기
		
		out.println("<!DOCTYPE html>       ");
		out.println("<html>                ");
		out.println("<head>                ");
		out.println("<meta charset='UTF-8'>");
		out.println("<title> 주문하신 상품 목록 </title>");
		out.println("</head>               ");
		out.println("<body>                ");
		out.println("<h1>요청주소 : " + req.getRequestURL() + "</h1>");
		out.println("<h1>요청주소 : " + req.getRequestURI() + "</h1>");
		out.println("<h1>애플리케이션 고유경로 : " + req.getContextPath() + "</h1>"); 
		out.println("<h1>요청방식 : " + req.getMethod() + "</h1>");
		out.println("<h1>User-Agent 요청헤더 : " + req.getHeader("User-Agent") + "</h1>");
		
		
		out.println("<h1> 주문할 상품 : " + pro + "</h1>");
		out.println("<img src='https://api.lorem.space/image/" + pro + "?w=450&h=450' />");
		
		/*
		 * switch (pro) { case "shoes":
		 * out.println("<img src=https://api.lorem.space/image/shoes?w=150&h=150>");
		 * break; case "watch":
		 * out.println("<img src=https://api.lorem.space/image/watch?w=150&h=150>");
		 * break; case "furniture":
		 * out.println("<img src=https://api.lorem.space/image/furniture?w=150&h=150>");
		 * break; }
		 */	
	out.println("<h2> 선택한 과일의 이름 : " + fru + " </h2>");
	out.println("<h2> 선택한 음료의 이름 : ");
	// 값을 하나도 못받으면 value 값은 null이 된다.
	if (dri != null) {
	for (int i = 0; i < dri.length; i++) {
		out.println("[" + dri[i] + "]");
		}
	}
	out.println("</h2>");
	out.println("</body>               ");
	out.println("</html>               ");
	
	
	}
}
