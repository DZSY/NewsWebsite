/**
 * Created by positif on 23/05/2017.
 */
var follow = function (button, columnName) {
    $.ajax({
        type: 'POST',
        url: '/toFollow',
        data: {
            columnName: columnName
        },
        success: function (msg) {
            if (msg == "ok") {
                $(button)
                    .removeClass("btn-success")
                    .addClass('btn-danger')
                    .attr("onclick", "unfollow(this,\""+columnName+"\")")
                    .html('<span ' +
                    'class="glyphicon glyphicon-minus"></span>' +
                    ' 取消关注</button>');
            }
            else alert(msg);
        }
    });
}


var unfollow = function (button,columnName) {
    $.ajax({
        type: 'POST',
        url: '/unFollow',
        data: {
            columnName: columnName
        },
        success: function (msg) {
            if (msg == "ok") {
                $(button)
                    .removeClass("btn-danger")
                    .addClass('btn-success')
                    .attr("onclick", "follow(this,\""+columnName+"\")")
                    .html('<span ' +
                        'class="glyphicon glyphicon-plus"></span>' +
                        ' 加入关注</button>');
            }
            else alert(msg);
        }
    });
}