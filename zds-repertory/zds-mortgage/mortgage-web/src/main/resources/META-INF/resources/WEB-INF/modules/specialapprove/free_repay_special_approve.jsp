<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../common/common_js.jsp'%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自由还款特批审批</title>
</head>
<body>
	<c:if test="${viewType == 0 }"><!-- 编辑页面 -->
       	<div class="save">
			<button id="btn-save" class="btn-blue mr10" >保存</button>
			<button id="btn-commit" class="btn-blue mr10">提交</button>
		</div>
   	</c:if>
	
	<input type="hidden" id="specialApproveManageId" value="${businessKey }"/><!-- 特批管理ID -->
	<!-- 引入案件基本信息 -->
	<%@ include file="public_case_apply_basic_info.jsp" %>
	<!-- 引入押品信息 -->
	<%@ include file="public_collateral_info.jsp" %>
	
	<!-- 备注 -->
	<div class="page-box">
		<h1 class="page-title">备注</h1>
		<div class="p5">
			<table class="table-detail">
				<tr>
					<td class="td-title pct10">申请的具体原因及内容(请详细列明)</td>
					<td colspan="5">
                         <label>
                         	<c:if test="${viewType == 1 || viewType == 2}"><!-- 查看页面 -->
                         		<textarea id="remark" class="zui-area row-width zui-validatebox" 
                       		 	validate-type="Length[0-512]" placeholder="最多可以输入512个字符" disabled="disabled">${remark }</textarea>
                         	</c:if>
                         	<c:if test="${viewType == 0 }"><!-- 编辑页面或流程中的编辑页面 -->
                         		<textarea id="remark" class="zui-area row-width zui-validatebox" 
                       		 	validate-type="Length[0-512]" placeholder="最多可以输入512个字符" >${remark }</textarea>
                         	</c:if>
                         </label>
					</td>
				</tr>		
			</table>
		</div>
	</div>
	<!-- 备注结束 -->
	
	<!-- 引入附件 -->
	<div id="freeAttachment"></div>
	
</body>

<script type="text/javascript">
	seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.table'],
		   function ($, callback) {
		//初始化页面
		$.ZUI.init();
		
		//保存方法
		function saveDate(isSubmit){
			var url = '<z:ukey key="com.zdsoft.finance.specialApprove.saveSpecialApproveFreeApply" context="admin"/>&jsoncallback=?';
			$.ajax({
				url:url,
				data:{"isSubmit":isSubmit,"specialApproveManageId":$("#specialApproveManageId").val(),
					"remark":$("#remark").val(),"caseApplyId":"${caseApplyId }"},
				dataType:"json",
				method:"post",
				success:function(data){
					if (data.resultStatus == 0){
						$("#specialApproveManageId").val(data.optional.specialApproveManageId);
						$.ZMessage.success("成功", data.msg, function () {
							if(isSubmit){
	                   			setTimeout(function(){
	     		                	ZDS_MESSAGE_CLIENT.refreshOpenner();
	     		                	ZDS_MESSAGE_CLIENT.closeSelf();
	     		                },200);
							}
	                    });
					}else{
						$.ZMessage.error("失败", data.msg, function(){});
					}
				}
			});
		}
		
		$("#btn-commit").click(function(){
			saveDate(true);
		});
		$("#btn-save").click(function(){
			saveDate(false);
		});
		
		$.ZUI.initDiv(".page-box");
		
		$("#freeAttachment").load('<z:ukey key="com.zdsoft.finance.fileStore.fileShowListPage" context="admin"/>'+'&productId=${fileStoreVo.productId }' +
		'&linkCode=${fileStoreVo.linkCode }&caseApplyId=${caseApplyId }&businessId=${fileStoreVo.businessId }');
	});
</script>
</html>