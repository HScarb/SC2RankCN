<%--
  Created by IntelliJ IDEA.
  User: Scarb
  Date: 9/25/2016
  Time: 3:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row-fluid">
    <div class="span12">

        <form id="form1">
            <table class="table table-striped tabel-hover" id = 'tableResult'>
                <thead>
                <tr>
                    <th>排名</th>
                    <th>组别</th>
                    <th>层级</th>
                    <th>种族</th>
                    <th>角色名</th>
                    <th>MMR</th>
                    <th>分数</th>
                    <th>胜利</th>
                    <th>失败</th>
                    <th>胜率</th>
                    <th>加入</th>
                    <th>上次游戏</th>
                    <th>更新</th>
                </tr>
                </thead>
                <tbody id="tableBody">
                </tbody>
            </table>
            <!-- 底部分页按钮 -->
            <div id="bottomTab"></div>
        </form>
    </div>
</div>