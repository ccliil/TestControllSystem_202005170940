<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.cdtu.entity.ChioceTest" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>文件数据展示</title>
    <script type="text/javascript " src="${ctx}/public/js/jquery.min.js"></script>
    <script type="text/javascript " src="${ctx}/public/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${ctx}/public/css/bootstrap.css">
</head>
<body style="padding: 1%;">
<form action="/teacher/addTest" method="post" class="form-horizontal" style="width: 60%;">
    <input  type="hidden" name="uid" value="${user.id}"/>
    <input  type="hidden" name="majorid" value="${user.majorid}"/>
    <input  type="hidden" name="createTime" id="createTime"/>

    <div class="form-group">
        <label class="col-sm-2 control-label">试卷名称</label>
        <div class="col-sm-10">
            <input class="form-control" type="text" name="testName"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">选择题</label>
        <div class="col-sm-10">
            <select class="form-control" name="chioceList" multiple="multiple" size="4" id="chioce">
                <c:forEach items="${cList}" var="chioce">
                    <option value="${chioce.cid}">${chioce.account}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">判断题</label>
        <div class="col-sm-10">
            <select class="form-control" name="judgeList" multiple="multiple" size="4" id="judge">
                <c:forEach items="${jList}" var="judge">
                    <option value="${judge.jid}">${judge.account}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">填空题</label>
        <div class="col-sm-10">
            <select class="form-control" name="vacantList" multiple="multiple" size="4" id="vacant">
                <c:forEach items="${vList}" var="vacant">
                    <option value="${vacant.vid}"> ${vacant.account}</option>
                </c:forEach>
            </select>
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
        $("#submit").click(function () {
            $("#createTime").val(year + "-" + month + "-" + day + hour + ":" + minute + ":" + second);
        });

    });
</script>
</html>