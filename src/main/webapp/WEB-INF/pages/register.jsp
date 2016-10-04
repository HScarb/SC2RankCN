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
    #pwd-strength{
        margin-bottom: 10px
    }
</style>
<html>
<jsp:include page="head.jsp"/>

<body>
<div class="container">
    <form name="formUser" class="form-signin" action="register/succeed" method="post">
        <h2 class="from-signin-heading">注册</h2>
        <input id="username" type="text" onblur="checkUserName(this)" name="username" placeholder="用户名">
        <span id="username_notice">*</span>
        <%--邮箱--%>
        <INPUT id="email" type="text" onBlur="checkEmail(this)" name="email" placeholder="邮箱">
        <SPAN id=email_notice >*</SPAN>
        <%--密码--%>
        <INPUT id="password" type="password" onBlur="checkPassword(this)"
               onkeyup="checkIntensity(this.value)" name="password" placeholder="密码">
        <SPAN id=password_notice >*</SPAN>
        <%--密码强度--%>
        <TABLE id="pwd-strength" cellSpacing=0 cellPadding=1 width=145 border=0 margin-bottom="10px">
            <TBODY>
            <TR align=middle>
                <TD id=pwd_lower width="33%">弱</TD>
                <TD id=pwd_middle width="33%">中</TD>
                <TD id=pwd_high width="33%">强</TD>
            </TR>
            </TBODY>
        </TABLE>
        <%--确认密码--%>
        <INPUT id="conform_password" type="password" onBlur="checkConformPassword(this)" name="confirm_password" placeholder="确认密码">
        <SPAN id=conform_password_notice >*</SPAN>
        <%--读过--%>
        <LABEL class="checkbox">
            <INPUT type="checkbox" id="agreement" onclick="checkAgreement(this)">
            <B>我已看过并接受《<a href="#">用户协议</a>》<SPAN id=agreement_notice >*</SPAN></B>
        </LABEL>
        <%--确认注册--%>
        <TD  ><INPUT type=hidden value=act_register name=act></TD>
        <TD  ><input type=submit value=确认注册    name="Submit1" class="btn btn-large btn-primary anniu" disabled></TD>
    </form>
<%--    <div id="reg" class="span12">
        <h2 class="form-signin-heading">注册新用户</h2>
        <hr>
        <FORM name="formUser" class="form-signin" action="<%=request.getContextPath()%>/register/succeed"  method=post>
            <BR>
            <TABLE>
                <TBODY>
                <TR>
                    <TD align=right class="span4"><STRONG>用户名:</STRONG></TD>
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
                    &lt;%&ndash;<TD><input type=submit value=确认注册    name="Submit1" class="anniu" disabled></TD>&ndash;%&gt;
                    <td><button type="submit" class="btn btn-large btn-primary anniu" name="Submit1" disabled>确认注册</button></td>
                </TR>
                <TR>
                    <TD colSpan=2> </TD>
                </TR>
                </TBODY>
            </TABLE>
        </FORM>
    </div>--%>
</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/registerCheck.js" ></script>
</body>
</html>