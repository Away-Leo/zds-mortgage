<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../common/common_js.jsp'%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>风险特批审批</title>
</head>
<body>
	<input type="hidden" id="specialApproveManageId" value="${businessKey }"/><!-- 特批管理ID -->
	<!-- 引入案件基本信息 -->
	<%@ include file="public_case_apply_basic_info.jsp" %>
	<!-- 引入押品信息 -->
	<%@ include file="public_collateral_info.jsp" %>
	
	<!-- 费用风险特项 -->
	<div class="page-box">
		<div class="p5">
			<div id="feeSpecialApproveTable" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.specialApprove.querySysFeeRules" context="admin"/>&businessKey=${businessKey }&jsoncallback=?","singleSelect":true,"pagination":false,"idField":"id","tableCls":"table-index","isRowNum":false }'>
				<table>
        			<tr>
            			<th data-options="field:index" formatter="serial">序号</th>
            			<th data-options="field:feeTypeName">费用类型</th>
            			<th data-options="field:expectedAmount">应收金额（元）</th>
            			<th data-options="field:standardAmount">标准应收金额（元）</th>
			        </tr>
				</table>
			</div>
		</div>
	</div>
	<!-- 费用风险特项结束 -->
	
	<!-- 备注 -->
	<div class="page-box">
		<h1 class="page-title">备注</h1>
		<div class="p5">
			<table class="table-detail">
				<tr>
					<td class="td-title pct10">申请的具体原因及内容(请详细列明)</td>
					<td colspan="5">
                         <label>
                       		<textarea id="remark" class="zui-area row-width zui-validatebox" validate-type="Length[0-512]"
                       		 	placeholder="最多可以输入512个字符" >${remark }</textarea>
                         </label>
					</td>
				</tr>		
			</table>
		</div>
	</div>
	<!-- 备注结束 -->
	
	<!-- 引入附件 -->
	<div id="feeAttachment"></div>
</body>

<script type="text/javascript">
	seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.table'],
		   function ($, callback) {
		//初始化页面
		$.ZUI.init();
		
		callback.serial=function(rowDate,value){
			if(value == 0){
				return "";
			}else return value;
		}
		
		//保存方法
		function saveData(isSubmit,datas){
			var url = '<z:ukey key="com.zdsoft.finance.specialApprove.saveOrCommitFeeSpecialApproveApply" context="admin"/>&jsoncallback=?';
			$.ajax({
				url:url,
				data:{"isSubmit":isSubmit,"specialApproveManageId":$("#specialApproveManageId").val(),
					"remark":$("#remark").val(),"caseApplyId":"${caseApplyId }"},
				dataType:"json",
				method:"post",
				success:function(data){
					if (data.resultStatus == 0){
						//执行回调函数
                        ZDS_WORKFLOW_CLIENT.callBackFuntion(datas,ZDS_WORKFLOW_PARAM._STATUS_SUCCESS,data.msg);
					}else{
						//执行回调函数
						ZDS_WORKFLOW_CLIENT.callBackFuntion(datas,ZDS_WORKFLOW_PARAM._STATUS_ERROR,data.msg);
					}
				}
			});
		}
		
		//保存
	    ZDS_WORKFLOW_CLIENT.saveFunction = function (datas) {
			//流程中有修改页面，需要提交业务数据操作
			saveData(false,datas);
    	}
		
	  	//提交方法
	    ZDS_WORKFLOW_CLIENT.submitFunction = ZDS_WORKFLOW_CLIENT.saveFunction;
		
	    $.ZUI.initDiv(".page-box");
	  	
		$("#feeAttachment").load('<z:ukey key="com.zdsoft.finance.fileStore.fileShowListPage" context="admin"/>'+'&productId=${fileStoreVo.productId }' +
		'&linkCode=${fileStoreVo.linkCode }&caseApplyId=${caseApplyId }&businessId=${fileStoreVo.businessId }');
	
	});
</script>
</html>