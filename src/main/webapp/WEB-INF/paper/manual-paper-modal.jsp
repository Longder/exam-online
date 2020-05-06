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
            <h4 class="modal-title">手动组卷</h4>
        </div>
        <small class="font-bold">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-content">
                                <form method="post" action="${ctx}/paper/manual">
                                    <div class="tabs-container">
                                        <ul class="nav nav-tabs">
                                            <li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">基本信息</a>
                                            </li>
                                            <li class=""><a data-toggle="tab" href="#tab-2"
                                                            aria-expanded="false">题目选择</a>
                                            </li>
                                        </ul>
                                        <div class="tab-content">
                                            <div id="tab-1" class="tab-pane active">
                                                <div class="panel-body">
                                                    <div method="post" class="form-horizontal">
                                                        <div class="form-group">
                                                            <label class="col-sm-2 control-label">所选科目<span
                                                                    class="text-danger">*</span></label>
                                                            <div class="col-sm-10">
                                                                <input type="hidden" value="${course.id}" name="courseId"/>
                                                                <input type="text" class="form-control" disabled value="${course.name}"/>
                                                            </div>
                                                        </div>
                                                        <div class="hr-line-dashed"></div>
                                                        <div class="form-group">
                                                            <label class="col-sm-2 control-label">试卷名称<span
                                                                    class="text-danger">*</span></label>
                                                            <div class="col-sm-10">
                                                                <input type="text" class="form-control"
                                                                       name="paperName"/>
                                                            </div>
                                                        </div>
                                                        <div class="hr-line-dashed"></div>
                                                        <div class="form-group form-inline">
                                                            <label class="col-sm-2 control-label">考试时间<span
                                                                    class="text-danger">*</span></label>
                                                            <div class="col-sm-10">
                                                                <div class="form-inline">
                                                                    <input type="number" name="hours"
                                                                           placeholder="请输入小时" class="form-control"
                                                                           max="4" min="0" value="0"/>
                                                                    <label>小时</label>
                                                                    <input type="number" name="minutes"
                                                                           placeholder="请输入分钟" class="form-control"
                                                                           max="59" min="1" value="5"/>
                                                                    <label>分钟</label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="hr-line-dashed"></div>

                                                    </div>
                                                </div>
                                            </div>
                                            <div id="tab-2" class="tab-pane">
                                                <div class="panel-body">
                                                    <div class="table-responsive">
                                                        <table class="table table-striped table-bordered">
                                                            <thead>
                                                            <tr>
                                                                <th></th>
                                                                <th>所属科目</th>
                                                                <th>题目类型</th>
                                                                <th>难度</th>
                                                                <th>分值</th>
                                                                <th>题目内容</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <c:forEach items="${questionList}" var="question" varStatus="loop">
                                                                <tr>
                                                                    <td>
                                                                        <input value="${question.id}" type="checkbox" class="i-checks"
                                                                               name="questionIds">
                                                                    </td>
                                                                    <td>${question.course.name}</td>
                                                                    <td>${question.type.displayName}</td>
                                                                    <td>${question.difficulty}</td>
                                                                    <td>${question.score}</td>
                                                                    <td>
                                                                            ${question.content}
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
                                    <div class="form-group">
                                        <div class="col-sm-4 col-sm-offset-2">
                                            <button class="btn btn-primary" type="submit">确定组卷</button>
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
<script>
    $(document).ready(function () {
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            raæædioClass: 'iradio_square-green'
        });
    });
</script>
