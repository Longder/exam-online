<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="session"/>
<!DOCTYPE html>
<html>
<head>
    <title>试卷页</title>
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
                        <span class="input-group-btn"></span>
                    </div>
                    <div class="col-sm-9"></div>
                </div>
                <div class="table table-bordered table-hover">
                    <div class="mail-box-header">
                        <h2 class="text-center">
                            ${exam.examPaper.name}
                        </h2>
                    </div>
                    <c:forEach items="${exam.detailList}" var="detail">
                        <div class="mail-box">
                            <div class="mail-body">
                                <p>${detail.question.content}</p>
                                <c:choose>
                                    <c:when test="${detail.question.type.name=='CHOICE'}">
                                        <div class="radio radio-info radio-inline">
                                            <input type="radio" value="A" name="Q${detail.question.id}" checked="">
                                            <label>A</label>
                                        </div>
                                        <div class="radio radio-info radio-inline">
                                            <input type="radio" value="B" name="Q${detail.question.id}">
                                            <label>B</label>
                                        </div>
                                        <div class="radio radio-info radio-inline">
                                            <input type="radio" value="C" name="Q${detail.question.id}">
                                            <label>C</label>
                                        </div>
                                        <div class="radio radio-info radio-inline">
                                            <input type="radio" value="D" name="Q${detail.question.id}">
                                            <label>D</label>
                                        </div>
                                    </c:when>
                                    <c:when test="${detail.question.type.name=='FILL'}">
                                        <p>填空题</p>
                                    </c:when>
                                    <c:when test="${detail.question.type.name=='ASK'}">
                                        <p>问答题</p>
                                    </c:when>
                                    <c:when test="${detail.question.type.name=='ESSAY'}">
                                        <p>简答题</p>
                                    </c:when>
                                </c:choose>
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
