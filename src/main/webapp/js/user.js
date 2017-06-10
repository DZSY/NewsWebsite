
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
    $("#login-submit").on("click", function(e){
        e.preventDefault();
        if ($('#login-username').val().length < 6 || $('#login-username').val().length > 16)
        {
            $('#login-feedback').html("用户名的长度需要在6-16之间");
        }
        else if (!(/^[a-z,A-Z,0-9,\u4e00-\u9fa5,_]+$/).test($('#login-username').val()))
        {
            $('#login-feedback').html("用户名只能由汉字、数字、大小写字母和下划线组成");
        }
        else if ($('#login-password').val().length < 6 || $('#login-password').val().length > 16)
        {
            $('#login-feedback').html("密码的长度需要在6-16之间");
        }
        else if (!(/^[a-z,A-Z,0-9,_]+$/).test($('#login-password').val()))
        {
            $('#login-feedback').html("密码只能由数字、大小写字母和下划线组成");
        }
        else {
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
                            $('#login-feedback').html("需要激活，将在<span id=\"mes\">2</span> 秒后跳转至激活页面");
                            i = 1;
                            interval = setInterval("activateCount()", 1000);
                        }
                        else if (msg == "log in") {
                            $('#login-feedback').css("color","#3c763d");
                            $('#login-feedback').html("登录成功，将在<span id=\"mes\">2</span> 秒后跳转至首页");
                            i = 1;
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
        }
    });
    $("#register-submit").on("click", function(e){
        e.preventDefault();
        if ($('#register-username').val().length < 6 || $('#register-username').val().length > 16)
        {
            $('#register-feedback').html("用户名的长度需要在6-16之间");
        }
        else if (!(/^[a-z,A-Z,0-9,\u4e00-\u9fa5,_]+$/).test($('#register-username').val()))
        {
            $('#register-feedback').html("用户名只能由汉字、数字、大小写字母和下划线组成");
        }
        else if ($('#register-password').val().length < 6 || $('#register-password').val().length > 16)
        {
            $('#register-feedback').html("密码的长度需要在6-16之间");
        }
        else if (!(/^[a-z,A-Z,0-9,_]+$/).test($('#register-password').val()))
        {
            $('#register-feedback').html("密码只能由数字、大小写字母和下划线组成");
        }
        else if ($('#register-password').val() == $('#register-username').val())
        {
            $('#register-feedback').html("用户名和密码不能相同");
        }
        else if ($('#register-password').val() != $('#register-confirm-password').val())
        {
            $('#register-feedback').html("两次密码不一致");
        }
        else if ($('#register-email').val().length == 0)
        {
            $('#register-feedback').html("电子邮箱不能为空");
        }
        else if (!(/^\w{3,}@\w+(\.\w+)+$/).test($('#register-email').val())){
            $('#register-feedback').html("请输入正确的邮箱地址");
        }
        else {
            $.ajax({
                type: 'POST',
                url: '/user/ajax_register',
                data: {
                    username: $('#register-username').val(),
                    password: $('#register-password').val(),
                    email: $('#register-email').val()
                },
                success: function (msg) {
                    if (msg == "success") {
                        $('#register-feedback').css("color", "#3c763d");
                        $('#register-feedback').html("注册成功，激活码已发送至您的邮箱<br>将在<span id=\"mes\">3</span> 秒后跳转至激活页");
                        i = 2;
                        interval = setInterval("activateCount()", 1000);
                    }
                    else if (msg == "already log in and need activate") {
                        $('#register-feedback').html("您已登录，无需注册，但需要激活<br>将在<span id=\"mes\">3</span> 秒后跳转至激活页");
                        i = 2;
                        interval = setInterval("activateCount()", 1000);
                    }
                    else if (msg == "already activated") {
                        $('#register-feedback').html("您已登录，无需注册，<br>将在<span id=\"mes\">3</span> 秒后跳转至首页");
                        i = 2;
                        interval = setInterval("homeCount()", 1000);
                    }
                    else if (msg == "username exist") {
                        $('#register-feedback').text("用户名已存在");
                    }
                    else if (msg == "email exist") {
                        $('#register-feedback').text("邮箱地址已存在");
                    }
                    else {
                        alert(msg);
                    }
                },
                error: function (msg) {
                    alert("出现错误了:" + msg.text);
                    return;
                }
            })
        }
    });
});

