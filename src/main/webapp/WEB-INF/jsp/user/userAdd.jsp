<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>用户添加</title>
    <script type="text/javascript " src="${ctx}/public/js/jquery.min.js"></script>
    <script type="text/javascript " src="${ctx}/public/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${ctx}/public/css/bootstrap.css">
</head>
<body style="padding: 1%;">
<form action="/user/addUser" method="post" class="form-horizontal" style="width: 60%;">
    <div class="form-group">
        <label class="col-sm-2 control-label">用户名</label>
        <div class="col-sm-10">
            <input class="form-control" type="text" name="username" placeholder="请填写用户名"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">密码</label>
        <div class="col-sm-10">
            <input class="form-control" type="text" name="password" placeholder="请填写密码"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">姓名</label>
        <div class="col-sm-10">
            <input class="form-control" type="text" name="name" placeholder="请填写姓名"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">身份</label>
        <div class="col-sm-10">
            <select name="identify" class="form-control">
                <option value="学生"/>
                学生
                <option value="老师"/>
                老师
                <option value="管理员"/>
                管理员
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">电话号码</label>
        <div class="col-sm-10">
            <input class="form-control" type="text" name="tel" placeholder="请填写电话号码"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">专业</label>
        <div class="col-sm-10">
            <select name="majorid" class="form-control">
                <c:forEach items="${list}" var="major">
                    <option value="${major.mid}">${major.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <input class="btn " type="submit" value="提交"/>
        </div>
    </div>
</form>
</body>
</html>