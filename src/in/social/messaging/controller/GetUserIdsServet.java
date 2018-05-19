package in.social.messaging.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.social.messaging.model.Response;
import in.social.messaging.model.User;
import in.social.messaging.service.UserDao;
import in.social.messaging.util.JSONUtil;

public class GetUserIdsServet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public GetUserIdsServet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		System.out.println("....................... Find all users Service .......................");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
		if(bufferedReader != null){
			json = bufferedReader.readLine();
		}
		System.out.println("Authorization request JSON : " + json);
		
    	User user = JSONUtil.mapper.readValue(json, User.class);
		Response loginResponse = new UserDao().findAllUsers(user);
		response.setContentType("application/json");
		JSONUtil.mapper.writeValue(response.getOutputStream(), loginResponse);
		
		System.err.println(loginResponse);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
