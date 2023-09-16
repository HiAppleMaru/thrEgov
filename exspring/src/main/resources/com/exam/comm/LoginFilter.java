package com.exam.comm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.member.MemberVo;

//필터 : 서블릿의 실행 전후에 끼어들어가서 실행
//      다수의 서블릿들이 수행하는 공통작업을 실행할때 사용
//      Filter 인터페이스를 구현하여 필터 클래스 정의
//web.xml 에 <filter> 태그로 등록하거나, 클래스에 @WebFilter 적용

public class LoginFilter implements Filter {
	//로그인 없이 사용가능한 요청경로들을 저장한 목록
	private List<String> whiteList = new ArrayList<String>();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		whiteList.add("/member/login.do"); //로그인화면
		whiteList.add("/member/add.do");  //회원추가화면
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
//		System.out.println("CharEncFilter doFilter() 실행");
//		request.setCharacterEncoding(enc);  
		HttpServletRequest req = (HttpServletRequest) request; //형변환 해서 받은뒤에 호출
		HttpServletResponse resp = (HttpServletResponse) response;
		
		System.out.println("URI: " + req.getRequestURI());
		System.out.println("URL: " + req.getRequestURL());
		String reqPath = req.getRequestURI().substring(req.getContextPath().length());
		System.out.println("reqPath: " + reqPath);
		
		if(whiteList.contains(reqPath) == false) { 
			//요청보낸 사용자의 세션을 가져와서
			HttpSession session = req.getSession();
			//세션에 로그인정보를 꺼내와서
			MemberVo vo = (MemberVo) session.getAttribute("loginUser");
			//로그인 정보가 없다면,
			if(vo == null) {
			//로그인페이지로 이동
				resp.sendRedirect(req.getContextPath() + "/member/login.do");
				return;
			}
		}
		//필터 전에 해야 될 작업
		chain.doFilter(request, response);
		//필터 후에 해야 될 작업
		
		
	}

	//	private String enc;
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//		System.out.println("CharEncFilter init() 실행");
//		enc = filterConfig.getInitParameter("encoding");
//	}
	

	
	@Override
	public void destroy() {
		//필터 객체가 소멸(삭제)되기 전에 1번 실행
//		System.out.println("CharEncFilter destroy() 실행");
	}
	
}
