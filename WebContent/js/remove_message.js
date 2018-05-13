function deleteMessages() {
	getMessagesForDelete(false);
}
function getMessagesForDelete(messageType){
	try {
		var message = new Object();
		message.owner = sessionStorage.getItem("emailId");
		message.privacy = messageType;
		$.ajax({
			url : "http://localhost:8080/MS/ReadMessages",
			type : 'POST',
			dataType : 'json',
			data : JSON.stringify(message),
			contentType : 'application/json',
			mimeType : 'application/json',

			success : function(data) {
				$('#tdbody').empty();
				$.each(data.responseObject, function(idx, obj) {
					var eachrow = "<tr>"
						+ "<td>" + obj.title + "</td>"
						+ "<td>" + obj.message + "</td>"
						+ "<td>" + obj.messageDate + "</td>"
						+ "<td><input type='button' value='Delete' onclick='deleteMessabeById("+ obj.messageId+");'/></td>"
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



function deleteMessabeById(messageId){
	try {

		var message = new Object();
		message.messageId = messageId;
		$.ajax({
			url : "http://localhost:8080/MS/DeleteMessage",
			type : 'POST',
			dataType : 'json',
			data : JSON.stringify(message),
			contentType : 'application/json',
			mimeType : 'application/json',

			success : function(data) {
				var respJSONString = JSON.stringify(data);
				console.log(respJSONString);
				var jsonObj = JSON.parse(respJSONString);
				console.log(jsonObj.status + " : " + jsonObj.statusMessage);
				if(jsonObj.status == "Success"){
					$("#deleteMessageDiv").hide(1000);
					alert("Message deleted successfully.");
					window.location="./message.html";
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