<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>新新新闻网-首页</title>
    <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>
    <link rel="stylesheet" type="text/css" href="css/htmleaf-demo.css">
    <link rel="stylesheet" href="css/style.css">
    <!--[if IE]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <![endif]-->
</head>
<body>
<!-- <div class="htmleaf-container">
    <header class="htmleaf-header">
        <h1>超酷Bootstrap 3 隐藏滑动侧边栏菜单 <span>Awesome Bootstrap 3 Sidebar Navigation</span></h1>
        <div class="htmleaf-links">
            <a class="htmleaf-icon icon-htmleaf-home-outline" href="http://www.htmleaf.com/" title="jQuery之家" target="_blank"><span> jQuery之家</span></a>
            <a class="htmleaf-icon icon-htmleaf-arrow-forward-outline" href="http://www.htmleaf.com/jQuery/Menu-Navigation/201610124098.html" title="返回下载页" target="_blank"><span> 返回下载页</span></a>
        </div>
    </header>
</div> -->
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
                <a href="/follow"><i class="glyphicon glyphicon-star"></i>  关注栏目  </a>
            </li>
            <li>
                <a href="#"><i class="glyphicon glyphicon-globe"></i>  最新资讯  </a>
            </li>
            <li>
                <a href="/recommend"><i class="glyphicon glyphicon-heart"></i>  为您推荐  </a>
            </li>
            <li class="dropdown">
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
                <a href="/user/logout" ><i class="glyphicon glyphicon-log-out"></i>  注销  </a>
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
            丰富的内容
        </div>
    </div>
    <!-- /#page-content-wrapper -->

</div>
<!-- /#wrapper -->

<script src="js/jquery.min.js" type="text/javascript"></script>
<script src='js/bootstrap.min.js'></script>
<script src="js/nav.js" type="text/javascript"></script>
</body>
</html>