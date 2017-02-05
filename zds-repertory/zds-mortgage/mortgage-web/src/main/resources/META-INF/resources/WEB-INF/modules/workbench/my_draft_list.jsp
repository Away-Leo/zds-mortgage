<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>我的草稿</title>
</head>
<body>
<div id="myDraftForm">
	<!-- 查询区域 -->
	<div class="page-box">
		<div class="page-title">查询</div>
		<div class="p10">
			<form id="searchForm" class="zui-form form-search" method="post" zdata-options="{}">
				<dl class="form-item">
					<dt class="title">流程名称:</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input zui-validatebox" type="text" validate-type="Length[0-60]" id="processName" name="processName">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">主借人:</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input zui-validatebox" type="text" validate-type="Length[0-60]" id="borrowerPerson" name="borrowerPerson">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">申请单:</dt>
					<dd class="detail">
						<input class="zui-combobox zui-validatebox" type="hidden" validate-type="" id="applayFormCode"
	             	          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=processFormCd"
	                        data-valuefield="fullcode" data-textfield="name" name="applayFormCode">
					</dd>
				</dl>
				<dl class="form-item">
                    <dt class="title">申请时间:</dt>
                    <dd class="detail">
                        <label>
                        	<input class="zui-input width2-1 zui-validatebox" type="text" id="startTime" name="startTime" validate-type="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\')}'})" readonly/>
                        </label>
                    </dd>
					<span class="word">至</span>
                    <dd class="detail">
                        <label>
                        	<input class="zui-input width2-1 zui-validatebox" type="text" id="endTime" name="endTime" validate-type="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}'})" readonly/>
                        </label>
                    </dd>
                </dl>
				<dl class="form-btn">
					<input type="button" class="btn-blue" id="search" value="查询" />
					<input type="button" class="btn-blue" id="reset" value="重置" />
				</dl>
			</form>
		</div>
	</div>
	<!-- 列表区域 -->
	<div class="page-box">
		<div class="page-title">我的草稿</div>
		<div class="p10">
			<div id="tb-myDraft" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.myDraft.getList' context='admin'/>","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index"}'>
				<table>
        			<tr>
            			<th data-options="field:businessCode,width:20%">业务编号</th>
            			<th data-options="field:borrowerPerson,width:20%">主借人</th>
            			<th data-options="field:applayFormName,width:20%">申请单</th>
            			<th data-options="field:createTime,width:20%">保存时间</th>
            			<th data-options="field:id,width:10%" formatter="formatId">操作</th>
			        </tr>
				</table>
			</div>
		</div>
	</div>
</div>

</body>
<script type="text/javascript">
seajs.use([
           'jquery','zd/tools','zd/jquery.zds.page.callback','zd/jquery.zds.dialog', 'zd/jquery.zds.combotree',
           'zd/jquery.zds.combobox','zd/jquery.zds.message','ztree', 'zd/jquery.zds.form','zd/jquery.zds.table','zd/jquery.zds.validate', 'zd/jquery.zds.seleter'
           ], 
	 	function ($,ZTOOlS,CALLBACK,Zdialog) {
	
	$('#search').on('click',function(){
		var flag=$.ZUI.validateForm($('#searchForm'));
    	if(flag){
        	var formArray=$("#searchForm").serialize();
        	formArray=decodeURIComponent(formArray, true);
        	$('#tb-myDraft').ZTable("reload", formArray);
    	}
	});
	
	$('#reset').on('click',function(){
    	$('#processName').val('');
    	$('#borrowerPerson').val('');
    	$('#startTime').val('');
    	$('#endTime').val('');
    	$('#applayFormCode').ZCombobox('setValue','');
		$('#tb-myDraft').ZTable("reload", {});
    });
	
	CALLBACK.formatId=function(rowData,index){
		var id=rowData.id;
		if(!id){
			$.ZMessage.error("错误", "未获取到主键", function () {
                $(".zd-message").ZWindow("close");
            });
			return ;
		}
		
		return '<a href="javaScript:void(0)" onclick="edit"><button class="btn-blue">修改</button></a>'
    	+
    	'&nbsp;&nbsp;'+'<a href="javaScript:void(0)" onclick="delete"><button class="btn-blue">废除</button></a>';
	}
	
	CALLBACK.edit=function(index,rowData){
		var id=rowData.id;
		if(!id){
			$.ZMessage.error("错误", "未获取到主键", function () {
                $(".zd-message").ZWindow("close");
            });
			return ;
		}
		
		ZDS_MESSAGE_CLIENT.openMenuLink('myDraft_applayForm', '申请单修改', '<z:ukey key="com.zdsoft.finance.myDraft.edit" context="admin"/>&busiFormId='+id);
	}
	
	CALLBACK.delete=function(index,rowData){
		$.ZMessage.warning("警告", "确认废除？", function () {
			$(".zd-message").ZWindow("close");
			$.ajax({
                type: 'post',
                url: '<z:ukey key="com.zdsoft.finance.myDraft.delete" context="admin"/>',
                data: {busiFormId:rowData.id},
                dataType: 'json',
                success: function (data) {
                    if (data.resultStatus == 0) {
                    	$('#tb-myDraft').ZTable("reload", {});
                    	$.ZMessage.success("提示", "废除成功", function () {
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
	$.ZUI.init();
});
</script>
</html>