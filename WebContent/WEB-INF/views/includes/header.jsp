<%@page import="com.javaex.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
//이건  uservo에 주소값이 들어간거야!
UserVO authUser = (UserVO)session.getAttribute("authUser");


%>


<div id="header">
			<h1>MySite</h1>
			<ul>
			<%
			    if(authUser==null){
			
			%>
				<!-- 로그인 전 -->
				<li><a href="/mysite/user?a=loginform">로그인</a></li>
				<li><a href="/mysite/user?a=joinform">회원가입</a></li>
			<%
			    }else {
			%>
				<!-- 로그인 후 -->
				 
				<li><a href="/mysite/user?a=modify">회원정보수정</a></li>
				<li><a href="/mysite/user?a=logout">로그아웃</a></li> 
				<li> <%=authUser.getName() %>님 안녕하세요✿✿✿  </li>
				
			<%
			    }
			
			%>	
			
			</ul>
		</div> <!-- /header -->
		