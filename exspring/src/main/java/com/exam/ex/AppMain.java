package com.exam.ex;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppMain {
	
	public static void main(String[] args) {
		//MyApp, MyServiceKo 를 사용하여,
		//콘솔에 "안녕"이 출력되도록 구현
		
//		MyApp app = new MyApp();
//		MyServiceKo msk = new MyServiceKo();
//		app.setMyService(msk);
//		MyServiceEn mse = new MyServiceEn();
//		app.setMyService(mse);

// 		이 일을 Spring으로 만들겠음..
		
//		스프링 == (IoC/DI와 AOP 기능을 가진) 객체컨테이너 == BeanFactory == ApplicationContext
//		IoC = 메소드나 객체의 호출작업을 개발자가 결정하는 것이 아니라, 외부에서 결정되는 것을 의미
//		DI = 객체를 직접 생성하는 게 아니라 외부에서 생성한 후 주입 시켜주는 방식
//		라이브러리 = 주로 내가, 필요할때 외부것 끼워넣기
//		프레임워크 = 주로 남이, 필요할때 내것 끼워넣기
		
//		클래스패스 상의 XML 파일로부터 설정을 읽어서, 스프링 객체 컨테이너를 생성
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("/com/exam/ex/context.xml");
		
//		JAVA 클래스로부터 설정을 읽어서, 스프링 객체 컨테이너를 생성
		ApplicationContext ctx = new AnnotationConfigApplicationContext(MyConfig.class);
		
//		스프링에 "ma"라는 이름으로 등록되어 있는 객체를 가져오기
		MyApp app = (MyApp) ctx.getBean("ma");		//		MyApp app = new MyApp();
		
		app.say();

	}

}
