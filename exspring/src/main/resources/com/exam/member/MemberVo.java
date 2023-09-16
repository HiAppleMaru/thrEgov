package com.exam.member;

public class MemberVo {
//Vo -> Value Object의 약자... DB에 있는 value의 테이블 하나...
	private String memId; //아이디 담을 변수 
	private String memPass; //비번 담을 변수
	private String memName; //이름 담을 변수
	private int memPoint; //포인트 담을 변수
	//속성과 변수를 대체로 일치하게 만들지만, 무조건 같지 않아도 됨.
	//밖에서 쓸 수 있게 getter setter 만들기
	public String getMemId() { 
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPass() {
		return memPass;
	}
	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public int getMemPoint() {
		return memPoint;
	}
	public void setMemPoint(int memPoint) {
		this.memPoint = memPoint;
	}
	

	
	
	
// MemberVo 에는 이제 문자열,문자열,문자열,숫자 저장 가능... private로 지정하여 직접 접근 막기.
}
