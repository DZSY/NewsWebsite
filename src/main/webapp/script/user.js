function activateCount() {
    if (i == 0) {
        location.href = "/user";
        clearInterval(interval);
    }
    document.getElementById("mes").innerHTML = i;
    i--;
}
function homeCount() {
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

    $('#login-form-link').click(function(e) {
        $("#login-form").delay(100).fadeIn(100);
        $("#register-form").fadeOut(100);
        $('#register-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#register-form-link').click(function(e) {
        $("#register-form").delay(100).fadeIn(100);
        $("#login-form").fadeOut(100);
        $('#login-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#login-form').bootstrapValidator({
        feedbackIcons: {/*input状态样式图片*/
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {/*验证：规则*/
            username: {//验证input项：验证规则
                validators: {
                    notEmpty: {//非空验证：提示消息
                        message: '用户名不能为空'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    }
                }
            },
        }
    })

    $("#login-submit").on("click", function(e){
        e.preventDefault();
        $.ajax({
            type: 'POST',
            url: '/user/ajax_login',
            data: {
                username: $('#login-username').val(),
                password: $('#login-password').val()
            },
            success: function (msg) {
                if (msg == "username doesn't exist"){
                    $('#login-feedback').text("用户名不存在");
                    return;
                }
                else if (msg == "password doesn't match") {
                    $('#login-feedback').text("密码错误");
                    return;
                }
                else {
                    if (msg == "need activate") {
                        $('#login-feedback').css("color","#3c763d");
                        $('#login-feedback').html("需要激活，将在<span id=\"mes\">3</span> 秒后跳转至激活页面");
                        i = 2;
                        interval = setInterval("activateCount()", 1000);
                    }
                    else if (msg == "log in") {
                        $('#login-feedback').css("color","#3c763d");
                        $('#login-feedback').html("登录成功，将在<span id=\"mes\">3</span> 秒后跳转至首页");
                        i = 2;
                        interval = setInterval("homeCount()", 1000);
                    }
                    else alert(msg);
                }
            },
            error: function (msg) {
                alert("出现错误了:" + msg.text);
                return;
            }
        })
    });
    $('#register-form')
        .bootstrapValidator({
            feedbackIcons: {/*input状态样式图片*/
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {/*验证：规则*/
                username: {//验证input项：验证规则
                    validators: {
                        notEmpty: {//非空验证：提示消息
                            message: '用户名不能为空'
                        },
                        stringLength: {
                            min: 6,
                            max: 16,
                            message: '用户名长度必须在6到16之间'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_]+$/,
                            message: '用户名只能由数字、大小写字母和下划线组成'
                        },
                        threshold: 6,
                        remote: {
                            type: 'POST',
                            url: '/user/ajax_exist',
                            data: {
                                username: $('#register-username').val()
                            },
                            message: '该用户名已注册',
                            delay : 3000
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        stringLength: {
                            min: 6,
                            max: 30,
                            message: '密码长度必须在6到16之间'
                        },
                        different: {//不能和用户名相同
                            field: 'username',         //需要进行比较的input name值
                            message: '不能和用户名相同'
                        },
                        identical: {//相同
                            field: 'confirmpassword', //需要进行比较的input name值
                            message: '两次密码不一致'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_]+$/,
                            message: '密码只能由数字、大小写字母和下划线组成'
                        }
                    }
                },
                confirmpassword: {
                    validators: {
                        identical: {//相同
                            field: 'password', //需要进行比较的input name值
                            message: '两次密码不一致'
                        }
                    }
                },
                email: {
                    validators: {
                        notEmpty: {
                            message: '邮箱不能为空'
                        },
                        emailAddress: {
                            message: '请输入正确的邮箱地址',
                        }
                    }
                }
            },
        }).submit(function (e) {
        $.ajax({
            type: 'POST',
            data: {
                username: $('#register-username').val(),
            },
            success: function (exist) {
                if (exist == "{\"valid\":false}") {
                    $('#login-feedback').text("用户名已存在");
                    e.preventDefault();
                }
                else return true;
            },
            error: function (msg) {
                e.preventDefault();
                alert("出现错误了:" + msg);
            }
        })
    })
    $("#register-submit").on("click", function(e){
        e.preventDefault();
        $.ajax({
            type: 'POST',
            url: '/user/ajax_register',
            data: {
                username: $('#register-username').val(),
                password: $('#register-password').val(),
                email: $('#register-email').val()
            },
            success: function (msg) {
                if (msg == "username doesn't exist"){
                    $('#login-feedback').text("用户名不存在");
                    return;
                }
                else if (msg == "password doesn't match") {
                    $('#login-feedback').text("密码错误");
                    return;
                }
                else {
                    if (msg == "need activate") {
                        $('#login-feedback').css("color","#3c763d");
                        $('#login-feedback').html("需要激活，将在<span id=\"mes\">3</span> 秒后跳转至激活页面");
                        i = 2;
                        interval = setInterval("activateCount()", 1000);
                    }
                    else if (msg == "log in") {
                        $('#login-feedback').css("color","#3c763d");
                        $('#login-feedback').html("登录成功，将在<span id=\"mes\">3</span> 秒后跳转至首页");
                        i = 2;
                        interval = setInterval("homeCount()", 1000);
                    }
                    else alert(msg);
                }
            },
            error: function (msg) {
                alert("出现错误了:" + msg.text);
                return;
            }
        })
    });
});

