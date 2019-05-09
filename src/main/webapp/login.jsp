<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>在线考试系统 - 登录</title>
    <link href="<s:url action="static/css/bootstrap.min.css?v=3.3.6"/>" rel="stylesheet">
    <link href="<s:url action="static/css/font-awesome.css?v=4.4.0"/> " rel="stylesheet">
    <link href="<s:url action="static/css/animate.css"/>" rel="stylesheet">
    <link href="<s:url action="static/css/style.css?v=4.1.0"/>" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>

<body class="gray-bg">

<div class="middle-box text-center loginscreen  animated fadeInDown">
    <div>
        <div>
            <h1 class="logo-name">考+</h1>
        </div>
        <h3>在线考试系统</h3>
        <form id="login-form" class="m-t" role="form" action="<s:url action="login_check"/>" method="post" target="_self">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="用户名" name="username" id="username"/>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" placeholder="密码" name="password">
            </div>
            <div class="form-group">
                <select id="role" class="form-control">
                    <option value="ROLE_ADMIN">管理员</option>
                    <option value="ROLE_TEACHER">教师</option>
                    <option value="ROLE_STUDENT">学生</option>
                </select>
            </div>
            <span class="text-danger">${SPRING_SECURITY_LAST_EXCEPTION.message}</span>
            <button id="login-button" type="submit" class="btn btn-primary block full-width m-b">登 录</button>
        </form>
    </div>
</div>
<script src="<s:url action="static/js/jquery.min.js?v=2.1.4"/>"></script>
<script src="<s:url action="static/js/plugins/validate/jquery.validate.min.js"/>"></script>
<script src="<s:url action="static/js/plugins/validate/messages_zh.min.js"/>"></script>
<script type="text/javascript">
$(function(){
    $("#login-button").click(function(){
        var flag = false;
        $.ajax({
            async:false,
            dataType:'json',
            data:{loginName:$("#username").val()},
            url:"/user/checkRole",
            success:function(data){
                if("no"===data){
                    alert("用户名不存在");
                }else if($("#role").val()===data){
                    flag = true;
                }else{
                    alert("用户角色不正确");
                }
            }
        });
        return flag;
    });
});
</script>
</body>
</html>

