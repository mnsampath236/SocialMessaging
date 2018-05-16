package in.social.messaging.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import in.social.messaging.model.Mail;
import in.social.messaging.model.Response;
import in.social.messaging.util.DBConnectionUtil;
import in.social.messaging.util.DateUtil;

public class MailDao {

	public Response changeMail(Mail mail) {
		Response response = new Response();
		PreparedStatement pstmt = null;
		Connection connection = null;
		ResultSet rs = null;
		try {
			String query = "update mail set mailFrom = ?, mailContent = ?, mailingDate = ?,mailSubject = ? , mailType = ? where mailId = ?";
			connection = DBConnectionUtil.getconnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, mail.getMailFrom());
			pstmt.setString(2, mail.getMailContent());
			pstmt.setString(3, DateUtil.getSqlDateTime());
			pstmt.setString(4, mail.getMailSubject());
			pstmt.setBoolean(5, mail.isMailType());
			pstmt.setInt(6,mail.getMailId());

			System.err.println("Prepared Statement for update mail after bind variables set:\n\t" + pstmt.toString());
			int updateResponse = pstmt.executeUpdate();
			if(updateResponse !=0){
				response.setResponseStatus("Success");
				response.setResponseMessage("mail Update Successfull");
			}else {
				response.setResponseStatus("Fail");
				response.setResponseMessage("mail Update Fail");
			}
		} catch (SQLException e) {
			response.setResponseStatus("Exception");
			response.setResponseMessage("mail Update Fail");
			e.printStackTrace();
		} finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
			} catch(Exception ex){}
		}
		response.setResponseBody(mail);
		System.err.println("update mail response : " + response);
		return response;
	}

	public Response fetchMails(Mail mail) {
		Response response = new Response();
		PreparedStatement pstmt = null;
		Connection connection = null;
		ResultSet resultSet = null;
		List<Mail> messages = null;
		try {
			String query = "select * from mail  where mailFrom = ?";
			connection = DBConnectionUtil.getconnection();
			if(mail.isMailType()) {
				query+=" union select * from mail  where mailFrom != ? and mailType =?";
				pstmt = connection.prepareStatement(query);
				pstmt.setString(2,mail.getMailFrom());
				pstmt.setBoolean(3, mail.isMailType());
			}else {
				pstmt = connection.prepareStatement(query);
			}
			pstmt.setString(1, mail.getMailFrom());
			System.err.println("Prepared Statement for read mails after bind variables set:\n\t" + pstmt.toString());
			resultSet = pstmt.executeQuery();
			if(resultSet != null){
				messages = new ArrayList<Mail>();
				while(resultSet.next()) {
					Mail mailMsg = new Mail();
					mailMsg.setMailId(resultSet.getInt(1));
					mailMsg.setMailContent(resultSet.getString(2));
					mailMsg.setMailingDate(resultSet.getString(3));
					mailMsg.setMailSubject(resultSet.getString(4));
					mailMsg.setMailFrom(resultSet.getString(5));
					mailMsg.setMailType(resultSet.getBoolean(6));
					messages.add(mailMsg);
				}
				response.setResponseStatus("Success");
				response.setResponseMessage("mails Reading Successfull");
			}else {
				response.setResponseStatus("Fail");
				response.setResponseMessage("mails Reading Fail");
			}
		} catch (SQLException e) {
			response.setResponseStatus("Exception");
			response.setResponseMessage("mails Reading Fail");
			e.printStackTrace();
		} finally{
			try{
				if(resultSet != null) resultSet.close();
				if(pstmt != null) pstmt.close();
			} catch(Exception ex){

			}
		}
		response.setResponseBody(messages);
		System.err.println("read mails response : " + response);
		return response;
	}

	public Response findMails(Mail mail) {
		return null;
	}

	public Response newMail(Mail mail) {
		Response response = new Response();
		PreparedStatement pstmt = null;
		Connection connection = null;
		ResultSet rs = null;
		try {
			String query = "insert into mail (mailFrom,mailContent,mailingDate,mailSubject,mailType) values (?,?,?,?,?)";
			connection = DBConnectionUtil.getconnection();
			pstmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, mail.getMailFrom());
			pstmt.setString(2, mail.getMailContent());
			pstmt.setString(3, DateUtil.getSqlDateTime());
			pstmt.setString(4, mail.getMailSubject());
			pstmt.setBoolean(5, mail.isMailType());
			System.err.println("Prepared Statement for save Mail after bind variables set:\n\t" + pstmt.toString());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs != null && rs.next()){
				int mailId= rs.getInt(1);
				System.out.println("Generated mail Id: "+mailId);
				mail.setMailId(mailId);
				response.setResponseStatus("Success");
				response.setResponseMessage("Message Post Successfull");
			}else {
				mail.setMailId(0);
				response.setResponseStatus("Fail");
				response.setResponseMessage("Message Post Fail");
			}
		} catch (SQLException e) {
			mail.setMailId(0);
			response.setResponseStatus("Exception");
			response.setResponseMessage("Message Post Fail");
			e.printStackTrace();
		} finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				//				if(connection != null) connection.close();
			} catch(Exception ex){}
		}
		response.setResponseBody(mail);
		System.err.println("save Mail response : " + response);
		return response;
	}

	public Response removeMail(Mail mail) {
		Response response = new Response();
		PreparedStatement pstmt = null;
		Connection connection = null;
		ResultSet rs = null;
		try {
			String query = "delete from mail  where mailId = ?";
			connection = DBConnectionUtil.getconnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, mail.getMailId());
			System.err.println("Prepared Statement for delete mail after bind variables set:\n\t" + pstmt.toString());
			int updateResponse = pstmt.executeUpdate();
			if(updateResponse !=0){
				response.setResponseStatus("Success");
				response.setResponseMessage("mail Delete Successfull");
			}else {
				response.setResponseStatus("Fail");
				response.setResponseMessage("mail Delete Fail");
			}
		} catch (SQLException e) {
			response.setResponseStatus("Exception");
			response.setResponseMessage("mail Delete Fail");
			e.printStackTrace();
		} finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
			} catch(Exception ex){}
		}
		response.setResponseBody(mail);
		System.err.println("delete mail response : " + response);
		return response;
	}

}
