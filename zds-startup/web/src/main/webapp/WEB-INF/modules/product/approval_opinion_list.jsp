<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>审批意见</title>
</head>
<body>
<div id="approvalOpinionForm">
	<!-- 查询区域 -->
	<div class="page-box">
		<div class="page-title">查询</div>
		<div class="p10">
			<form id="queryApprovalOpinion" class="zui-form form-search" method="post" zdata-options="{}">
				<dl class="form-item">
					<dt class="title">审批类型:</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input zui-validatebox" validate-type="Length[0-60]" id="approvalTypeNm" name="approvalTypeNm">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">是否启用:</dt>
					<dd class="detail">
						<input class="zui-combobox zui-validatebox" type="hidden" validate-type="" id="approvalOpinionIsEnable"
	                        data-data="[{'id':'true','text':'是'},{'id':'false','text':'否'}]" value="true"
	                        data-valuefield="id" data-textfield="text" name="isEnable">
					</dd>
				</dl>
				<dl class="form-btn">
					<input type="button" class="btn-blue" id="searchApprovalOpinion" value="查询" />
					<input type="button" class="btn-gray" id="resetApprovalOpinion" value="重置" />
				</dl>
			</form>
		</div>
	</div>
	<!-- 列表区域 -->
	<div class="page-box">
		<div class="page-title">审批意见</div>
		<div class="p10">
			<div id="tb-approvalOpinion" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.approvalOpinion.getList' context='admin'/>&productVo.id=${product.id }","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#approvalOpinion_toolbar"}'>
				<table>
        			<tr>
            			<th data-options="field:approvalTypeNm,width:15%">审批类型</th>
            			<th data-options="field:mortgageOrderNm,width:15%">抵押顺位</th>
            			<th data-options="field:remark,width:40%">审批用语</th>
            			<th data-options="field:isEnable,width:10%" formatter="formatIsEnable">是否启用</th>
            			<th data-options="field:id,width:20%" formatter="formatId">操作</th>
			        </tr>
				</table>
			</div>
			<div id="approvalOpinion_toolbar">
				<a class="zui-toolbar" iconCls="icon-btn08" text="新增" buttonCls="btn-blue" handler="addApprovalOpinion"></a>
        	</div>
		</div>
	</div>
</div>

<div id="editApprovalOpinionDialog" style="display: none">
</div>

</body>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
	
	CALLBACK.addApprovalOpinion=function(){
		var	url = '<z:ukey key="com.zdsoft.finance.approvalOpinion.dialog" context="admin"/>&productId=${product.id}';
		$('#editApprovalOpinionDialog').load(url,function(){
			
		});
	}

	$('#searchApprovalOpinion').on('click',function(){
		var flag=$.ZUI.validateForm($('#queryApprovalOpinion'));
    	if(flag){
        	var formArray=$("#queryApprovalOpinion").serialize();
        	formArray=decodeURIComponent(formArray, true);
        	$('#tb-approvalOpinion').ZTable("reload", formArray);
    	}
	});
	
	$('#resetApprovalOpinion').on('click',function(){
    	$('#approvalTypeNm').val('');
    	$('#approvalOpinionIsEnable').ZCombobox('setValue',true);
		$('#tb-approvalOpinion').ZTable("reload", {isEnable:true});
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
		
		return '<a href="javaScript:void(0)" onclick="editApprovalOpinion"><button class="btn-blue">编辑</button></a>'
    	+
    	'&nbsp;&nbsp;'+'<a href="javaScript:void(0)" onclick="deleteApprovalOpinion"><button class="btn-blue">删除</button></a>';
	}
	
	CALLBACK.editApprovalOpinion=function(index,rowData){
		var	url = '<z:ukey key="com.zdsoft.finance.approvalOpinion.dialog" context="admin"/>&productId=${product.id}&approvalOpinionId='+rowData.id;
		$('#editApprovalOpinionDialog').load(url,function(){
			
		});
	}
	
	CALLBACK.deleteApprovalOpinion=function(index,rowData){
		$.ZMessage.question("警告", "确认删除？", function () {
			$(".zd-message").ZWindow("close");
			$.ajax({
                type: 'post',
                url: '<z:ukey key="com.zdsoft.finance.approvalOpinion.delete" context="admin"/>',
                data: {approvalOpinionId:rowData.id},
                dataType: 'json',
                success: function (data) {
                    if (data.resultStatus == 0) {
                    	$('#tb-approvalOpinion').ZTable("reload", {});
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
	$.ZUI.initForms("#approvalOpinionForm");
	$.ZUI.initGrid("#approvalOpinionForm");
});
</script>
</html>