<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.cdtu.entity.User" %>
<!DOCTYPE html>
<html>
<div>
    <form action="/user/resetPassword" method="post">
        电话号码：<input type="tel" name="tel" id="tel"><br/>
        验证码：<input type="text" name="code" id="code"> &nbsp;&nbsp;&nbsp;<label id=""></label>
        密&nbsp;&nbsp;码：<input type="password" name="password" id="password"><br/>
        确认密码：<input type="password" name="repassword" id="repassword"><br/>
    </form>
</div>

</html>