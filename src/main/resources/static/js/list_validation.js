$(function () {
	$("#js-list-validation-button").prop("disabled", true);
	$("#js-list-validation-textbox").on('keyup', function() {
		$("#js-list-validation-message").empty();
	var title = $("#js-list-validation-textbox").val();
		if(!title){
			$("#js-list-validation-button").prop("disabled", true);
			$("#js-list-validation-message").append('<p style="color:#FF0000;">タイトルを入力してください。</p>');
		}else if(title.length > 30){
			$("#js-list-validation-button").prop("disabled", true);
			$("#js-list-validation-message").append('<p style="color:#FF0000;">タイトルを30文字以内で入力してください。</p>');
		}else{ 
			$.ajax({
				type : "GET",
				url : "/api/v1/list/freeword",
				data : {
					title: title
				},
				dataType : "json",
				success : function(data,status,xhr) {
					for(var i in data){
						if(title == data[i].listTitle){
							$("#js-list-validation-button").prop("disabled", true);
							$("#js-list-validation-message").append('<p style="color:#FF0000;">同じタイトルのリストが重複してます。</p>');
							break;
						}
					}
				 },
					error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("error:" + XMLHttpRequest + "/" + textStatus + "/" + errorThrown);
				 }
			});
			$("#js-list-validation-button").prop("disabled", false);
		}
	});
		
});