$(function () {
	$("#js-search-word").val("");
	$("#js-search-word").on('keyup', function() {
		var freeword = $("#js-search-word").val();
		if(!freeword)
			return null;
			
		$.ajax({
			type : "GET",
			url : "/api/v1/task/freeword",
			data : {
				title: freeword
			},
			dataType : "json",
			success : function(data,status,xhr) {
				$("#js-search-tasks").empty();
				$("#js-search-num-tasks").empty();
				if(!data[0])$("#js-search-tasks").append('<p style="color:#FF0000;">対象のToTaskは見つかりません</p>');
				for(var i in data){
					var todotask =
						'<tr style="argin-top:10px;">'
						+'<td>'
						+'<a href="/list/'+data[i].listId+'/tasks/">'+data[i].taskTitle+'</a>'
						+'<p>リスト名：'+data[i].listTitle+'</p>'
						+'</td>'
						+'<td>'
						+'<p>期限：'+data[i].frontTaskLimitDate+'</p>'
						+'<p>作成日：'+data[i].taskCreated+'</p>'
						+'</td>'
						+'</tr>';
					$("#js-search-tasks").append(todotask);
					var countNum = parseInt(i)+1;
					$("#js-search-num-tasks").empty();
					$("#js-search-num-tasks").append('<p style="color:#FF0000;">ヒットしたタスク数：'+countNum+'</p>');
				}
			 },
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("error:" + XMLHttpRequest + "/" + textStatus + "/" + errorThrown);
			 }
		});
		$.ajax({
			type : "GET",
			url : "/api/v1/list/freeword",
			data : {
				title: freeword
			},
			dataType : "json",
			success : function(data,status,xhr) {
				$("#js-search-lists").empty();
				$("#js-search-num-lists").empty();
				if(!data[0])$("#js-search-lists").append('<p style="color:#FF0000;">対象のToListは見つかりません</p>');
				for(var i in data){
					var todotask =
						'<tr style="margin-top:10px;">'
						+'<td>'
						+'<a href="/list/'+data[i].listId+'/tasks/">'+data[i].listTitle+'</a>'
						+'</td>'
						+'<td>'
						+'<p style="margin-left:20%;">作成日：'+data[i].listCreated+'</p>'
						+'</td>'
						+'</tr>';
					$("#js-search-lists").append(todotask);
					var countNum = parseInt(i)+1;
					$("#js-search-num-lists").empty();
					$("#js-search-num-lists").append('<p style="color:#FF0000;">ヒットしたタスク数：'+countNum+'</p>');
				}
			 },
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("error:" + XMLHttpRequest + "/" + textStatus + "/" + errorThrown);
			 }
		});
	});
});