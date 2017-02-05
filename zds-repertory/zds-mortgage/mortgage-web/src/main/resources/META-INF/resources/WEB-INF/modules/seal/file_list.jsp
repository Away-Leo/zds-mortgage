<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@ include file='../common/common_js.jsp'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>文件列表</title>
</head>
<body>
	<div id="filesTable" class="zui-datagrid"
		zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.seal.filesList" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","rows":5}'>
		<table>
			<thead>
				<tr>
					<th data-options="field:belongClass">所属分类</th>
					<th data-options="field:className">类别名称</th>
					<th data-options="field:pingyingCode">拼音码</th>
					<th data-options="field:numCode">数字记忆码</th>
					<th data-options="field:fileName">文件名</th>
					<th data-options="field:documentName">文档名称</th>
					<th data-options="field:createBy">上传人</th>
					<th data-options="field:createDate">上传时间</th>
					<th data-options="field:from">来源</th>
					<th data-options="field:id,width:200" formatter="operate">操作</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
</html>