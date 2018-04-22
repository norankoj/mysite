package com.javaex.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BoardDAO;
import com.javaex.util.Webutil;
import com.javaex.vo.BoardVO;
import com.javaex.vo.UserVO;



@WebServlet("/board")
public class boardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String actionName = request.getParameter("a");
		String page = request.getParameter("page");
		
		if("view".equals(actionName)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			BoardDAO dao = new BoardDAO();
			//업데이트 하나 새로 생성, 히트 수만 올리는 
			BoardVO vo = dao.getcontent(no);
			System.out.println(vo.getHit());
			vo.setHit(vo.getHit()+1);
			dao.updateHit(vo);
			request.setAttribute("vo", vo);
			
			Webutil.forword(request, response, "/WEB-INF/views/board/view.jsp");
			
		}else if("modify".equals(actionName)) {
			int no = Integer.parseInt(request.getParameter("no"));
			System.out.println(no);
			BoardDAO dao = new BoardDAO();
			
			BoardVO vo = dao.getcontent(no);
			request.setAttribute("vo", vo);
			
			Webutil.forword(request, response, "/WEB-INF/views/board/modify.jsp");
			
		}else if("update".equals(actionName)) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int no = Integer.parseInt(request.getParameter("no"));
			
			BoardVO vo = new BoardVO();
			vo.setContent(content);
			vo.setTitle(title);
			vo.setNo(no);
			BoardDAO dao = new BoardDAO();
			dao.update(vo);
			
			Webutil.redirect(request, response, "/mysite/board");
		
		
		}else if("write".equals(actionName)) {
			Webutil.forword(request, response, "/WEB-INF/views/board/write.jsp");
		
		}else if("write2".equals(actionName)) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			HttpSession session = request.getSession();
		    UserVO authUser = (UserVO)session.getAttribute("authUser");
			
		    BoardVO vo = new BoardVO();
			vo.setTitle(title);
			vo.setContent(content); 
			vo.setUser_no(authUser.getNo());
			
			BoardDAO dao = new BoardDAO();
			dao.insert(vo);
			Webutil.redirect(request, response, "/mysite/board");
		} 
		else {
			
			BoardDAO dao = new BoardDAO();
			ArrayList<BoardVO> list = dao.Select();
			//HttpSession session = request.getSession();
		    request.setAttribute("list", list);
			Webutil.forword(request, response, "/WEB-INF/views/board/list.jsp");
			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
