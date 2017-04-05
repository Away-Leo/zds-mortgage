<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>案件列表</title>
</head>
<body>
<div id="myRemindCaseForm">
	<!-- 查询区域 -->
	<div class="page-box">
		<div class="page-title">案件提醒</div>
		<div class="p10">
			<form id="searchForm" class="zui-form form-search" method="post" zdata-options="{}">
				<dl class="form-item">
					<dt class="title">案件号:</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input zui-validatebox" type="text" validate-type="Length[0-32]" id="projectCode" name="projectCode">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">主借人:</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input zui-validatebox" type="text" validate-type="Length[0-100]" id="customerName" name="customerName">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">提醒类型:</dt>
					<dd class="detail">
						<input class="zui-combobox zui-validatebox" type="hidden" validate-type="" id="category"
	             	          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0078"
	                        data-valuefield="fullcode" data-textfield="name" name="category">
					</dd>
				</dl>
				<dl class="form-item">
                    <dt class="title">截止日:</dt>
                    <dd class="detail">
                        <label>
                        	<input class="zui-input width2-1 zui-validatebox" type="text" id="startTime" name="startDate" validate-type="" onclick="WdatePicker({dateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'endTime\')}'})" readonly/>
                        </label>
                    </dd>
					<span class="word">至</span>
                    <dd class="detail">
                        <label>
                        	<input class="zui-input width2-1 zui-validatebox" type="text" id="endTime" name="endDate" validate-type="" onclick="WdatePicker({dateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'startTime\')}'})" readonly/>
                        </label>
                    </dd>
                </dl>
				<dl class="form-btn">
					<input type="button" class="btn-blue" id="search" value="查询" />
					<input type="button" class="btn-gray" id="reset" value="重置" />
				</dl>
			</form>
		</div>
	</div>
	<!-- 列表区域 -->
	<div class="page-box">
		<div class="p10">
			<div id="tb-remindCaseList" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.workbench.remindCase.getRemindCaseList' context='admin'/>","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index"}'>
				<table>
        			<tr>
            			<th data-options="field:projectCode,width:10%">案件号</th>
            			<th data-options="field:customerName,width:10%">主借人</th>
            			<th data-options="field:category,width:20%">提醒类型</th>
            			<th data-options="field:content,width:30%">提醒内容</th>
            			<th data-options="field:startDate,width:20%">提醒日</th>
            			<th data-options="field:id,width:10%" formatter="formatId">操作</th>
			        </tr>
				</table>
			</div>
		</div>
	</div>
</div>

<div id="findRemindPoolDialog" style="display: none">
</div>
</body>
<script type="text/javascript">
seajs.use([
           'jquery','zd/tools','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.table'
           ], 
	 	function ($,ZTOOlS,CALLBACK) {
	
	$('#search').on('click',function(){
		var flag=$.ZUI.validateForm($('#searchForm'));
    	if(flag){
        	var formArray=$("#searchForm").serialize();
        	formArray=decodeURIComponent(formArray, true);
        	$('#tb-remindCaseList').ZTable("reload", formArray);
    	}
	});
	
	$('#reset').on('click',function(){
    	$('#projectCode').val('');
    	$('#customerName').val('');
    	$('#category').ZCombobox('setValue','');
    	$('#startTime').val('');
    	$('#endTime').val('');
		$('#tb-remindCaseList').ZTable("reload", {});
    });
	
	CALLBACK.formatId=function(rowData,index){
		var id=rowData.id;
		if(!id){
			$.ZMessage.error("错误", "未获取到主键", function () {
                $(".zd-message").ZWindow("close");
            });
			return ;
		}
		
		return '<a href="javaScript:void(0)" onclick="confirmeRemind"><button class="btn-blue">确认</button></a>'
    	+
    	'&nbsp;&nbsp;'+'<a href="javaScript:void(0)" onclick="findRemind"><button class="btn-blue">查看</button></a>';
	}
	
	CALLBACK.confirmeRemind=function(index,rowData){
		var id=rowData.id;
		if(!id){
			$.ZMessage.error("错误", "未获取到主键", function () {
                $(".zd-message").ZWindow("close");
            });
			return ;
		}
		
		$.ajax({
            type: 'post',
            url: '<z:ukey key="com.zdsoft.finance.workbench.remindCase.confirmeRemind" context="admin"/>',
            data: {remindPoolId:id},
            dataType: 'json',
            success: function (data) {
                if (data.resultStatus == 0) {
                	var formArray=$("#searchForm").serialize();
                	formArray=decodeURIComponent(formArray, true);
                	$('#tb-remindCaseList').ZTable("reload", formArray);
                	$.ZMessage.success("提示", "成功", function () {
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
	}
	
	CALLBACK.findRemind=function(index,rowData){
		var id=rowData.id;
		if(!id){
			$.ZMessage.error("错误", "未获取到主键", function () {
                $(".zd-message").ZWindow("close");
            });
			return ;
		}
		
		var	url = '<z:ukey key="com.zdsoft.finance.workbench.remindCase.findRemind" context="admin"/>&remindPoolId='+id;
		$('#findRemindPoolDialog').load(url,function(){
			
		});
	}
	
	//初始化页面
	$.ZUI.init();
});
</script>
</html>