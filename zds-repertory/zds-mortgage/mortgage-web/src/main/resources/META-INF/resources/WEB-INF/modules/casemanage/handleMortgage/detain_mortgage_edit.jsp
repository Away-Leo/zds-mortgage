<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%> 
<!-- 查册入押 -->
<div class="opinionDiv">
	<!-- 复制户籍地址 -->
	<input type="hidden" id="liveCounty"  value="${registrationAddressVo.district }"/>
    <input type="hidden" value="${registrationAddressVo.address }" id="liveAddr">
	<!-- 家庭地址 -->
    <input type="hidden" id="homeDistrict" name="homeAddressVo.district" value="${homeAddressVo.district }"/>
    <input type="hidden" value="${homeAddressVo.address }" id="homePlace">
	<!-- 单个押品信息列表 -->
	<%@ include file="common/house_single_list.jsp" %>
	<!-- 抵押情况 -->
	<%@ include file="../../marketing/beforehandapply/pledge_info_edit.jsp"%>
	 <!-- 产权状态 -->
	<%@ include file="../datasurvey/housePledgeInfoModules/pledge_status_edit.jsp"%>
	<!--  产权人信息 -->
	<%@ include file="../../marketing/beforehandapply/property_owner_edit.jsp"%>
	<!-- 查册入押 -->
	<%@ include file="detain_edit.jsp" %>
</div>