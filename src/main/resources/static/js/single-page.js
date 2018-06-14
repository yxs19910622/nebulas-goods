function addComments() {
    var comment = $("#input_comment").val();
    var id = GetQueryString("id");
    $.ajax({
        type: "get",
        url: "/comments/add",
        data: "userId=2&goodsId="+id+"&content="+comment,
        success: function (data) {
            location.reload();
        }
    });
}

function GetQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}