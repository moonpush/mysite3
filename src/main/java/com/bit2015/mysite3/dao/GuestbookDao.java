package com.bit2015.mysite3.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bit2015.mysite3.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int delete( GuestbookVo vo ) {
		return sqlSession.delete( "guestbook.delete", vo );
	}
	
	public GuestbookVo insert( GuestbookVo vo ) {
		sqlSession.insert( "guestbook.insert", vo );
		GuestbookVo guestbookVo = sqlSession.selectOne( "guestbook.selectbyno", vo.getNo() );
		
		return guestbookVo;
	}
	
	public List<GuestbookVo> getList() {
		List<GuestbookVo> list = sqlSession.selectList( "guestbook.select" );
		return list;
	}
	
	public List<GuestbookVo> getList( Long page ) {
		List<GuestbookVo> list = sqlSession.selectList( "guestbook.selectbypage", page );
		return list;
	}	
}
