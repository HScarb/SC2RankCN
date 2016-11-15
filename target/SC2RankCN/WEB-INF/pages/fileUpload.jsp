<%--
  Created by IntelliJ IDEA.
  User: Scarb
  Date: 11/15/2016
  Time: 11:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>用户上传图片页面</title>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<center>
    <form action="file/onefile"
          method="post" enctype="multipart/form-data">
        <input type="file" name="file" />
        <input type="submit" value="上 传" />
    </form>
    <h5>上传结果：</h5>
    <img alt="暂无图片" src="${fileUrl}" />
</center>
</body>
</html>

