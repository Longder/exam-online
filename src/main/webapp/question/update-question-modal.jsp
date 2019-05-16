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
            <h4 class="modal-title">修改试题</h4>
        </div>
        <small class="font-bold">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-content">
                                <form id="createUserForm" method="post" class="form-horizontal"
                                      action="${ctx}/question/update">
                                    <input type="hidden" value="${question.id}" name="question.id"/>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">所属科目<span class="text-danger">*</span></label>
                                        <div class="col-sm-10">
                                            <select class="form-control m-b" name="question.course.id">
                                                <option value="">请选择</option>
                                                <c:forEach items="${courseList}" var="course">
                                                    <c:choose>
                                                        <c:when test="${question.course.id == course.id}">
                                                            <option value="${course.id}" selected>${course.name}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${course.id}">${course.name}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">试题类型<span class="text-danger">*</span></label>
                                        <div class="col-sm-10">
                                            <select id="question-type-select" class="form-control m-b" name="question.type">
                                                <option value="">请选择</option>
                                                <c:forEach items="${questionTypes}" var="type">
                                                    <c:choose>
                                                        <c:when test="${question.type.name == type.name}">
                                                            <option value="${type.name}" selected>${type.displayName}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${type.name}">${type.displayName}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">试题难度<span class="text-danger">*</span></label>
                                        <div class="col-sm-10">
                                            <select class="form-control m-b" name="question.difficulty">
                                                <option value="">请选择</option>
                                                <c:forEach items="${difficultyTypes}" var="difficulty">
                                                    <c:choose>
                                                        <c:when test="${question.difficulty == difficulty.value}">
                                                            <option value="${difficulty.value}" selected>${difficulty.displayName}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${difficulty.value}">${difficulty.displayName}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">试题分数<span class="text-danger">*</span></label>
                                        <div class="col-sm-10">
                                            <input type="number" class="form-control" name="question.score" value="${question.score}"/>
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">试题内容<span class="text-danger">*</span></label>
                                        <div class="col-sm-10">
                                            <textarea rows="10" type="text" class="form-control" name="question.content">${question.content}</textarea>
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    <div id="choice-detail" class="form-group" <c:if test="${question.type.name!='CHOICE'}">style="display: none;"</c:if>>
                                        <label class="col-sm-2 control-label">选项A：<span class="text-danger">*</span></label>
                                        <div class="col-sm-4">
                                            <input type="text" class="form-control" name="question.choiceA" value="${question.choiceA}"/>
                                        </div>
                                        <label class="col-sm-2 control-label">选项B：<span class="text-danger">*</span></label>
                                        <div class="col-sm-4">
                                            <input type="text" class="form-control" name="question.choiceB" value="${question.choiceB}"/>
                                        </div>
                                        <label class="col-sm-2 control-label">选项C：<span class="text-danger">*</span></label>
                                        <div class="col-sm-4">
                                            <input type="text" class="form-control" name="question.choiceC" value="${question.choiceC}"/>
                                        </div>
                                        <label class="col-sm-2 control-label">选项D：<span class="text-danger">*</span></label>
                                        <div class="col-sm-4">
                                            <input type="text" class="form-control" name="question.choiceD" value="${question.choiceD}"/>
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    <div class="form-group" id="answer-normal" <c:if test="${question.type.name=='CHOICE'}">style="display: none;"</c:if>>
                                        <label class="col-sm-2 control-label">试题答案<span
                                                class="text-danger">*</span></label>
                                        <div class="col-sm-10">
                                            <input <c:if test="${question.type.name=='CHOICE'}">disabled</c:if> type="text" class="form-control" name="question.answer" value="${question.answer}"/>
                                        </div>
                                    </div>
                                    <div class="form-group" id="answer-for-choice" <c:if test="${question.type.name!='CHOICE'}">style="display: none;"</c:if>>
                                        <label class="col-sm-2 control-label">试题答案<span
                                                class="text-danger">*</span></label>
                                        <div class="col-sm-10">
                                            <select <c:if test="${question.type.name!='CHOICE'}">disabled</c:if> class="form-control m-b" name="question.answer">
                                                <option value="">请选择</option>
                                                <option <c:if test="${question.answer=='A'}">selected</c:if> value="A">A</option>
                                                <option <c:if test="${question.answer=='B'}">selected</c:if> value="B">B</option>
                                                <option <c:if test="${question.answer=='C'}">selected</c:if> value="C">C</option>
                                                <option <c:if test="${question.answer=='D'}">selected</c:if> value="D">D</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    <div class="form-group">
                                        <div class="col-sm-4 col-sm-offset-2">
                                            <button class="btn btn-primary" type="submit">保存</button>
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
<script type="text/javascript">
    $(function(){
        $("#question-type-select").change(function () {
            if ($("#question-type-select").val() === "CHOICE") {
                $("#choice-detail").show();
                $("#answer-for-choice").show();
                $("#answer-for-choice").find("select").removeAttrs("disabled");

                $("#answer-normal").hide();
                $("#answer-normal").find("input").attr("disabled",true);

            } else {
                $("#choice-detail").hide();
                $("#answer-for-choice").hide();
                $("#answer-for-choice").find("select").attr("disabled",true);

                $("#answer-normal").show();
                $("#answer-normal").find("input").removeAttrs("disabled");
            }
            if($("#question-type-select").val() === "ASK"||$("#question-type-select").val() === "ESSAY"){
                $("#answer-normal").find("input").attr("disabled",true);
            }
        });
    });
</script>