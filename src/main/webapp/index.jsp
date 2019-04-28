<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="session"/>
<!DOCTYPE html>
<html>
<head>
    <title>在线考试管理</title>
    <jsp:include page="/import/head.jsp"/>
</head>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close">
            <i class="fa fa-times-circle"></i>
        </div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                               <span class="block m-t-xs">
                                   <strong class="font-bold">
                                   您好！大表哥
                                   </strong>
                               </span>
                                </span>
                        </a>
                    </div>
                    <div class="logo-element">
                        考
                    </div>
                </li>
                <li>
                    <a href="#" title="用户管理">
                        <i class="fa fa-users"></i>
                        <span class="nav-label">用户管理</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="${ctx}/student/list" title="学生管理">学生管理</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="${ctx}/teacher/list" title="教师管理">教师管理</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a class="J_menuItem" href="${ctx}/course/list" title="科目管理">
                        <i class="fa fa-cog"></i>
                        <span class="nav-label">科目管理</span>
                    </a>
                </li>
                <li>
                    <a href="#" title="考试管理">
                        <i class="fa fa-users"></i>
                        <span class="nav-label">考试管理</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="${ctx}/course/list" title="科目管理">科目管理</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="${ctx}/paper/list" title="试卷管理">试卷管理</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a class="J_menuItem" href="${ctx}/question/list" title="试题管理">
                        <i class="fa fa-cog"></i>
                        <span class="nav-label">试题管理</span>
                    </a>
                </li>
                <li>
                    <a href="#" title="我的考试">
                        <i class="fa fa-users"></i>
                        <span class="nav-label">我的考试</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="#" title="开始考试">开始考试</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="#" title="我的成绩">我的成绩</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="#" title="试题查询">试题查询</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom ">
            <nav class="navbar navbar-static-top rowbj" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header ">
                    <a id="miniLink" class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#">
                        <i class="fa fa-bars"></i> </a>
                    <form role="search" class="navbar-form-custom" method="post">
                        <div class="form-group">
                            <h1>在线考试系统</h1>
                        </div>
                    </form>
                </div>
            </nav>
        </div>
        <!--选项卡TAB-->
        <div class="row content-tabs">
            <nav class="page-tabs J_menuTabs" id="tabNav">
                <div class="page-tabs-content">
                    <a href="javascript:;" class="active J_menuTab" data-id="dashboard">首页</a>
                </div>
            </nav>
            <div class="btn-group roll-nav roll-right">
                <button class="dropdown J_tabClose" data-toggle="dropdown">选项卡操作
                </button>
                <ul role="menu" class="dropdown-menu dropdown-menu-right">
                    <li class=""><a id="refreshTab">刷新当前选项卡</a>
                    </li>
                    <li class="divider"></li>
                    <li class="J_tabCloseAll"><a>关闭全部</a>
                    </li>
                    <li class="J_tabCloseOther"><a>关闭其他</a>
                    </li>
                </ul>
            </div>
            <a href="logout" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
        </div>
        <!--用来切换的iframe-->
        <div class="row J_mainContent" id="content-main">
            <iframe class="J_iframe" id="iframe0" name="iframe0" width="100%" height="100%" src="${ctx}/admin/dashboard"
                    frameborder="0"
                    data-id="dashboard"></iframe>
        </div>
        <div class="footer">
            <div class="pull-right">&copy; 2019 <a href="#" target="_blank">大表哥出品</a>
            </div>
        </div>
    </div>
    <!--右侧部分结束-->
</div>
<jsp:include page="/import/Script.jsp"/>
</body>
</html>
