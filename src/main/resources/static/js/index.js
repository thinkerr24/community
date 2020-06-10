$(function(){
	$("#publishBtn").click(publish);
});

function publish() {
	$("#publishModal").modal("hide");

    // 提示框
    var hint = $("#hintBody");
	// 获取标题和内容
	var title = $("#recipient-name").val();
	if (title.length == 0) {
	    hint.text("标题为空!");
	}
	var content = $("#message-text").val();
	if (content.length == 0) {
	    hint.text("内容为空!");
	}
	// 发送异步请求(POST)
    $.post(
        CONTEXT_PATH + "/discuss/add",
        {"title":title, "content":content},
        function(data) {
             data = $.parseJSON(data);
             // 在提示框中显示返回消息
             hint.text(data.msg);
             // 显示提示框
             $("#hintModal").modal("show");
             // 2s后自动隐藏提示框
             	setTimeout(function(){
             		$("#hintModal").modal("hide");
             		// 刷新页面
             		if (data.code == 0) {
             		    window.location.reload();
             		}
             	}, 2000);
        }
    );


}