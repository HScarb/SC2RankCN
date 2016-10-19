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


//生成表格
function buildComments(pageNumber,pageSize) {
    var url =  urlRootContext + "/commentlist"; //请求的网址
    var reqParams = {'pageNumber':pageNumber,'pageSize':pageSize};//请求数据
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
                            buildComments(page,PAGESIZE);//默认每页最多10条
                        }
                    }
                    $('#bottomTab').bootstrapPaginator("setOptions",newoptions); //重新设置总页面数目
                    var dataList = data.dataList;

                    $("#tableBody").empty();//清空表格内容
                    if (dataList.length > 0 ) {
                        $(dataList).each(function(){//重新生成
                            var date = new Date(Number(this.commentTime));
                            date = date.toLocaleDateString() + " " + date.toLocaleTimeString();
                            $("#commentTextArea").before(
                                '<li class="comment" id="' + this.commentId + '">' +
                                '<div class="comment-left">' + this.commentAuthorName + '</div>' +
                                '<div class="comment-body">' +
                                    '<div class="comment-head">' + this.commentAuthorName + '</div>' +
                                    '<p>' + this.commentContent +'</p>' +
                                    '<div class="comment-footer">' + date + '</div>' +
                                '</div>' +
                                '</div></li>' +
                                '<hr>'
                            );
                            /*$(".comment-list").append('<div class="comment-left"></div>');
                            $(".comment-list").append('<div class="comment-body">' + this.commentcontent +'</div>');
                            $(".comment-list").append('</div></li>');*/
                        });;
                    } else {
                        $("#tableBody").append('<tr><th colspan ="4"><center>没有评论</center></th></tr>');
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

