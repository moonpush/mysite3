package com.bit2015.mysite3.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bit2015.mysite3.service.UserService;
import com.bit2015.mysite3.vo.UserVo;

@Controller
@RequestMapping( "/user" )
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping( "/joinform" )
	public String joinform() {
		return "/user/joinform";
	}
	
	@RequestMapping( "/join" )
	public String join( @ModelAttribute UserVo vo ) {
		userService.join( vo );
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping( "/joinsuccess" )
	public String joinSuccess() {
		return "/user/joinsuccess";
	}
	
	@RequestMapping( "/loginform" )
	public String loginform(){
		return "/user/loginform";
	}
	
	@RequestMapping( "/login" )
	public String login( HttpSession session, @ModelAttribute UserVo vo ){
		UserVo userVo = userService.login(vo);
		if( userVo == null ) {
			return "redirect:/user/loginform?result=error";
		}
		
		//로그인 처리
		session.setAttribute( "authUser", userVo );
		return "redirect:/";
	}
	
	@RequestMapping( "/logout" )
	public String logout( HttpSession session ) {
		// 로그아웃 처리
		session.removeAttribute( "authUser" );
		session.invalidate();
		
		return "redirect:/";
	}
}