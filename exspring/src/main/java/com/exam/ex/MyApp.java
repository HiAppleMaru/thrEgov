package com.exam.ex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



//이 클래스의 객체를 생성하여 "ma"라는 이름으로 스프링에 등록
@Component("ma") //java annotation 문법 -> value가 한개일때는 삭제 가능, "ma"를 삭제시 class값인 MyApp->myApp 으로 자동 지정됨.   
public class MyApp {
	//@Autowired, @Inject, @Resource : 스프링에 등록된 객체를 이 변수(속성)에 주입(저장)
	//Autowired, Inject -> 자동으로 찾음. 이름을 지정하지 않으면 변수 이름을 넣음.
	//지정하고 싶으면 @Qualifier("OO") (별명같은거 지정)
	//					@Named("OO") (정확히 이름지정)
	//@Resource : 이름으로 찾음. ("OO") 이름이 일치하는것을 찾고, 일치하는게 없으면 타입이 일치하는것을 찾음.
	@Autowired
	private MyService myService;
	
	public void say() {
		System.out.println(myService.getMessage());
	}

	public MyService getMyService() {
		return myService;
	}

	public void setMyService(MyService myService) {
		this.myService = myService;
	}
	
	
}
