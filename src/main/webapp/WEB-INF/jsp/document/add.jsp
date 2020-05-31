<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>文件添加</title>
    <script type="text/javascript " src="${ctx}/public/js/jquery.min.js"></script>
    <script type="text/javascript " src="${ctx}/public/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${ctx}/public/css/bootstrap.css">
</head>
<body style="margin: 1%;">

<form action="/document/add" method="post" enctype="multipart/form-data" class="form-horizontal" style="width: 60%;">
    <input type="hidden" name="createTime" id="createTime"/>
    <div class="form-group">
        <label class="col-sm-2 control-label">标题</label>
        <div class="col-sm-10">
            <input class="form-control" name="title" rows="5" cols="20" placeholder="请输入要添加文件的标题"></input>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">描述</label>
        <div class="col-sm-10">
            <textarea class="form-control" name="remark" rows="5" cols="20" placeholder="请输入要添加文档的描述信息"></textarea>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">可下载否</label>
        <div class="col-sm-10">
            <input type="radio" name="isLoad" value="0"/>不可下载 &nbsp;&nbsp;
            <input type="radio" name="isLoad" value="1" checked="checked"/>可下载
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">发布人</label>
        <div class="col-sm-10">
            <select class="form-control" name="uId">
                <c:forEach items="${listUser}" var="user">
                    <option value="${user.id}">${user.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">适用专业</label>
        <div class="col-sm-10">
            <select class="form-control" name="majorId">
                <c:forEach items="${listMajor}" var="major">
                    <option value="${major.mid}">${major.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">上传文件</label>
        <div class="col-sm-10">
            <input class="btn" type="file" name="file"/>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <input class="btn btn-info" type="submit" value="提交" id="submit"/>
        </div>
    </div>
</form>

</body>
<script>
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var hour = "00" + date.getHours();
    hour = hour.substr(hour.length - 2);
    var minute = "00" + date.getMinutes();
    minute = minute.substr(minute.length - 2);
    var second = "00" + date.getSeconds();
    second = second.substr(second.length - 2);

    $(document).ready(function () {
        $("#createTime").val(year + "-" + month + "-" + day + hour + ":" + minute + ":" + second);

    });
</script>
</html>