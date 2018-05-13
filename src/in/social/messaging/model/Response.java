package in.social.messaging.model;

public class Response {
	private String responseStatus;
	private String responseMessage;
	private Object responseBody;
	public Response() {
		super();
	}
	public Response(String responseStatus, String responseMessage, Object responseBody) {
		this.responseStatus = responseStatus;
		this.responseMessage = responseMessage;
		this.responseBody = responseBody;
	}
	public String getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public Object getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(Object responseBody) {
		this.responseBody = responseBody;
	}
	@Override
	public String toString() {
		return "Response [responseStatus=" + responseStatus + ", responseMessage=" + responseMessage + ", responseBody="
				+ responseBody + "]";
	}
	
}
