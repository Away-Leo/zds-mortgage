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
	<c:forEach  items="${expandFileds }" var="expandFiled" varStatus="status">
		<c:set var ="expandFiledValue" scope="page" value="${expandFiled.filed }"></c:set>
		
		<c:if test="${expandFiled.viewType == 'TEXT' }">
			<%i+= 1; %>
		</c:if>
		<c:if test="${expandFiled.viewType == 'TEXTAREA' }">
			<%i += 2; %>
		</c:if>
		<% if(i==3){ %>
			<th></th><td></td></tr><tr>
			<th>${expandFiled.label }</th><td colspan="3">${expandFiledValue}</td></tr><tr>
			<% i = 0; %>
		<% }else if(i == 2){ %>
			<c:if test="${expandFiled.viewType == 'TEXT' }">
				<th>${expandFiled.label }</th><td>${expandFiledValue}</td>
				</tr><tr>
			</c:if>
			<c:if test="${expandFiled.viewType == 'TEXTAREA' }">
				<th>${expandFiled.label }</th><td colspan="3">${expandFiledValue}</td></tr><tr>
			</c:if>
			<% i = 0; %>
		<% }else{ %>
			<th>${expandFiled.label }</th><td>${clazzNm[expandFiledValue]}</td>
		<% } %>
	</c:forEach>
	<% if(i % 2 == 1){ %>
		<th></th><td></td></tr><tr>
	<% } %>
		
	</tr>
</c:if>

<!--  
<tr>
	<th>这里是名称：</th>
	<td>这里是内容，这里是内容</td>
	<th></th>
	<td></td>
</tr>
<tr>
	<th>这里是名称这里是名称：</th>
	<td colspan="3">这里是内容，这里是内容,这里是内容，这里是内容,这里是内容，
		这里是内容,这里是内容，这里是内容，这里是内容，这里是内容，这里是内容，
		这里是内容，这里是内容，这里是内容,这里是内容，这里是内容,这里是内容，
		这里是内容,这里是内容，这里是内容,这里是内容，这里是内容，这里是内容,
		这里是内容，这里是内容，这里是内容，这里是内容，这里是内容
	</td>
</tr>
-->





