package com.exam.myapp.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;



@Mapper //이거 붙은애만 구현체를 만듬. annotation 규칙과 맞추기면 하면 됨. 이러면 daobatis가 필요 없어짐.
public interface MemberDao {

	List<MemberVo> selectMemberList();

	int insertMember(MemberVo vo);

	int deleteMember(String memId);

	MemberVo selectMember(String memId);

	int updateMember(MemberVo vo);

	MemberVo selectLogin(MemberVo vo);

	
	
}