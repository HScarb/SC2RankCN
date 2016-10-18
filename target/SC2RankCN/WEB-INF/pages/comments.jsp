<%--
  Created by IntelliJ IDEA.
  User: Scarb
  Date: 10/5/2016
  Time: 3:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp"/>
<style>
    .accordion-group{
        margin-left:auto;
        margin-right:auto;
        border-color:#ddd;
    }
    .accordion-heading{
        display:block;
        padding: 8px 15px;
        font-size:14px;
    }
    .accordion-inner{
        padding:15px;
    }
    li{
        list-style-type: none;
    }
    .comment-left{
        display: table-cell;
        vertical-align: top;
        padding-right: 10px;
    }
    .comment-body{
        width: 100%;
        display: table-cell;
        vertical-align: top;
    }
    .row{
        margin-bottom:20px;
    }
    #CommentWords{
        width: 99%;
        height:100px;
    }


</style>

<body>
<div class="container">
    <div class="accordion-group">
        <div class="accordion-heading">
            留言板
        </div>
        <div class="accordion-inner">
            <ul class="comment-list">
                <li class="comment">
                    <div class="comment-left">

                    </div>
                    <div class="comment-body">
                        <p>测试评论</p>
                    </div>
                </li>
                <%--提交评论--%>
                <li class="comment">
                    <div class="comment-left">
                        <img class="avatar-medium" src="http://sc2.cdn.haoest.com/Resources/Common/images/default-avatars/default.jpg" onerror="javascript:this.src='http://sc2.cdn.haoest.com/Resources/Common/images/default-avatars/default.jpg'">
                    </div>
                    <div class="comment-body">
                        <div class="row">
                            <form action="<%=path %>/leavecomment" class="" method="post"><input id="ParentId" name="ParentId" type="hidden" value="5"><input id="ReturnUrl" name="ReturnUrl" type="hidden" value="/">                    <div class="row">
                                <div class="col-sm-10 col-xs-12">
                                    <textarea class="form-control" rows="4" id="CommentWords" name="CommentWords" placeholder="在这里留言"></textarea>
                                </div>
                                <div class="col-sm-2 col-xs-12">
                                    <button id="submitButton" type="submit" class="btn btn-primary">提&nbsp;交</button>
                                </div>
                            </div>
                            </form>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>

<script type="text/javascript">
    //渲染完就执行
    $(function() {

        //生成底部分页栏
        $('#bottomTab').bootstrapPaginator(options);

        //创建结算规则
        $("#submitButton").bind("click",function(){
            var userName = $("#textInput").val();
            buildTable(userName,1,PAGESIZE);
        });
    });
</script>

</body>
</html>
