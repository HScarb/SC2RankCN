/**
 * Created by Scarb on 9/24/2016.
 */
var PAGESIZE = 50;
var options = {
    currentPage: 1,  //当前页数
    totalPages: 10,  //总页数，这里只是暂时的，后头会根据查出来的条件进行更改
    size:"large",
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

function dateDiff(d1, d2) {
    var timeDiff = Math.abs(d1.getTime() - d2.getTime());
    if (timeDiff > 86400000)
    {
        return parseInt(timeDiff / 86400000) + "d";
    }
    else if (timeDiff > 3600000)
    {
        return parseInt(timeDiff / 3600000) + "h";
    }
    else
    {
        return parseInt(timeDiff / 60000) + "m";
    }
}

//生成表格
function buildTable(league,pageNumber,pageSize) {
    var url =  urlRootContext + "/listleague"; //请求的网址
    var reqParams = {'league':league, 'pageNumber':pageNumber,'pageSize':pageSize};//请求数据
    $(function () {
        $.ajax({
            size:"large",
            type:"POST",
            url:url,
            data:reqParams,
            async:false,
            dataType:"json",
            success: function(data){
                if(data.isError == false) {
                    // options.totalPages = data.pages;
                    var now = new Date();
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
                            buildTable(league,page,PAGESIZE);//默认每页最多10条
                        }
                    }
                    $('#bottomTab').bootstrapPaginator("setOptions",newoptions); //重新设置总页面数目
                    var dataList = data.dataList;

                    $("#tableBody").empty();//清空表格内容
                    if (dataList.length > 0 ) {
                        $(dataList).each(function(){//重新生成
                            var joinDate = new Date(Number(this.jointime) * 1000);
                            var lastPlayed = new Date(Number(this.lastplayedtime) * 1000);
                            var updateDate = new Date(Number(this.updatetime) * 1000);
                            $("#tableBody").append('<tr>');
                            $("#tableBody").append('<td>' + this.rank + '</td>');
                            // 组别和层级
                            if (this.league == 6) {
                                $("#tableBody").append('<td>' + '<img src="' + urlRootContext + '/images/grade7.png">' + '</td>');
                                $("#tableBody").append('<td>' + '</td>');
                            }
                            else if (this.league == 5) {
                                $("#tableBody").append('<td>' + '<img src="' + urlRootContext + '/images/grade6.png">' + '</td>');
                                if (this.tier == 0)
                                    $("#tableBody").append('<td>' + '<img src="' + urlRootContext + '/images/master1Icon.png">' + '</td>');
                                else if(this.tier == 1)
                                    $("#tableBody").append('<td>' + '<img src="' + urlRootContext + '/images/master2Icon.png">' + '</td>');
                                else if(this.tier == 2)
                                    $("#tableBody").append('<td>' + '<img src="' + urlRootContext + '/images/master3Icon.png">' + '</td>');
                                else
                                    $("#tableBody").append('<td>' + '</td>');
                            }
                            else if (this.league == 4) {
                                $("#tableBody").append('<td>' + '<img src="' + urlRootContext + '/images/grade5.png">' + '</td>');
                                if (this.tier == 0)
                                    $("#tableBody").append('<td>' + '<img src="' + urlRootContext + '/images/diamond1Icon.png">' + '</td>');
                                else if(this.tier == 1)
                                    $("#tableBody").append('<td>' + '<img src="' + urlRootContext + '/images/diamond2Icon.png">' + '</td>');
                                else if(this.tier == 2)
                                    $("#tableBody").append('<td>' + '<img src="' + urlRootContext + '/images/diamond3Icon.png">' + '</td>');
                                else
                                    $("#tableBody").append('<td>' + '</td>');
                            }
                            else if (this.league == 3) {
                                $("#tableBody").append('<td>' + '<img src="' + urlRootContext + '/images/grade4.png">' + '</td>');
                                if (this.tier == 0)
                                    $("#tableBody").append('<td>' + '<img src="' + urlRootContext + '/images/platinum1Icon.png">' + '</td>');
                                else if(this.tier == 1)
                                    $("#tableBody").append('<td>' + '<img src="' + urlRootContext + '/images/platinum2Icon.png">' + '</td>');
                                else if(this.tier == 2)
                                    $("#tableBody").append('<td>' + '<img src="' + urlRootContext + '/images/platinum3Icon.png">' + '</td>');
                                else
                                    $("#tableBody").append('<td>' + '</td>');
                            }
                            else if (this.league == 2) {
                                $("#tableBody").append('<td>' + '<img src="' + urlRootContext + '/images/grade3.png">' + '</td>');
                                if (this.tier == 0)
                                    $("#tableBody").append('<td>' + '<img src="' + urlRootContext + '/images/gold1Icon.png">' + '</td>');
                                else if(this.tier == 1)
                                    $("#tableBody").append('<td>' + '<img src="' + urlRootContext + '/images/gold2Icon.png">' + '</td>');
                                else if(this.tier == 2)
                                    $("#tableBody").append('<td>' + '<img src="' + urlRootContext + '/images/gold3Icon.png">' + '</td>');
                                else
                                    $("#tableBody").append('<td>' + '</td>');
                            }
                            else if (this.league == 1) {
                                $("#tableBody").append('<td>' + '<img src="' + urlRootContext + '/images/grade2.png">' + '</td>');
                                if (this.tier == 0)
                                    $("#tableBody").append('<td>' + '<img src="' + urlRootContext + '/images/silver1Icon.png">' + '</td>');
                                else if(this.tier == 1)
                                    $("#tableBody").append('<td>' + '<img src="' + urlRootContext + '/images/silver2Icon.png">' + '</td>');
                                else if(this.tier == 2)
                                    $("#tableBody").append('<td>' + '<img src="' + urlRootContext + '/images/silver3Icon.png">' + '</td>');
                                else
                                    $("#tableBody").append('<td>' + '</td>');
                            }
                            else if (this.league == 0) {
                                $("#tableBody").append('<td>' + '<img src="' + urlRootContext + '/images/grade1.png">' + '</td>');
                                if (this.tier == 0)
                                    $("#tableBody").append('<td>' + '<img src="' + urlRootContext + '/images/bronze1Icon.png">' + '</td>');
                                else if(this.tier == 1)
                                    $("#tableBody").append('<td>' + '<img src="' + urlRootContext + '/images/bronze2Icon.png">' + '</td>');
                                else if(this.tier == 2)
                                    $("#tableBody").append('<td>' + '<img src="' + urlRootContext + '/images/bronze3Icon.png">' + '</td>');
                                else
                                    $("#tableBody").append('<td>' + '</td>');
                            }
                            else
                                $("#tableBody").append('<td>' + '</td>');
                            // 种族
                            if(this.favoriterace == 'Terran')
                                $("#tableBody").append('<td>' + '<img src="' + urlRootContext + '/images/race_t.png">' + '</td>');
                            else if (this.favoriterace == 'Protoss')
                                $("#tableBody").append('<td>' + '<img src="' + urlRootContext + '/images/race_p.png">' + '</td>');
                            else if (this.favoriterace == 'Zerg')
                                $("#tableBody").append('<td>' + '<img src="' + urlRootContext + '/images/race_z.png">' + '</td>');
                            else if (this.favoriterace == 'Random')
                                $("#tableBody").append('<td>' + '<img src="' + urlRootContext + '/images/race_r.png">' + '</td>');
                            else
                                $("#tableBody").append('<td>' + '</td>');
                            // 名字
                            if (this.clantag != '')
                                $("#tableBody").append('<td> <a href="http://www.battlenet.com.cn/sc2/zh' + this.profilepath + '" target="_blank">' +
                                    '[' + this.clantag + ']' + this.name + '</a> </td>');
                            else
                                $("#tableBody").append('<td> <a href="http://www.battlenet.com.cn/sc2/zh' + this.profilepath + '" target="_blank">' +
                                    this.name + '</a> </td>');

                            $("#tableBody").append('<td>' + this.mmr + '</td>');
                            $("#tableBody").append('<td>' + this.points + '</td>');
                            $("#tableBody").append('<td>' + this.wins + '</td>');
                            $("#tableBody").append('<td>' + this.losses + '</td>');
                            $("#tableBody").append('<td>' + this.winrate + '%</td>');
                            $("#tableBody").append('<td>' + joinDate.toLocaleDateString() + '</td>');
                            $("#tableBody").append('<td>' + lastPlayed.toLocaleDateString() + '</td>');
                            $("#tableBody").append('<td>' + dateDiff(now, updateDate) + '</td>');
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

