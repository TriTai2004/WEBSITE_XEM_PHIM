package com.assignment2.api;

import java.io.IOException;
import java.util.Date;
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
import com.assignment2.io.RestIO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.j4.dto.LikeDTO;


/**
 * Servlet implementation class ApiLike
 */
@WebServlet("/api-like")
public class ApiLike extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApiLike() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			User u = (User) request.getSession().getAttribute("user");
			String rs = "";
			if(u != null) {
				List<Like> list = LikeDAO.findByUserId(u);
				RestIO.writeObject(response, list);
				return;
			}
			rs = "{\"status\":\"fail\", \"message\":\"error\"}";
			RestIO.writeJson(response, rs);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String rs = "";
		String url = (String) request.getSession().getAttribute("securityUri");
		try {
			LikeDTO likeDTO = RestIO.readObject(request, LikeDTO.class);

				User user = UserDAO.findById(likeDTO.getUserId());
				Video video = VideoDAO.findById(likeDTO.getVideoId());
				Like like = new Like();
				like.setLikeDate(new Date());
				like.setUser(user);
				like.setVideo(video);
				LikeDAO.insert(like);
		
			rs = "{\"status\":\"ok\", \"message\":\"success\"}";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			rs = "{\"status\":\"fail\", \"message\":\"error\"}";
		}
		
		RestIO.writeJson(response, rs);
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String rs = "";
		String url = (String) req.getSession().getAttribute("securityUri");
		try {
			LikeDTO likeDTO = RestIO.readObject(req, LikeDTO.class);
			User user = UserDAO.findById(likeDTO.getUserId());
			Video video = VideoDAO.findById(likeDTO.getVideoId());
			Like like = LikeDAO.findByLike(user, video);
			LikeDAO.delete(like);
			rs = "{\"status\":\"ok\", \"url\":"+"\""+url+"\"}";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			rs = "{\"status\":\"fail\", \"message\":\"error\"}";
		}
		RestIO.writeJson(resp, rs);
	}

}
