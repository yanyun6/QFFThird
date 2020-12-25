<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2020/12/9
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>云盘登录页面</title>
    <meta charset="UTF-8">
    <title>云盘登录页面</title>
    <style>
        .td_left {
            margin-top: 1px;
            width: 100px;
            text-align: right;
            height: 60px;
        }

        .td_right {
            padding-top: 0px;
            padding-left: 50px;
            height: 60px;
        }
    </style>
    <script>
        window.onload = function () {
            var img = document.getElementById("ss");
            img.onclick = function () {
                var date = new Date().getTime();
                img.src = "/QFFThirds_war_exploded/checkCode?" + date;
            }

        }
    </script>
</head>
<body>
<form action="/QFFThirds_war_exploded/login" method="get">
    <table class="t1" width="500">
        <tr>
            <td class="td_left"><label for="username"> 用户名</label></td>
            <td class="td_right"><input type="text" name="username" id="Username" placeholder="请输入用户名"></td>
        </tr>
        <br>

        <tr>
            <td class="td_left"><label for="ssss"> 密码</label></td>
            <td class="td_right"><input type="password" name="password" id="ssss" placeholder="请输入密码">
            </td>
        </tr>
        <tr>
            <td class="td_left"><label for="checkcode">验证码</label></td>
            <td class="td_right"><input type="text" name="checkcode" id="Checkcode" placeholder="请输入验证码"></td>
            <td>
                <img id="ss" src="/QFFThirds_war_exploded/checkCode"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="登录" id="btn_sub">
            </td>
        </tr>
        <tr><%=request.getAttribute("login_error") == null ? "" : request.getAttribute("login_error")%>
        </tr>
        <tr><%=request.getAttribute("check_error") == null ? "" : request.getAttribute("check_error")%>
        </tr>
        <tr><%=request.getAttribute("have_login") == null ? "" : request.getAttribute("have_login")%>
        </tr>

    </table>
</form>
</body>
</html>
