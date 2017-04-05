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
	<!-- 列表区域 -->
	<div class="page-box">
		<div class="page-title">资金计划规则</div>
		<div class="p10">
			<div id="tb-creditEntrustPlanConfig" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.creditEntrustPlanConfig.getList' context='admin'/>&productVo.id=${product.id }","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#creditEntrustPlanConfig_toolbar"}'>
				<table>
        			<tr>
            			<th data-options="field:capitalistName,width:20%">资方</th>
            			<th data-options="field:capitalPlanName,width:20%">资金计划</th>
            			<th data-options="field:minPercentage,width:15%">最低评估成数(包含)</th>
            			<th data-options="field:maxPercentage,width:15%">最高评估成数(不包含)</th>
            			<th data-options="field:isEnable,width:10%" formatter="formatIsEnable">是否启用</th>
            			<th data-options="field:id,width:20%" formatter="creditFunction">操作</th>
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
	CALLBACK.formatIsEnable=function(rowData,index){
		if(rowData.isEnable){
			return '是';
		}else{
			return '否';
		}
	}
	
	CALLBACK.creditFunction=function(rowData,index){
		var id=rowData.id;
		if(!id){
			$.ZMessage.error("错误", "未获取到主键", function () {
                $(".zd-message").ZWindow("close");
            });
			return ;
		}
		var str = "<a title='修改' class='btn-blue' onclick='editCreditEntrustPlanConfig'>修改</a>" +
    	"&nbsp;&nbsp;<a title='删除' class='btn-blue' onclick='deleteCreditEntrustPlanConfig'>删除</a>";
		return str;
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