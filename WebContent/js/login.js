function login() {

	// get inputs
	try {
		var user = new Object();
		user.userId = $('#signinEmailId').val();
		user.password = $('#singinPassword').val();

		$.ajax({
			url : "http://localhost:8080/SocialMessaging/UserAuthentication",
			type : 'POST',
			dataType : 'json',
			data : JSON.stringify(user),
			contentType : 'application/json',
			mimeType : 'application/json',

			success : function(data) {
				var respJSONString = JSON.stringify(data);
				console.log(respJSONString);
				var jsonObj = JSON.parse(respJSONString);
				console.log(jsonObj.responseStatus + " : " + jsonObj.responseMessage);
				if(jsonObj.responseStatus == "Success"){
					// Check browser support for session storage
					if (typeof(Storage) !== "undefined") {
						// Store
						var responseObject =JSON.parse(JSON.stringify(data.responseBody));
						sessionStorage.setItem("userId", responseObject.userId);
						console.log("User Id stored in session storage. " + sessionStorage.getItem("userId"));
					} else {
						console.log("Sorry, your browser does not support Web Storage...");
					}
					alert("User Login Success.");
					window.location="./mail.html"
				}else{
					alert("User login Failed.\n please verify credentials");
				}
			},

			error : function(data, status, er) {
				alert("error: " + data + " status: " + status + " er:" + er);
			}
		});
	} catch (ex) {
		alert(ex);
	}
}
