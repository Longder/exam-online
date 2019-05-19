<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="session"/>
<!DOCTYPE html>
<html>
<head>
    <title>考试结果页</title>
    <jsp:include page="/import/head.jsp"/>
</head>
<body>
<div class="row">
    <div class="col-sm-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>${exam.examPaper.name}</h5>
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
                            <c:if test="${source=='list'}">
                                <button onclick="window.history.back()" type="button" class="btn btn-primary">返回</button>
                            </c:if>
                            <c:if test="${source=='paper'}">
                                <a href="${ctx}/exam/toChoose" type="button" class="btn btn-primary">返回</a>
                            </c:if>
                        </span>
                    </div>
                    <div class="col-sm-9"></div>
                </div>
                <div class="table table-bordered table-hover">
                    <div class="mail-box-header">
                        <h2 class="text-center">
                            ${exam.examPaper.name}
                        </h2>
                        <c:if test="${exam.isChecked}">
                            <h3 class="text-center">
                                考试得分：${exam.grade}
                            </h3>
                        </c:if>
                    </div>
                    <form method="post" action="${ctx}/exam/completeExam">
                        <input type="hidden" name="exam.id" value="${exam.id}"/>
                        <c:forEach items="${exam.detailList}" var="detail" varStatus="loop">
                            <input type="hidden" name="exam.detailList[${loop.index}].id" value="${detail.id}"/>
                            <div class="mail-box">
                                <div class="mail-body">
                                    <p>${loop.index+1}.${detail.question.type.displayName}：${detail.question.content}</p>
                                    <p>分值：${detail.question.score}</p>
                                    <c:choose>
                                        <c:when test="${empty detail.correct}">
                                            <p class="text-primary">未阅卷</p>
                                        </c:when>
                                        <c:when test="${detail.correct}">
                                            <p class="text-success">回答正确</p>
                                        </c:when>
                                        <c:when test="${!detail.correct}">
                                            <p class="text-danger">回答错误</p>
                                        </c:when>
                                        <c:otherwise>
                                            <p class="text-primary">未阅卷</p>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${detail.question.type.name=='CHOICE'}">
                                            <div class="radio radio-info radio-inline">
                                                <input disabled id="Q${detail.question.id}A" type="radio" value="A"
                                                       name="exam.detailList[${loop.index}].answer"
                                                    <c:if test="${detail.answer=='A'}">checked</c:if>>
                                                <label for="Q${detail.question.id}A">A.${detail.question.choiceA}</label>
                                            </div>
                                            <div class="radio radio-info radio-inline">
                                                <input disabled id="Q${detail.question.id}B" type="radio" value="B"
                                                       name="exam.detailList[${loop.index}].answer"
                                                       <c:if test="${detail.answer=='B'}">checked</c:if>>
                                                <label for="Q${detail.question.id}B">B.${detail.question.choiceB}</label>
                                            </div>
                                            <div class="radio radio-info radio-inline">
                                                <input disabled id="Q${detail.question.id}C" type="radio" value="C"
                                                       name="exam.detailList[${loop.index}].answer"
                                                       <c:if test="${detail.answer=='C'}">checked</c:if>>
                                                <label for="Q${detail.question.id}C">C.${detail.question.choiceC}</label>
                                            </div>
                                            <div class="radio radio-info radio-inline">
                                                <input disabled id="Q${detail.question.id}D" type="radio" value="D"
                                                       name="exam.detailList[${loop.index}].answer"
                                                       <c:if test="${detail.answer=='D'}">checked</c:if>>
                                                <label for="Q${detail.question.id}D">D.${detail.question.choiceD}</label>
                                            </div>
                                        </c:when>
                                        <c:when test="${detail.question.type.name=='FILL'}">
                                            <p>
                                                <input readonly name="exam.detailList[${loop.index}].answer" type="text" class="form-control" value="${detail.answer}"/>
                                            </p>
                                        </c:when>
                                        <c:when test="${detail.question.type.name=='ASK'}">
                                            <p>
                                                <textarea readonly name="exam.detailList[${loop.index}].answer" class="form-control">${detail.answer}</textarea>
                                            </p>
                                        </c:when>
                                        <c:when test="${detail.question.type.name=='ESSAY'}">
                                            <p>
                                                <textarea readonly name="exam.detailList[${loop.index}].answer" class="form-control">${detail.answer}</textarea>
                                            </p>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                        </c:forEach>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal inmodal fade" id="paperModal" tabindex="-1" role="dialog" aria-hidden="true"></div>
<jsp:include page="/import/Script.jsp"/>
</body>
</html>
