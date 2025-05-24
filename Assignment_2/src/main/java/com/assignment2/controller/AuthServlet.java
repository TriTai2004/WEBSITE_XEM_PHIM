package com.assignment2.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.assignment2.dao.UserDAO;
import com.assignment2.entity.User;
import com.assignment2.util.Mail;

/**
 * Servlet implementation class AuthServlet
 */
@WebServlet({"/dang-nhap", "/dang-ky", "/quen-mat-khau"})
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		if(uri.contains("dang-ky")) {
			
			request.getRequestDispatcher("/views/auth/register.jsp").forward(request, response);
		}else if(uri.contains("quen-mat-khau")) {
			request.getRequestDispatcher("/views/auth/forget.jsp").forward(request, response);
		}else {
			request.getSession().removeAttribute("user");
			request.getRequestDispatcher("/views/auth/login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		String url = (String) request.getSession().getAttribute("securityUri");
		if(uri.contains("dang-ky")) {
			User user = new User();
			try {
				BeanUtils.populate(user, request.getParameterMap());
				user.setAdmin(false);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			if(user != null) {
				user.setActive(true);
				int add = UserDAO.insert(user);
				if(add == 1) {
					response.sendRedirect(request.getContextPath() + "/dang-nhap");
					return;
				}
			}
			request.getRequestDispatcher("/views/auth/register.jsp").forward(request, response);
		}else if(uri.contains("quen-mat-khau")) {
			String email = request.getParameter("email");
			String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			User user = UserDAO.findByEmail(email);
			if(user == null) {
				request.setAttribute("message", "Email không tồn tại!");
				request.getRequestDispatcher("/views/auth/forget.jsp").forward(request, response);
				return;
			}else {
		        StringBuilder c = new StringBuilder();
		        Random random = new Random();
		        for (int i = 0; i < 6; i++) {
		        	c.append(chars.charAt(random.nextInt(chars.length())));
		        }	
		        Mail.send(email, "Mã xác nhận", "Mã xác nhận của bạn là: "+c);
		        user.setPassword(c.toString());
		        UserDAO.update(user);
				request.setAttribute("pass", "Mật khẩu mới đã được gửi tới email của bạn!");
				request.getRequestDispatcher("/views/auth/forget.jsp").forward(request, response);
			}

			
		}else {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			List<User> users = UserDAO.findByUser(email, password);
			if(users.size() != 0) {
				User user = users.get(0);
				if(!user.getActive()) {
					request.setAttribute("message", "Tài khoản đã ngừng hoạt động!");
					request.getRequestDispatcher("/views/auth/login.jsp").forward(request, response);
					return;
				}
				user.setPassword("*****");
				request.getSession().setAttribute("user", user);
				response.sendRedirect(url);
			}else {
				request.setAttribute("message", "Tài khoản hoặc mật khẩu không đúng!");
				request.getRequestDispatcher("/views/auth/login.jsp").forward(request, response);
			}
					
		}
	}

}
