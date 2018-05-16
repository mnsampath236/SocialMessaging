function sendMessage() {

	// get inputs
	try {

		var mail = new Object();
		mail.mailSubject = $("#mailSubject").val();
		mail.mailFrom = sessionStorage.getItem("userId");
		mail.mailContent = $("#mailContent").val();
		mail.mailType = $("#mailType").val();
		$.ajax({
			url : "http://localhost:8080/SocialMessaging/NewMail",
			type : 'POST',
			dataType : 'json',
			data : JSON.stringify(mail),
			contentType : 'application/json',
			mimeType : 'application/json',

			success : function(data) {
				var respJSONString = JSON.stringify(data);
				console.log(respJSONString);
				var respJson = JSON.parse(respJSONString);
				var jsonObj = JSON.parse(JSON.stringify(data.responseBody));
				console.log(respJson.responseStatus);
				if(respJson.responseStatus=="Success"){
					alert("New Message Posted Successfully");
				}else if(respJson.responseStatus=="Error"){
					alert("New Message Post got Exception");
				}else{
					alert("New Message Post Failed");
				}
			},
			error : function(data, status, er) {
				alert("error: " + JSON.stringify(data) + " status: " + status + " er:" + er);
			}
		});
	} catch (ex) {
		alert(ex);
	}
}