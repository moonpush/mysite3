<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite3/assets/css/user.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/mysite3/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
/*
$(function(){
	$( "#join-form" ).submit(function(){
		//이름 체크
		if( $( "#name" ).val() == "" ) {
			alert( "이름이 비어 있습니다." );
			$( "#name" ).focus();
			return false;
		}
		
		//이메일 중복체크
		if( $( "#image-checked" ).is( ":visible" ) == false ) {
			alert( "이메일 중복 체크를 해야 합니다." );
			$( "#email" ).focus();
			return false;
		}
		
		//비밀번호
		if( $( "#password" ).val() == "" ) {
			alert( "비밀번호가 비어 있습니다." );
			$( "#password" ).focus();
			return false;
		}
		
		//약관동의
		if( $( "#agree-prov" ).is( ":checked" ) == false ) {
			alert( "약관 동의를 해 주세요." );
			return false;
		}
		
		return true;	
	});
	
	$( "#email" ).change( function(){
		$( "#image-checked" ).hide();
		$( "#btn-checkemail" ).show();	
	});
	
	$( "#btn-checkemail" ).click( function() {
		var email = $( "#email" ).val();
		if( email == "" ) {
			return;
		}
		//ajax 통신
		$.ajax( {
			url : "/mysite3/api/member",
			type: "get",
			dataType: "json",
			data: "a=checkemail&email=" + email,
			contentType: 'application/json',
			success: function( response ){
				console.log( response );
				if( response.result == "fail" ) {
					console.error( response.message );
					return;
				}
				
				if( response.data == false ) {
					alert( "사용중 인 이메일입니다. 다른 이메일을 사용해 주세요." );
					$( "#email" ).focus();
					return;
				}
				
				// 등록 가능한 이메일 처리
				$( "#image-checked" ).show();
				$( "#btn-checkemail" ).hide();
			},
			error: function( jqXHR, status, e ){
				console.log( status + " : " + e );
			}
		});		
	});
});
*/
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post" action="/mysite3/user/modify">
					
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${vo.name }">

					<label class="block-label" for="email">이메일</label>
					<p style="text-align:left">
					${vo.email }
					</p>	
					
					<label class="block-label">패스워드</label>
					<input id="password" name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						<c:choose>
							<c:when test='${vo.gender == "female" }'>
								<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
								<label>남</label> <input type="radio" name="gender" value="male">
							</c:when>
							<c:otherwise>
								<label>여</label> <input type="radio" name="gender" value="female">
								<label>남</label> <input type="radio" name="gender" value="male" checked="checked">
							</c:otherwise>
						</c:choose>
					</fieldset>
					
					<input type="submit" value="수정하기">
					
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp"></c:import>
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
	</div>
</body>
</html>