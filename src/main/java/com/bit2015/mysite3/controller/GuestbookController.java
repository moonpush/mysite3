package com.bit2015.mysite3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bit2015.mysite3.service.GuestbookService;
import com.bit2015.mysite3.vo.GuestbookVo;

@Controller
@RequestMapping( "/guestbook" )
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping( "" )
	public String list( Model model ) {
		List<GuestbookVo> list = guestbookService.listMessage( 1L );
		model.addAttribute( "list", list );
		return "/guestbook/list";
	}
	
	@RequestMapping( "/deleteform/{no}" )
	public String list( @PathVariable( "no" ) Long no, Model model ) {
		model.addAttribute( "no", no );
		return "/guestbook/deleteform";
	}

	@RequestMapping( "/insert" )
	public String insert( @ModelAttribute GuestbookVo vo ) {
		guestbookService.writeMessage(vo);
		return "redirect:/guestbook";
	}	

	@RequestMapping( "/delete" )
	public String list( @ModelAttribute GuestbookVo vo, Model model ) {
		guestbookService.deleteMessage(vo);
		return "redirect:/guestbook";
	}	
}