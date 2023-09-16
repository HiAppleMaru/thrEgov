package com.exam.ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc.do")
public class CalServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("UTF-8");
	String xval = req.getParameter("x");
	String yval = req.getParameter("y");
	String opval = req.getParameter("op");
	double xnum = Double.parseDouble(xval);
	double ynum = Double.parseDouble(yval);
	

	
	
	//op 파라미터값에 맞는 사칙연산을 수행
	//문자열 값을 동등비교하는 경우, == 연산자가 아닌 .equals() 메소드 사용
	//"문자열1" == "문자열2" (x)
	//"문자열1".equals("문자열2") (o)
	double result = 0;
	String operator = "+";
	switch (opval) {
	case "plu":
		result = xnum + ynum;
		operator = "+";
		break;
	case "min":
		result = xnum - ynum;
		operator = "-";
		break;
	case "mul":
		result = xnum * ynum;
		operator = "*";
		break;
	case "div":
		result = xnum / ynum;
		operator = "/";
		break;
	}
	
	
	//Parameter는 항상 문자열이다.
	//숫자타입클래스명.parse숫자타입명("숫자문자열")
	//Integer.parseInt("123") == 123
	//Float.parseFloat("123.456") == 123.456
	//Double.parseDouble("123.456") == 123.456
	
	resp.setCharacterEncoding("UTF-8");
	resp.setContentType("text/html");
	PrintWriter out = resp.getWriter();
	
	out.println("<!DOCTYPE html>       ");
	out.println("<html>                ");
	out.println("<head>                ");
	out.println("<meta charset='UTF-8'>");
	out.println("<title>계산기</title>  ");
	out.println("</head>               ");
	out.println("<body>                ");
	out.println("<h1> " + xval + operator + yval + " = " + result + " </h1>");
	out.println("</body>               ");
	out.println("</html>               ");
	
	
	}
}
