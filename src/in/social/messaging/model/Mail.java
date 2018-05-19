package in.social.messaging.model;

import java.util.Arrays;

@SuppressWarnings("serial")
public class Mail implements java.io.Serializable{

	private int mailId;
	private String mailContent;
	private String mailingDate;
	private String mailSubject;
	private String mailFrom;
	private boolean mailType;
	private String [] sharingIds;
	/**
	 * 
	 */
	public Mail() {
		super();
	}
	public Mail(int mailId, String mailContent, String mailingDate, String mailSubject, String mailFrom,
			boolean mailType, String[] sharingIds) {
		super();
		this.mailId = mailId;
		this.mailContent = mailContent;
		this.mailingDate = mailingDate;
		this.mailSubject = mailSubject;
		this.mailFrom = mailFrom;
		this.mailType = mailType;
		this.sharingIds = sharingIds;
	}
	public int getMailId() {
		return mailId;
	}
	public void setMailId(int mailId) {
		this.mailId = mailId;
	}
	public String getMailContent() {
		return mailContent;
	}
	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}
	public String getMailingDate() {
		return mailingDate;
	}
	public void setMailingDate(String mailingDate) {
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
	public String[] getSharingIds() {
		return sharingIds;
	}
	public void setSharingIds(String[] sharingIds) {
		this.sharingIds = sharingIds;
	}
	@Override
	public String toString() {
		return "Mail [mailId=" + mailId + ", mailContent=" + mailContent + ", mailingDate=" + mailingDate
				+ ", mailSubject=" + mailSubject + ", mailFrom=" + mailFrom + ", mailType=" + mailType + ", sharingIds="
				+ Arrays.toString(sharingIds) + "]";
	}
	
}
