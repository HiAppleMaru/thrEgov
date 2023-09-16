package com.exam.myapp.bbs;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper //이거 붙은애만 구현체를 만듬. annotation 규칙과 맞추기면 하면 됨. 이러면 daobatis가 필요 없어짐.
public interface BbsDao {

	List<BbsVo> selectBbsList(SearchInfo info);

	int insertBbs(BbsVo vo);

	int deleteBbs(int bbsNo);

	BbsVo selectBbs(int bbsNo);

	int updateBbs(BbsVo vo);

	int selectBbsCount(SearchInfo info);

}