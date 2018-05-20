function deleteMessages() {
	getMessagesForDelete(false);
}
function getMessagesForDelete(messageType){
	try {
		var message = new Object();
		message.mailFrom = sessionStorage.getItem("userId");
		message.mailType = messageType;
		$.ajax({
			url : "./FetchMail",
			type : 'POST',
			dataType : 'json',
			data : JSON.stringify(message),
			contentType : 'application/json',
			mimeType : 'application/json',

			success : function(data) {
				$('#tdbody').empty();
				$.each(data.responseBody, function(idx, obj) {
					var eachrow = "<tr>"
						+ "<td>" + obj.mailSubject + "</td>"
						+ "<td>" + obj.mailContent + "</td>"
						+ "<td>" + obj.mailingDate + "</td>"
						+ "<td><input type='button' value='Delete' onclick='deleteMessabeById("+ obj.mailId+");'/></td>"
						+ "</tr>";
					$('#tdbody').append(eachrow);
				})
			},

			error : function(data, status, er) {
				alert("error: " + JSON.stringify(data) + " status: " + status + " er:" + er);
			}
		});
	} catch (ex) {
		alert(ex);
	}

}



function deleteMessabeById(mailId){
	try {

		var message = new Object();
		message.mailId = mailId;
		$.ajax({
			url : "./RemoveMail",
			type : 'POST',
			dataType : 'json',
			data : JSON.stringify(message),
			contentType : 'application/json',
			mimeType : 'application/json',

			success : function(data) {
				var respJSONString = JSON.stringify(data);
				console.log(respJSONString);
				var jsonObj = JSON.parse(respJSONString);
				console.log(jsonObj.responseStatus + " : " + jsonObj.responseMessage);
				if(jsonObj.responseStatus == "Success"){
					alert("Message deleted successfully.");
					window.location="./mail.html";
				}else{
					alert("Message Not deleted.");
					window.location="./mail.html";
				}
			},

			error : function(data, status, er) {
				alert("error: " + JSON.stringify(data) + " status: " + status + " er:" + er);
				window.location="./mail.html";
			}
		});
	} catch (ex) {
		alert(ex);
		window.location="./mail.html";
	}

}