package com.exam.member;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.exam.comm.MyBatisUtils;


public class MemberDaoBatis implements MemberDao {
	private MemberDaoBatis() {}
	private static MemberDao memberDao = new MemberDaoBatis();
	public static MemberDao getInstance() {
		return memberDao;
	}
	private SqlSessionFactory sqlSessionFactory = MyBatisUtils.getSqlSessionFactory(); //SqlSessionFactory 전체적으로 하나만 만들어야지 테이블마다 만들면 안됨.
	
	
	@Override
	public List<MemberVo> selectMemberList() {
		List<MemberVo> list = null; //new ArrayList<MemberVo>(); 이렇게 초기값을 줘도 됨. 
		//지금은 단순히 명령어를 내리는것이지 데이터값도 같이 주는게 아니기 때문..
		try (SqlSession session = sqlSessionFactory.openSession()) {
			//실행할 SQL문과 동일한 이름의 메서드를 사용하여 SQL문 실행
			//SELECT결과가 1행인 경우 selectOne, 2행이상인 경우 selectList 메서드 사용 
			//첫번째 인자로 실행할 SQL문의 고유한 이름을 전달
			//두번째 인자로 SQL문 실행시 필요한 데이터를 담은 객체를 전달
			list = session.selectList("com.exam.member.MemberDao.selectMemberList");
			}
		return list;
	}

	@Override
	public int insertMember(MemberVo vo) {
		int num = 0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			num = session.insert("com.exam.member.MemberDao.insertMember", vo);
			session.commit(); //Insert,Update,Delete 후에는 commit 필요
		}
		return num;
	}
	//삭제버튼을 클릭하면, 
	//삭제가 되도록 MemberDaoBatis 클래스와 MemberMapper.xml 파일을 변경하세요.
	
	@Override
	public int deleteMember(String memId) {
		int num = 0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			num = session.delete("com.exam.member.MemberDao.deleteMember", memId);
			session.commit();
		}
		return num;
	}

	@Override
	public MemberVo selectMember(String memId) {
		MemberVo vo = null;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			vo = session.selectOne("com.exam.member.MemberDao.selectMember", memId);
			}
		return vo;
	}

	@Override
	public int updateMember(MemberVo vo) {
		int num = 0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			num = session.update("com.exam.member.MemberDao.updateMember", vo);
			session.commit(); //Insert,Update,Delete 후에는 commit 필요
		}
		return num;
		
		
	}

	@Override
	public MemberVo selectLogin(MemberVo mvo) {
		MemberVo vo = null;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			vo = session.selectOne("com.exam.member.MemberDao.selectLogin", mvo);
			}
		return vo;
	}
	
	
	
}
