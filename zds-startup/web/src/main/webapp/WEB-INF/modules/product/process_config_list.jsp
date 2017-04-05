<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>流程配置</title>
</head>
<body>
<div id="processConfigForm">
	<!-- 列表区域 -->
	<div class="page-box">
		<div class="page-title">流程配置</div>
		<div class="p10">
			<div id="tb-processConfig" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.processConfig.getList' context='admin'/>&productVo.id=${product.id }","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#processConfig_toolbar"}'>
				<table>
        			<tr>
            			<th data-options="field:processName,width:20%">流程名称</th>
            			<th data-options="field:processCodeName,width:20%">代码</th>
            			<th data-options="field:processKey,width:30%">流程Key</th>
            			<th data-options="field:isEnable,width:10%" formatter="formatIsEnable">是否启用</th>
            			<th data-options="field:id,width:20%" formatter="processFunction">操作</th>
			        </tr>
				</table>
			</div>
			<div id="processConfig_toolbar">
				<a class="zui-toolbar" iconCls="icon-btn08" text="新增" buttonCls="btn-blue" handler="addProcessConfig"></a>
        	</div>
		</div>
	</div>
</div>

<div id="editProcessConfigDialog" style="display: none">
</div>

</body>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
	
	CALLBACK.addProcessConfig=function(){
		var	url = '<z:ukey key="com.zdsoft.finance.processConfig.dialog" context="admin"/>&productId=${product.id}';
		$('#editProcessConfigDialog').load(url,function(){});
	}
	
	CALLBACK.formatIsEnable=function(rowData,index){
		if(rowData.isEnable){
			return '是';
		}else{
			return '否';
		}
	}
	
	CALLBACK.processFunction=function(rowData,index){
		var id=rowData.id;
		if(!id){
			$.ZMessage.error("错误", "未获取到主键", function () {
                $(".zd-message").ZWindow("close");
            });
			return ;
		}
		var str = "<a title='修改' class='btn-blue' onclick='editProcessConfig'>修改</a>" +
    	"&nbsp;&nbsp;<a title='删除' class='btn-blue' onclick='deleteProcessConfig'>删除</a>";
		return str;
	}
	
	CALLBACK.editProcessConfig=function(index,rowData){
		var	url = '<z:ukey key="com.zdsoft.finance.processConfig.dialog" context="admin"/>&productId=${product.id}&processConfigId='+rowData.id;
		$('#editProcessConfigDialog').load(url,function(){});
	}
	
	CALLBACK.deleteProcessConfig=function(index,rowData){
		$.ZMessage.question("警告", "确认删除？", function () {
			$(".zd-message").ZWindow("close");
			$.ajax({
                type: 'post',
                url: '<z:ukey key="com.zdsoft.finance.processConfig.delete" context="admin"/>',
                data: {processConfigId:rowData.id},
                dataType: 'json',
                success: function (data) {
                    if (data.resultStatus == 0) {
                    	$('#tb-processConfig').ZTable("reload", {});
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
	$.ZUI.initForms("#processConfigForm");
	$.ZUI.initGrid("#processConfigForm");
});
</script>
</html>