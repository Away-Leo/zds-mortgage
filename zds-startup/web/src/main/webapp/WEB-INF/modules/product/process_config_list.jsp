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
	<!-- 查询区域 -->
	<div class="page-box">
		<div class="page-title">查询</div>
		<div class="p10">
			<form id="queryProcessConfigForm" class="zui-form form-search" method="post" zdata-options="{}">
				<dl class="form-item">
					<dt class="title">流程名称:</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input zui-validatebox" validate-type="Length[0-60]" id="processName" name="processName">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">代码:</dt>
					<dd class="detail">
						<input class="zui-combobox zui-validatebox" type="hidden" validate-type="" id="processFormCd"
	                        data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=processFormCd"
	                        data-valuefield="fullcode" data-textfield="name" name="processFormCd">
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">是否启用:</dt>
					<dd class="detail">
						<input class="zui-combobox zui-validatebox" type="hidden" validate-type="" id="processConfigIsEnable"
	                        data-data="[{'id':'true','text':'是'},{'id':'false','text':'否'}]" value="true"
	                        data-valuefield="id" data-textfield="text" name="isEnable">
					</dd>
				</dl>
				<dl class="form-btn">
					<input type="button" class="btn-blue" id="searchProcessConfig" value="查询" />
					<input type="button" class="btn-gray" id="resetProcessConfig" value="重置" />
				</dl>
			</form>
		</div>
	</div>
	<!-- 列表区域 -->
	<div class="page-box">
		<div class="page-title">流程配置</div>
		<div class="p10">
			<div id="tb-processConfig" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.processConfig.getList' context='admin'/>&productVo.id=${product.id }","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#processConfig_toolbar"}'>
				<table>
        			<tr>
            			<th data-options="field:processName,width:20%">流程名称</th>
            			<th data-options="field:processFormNm,width:20%">代码</th>
            			<th data-options="field:processKey,width:30%">流程Key</th>
            			<th data-options="field:isEnable,width:10%" formatter="formatIsEnable">是否启用</th>
            			<th data-options="field:id,width:20%" formatter="formatId">操作</th>
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
		$('#editProcessConfigDialog').load(url,function(){
			
		});
	}

	$('#searchProcessConfig').on('click',function(){
		var flag=$.ZUI.validateForm($('#queryProcessConfigForm'));
    	if(flag){
        	var formArray=$("#queryProcessConfigForm").serialize();
        	formArray=decodeURIComponent(formArray, true);
        	$('#tb-processConfig').ZTable("reload", formArray);
    	}
	});
	
	$('#resetProcessConfig').on('click',function(){
    	$('#processName').val('');
    	$('#processFormCd').ZCombobox('setValue','');
    	$('#processConfigIsEnable').ZCombobox('setValue',true);
		$('#tb-processConfig').ZTable("reload", {isEnable:true});
    });
	
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
		
		return '<a href="javaScript:void(0)" onclick="editProcessConfig"><button class="btn-blue">编辑</button></a>'
    	+
    	'&nbsp;&nbsp;'+'<a href="javaScript:void(0)" onclick="deleteProcessConfig"><button class="btn-blue">删除</button></a>';
	}
	
	CALLBACK.editProcessConfig=function(index,rowData){
		var	url = '<z:ukey key="com.zdsoft.finance.processConfig.dialog" context="admin"/>&productId=${product.id}&processConfigId='+rowData.id;
		$('#editProcessConfigDialog').load(url,function(){
			
		});
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