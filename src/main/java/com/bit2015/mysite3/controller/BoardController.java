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
	
	// 리스트 요청
	@RequestMapping( "" )
	public String list( Model model ) {
		List<BoardVo> list = boardService.listBoard();
		model.addAttribute( "list", list );
		
		return "/board/list";
	}
	
	// 글쓰기 폼 요청
	@RequestMapping( "/write" )
	public String write( HttpSession session ) {
		// 로그인 사용자 체크
		UserVo authUser = (UserVo)session.getAttribute( "authUser" );
		if( authUser == null ) {
			return "redirect:/board";
		}
		
		return "/board/write";
	}
	
	// 답글달기 폼 요청
	@RequestMapping( "/reply/{no}" )
	public String reply( HttpSession session, @PathVariable( "no" ) Long no, Model model ) {
		UserVo authUser = (UserVo)session.getAttribute( "authUser" );
		
		// 로그인 사용자 체크
		if( authUser == null ) {
			return "redirect:/board";
		}
		
		BoardVo vo = boardService.viewBoard( no );
		model.addAttribute( "vo", vo );
		
		return "/board/write";
	}
	
	
	// 글(새글/답글) 등록 요청
	@RequestMapping( "/insert" )
	public String insert( HttpSession session, @ModelAttribute BoardVo vo ) {
		UserVo authUser = (UserVo)session.getAttribute( "authUser" );

		// 로그인 사용자 체크
		if( authUser == null ) {
			return "redirect:/board";
		}
		
		vo.setMemberNo( authUser.getNo() );
		boardService.writeBoard( vo );
		
		return "redirect:/board";
	}
	
	// 글(새글/답글) 수정폼 요청
	@RequestMapping( "/modify/{no}" )
	public String modify( HttpSession session, @PathVariable( "no" ) Long no, Model model ) {
		UserVo authUser = (UserVo)session.getAttribute( "authUser" );
		
		// 로그인 사용자 체크
		if( authUser == null ) {
			return "redirect:/board";
		}
		
		BoardVo vo = boardService.viewBoard( no );
		model.addAttribute( "vo", vo );
		
		return "/board/modify";
	}
	
	// 글(새글/답글) 수정 요청
	@RequestMapping( "/update" )
	public String update( HttpSession session, @ModelAttribute BoardVo vo ) {
		UserVo authUser = (UserVo)session.getAttribute( "authUser" );
		
		// 로그인 사용자 체크
		if( authUser == null ) {
			return "redirect:/board";
		}
		
		vo.setMemberNo( authUser.getNo() );
		boardService.updateBoard( vo );
		
		return "redirect:/board";
	}
	
	// 글(새글/답글) 삭제 요청
	@RequestMapping( "/delete/{no}" )
	public String delete( HttpSession session, @PathVariable( "no" ) Long no ) {
		UserVo authUser = (UserVo)session.getAttribute( "authUser" );

		// 로그인 사용자 체크
		if( authUser == null ) {
			return "redirect:/board";
		}
		
		boardService.deleteBoard( no, authUser.getNo() );
		
		return "redirect:/board";
	}	
	
	// 글(새글/답글) 보기 요청
	@RequestMapping( "/view/{no}" )
	public String view( @PathVariable( "no" ) Long no, Model model ) {
		BoardVo vo = boardService.viewBoard( no );
		model.addAttribute( "vo", vo );
		
		return "/board/view";
	}
}