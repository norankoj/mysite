<%@page import="sun.net.www.protocol.http.AuthCacheImpl"%>
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
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${requestScope.vo.title}</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${requestScope.vo.content}<br>

							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<a href="/mysite/board?a=list">글목록</a>

					<c:choose>
						<c:when test="${empty authUser}"></c:when>
						<c:when test="${authUser.no==vo.user_no}">
							<a href="/mysite/board?a=modify&no=${requestScope.vo.no}">글수정</a>
						</c:when>

					</c:choose>

				</div>
			</div>
		</div>

<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
</body>
</html>