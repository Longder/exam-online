<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="session"/>
<!DOCTYPE html>
<html>
<head>
    <title>试卷列表</title>
    <jsp:include page="/WEB-INF/import/head.jsp"/>
</head>
<body>
<div class="row">
    <div class="col-sm-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>试卷列表</h5>
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
                               data-target="#paperModal"
                               onclick="openModal('${ctx}/paper/toGenerate','paperModal')"
                               value="自动生成试卷"/>
                        </span>

                    </div>
                    <div class="col-sm-9">
                        <div class="form-inline">
                            <label>选择课程：</label>
                            <select id="selectedCourse" class="form-control">
                                <c:forEach items="${courseList}" var="course">
                                    <option value="${course.id}">${course.name}</option>
                                </c:forEach>
                            </select>
                            <input id="to-manual-button" type="button" class="btn btn-warning" data-toggle="modal"
                                   data-target="#paperModal"
                                   onclick="openModal('${ctx}/paper/toManual?courseId=${courseList[0].id}','paperModal')"
                                   value="手动组卷"/>
                        </div>
                    </div>
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
                                    <a href="${ctx}/paper/detail?paperId=${paper.id}" type="button" class="btn btn-sm btn-warning" >
                                        试卷详情
                                    </a>
                                    <button onclick="deletePaper(${paper.id})" type="button" class="btn btn-sm btn-danger">
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
<div class="modal inmodal fade" id="paperModal" tabindex="-1" role="dialog" aria-hidden="true"></div>
<jsp:include page="/WEB-INF/import/Script.jsp"/>
<script>
    $(function(){
        $("#selectedCourse").change(function(){
            var courseId = $("#selectedCourse").val();
            $("#to-manual-button").attr("onclick","openModal('${ctx}/paper/toManual?courseId="+courseId+"','paperModal')");
        });
    });

    function deletePaper(id){
        if(confirm("确定要删除吗？")){
            $.ajax({
                url: "/paper/delete",
                type: "post",
                async: false,
                data: {
                    paperId:id
                },success: function (data) {
                    if(data==="ok"){
                        alert("删除成功");
                    }else{
                        alert("已经有完成的考试关联该试卷，删除失败");
                    }
                    window.location = "/paper/list";
                }
            });
        }
    }

</script>
</body>
</html>
