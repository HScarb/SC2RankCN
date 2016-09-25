<%--
  Created by IntelliJ IDEA.
  User: Scarb
  Date: 9/25/2016
  Time: 1:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .accordion-group{
        margin-left:auto;
        margin-right:auto;
        width:800px;
    }
</style>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SC2RankCN</title>
    <link href="<%=request.getContextPath()%>/static/js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/static/js/jQuery/jquery-2.1.4.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/bootstrap/js/bootstrap-paginator.min.js"></script>
    <%--<style src="<%=request.getContextPath()%>/static/css/style.css"></style>--%>

    <div class="navbar">
        <div class="navbar-inner">
            <div class="container">
                <a class="btn btn-navbar" data-toggle="collapse" data-target=".navbar-responsive-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <a class="brand" href="#">SC2Rank</a>
                <div class="nav-collapse collapse navbar-responsive-collapse">
                    <ul class="nav">
                        <li id="mainpage"><a href="<%=request.getContextPath()%>/">首页</a></li>
                        <li id="blog"><a href="http://scarb.website/wordpress/">博客</a></li>
                        <li id="about" class="active"><a href="<%=request.getContextPath()%>/about">关于</a></li>
                    </ul>
                    <ul class="nav pull-right">
                        <li><a href="#"><%=request.getContextPath()%></a></li>
                        <li class="divider-vertical"></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Action</a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something else here</a></li>
                                <li class="divider"></li>
                                <li><a href="#">Separated link</a></li>
                            </ul>
                        </li>
                    </ul>
                    <form class="navbar-search pull-right" action="">
                        <input type="text" class="search-query span2" placeholder="Search">
                    </form>
                </div><!-- /.nav-collapse -->
            </div>
        </div><!-- /navbar-inner -->
    </div>
</head>

<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
<body>
    <div>
        <div class="accordion-group">
            <div class="accordion-heading">
                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
                    作者
                </a>
            </div>
            <div id="collapseOne" class="accordion-body in collapse" style="height: auto;">
                <div class="accordion-inner">
                    <a href="http://www.battlenet.com.cn/sc2/zh/profile/963462/1/%E9%87%91%E7%94%B2%E8%99%AB/">金甲虫</a>
                    <br>
                    在校大学生，国服大师T。
                    正边学边做SC2Rank网站。
                </div>
            </div>
        </div>
    </div>
</body>
</html>
