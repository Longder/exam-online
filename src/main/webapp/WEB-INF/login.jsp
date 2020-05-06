<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="session"/>
<!DOCTYPE html>
<html lang="zh">
<head>
    <title>在线考试系统后台 - 登录页</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="${ctx}/static/images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${ctx}/static/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${ctx}/static/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${ctx}/static/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${ctx}/static/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${ctx}/static/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/util.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/main.css">
    <!--===============================================================================================-->
</head>
<body>

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <div class="login100-pic js-tilt" data-tilt>
                <img src="${ctx}/static/images/img-01.png" alt="IMG">
            </div>

            <form class="login100-form validate-form"
                  action="${ctx}login_check" method="post" target="_self">
					<span class="login100-form-title">
                        用户登录
					</span>

                <div class="wrap-input100 validate-input" data-validate="请输入用户名">
                    <input class="input100" type="text" name="username" id="username" placeholder="用户名">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
							<i class="fa fa-user" aria-hidden="true"></i>
						</span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="请输入密码">
                    <input class="input100" type="password" name="password" placeholder="密码">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
							<i class="fa fa-lock" aria-hidden="true"></i>
                    </span>
                </div>
                <div class="wrap-input100 validate-input">
                    <span class="text-danger">${SPRING_SECURITY_LAST_EXCEPTION.message}</span>
                </div>
                <div class="container-login100-form-btn">
                    <button class="login100-form-btn" type="submit" id="login-button">
                        登录
                    </button>
                </div>

            </form>
        </div>
    </div>
</div>


<!--===============================================================================================-->
<script src="${ctx}/static/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="${ctx}/static/vendor/bootstrap/js/popper.js"></script>
<script src="${ctx}/static/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="${ctx}/static/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="${ctx}/static/vendor/tilt/tilt.jquery.min.js"></script>
<script>
    $('.js-tilt').tilt({
        scale: 1.1
    });
</script>
<!--===============================================================================================-->
<script src="${ctx}/static/js/main.js"></script>

</body>
</html>

