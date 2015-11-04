package com.bit2015.mysite3.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2015.mysite3.dao.BoardDao;
import com.bit2015.mysite3.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	public Map<String, Object> listBoard( int startPage, int currentPage ){
		final int PAGE_SIZE = 10;
		final int BLOCK_SIZE = 5;
		
		//list 가져오기
		List<BoardVo> list = boardDao.getList();

		//
		Long totalCount = boardDao.getCount();
				
		/* pager 계산 */
		boolean hasPrev = false;
		boolean hasNext = true;
		
		int totalPage = (int)Math.ceil( totalCount/PAGE_SIZE );
		
		// start page 결정
		
		// endpage 결정
		int endPage = startPage + BLOCK_SIZE;
		
		//
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "list", list );
		map.put( "currentPage", currentPage );
		map.put( "startPage", startPage );
		map.put( "endPage", endPage );
		map.put( "hasPrev", hasPrev );
		map.put( "hasNext", hasNext );
		
		return map;
	}
	
	public void writeBoard( BoardVo vo ){
		boardDao.insert( vo );
	}
	
	public void updateBoard( BoardVo vo ){
		boardDao.update( vo );
	}	

	public void deleteBoard( Long no, Long memberNo ){
		boardDao.delete( no, memberNo );
	}	

	public BoardVo viewBoard( Long no ){
		BoardVo vo = boardDao.get( no );
		boardDao.updateViewCount( no );
		return vo;
	}
}
