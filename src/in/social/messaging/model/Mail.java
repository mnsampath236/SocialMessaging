package in.social.messaging.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Mail {
	
	@Id
	@GeneratedValue
	private Long mailId;
	private String mailContent;
	private Date mailingDate;
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
	public Mail(Long mailId, String mailContent, Date mailingDate, String mailSubject, String mailFrom,
			boolean mailType) {
		this.mailId = mailId;
		this.mailContent = mailContent;
		this.mailingDate = mailingDate;
		this.mailSubject = mailSubject;
		this.mailFrom = mailFrom;
		this.mailType = mailType;
	}
	public Long getMailId() {
		return mailId;
	}
	public void setMailId(Long mailId) {
		this.mailId = mailId;
	}
	public String getMailContent() {
		return mailContent;
	}
	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}
	public Date getMailingDate() {
		return mailingDate;
	}
	public void setMailingDate(Date mailingDate) {
		this.mailingDate = mailingDate;
	}
	public String getMailSubject() {
		return mailSubject;
	}
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}
	public String getMailFrom() {
		return mailFrom;
	}
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}
	public boolean isMailType() {
		return mailType;
	}
	public void setMailType(boolean mailType) {
		this.mailType = mailType;
	}
	@Override
	public String toString() {
		return "Mail [mailId=" + mailId + ", mailContent=" + mailContent + ", mailingDate=" + mailingDate
				+ ", mailSubject=" + mailSubject + ", mailFrom=" + mailFrom + ", mailType=" + mailType + "]";
	}
	
	
}
