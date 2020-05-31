<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>判断题情况</title>
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/public/js/echarts.min.js"></script>
    <style>
        #chart{
            width: 100%;
            height: 500px;
        }
    </style>
</head>
<body>
<center><div><h3>${test.tname}选择题详情</h3></div></center>
<input type="hidden" id="tid" value="${test.tid}" />
<input type="hidden" id="nums" value="${test.listInfo.size()}"/>
<input type="hidden" id="jNames" value="${listNames}" />
<input type="hidden" id="jNums" value="${listNums}" />
<div id="chart"></div><br />
</body>
<script>
    var listNames;
    var listNums;
    $(document).ready(function(){
        var myChart=echarts.init(document.getElementById('chart'));
        var jNames=$("#jNames").val();
        jNames=jNames.replace(/\[|]/g,'');
        listNames =jNames.split(",");
        var jNums=$("#jNums").val();
        jNums=jNums.replace(/\[|]/g,'');
        listNums=jNums.split(",");
        option = {
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                },
                formatter: function (params) {
                    var tar = params[1];
                    return '考生总人数：'+ $("#nums").val()+ '<br/>' + tar.name+'正确的人数 : ' + tar.value;
                }
            },
            xAxis: {
                type: 'category',
                data: listNames
            },
            yAxis: {
                type: 'value'
            },
            series: [{ name: '辅助',
                type: 'bar',
                stack:  '总量',
                label: {
                    normal: {
                        show:true,
                        position: 'inside'
                    }
                },
                data: listNums
            },
                {
                    name: '生活费',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show:true,
                            position: 'inside'
                        }
                    },
                    itemStyle: {
                        normal: {
                            barBorderColor: 'rgba(0,0,0,0)',
                            color: 'rgba(0,0,0,0)'
                        },
                        emphasis: {
                            barBorderColor: 'rgba(0,0,0,0)',
                            color: 'rgba(0,0,0,0)'
                        }
                    },
                    data:listNums
                }
            ]

        };
        myChart.setOption(option);

    });

</script>
</html>
