package com.exam.myapp.member;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository; 

//CTRL+SHIFT+O 자동 임포트 정리.
//@Repository 
public class MemberDaoBatis implements MemberDao {
//	@Autowired
//	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private SqlSession session;


	@Override
	public List<MemberVo> selectMemberList() {
		List<MemberVo> list = null; 
//		try (SqlSession session = sqlSessionFactory.openSession()) {//라이브러리에 없어서 오류..Form.xml에다가 넣어야함.
			//실행할 SQL문과 동일한 이름의 메서드를 사용하여 SQL문 실행
			//SELECT결과가 1행인 경우 selectOne, 2행이상인 경우 selectList 메서드 사용 
			//첫번째 인자로 실행할 SQL문의 고유한 이름을 전달
			//두번째 인자로 SQL문 실행시 필요한 데이터를 담은 객체를 전달
			return session.selectList("com.exam.myapp.member.MemberDao.selectMemberList");
//			}
//		return list;
	}

	@Override
	public int insertMember(MemberVo vo) {
//		int num = 0;
//		try (SqlSession session = sqlSessionFactory.openSession()) {
			return session.insert("com.exam.myapp.member.MemberDao.insertMember", vo);
//			session.commit(); //Insert,Update,Delete 후에는 commit 필요
//		}
//		return num;
	}
	//삭제버튼을 클릭하면, 
	//삭제가 되도록 MemberDaoBatis 클래스와 MemberMapper.xml 파일을 변경하세요.
	
	@Override
	public int deleteMember(String memId) {
//		int num = 0;
//		try (SqlSession session = sqlSessionFactory.openSession()) {
			return session.delete("com.exam.myapp.member.MemberDao.deleteMember", memId);
//			session.commit();
//		}
//		return num;
	}

	@Override
	public MemberVo selectMember(String memId) {
//		MemberVo vo = null;
//		try (SqlSession session = sqlSessionFactory.openSession()) {
			return session.selectOne("com.exam.myapp.member.MemberDao.selectMember", memId);
//			}
//		return vo;
	}

	@Override
	public int updateMember(MemberVo vo) {
//		int num = 0;
//		try (SqlSession session = sqlSessionFactory.openSession()) {
			return session.update("com.exam.myapp.member.MemberDao.updateMember", vo);
//			session.commit(); //Insert,Update,Delete 후에는 commit 필요
//		}
//		return num;
		
		
	}

	@Override
	public MemberVo selectLogin(MemberVo mvo) {
//		MemberVo vo = null;
//		try (SqlSession session = sqlSessionFactory.openSession()) {
			return session.selectOne("com.exam.myapp.member.MemberDao.selectLogin", mvo);
//			}
//		return vo;
	}
	
	
	
}
