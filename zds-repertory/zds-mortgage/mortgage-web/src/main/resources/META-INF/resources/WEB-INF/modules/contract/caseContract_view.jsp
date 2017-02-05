<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file='../common/common_js.jsp'%>
<title>合同套打预览</title>
</head>
<body>
	<div class="page-box">


		<table>
			<tr>
				<td>打印第<input id="page" />页
					<button class="btn-blue" id="printPage">打印当前页</button></td>
				<td>模板名</td>
				<td>:《苏州贷款合同》</td>
				<td>文件编号</td>
				<td><input id="page" value="套打需填写" /></td>
				<td>案件号</td>
				<td>1125-445</td>
				<td>合同编号</td>
				<td>666</td>
				<td><button class="btn-blue" id="printAll">全部打印</button></td>
			</tr>
		</table>
	</div>
</body>
</html>