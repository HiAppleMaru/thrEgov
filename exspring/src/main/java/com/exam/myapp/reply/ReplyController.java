package com.exam.myapp.reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.exam.myapp.member.MemberVo;

//@Controller
@RestController //현재 클래스의 모든 요청처리 메서드에 @ResponseBody를 적용
public class ReplyController {
	
	@Autowired
	private ReplyService replyService; 
	
	@GetMapping("/reply/list.do")
//	@ResponseBody //메서드의 반환값을 그대로 응답메시지 내용으로 전송
	public List<ReplyVo> list(int repBbsNo) {
		List<ReplyVo> repList = replyService.selectReplyList(repBbsNo);
		return repList;
		}//여기서 더 보내야 할게 없으니 그냥 repList를 보내겠음. 더 보낼게 있으면 Map으로 만들어서 밑에같이 put해야함.

		@PostMapping("/reply/add.do") 
//		@ResponseBody //메서드의 반환값을 그대로 응답메시지 내용으로 전송
		public Map<String, Object> add(ReplyVo vo, @SessionAttribute("loginUser") MemberVo mvo) {
			
			vo.setRepWriter(mvo.getMemId());
			
			int num = replyService.insertReply(vo);
			
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("ok", true);
			map.put("result", num);
			
			
//			return "redirect:/bbs/bbs/edit.do?bbsNo="+vo.getRepBbsNo();
//			return "{ \"ok\": true, \"result\" : " + num + " }"; //이렇게 보내줘야 되는데 라이브러리를 쓰면 자동으로 바꿔줌.
			return map;
		}
		
		@GetMapping("/reply/del.do")
//		@ResponseBody
		public Map<String, Object> del(ReplyVo vo, 
		@SessionAttribute("loginUser") MemberVo mvo) {
			vo.setRepWriter(mvo.getMemId());			
			
			int num = replyService.deleteReply(vo);
			
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("ok", true);
			map.put("result", num);
			return map;
		}
		
		
}
//MemberVo v = new MemberVo();
//v.setMemId("a001");
//v.setMemName("쌍문동소드마스터고길동");
//v.setMemPoint(80);

//자바스크립트로 위의 v와 동일한 데이터를 저장한 객체를 정의
//var v = {memId: "a001", memName: "쌍문동소드마스터고길동", memPoint: 80 };


//JSON은 자바스크립트 객체 표현과 동일하지만 2가지 차이점 존재
//(1)문자열은 반드시 큰따옴표만 가능(작은따옴표사용불가)
//(2)객체의 속성이름은 반드시 문자열로 표현
//String jsonStr = "{ \"memId\" : \"a001\", \"memName\": \"쌍문동소드마스터고길동\", memPoint: 80 }";
//직접 이렇게 만들기에는 너무 번거롭고 귀찮기 때문에... 변환 라이브러리를 사용함. ex)Jackson, Gson
