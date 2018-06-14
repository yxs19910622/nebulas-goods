/**
 * Created by yangxs on 2018/6/14.
 */
function addGoods(){
    var imgUrl = $("#imgUrl").val();
    var title = $("#title").val();
    var label = $("#label").val();
    var message = $("#message").val();
    $.ajax({
        type: "get",
        url: "/goods/add",
        data: "userId=1&url="+imgUrl+"&title="+title+"&label="+label+"&message="+message,
        success: function (data) {
            location.href="/";
        }
    });
}