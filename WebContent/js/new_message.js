function sendMessage() {

	// get inputs
	try {

		var message = new Object();
		message.title = $("#messageTitle").val();
		message.owner = sessionStorage.getItem("emailId");
		message.privacy = $("#messageType").val();
		message.message = $("#message").val();
		$.ajax({
			url : "http://localhost:8080/MS/PostMessage",
			type : 'POST',
			dataType : 'json',
			data : JSON.stringify(message),
			contentType : 'application/json',
			mimeType : 'application/json',

			success : function(data) {
				var respJSONString = JSON.stringify(data);
				console.log(respJSONString);
				var jsonObj = JSON.parse(JSON.stringify(data.responseObject));
				console.log(jsonObj.owner);
			},
			error : function(data, status, er) {
				alert("error: " + JSON.stringify(data) + " status: " + status + " er:" + er);
			}
		});
	} catch (ex) {
		alert(ex);
	}
}