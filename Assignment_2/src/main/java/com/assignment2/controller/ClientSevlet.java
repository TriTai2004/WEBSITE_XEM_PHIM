package com.assignment2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assignment2.dao.LikeDAO;
import com.assignment2.dao.UserDAO;
import com.assignment2.dao.VideoDAO;
import com.assignment2.entity.Like;
import com.assignment2.entity.User;
import com.assignment2.entity.Video;

/**
 * Servlet implementation class ClientSevlet
 */
@WebServlet({"/trang-chu", "/video-chi-tiet", "/video", "/lien-he", "/tai-khoan", "/doi-mat-khau", "/yeu-thich"})
public class ClientSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /** 
     * @see HttpServlet#HttpServlet()
     */
    public ClientSevlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		request.setAttribute("uri", uri);
		if(uri.contains("video-chi-tiet")) { 
			String id = request.getParameter("id");
			List<Video> list = VideoDAO.findAll();
			Video video = VideoDAO.findById(Integer.parseInt(id));
			video.setViews(video.getViews()+1);
			VideoDAO.update(video);
			Like like = null;
			
			try {
				User user = (User) request.getSession().getAttribute("user");
				like = LikeDAO.findByLike(user == null?new User(0, null, null,null, null, null, null, null):user, video);
			} catch (Exception e) {
				like = null;
			}
			request.setAttribute("list", list);
			request.setAttribute("like", like);
			request.setAttribute("video", video);
			request.setAttribute("page", "/views/client/videoDetail/videoDetail.jsp");
		}else if(uri.contains("video")) {
			String url = request.getRequestURI();
			String search = request.getParameter("search");
			String numberPage = request.getParameter("page");
			int size = 9;
			int page = 1;
			if(numberPage != null) {
				page = Integer.parseInt(numberPage);
			} 
			double sizeList = VideoDAO.findAll().size();
			int pagination = (int) Math.ceil(sizeList/size);
			List<Video> list = null;
			if( search != null) {
				double sizeSearch = VideoDAO.findVideoByTitile(search).size();
				pagination = (int) Math.ceil(sizeSearch/size);
				list  = VideoDAO.pagination(page, size, search);
				request.setAttribute("search", search);
			}else {
				list = VideoDAO.pagination(page, size, "");
				
			}
			
			request.setAttribute("numberPage", page);
			request.setAttribute("url", url);
			request.setAttribute("pagination", pagination);
			request.setAttribute("list", list);
			request.setAttribute("page", "/views/client/video/video.jsp");
		}else if(uri.contains("yeu-thich")) {
			
			User user = (User) request.getSession().getAttribute("user");
			String url = request.getRequestURI();
			String search = request.getParameter("search");
			String numberPage = request.getParameter("page");
			int size = 9;
			int page = 1;
			if(numberPage != null) {
				page = Integer.parseInt(numberPage);
			} 
			double sizeList = LikeDAO.findByUserId(user).size();
			int pagani = (int) Math.ceil(sizeList/size);
			List<Like> list = null;
			if( search != null && !search.equals("")) {
				double sizeSearch = LikeDAO.findByUserAndTitle(user, search).size();
				pagani = (int) Math.ceil(sizeSearch/size);
				list  = LikeDAO.pagination(size, page, user.getId(), search);
				request.setAttribute("search", search);
			}else {				
				list = LikeDAO.pagination(size, page, user.getId(), "");
				sizeList = LikeDAO.findByUserId(user).size();
				
				pagani = (int) Math.ceil(sizeList/size);
			}
			System.out.println(sizeList+"------------------------===================");
			System.out.println(pagani+"----------------------------------------------------");
			request.setAttribute("numberPage", page);
			request.setAttribute("url", url);
			request.setAttribute("pagani", pagani);
			request.setAttribute("list", list);
			
			
			
			
			request.setAttribute("page", "/views/client/video/like.jsp");
		}else if(uri.contains("tai-khoan")) {
			User user = (User) request.getSession().getAttribute("user");
			request.setAttribute("user", user);
			
			request.setAttribute("pageAccount", "/views/client/account/account.jsp");
			request.setAttribute("page", "/views/client/account/form.jsp");
		}else if(uri.contains("doi-mat-khau")) {
			User user = (User) request.getSession().getAttribute("user");
			request.setAttribute("user", user);
			
			request.setAttribute("pageAccount", "/views/client/account/change.jsp");
			request.setAttribute("page", "/views/client/account/form.jsp");
		}else if(uri.contains("lien-he")) {
			request.setAttribute("page", "/views/client/contact/contact.jsp");
		}else {
			List<Video> outstanding = VideoDAO.findVideoByView();
			List<Video> likes = VideoDAO.findVideoByLike();
			List<Video> news = VideoDAO.findVideoByNew();		
			request.setAttribute("outstanding", outstanding);
			request.setAttribute("likes", likes);
			request.setAttribute("news", news);
			request.setAttribute("page", "/views/client/home/home.jsp");	
		}
		
		request.getRequestDispatcher("/views/client/form.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		if(uri.contains("tai-khoan")) {
			String id = request.getParameter("id");
			String fullname = request.getParameter("fullName");
			User user = UserDAO.findById(Integer.parseInt(id));
			user.setFullName(fullname.trim());
			
			response.sendRedirect(request.getContextPath() + "/tai-khoan");
		}else if(uri.contains("doi-mat-khau")) {
			String id = request.getParameter("id");
			String passOld = request.getParameter("passwordOld");
			String passNew = request.getParameter("passwordNew");
			String confirmPass = request.getParameter("confirmPass");
			String email = request.getParameter("email");
			boolean error = false;
			System.out.println(passOld);
			List<User> users = UserDAO.findByUser(email, passOld);
			System.out.println(users);
			System.out.println(email + "=====================================");
			if (users.size() == 0) {
			    request.setAttribute("messagePassOld", "Mật khẩu không chính xác!");
			    error = true;
			} else if (!passNew.trim().equals(confirmPass.trim())) {
			    request.setAttribute("messageConfirm", "Xác nhận mật khẩu không chính xác!");
			    error = true;	
			}
			if(!error) {
				User user = users.get(0);
				user.setPassword(passNew);
				UserDAO.insert(user);
			}
				
			request.setAttribute("pageAccount", "/views/client/account/change.jsp");
			request.setAttribute("page", "/views/client/account/form.jsp");
			request.getRequestDispatcher("/views/client/form.jsp").forward(request, response);
		}
	}

}
