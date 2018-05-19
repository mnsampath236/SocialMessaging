function updateMessages() {
	getMessagesForUpdate(false);
}
function getMessagesForUpdate(messageType){
	try {
		var message = new Object();
		message.mailFrom = sessionStorage.getItem("userId");
		message.mailType = messageType;
		$.ajax({
			url : "http://localhost:8080/SocialMessaging/FetchMail",
			type : 'POST',
			dataType : 'json',
			data : JSON.stringify(message),
			contentType : 'application/json',
			mimeType : 'application/json',

			success : function(data) {
				$('#tubody').empty();
				$.each(data.responseBody, function(idx, obj) {
					var eachrow = "<tr>"
						+ "<td>" + obj.mailSubject + "</td>"
						+ "<td>" + obj.mailContent + "</td>"
						+ "<td>" + obj.mailingDate + "</td>"
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
function getSharingIdsforByMessageId(){
	try {
		var mail = new Object();
		mail.mailId = sessionStorage.getItem("mailId");
		$.ajax({
			url : "http://localhost:8080/SocialMessaging/GetSharingIdsByMessageId",
			type : 'POST',
			dataType : 'json',
			data : JSON.stringify(mail),
			contentType : 'application/json',
			mimeType : 'application/json',

			success : function(data) {
				var respJSONString = JSON.stringify(data);
				console.log(respJSONString);
				var jsonObj = JSON.parse(respJSONString);
				console.log(jsonObj.responseStatus + " : " + jsonObj.responseMessage);
				$('#updateMailSharingUserIds').empty();
				$.each(data.responseBody, function (i, item) {
					var option = new Option(item, item); 
					$('#updateMailSharingUserIds').append('<option>' + item + '</option>');

				});
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
	$("#editMailSubject").val(message.mailSubject);
	$("#editMailContent").val(message.mailContent);
	$('.editMailType option[value='+message.mailType+']')
	
	if (typeof(Storage) !== "undefined") {
		// Store
		sessionStorage.setItem("mailId",message.mailId);
		console.log("mailId Id stored in session storage. " + sessionStorage.getItem("mailId"));
	} else {
		console.log("Sorry, your browser does not support Web Storage...");
	}
	getSharingIdsforByMessageId();
	location.href = "#editMessagePost";
}

function updateMessageById(){
	try {

		var message = new Object();
		message.mailId = sessionStorage.getItem("mailId");
		message.mailSubject = $("#editMailSubject").val();
		message.mailFrom = sessionStorage.getItem("userId");
		message.mailContent = $("#editMailContent").val();
		message.mailType = $("#editMailType").val();
		message.sharingIds = $('#updateMailSharingUserIds').val();
		$.ajax({
			url : "http://localhost:8080/SocialMessaging/ChangeMail",
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
					alert("Message Updated successfully.");
					window.location="./mail.html";
				}else{
					alert("Message Updated Fail.");
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