package com.bit2015.mysite3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2015.mysite3.dao.BoardDao;
import com.bit2015.mysite3.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	public List<BoardVo> listBoard(){
		List<BoardVo> list = boardDao.getList();
		return list;
	}
	
	public void writeBoard( BoardVo vo ){
		boardDao.insert( vo );
	}
	
	public BoardVo viewBoard( Long no ){
		BoardVo vo = boardDao.get( no );
		return vo;
	}
}
