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
    </nav>
    <div id="page-content-wrapper">
        <button type="button" class="hamburger is-closed animated fadeInLeft" data-toggle="offcanvas">
            <span class="hamb-top"></span>
            <span class="hamb-middle"></span>
            <span class="hamb-bottom"></span>
        </button>
        <div class="container">
            <table width="100%">
                <tr width="100%">
                    <td>
                        <h4>${username}, 以下是为您推荐的内容:</h4>
                    </td>
                </tr>
            </table>
            <table class="table">
                <thead>
                <tr>
                    <th width="10%">#</th>
                    <th width="70%">新闻标题</th>
                    <th width="20%">发布时间
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items ="${requestScope.list}" var= "news" varStatus="status">
                    <tr>
                        <td width="10%">${status.index + 1}</td>
                        <td width="70%">
                            <a href="/news/${news["newsID"]}"
                               onmouseover="this.style.cssText='color:#000000'"
                               onmouseleave="this.style.cssText='color: #616161;'"
                               style="color: #616161;">
                                    ${news["newsTitle"]}
                            </a>
                        </td>
                        <td width="20%">
                                ${news["newsTime"]}
                        </td>
                    </tr>
                </c:forEach >
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
<script src="../js/jquery.min.js" type="text/javascript"></script>
<script src='../js/bootstrap.min.js'></script>
<script src="../js/nav.js" type="text/javascript"></script>
</html>
