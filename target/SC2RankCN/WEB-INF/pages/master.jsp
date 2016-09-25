<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp"/>
<body>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">

<%--组别选项条--%>
<ul id="league-nav" class="nav nav-pills">
    <li><a href="<%=request.getContextPath()%>/">全部</a></li>
    <li><a href="<%=request.getContextPath()%>/grandmaster"><img src="<%=request.getContextPath()%>/images/grade7.png">宗师</a></li>
    <li class="active"><a href="<%=request.getContextPath()%>/master"><img src="<%=request.getContextPath()%>/images/grade6.png">大师</a></li>
    <li><a href="<%=request.getContextPath()%>/diamond"><img src="<%=request.getContextPath()%>/images/grade5.png">钻石</a></li>
    <li><a href="<%=request.getContextPath()%>/platinum"><img src="<%=request.getContextPath()%>/images/grade4.png">白金</a></li>
    <li><a href="<%=request.getContextPath()%>/gold"><img src="<%=request.getContextPath()%>/images/grade3.png">黄金</a></li>
    <li><a href="<%=request.getContextPath()%>/silver"><img src="<%=request.getContextPath()%>/images/grade2.png">白银</a></li>
    <li><a href="<%=request.getContextPath()%>/bronze"><img src="<%=request.getContextPath()%>/images/grade1.png">青铜</a></li>
    <li><a href="<%=request.getContextPath()%>/noleague"><img src="<%=request.getContextPath()%>/images/grade0.png">未分组</a></li>
</ul>

<jsp:include page="tableHead.jsp"/>

<script src="<%=request.getContextPath()%>/js/showLeague.js"></script>
<script type="text/javascript">
    //渲染完就执行
    $(function() {

        //生成底部分页栏
        $('#bottomTab').bootstrapPaginator(options);

        buildTable("MASTER",1,PAGESIZE);//默认空白查全部
    });
</script>
</body>
</html>