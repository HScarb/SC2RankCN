<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SC2RankCN</title>
    <link href="<%=request.getContextPath()%>/static/js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/static/js/jQuery/jquery-2.1.4.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/bootstrap/js/bootstrap-paginator.min.js"></script>
    <style type="text/css">
        #queryDiv {
            margin-right: auto;
            margin-left: auto;
            width:600px;
        }
        #textInput {
            margin-top: 10px;
        }
        #tableResult {
            margin-right: auto;
            margin-left: auto;
            width:1000px;
        }
        td {
            width:150px
        }
    </style>
</head>
<body>
<div class="navbar">
    <div class="navbar-inner">
        <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".navbar-responsive-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="#">Title</a>
            <div class="nav-collapse collapse navbar-responsive-collapse">
                <ul class="nav">
                    <li class="active"><a href="#">首页</a></li>
                    <li><a href="#">Link</a></li>
                    <li><a href="#">Link</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li class="divider"></li>
                            <li class="nav-header">Nav header</li>
                            <li><a href="#">Separated link</a></li>
                            <li><a href="#">One more separated link</a></li>
                        </ul>
                    </li>
                </ul>
                <form class="navbar-search pull-left" action="">
                    <input type="text" class="search-query span2" placeholder="Search">
                </form>
                <ul class="nav pull-right">
                    <li><a href="#">Link</a></li>
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
            </div><!-- /.nav-collapse -->
        </div>
    </div><!-- /navbar-inner -->
</div>
<div id = "queryDiv">
    <input id = "textInput" type="text" placeholder="请输入用户名" >
    <button id = "queryButton" class="btn btn-primary" type="button">查询</button>
</div>
<form id="form1">
    <table class="table table-bordered" id = 'tableResult'>
        <caption>查询结果</caption>
        <thead>
        <tr>
            <th>玩家ID</th>
            <th>组别</th>
            <th>种族</th>
            <th>角色名</th>
            <th>分数</th>
            <th>胜利</th>
            <th>失败</th>
            <th>胜率</th>
            <th>时间</th>
        </tr>
        </thead>
        <tbody id="tableBody">
        </tbody>
    </table>
    <!-- 底部分页按钮 -->
    <div id="bottomTab"></div>
</form>
<script type='text/javascript'>
    var PAGESIZE = 50;
    var options = {
        currentPage: 1,  //当前页数
        totalPages: 10,  //总页数，这里只是暂时的，后头会根据查出来的条件进行更改
        size:"normal",
        alignment:"center",
        itemTexts: function (type, page, current) {
            switch (type) {
                case "first":
                    return "<<";
                case "prev":
                    return "<";
                case "next":
                    return ">";
                case "last":
                    return ">>";
                case "page":
                    return  page;
            }
        },
        onPageClicked: function (e, originalEvent, type, page) {
            var param = $("#textInput").val(); //取内容
            buildTable(param,page,PAGESIZE);//默认每页最多10条
        }
    }

    //获取当前项目的路径
    var urlRootContext = (function () {
        var strPath = window.document.location.pathname;
        var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
        return postPath;
    })();


    //生成表格
    function buildTable(name,pageNumber,pageSize) {
        var url =  urlRootContext + "/list"; //请求的网址
        var reqParams = {'name':name, 'pageNumber':pageNumber,'pageSize':pageSize};//请求数据
        $(function () {
            $.ajax({
                type:"POST",
                url:url,
                data:reqParams,
                async:false,
                dataType:"json",
                success: function(data){
                    if(data.isError == false) {
                        // options.totalPages = data.pages;
                        var newoptions = {
                            currentPage: data.currentPage,  //当前页数
                            totalPages: data.pages== 0 ? 1 : data.pages,  //总页数
                            size:"normal",
                            alignment:"center",
                            itemTexts: function (type, page, current) {
                                switch (type) {
                                    case "first":
                                        return "<<";
                                    case "prev":
                                        return "<";
                                    case "next":
                                        return ">";
                                    case "last":
                                        return ">>";
                                    case "page":
                                        return  page;
                                }
                            },
                            onPageClicked: function (e, originalEvent, type, page) {
                                var name = $("#textInput").val(); //取内容
                                buildTable(name,page,PAGESIZE);//默认每页最多10条
                            }
                        }
                        $('#bottomTab').bootstrapPaginator("setOptions",newoptions); //重新设置总页面数目
                        var dataList = data.dataList;

                        $("#tableBody").empty();//清空表格内容
                        if (dataList.length > 0 ) {
                            $(dataList).each(function(){//重新生成
                                var date = new Date(Number(this.updatetime) * 1000);
                                $("#tableBody").append('<tr>');
                                $("#tableBody").append('<td>' + this.id + '</td>');
                                $("#tableBody").append('<td>' + this.league + '</td>');
                                $("#tableBody").append('<td>' + this.favoriterace + '</td>');
//                                $("#tableBody").append('<td>' + this.name + '</td>');
                                if (this.clantag != '')
                                    $("#tableBody").append('<td> <a href="http://www.battlenet.com.cn/sc2/zh' + this.profilepath + '" target="_blank">' +
                                            '[' + this.clantag + ']' + this.name + '</a> </td>');
                                else
                                    $("#tableBody").append('<td> <a href="http://www.battlenet.com.cn/sc2/zh' + this.profilepath + '" target="_blank">' +
                                            this.name + '</a> </td>');

                                $("#tableBody").append('<td>' + this.points + '</td>');
                                $("#tableBody").append('<td>' + this.wins + '</td>');
                                $("#tableBody").append('<td>' + this.losses + '</td>');
                                $("#tableBody").append('<td>' + this.winrate + '</td>');
                                $("#tableBody").append('<td>' + date.toLocaleDateString() + '</td>');
                                $("#tableBody").append('</tr>');
                            });
                        } else {
                            $("#tableBody").append('<tr><th colspan ="4"><center>查询无数据</center></th></tr>');
                        }
                    }else{
                        alert(data.errorMsg);
                    }
                },
                error: function(e){
                    alert("查询失败:" + e);
                }
            });
        });
    }

    //渲染完就执行
    $(function() {

        //生成底部分页栏
        $('#bottomTab').bootstrapPaginator(options);

        buildTable("",1,PAGESIZE);//默认空白查全部

        //创建结算规则
        $("#queryButton").bind("click",function(){
            var userName = $("#textInput").val();
            buildTable(userName,1,PAGESIZE);
        });
    });
</script>
</body>
</html>