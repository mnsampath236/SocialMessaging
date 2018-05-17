function readMessages() {
	getMessages(true);
}
function getMessages(messageType){
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

				$('#tbody').empty();
				$.each(data.responseBody, function(idx, obj) {
					var eachrow = "<tr>"
						+ "<td>" + obj.mailSubject + "</td>"
						+ "<td>" + obj.mailContent + "</td>"
						+ "<td>" + obj.mailingDate + "</td>";
					
						if(obj.mailType === true){
							eachrow += "<td> Public </td>"
						}else{
							eachrow += "<td> Private </td>"
						}
						eachrow += "</tr>";
					$('#tbody').append(eachrow);
				})
				location.href = "#messageRead";
			},

			error : function(data, status, er) {
				alert("error: " + JSON.stringify(data) + " status: " + status + " er:" + er);
			}
		});
	} catch (ex) {
		alert(ex);
	}

}