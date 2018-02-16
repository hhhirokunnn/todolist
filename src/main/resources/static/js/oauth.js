$.get("/user", function(data) {
	    $("#pic").append("<img src='"+data.userAuthentication.details.picture+"' style=\"height:50px;width:50px\">");
	    $("#user").html(data.userAuthentication.details.name);
	    $(".authenticated").show()
});
var logout = function() {
    $.post("/logout", function() {
        $("#user").html('');
        $(".authenticated").hide();
    })
    return true;
}
$.ajaxSetup({
    beforeSend : function(xhr, req) {
      if (req.type == 'POST') {
          xhr.setRequestHeader("X-XSRF-TOKEN",
              Cookies.get('XSRF-TOKEN'));
      }
    }
});