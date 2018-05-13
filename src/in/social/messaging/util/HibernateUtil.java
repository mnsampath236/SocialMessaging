package in.social.messaging.util;

import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import in.social.messaging.model.Mail;
import in.social.messaging.model.User;

public class HibernateUtil {

	public final static Logger logger = Logger.getLogger("HibernateUtil");

	private static SessionFactory sessionFactory;
	static {
		try {
			sessionFactory =  new Configuration()
					.configure("hibernate.cfg.xml")
					.addPackage("in.social.messaging.model")
					.addClass(Mail.class)
					.addClass(User.class)
					.buildSessionFactory();
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session getSession()
			throws HibernateException {
		return sessionFactory.openSession();
	}
	public static void shutdown() {
		// Close caches and connection pools
		sessionFactory.close();
	}
	public static void main(String ... a) {
		getSession();
		System.out.println("session created");
	}
}
