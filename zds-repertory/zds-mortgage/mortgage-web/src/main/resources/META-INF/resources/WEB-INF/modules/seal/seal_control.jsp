<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file='../common/common_js.jsp'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>打印资料明细清单</title>

</head>
<body>
	<div id="printDetailsTable<%= request.getParameter("id")%>" class="zui-datagrid"
		zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.seal.printDetailsList" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":false,"idField":"id","tableCls":"table-index"}'>
		<table>
			<thead>
				<tr>
					<th data-options="field:detailsName">资料名称</th>
					<th data-options="field:originalCount" formatter="appendText">原件（份数）</th>
					<th data-options="field:copyCount" formatter="appendText">复印件（份数）</th>
					<th data-options="field:appSeal" formatter="appendCheckBox">申请公章</th>
					<th data-options="field:otherDesc" formatter="appendTextAera">其他说明</th>

				</tr>
			</thead>

		</table>
	</div>
</body>
</html>