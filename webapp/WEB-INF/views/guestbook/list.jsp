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
<link href="/mysite3/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/mysite3/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
$(function() {
	$( "#write-form" ).submit( function(){
		if( $( "input[name='name']" ).val() == "" ) {
			return false;
		}
		if( $( "input[name='password']" ).val() == "" ) {
			return false;
		}
		if( $( "#textarea-content" ).val() == "" ) {
			return false;
		}
		
		return true;
	});	
});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<div id="content">
			<div id="guestbook">
				<form id="write-form" action="/mysite3/guestbook/insert" method="post">
					<table>
						<tr>
							<td>이름</td>
							<td>
								<input type="text" name="name">
							</td>
							<td>비밀번호</td>
							<td>
								<input type="password" name="password">
							</td>
						</tr>
						<tr>
							<td colspan=4>
								<textarea name="message" id="textarea-content"></textarea>
							</td>
						</tr>
						<tr>
							<td colspan=4 align=right>
								<input type="submit" VALUE=" 확인 ">
							</td>
						</tr>
					</table>
				</form>
				<ul>
					<c:set var='count' value='${fn:length(list) }'/>
					<c:forEach items='${list }' var='vo' varStatus='status'>			
						<li>
							<table>
								<tr>
									<td>[${count-status.index }]</td>
									<td>${vo.name }</td>
									<td>${vo.regDate }</td>
									<td><a href="/mysite3/guestbook/deleteform/${vo.no }">삭제</a></td>
								</tr>
								<tr>
									<td colspan=4>
									${fn:replace( vo.message, newLine, '<br>' ) }
									</td>
								</tr>
							</table>
							<br>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp"></c:import>
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
	</div>
</body>
</html>