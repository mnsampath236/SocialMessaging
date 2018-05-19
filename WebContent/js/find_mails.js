function searchMessages(){
	try {
		var message = new Object();
		message.mailFrom = sessionStorage.getItem("userId");
		message.mailContent = $("#findMailContent").val();
		message.mailSubject = $("#findMessageSubject").val();
		$.ajax({
			url : "http://localhost:8080/SocialMessaging/FindMails",
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
				window.location="./mail.html";
			}
		});
	} catch (ex) {
		alert(ex);
		window.location="./mail.html";
	}

}