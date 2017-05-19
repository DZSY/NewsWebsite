<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>新新新闻网-首页</title>
    <link rel='stylesheet prefetch' href='css/bootstrap.min.css'>
    <link rel="stylesheet" href="/css/style.css">
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
            <li>
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-search"></i>  搜索  <span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <form action="" id="searchForm" name="searchForm" method=post>
                        <input type="text" id="searchItem" name="searchItem" class="form-control" placeholder="请输入关键词" />
                        <li><a onclick="window.searchForm.action='/searchTitle/1';searchForm.submit()">在标题中搜索</a></li>
                        <li><a onclick="window.searchForm.action='/searchBody/1';searchForm.submit()">在正文中搜索</a></li>
                    </form>
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
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src='js/bootstrap.min.js'></script>
<script src="js/nav.js" type="text/javascript"></script>
</body>
</html>