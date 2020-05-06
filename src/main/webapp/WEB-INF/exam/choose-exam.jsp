<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="session"/>
<!DOCTYPE html>
<html>
<head>
    <title>选择试卷</title>
    <jsp:include page="/WEB-INF/import/head.jsp"/>
</head>
<body>
<div class="row">
    <div class="col-sm-12">`
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>选择试卷</h5>
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

                    <table class="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>所属科目</th>
                            <th>试卷名称</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${examPaperList}" var="paper" varStatus="loop">
                            <tr>
                                <td>${loop.index+1}</td>
                                <td>${paper.course.name}</td>
                                <td>${paper.name}</td>
                                <td>
                                    <a onclick="return validExam(${paper.id})" href="${ctx}/exam/startExam?examPaperId=${paper.id}"  class="btn btn-sm btn-warning" >
                                        开始考试
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
<div class="modal inmodal fade" id="paperModal" tabindex="-1" role="dialog" aria-hidden="true"></div>
<jsp:include page="/WEB-INF/import/Script.jsp"/>
<script type="text/javascript">
    //验证考试
    function validExam(examPaperId){
        var canChoose = false;
        $.ajax({
            url: "/exam/validExam",
            type: "post",
            async: false,
            data: {
                examPaperId:examPaperId
            },success: function (data) {
                if(data==="ok"){
                    canChoose = true;
                }else{
                    alert("你已经参加过该试卷的考试。");
                }
            }
        });
        return canChoose;
    }
</script>
</body>
</html>
