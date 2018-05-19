package in.social.messaging.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.social.messaging.model.Mail;
import in.social.messaging.model.Response;
import in.social.messaging.service.SharingMailDao;
import in.social.messaging.util.JSONUtil;
public class GetSharingIdsByMessageIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetSharingIdsByMessageIdServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("....................... Find Mail Sharing Ids Service .......................");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
		if(bufferedReader != null){
			json = bufferedReader.readLine();
		}
		System.out.println("Find Mail Sharing Id request JSON : " + json);

		Mail mail = JSONUtil.mapper.readValue(json, Mail.class);

		Response changeMailResponse = new SharingMailDao().getSharingIDsByMailId(mail);

		response.setContentType("application/json");
		JSONUtil.mapper.writeValue(response.getOutputStream(), changeMailResponse);

		System.err.println(changeMailResponse);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
