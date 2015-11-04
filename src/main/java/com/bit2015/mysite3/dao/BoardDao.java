package com.bit2015.mysite3.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2015.mysite3.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void update( BoardVo vo ) {
		sqlSession.update( "board.update", vo );
	}
	
	public List<BoardVo> getList() {
		List<BoardVo> list = sqlSession.selectList( "board.selectList" );
		return list;
	}

	public void insert( BoardVo vo ) {
		if( vo.getGroupNo() == null ) {
			// 답글인 경우,
			// sqlSession.update( "board.updateOrderNo", vo );
		}
		
		sqlSession.insert( "board.insert", vo );
	}
	
	public void delete( Long no, Long memberNo ) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put( "no", no );
		map.put( "memberNo", memberNo );
		
		sqlSession.delete( "board.delete", map );
	}
	
	public void updateViewCount( Long no ) {
		sqlSession.update( "board.updateViewCount", no );
	}
	
	public BoardVo get( Long no ) {
		BoardVo vo = sqlSession.selectOne( "board.selectByNo", no );
		return vo;
	}	
}
