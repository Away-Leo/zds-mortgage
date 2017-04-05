<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty vo }">无家庭收入！</c:if>
<c:if test="${not empty vo }">
	<div class="info-tab" style="position: relative;">
		<ul class="tabs" id="page-tab">
			<c:forEach items="${vo}" var="familyIncome" varStatus="status">
				<c:choose>
					<c:when test="${status.index==0}">
						<li class="tabs-on" id="family_${familyIncome.id}"><a href="javascript:void(0);">${familyIncome.houseHolder}</a></li>
					</c:when>
					<c:otherwise>
						<li id="family_${familyIncome.id}"><a href="javascript:void(0);">${familyIncome.houseHolder}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</ul>
		<div class="tabcontents" id="familyIncomeview-tab"></div>
	</div>
</c:if>
<script>
	seajs.use([ 'jquery', 'zd/switch', 'zd/jquery.zds.page.callback','zd/jquery.zds.message' ],function($, tabSwitch,callback) {
		var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.familyincomeinfo.view" context="admin"/>';
		//初始化一个	dynamic-tabcontents
		$("#familyIncomeview-tab").empty();
		$("#familyIncomeview-tab").load(url + "&familyIncomeId=${vo[0].id}&caseApplyId=${caseApplyId}");
		
		var hasDeleteFlag=false;
	 	//点击li,load新页面
		$("#page-tab li").live("click",function() {
			if(!hasDeleteFlag){
				loadContent(this);
			}else{
				//已点击删除事件
				hasDeleteFlag=false;
			}
		});
		function loadContent(object){
		 	var that = $(object);
			//移出选中样式
			that.prevAll().removeClass("tabs-on");
			//添加选择样式
			that.addClass("tabs-on");
			var id = that.attr("id");
			var ids = id.split("_");
			var familyIncomeId = ids[1];//id
			//删除以前的内容
			$("#familyIncomeview-tab").empty();
			$("#contactEditDiv").closest(".zd-window").remove();
			$("#workUnitEditDiv").closest(".zd-window").remove();
			$("#familyIncomeview-tab").load(url + "&familyIncomeId=" + familyIncomeId+'&caseApplyId=${caseApplyId}');
			tabSwitch.init(); //初始化页签
			//默认没有点击删除事件
			hasDeleteFlag=false;
		}
	});
</script>