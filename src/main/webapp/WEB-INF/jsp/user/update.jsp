<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>修改信息</title>
    <script type="text/javascript " src="${ctx}/public/js/jquery.min.js"></script>
    <script type="text/javascript " src="${ctx}/public/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${ctx}/public/css/bootstrap.css">
</head>
<body style="padding: 1%;">
<form action="/user/updateUser" method="post" class="form-horizontal" style="width: 60%;">
    <input type="hidden" name="id" value="${user.id}"/>
    <div class="form-group">
        <label class="col-sm-2 control-label">用户名</label>
        <div class="col-sm-10">
            <input class="form-control" type="text" name="username" value="${user.username}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">密码</label>
        <div class="col-sm-10">
            <input class="form-control" type="text" name="password" value="${user.textpass}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">姓名</label>
        <div class="col-sm-10">
            <input class="form-control" type="text" name="name" value="${user.name}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">身份</label>
        <div class="col-sm-10">
            <select class="form-control" id="identify" name="identify" value='${user.identify}'>
                <option value="管理员">管理员</option>
                <option value="老师">老师</option>
                <option value="学生">学生</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">电话号码</label>
        <div class="col-sm-10">
            <input class="form-control" type="text" name="tel" value="${user.tel}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">专业</label>
        <div class="col-sm-10">
            <select class="form-control" id="majorid" name="majorid" value='${user.major.mid}'>
                <c:forEach items="${list}" var="major">
                    <option value="${major.mid}">${major.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <input class="btn btn-default" type="submit" name="submit" id="submit" value="提交"/>
        </div>
    </div>
</form>
</body>
</html>