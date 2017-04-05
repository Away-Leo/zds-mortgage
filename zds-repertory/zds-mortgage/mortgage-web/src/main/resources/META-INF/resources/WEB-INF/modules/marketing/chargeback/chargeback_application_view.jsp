<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../../common/common_js.jsp'%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>退单申请</title>
</head>
<body>
	<!-- 引入案件基本信息 -->
	<%@ include file="../../specialapprove/public_case_apply_basic_info.jsp" %>
	<!-- 引用押品信息 -->
	<%@ include file="house_properties_info.jsp" %>
	<!--  -->
	<div id="div-detain" class="page-box">
		<div class="page-title" id="div-detain-form">退单函详情</div>
		<div class="p5">
			<form id="chargebackForm" class="zui-form " method="post" enctype="multipart/form-data">
				<input type="hidden" id="caseApplyId" name="caseApplyId" value="${caseApplyId}">
				<input type="hidden" id=chargebackVoId name="id" value="${chargebackVo.id}">
				<table class="table-detail">
					<tr>
						<td class="td-title"><b class="c-red mr5">*</b>退单原因</td>
						<td>
							<dl class="form-item form-auto">
								<dd class="detail">
			                        <input class="zui-combobox zui-validatebox" validate-type="Require" id="chargebackCause" type="hidden" name="chargebackCause" value="${chargebackVo.chargebackCause }"
				                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0079"
				                           data-valuefield="fullcode" data-textfield="name" data-toggle="combobox" data-choose = "disable">
			                    </dd>
		                 </dl>  
						</td>
						<td class="td-title"></td>
						<td>
						</td>
						<td class="td-title"></td>
						<td>
						</td>
					</tr>
					<tr>
						<td class="td-title">备注</td>
						<td colspan="5">${chargebackVo.remark }</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!-- 附件信息 -->
	<div id="file_store"></div>
</body>
<script type="text/javascript">
seajs.use(['jquery', 'zd/jquery.zds.table','zd/jquery.zds.combobox','zd/jquery.zds.form',
           'zd/jquery.zds.page.callback'], function($,ZTable) {
	
	//加载附件div
	$("#file_store").load('<z:ukey key="com.zdsoft.finance.fileStore.fileShowListPage" context="admin"/>&productId=${productId}&linkCode=${linkCode}&caseApplyId=${caseApplyId}&businessId=${businessKey}');
	
	$.ZUI.init();
	});
</script>
</html>