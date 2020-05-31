<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>考试时间控制</title>
    <script type="text/javascript " src="${ctx}/public/js/jquery.min.js"></script>
    <script type="text/javascript " src="${ctx}/public/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${ctx}/public/css/bootstrap.css">
</head>
<body style="padding: 1%;">
<form action="/test/timeControl" method="post" class="form-horizontal" style="width: 60%;">
    <input type="hidden" name="uid" value="${user.id}"/>
    <input type="hidden" name="createTime" id="createTime"/>
    <div class="form-group">
        <label class="col-sm-2 control-label">开始日期</label>
        <div class="col-sm-10">
            <input class="form-control" type="date" name="startTime" id="startTime"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">结束日期</label>
        <div class="col-sm-10">
            <input class="form-control" type="date" name="endTime" id="endTime"/>

        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-10 col-sm-offset-2">
            <input class="btn btn-info" type="submit" value="提交" id="submit">
        </div>
    </div>
</form>
</body>
<script>
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();

    $(document).ready(function () {
        $("#submit").click(function () {
            $("#createTime").val(year + "-" + month + "-" + day);
        });

    });
</script>
</html>

