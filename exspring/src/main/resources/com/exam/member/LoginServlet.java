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
import javax.servlet.http.HttpSession;

// 1.브라우저 주소창에
// http://localhost:8000/exweb/member/login.do
// 를 입력하여 접속하면, LoginServlet 클래스와 login.jsp 파일이 순차적으로 실행되어
// 로그인 화면이 출력되도록 구현
// 2. 로그인 화면에서 submit 버튼을 클릭하면, 
// LoginServlet 클래스의 doPost가 실행되도록 구현.


@WebServlet("/member/login.do")
public class LoginServlet extends HttpServlet {
	private MemberService memberService = MemberServiceImpl.getInstance();
//alt + shift + R -> 이름바꾸기
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String memId = req.getParameter("memId"); 파라미터 읽을 필요 없고...
//		MemberVo vo = memberDao.selectMember(memId ); 읽은 파라미터를 Vo객체에 넣을 필요도 없고
//		req.setAttribute("mvo", vo); vo에서 읽어온 정보를 페이지에 저장할 필요도 없다.
		
		
		req.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(req, resp);
	}
	
	@Override 
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVo vo = new MemberVo();
		vo.setMemId(req.getParameter("memId"));
		vo.setMemPass(req.getParameter("memPass")); 
		//아이디랑 비번을 줘서 이런 회원이 있는지 확인여부 db에다가 select 해서 찾아봄
		
		MemberVo mvo = memberService.selectLogin(vo); //vo에 아이디 패스워드 정보가 들어있으므로.. vo.getMemId(), vo.getMemPass()로 해도 되긴 함.	
		
		if(mvo == null) { //로그인 실패 -> mvo = null
			resp.sendRedirect(req.getContextPath() + "/member/login.do"); //실패하면 다시 login.do로 
		} else { //로그인 성공 -> mvo = not null
//			요청객체(요청할때만..), 세션객체(일정시간이 지날때 까지, 사용자별로), 서블릿컨텍스트객체(서버가 종료될때까지)
			HttpSession session = req.getSession();
			session.setAttribute("loginUser", mvo); //세션에 로그인한 사용자정보 저장
			resp.sendRedirect(req.getContextPath() + "/member/list.do"); 
    	}
		
		
		}


	}
	
	

