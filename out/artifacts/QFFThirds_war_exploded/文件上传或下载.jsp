<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2020/12/9
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传或下载</title>
</head>
<body>

<%--cookie小案例，显示上次登录的时间--%>
<%= request.getSession().getAttribute("username")+"欢迎登陆云盘"%>
<br>
<%
    Cookie[] cookies = request.getCookies();
    boolean flag=false;//标记有无lastTime键名
    if(cookies!=null&&cookies.length>0){
        for (Cookie cookie:cookies) {
            if(cookie.getName().equals("lastTime")){
                flag=true;
                Date date=new Date();
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日 hh：mm:ss");
                String str_date = simpleDateFormat.format(date);
                //URL编码 tomcat无法识别空格 需要转码
                str_date = URLEncoder.encode(str_date, "utf-8");
                cookie.setValue(str_date);
                //设置cookie存活时间
                cookie.setMaxAge(60*60*60*30);
                response.addCookie(cookie);
                String value = cookie.getValue();
                //解码
                value= URLDecoder.decode(value,"utf-8");
                out.write("您上次访问的时间是"+value);
                break;
            }
        }
    }
    if(cookies==null||cookies.length==0||flag==false) {
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日 hh：mm:ss");
        String str_date = simpleDateFormat.format(date);
        str_date= URLEncoder.encode(str_date, "utf-8");
        Cookie cookie=new Cookie("lastTime",str_date);
        //设置cookie存活时间
        cookie.setMaxAge(60*60*60*30);
        response.addCookie(cookie);
        out.write("您好，欢迎首次登录");
    }
%>
<br>
<%="请选择进行的操作"%>
<br>
<a href="/QFFThird_war_exploded/文件上传.html">文件上传</a>
<br>
<a href="/QFFThird_war_exploded/文件下载.jsp">文件下载</a>
<br>
<a href="/QFFThird_war_exploded/sessionRemove">退出登录</a>
</body>
</html>
