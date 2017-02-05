<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>资金计划配置</title>
</head>
<body>
<div id="creditEntrustPlanConfigForm">
	<!-- 查询区域 -->
	<!-- 
	<div class="page-box">
		<div class="page-title">查询</div>
		<div class="p10">
			<form id="queryCreditEntrustPlanConfig" class="zui-form form-search" method="post" zdata-options="{}">
				<dl class="form-item">
					<dt class="title">费用项目:</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input zui-validatebox" validate-type="Length[0-60]" id="feeNm" name="feeNm">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">收款方:</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input zui-validatebox" validate-type="Length[0-60]" id="receiverNm" name="receiverNm">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">是否启用:</dt>
					<dd class="detail">
						<input class="zui-combobox zui-validatebox" type="hidden" validate-type="" id="repayPlanConfigIsEnable"
	                        data-data="[{'id':'true','text':'是'},{'id':'false','text':'否'}]" value="true"
	                        data-valuefield="id" data-textfield="text" name="isEnable">
					</dd>
				</dl>
				<dl class="form-btn">
					<input type="button" class="btn-blue" id="searchCreditEntrustPlanConfig" value="查询" />
					<input type="button" class="btn-gray" id="resetCreditEntrustPlanConfig" value="重置" />
				</dl>
			</form>
		</div>
	</div>
	 -->
	<!-- 列表区域 -->
	<div class="page-box">
		<div class="page-title">资金计划规则</div>
		<div class="p10">
			<div id="tb-creditEntrustPlanConfig" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.creditEntrustPlanConfig.getList' context='admin'/>&productVo.id=${product.id }","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#creditEntrustPlanConfig_toolbar"}'>
				<table>
        			<tr>
            			<th data-options="field:capitalistName,width:20%">资方</th>
            			<th data-options="field:creditEntrustName,width:20%">资金计划</th>
            			<th data-options="field:minEvaluateNum,width:15%">最低评估成数(包含)</th>
            			<th data-options="field:maxEvaluateNum,width:15%">最高评估成数(不包含)</th>
            			<th data-options="field:isEnable,width:10%" formatter="formatIsEnable">是否启用</th>
            			<th data-options="field:id,width:20%" formatter="formatId">操作</th>
			        </tr>
				</table>
			</div>
			<div id="creditEntrustPlanConfig_toolbar">
				<a class="zui-toolbar" iconCls="icon-btn08" text="新增" buttonCls="btn-blue" handler="addCreditEntrustPlanConfig"></a>
        	</div>
		</div>
	</div>
</div>

<div id="editCreditEntrustPlanConfigDialog" style="display: none">
</div>

</body>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
	
	CALLBACK.addCreditEntrustPlanConfig=function(){
		var	url = '<z:ukey key="com.zdsoft.finance.creditEntrustPlanConfig.dialog" context="admin"/>&productId=${product.id}';
		$('#editCreditEntrustPlanConfigDialog').load(url,function(){
			
		});
	}
	/* 
	$('#searchCreditEntrustPlanConfig').on('click',function(){
		var flag=$.ZUI.validateForm($('#queryRepayPlanConfig'));
    	if(flag){
        	var formArray=$("#queryRepayPlanConfig").serialize();
        	formArray=decodeURIComponent(formArray, true);
        	$('#tb-repayPlanConfig').ZTable("reload", formArray);
    	}
	});
	
	$('#resetCreditEntrustPlanConfig').on('click',function(){
    	$('#feeNm').val('');
    	$('#receiverNm').val('');
    	$('#repayPlanConfigIsEnable').ZCombobox('setValue',true);
		$('#tb-repayPlanConfig').ZTable("reload", {isEnable:true});
    });
	 */
	CALLBACK.formatIsEnable=function(rowData,index){
		if(rowData.isEnable){
			return '是';
		}else{
			return '否';
		}
	}
	
	CALLBACK.formatId=function(rowData,index){
		var id=rowData.id;
		if(!id){
			$.ZMessage.error("错误", "未获取到主键", function () {
                $(".zd-message").ZWindow("close");
            });
			return ;
		}
		
		return '<a href="javaScript:void(0)" onclick="editCreditEntrustPlanConfig"><button class="btn-blue">编辑</button></a>'
    	+
    	'&nbsp;&nbsp;'+'<a href="javaScript:void(0)" onclick="deleteCreditEntrustPlanConfig"><button class="btn-blue">删除</button></a>';
	}
	
	CALLBACK.editCreditEntrustPlanConfig=function(index,rowData){
		var	url = '<z:ukey key="com.zdsoft.finance.creditEntrustPlanConfig.dialog" context="admin"/>&productId=${product.id}&creditEntrustPlanConfigId='+rowData.id;
		$('#editCreditEntrustPlanConfigDialog').load(url,function(){
			
		});
	}
	
	CALLBACK.deleteCreditEntrustPlanConfig=function(index,rowData){
		$.ZMessage.question("警告", "确认删除？", function () {
			$(".zd-message").ZWindow("close");
			$.ajax({
                type: 'post',
                url: '<z:ukey key="com.zdsoft.finance.creditEntrustPlanConfig.delete" context="admin"/>',
                data: {creditEntrustPlanConfigId:rowData.id},
                dataType: 'json',
                success: function (data) {
                    if (data.resultStatus == 0) {
                    	$('#tb-creditEntrustPlanConfig').ZTable("reload", {});
                    	$.ZMessage.success("提示", "删除成功", function () {
    	                    $(".zd-message").ZWindow("close");
    	                });
                    }else{
                    	$.ZMessage.error("错误", data.msg, function () {
    	                    $(".zd-message").ZWindow("close");
    	                });
                    }
                },
                error: function () {
                	$.ZMessage.error("错误", "系统异常,请联系管理员", function () {
                        $(".zd-message").ZWindow("close");
                    });
                }
            });
        });
	}
	
	//初始化页面
	$.ZUI.initForms("#creditEntrustPlanConfigForm");
	$.ZUI.initGrid("#creditEntrustPlanConfigForm");
});
</script>
</html>