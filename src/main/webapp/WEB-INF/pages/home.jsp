<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>新新新闻网-首页</title>
    <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>
    <link rel="stylesheet" href="/css/style.css">
    <script src="http://cdn.bootcss.com/jquery/1.11.0/jquery.min.js" type="text/javascript"></script>
    <script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>
</head>
<body>
<div id="wrapper">
    <div class="overlay"></div>

    <!-- Sidebar -->
    <nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
        <ul class="nav sidebar-nav">
            <li class="sidebar-brand">
                <a href="#">
                    新新新闻网
                </a>
            </li>
            <li>
                <a href="#"><i class="glyphicon glyphicon-home"></i>  首页  </a>
            </li>
            <li>
                <a href="/column"><i class="glyphicon glyphicon-th-list"></i>  新闻栏目  </a>
            </li>
            <li>
                <a href="/latest"><i class="glyphicon glyphicon-globe"></i>  最新资讯  </a>
            </li>
            <li>
                <a href="/user"><i class="glyphicon glyphicon-user"></i>  注册/登录  </a>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-search"></i>  搜索  <span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <input type="text" class="form-control" placeholder="请输入关键词" />
                    <li><a href="#">按标题搜索</a></li>
                    <li><a href="#">按内容搜索</a></li>
                </ul>
            </li>
            <li>
                <a href="#"><i class="glyphicon glyphicon-phone"></i>  客户端下载  </a>
            </li>
        </ul>
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
            🙄简历投了吗
        </div>
    </div>
    <!-- /#page-content-wrapper -->

</div>
<script type="text/javascript">
    $(document).ready(function () {
        var trigger = $('.hamburger'),
            overlay = $('.overlay'),
            isClosed = false;

        trigger.click(function () {
            hamburger_cross();
        });

        function hamburger_cross() {

            if (isClosed == true) {
                overlay.hide();
                trigger.removeClass('is-open');
                trigger.addClass('is-closed');
                isClosed = false;
            } else {
                overlay.show();
                trigger.removeClass('is-closed');
                trigger.addClass('is-open');
                isClosed = true;
            }
        }

        $('[data-toggle="offcanvas"]').click(function () {
            $('#wrapper').toggleClass('toggled');
        });
    });
</script>
</body>
</html>