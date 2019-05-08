<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="session"/>
<!DOCTYPE html>
<html>
<head>
    <title>学生列表</title>
    <jsp:include page="/import/head.jsp"/>
</head>
<body>
<div class="row">
    <div class="col-sm-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>学生列表</h5>
                <div class="ibox-tools">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-wrench"></i>
                    </a>
                    <a class="close-link">
                        <i class="fa fa-times"></i>
                    </a>
                </div>
            </div>
            <div class="ibox-content">
                <div class="row">
                    <div class="col-sm-3">
                        <span class="input-group-btn">
                        <input type="button" class="btn btn-success" data-toggle="modal"
                               data-target="#studentModal"
                               onclick="openModal('${ctx}/student/toAdd','studentModal')"
                               value="新增学生"/>
                        </span>
                    </div>
                    <div class="col-sm-9">

                    </div>
                </div>
                <div class="table table-bordered table-hover">
                    <table class="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>姓名</th>
                            <th>登陆名</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${studentList}" var="student" varStatus="loop">
                            <tr>
                                <td>${loop.index+1}</td>
                                <td>${student.name}</td>
                                <td>${student.loginName}</td>
                                <td>
                                    <button type="button" class="btn btn-sm btn-primary" data-toggle="modal"
                                            data-target="#studentModal"
                                            onclick="openModal('${ctx}/student/toUpdate?studentId=${student.id}','studentModal')">
                                        修改
                                    </button>
                                    <a href="${ctx}/student/delete?studentId=${student.id}" class="btn btn-sm btn-danger"
                                            onclick="return confirm('确定删除吗？')">
                                        删除
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal inmodal fade" id="studentModal" tabindex="-1" role="dialog" aria-hidden="true"></div>
<jsp:include page="/import/Script.jsp"/>
</body>
</html>
