<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="session"/>
<!DOCTYPE html>
<html>
<head>
    <title>试卷页</title>
    <jsp:include page="/import/head.jsp"/>
    <style type="text/css">
        .time-item strong {
            background:#C71C60;
            color:#fff;
            line-height:49px;
            font-size:36px;
            font-family:Arial;
            padding:0 10px;
            margin-right:10px;
            border-radius:5px;
            box-shadow:1px 1px 3px rgba(0,0,0,0.2);
        }
        #day_show {
            float:left;
            line-height:49px;
            color:#c71c60;
            font-size:32px;
            margin:0 10px;
            font-family:Arial,Helvetica,sans-serif;
        }
        .item-title .unit {
            background:none;
            line-height:49px;
            font-size:24px;
            padding:0 10px;
            float:left;
        }
    </style>
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
                        <div class="time-item">
                            <strong id="hour_show">0时</strong>
                            <strong id="minute_show">0分</strong>
                            <strong id="second_show">0秒</strong>
                        </div>
                    </div>
                    <form method="post" action="${ctx}/exam/completeExam">
                        <input type="hidden" name="exam.id" value="${exam.id}"/>
                        <c:forEach items="${exam.detailList}" var="detail" varStatus="loop">
                            <input type="hidden" name="exam.detailList[${loop.index}].id" value="${detail.id}"/>
                            <div class="mail-box">
                                <div class="mail-body">
                                    <p>${detail.question.content}</p>
                                    <p class="text-danger" style="display:none;">回答错误</p>
                                    <p class="text-success" style="display:none;">回答正确</p>
                                    <c:choose>
                                        <c:when test="${detail.question.type.name=='CHOICE'}">
                                            <div class="radio radio-info radio-inline">
                                                <input id="Q${detail.question.id}A" type="radio" value="A"
                                                       name="exam.detailList[${loop.index}].answer" checked="">
                                                <label for="Q${detail.question.id}A">A</label>
                                            </div>
                                            <div class="radio radio-info radio-inline">
                                                <input id="Q${detail.question.id}B" type="radio" value="B"
                                                       name="exam.detailList[${loop.index}].answer">
                                                <label for="Q${detail.question.id}B">B</label>
                                            </div>
                                            <div class="radio radio-info radio-inline">
                                                <input id="Q${detail.question.id}C" type="radio" value="C"
                                                       name="exam.detailList[${loop.index}].answer">
                                                <label for="Q${detail.question.id}C">C</label>
                                            </div>
                                            <div class="radio radio-info radio-inline">
                                                <input id="Q${detail.question.id}D" type="radio" value="D"
                                                       name="exam.detailList[${loop.index}].answer">
                                                <label for="Q${detail.question.id}D">D</label>
                                            </div>
                                        </c:when>
                                        <c:when test="${detail.question.type.name=='FILL'}">
                                            <p>
                                                <input name="exam.detailList[${loop.index}].answer" type="text" class="form-control"/>
                                            </p>
                                        </c:when>
                                        <c:when test="${detail.question.type.name=='ASK'}">
                                            <p>
                                                <textarea name="exam.detailList[${loop.index}].answer" class="form-control"></textarea>
                                            </p>
                                        </c:when>
                                        <c:when test="${detail.question.type.name=='ESSAY'}">
                                            <p>
                                                <textarea name="exam.detailList[${loop.index}].answer" class="form-control"></textarea>
                                            </p>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                        </c:forEach>
                        <div class="mail-box">
                            <div class="mail-body">
                                <button id="submit-button" class="btn btn-primary" type="submit">提交试卷</button>
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
<script>
    var seconds = '${exam.examTime}';//倒计时总秒数量
    var intDiff = parseInt(seconds);
    function timer(intDiff){
        window.setInterval(function(){
            var day=0,
                hour=0,
                minute=0,
                second=0;//时间默认值       
            if(intDiff > 0){
                day = Math.floor(intDiff / (60 * 60 * 24));
                hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
                minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
                second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
            }
            if (minute <= 9) minute = '0' + minute;
            if (second <= 9) second = '0' + second;
            $('#hour_show').html('<s id="h"></s>'+hour+'时');
            $('#minute_show').html('<s></s>'+minute+'分');
            $('#second_show').html('<s></s>'+second+'秒');
            intDiff--;
        }, 1000);
    }
    $(function(){
        //倒计时
        timer(intDiff);
        console.log("定时的时间：");
        //定时交卷
        setTimeout(function(){
            alert("考试时间到，即将自动交卷");
            $("#submit-button").trigger('click');
        },intDiff*1000);
    });
</script>
</body>
</html>
