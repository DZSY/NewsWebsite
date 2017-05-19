<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: positif
  Date: 15/05/2017
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>最新资讯</title>
    <meta charset="UTF-8">
    <link rel='stylesheet' href='../css/bootstrap.min.css'>
    <link rel="stylesheet" href="../css/style.css">
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
                <a href="#"><i class="glyphicon glyphicon-home"></i>首页</a>
            </li>
            <li>
                <a href="/column"><i class="glyphicon glyphicon-th-list"></i>新闻栏目</a>
            </li>
            <li>
                <a href="#"><i class="glyphicon glyphicon-globe"></i>最新资讯</a>
            </li>
            <li>
                <a href="/user"><i class="glyphicon glyphicon-user"></i>注册/登录</a>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-search"></i>搜索<span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <input type="text" class="form-control" placeholder="请输入关键词" />
                    <li><a href="#">按标题搜索</a></li>
                    <li><a href="#">按内容搜索</a></li>
                </ul>
            </li>
            <li>
                <a href="#"><i class="glyphicon glyphicon-phone"></i>客户端下载</a>
            </li>
        </ul>
    </nav>
    <div id="page-content-wrapper">
        <button type="button" class="hamburger is-closed animated fadeInLeft" data-toggle="offcanvas">
            <span class="hamb-top"></span>
            <span class="hamb-middle"></span>
            <span class="hamb-bottom"></span>
        </button>
        <div class="container">
            <table class="table">
                <thead>
                <tr>
                    <th width="10%">#</th>
                    <th width="60%">新闻标题</th>
                    <th width="30%">发布时间
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items ="${requestScope.list}" var= "news" varStatus="status">
                    <tr>
                        <td width="10%">${start + status.index + 1}</td>
                        <td width="60%">
                            <a href="/news/${news["newsID"]}"
                               onmouseover="this.style.cssText='color:#000000'"
                               style="color: #616161;">
                                    ${news["newsTitle"]}
                            </a>
                        </td>
                        <td width="30%">
                                ${news["newsTime"]}
                        </td>
                    </tr>
                </c:forEach >
                </tbody>
            </table>
            <nav style="text-align: center">
                <ul class="pagination">
                    <li class=${page==1?"disabled":""}><a href="/latest/${page-1}" aria-label="Previous">&laquo;</a></li>
                    <c:forEach var="i" begin="${page-2 < 1? 1 : page-2}" end="${page+2 > totalPage ? totalPage : page+2}" varStatus="status">
                        <li><a href="/latest/${begin + status.index}">${begin + status.index}</a></li>
                    </c:forEach >
                    <li class=${page==totalPage?"disabled":""}><a href="/latest/${page+1}" aria-label="Next">&raquo;</a></li>
                </ul>
            </nav>
        </div>
    </div>
</div>
</body>
<script src="../js/jquery.min.js" type="text/javascript"></script>
<script src='../js/bootstrap.min.js'></script>
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
</html>
