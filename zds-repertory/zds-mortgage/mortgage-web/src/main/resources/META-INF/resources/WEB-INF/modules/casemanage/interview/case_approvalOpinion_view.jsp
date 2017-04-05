<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<div class ="frm-content" id="opinionDiva">
	<div class="page-box">
		<form class="zui-form">
		<table class="table-flow">
			<c:forEach items="${opinions}" var="item">
	              <tr>
	                  <th rowspan="2" id="taskLabel"><strong>${item.taskLabel}</strong></th>
	                  <th class="tr">审批人</th>
	                  <td class="tl" id="employeeName">${item.employeeName}</td>
	                  <th class="tr">审批时间</th>
	                  <td class="tl" id="issueTime">${item.issueTime}</td>
	                  <th class="tr">意见结论</th>
	                  <td class="tl" id="opinionLabel">${item.opinionLabel}</td>
	              </tr>
	              <tr>
	                  <th class="tr">审批意见</th>
	                  <td colspan="5" class="tl">
	                  	<c:if test="${item.opinionContent.length() < 98}">
	                  		${item.opinionContent}
	                  	</c:if>
	                  	<c:if test="${item.opinionContent.length() >=98}">
	                  		<div class='multi-ell'  width= '30%'>${item.opinionContent}</div>
	                  		<input type="hidden" id="opinionContent" value="${item.opinionContent}">
	                    	<a href="javascript:void(0);" class="ca-blue more">显示更多</a>
	                  	</c:if>
	                  </td>
	              </tr>
			</c:forEach>
		 </table>
		 </form>
	</div>
</div>
<script>
    seajs.use(['jquery', 'zd/iframe', 'zd/tools', 'zd/jquery.zds.form', 'zd/jquery.zds.button', 'zd/jquery.zds.seleter'], function ($, IFRAME, ZTOOlS) {

        //初始化表单
        //============================================================
        $.ZUI.initDiv('#opinionDiva');

        //显示更多
        //============================================================
        $('.more').on('click', function () {
			var opinionContent_temp =$(this).parent().parent().find("#opinionContent").val();
			var opinionContent =opinionContent_temp.substr(0,opinionContent_temp.length-5);
			var employeeName = $(this).parent().parent().prev().find("#employeeName").text();
			var issueTime = $(this).parent().parent().prev().find("#issueTime").text();
			var opinionLabel = $(this).parent().parent().prev().find("#opinionLabel").text();
			var taskLabel = $(this).parent().parent().prev().find("#taskLabel").text();
			var html = '<table class="table-flow"><tr> <th class="tr">审批人</th> <td>'+employeeName+'</td> <th class="tr">审批时间</th> <td>'+issueTime+'</td> <th class="tr">意见结论</th> <td colspan="2">'+opinionLabel+'</td></tr><tr><th class="tr vt">审批意见</th> <td colspan="6" class="tl">'+opinionContent+'</td></tr></table>';
			$.ZMessage.html(taskLabel, html);
		});

    });

</script>
