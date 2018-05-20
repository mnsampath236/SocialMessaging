function registration() {

	// get inputs
	try {
		var user = new Object();
		user.firstName = $('#registrationFirstName').val();
		user.lastName = $('#registrationLastName').val();
		user.mobile = $('#registrationMobile').val();
		user.address = $('#registrationAddress').val();
		user.userId = $('#registrationEmailId').val();
		user.password = $('#registrationPassword').val();

		$.ajax({
			url : "./UserAuthorization",
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
					alert("User Registration Success.");
					window.location="./index.html"
				}else{
					alert("User User Authorization Failed.\n please verify details");
				}
			},

			error : function(data, status, er) {
				alert("error: " + JSON.stringify(data) + " status: " + status + " er:" + er);
				window.location="./index.html"
			}
		});
	} catch (ex) {
		alert(ex);
		window.location="./index.html"
	}
}