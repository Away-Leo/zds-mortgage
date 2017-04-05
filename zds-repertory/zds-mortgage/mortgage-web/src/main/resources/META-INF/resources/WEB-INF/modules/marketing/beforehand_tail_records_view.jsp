<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 案件跟踪回显 -->
<div class="page-box">
	<div class="page-title">案件跟踪信息</div>
	<c:if test="${not empty caseTailVos }">
		<c:forEach items="${caseTailVos }" var="caseTailVo">
			<div class="p5">
				<table class="table-detail">
					<tr>
						<td class="td-title pct10">营销人</td>
						<td class="pct20">${caseTailVo.marketingPersonName }</td>
						<td class="td-title pct10">跟踪时间时间</td>
						<td class="pct20">${caseTailVo.tailDateView }</td>
						<td class="td-title pct10"></td>
						<td class="pct20"></td>
			
					</tr>
					<tr>
						<td class="td-title">跟踪内容</td>
						<td colspan="5">${caseTailVo.tailContent }</td>
					</tr>
				</table>
			</div>
		</c:forEach>
</c:if>
</div>