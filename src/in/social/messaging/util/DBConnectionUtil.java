
package in.social.messaging.util;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnectionUtil {

    private static Connection connection;
    public static ResultSet resultSet= null;
    public static Statement statement;
    private String dbURL;
    private String dbDriver;
    private String userName;
    private String password;
    
    /**
	 * @param dbURL
	 * @param dbDriver
	 * @param userName
	 * @param password
	 */
	public DBConnectionUtil(String dbURL, String dbDriver, String userName, String password) {
		this.dbURL = dbURL;
		this.dbDriver = dbDriver;
		this.userName = userName;
		this.password = password;
	}
	public void loadDBConfiguration() {
        try {
            
            Class.forName(dbDriver);
            Logger.getLogger(DBConnectionUtil.class.getName()).log(Level.INFO, dbDriver + " Loaded" , dbDriver + " Loaded");
            Logger.getLogger(DBConnectionUtil.class.getName()).log(Level.INFO, dbURL, dbURL);
            connection = DriverManager.getConnection(dbURL, userName, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int insert(String query) {
        int response = 0;
        Logger.getLogger(DBConnectionUtil.class.getName()).log(Level.INFO, query, query);
        try {
            statement = connection.createStatement();
            response = statement.executeUpdate(query);
        } catch (SQLException ex) {
            response =-1;
            Logger.getLogger(DBConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return response;
    }

    public static ResultSet getData(String query){
        try {
             Logger.getLogger(DBConnectionUtil.class.getName()).log(Level.INFO, query, query);
            statement= connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultSet;
    }
    public static Connection getconnection() {
    	return connection;
    }
    public static void main(String[] args) {
		
	}
}
