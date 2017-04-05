<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--抵押信息查看-->
<div class="opinionDiv">
	<!-- 房产信息 -->
	<%@ include file="collateral_info_view.jsp" %>
	
	<!-- 抵押情况 -->
	<%@ include file="../../../marketing/beforehandapply/pledge_info_view.jsp"%>
	
	<!-- 产权状态 -->
	<%@ include file="pledge_status_view.jsp"%>
	
	<!--  产权人信息 -->
	<%@ include file="../../../marketing/beforehandapply/property_owner_view.jsp"%>
</div>