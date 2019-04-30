<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="session"/>
<!DOCTYPE html>
<html>
<head>
    <title>题目列表</title>
    <jsp:include page="/import/head.jsp"/>
</head>
<body>
<div class="row">
    <div class="col-sm-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>题目列表</h5>
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
                                   data-target="#questionModal"
                                   onclick="openModal('${ctx}/question/toAdd','questionModal')"
                                   value="新增题目"/>
                            <input type="button" class="btn btn-warning" data-toggle="modal"
                                   data-target="#uploadExcelModal" value="导入题目"/>
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
                            <th>所属科目</th>
                            <th>题目类型</th>
                            <th>难度</th>
                            <th>分值</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${questionList}" var="question" varStatus="loop">
                            <tr>
                                <td>${loop.index+1}</td>
                                <td>${question.course.name}</td>
                                <td>${question.type.displayName}</td>
                                <td>${question.difficulty}</td>
                                <td>${question.score}</td>
                                <td>
                                    <button type="button" class="btn btn-sm btn-primary" data-toggle="modal"
                                            data-target="#questionModal">
                                        修改
                                    </button>
                                    <button type="button" class="btn btn-sm btn-danger">
                                        删除
                                    </button>
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
<div class="modal inmodal fade" id="questionModal" tabindex="-1" role="dialog" aria-hidden="true"></div>
<div class="modal inmodal fade" id="uploadExcelModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">导入试题</h4>
            </div>
            <small class="font-bold">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="ibox float-e-margins">
                                <div class="ibox-content">
                                    <form  method="post" enctype="multipart/form-data" class="form-horizontal" action="${ctx}/question/upload">
                                        <div class="form-group" style="margin-top: 10px;">
                                            <div class="col-sm-4 col-sm-offset-2">
                                                <a class="btn btn-warning" href="${ctx}/static/template/Questions.xlsx">下载上传模板</a>
                                            </div>
                                        </div>
                                        <div class="hr-line-dashed"></div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">所属科目<span class="text-danger">*</span></label>
                                            <div class="col-sm-10">
                                                <select class="form-control m-b" name="courseId">
                                                    <option value="">请选择</option>
                                                    <c:forEach items="${courseList}" var="course">
                                                        <option value="${course.id}">${course.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="hr-line-dashed"></div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">上传<span class="text-danger">*</span></label>
                                            <div class="col-sm-10">
                                                <input type="file" name="upload" class="form-control"/>
                                            </div>
                                        </div>
                                        <div class="hr-line-dashed"></div>
                                        <div class="form-group">
                                            <div class="col-sm-4 col-sm-offset-2">
                                                <button class="btn btn-primary" type="submit">确定上传</button>
                                                <button class="btn btn-white" type="button" data-dismiss="modal">取消</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </small>

        </div><!-- /.modal-content -->
    </div>
</div>
<jsp:include page="/import/Script.jsp"/>
</body>
</html>
