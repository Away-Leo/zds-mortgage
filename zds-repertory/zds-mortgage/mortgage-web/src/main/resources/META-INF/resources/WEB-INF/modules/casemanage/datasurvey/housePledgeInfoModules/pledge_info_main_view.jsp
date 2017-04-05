<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!-- 押品信息查看tab -->
<div class="page-box">
	<div class="p5">
		<div class="info-tab" style="position: relative;">
			<ul class="tabs houseBox">
				<c:forEach items="${voList}" var="housePropertyVo" varStatus="status">
					<c:choose>
						<c:when test="${status.index==0}">
							<li class="tabs-on" id="house_${housePropertyVo.id}" ><a href="javascript:void(0);">${housePropertyVo.communityName}</a></li>
						</c:when>
						<c:otherwise>
							<li id="house_${housePropertyVo.id}"><a href="javascript:void(0);">${housePropertyVo.communityName}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
			<div class="tabcontents" id="dynamic-viewPledge"></div>
		</div>
	</div>
</div>

<script type="text/javascript">
	seajs.use([ 'jquery', 'zd/switch', 'zd/jquery.zds.page.callback' ],function($, tabSwitch,callback) {
		//押品信息(初始化)
		var url = '<z:ukey key="com.cnfh.rms.marketing.houseProperty.viewHousePropertyById" context="admin"/>';
		
		//初始化一个	dynamic-viewPledge
		$("#dynamic-viewPledge").empty();
		//清除抵押情况的zdialog
		$("#pledgeEditDiv").closest(".zd-window").remove();
		//清除产权信息的zdialog
		$("#propertyEditDiv").closest(".zd-window").remove();
		//初始化
		$("#dynamic-viewPledge").load(url + "&housePropertyId=${voList[0].id}");
		
		//点击li,load新页面
		$(".houseBox li").live("click",function() {
			//移出选中样式
			$(this).prevAll().removeClass("tabs-on");
			//添加选择样式
			$(this).addClass("tabs-on");
			var ids = $(this).attr("id");
			var id = ids.split("_");
			var idIndex = id[1];
			//删除以前的内容
			$("#dynamic-viewPledge").empty();
			$("#pledgeEditDiv").closest(".zd-window").remove();
			$("#propertyEditDiv").closest(".zd-window").remove();
			$("#dynamic-viewPledge").load(url + "&housePropertyId=" + idIndex);
			tabSwitch.init(); //初始化页签
		});
	});
</script>