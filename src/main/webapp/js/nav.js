/**
 * Created by positif on 19/05/2017.
 */
var searchTitleBegin = function () {
    window.searchForm.action='/searchTitle/1';
    window.searchForm.submit();
}

var searchBodyBegin = function () {
    window.searchForm.action='/searchBody/1';
    window.searchForm.submit();
}

var searchTitle = function (item, page) {
    $("#searchItem").val(item)
    window.searchForm.action='/searchTitle/'+page;
    window.searchForm.submit();
}

var searchBody = function (item, page) {
    $("#searchItem").val(item)
    window.searchForm.action='/searchBody/'+page;
    window.searchForm.submit();
}

$(document).ready(function () {

    $.ajax({
        type: 'POST',
        url: '/isOnline',
        success: function (msg) {
            if (msg == "yes")
            {
                $('#sidebar-wrapper').html(
                    '<ul class="nav sidebar-nav">' +
                    '<li class="sidebar-brand"><a href="#">新新新闻网</a></li>' +
                    '<li><a href="/home"><i class="glyphicon glyphicon-home"></i>  首页  </a></li>' +
                    '<li><a href="/column"><i class="glyphicon glyphicon-th-list"></i>  新闻栏目  </a></li>' +
                    '<li><a href="/follow"><i class="glyphicon glyphicon-star"></i>  关注栏目  </a></li>' +
                    '<li><a href="/latest"><i class="glyphicon glyphicon-globe"></i>  最新资讯  </a></li>' +
                    '<li><a href="/recommend"><i class="glyphicon glyphicon-heart"></i>  为您推荐  </a></li>' +
                    '<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-search"></i>  搜索  <span class="caret"></span></a><ul class="dropdown-menu" role="menu"><form action=\"\" id="searchForm" name="searchForm" method="post"><input type="text" id="searchItem" name="searchItem" class="form-control" placeholder="请输入关键词" /><li><a onclick=searchTitleBegin()>在标题中搜索</a></li> <li><a onclick=searchBodyBegin()>在正文中搜索</a></li></form></ul></li>' +
                    '<li><a href="/user/logout" ><i class="glyphicon glyphicon-log-out"></i>  注销  </a></li>' +
                    '<li><a href="/client"><i class="glyphicon glyphicon-phone"></i>  客户端下载  </a></li>' +
                    '</ul>'
                );
            }
            else {
                $('#sidebar-wrapper').html(
                    '<ul class="nav sidebar-nav">' +
                    '<li class="sidebar-brand"><a href="#">新新新闻网</a></li>' +
                    '<li><a href="/"><i class="glyphicon glyphicon-home"></i>  首页  </a></li>' +
                    '<li><a href="/column"><i class="glyphicon glyphicon-th-list"></i>  新闻栏目  </a></li>' +
                    '<li><a href="/latest"><i class="glyphicon glyphicon-globe"></i>  最新资讯  </a></li>' +
                    '<li><a href="/user"><i class="glyphicon glyphicon-user"></i>  注册/登录  </a></li>' +
                    '<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-search"></i>  搜索  <span class="caret"></span></a><ul class="dropdown-menu" role="menu"><form action="" id="searchForm" name="searchForm" method="post"><input type="text" id="searchItem" name="searchItem" class="form-control" placeholder="请输入关键词" /><li><a onclick=searchTitleBegin()>在标题中搜索</a></li> <li><a onclick=searchBodyBegin()>在正文中搜索</a></li></form></ul></li>' +
                    '<li><a href="/client"><i class="glyphicon glyphicon-phone"></i>  客户端下载  </a></li>' +
                    '</ul>'
                );
            }
        },
        error: function() {
            $('#sidebar-wrapper').html(
                '<ul class="nav sidebar-nav">' +
                '<li class="sidebar-brand"><a href="#">新新新闻网</a></li>' +
                '<li><a href="/"><i class="glyphicon glyphicon-home"></i>  首页  </a></li>' +
                '<li><a href="/column"><i class="glyphicon glyphicon-th-list"></i>  新闻栏目  </a></li>' +
                '<li><a href="/latest"><i class="glyphicon glyphicon-globe"></i>  最新资讯  </a></li>' +
                '<li><a href="/user"><i class="glyphicon glyphicon-user"></i>  注册/登录  </a></li>' +
                '<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-search"></i>  搜索  <span class="caret"></span></a><ul class="dropdown-menu" role="menu"><form action="" id="searchForm" name="searchForm" method="post"><input type="text" id="searchItem" name="searchItem" class="form-control" placeholder="请输入关键词" /><li><a onclick=searchTitleBegin()>在标题中搜索</a></li> <li><a onclick=searchBodyBegin()>在正文中搜索</a></li></form></ul></li>' +
                '<li><a href="/client"><i class="glyphicon glyphicon-phone"></i>  客户端下载  </a></li>' +
                '</ul>'
            );
        }
    });
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
