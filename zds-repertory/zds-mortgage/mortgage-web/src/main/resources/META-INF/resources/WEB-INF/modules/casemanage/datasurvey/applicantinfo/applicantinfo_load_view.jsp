<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 客户信息tab -->
<div class="page-box">
	<div class="p5">
		<div class="info-tab" style="position: relative;">
			<ul class="tabs" id="page-tab">
				<c:forEach items="${listVo}" var="customers" varStatus="status">
					<c:choose>
						<c:when test="${status.index==0}">
							<li class="tabs-on" id="${customers.id}" ><a href="javascript:void(0);">${customers.joinTypeName}:${customers.customerName}</a></li>
						</c:when>
						<c:otherwise>
							<li id="${customers.id}" ><a href="javascript:void(0);">${customers.joinTypeName}:${customers.customerName}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
			<div class="tabcontents" id="dynamic-tabcontents-view"></div>
		</div>
	</div>
</div>
<script>
	seajs.use([ 'jquery', 'zd/switch', 'zd/jquery.zds.page.callback','zd/jquery.zds.message' ],function($, tabSwitch,callback) {
		var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.viewApplicantInfomation" context="admin"/>';
		
		//初始化一个	dynamic-tabcontents-view
		$("#dynamic-tabcontents-view").empty();
		$("#dynamic-tabcontents-view").load(url + "&customerId=${listVo[0].id}&caseApplyId=" + caseApplyId);
		$("#page-tab li").live("click",function() {
			loadContent(this);
		});
		
		function loadContent(object){
		 	var that = $(object);
			//移出选中样式
			that.prevAll().removeClass("tabs-on");
			//添加选择样式
			that.addClass("tabs-on");
			var customerId = that.attr("id");//id
			//删除以前的内容
			$("#dynamic-tabcontents-view").empty();
			$("#dynamic-tabcontents-view").load(url + "&customerId=" + customerId+"&caseApplyId=" + caseApplyId);
			tabSwitch.init(); //初始化页签
		}
	});
</script>