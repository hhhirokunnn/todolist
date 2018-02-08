$(function () {
	$("#js-task-validation-button").prop("disabled", true);
	$("#js-task-validation-textbox").on('keyup', function() {
		$("#js-task-validation-message").empty();
	var title = $("#js-task-validation-textbox").val();
		if(!title){
			$("#js-task-validation-button").prop("disabled", true);
			$("#js-task-validation-message").append('<p style="color:#FF0000;">タイトルを入力してください。</p>');
		}else if(stringToArray(title).length > 30){
			$("#js-task-validation-button").prop("disabled", true);
			$("#js-task-validation-message").append('<p style="color:#FF0000;">タイトルを30文字以内で入力してください。</p>');
		}else{ 
			$.ajax({
				type : "GET",
				url : "/api/v1/task/freeword",
				data : {
					title: title
				},
				dataType : "json",
				success : function(data,status,xhr) {
					for(var i in data){
						if(title == data[i].taskTitle){
							$("#js-task-validation-button").prop("disabled", true);
							$("#js-task-validation-message").append('<p style="color:#FF0000;">同じタイトルのタスクが存在してます。</p>');
							break;
						}
					}
				 },
					error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("error:" + XMLHttpRequest + "/" + textStatus + "/" + errorThrown);
				 }
			});
			if($("#js-task-validation-limitdate").val().match("^[0-9]{8}$"))
				$("#js-task-validation-button").prop("disabled", false);
		}
	});
	$("#js-task-validation-limitdate").on('keyup', function() {
		$("#js-task-validation-limitdate-message").empty();
		var limitDate = $("#js-task-validation-limitdate").val();
		if(!limitDate){
			$("#js-task-validation-button").prop("disabled", true);
			$("#js-task-validation-limitdate-message").append('<p style="color:#FF0000;">期日を入力してください。</p>');
		}else if(!limitDate.match("^[0-9]{8}$")){
			$("#js-task-validation-button").prop("disabled", true);
			$("#js-task-validation-limitdate-message").append('<p style="color:#FF0000;">期日はyyyyMMddで入力してください。</p>');
		}else{
			if(stringToArray($("#js-task-validation-textbox").val()).length <= 30)
				$("#js-task-validation-button").prop("disabled", false);
		}
	});
	//utf8mb4の文字数をカウントするためのfunction
	function stringToArray (str) {
	    return str.match(/[\uD800-\uDBFF][\uDC00-\uDFFF]|[^\uD800-\uDFFF]/g) || [];
	}
		
});