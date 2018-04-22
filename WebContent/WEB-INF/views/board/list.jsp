<%@page import="com.javaex.vo.UserVO"%>
<%@page import="com.javaex.vo.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
         <c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>


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

					<c:forEach items="${list}" var="BoardVO" varStatus="status">

						<tr>
							<td>${BoardVO.no}</td>
							<td><a href="/mysite/board?a=view&no=${BoardVO.no}">${BoardVO.title}</a></td>
							<td>${BoardVO.name}</td>
							<td>${BoardVO.hit}</td>
							<td>${BoardVO.reg_date}</td>

							<c:choose>
								<c:when test="${empty authUser}"></c:when>
								<c:when test="${authUser.no==vo.user_no}">
									<td><a href="" class="del">삭제</a>
									<td>
								</c:when>
						</c:choose>
						</tr>
					</c:forEach>

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


					<c:choose>
						<c:when test="${authUser != null}">
							<a href="/mysite/board?a=write" id="new-book">글쓰기</a>

						</c:when>
					</c:choose>

				</div>
			</div>
		</div>

		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
</body>
</html>