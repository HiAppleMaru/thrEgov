package com.exam.myapp;

public class MyVo {
	private int x; //속성이름이 파라미터 이름과 같아야 된다.
	private int y;
	
	public int getX() { //속성이름은 4행 x가 아니고 7행의 get을 제외한 X가 됨.
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	
	
}
