function searchMessages(){
	try {
		var message = new Object();
		message.owner = sessionStorage.getItem("emailId");
		message.privacy = $('#searchMessageType').val();
		message.message = $("#searchMessageBody").val();
		message.title = $("#searchMessageTitle").val();
		$.ajax({
			url : "http://localhost:8080/MS/searchMessages",
			type : 'POST',
			dataType : 'json',
			data : JSON.stringify(message),
			contentType : 'application/json',
			mimeType : 'application/json',

			success : function(data) {
//				// Check browser support for session storage
//				if (typeof(Storage) !== "undefined") {
//				// Store
//				sessionStorage.setItem("messages", data);
//				} else {
//				console.log("Sorry, your browser does not support Web Storage...");
//				}
				$('#tbody').empty();
				$.each(data.responseObject, function(idx, obj) {
					var eachrow = "<tr>"
						+ "<td>" + obj.title + "</td>"
						+ "<td>" + obj.message + "</td>"
						+ "<td>" + obj.messageDate + "</td>"
						+ "</tr>";
					$('#tbody').append(eachrow);
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