<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>新新新闻网-首页</title>
    <link rel='stylesheet prefetch' href='css/bootstrap.min.css'>
    <link rel="stylesheet" href="css/style.css">
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
            <div align="right" style="margin-top:10%; height: 90%; background-image: url(/images/home.jpg); background-size: 100%; min-height: 555px; background-repeat: no-repeat">
                <div style="font-size: 50px">欢迎使用</div>
                <div style="font-size: 30px">新新新闻网</div>
                <div style="font-size: small">点击左上角按钮滑出侧边栏</div>
                <div class="call-to-action">
                    <a href="/latest" class="btn btn-success">最新资讯</a>
                    <a href="/client" class="btn btn-info">客户端下载</a>
                </div>
            </div>
        </div>
    </div>

</div>
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src='js/bootstrap.min.js'></script>
<script src="js/nav.js" type="text/javascript"></script>
</body>
</html>