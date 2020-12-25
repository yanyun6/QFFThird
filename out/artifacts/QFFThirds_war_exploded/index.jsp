<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2020/12/9
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>云盘注册页面</title>
    <meta charset="UTF-8">
    <title>云盘注册页面</title>
    <script>
        window.onload = function () {
            var img = document.getElementById("ss");
            img.onclick = function () {
                var date = new Date().getTime();
                img.src = "/QFFThirds_war_exploded/checkCode?" + date;
            }
        }
    </script>
    <style>
        * {
            margin: 0px;
            padding: 0px;
            box-sizing: border-box;
        }

        /*body {*/
        /*    background-image: url(./OIP.jpg);*/
        /*}*/

        .rg_laborator {
            width: 900px;
            height: 700px;
            border: 8px solid #EEEEEE;
            background-color: white;
            margin: auto;
            margin-top: 15px;
        }

        .rg_left {

            float: left;
            margin-right: 50px;
        }

        .rg_left > p:first-child {
            height: 10px;
            color: #FFd026;
            font-size: 20px;
        }

        .rg_left > p:nth-child(2) {
            color: #A6A6A6;
            font-size: 20px;
        }

        .rg_centre {
            float: left;
        }

        .rg_right {
            float: left;
            margin-left: 50px;
        }

        .rg_right > p:first-child {
            color: red;
            font-size: 20px;
        }

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

        #username, #birthday, #email, #ssss, #phonenumber, #btn_sub, #dddd {
            width: 251px;
            height: 32px;
            border: 1px solid #A6A6A6;
            border-radius: 5px;
            padding-left: 15px;
        }

        #btn_sub {
            width: 150px;
            height: 40px;
            background-color: #FFD026;
            border: 1px solid #FFD026;
            text-align: center;
        }

        .text2 {
            color: pink;
        }

        .text3 {
            color: pink;
        }

        .rg_form {
            padding-top: 1px;
        }

        .t1 {
            width: 500px;
            height: 400px;
            margin-top: -145px;
        }
    </style>
</head>
<body>
<div class="rg_laborator">
    <div class="rg_left">
        <p class="text1"> 新用户注册</p> <br>
        <p class="text2">USER REGISTER</p><br>
    </div>
    <div class="rg_centre">
        <div class="rg_form">
            <form action="/QFFThird_war_exploded/register" method="get">
                <table class="t1" width="500">
                    <tr>
                        <td class="td_left"><label for="username"> 用户名</label></td>
                        <td class="td_right"><input type="text" name="username" id="username" placeholder="请输入用户名"></td>
                    </tr>
                    <br>

                    <tr>
                        <td class="td_left"><label for="ssss"> 密码</label></td>
                        <td class="td_right"><input type="password" name="password" id="ssss" placeholder="请输入密码">
                        </td>
                    </tr>
                    <br>

                    <tr>
                        <td class="td_left"><label for="dddd"> 确认密码</label></td>
                        <td class="td_right"><input type="password" name="again" id="dddd" placeholder="再次输入密码">
                        </td>
                    </tr>
                    <br>

                    <tr>
                        <td class="td_left"><label for="email"> Email</label></td>
                        <td class="td_right"><input type="email" name="email" id="Email" placeholder="请输入邮件地址"></td>
                    </tr>
                    <br>

                    <tr>
                        <td class="td_left"><label for="phonenumber"> 电话号码</label></td>
                        <td class="td_right"><input type="text" name="phonenumber" id="Phonenumber"
                                                    placeholder="请输入电话号码"></td>
                    </tr>
                    <br>

                    <tr>
                        <td class="td_left"><label>性别</label></td>
                        <td class="td_right"><input type="radio" name="gender" value="male">男
                            <input type="radio" name="gender" value="female">女
                        </td>
                    </tr>
                    <br>

                    <tr>
                        <td class="td_left"><label for="birthday"> 出生年月</label></td>
                        <td class="td_right"><input type="date" name="birthday" id="birthday"></td>
                    </tr>
                    <br>
                  <tr>
                    <td class="td_left"><label for="checkcode">验证码</label></td>
                    <td class="td_right"><input type="text" name="checkcode" id="checkcode" placeholder="请输入验证码">
                    </td>
                   <td><img id= "ss" src="/QFFThirds_war_exploded/checkCode"/></td>
                  </tr>
                  <br>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit" value="注册" id="btn_sub">
                        </td>
                    </tr>
                  <br>
                  <tr>
                      <td><%out.print(request.getAttribute("register_error") == null ? "" : request.getAttribute("register_error"));%><</td>
                     <td> <%out.print(request.getAttribute("checkcode_error") == null ? "" : request.getAttribute("checkcode_error"));%></td>
                  </tr>
                </table>
            </form>
        </div>
    </div>
    <div class="rg_right">
        <p>已有帐号</p>
        <a href="云盘登录页面.jsp" class="text3">立即登录</a>
    </div>
</div>
</tr>
</body>
</html>

