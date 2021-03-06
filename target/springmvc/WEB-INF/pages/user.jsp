<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>新新新闻网-登录</title>
    <link rel='stylesheet' href='css/bootstrap.min.css'>
    <link rel='stylesheet' href='css/bootstrap-theme.min.css'>
    <link rel='stylesheet' href='css/bootstrapValidator.min.css'>
    <link rel="stylesheet" type="text/css" href="css/htmleaf-demo.css">
    <link rel="stylesheet" href="css/style.css">
    <!--[if IE]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <![endif]-->
</head>
<body>

<div id="wrapper">
    <div class="overlay"></div>

    <!-- Sidebar -->
    <nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
    </nav>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <button type="button" class="hamburger is-closed animated fadeInLeft" data-toggle="offcanvas">
            <span class="hamb-top"></span>
            <span class="hamb-middle"></span>
            <span class="hamb-bottom"></span>
        </button>
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <div class="panel panel-login">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-6">
                                    <a href="#" class="active" id="login-form-link">登录</a>
                                </div>
                                <div class="col-xs-6">
                                    <a href="#" id="register-form-link">注册</a>
                                </div>
                            </div>
                            <hr>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form id="login-form" action="/user/login" data-toggle="validator" method="post" role="form" style="display: block;">
                                        <div class="form-group">
                                            <input type="text" name="username" id="login-username" tabindex="1" class="form-control" placeholder="用户名" data-minlength="6" value="">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" name="password" id="login-password" tabindex="2" class="form-control" placeholder="密码">
                                        </div>
                                        <div class="form-group text-center">
                                            <input type="checkbox" tabindex="3" class="" name="remember" id="remember">
                                            <label for="remember">记住密码</label>
                                        </div>
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-sm-6 col-sm-offset-3">
                                                    <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="登录">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-lg-12">
                                                    <div class="text-center">
                                                        <span id="login-feedback" style="color: #a94442; font-family: Arial; font-size: medium"></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                    <form id="register-form" action="/user/register" data-toggle="validator" method="post" role="form" style="display: none;">
                                        <div class="form-group">
                                            <input type="text" name="username" id="register-username" tabindex="1" class="form-control" placeholder="请输入6-16位用户名，由数字、大小写字母和下划线组成" data-minlength="6" value="">
                                        </div>
                                        <div class="form-group">
                                            <input type="email" name="email" id="register-email" tabindex="1" class="form-control" placeholder="请输入邮箱" value="">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" name="password" id="register-password" tabindex="2" class="form-control" placeholder="输入密码" data-minlength="6">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" name="confirmpassword" id="register-confirm-password" tabindex="2" class="form-control" placeholder="再次输入密码" data-match="#register-password" data-match-error="Whoops, these don't match">
                                        </div>
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-sm-6 col-sm-offset-3">
                                                    <input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="注册">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-lg-12">
                                                    <div class="text-center">
                                                        <span id="register-feedback" style="color: #a94442; font-family: Arial; font-size: medium"></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /#page-content-wrapper -->

</div>
<!-- /#wrapper -->

<script src="js/jquery.min.js" type="text/javascript"></script>
<script>window.jQuery || document.write('<script src="js/jquery.min.js"><\/script>')</script>

<script src="js/bootstrapvalidator.min.js" type="text/javascript"></script>

<script src='js/bootstrap.min.js'></script>
<script src="js/user.js" type="text/javascript"></script>
<script src="js/nav.js" type="text/javascript"></script>
</body>
</html>