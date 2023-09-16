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

@WebServlet("/member/edit.do")
public class MemEditServlet extends HttpServlet {
	private MemberService memberService = MemberServiceImpl.getInstance();
//회원목록에서 아이디를 클릭하면 MemEditServlet 과 memEdut.jsp가 순차적으로 실행되어
//회원정보변경 화면이 브라우저에 출력되도록 구현	

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String memId = req.getParameter("memId");
		//이동전에 db에서 정보 꺼내와야 됨.
		MemberVo vo = memberService.selectMember(memId );
		req.setAttribute("mvo", vo);
		
		
		req.getRequestDispatcher("/WEB-INF/views/member/memEdit.jsp").forward(req, resp);
	}
	
	@Override //post 요청왔을때 할일
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVo vo = new MemberVo();
		vo.setMemId(req.getParameter("memId"));
		vo.setMemPass(req.getParameter("memPass")); 
		vo.setMemName(req.getParameter("memName"));
		vo.setMemPoint(Integer.parseInt(req.getParameter("memPoint"))); //이거를 하나의 객체에 넣어서 전달하면 파라미터 갯수가 늘어나도 깔끔..
		int n = memberService.updateMember(vo);
		System.out.println(n + "명의 회원 변경"); //기능상 필요는 없지만 n값을 받는것을 만들어야 분리할 메소드에 n을 사용할 수 있음
		resp.sendRedirect(req.getContextPath() + "/member/list.do"); 
		}


	}
	
	

