<%@page import="com.javaex.vo.UserVO"%>
<%@page import="com.javaex.vo.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	ArrayList<BoardVO> list = (ArrayList<BoardVO>) request.getAttribute("list");
    UserVO authUser = (UserVO)session.getAttribute("authUser");

	list = list == null ? null : list;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/mysite.css" rel="stylesheet"
	type="text/css">
<link href="/mysite/assets/css/board.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">

		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>

		<div id="content">
			<div id="board">
				<form id="search_form" action="/mysite/board?a=search" method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<% 
							for (BoardVO vo : list) {
					%>

					<tr>
						<td><%=vo.getNo() %></td>
						<td><a href="/mysite/board?a=view&no=<%=vo.getNo()%>"><%=vo.getTitle()%></a></td>
						<td><%=vo.getName()%></td>
						<td>3</td>
						<td><%=vo.getReg_date()%></td>
						<%
			             if(authUser!=null){
			             %>
						<td><a href="" class="del">삭제</a></td>
						<%
			             }
						%>
					</tr>
					<%
						}
						
					%>
				</table>
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li><a href="">2</a></li>
						<li class="selected">3</li>
						<li><a href="">4</a></li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>
				<div class="bottom">
				
			           <%
			    if(authUser!=null){
			
			%>
					<a href="/mysite/board?a=write" id="new-book">글쓰기</a>
						
			<%
			    }
			
			%>	
				</div>
			</div>
		</div>

		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>

	</div>
</body>
</html>