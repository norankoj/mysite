package com.javaex.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDAO;
import com.javaex.util.Webutil;
import com.javaex.vo.GuestbookVO;


@WebServlet("/guest")
public class guestbookcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("guest");
		String actionName = request.getParameter("a");

		if ("insert".equals(actionName)) {
			String name = request.getParameter("name");
			String pw = request.getParameter("pass");
			String content = request.getParameter("content");

			GuestbookVO vo = new GuestbookVO(name, pw, content);
			GuestbookDAO dao = new GuestbookDAO();
			dao.insert(vo);

			Webutil.redirect(request, response, "/mysite/guest");

		} else if ("deleteform".equals(actionName)) {
		
			//int no =Integer.parseInt(request.getParameter("no"));
			//나는 이제까지 꺼내서 다시 집어넣는지 알았는데... 그게 아니라 
			//포워드에 있는 request객체에 넣어있어서 그냥 그대로 가지고 가면 됩니다.!!! 
			
			Webutil.forword(request, response, "WEB-INF/views/guestbook/deleteform.jsp");

		} else if ("delete".equals(actionName)) {
			String pw = request.getParameter("password");
			System.out.println(request.getParameter("no"));
			int no = Integer.parseInt(request.getParameter("no"));
			GuestbookVO vo = new GuestbookVO();
			vo.setPw(pw);
			vo.setNo(no);
			GuestbookDAO dao = new GuestbookDAO();
			dao.delete(vo);
			
			Webutil.redirect(request, response, "/mysite/guest");
	
		} else {

			GuestbookDAO dao = new GuestbookDAO();
			ArrayList<GuestbookVO> list = dao.Select();
			// System.out.println(list);
			request.setAttribute("list", list);
			Webutil.forword(request, response, "WEB-INF/views/guestbook/list.jsp");
			
		}

	
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
