<%--
  Created by IntelliJ IDEA.
  User: Scarb
  Date: 9/25/2016
  Time: 12:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String url = request.getRequestURL().toString();
    url = url.substring(0, url.indexOf('/', url.indexOf("//") + 2));
    String context = request.getContextPath();
    url += context;
    application.setAttribute("ctx", url);
%>
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
                        <%--首页--%>
                        <c:choose>
                            <c:when test="${currentPage == 'index'}">
                                li id="about" class="active"><a href="<%=request.getContextPath()%>/index">首页</a></li>
                            </c:when>
                            <c:otherwise>
                                <li id="about"><a href="<%=request.getContextPath()%>/index">首页</a></li>
                            </c:otherwise>
                        </c:choose>
                        <%--留言板--%>
                        <c:choose>
                            <c:when test="${currentPage == 'comments'}">
                                li id="about" class="active"><a href="<%=request.getContextPath()%>/comments">留言板</a></li>
                            </c:when>
                            <c:otherwise>
                                <li id="about"><a href="<%=request.getContextPath()%>/comments">留言板</a></li>
                            </c:otherwise>
                        </c:choose>
                        <li id="blog"><a href="http://scarb.website/">博客</a></li>
                        <%--关于--%>
                        <c:choose>
                            <c:when test="${currentPage == 'about'}">
                                li id="about" class="active"><a href="<%=request.getContextPath()%>/about">关于</a></li>
                            </c:when>
                            <c:otherwise>
                                <li id="about"><a href="<%=request.getContextPath()%>/about">关于</a></li>
                            </c:otherwise>
                        </c:choose>

                    </ul>
                    <ul class="nav pull-right">
                        <%--已登录--%>
                        <shiro:user>
                            <li id="principal"><a href="#"><shiro:principal/></a></li>
                            <li id="logout"><a href="logout">登出</a></li>
                        </shiro:user>
                        <%--未登录--%>
                        <shiro:guest>
                            <c:choose>
                                <c:when test="${currentPage == 'login'}">
                                    <li id="login" class="active"><a href="login">登录</a></li>
                                    <li id="register"><a href="register">注册</a></li>
                                </c:when>
                                <c:when test="${currentPage == 'register'}">
                                    <li id="login"><a href="login">登录</a></li>
                                    <li id="register" class="active"><a href="register">注册</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li id="login"><a href="login">登录</a></li>
                                    <li id="register"><a href="register">注册</a></li>
                                </c:otherwise>
                            </c:choose>
                        </shiro:guest>

                        <li class="divider-vertical"></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Action</a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something else here</a></li>
                                <li class="divider"></li>
                                <li><a href="#">Action</a></li>
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

<script>
//    function mainPageClicked() {
//        if ($("#mainpage").hasClass("active"))
//            return;
//        else
//        {
//            $("#mainpage").addClass("active");
//            $("#about").removeClass("active");
//            $("#blog").removeClass("active");
//        }
//    }
//    function aboutClicked() {
//        if ($("#about").hasClass("active"))
//            return;
//        else
//        {
//            $("#mainpage").removeClass("active");
//            $("#about").addClass("active");
//            $("#blog").removeClass("active");
//        }
//    }
//    $("#mainpage").bind("click", mainPageClicked);
//    $("#about").bind("click", aboutClicked);
//    document.getElementById("mainpage").onclick=mainPageClicked;
</script>