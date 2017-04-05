<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>还款计划配置</title>
</head>
<body>
<div id="repayPlanConfigForm">
	<!-- 查询区域 -->
	<div class="page-box">
		<div class="page-title">查询</div>
		<div class="p10">
			<form id="queryRepayPlanConfig" class="zui-form form-search" method="post" zdata-options="{}">
				<dl class="form-item">
					<dt class="title">费用项目:</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input zui-validatebox" validate-type="Length[0-60]" id="feeName" name="feeName">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">收款方:</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input zui-validatebox" validate-type="Length[0-60]" id="receiverName" name="receiverName">
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
					<input type="button" class="btn-blue" id="searchRepayPlanConfig" value="查询" />
					<input type="button" class="btn-gray" id="resetRepayPlanConfig" value="重置" />
				</dl>
			</form>
		</div>
	</div>
	<!-- 列表区域 -->
	<div class="page-box">
		<div class="page-title">还款计划</div>
		<div class="p10">
			<div id="tb-repayPlanConfig" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.repayPlanConfig.getList' context='admin'/>&productVo.id=${product.id }","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#repayPlanConfig_toolbar"}'>
				<table>
        			<tr>
            			<th data-options="field:feeName,width:30%">费用项目</th>
            			<th data-options="field:receiverName,width:30%">收款方</th>
            			<th data-options="field:isEnable,width:15%" formatter="formatIsEnable">是否启用</th>
            			<th data-options="field:id,width:25%" formatter="planconfigFunction">操作</th>
			        </tr>
				</table>
			</div>
			<div id="repayPlanConfig_toolbar">
				<a class="zui-toolbar" iconCls="icon-btn08" text="新增" buttonCls="btn-blue" handler="addRepayPlanConfig"></a>
        	</div>
		</div>
	</div>
</div>

<div id="editRepayPlanConfigDialog" style="display: none">
</div>

</body>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
	
	CALLBACK.addRepayPlanConfig=function(){
		var	url = '<z:ukey key="com.zdsoft.finance.repayPlanConfig.dialog" context="admin"/>&productId=${product.id}';
		$('#editRepayPlanConfigDialog').load(url,function(){
			
		});
	}

	$('#searchRepayPlanConfig').on('click',function(){
       	var formArray=$("#queryRepayPlanConfig").serializeArray();
       	$('#tb-repayPlanConfig').ZTable("reload", formArray);
	});
	
	$('#resetRepayPlanConfig').on('click',function(){
    	$('#feeName').val('');
    	$('#receiverName').val('');
    	$('#repayPlanConfigIsEnable').ZCombobox('setValue',true);
		$('#tb-repayPlanConfig').ZTable("reload", {isEnable:true});
    });
	
	CALLBACK.formatIsEnable=function(rowData,index){
		if(rowData.isEnable){
			return '是';
		}else{
			return '否';
		}
	}
	
	CALLBACK.planconfigFunction=function(rowData,index){
		var id=rowData.id;
		if(!id){
			$.ZMessage.error("错误", "未获取到主键", function () {
                $(".zd-message").ZWindow("close");
            });
			return ;
		}
		var str = "<a title='修改' class='btn-blue' onclick='editRepayPlanConfig'>修改</a>" +
	    	"&nbsp;&nbsp;<a title='删除' class='btn-blue' onclick='deleteRepayPlanConfig'>删除</a>";
		return str;
	}
	
	CALLBACK.editRepayPlanConfig=function(index,rowData){
		var	url = '<z:ukey key="com.zdsoft.finance.repayPlanConfig.dialog" context="admin"/>&productId=${product.id}&repayPlanConfigId='+rowData.id;
		$('#editRepayPlanConfigDialog').load(url,function(){
			
		});
	}
	
	CALLBACK.deleteRepayPlanConfig=function(index,rowData){
		$.ZMessage.question("警告", "确认删除？", function () {
			$(".zd-message").ZWindow("close");
			$.ajax({
                type: 'post',
                url: '<z:ukey key="com.zdsoft.finance.repayPlanConfig.delete" context="admin"/>',
                data: {repayPlanConfigId:rowData.id},
                dataType: 'json',
                success: function (data) {
                    if (data.resultStatus == 0) {
                    	$('#tb-repayPlanConfig').ZTable("reload", {});
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
	$.ZUI.initForms("#repayPlanConfigForm");
	$.ZUI.initGrid("#repayPlanConfigForm");
});
</script>
</html>