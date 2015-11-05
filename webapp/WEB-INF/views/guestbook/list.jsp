<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute( "newLine", "\n" ); %>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="/mysite3/assets/css/guestbook.css" type="text/css">
<link rel="stylesheet" href="/mysite3/assets/css/jBox.css" type="text/css">
<script type="text/javascript" src="/mysite3/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript" src="/mysite3/assets/js/jBox.js"></script>
<script>
var jbox;
var pageNumber = 2;
var fetchList = function() {
	$.ajax( {
		url : "/mysite3/api/guestbook/list",
		type: "get",
		dataType: "json",
		data: "page=" + pageNumber++,
		contentType: 'application/json',
		success: function( response ){
			if( response.result == "fail" ) {				
				console.error( response.message );
				return;
			}
			//redering
			$.each( response.data, function(index, data){
				insertMessage( data, false );
			});
		},
		error: function( jqXHR, status, e ){
			console.error( status + " : " + e );
		}
	});	
}

var insertMessage = function( data, isHead ) {
	// template EJS Library 로 교체할 것
	var html =
		"<table id='table-" + data.no + "'><tr><td>" + data.name + 
		"</td><td>" + data.regDate + 
		"</td><td><a href='' class='delete-guestbook' data-no='" + data.no + "'>삭제</a></td></tr><tr><td colspan='3'>" + 
		data.message.replace( /\n/g, "<br>" ).replace( /\r\n/g, "<br>" ) + 
		"</td></tr></table>";
	////////////////////////////////
	
	var $listDiv = $( "#list" );
	if( isHead ) {
		$listDiv.prepend( html );
	} else {
		$listDiv.append( html );
	}
}


$(function() {
	//다음 버튼 이벤트 매핑
	$( "#btn-next" ).click( function() {
		fetchList();
	});
	
	// 게시물 추가 확인 버튼 이벤트 매핑
	$("#btn-add").click( function() {
		//이름, 비밀번호, content 가져오기
		var name = $("#guestbook-name").val();
		var password = $( "#guestbook-password" ).val();
		var message = $( "#guestbook-message" ).val();
		
		//ajax
		$.ajax( {
			url : "/mysite3/api/guestbook/insert",
			type: "post",
			data: "name=" + name + "&password=" + password + "&message=" + message,
			dataType: "json",
			/* contentType: 'application/json', post 방식에서는 뺀다.*/
			success: function( response ){
				if( response.result == "fail" ) {				
					console.error( response.message );
					return;
				}
				console.log( response );			
				insertMessage( response.data, true );
			},
			error: function( jqXHR, status, e ){
				console.error( status + " : " + e );
			}
		});
	});
	
	//삭제 버튼 이벤트 매핑(Live 이벤트)
	$( document ).on( "click", ".delete-guestbook", function(){
		event.preventDefault();
		
		var $a = $( this );
		var no = $a.attr( "data-no" );
		
		jbox = new jBox('Modal', {
		    attach: $('#myModal'),
		    title: '비밀번호 입력',
		    content: $('#form-password')
		});
		
		jbox.open();
		
		//폼 리셋
		$( "#delete-no" ).val( no );
		$( "#delete-password" ).val( "" );
	});
	
	// 팝업폼의 삭제 버튼 이벤트 매핑
	$( "#form-password input[type='button']" ).click( function() {
		//팝업박스 닫기
		jbox.close();
		
		// no, password값을 받아온다
		var no = $( "#delete-no" ).val();
		var password = $( "#delete-password" ).val();
		
		console.log( no + ":" + password );
		
		//ajax 통신
		$.ajax( {
			url : "/mysite3/api/guestbook/delete",
			type: "post",
			dataType: "json",
			data: "no=" + no + "&password=" + password,
			/* contentType: 'application/json', post 방식에서는 뺀다.*/
			success: function( response ){
				if( response.result == "fail" ) {				
					console.error( response.message );
					return;
				}
				
				if( response.data == true ) {
					$( "#table-" + no ).remove();
				} else {
					alert( "비밀번호가 틀립니다." );
				}
				console.log( response );
			},
			error: function( jqXHR, status, e ){
				console.error( status + " : " + e );
			}
		});			
	});	
});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<div id="content">
			<div id="guestbook">
				<form>
					<input type="hidden" name="a" value="insert">
					<table>
						<tr>
							<td>이름</td>
							<td><input type="text" name="name" id="guestbook-name"></td>
							<td>비밀번호</td>
							<td><input type="password" name="pass" id="guestbook-password"></td>
						</tr>
						<tr>
							<td colspan=4>
								<textarea name="message" id="guestbook-message"></textarea>
							</td>
						</tr>
						<tr>
							<td colspan=4 align=right>
								<input type="button" id="btn-add" VALUE=" 확인 ">
							</td>
						</tr>
					</table>
				</form>
				<div id="list">
					<c:forEach items='${list }' var='vo'>			
						<table id="table-${vo.no }">
							<tr>
								<td>${vo.name }</td>
								<td>${vo.regDate }</td>
								<td><a href="" class="delete-guestbook" data-no="${vo.no }">삭제</a></td>
							</tr>
							<tr>
								<td colspan=3>
								${fn:replace( vo.message, newLine, '<br>' ) }
								</td>
							</tr>
						</table>
					</c:forEach>
				</div>
				<div id="buttons">
					<button id="btn-next">다음 가져오기</button>
				</div>					
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp"></c:import>
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
	</div>
	<div id="form-password" style="display:none">
		<p>
			작성시 비밀번호를 입력해 주세요.
		</p>
		<form>
			<input type="hidden" name="no" id="delete-no"> 
			<input type="password" name="password" id="delete-password">
			<input type="button" value="삭제">
		</form>
	</div>	
</body>
</html>