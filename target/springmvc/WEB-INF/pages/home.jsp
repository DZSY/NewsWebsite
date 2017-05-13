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
                <a href="#"><i class="fa fa-fw fa-home"></i>  首页  </a>
            </li>
            <li>
                <a href="#"><i class="fa fa-fw fa-flag"></i>  新闻栏目  </a>
            </li>
            <li>
                <a href="#"><i class="fa fa-fw fa-trophy"></i>  最新资讯  </a>
            </li>
            <li>
                <a href="/user"><i class="fa fa-fw fa-user"></i>  注册/登录  </a>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-fw fa-search"></i>  搜索  <span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <input type="text" class="form-control" placeholder="请输入关键词" />
                    <li><a href="#">按标题搜索</a></li>
                    <li><a href="#">按内容搜索</a></li>
                </ul>
            </li>
            <li>
                <a href="#"><i class="fa fa-fw fa-mobile icon-large"></i>  客户端下载  </a>
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
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2">
                    <h1 class="page-header">这些名字，习近平从未忘记</h1>
                    <p>【学习进行时】身教重于言传。榜样，能够起到春风化雨、润物无声之功效。习近平曾为我们树立了哪些榜样？哪些名字，让习近平一直念兹在兹，从未忘记？新华社《学习进行时》原创品牌栏目“讲习所”今天推出文章，为您解读。
                        习近平说：“伟大时代呼唤伟大精神，崇高事业需要榜样引领。”
                        伟大的精神，不因岁月的流逝而褪色；榜样的名字，不因斗转星移而磨灭。</p>
                    <p>各级党员干部的所作所为，关系到党风、政声。他们之中的优秀者，正是党“全心全意为人民服务”宗旨的体现。
                        近日，习近平作出重要指示，要求广大党员、干部要向廖俊波同志学习，不忘初心、扎实工作、廉洁奉公，身体力行把党的方针政策落实到基层和群众中去，真心实意为人民造福。习近平称廖俊波以实际行动体现了对党忠诚、心系群众、忘我工作、无私奉献的优秀品质，无愧于“全国优秀县委书记”的称号。
                        如廖俊波这样的榜样，有很多很多。
                        2015年6月30日，习近平在北京亲切会见百余名优秀县委书记，并发表重要讲话，提出殷切希望。这是时隔二十载后，中央再次大规模表彰优秀县委书记。
                        再往前追溯，还有一连串闪光的名字：焦裕禄、谷文昌、王伯祥、孔繁森、李林森、邓平寿、牛玉儒、邹碧华……他们，习近平从未忘记。
                        为何是他们？因为这些优秀党员干部，以“天下大事必做于细”的态度，把群众安危冷暖挂心头，真心诚意地为老百姓办实事、做好事、解难事。
                        为何表彰他们？因为“表彰既是对大家工作成绩的肯定，也是为了形成学赶先进、见贤思齐的社会氛围，让更多党员领导干部学有榜样、赶有目标，更好为人民服务，更好干事创业”。
                        习近平说，心无百姓莫为官。办实每件事，赢得万人心。这就是榜样的力量。</p>
                </div>
            </div>
        </div>
    </div>
    <!-- /#page-content-wrapper -->

</div>
<!-- /#wrapper -->

<script src="script/jquery.min.js" type="text/javascript"></script>
<script>window.jQuery || document.write('<script src="script/jquery.min.js"><\/script>')</script>
<script src='script/bootstrap.min.js'></script>
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