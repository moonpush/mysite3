package com.bit2015.mysite3.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bit2015.mysite3.service.BoardService;
import com.bit2015.mysite3.vo.BoardVo;
import com.bit2015.mysite3.vo.UserVo;

@Controller
@RequestMapping( "/board" )
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping( "" )
	public String list( Model model ) {
		List<BoardVo> list = boardService.listBoard();
		model.addAttribute( "list", list );
		return "/board/list";
	}
	
	@RequestMapping( "/writeform" )
	public String list() {
		return "/board/writeform";
	}
	
	@RequestMapping( "/insert" )
	public String insert( HttpSession session, @ModelAttribute BoardVo vo ) {
		UserVo authUser = (UserVo)session.getAttribute( "authUser" );
		if( authUser == null ) {
			return "redirect:/board";
		}
		vo.setMemberNo( authUser.getNo() );
		
		boardService.writeBoard( vo );
		return "redirect:/board";
	}
	
	@RequestMapping( "/view/{no}" )
	public String view( @PathVariable( "no" ) Long no, Model model ) {
		BoardVo vo = boardService.viewBoard( no );
		model.addAttribute( "vo", vo );
		return "/board/view";
	}	
}
