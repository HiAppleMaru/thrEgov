package com.exam.ex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration //스프링 설정파일 역할을 하는 클래스임을 표시(xml 대체)
@ComponentScan("com.exam.ex") //value 는 basepackage의 별명임, 하나만 있으면 삭제 가능
public class MyConfig {
	
	//@Bean//(name = "ma") //이 메서드에 반환하는 객체를 "ma"라는 이름으로 스프링에 등록
	public MyApp ma() { //이름을 생략하면 메서드명을 빈 이름으로 사용
		MyApp app = new MyApp();
		app.setMyService(mse()); // this.msk(), this.mse(), msk(), mse() 다 가능
		return app;
	}
	//@Bean//(name = "msk") //이 메서드에 반환하는 객체를 "msk"라는 이름으로 스프링에 등록
	public MyService msk() {
		return new MyServiceKo();
	}
	//@Bean//(name = "mse") //이 메서드에 반환하는 객체를 "mse"라는 이름으로 스프링에 등록
	public MyService mse() {
		return new MyServiceEn();
	}
	
}
