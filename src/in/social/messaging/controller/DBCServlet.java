package in.social.messaging.controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.social.messaging.util.DBConnectionUtil;

/**
 * Servlet implementation class DBCServlet
 */
public class DBCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBCServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		String dbURL = config.getServletContext().getInitParameter("DB_URL");
		String driverName = config.getServletContext().getInitParameter("DB_DRIVER");
		String userName = config.getServletContext().getInitParameter("DB_USER");
		String password = config.getServletContext().getInitParameter("DB_PASSWORD");
		DBConnectionUtil connectionUtil =new  DBConnectionUtil(dbURL,driverName,userName, password);
		connectionUtil.loadDBConfiguration();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
