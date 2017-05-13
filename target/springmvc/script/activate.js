function activateCount() {
    if (i == 0) {
        location.href = "/";
        clearInterval(interval);
    }
    document.getElementById("mes").innerHTML = i;
    i--;
}
var i;
var interval;

$(function() {
    $('#activate-form').bootstrapValidator({
        feedbackIcons: {/*input状态样式图片*/
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {/*验证：规则*/
            activation_code: {//验证input项：验证规则
                validators: {
                    notEmpty: {//非空验证：提示消息
                        message: '激活码不能为空'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9]+$/,
                        message: '激活码只能由数字、大小写字母组成'
                    },
                    stringLength: {
                        min: 6,
                        max: 6,
                        message: '请输入6位激活码'
                    },
                }
            }
        }
    });
    $("#activate-submit").on("click", function(e){
        e.preventDefault();
        $.ajax({
            type: 'POST',
            url: '/user/ajax_activate',
            data: {
                activation : $('#activation-code').val(),
            },
            success: function (msg) {
                if (msg == "success") {
                    $('#login-feedback').css("color","#3c763d");
                    $('#login-feedback').html("激活成功，将在<span id=\"mes\">3</span> 秒后跳转至首页");
                    i = 2;
                    interval = setInterval("activateCount()", 1000);
                }
                else if (msg == "wrong activation code") {
                    $('#login-feedback').text("激活码错误，请重新输入");
                }
                else {
                    location.href("error");
                }
            },
            error: function (msg) {
                alert("出现错误了:" + msg.text);
                return;
            }
        })
    });
});
