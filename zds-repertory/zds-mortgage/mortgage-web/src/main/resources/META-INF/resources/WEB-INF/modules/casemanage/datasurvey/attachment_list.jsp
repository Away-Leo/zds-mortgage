<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- 风险信息 -->
<c:if test="${not empty errorMsg }">页面初始化出现异常，请联系管理员！异常信息为：${errorMsg }</c:if>
<c:if test="${empty errorMsg }">
	<div id="attachment_list"></div>
</c:if>
<script>
	seajs.use([ 'jquery', 'zd/jquery.zds.loading'], 
			function($) {
		//加载附件列表
		$("#attachment_list").load('<z:ukey key="com.zdsoft.finance.fileStore.fileShowListPage" context="admin"/>'+
                '&productId=${productId}&linkCode=${linkCode}&caseApplyId=${caseApplyId}&businessId=${businessId}');
		
	});
</script>