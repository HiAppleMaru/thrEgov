package com.exam.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//회원목록의 각 회원정보 옆에 "삭제" 링크를 출력하고,
//링크를 클릭하면 해당 회원이 삭제되도록
//MemListServlet 클래스를 변경하세요.

//삭제 링크가 버튼 모양이면 더 좋을 것 같아요.


@WebServlet("/member/list.do")
public class MemListServlet extends HttpServlet {
	private MemberService memberService = MemberServiceImpl.getInstance(); //다른 클래스에서 쓸일이 없기 때문에... private (안붙여도 기능상 문제 없음.)
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
				
		List<MemberVo> list = memberService.selectMemberList();
		
		req.setAttribute("memberList", list);
		
		req.getRequestDispatcher("/WEB-INF/views/member/memList.jsp").forward(req, resp);
		
		//memberDao는 변수 3개를 가지고 있는데 이는 요청할때마다 값이 안바뀌기 때문에...
//db에서 값을 다 불러서 list에 저장하고.. 아래 출력...		
		/*resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<!DOCTYPE html>       ");
		out.println("<html>                ");
		out.println("<head>                ");
		out.println("<meta charset='UTF-8'>");
		out.println("<title>회원관리</title>  ");
		out.println("</head>               ");
		out.println("<body>                ");
		out.println("<h1>회원목록</h1>");
		out.println("<a href='" + req.getContextPath() + "/member/addform.do' >회원추가</a>");
		


		for (MemberVo vo : list) {
//		System.out.println( memId +":"+ memPass +":"+ memName +":"+ memPoint);
		out.println("<p>"+ vo.getMemId() +" : "+ vo.getMemPass() +" : "+ vo.getMemName() +" : "+ vo.getMemPoint()); 
		out.println("<a href='" + req.getContextPath() + "/member/del.do?memId=" + vo.getMemId() + "' >삭제</a>");
		out.println("<a href='" + req.getContextPath() + "/member/del.do?memId=" + vo.getMemId() + "' ><button type='button'>삭제</button></a>");
		out.println("</p>");
		}
		out.println("<button onclick=\"location.href='" + req.getContextPath() + "/member/addform.do'\">회원추가</button >");
		out.println("</body>               ");
		out.println("</html>               "); 				
	}*/


}
	
}
// 현재 페이지에서 다른 페이지으로 이동하기
//<a href="#" onclick="location.href='index.html'">text</a>
// 현재 페이지에서 다른 페이지으로 이동하기
//<button onclick="location.href='index.html'">text</button >

//회원목록이 이클립스 콘솔이 아닌 웹 브라우저 화면에 출력되도록 MemListServlet을 변경하세요.
