<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--抵押信息开始-->
<div class="opinionDiv">
	<!-- 房产信息 -->
	<%@ include file="collateral_info_edit.jsp" %>
	
	<!-- 引入抵押情况 -->
	<%@ include file="../../../marketing/beforehandapply/pledge_info_edit.jsp"%>
	
	<!-- 产权状态 -->
	<%@ include file="pledge_status_edit.jsp"%>
	
	<!--  产权人信息 -->
	<%@ include file="../../../marketing/beforehandapply/property_owner_edit.jsp"%>
	
</div>
<!--抵押信息结束-->