package com.javaex.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDAO;
import com.javaex.util.Webutil;
import com.javaex.vo.UserVO;


@WebServlet("/user")
public class userController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String actionName = request.getParameter("a");
		if("joinform".equals(actionName)) { //회원가입 버튼 누르면, 회원가입 폼으로 가기 
			Webutil.forword(request, response, "/WEB-INF/views/user/joinform.jsp");
		} else if("join".equals(actionName)) { //회원가입 정보 저장 
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");
			
			UserVO vo = new UserVO(name,email,password,gender);
			System.out.println(vo.toString());
			
			UserDAO userdao = new UserDAO();
			userdao.insert(vo);
			Webutil.forword(request, response, "/WEB-INF/views/user/joinsuccess.jsp");
			
		} else if("loginform".equals(actionName)) {
			Webutil.forword(request, response, "WEB-INF/views/user/loginform.jsp");
		} else if("login".equals(actionName)) {
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			UserDAO userDao = new UserDAO();
			UserVO uservo = userDao.getUser(email, password);
			
			  if(uservo==null) {
				//로그인실패
				  System.out.println("로그인실패");
				  Webutil.redirect(request, response, "/mysite/user?a=loginform&result=fail");
			  } else {
				//로그인성공
				  System.out.println("로그인성공");
				//주소값 갖고있는 애한테  어디야 세션내놔!
				  HttpSession session = request.getSession();
			      session.setAttribute("authUser", uservo);
			    
			      Webutil.redirect(request, response, "/mysite/main");
			    
			  }
			
		}else if("logout".equals(actionName)) {
			  HttpSession session = request.getSession();
			  session.removeAttribute("authUser");
			  //완전 삭제 
			  session.invalidate();
			  
			  Webutil.redirect(request, response, "/mysite/main");
			
		} else if("modify".equals(actionName)) {
			
			HttpSession session = request.getSession();
			UserVO authUser = (UserVO)session.getAttribute("authUser");
			UserDAO dao = new UserDAO();
     		UserVO vo = dao.Select(authUser.getNo());
     		request.setAttribute("vo", vo);
     		Webutil.forword(request, response, "/WEB-INF/views/user/modifyform.jsp");
			
			
		} else if("update".equals(actionName)) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");
			String email = request.getParameter("email");
			
			UserVO vo = new UserVO();
			vo.setName(name);
			vo.setPassword(password);
			vo.setGender(gender);
			vo.setEmail(email);
			System.out.println(vo.getName());
			UserDAO dao = new UserDAO();
			 dao.update(vo);
			 HttpSession session = request.getSession();
		     UserVO authUser = (UserVO)session.getAttribute("authUser");
		     authUser.setName(vo.getName());
			 
			Webutil.redirect(request, response, "/mysite/main");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
