package in.social.messaging.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.social.messaging.model.Response;
import in.social.messaging.model.User;
import in.social.messaging.util.DBConnectionUtil;

public class UserDao {

	public Response findUserByEmailIdAndPassword(User user) {
		Response response = new Response();
		String query = "select * from user where userId ='" + user.getUserId() +"' and password = '"+user.getPassword()+"'";
		user = parseUser(DBConnectionUtil.getData(query));
		if(null == user) {
			response.setResponseStatus("Fail");
			response.setResponseMessage("Please verify credentials");
			response.setResponseBody(user);
		}else {
			response.setResponseStatus("Success");
			response.setResponseMessage("Login success");
			response.setResponseBody(user);
		}
		System.out.println("Login Response : " + response);
		return response;
	}

	public Response authorizeUser(User user) {
		Response response = new Response();
		String query = "insert into user values ('"+user.getUserId()+"','"+user.getFirstName()+"','"+user.getLastName()+"','"+user.getMobile()+"','"+user.getAddress()+"','"+user.getPassword()+"')";
		int saveDBResponse = DBConnectionUtil.insert(query);
		if(saveDBResponse == 0) {
			response.setResponseStatus("Fail");
			response.setResponseMessage("Please verify details");
			response.setResponseBody(user);
		}else 	if(saveDBResponse == -1) {
			response.setResponseStatus("Error");
			response.setResponseMessage("Internal server error");
			response.setResponseBody(user);
		}else {
			response.setResponseStatus("Success");
			response.setResponseMessage("Registration success");
			response.setResponseBody(user);
		}
		System.out.println("Registration Response : " + response);
		return response;
	}
	private User parseUser(ResultSet rs) {
		User user = null;
		try {
			if(null != rs && rs.next()) {
				user  = new User();
				user.setUserId(rs.getString(1));
				user.setFirstName(rs.getString(2));
				user.setLastName(rs.getString(3));
				user.setMobile(rs.getString(4));
				user.setAddress(rs.getString(5));
				user.setPassword(rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	public Response findAllUsers(User user) {
		Response response = new Response();
		List<String> users = null;
		String query = "select userId from user where  userId !='" + user.getUserId() +"'";
		users = parseUserIds(DBConnectionUtil.getData(query));
		if(users.size() ==0) {
			response.setResponseStatus("Fail");
			response.setResponseMessage("findAllUsers fail");
			response.setResponseBody(users);
		}else {
			response.setResponseStatus("Success");
			response.setResponseMessage("findAllUsers success");
		}
		response.setResponseBody(users);
		System.out.println("findAllUsers Response : " + response);
		return response;
	}

	private List<String> parseUserIds(ResultSet rs) {
		List<String> users = new ArrayList<String>();
		try {
			while(null != rs && rs.next()) {
				users.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
}
