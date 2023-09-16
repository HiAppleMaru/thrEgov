package com.exam.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//웹브라우저에서 
// http://localhost:8000/exweb/member/del.do?memId=삭제할회원아이디
//로 요청을 보내면, 지정한 회원 정보를 데이터베이스에서 삭제하고
//"몇명의 회원 삭제 성공" 메시지와 "회원목록으로 이동" 링크를 화면에 출력

//delete from member where mem_id = 'a005'; 를 활용.

@WebServlet("/member/del.do")
public class MemDelServlet extends HttpServlet {
	private MemberService memberService = MemberServiceImpl.getInstance();
	
//	String url = "jdbc:oracle:thin:@localhost:1521:xe"; //데이터베이스 서버 주소
//	String user = "web"; //데이터베이스 접속 아이디
//	String password = "web01"; //데이터베이스 접속 비밀번호. 이것또한 매번 반복할 이유 없으니 서비스 밖으로 뺌.

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
		String memId = req.getParameter("memId");
		//컬럼이 늘어나도 상관없으니 이부분 안옮겨도 됨..
		
		int n = memberService.deleteMember(memId);


		System.out.println(n+"명의 회원 삭제"); //필요는 없지만 n값을 리턴받기 위해...

		resp.sendRedirect(req.getContextPath() + "/member/list.do");
		

//		
//			resp.setCharacterEncoding("UTF-8");
//			resp.setContentType("text/html");
//			PrintWriter out = resp.getWriter();
//			out.println("<!DOCTYPE html>       ");
//			out.println("<html>                ");
//			out.println("<head>                ");
//			out.println("<meta charset='UTF-8'>");
//			out.println("<title>회원 삭제 성공</title>  ");
//			out.println("</head>               ");
//			out.println("<body>                ");
//			out.println("<h1>" + n + "명의 회원 삭제 성공</h1>");
//			out.println("<a href='" + req.getContextPath() + "/member/list.do' >회원목록</a>");
//			out.println("<button onclick=\"location.href='" + req.getContextPath() + "/member/list.do'\">회원목록</button >");
//			out.println("</body>               ");
//			out.println("</html>               ");
		}
		
	}

	
	

