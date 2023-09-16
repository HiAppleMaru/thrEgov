package com.exam.ex;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

	@Override                      
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		out.println("<!DOCTYPE html>       ");
		out.println("<html>                ");
		out.println("<head>                ");
		out.println("<meta charset='UTF-8'>");
		out.println("<title>HOME</title>  ");
		out.println("</head>               ");
		out.println("<body>                ");
		out.println("	<h1>HOME</h1>");
		
		//SaveServlet에서 저장한 데이터를 읽어서 출력
		HttpSession session = req.getSession();//세션객체 가져오기... 집어넣을땐 set 가져올땐 get
		String nickName = (String) session.getAttribute("nick"); //세션객체에 "nick"라는 이름으로 저장된 데이터 읽기, String으로 저장한것을 알고 있기 때문에 String 형변환.
		out.println("세션에 저장된 닉네임: " + nickName + "<br>");

		//SaveServlet에서 저장한 데이터를 읽어서 출력. 변수 이름이 같기 때문에 오류... 바꿔줌
		ServletContext context = getServletContext();
		String contextNick = (String) context.getAttribute("nick"); //서블릿컨텍스트객체에 "nick"라는 이름으로 저장된 데이터 읽기, String으로 저장한것을 알고 있기 때문에 String 형변환.
		out.println("서블릿컨텍스트에 저장된 닉네임: " + contextNick + "<br>");
// 사용자마다 다른값이 필요하면 세션에 저장... 같은값을 저장해야 되면 컨텍스트에 저장!
		
		//요청헤더(Cookie)에 포함된 쿠키 값들을 읽기. 쿠키는 달달해..
		Cookie[] cookies = req.getCookies(); //바로 꺼내는 명령어는 없기 때문에 쿠키들을 돌아다니면서 뽑아내야됨.
		for (Cookie c : cookies) { //cookies를 돌아댕기면서 찾음. for문
			if ("nick".equals(c.getName() ) ) { //쿠키이름이 "nick"인 경우
				//읽어올때는 인코딩된 enval을 디코딩해야됨.
				String v = URLDecoder.decode(c.getValue(), "UTF-8"); //인코딩때와 문자표 맞추기, 디코딩해서 v로 지정
				out.println("쿠키에 저장된 닉네임: " + v + "<br>"); //쿠키는 브라우저별로 저장되는거기 때문에 다른브라우저에서는 안나옴.
			}
		}
		
		
		out.println("</body>               ");
		out.println("</html>               ");
	}
	
}