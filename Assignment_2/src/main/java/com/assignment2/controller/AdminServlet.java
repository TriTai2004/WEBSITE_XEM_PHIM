package com.assignment2.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import com.assignment2.dao.UserDAO;
import com.assignment2.dao.VideoDAO;
import com.assignment2.entity.User;
import com.assignment2.entity.Video;


/**
 * Servlet implementation class AdminServlet
 * 
 * Nguyễn Trí Tài
 * Mssv: PC09496
 * 
 */
@MultipartConfig
@WebServlet({"/admin/trang-chu","/admin/video","/admin/xoa","/admin/tai-khoan","/admin/video/edit", "/admin/thong-ke" })
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		if(uri.contains("edit")) {
			
			String id = request.getParameter("id");
			try {
				Video video = VideoDAO.findById(Integer.parseInt(id));
				request.setAttribute("video", video);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			request.setAttribute("page", "/views/admin/video/edit.jsp");
		}else if(uri.contains("tai-khoan")) {
			String search = request.getParameter("search");
			String pageNumber = request.getParameter("page");
			int page = 1;
			int size = 7;
			List<User> users = null;
			double maxSize = UserDAO.findAll().size();
			int pagination = (int) Math.ceil(maxSize/size);
			try {
				page = Integer.parseInt(pageNumber);
			} catch (Exception e) {
				page = 1;
			}
			if(search != null) {
				maxSize = UserDAO.searchUser(search).size();
				users = UserDAO.pagination(page, size, search);
			}else {
				users = UserDAO.pagination(page, size, "");
			}
			pagination = (int) Math.ceil(maxSize/size);
			request.setAttribute("list", users);
			request.setAttribute("pagination", pagination);
			request.setAttribute("pageNumber", page);
			request.setAttribute("search", search);

			request.setAttribute("page", "/views/admin/account/account.jsp");
		}else if(uri.contains("video")) {
			String page = request.getParameter("trang");
			String search = request.getParameter("tim-kiem");
			
			List<Video> list = null;
			int size = 7;
			double numberVideo = 0;
			if(search != null) {
				try {
					list = VideoDAO.findVideosByPage(Integer.parseInt(page), size, search);
				} catch (Exception e) {
					list = VideoDAO.findVideosByPage(1, size, search);
					page="1";
				}
				request.setAttribute("search", search);
			}else {
				try {
					list = VideoDAO.findVideosByPage(Integer.parseInt(page), size, "");
				} catch (Exception e) {
					list = VideoDAO.findVideosByPage(1, size, "");
					page = "1";
					
				}
			}
			numberVideo = VideoDAO.findVideosByTitle(search == null ? "" : search).size();
			int pagination = (int) Math.ceil(numberVideo / size);
			request.setAttribute("trang", page);
			request.setAttribute("list", list);
			request.setAttribute("pagination", pagination);
			request.setAttribute("page", "/views/admin/video/video.jsp");

		}else if(uri.contains("thong-ke")) {
			
			request.setAttribute("page", "/views/admin/statistical/statistical.jsp");
		}else {
			int countVideo = VideoDAO.findAll().size();
			int countVideoIsActive = VideoDAO.findActive(true).size();
			int countVideoNoActive = VideoDAO.findActive(false).size();
			List<Video> list = VideoDAO.findViewHasNumber(3);
			
			request.setAttribute("countVideo", countVideo);
			request.setAttribute("countVideoIsActive", countVideoIsActive);
			request.setAttribute("countVideoNoActive", countVideoNoActive);
			request.setAttribute("list", list);
			
			request.setAttribute("page", "/views/admin/home/home.jsp");
		}
		
		request.setAttribute("uri", uri);
		request.getRequestDispatcher("/views/admin/form.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		if(uri.contains("edit")) {
			String id = request.getParameter("id");
			Video video = new Video();
			try {
				BeanUtils.populate(video, request.getParameterMap());
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}		
			Part img = request.getPart("poster");
			if (img != null && img.getSize() > 0) {
				String fileName = img.getSubmittedFileName();
				String path = "/img/" + fileName;
				String realPath = request.getServletContext().getRealPath("/img"); // Lấy đường dẫn thư mục

				// Tạo thư mục nếu không tồn tại
				Path dirPath = Path.of(realPath);
				if (!Files.exists(dirPath)) {
					try {
						Files.createDirectories(dirPath); // Tạo thư mục
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				// Lưu tệp vào thư mục
				Path filePath = dirPath.resolve(fileName);
				System.out.println(filePath.toString());
				
				try {
					img.write(filePath.toString()); // Lưu hình ảnh vào tệp
				} catch (IOException e) {
					e.printStackTrace();
				}
				video.setPoster(path);
			}
			if(video.getId() == 0) {
				video.setId(null);
				int add = VideoDAO.insert(video);
				response.sendRedirect(request.getContextPath()+ "/admin/video");
			}else {
				Video v = VideoDAO.findById(video.getId());
				v.setActive(video.getActive());
				v.setDescription(video.getDescription());
				v.setTitle(video.getTitle());
				v.setUrlVideo(video.getUrlVideo());
				if(video.getPoster() != null) {
					v.setPoster(video.getPoster());
				}  
				int update = VideoDAO.update(v);
				if(update == 1) {
					response.sendRedirect(request.getContextPath()+ "/admin/video");
				}		
			}
		
			
				
		}else if(uri.contains("tai-khoan")) {
			System.out.println(request.getParameter("id")+"-----------------");
			request.setAttribute("page", "/views/admin/account/account.jsp");
		}else if(uri.contains("video")) {
			
			
		}else if(uri.contains("thong-ke")) {
			
			request.setAttribute("page", "/views/admin/statistical/statistical.jsp");
		}else if(uri.contains("xoa")) {
			String id = request.getParameter("id");
			VideoDAO.delete(Integer.parseInt(id));
			response.sendRedirect(request.getContextPath() + "/admin/video");
		}else {
			int countVideo = VideoDAO.findAll().size();
			int countVideoIsActive = VideoDAO.findActive(true).size();
			int countVideoNoActive = VideoDAO.findActive(false).size();
			List<Video> list = VideoDAO.findViewHasNumber(3);
			
			request.setAttribute("countVideo", countVideo);
			request.setAttribute("countVideoIsActive", countVideoIsActive);
			request.setAttribute("countVideoNoActive", countVideoNoActive);
			request.setAttribute("list", list);
			
			request.setAttribute("page", "/views/admin/home/home.jsp");
		}
	}

	
}
