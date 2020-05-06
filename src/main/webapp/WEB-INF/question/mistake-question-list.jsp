<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="session"/>
<!DOCTYPE html>
<html>
<head>
    <title>题目列表</title>
    <jsp:include page="/WEB-INF/import/head.jsp"/>
</head>
<body>
<div class="row">
    <div class="col-sm-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>错题列表</h5>
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
                <form id="searchForm" method="post" action="${ctx}/question/listForMistake">
                    <input type="hidden" value="1" name="submit"/>
                    <div class="row">
                        <div class="col-sm-2">
                            <div class="form-group">
                                <label>错题包含内容：</label>
                                <input type="text" name="keyWord" placeholder="试题关键字" class="input-sm form-control"
                                       value="${keyWord}"/>
                            </div>
                        </div>

                        <div class="col-sm-2">
                            <div class="form-group">
                                <button style="margin-top: 20px;" id="searchSubmit" type="submit"
                                        class="btn btn-primary">查询
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
                <div class="table table-bordered table-hover">
                    <table class="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>所属科目</th>
                            <th>题目类型</th>
                            <th>难度</th>
                            <th>分值</th>
                            <th>错误数</th>
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
                                <td>${question.mistakeCount}</td>
                                <td>
                                    <button type="button" class="btn btn-sm btn-primary" data-toggle="modal"
                                            data-target="#questionModal"
                                            onclick="openModal('${ctx}/question/detail?questionId=${question.id}','questionModal')">
                                        详情
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
<jsp:include page="/WEB-INF/import/Script.jsp"/>
<script>
    function deleteQuestion(questionId) {
        if (confirm("确定删除吗？")) {
            $.ajax({
                url: "/question/delete",
                type: "post",
                async: false,
                data: {
                    questionId: questionId
                }, success: function (data) {
                    if (data === "ok") {
                        alert("删除成功");
                    } else {
                        alert("题目在试卷中已存在，删除失败");
                    }
                    window.location = "/question/list";
                }
            });
        }
    }
</script>
</body>
</html>
