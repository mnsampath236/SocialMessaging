package in.social.messaging.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.social.messaging.model.Mail;
import in.social.messaging.model.Response;
import in.social.messaging.util.DBConnectionUtil;

public class SharingMailDao {
	public void shareMail(int mailId,String[] sharingTo) {
		PreparedStatement pstmt = null;
		Connection connection = null;
		ResultSet rs = null;
		try {
			if(sharingTo.length >0) {
				String query = "insert into messagesharing values ";
				connection = DBConnectionUtil.getconnection();
				for(int i=0;i<sharingTo.length;i++) {
					query += "(?,?)";
					if(i< sharingTo.length-1) {
						query+=",";
					}
				}
				pstmt = connection.prepareStatement(query);
				for(int i=0, k=1;i<sharingTo.length;i++) {
					pstmt.setInt(k++,mailId );
					pstmt.setString(k++, sharingTo[i]);
				}
				System.err.println("Prepared Statement for share Mail after bind variables set:\n\t" + pstmt.toString());
				int executeUpdate = pstmt.executeUpdate();
				System.out.println("delete message response ::::::::::::::::::::::::::::::::: " + executeUpdate);
				if(executeUpdate>0) {
					System.out.println("Sharing successed ::::::::::::::::::::::::::::::::::::::::::::::::::::");
				}else{
					System.out.println("Sharing Failed ::::::::::::::::::::::::::::::::::::::::::::::::::::");
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
			} catch(Exception ex){}
		}
	}
	public void deleteSharingMessages(int mailId) {
		PreparedStatement pstmt = null;
		Connection connection = null;
		ResultSet rs = null;
		try {
			String query = "delete from messagesharing where mailId = ? ";
			connection = DBConnectionUtil.getconnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1,mailId );
			System.err.println("Prepared Statement for share Mail after bind variables set:\n\t" + pstmt.toString());
			int executeUpdate = pstmt.executeUpdate();
			if(executeUpdate>0) {
				System.out.println("deleteSharingMessages successed ::::::::::::::::::::::::::::::::::::::::::::::::::::");
			}else{
				System.out.println("deleteSharingMessages Failed ::::::::::::::::::::::::::::::::::::::::::::::::::::");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
			} catch(Exception ex){}
		}
	}
	public Response getSharingIDsByMailId(Mail mail) {
		PreparedStatement pstmt = null;
		Connection connection = null;
		Response response = new Response();
		ResultSet rs = null;
		List<String> userIds = new ArrayList<String>();
		try {
			String query = "select userId from messagesharing where mailId = ?;";
			connection = DBConnectionUtil.getconnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, mail.getMailId());
			System.err.println("Prepared Statement for Get Sharing User id by message id after bind variables set ::::::::::::::::::::::::: " + pstmt.toString());
			rs = pstmt.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					userIds.add(rs.getString(1));
				}
			}
			System.out.println("Get Share message IDs response ::::::::::::::::::::::::::::::::: " + userIds );
			response.setResponseStatus("Success");
			response.setResponseMessage("mails Reading Successfull");
			response.setResponseBody(userIds);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
			} catch(Exception ex){}
		}
		System.err.println("Get Message Sharing USer IDS response : " + response);
		return response;
	}

}
