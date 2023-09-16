package com.exam.myapp.member;

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
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	ApplicationContext context;
	
	@RequestMapping(value = "/member/list.do", method = RequestMethod.GET)
	public String list(Model model) {	
		List<MemberVo> list = memberService.selectMemberList();//DB에서 회원목록 꺼내와서
		model.addAttribute("memberList", list);
		return "member/memList";
	}
	
	@RequestMapping(value = "/member/add.do", method = RequestMethod.GET)
	public String addform(MemberVo vo) {
		String message = context.getMessage("Size", null, null);
		System.out.println(message);
		return "member/memAdd";//스프링설정에 접두 접미 설정했으니 경로 축소
	}
	
	//스프링에 등록된 표준 BeanValidator를 사용하여
	//저장된 값을 검증하고 싶은 객체 매개변수 앞에 @Valid 적용
	//@Valid 매개변수 다음 위치에 검증결과를 저장하기 위한
	//BindingResult 또는 Errors 타입의 매개변수를 추가
	@RequestMapping(value = "/member/add.do", method = RequestMethod.POST)	
	public String add(@Valid MemberVo vo, BindingResult result) {
		if (result.hasErrors()) {//검증결과 오류가 있다면
//			for (FieldError fe : result.getFieldErrors()) {
//				System.out.println("** " + fe.getField());
//
//				for (String c : fe.getCodes()) {
//					System.out.println("==" + c);
//				}
//			}
			return "member/memAdd";//다시 입력하는 JSP로 이동
		}	
		//오류가 없다면	
		//spring에서는 객체의 속성이름과 파라미터이름이 같으면 자동으로 들어감.
		int n = memberService.insertMember(vo);
		System.out.println(n + "명의 회원 추가");
		return "redirect:/member/list.do"; //jsp로 가는것이 아니기 때문에 redirect 붙여서 알려줌.
		}//필터는 web.xml을 보면 됨.
	
	@RequestMapping(value = "/member/edit.do", method = RequestMethod.GET)
	public String editform(String memId, Model model)	{//jsp에 넣으려면 model modelmap 등등 사용
		MemberVo vo = memberService.selectMember(memId);
		model.addAttribute("mvo", vo);
		return "member/memEdit";
	}
	
	@RequestMapping(value = "/member/edit.do", method = RequestMethod.POST)
	public String edit(MemberVo vo) {
		int n = memberService.updateMember(vo);
		System.out.println(n + "명의 회원 변경"); 
		return "redirect:/member/list.do";
	}
	
	
	@RequestMapping(value = "/member/del.do", method = RequestMethod.GET)
	public String del(String memId) {
		int n = memberService.deleteMember(memId);
		System.out.println(n+"명의 회원 삭제");
		return "redirect:/member/list.do";
	}
	// 로그인 동작이 수행되도록 아래 메서드들을 변경
	
	@RequestMapping(value = "/member/login.do", method = RequestMethod.GET)
	public String loginform() {
		return "member/login";
	}
	
	@RequestMapping(value = "/member/login.do", method = RequestMethod.POST)
	public String login(MemberVo vo, HttpSession session) { //HttpServletRequest req) { //이렇게도 가능
		MemberVo mvo = memberService.selectLogin(vo); //vo에 아이디 패스워드 정보가 들어있으므로.. vo.getMemId(), vo.getMemPass()로 해도 되긴 함.	
		if(mvo == null) { //로그인 실패 -> mvo = null
			return "redirect:/member/login.do"; //실패하면 다시 login.do로 
		} else { //로그인 성공 -> mvo = not null
//			요청객체(요청할때만..), 세션객체(일정시간이 지날때 까지, 사용자별로), 서블릿컨텍스트객체(서버가 종료될때까지)
//			HttpSession session = req.getSession();
			session.setAttribute("loginUser", mvo); //세션에 로그인한 사용자정보 저장
			return "redirect:/member/list.do";
    	}
	}
	
	@RequestMapping(value = "/member/logout.do", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return  "redirect:/member/login.do";
	}
	
}	

