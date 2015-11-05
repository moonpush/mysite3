package com.bit2015.mysite3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2015.mysite3.dao.GuestbookDao;
import com.bit2015.mysite3.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookDao guestbookDao;
	
	public GuestbookVo writeMessage( GuestbookVo vo ) {
		return guestbookDao.insert( vo );
	}
	
	public boolean deleteMessage( GuestbookVo vo ) {
		int count = guestbookDao.delete( vo );
		return count == 1;
	}
	
	public List<GuestbookVo> listMessage() {
		List<GuestbookVo> list = guestbookDao.getList();
		return list;
	}
	
	public List<GuestbookVo> listMessage( Long page ) {
		List<GuestbookVo> list = guestbookDao.getList( page );
		return list;
	}	
}
