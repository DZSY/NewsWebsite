<%--
  Created by IntelliJ IDEA.
  User: positif
  Date: 11/05/2017
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>出现错误了</title>
</head>
<body>
<span>出现错误了，将在<span id="mes">5</span> 秒后跳转至首页，或</span><a href="/">点此跳转</a>
<script language="javascript" type="text/javascript">
    var i = 5;
    var interval = setInterval("fun()", 1000);
    function fun() {
        if (i == 0) {
            window.location.href = "/";
            clearInterval(interval);
        }
        document.getElementById("mes").innerHTML = i;
        i--;
    }
</script>
</body>
</html>
