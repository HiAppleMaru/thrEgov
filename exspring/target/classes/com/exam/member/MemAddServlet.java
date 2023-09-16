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

@WebServlet("/member/add.do")
public class MemAddServlet extends HttpServlet {
	private MemberService memberService = MemberServiceImpl.getInstance();
	
	
//이미 Dao에서 같은것을 넣었기 때문에 필요 없음..	
//	String url = "jdbc:oracle:thin:@localhost:1521:xe"; //데이터베이스 서버 주소
//	String user = "web"; //데이터베이스 접속 아이디
//	String password = "web01"; //데이터베이스 접속 비밀번호. 이것또한 매번 반복할 이유 없으니 서비스 밖으로 뺌.
	
//get 방식의 form을 불러오고, form이 post방식의 데이터를 보내게 되므로 doPost를 통해 db에 데이터 입력.	
	@Override //get 요청왔을때 할일
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/member/memAdd.jsp").forward(req, resp);
	}
	
	@Override //post 요청왔을때 할일
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * super.doPost(req, resp); }
		 * 
		 * @Override protected void service(HttpServletRequest req, HttpServletResponse
		 * resp) throws ServletException, IOException {
		 */		//req.setCharacterEncoding("UTF-8"); //필터로 이동 복붙할거임...
		MemberVo vo = new MemberVo();
		vo.setMemId(req.getParameter("memId"));
		vo.setMemPass(req.getParameter("memPass")); 
		vo.setMemName(req.getParameter("memName"));
		vo.setMemPoint(Integer.parseInt(req.getParameter("memPoint"))); //이거를 하나의 객체에 넣어서 전달하면 파라미터 갯수가 늘어나도 깔끔..

		
		
		int n = memberService.insertMember(vo);
		
		System.out.println(n + "명의 회원 추가"); //기능상 필요는 없지만 n값을 받는것을 만들어야 분리할 메소드에 n을 사용할 수 있음
		
		// 이시점에서 추가된 목록을 보여주고 싶다면?
		// 회원목록 출력
		// MemListServlet 실행! 3가지 방법
		// forward : 요청객체와 응답객체를 전달하면서, 지정한 주소의 서블릿을 실행
		// req.getRequestDispatcher("/member/list.do").forward(req, resp); 
		// forward와 include 차이 : forward ->현재 서블릿에서는 더 이상 응답을 출력하지 않는다. 
//		   				          include ->현재 서블릿의 출력 내용 중간에 지정한 서블릿의 출력 내용을 포함. 
 		// include : 요청객체와 응답객체를 전달하면서, 지정한 주소의 서블릿을 실행
		// req.getRequestDispatcher("/member/list.do").include(req, resp); // 모든화면에서 공통적인것을 보여줘야될때, 메뉴바 같은거... 이럴때 사용함.
			
		// redirect : 지정한 주소로 이동하라는 명령을 담은 응답을 웹브라우저에게 전송
		resp.sendRedirect(req.getContextPath() + "/member/list.do"); 
		
		
//			resp.setCharacterEncoding("UTF-8");
//			resp.setContentType("text/html");
//			PrintWriter out = resp.getWriter();
//			out.println("<!DOCTYPE html>       ");
//			out.println("<html>                ");
//			out.println("<head>                ");
//			out.println("<meta charset='UTF-8'>");
//			out.println("<title>회원 추가 성공</title>  ");
//			out.println("</head>               ");
//			out.println("<body>                ");
//			out.println("<h1>" + n + "명의 회원 추가 성공</h1>");
//			out.println("<a href='" + req.getContextPath() + "/member/list.do' >회원목록</a>");
//			out.println("<button onclick=\"location.href='" + req.getContextPath() + "/member/list.do'\">회원목록</button >");
//			out.println("</body>               ");
//			out.println("</html>               ");
		}


	}
	
	

