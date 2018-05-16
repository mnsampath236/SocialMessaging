package in.social.messaging.model;

import java.util.Date;

@SuppressWarnings("serial")
public class Mail implements java.io.Serializable{

	private int mailId;
	private String mailContent;
	private String mailingDate;
	private String mailSubject;
	private String mailFrom;
	private boolean mailType;
	/**
	 * 
	 */
	public Mail() {
		super();
	}
	/**
	 * @param mailId
	 * @param mailContent
	 * @param mailingDate
	 * @param mailSubject
	 * @param mailFrom
	 * @param mailType
	 */
	public Mail(int mailId, String mailContent, String mailingDate, String mailSubject, String mailFrom,
			boolean mailType) {
		this.mailId = mailId;
		this.mailContent = mailContent;
		this.mailingDate = mailingDate;
		this.mailSubject = mailSubject;
		this.mailFrom = mailFrom;
		this.mailType = mailType;
	}
	/**
	 * @return the mailId
	 */
	public int getMailId() {
		return mailId;
	}
	/**
	 * @param mailId the mailId to set
	 */
	public void setMailId(int mailId) {
		this.mailId = mailId;
	}
	/**
	 * @return the mailContent
	 */
	public String getMailContent() {
		return mailContent;
	}
	/**
	 * @param mailContent the mailContent to set
	 */
	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}
	/**
	 * @return the mailingDate
	 */
	public String getMailingDate() {
		return mailingDate;
	}
	/**
	 * @param mailingDate the mailingDate to set
	 */
	public void setMailingDate(String mailingDate) {
		this.mailingDate = mailingDate;
	}
	/**
	 * @return the mailSubject
	 */
	public String getMailSubject() {
		return mailSubject;
	}
	/**
	 * @param mailSubject the mailSubject to set
	 */
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}
	/**
	 * @return the mailFrom
	 */
	public String getMailFrom() {
		return mailFrom;
	}
	/**
	 * @param mailFrom the mailFrom to set
	 */
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}
	/**
	 * @return the mailType
	 */
	public boolean isMailType() {
		return mailType;
	}
	/**
	 * @param mailType the mailType to set
	 */
	public void setMailType(boolean mailType) {
		this.mailType = mailType;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Mail [mailId=" + mailId + ", mailContent=" + mailContent + ", mailingDate=" + mailingDate
				+ ", mailSubject=" + mailSubject + ", mailFrom=" + mailFrom + ", mailType=" + mailType + "]";
	}
	
}
