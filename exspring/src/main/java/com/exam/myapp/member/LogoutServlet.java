package com.exam.myapp.member;

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
import javax.servlet.http.HttpSession;

@WebServlet("/member/logout.do")
public class LogoutServlet extends HttpServlet {
//	private MemberDao memberDao = new MemberDaoBatis(); //db 사용 안함

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
//		session.setAttribute("loginUser", null); //세션에 지정한 이름의 속성값을 null로 설정
//		session.removeAttribute("loginUser"); //세션에서 지정한 이름의 속성을 삭제
		session.invalidate(); //세션객체를 제거(후 다시 생성) 셋중에 하나 쓰면 됨.
		
		resp.sendRedirect(req.getContextPath() + "/member/login.do");
	}
}
	
	

