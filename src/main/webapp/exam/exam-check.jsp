<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="session"/>
<!DOCTYPE html>
<html>
<head>
    <title>阅卷页</title>
    <jsp:include page="/import/head.jsp"/>
</head>
<body>
<div class="row">
    <div class="col-sm-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>阅卷试卷名称：${exam.examPaper.name}</h5>
                <h6>考试学生：${exam.student.name}</h6>
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
                            <button onclick="window.history.back()" type="button" class="btn btn-primary">返回</button>
                        </span>
                    </div>
                    <div class="col-sm-9"></div>
                </div>
                <div class="table table-bordered table-hover">
                    <div class="mail-box-header">
                        <h2 class="text-center">
                            ${exam.examPaper.name}
                        </h2>
                    </div>
                    <form method="post" action="${ctx}/exam/checkExam">
                        <input type="hidden" name="exam.id" value="${exam.id}"/>
                        <c:forEach items="${exam.detailList}" var="detail" varStatus="loop">
                            <input type="hidden" name="exam.detailList[${loop.index}].id" value="${detail.id}"/>
                            <div class="mail-box">
                                <div class="mail-body">
                                    <p>${detail.question.content}</p>
                                    <c:if test="${detail.question.type.name=='FILL'||detail.question.type.name=='CHOICE'}">
                                        <p>标准答案：${detail.question.answer}</p>
                                    </c:if>
                                    <c:choose>
                                        <c:when test="${empty detail.correct}">

                                        </c:when>
                                        <c:when test="${detail.correct}">
                                            <p class="text-success">回答正确</p>
                                        </c:when>
                                        <c:when test="${!detail.correct}">
                                            <p class="text-danger">回答错误</p>
                                        </c:when>
                                        <c:otherwise>

                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${detail.question.type.name=='CHOICE'}">
                                            <div class="radio radio-info radio-inline">
                                                <input disabled id="Q${detail.question.id}A" type="radio" value="A"
                                                       <c:if test="${detail.answer=='A'}">checked</c:if>>
                                                <label for="Q${detail.question.id}A">A.${detail.question.choiceA}</label>
                                            </div>
                                            <div class="radio radio-info radio-inline">
                                                <input disabled id="Q${detail.question.id}B" type="radio" value="B"
                                                       <c:if test="${detail.answer=='B'}">checked</c:if>>
                                                <label for="Q${detail.question.id}B">B.${detail.question.choiceB}</label>
                                            </div>
                                            <div class="radio radio-info radio-inline">
                                                <input disabled id="Q${detail.question.id}C" type="radio" value="C"
                                                       <c:if test="${detail.answer=='C'}">checked</c:if>>
                                                <label for="Q${detail.question.id}C">C.${detail.question.choiceC}</label>
                                            </div>
                                            <div class="radio radio-info radio-inline">
                                                <input disabled id="Q${detail.question.id}D" type="radio" value="D"
                                                       <c:if test="${detail.answer=='D'}">checked</c:if>>
                                                <label for="Q${detail.question.id}D">D.${detail.question.choiceD}</label>
                                            </div>
                                        </c:when>
                                        <c:when test="${detail.question.type.name=='FILL'}">
                                            <p>
                                                <input readonly type="text" class="form-control" value="${detail.answer}"/>
                                            </p>
                                        </c:when>
                                        <c:when test="${detail.question.type.name=='ASK'}">
                                            <p>
                                                <textarea readonly class="form-control">${detail.answer}</textarea>
                                            </p>
                                        </c:when>
                                        <c:when test="${detail.question.type.name=='ESSAY'}">
                                            <p>
                                                <textarea readonly class="form-control">${detail.answer}</textarea>
                                            </p>
                                        </c:when>
                                    </c:choose>
                                    <c:if test="${empty detail.correct}">
                                        <p class="text-warning">未批改</p>
                                        <p>
                                            回答：
                                            <span class="radio">
                                            <input type="radio" name="exam.detailList[${loop.index}].correct"
                                                   id="Q${detail.question.id}C" value="true" checked>
                                            <label for="Q${detail.question.id}C">
                                                正确
                                            </label>
                                        </span>
                                        <span class="radio">
                                            <input type="radio" name="exam.detailList[${loop.index}].correct"
                                                   id="Q${detail.question.id}W" value="false">
                                            <label for="Q${detail.question.id}W">
                                                错误
                                            </label>
                                        </span>
                                        </p>
                                    </c:if>
                                </div>
                            </div>
                        </c:forEach>
                        <div class="mail-box">
                            <div class="mail-body">
                                <button class="btn btn-primary" type="submit">确定阅卷</button>
                            </div>
                        </div>
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
