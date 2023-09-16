package com.exam.comm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet; //서블릿 역할을 하기 위한 상속

public class DriverServlet extends HttpServlet {

	//요청을 받았을때 드라이버를 불러오기 위한것이기 때문에
	@Override
	public void init() throws ServletException {
		System.out.println("DriverServlet init() 실행");
		
		String cname = getInitParameter("driver"); //현재 서블릿의 "driver" 초기화파라미터 값 읽기
		
			// 애플리케이션에 JDBC 사용 전에 최초 1번은 JDBC 드라이버 클래스를 메모리에 로드 필요
		try {
			Class.forName(cname); //얘를 자바에 직접쓰지 않고 설정파일로 만들어서 쓰고 싶을때...
		} catch (ClassNotFoundException e) {
			e.printStackTrace();}
		}
	//다른 서블릿에 가서 같은 부분을 지워줬음. 이제 톰캣에 이 서블릿을 등록해야됨. import시키거나 web.xml에 등록
	//서블릿의 목적은 요청이 왔을때 수행하는것인데, 얘는 아예 미리 시작해버리게 했으니.. 서블릿의 목적에 위배되기 때문에... 이것을 Listener로 바꿔 줄 예정임...
	
}
