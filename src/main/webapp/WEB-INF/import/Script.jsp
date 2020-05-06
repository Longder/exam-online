<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="session"/>
<!-- 全局js -->
<script src="${ctx}/static/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx}/static/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${ctx}/static/js/plugins/validate/messages_zh.min.js"></script>
<script src="${ctx}/static/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${ctx}/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${ctx}/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${ctx}/static/js/plugins/layer/layer.min.js"></script>

<!-- 自定义js -->
<script src="${ctx}/static/js/hplus.js?v=4.1.0"></script>
<script type="text/javascript" src="${ctx}/static/js/contabs.js"></script>

<!-- 第三方插件 -->
<script src="${ctx}/static/js/plugins/pace/pace.min.js"></script>
<script src="${ctx}/static/js/plugins/peity/jquery.peity.min.js"></script>
<script src="${ctx}/static/js/plugins/iCheck/icheck.min.js"></script>
<script src="${ctx}/static/js/demo/peity-demo.js"></script>
<script src="${ctx}/static/js/plugins/layer/layer.min.js"></script>
<script src="${ctx}/static/js/plugins/layer/laydate/laydate.js"></script>
<script src="${ctx}/static/js/plugins/suggest/bootstrap-suggest.min.js"></script>
<script src="${ctx}/static/js/plugins/chosen/chosen.jquery.js"></script>
<script src="${ctx}/static/js/plugins/sweetalert/sweetalert.min.js"></script>
<script src="${ctx}/static/js/plugins/echarts/echarts-all.js"></script>
<script src="${ctx}/static/js/layui.js"></script>
<script src="${ctx}/static/js/modal.js"></script>

<script>
    //获取当前日期 yyyy-MM-dd
    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        return date.getFullYear() + seperator1 + month + seperator1 + strDate;
    }
    //清除查询框
    function emptyInput(){
        var inputArray=$("#searchForm input[type!='hidden']");
        inputArray.each(
            function (){
                $(this).val('');
            }
        )
    }
    //检查开始结束时间之间的大小关系
    function compareTime(beginTime,endTime){
        if(beginTime<=endTime){
            return true;
        }else{
            return false;
        }
    }
</script>
