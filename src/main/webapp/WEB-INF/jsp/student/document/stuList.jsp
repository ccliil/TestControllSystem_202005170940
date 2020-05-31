<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>文档学习</title>
    <script type="text/javascript " src="${ctx}/public/js/jquery.min.js"></script>
    <script type="text/javascript " src="${ctx}/public/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${ctx}/public/css/bootstrap.css">

</head>
<body>
<form action="/student/docment/studyDoc" method="post" style="padding-left: 10px;">
    <input type="hidden" id="doc" name="doc"/>
    <input type="hidden" name="uid" value="${user.id}"/>
    <c:if test="${listDocs!=null}">
        <input type="hidden" id="docNames" value="${listDocs}"/>
    </c:if>
    <c:if test="${listDocs==null}">
        <input type="hidden" id="docNames"/>
    </c:if>
    <table class="table table-striped" style="width: 60%;">
        <tr>
            <td colspan="2" align="center">
                <h4>要学习的文档</h4>
            </td>
        </tr>

        <%--        <ul id="doclist">--%>
        <c:forEach items="${list}" var="document">
            <tr>
                <td>
                    <lable>${document.dataName}</lable>
                    <a style="display: none" href="/document/lookUp?fid=${document.id}"
                       target="_blank">${document.dataName}</a>
                </td>
                <td>
                    <strong>未学习</strong>
                </td>
            </tr>
        </c:forEach>
        <%--        </ul>--%>
    </table>
    <input class="btn btn-info" type="submit" value="保存学习进度" id="takeUp"/>
</form>

</body>
<script>
    $(document).ready(function () {
        //获取文档学习进度
        window.dataList = $("#docNames").val().replace(/\[|]/g, '').split(", ");
        //回写数据到需要提交的input
        $("#doc").val(dataList.join("//"));

        //装载文档进度到页面
        $("table td lable").each(function () {
            if (dataList.indexOf($(this).text()) > -1) {
                $(this).hide().siblings().show();
                $(this).parent().next().html("<strong finished=1>已学习</strong>");
            }
        })
        //解锁已经学习的文档的下一篇
        $("strong[finished]:last").parent().parent().next().find("a").show().siblings().hide();
        //解锁第一篇文档
        $("table td lable:first").each(function () {
            $(this).hide().siblings().show();
        })
        //绑定点击事件
        $("table td a").click(function () {
            if (dataList.indexOf($(this).text()) == -1) {
                dataList.push($(this).text());
            }
            //更新input中的值
            window.currentDoc = this;
            $("#doc").val(dataList.join("//"));
            //60秒之后解封文档
            setTimeout(function () {
                $(window.currentDoc).parent().parent().next().find("a").show().siblings().hide()
                $(window.currentDoc).parent().next().html("<strong finished=1>已学习</strong>");
            }, 1000*60);
        });
    });
</script>
</html>
