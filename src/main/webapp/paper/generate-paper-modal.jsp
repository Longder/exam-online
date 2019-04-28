<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="session"/>
<div class="modal-dialog modal-lg">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">
                <span aria-hidden="true">×</span>
                <span class="sr-only">Close</span>
            </button>
            <h4 class="modal-title">生成试卷</h4>
        </div>
        <small class="font-bold">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-content">
                                <form id="createUserForm" method="post" class="form-horizontal"
                                      action="${ctx}/paper/generate">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">所属科目<span class="text-danger">*</span></label>
                                        <div class="col-sm-10">
                                            <select class="form-control m-b" name="question.course.id">
                                                <option value="">请选择</option>
                                                <c:forEach items="${courseList}" var="course">
                                                    <option value="${course.id}">${course.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">试卷名称<span class="text-danger">*</span></label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name=""/>
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">选择题数量<span class="text-danger">*</span></label>
                                        <div class="col-sm-10">
                                            <input type="number" class="form-control" name=""/>
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">填空题数量<span class="text-danger">*</span></label>
                                        <div class="col-sm-10">
                                            <input type="number" class="form-control" name=""/>
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">问答题数量<span class="text-danger">*</span></label>
                                        <div class="col-sm-10">
                                            <input type="number" class="form-control" name=""/>
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">简答题数量<span class="text-danger">*</span></label>
                                        <div class="col-sm-10">
                                            <input type="number" class="form-control" name=""/>
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    <div class="form-group">
                                        <div class="col-sm-4 col-sm-offset-2">
                                            <button class="btn btn-primary" type="submit">生成</button>
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
    </div>
</div>
