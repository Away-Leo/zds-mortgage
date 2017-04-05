<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file='../common/common_js.jsp' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${localWebServer}/homePage/css/fanhuahoutai.css">
<title>新任务</title>
<script type="text/javascript">
	var taskUrl = '<z:ukey key="com.zdsoft.finance.homePage.findMyPendingTasks" context="admin"/>';
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
	var thisIframeId=window.name;
    //处理任务
    window.viewTasks = function(taskName,url,taskCandidateId,taskCandidateId) {
    	ZDS_MESSAGE_CLIENT.openMenuLink('myPendingTask',taskName,url + "&openMethod=tabs&parentIframeId="+thisIframeId);
	
	}
  //处理任务
    window.viewTask = function(taskName,url,taskCandidateId,taskCandidateId) {
    	$("#process_frame").load(taskUrl+'&_t=' + new Date().getTime());
	
	}
	function viewTask(taskName,url) {
		jQuery.openPortal({
			url : url,
			method : 'get',
			data : {},
			type : 'URL',
			model : true,
			maximize : true,
			openMethod : "div",
			onClose : function() {
				$("#test").empty();
				$("#test").closest("#process_frame").load(taskUrl+'&_t=' + new Date().getTime());
				//refreshPage();
			}
		});
	}

	$(function() {
		$("ul li em").each(function() {
			var value = $(this).html();
			$(this).html(formatTime(value));
		});
		//新任务绑定点击事情
		$("#refreshNewPage").attr("style","cursor:hand");
		$("#refreshNewPage").bind("click",function(){
			refreshPage();
		});
		//对[更多信息]的HREF属性添加对应的方法
		$("#more_taskList").attr("href","javascript:viewTaskList();");
	});
	
});	
//格式化时间
function formatTime(time) {
	time = String(time);
	if (time == null) {
		return time;
	}
	return time.substring(0, 10);
}
	
</script>
        <dl class="myTask">
            <dt ><i><a href="###" onclick="viewTask();">我的任务</a><span>${pendingTasks.totalRows == null ? 0 : pendingTasks.totalRows}</span></i><a href="##" onclick="pendingMore()">更多</a></dt>
            <c:forEach items="${pendingTasks.records}" var="pendingTask">
            	<dd><a href="###" onclick="viewTasks('${pendingTask.taskName}','${pendingTask.framePageUrl}','${pendingTask.taskInstanceId}','${pendingTaskl.taskCandidateId}')">${pendingTask.processNm}一${pendingTask.taskName}</a>
            	<span><fmt:formatDate value="${pendingTask.startTime}" pattern="yyyy/MM/dd"/></span></dd>
            </c:forEach>
            <c:if test="${pendingTasks.totalRows < 6}">
            	<c:forEach begin="0" end="${6 - pendingTasks.totalRows}" step="1">
            		<dd><a href="###"></a><span></span></dd>
            	</c:forEach>
            </c:if>
        </dl>
</html>