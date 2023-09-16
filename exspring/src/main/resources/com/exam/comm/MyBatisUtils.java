package com.exam.comm;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtils {

	private static SqlSessionFactory sqlSessionFactory; //SqlSessionFactory 전체적으로 하나만 만들어야지 테이블마다 만들면 안됨.
	static {//CTRL+Shift+O : 자동 import		
		try { //예외처리함
			//마이바티스 전체 설정파일 위치 (클래스패스 기준)
			String resource = "batis/mybatis-config.xml"; //마이바티스 전체 설정파일 위치(이것만 바뀔 수 있음)
			InputStream inputStream = Resources.getResourceAsStream(resource); 
			//설정파일의 내용대로 SqlSessionFactory(마이바티스본체)를 생성		
			 
			//sqlSessionFactory 객체 생성
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}  
		catch (IOException e) {
		e.printStackTrace();
		}
	}
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	
}
