package com.exam.myapp.bbs;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BbsVo {
	private int bbsNo;
	private String bbsTitle;
	private String bbsContent;
	private String bbsWriter;
	private Date bbsRegDate;
	private int bbsCount;
	
	private List<AttachVo> attachList; //하나의 게시판에 속한 첨부파일을 받아오기 위한 변수
		
	public List<AttachVo> getAttachList() {
		return attachList;
	}
	public void setAttachList(List<AttachVo> attachList) {
		this.attachList = attachList;
	}
	//멀티파트 폼 데이터에 포함된 파일을 받기 위한 변수는 MultipartFile 타입으로 설정
	//이름이 같은 다수의 요청 파라미터 값을 받기 위해서는 배열 또는 List 타입으로 설정
	private List<MultipartFile> bbsFile;
	
	
	public List<MultipartFile> getBbsFile() {
		return bbsFile;
	}
	public void setBbsFile(List<MultipartFile> bbsFile) {
		this.bbsFile = bbsFile;
	}
	//CTRL+SHIFT+Y 소문자화
	public int getBbsNo() {
		return bbsNo;
	}
	public void setBbsNo(int bbsNo) {
		this.bbsNo = bbsNo;
	}
	public String getBbsTitle() {
		return bbsTitle;
	}
	public void setBbsTitle(String bbsTitle) {
		this.bbsTitle = bbsTitle;
	}
	public String getBbsContent() {
		return bbsContent;
	}
	public void setBbsContent(String bbsContent) {
		this.bbsContent = bbsContent;
	}
	public String getBbsWriter() {
		return bbsWriter;
	}
	public void setBbsWriter(String bbsWriter) {
		this.bbsWriter = bbsWriter;
	}
	public Date getBbsRegDate() {
		return bbsRegDate;
	}
	public void setBbsRegDate(Date bbsRegDate) {
		this.bbsRegDate = bbsRegDate;
	}
	public int getBbsCount() {
		return bbsCount;
	}
	public void setBbsCount(int bbsCount) {
		this.bbsCount = bbsCount;
	}
}
