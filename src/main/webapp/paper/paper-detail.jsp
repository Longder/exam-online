<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="session"/>
<!DOCTYPE html>
<html>
<head>
    <title>试卷详情</title>
    <jsp:include page="/import/head.jsp"/>
</head>
<body>
<div class="row">
    <div class="col-sm-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>${examPaper.name}</h5>
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
                            ${examPaper.name}
                        </h2>
                    </div>
                    <c:forEach items="${questionList}" var="question" varStatus="loop">
                            <div class="mail-box">
                                <div class="mail-body">
                                    <p>${question.content}</p>
                                    <c:if test="${question.type.name=='CHOICE'}">
                                        <p>A.${question.choiceA}</p>
                                        <p>B.${question.choiceB}</p>
                                        <p>C.${question.choiceC}</p>
                                        <p>D.${question.choiceD}</p>
                                    </c:if>
                                    <p>分数：${question.score}</p>
                                    <p>答案：${question.answer}</p>
                                </div>
                            </div>
                        </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal inmodal fade" id="paperModal" tabindex="-1" role="dialog" aria-hidden="true"></div>
<jsp:include page="/import/Script.jsp"/>
</body>
</html>
