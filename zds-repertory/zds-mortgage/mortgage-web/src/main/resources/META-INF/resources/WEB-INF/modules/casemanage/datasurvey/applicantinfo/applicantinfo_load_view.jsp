<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="frm-content">
	<div class="page-box">
		<div class="p10">
			<div class="info-tab" style="position: relative;">
				<ul class="tabs" id="page-tab">
					<c:forEach items="${vo}" var="customers" varStatus="status">
						<c:choose>
							<c:when test="${status.index==0}">
								<li class="tabs-on" id="cust_${customers.id}" ><a href="javascript:void(0);">${customers.joinTypeName}:${customers.customerName}</a></li>
							</c:when>
							<c:otherwise>
								<li id="cust_${customers.id}" ><a href="javascript:void(0);">${customers.joinTypeName}:${customers.customerName}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
				<div class="tabcontents" id="dynamic-tabcontents"></div>
			</div>
		</div>

	</div>
</div>
<script>

	seajs.use([ 'jquery', 'zd/switch', 'zd/jquery.zds.page.callback','zd/jquery.zds.message' ],function($, tabSwitch,callback) {
		var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.view" context="admin"/>';
		
		//初始化一个	dynamic-tabcontents
		$("#dynamic-tabcontents").empty();
		$("#dynamic-tabcontents").load(url + "&customerId=${vo[0].id}&caseApplyId=${caseApplyId}");
		$("#page-tab li").live("click",function() {
				loadContent(this);
		});
		
	
		function loadContent(object){
			 	var that = $(object);
				//移出选中样式
				that.prevAll().removeClass("tabs-on");
				//添加选择样式
				that.addClass("tabs-on");
				var id = that.attr("id");
				var ids = id.split("_");
				var customerId = ids[1];//id
				//删除以前的内容
				$("#dynamic-tabcontents").empty();
				$("#contactEditDiv").closest(".zd-window").remove();
				$("#workUnitEditDiv").closest(".zd-window").remove();
				$("#dynamic-tabcontents").load(url + "&customerId=" + customerId+'&caseApplyId=${caseApplyId}');
				tabSwitch.init(); //初始化页签
		}
		
});
</script>