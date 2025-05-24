package com.assignment2.api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assignment2.dao.UserDAO;
import com.assignment2.entity.User;
import com.assignment2.io.RestIO;
import com.j4.dto.UserDTO;

/**
 * Servlet implementation class ApiUser
 */
@WebServlet("/api-user")
public class ApiUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApiUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			UserDTO userDto = RestIO.readObject(req, UserDTO.class);
			User user = UserDAO.findById(userDto.getUserId());
			System.out.println(user+ "======================================================================================");
			user.setActive(!userDto.getStatus());
			int rs = UserDAO.update(user);
			if(rs == 1) {
				RestIO.writeJson(resp,"{\"status\":\"ok\", \"message\":\"success\"}");
			}else {
				RestIO.writeJson(resp,"{\"status\":\"fail\", \"message\":\"success\"}");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			RestIO.writeJson(resp,"{\"status\":\"fail\", \"message\":\"success\"}");
		}
		
	}

}
