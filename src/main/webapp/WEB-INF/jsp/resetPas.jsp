<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.cdtu.entity.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>重置密码</title>
    <style>
        #bg{
            border: 1px solid black;
            width: 400px;
            height: 300px;
            text-align: center;
            position: relative;
            background-color:paleturquoise;

        }
        form{
            position: absolute;
            top:60px;
        }
        a{
            text-decoration: none;
        }
    </style>
</head>
<body>
<center>
    <div id="bg">
        <h3>重置密码</h3>
        <c:if test="${message!=null}">
            <h3 id="msg">${message}</h3>
        </c:if>

        <form action="/user/resetPassword" method="post"><br/>
            <%--<input type="hidden" id="btnType" name="btnType">--%>
            <c:if test="${username!=null}">
                用户名：<input type="text" name="username" id="username" value="${username}"><br/>
            </c:if>
            <c:if test="${username==null}">
                用户名：<input type="text" name="username" id="username"><br/>
            </c:if>
            <c:if test="${tel!=null}">
                电话号码：<input type="text" name="tel" id="tel" value="${tel}"><br/>
            </c:if>
            <c:if test="${tel==null}">
                电话号码：<input type="text" name="tel" id="tel"><br/>
            </c:if>

            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            验证码：<input type="text" name="code" id="code"> &nbsp;&nbsp;&nbsp;
            <a href="javascript:getHref()" id="sms">获取验证码</a>
            密&nbsp;&nbsp;码：<input type="password" name="password" id="password"><br/>
            确认密码：<input type="password" name="repassword" id="repassword"><br/>
            <input type="submit" id="submit" value="提交" onclick="return contronllSub()"/>
        </form>

    </div>
</center>
<script>
    username=document.getElementById("username");
    tel=document.getElementById("tel");
    code=document.getElementById("code");
    pad=document.getElementById("password");
    rePad=document.getElementById("repassword");
    sub=document.getElementById("submit");
    msg=document.getElementById("msg");

    function contronllSub(){
        flag=true;
        if(username==""||tel.value==""|| code.value==""|| pad.value==""||rePad.value==""){
            msg.innerHTML="请填写完整";
            msg.hidden=false;
            flag=false;
        }
        if(pad.value != rePad.value){

            if(msg.hidden==false){
                msg.innerHTML="密码不相同，请确认！！！"
            }else{
                msg.hidden=false;
                msg.innerHTML="密码不相同，请确认！！！"
            }
            flag=false;
        }
        return flag;
    }
    function getHref() {
        tel=document.getElementById("tel").value;
        if((tel!=""&&tel!=null)&&(username!=""&&username!=null)){
            window.location.href="sendSms?tel="+tel+"&username="+username.value;
        }else{
            document.getElementById("sms").innerText="请填写电话号码";
            window.location.href="#";
        }

    }

</script>
</body>
</html>
