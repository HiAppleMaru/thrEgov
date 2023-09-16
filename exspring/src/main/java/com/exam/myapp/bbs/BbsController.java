package com.exam.myapp.bbs;

import java.awt.PageAttributes.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.exam.myapp.member.MemberVo;


@Controller
@RequestMapping("/bbs/") // 현재 컨트롤러 클래스 내부의 모든 메서드들의 공통 경로 설정
public class BbsController {
	@Autowired
	private BbsService bbsService; //ALT+SHIFT+R 로 한꺼번에 고치기
	
	//@RequestMapping(value = "list.do", method = RequestMethod.GET)
	@GetMapping("list.do")
	public String list(Model model, SearchInfo info) { //SearchInfo의 첫글자만 소문자로 바꿔서 jsp가 읽어줌.	
		int cnt = bbsService.selectBbsCount(info); //전체 레코드 수 조회
		info.setTotalRecordCount(cnt); //전체 레코드 수 정보 설정
		info.makePageHtml(); //페이지 처리에 필요한 값들 계산
		
		List<BbsVo> list = bbsService.selectBbsList(info);//DB에서 회원목록 꺼내와서
		model.addAttribute("bbsList", list);
		return "bbs/bbsList";
	}
	
	//@RequestMapping(value = "add.do", method = RequestMethod.GET)
	@GetMapping("add.do")
	public String addform() {
		return "bbs/bbsAdd";//스프링설정에 접두 접미 설정했으니 경로 축소
	}
	
	//@RequestMapping(value = "add.do", method = RequestMethod.POST)	
	@PostMapping("add.do")
	public String add(BbsVo vo
//			, HttpSession session
			, @SessionAttribute("loginUser") MemberVo mvo //지정한 세션속성값을 이 변수에 주입(전달)(Spring 4.3이상)
			) {
		//MemberVo mvo = (MemberVo) session.getAttribute("loginUser"); //그러면 이 일을 Spring이 알아서 해줌
		vo.setBbsWriter(mvo.getMemId());
		
		int n = bbsService.insertBbs(vo);
		System.out.println(n + "개의 글 추가");
		return "redirect:/bbs/list.do"; //jsp로 가는것이 아니기 때문에 redirect 붙여서 알려줌.
		}//필터는 web.xml을 보면 됨.
	
	//@RequestMapping(value = "edit.do", method = RequestMethod.GET)
	@GetMapping("edit.do")
	public String editform(int bbsNo, Model model)	{//jsp에 넣으려면 model modelmap 등등 사용
		BbsVo vo = bbsService.selectBbs(bbsNo);
		model.addAttribute("bbsVo", vo);
		return "bbs/bbsEdit";
	}
	
	//@RequestMapping(value = "edit.do", method = RequestMethod.POST)
	@PostMapping("edit.do")
	public String edit(BbsVo vo, @SessionAttribute("loginUser") MemberVo mvo) {
		vo.setBbsWriter(mvo.getMemId());
		int n = bbsService.updateBbs(vo);
		System.out.println(n + "개의 게시글 변경"); 
		return "redirect:/bbs/list.do";
	}
	
	
	//@RequestMapping(value = "del.do", method = RequestMethod.GET)
	@GetMapping("del.do")
	public String del(BbsVo vo, @SessionAttribute("loginUser") MemberVo mvo) {
		vo.setBbsWriter(mvo.getMemId());
		int n = bbsService.deleteBbs(vo);
		System.out.println(n+"개의 게시글 삭제");
		return "redirect:/bbs/list.do";
	}
	
	//컨트롤러 메서드가 인자로 HttpServletResponse, OutputStream, Writer를 받고
	//반환타입이 void 이면,
	//직접 응답을 처리(전송)했다고 판단하여 스프링은 뷰에 대한 처리를 하지 않는다.
	@GetMapping("down.do")									
	public void download(int attNo, HttpServletResponse resp) { //파일을 받아서 응답객체에게 보냄
		AttachVo vo = bbsService.selectAttach(attNo); //DB에서 다운로드할 첨부파일 정보 조회
		
		File f = bbsService.getAttachFile(vo); //디스크 상에서 첨부파일의 위치 가져오기
		
//		resp.setContentLength((int) f.length()); //응답메시지 본문(파일)의 크기 설정 
		resp.setContentLengthLong(f.length()); //length는 long으로 오기때문에 int로 형변환 or 아예 long으로 받음
		//어떤 타입인지 알려주면 됨.
		resp.setContentType("application/octet-stream"); //팝업창을 띄우게 만듬. 
//		resp.setContentType( MediaType.APPLICATION_OCTET_STREAM_VALUE ); //그냥 받음.
		
		//다운로드 파일을 저장할 때 사용할 디폴트 파일명 설정 
		//지원하는 브라우저에 따라서 다른 처리가 필요할 수 있음. 
//		try {
//			//방법1.
//			//URLEncoder.encode는 공백을 +로 바꿈.
//			String fname = URLEncoder.encode(vo.getAttOrgName(), "UTF-8").replace("+", "%20");
//			resp.setHeader("Content-Disposition", "attachment; filename*=UTF-8''"+fname);
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		//방법2.
		String cdv = ContentDisposition.attachment().filename(vo.getAttOrgName(), StandardCharsets.UTF_8).build().toString();
						//스프링 5.0부터 가능																					// 설정한대로->만들고->문자열지정
		resp.setHeader(HttpHeaders.CONTENT_DISPOSITION, cdv);
		
		
		
		try {
			//파일 f의 내용을 응답 객체(의 출력 스트림)에 복사(전송)
			FileCopyUtils.copy(new FileInputStream(f) , resp.getOutputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
}	

