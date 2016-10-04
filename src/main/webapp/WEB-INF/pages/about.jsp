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
<jsp:include page="head.jsp"/>

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
