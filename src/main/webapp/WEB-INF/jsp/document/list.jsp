<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>文件数据展示</title>
    <script type="text/javascript " src="/js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript " src="/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/public/css/bootstrap.css">
</head>
<body style="margin: 1%;">
<form action="/document/list" method="post" class="form-inline">
    <div class="form-group">
        <select class="form-control"  name="find" id="findWay">
            <option value="uid">发布人</option>
            <option value="majorid">专业</option>
            <option value="filename">文件名</option>
        </select>
    </div>
    <div class="form-group">
        <select class="form-control"  name="value" id="majorValue">
            <c:forEach items="${listMajor}" var="major">
                <option value="${major.mid}">${major.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <select class="form-control"  name="value" id="userValue">
            <c:forEach items="${listUser}" var="user">
                <option value="${user.id}">${user.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <input class="form-control" type="text" name="value" placeholder="请输入你要查询到文件名" id="fileValue"/>
    </div>
    <div class="form-group">
        <input class="btn btn-info" type="submit" value="提交"/>
    </div>
</form>
<table class="table table-bordered table-striped table-hover">
    <tr>
        <td>标题</td>
        <td>文件描述</td>
        <td>文件名称</td>
        <td>文件发布人</td>
        <td>文件使用专业</td>
        <td>创建时间</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${list}" var="document">
        <tr>
            <td>${document.title}</td>
            <td>${document.remark}</td>
            <td>${document.dataName}</td>
            <td>${document.user.name}</td>
            <td>${document.major.name}</td>
            <td>${document.createTime}</td>
            <c:if test="${document.isLoad==1}">
                <c:if test="${document.dataName.indexOf('.mp4')>-1}">
                    <td><a href="/document/update?id=${document.id}">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
                            href="/document/delete?id=${document.id}">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
                            href="/document/downLoad?id=${document.id}">下载</a></td>
                </c:if>
                <c:if test="${document.dataName.indexOf('.mp4')==-1}">
                    <td><a href="/document/update?id=${document.id}">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
                            href="/document/delete?id=${document.id}">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
                            href="/document/lookUp?fid=${document.id}">在线查看</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
                            href="/document/downLoad?id=${document.id}">下载</a></td>
                </c:if>
            </c:if>
            <c:if test="${document.isLoad==0}">
                <c:if test="${document.dataName.indexOf('.mp4')>-1}">
                    <td><a href="/document/update?id=${document.id}">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
                            href="/document/delete?id=${document.id}">删除</a></td>
                </c:if>
                <c:if test="${document.dataName.indexOf('.mp4')==-1}">
                    <td><a href="/document/update?id=${document.id}">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
                            href="/document/delete?id=${document.id}">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
                            href="/document/lookUp?fid=${document.id}">在线查看</a></td>
                </c:if>
            </c:if>
        </tr>
    </c:forEach>
</table>


<nav aria-label="Page navigation">
    <ul class="pagination">
        <li>
            <a href="list?indexPage=1">首页</a>
        </li>
        <c:forEach begin="1" end="${pages}" var="p">
            <li>
                <a href="list?indexPage=${p}">${p}</a>
            </li>
        </c:forEach>
        <li>
            <a href="list?indexPage=${pages}">尾页</a>
        </li>
    </ul>
</nav>
</body>
<script>
    function changSelect() {
        if ("majorid" == $("#findWay").val()) {
            $("#userValue").attr("disabled", "disabled");
            $("#majorValue").removeAttr("disabled");
            $("#fileValue").attr("disabled", true);
        } else if ("uid" == $("#findWay").val()) {
            $("#majorValue").attr("disabled", "disabled");
            $("#userValue").removeAttr("disabled");
            $("#fileValue").attr("disabled", true);
        } else if ("filename" == $("#findWay").val()) {
            $("#fileValue").attr("disabled", false);
            $("#majorValue").attr("disabled", "disabled");
            $("#userValue").attr("disabled", "disabled");
        }
    }

    $(document).ready(function () {
        changSelect();
        $("#findWay").change(function () {
            changSelect();
        });
    });
</script>

</html>