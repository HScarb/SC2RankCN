<%--
  Created by IntelliJ IDEA.
  User: Scarb
  Date: 10/1/2016
  Time: 8:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String url = request.getRequestURL().toString();
    url = url.substring(0, url.indexOf('/', url.indexOf("//") + 2));
    String context = request.getContextPath();
    url += context;
    application.setAttribute("ctx", url);
%>
<style type="text/css">
    body {
        /*padding-top: 40px;*/
        padding-bottom: 40px;
        background-color: #f5f5f5;
    }
    .form-signin{
        max-width: 450px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
        -moz-border-radius: 5px;
        border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
        -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
        box-shadow: 0 1px 2px rgba(0,0,0,.05);
    }
    .form-signin .form-signin-heading,
    .form-signin .checkbox {
        margin-bottom: 10px;
    }
    .form-signin input[type="text"],
    .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
    }
</style>
<html>
<jsp:include page="head.jsp"/>

<body>
    <div class="container">

        <form class="form-signin" action="${ctx}/checkLogin.do" method="post">
            <h2 class="form-signin-heading">登录</h2>
            <input type="text" class="input-block-level" placeholder="Email address" name="username">
            <input type="password" class="input-block-level" placeholder="Password" name="password">
            <label class="checkbox">
                <input type="checkbox" value="remember-me"> 记住我
            </label>
            <button class="btn btn-large btn-primary" type="submit">登录</button>
            <span><font color="red" id="error">  ${errorInfo}</font></span>
        </form>

    </div>
</body>
</html>
