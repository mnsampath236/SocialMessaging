function updateMessages() {
	getMessagesForUpdate(false);
}
function getMessagesForUpdate(messageType){
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
				$('#tubody').empty();
				$.each(data.responseObject, function(idx, obj) {
					console.log(obj);
					var eachrow = "<tr>"
						+ "<td>" + obj.title + "</td>"
						+ "<td>" + obj.message + "</td>"
						+ "<td>" + obj.messageDate + "</td>"
						+ "<td><input type='button' value='Edit' onclick='editMessage("+JSON.stringify(obj)+");'/></td>"
						+ "</tr>";
					$('#tubody').append(eachrow);
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

function editMessage(message){
	console.log(message);
	$("#updateMessageDiv").hide(1000);
	$("#updateMessageContentDiv").show();
	$("#updateMessageTitle").val(message.title);
	$("#updateMessageBody").val(message.message);
	$('.updateMessageType option[value='+message.privacy+']')
	//$("#updateMessageType").val(message.privacy);
	if (typeof(Storage) !== "undefined") {
		// Store
		sessionStorage.setItem("updateMessageId",message.messageId);
		console.log("Email Id stored in session storage. " + sessionStorage.getItem("emailId"));
	} else {
		console.log("Sorry, your browser does not support Web Storage...");
	}
}

function updateMessageById(){
	try {

		var message = new Object();
		message.messageId = sessionStorage.getItem("updateMessageId");
		message.message = $("#updateMessageBody").val();
		message.owner = sessionStorage.getItem("emailId");
		message.title = $("#updateMessageTitle").val();
		$.ajax({
			url : "http://localhost:8080/MS/UpdateMessage",
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
					$("#updateMessageDiv").hide(1000);
					alert("Message Updated successfully.");
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