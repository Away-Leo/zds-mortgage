<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8" import="java.util.List,com.zdsoft.finance.common.auto.entity.ExpandFiled"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	// 扩展字段总大小
	List<ExpandFiled> expandFileds = (List<ExpandFiled>) request.getAttribute("expandFileds");
	int i = 0;
%>
<!-- 判断是否拥有扩展字段 -->
<c:if test="${fn:length(expandFileds)>0 }">
	<tr>
	<c:forEach  items="${expandFileds }" var="expandFiled">
		<c:if test="${expandFiled.viewType == 'TEXT' }">
			<dl class="form-item">
				<dt class="title">${expandFiled.label }：</dt>
				<dd class="detail">
					<label><input class="zui-input" id="${expandFiled.filed }" name="${expandFiled.filed }"></label>
				</dd>
			</dl>
		</c:if>
		<c:if test="${expandFiled.viewType == 'TEXTAREA' }">
			<dl class="form-item block">
			   <dt class="title">${expandFiled.label }：</dt>
			   <dd class="detail">
				  <label><textarea class="zui-area" id="${expandFiled.filed }" name="${expandFiled.filed }"></textarea></label>
			   </dd>
			</dl>
		</c:if>
	
	</c:forEach>
	</tr>
</c:if>
<!-- 
<dl class="form-item">
	<dt class="title">会议纪要号：</dt>
	<dd class="detail">
		<label><input class="zui-input" id="meetingMinutesNo" name="meetingMinutesNo"></label>
	</dd>
</dl>

<dl class="form-item span2">
   <dt class="title">意见</dt>
   <dd class="detail">
	  <label><textarea class="zui-area" id="opinion" name="opinion"></textarea></label>
   </dd>
</dl>
 -->



