<%--
  Created by IntelliJ IDEA.
  User: Scarb
  Date: 9/27/2016
  Time: 5:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>注册账号</title>
    <link href="<%=request.getContextPath()%>/static/js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/static/js/jQuery/jquery-2.1.4.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/registerCheck.js" ></script>


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
                        <li id="mainpage" class="active"><a href="<%=request.getContextPath()%>/">首页</a></li>
                        <li id="blog"><a href="http://scarb.website/">博客</a></li>
                        <li id="about"><a href="<%=request.getContextPath()%>/about">关于</a></li>
                    </ul>
                    <ul class="nav pull-right">
                        <li><a href="#"></a></li>
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
<body>
<div class="container">
    <div id="reg" class="span8">
        <h2 class="form-signin-heading">注册新用户</h2>
        <hr>
        <FORM name="formUser" class="form-signin" action="<%=request.getContextPath()%>/register/succeed"  method=post>
            <BR>
            <TABLE align=center border=0>
                <TBODY>
                <TR>
                    <TD align=right><STRONG>用户名:</STRONG></TD>
                    <TD ><INPUT id="username" type="text" onBlur="checkUserName(this)" name="username" placeholder="User name">
                        <SPAN id="username_notice" >*</SPAN></TD>
                </TR>
                <TR>
                    <TD align=right><STRONG>邮箱:</STRONG></TD>
                    <TD><INPUT id="email" type="text" onBlur="checkEmail(this)" name="email" placeholder="Email address">
                        <SPAN id=email_notice >*</SPAN></TD>
                </TR>
                <TR>
                    <TD align=right><STRONG>密码:</STRONG></TD>
                    <TD><INPUT id="password" type="password" onBlur="checkPassword(this)"
                               onkeyup="checkIntensity(this.value)" name="password" placeholder="Password">
                        <SPAN id=password_notice >*</SPAN></TD>
                </TR>
                <TR>
                    <TD align=right><STRONG>密码强度:</STRONG></TD>
                    <TD><TABLE cellSpacing=0 cellPadding=1 width=145 border=0>
                        <TBODY>
                        <TR align=middle>
                            <TD id=pwd_lower width="33%">弱</TD>
                            <TD id=pwd_middle width="33%">中</TD>
                            <TD id=pwd_high width="33%">强</TD>
                        </TR>
                        </TBODY>
                    </TABLE></TD>
                </TR>
                <TR>
                    <TD align=right><STRONG>确认密码:</STRONG></TD>
                    <TD><INPUT id="conform_password" type="password" onBlur="checkConformPassword(this)" name="confirm_password" placeholder="Repeat password">
                        <SPAN id=conform_password_notice >*</SPAN></TD>
                </TR>
                <TR>
                    <TD> </TD>
                    <TD><LABEL>
                        <INPUT type="checkbox" id="agreement" onclick="checkAgreement(this)">
                        <B>我已看过并接受《<a href="#">用户协议</a>》<SPAN id=agreement_notice >*</SPAN></B></LABEL></TD>
                </TR>
                <TR>
                    <TD><INPUT type=hidden value=act_register name=act></TD>
                    <%--<TD><input type=submit value=确认注册    name="Submit1" class="anniu" disabled></TD>--%>
                    <td><button type="submit" class="btn btn-large btn-primary anniu" name="Submit1" disabled>确认注册</button></td>
                </TR>
                <TR>
                    <TD colSpan=2> </TD>
                </TR>
                </TBODY>
            </TABLE>
        </FORM>
    </div>
</div>
</body>
</html>