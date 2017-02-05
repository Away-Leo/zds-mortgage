<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>场景设置</title>
</head>
<body>
<div id="questionSceneForm">
	<!-- 查询区域 -->
	<div class="page-box">
		<div class="page-title">查询</div>
		<div class="p10">
			<form id="searchForm" class="zui-form form-search" method="post" zdata-options="{}">
				<dl class="form-item">
					<dt class="title">问题:</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input zui-validatebox" id="title" type="text" validate-type="Length[0-50]" id="title" name="title">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">字段类型:</dt>
					<dd class="detail">
						<input class="zui-combobox zui-validatebox" type="hidden" validate-type="" id="typeCd"
	             	          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=questionTypeCd"
	                        data-valuefield="fullcode" data-textfield="name" name="typeCd">
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">类型:</dt>
					<dd class="detail">
						<input class="zui-combobox zui-validatebox" type="hidden" validate-type="" id="sceneCd"
	             	          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=questionSceneCd"
	                        data-valuefield="fullcode" data-textfield="name" name="sceneCd">
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
		<div class="page-title">场景列表</div>
		<div class="p10">
			<div id="tb-questionScene" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.questionScene.getList' context='admin'/>","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#questionScene_toolbar"}'>
				<table>
        			<tr>
            			<th data-options="field:sceneNm,width:15%">类型</th>
            			<th data-options="field:title,width:40%">问题</th>
            			<th data-options="field:typeNm,width:15%">字段类型</th>
            			<th data-options="field:paramNm,width:20%" formatter="formatParamNm">参数值</th>
            			<th data-options="field:id,width:10%" formatter="formatId">操作</th>
			        </tr>
				</table>
			</div>
			<div id="questionScene_toolbar">
				<a class="zui-toolbar" iconCls="icon-btn08" text="新增" buttonCls="btn-blue" handler="addQuestionScene"></a>
        	</div>
		</div>
	</div>
</div>

<div id="editQuestionSceneDialog" style="display: none">
</div>

</body>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
	
	CALLBACK.addQuestionScene=function(){
		ZDS_MESSAGE_CLIENT.openMenuLink('addQuestionScene', '添加场景设置', '<z:ukey key="com.zdsoft.finance.questionScene.add" context="admin"/>'+ "&openMethod=tabs");
	};
	
	ZDS_MESSAGE_CLIENT.refreshThis=function(){
		$('#tb-questionScene').ZTable('reload');
    };

	$('#search').on('click',function(){
		var flag=$.ZUI.validateForm($('#searchForm'));
    	if(flag){
        	var formArray=$("#searchForm").serialize();
        	formArray=decodeURIComponent(formArray, true);
        	$('#tb-questionScene').ZTable("reload", formArray);
    	}
	});
	
	$('#reset').on('click',function(){
    	$('#title').val('');
    	$('#typeCd').ZCombobox('setValue','');
    	$('#sceneCd').ZCombobox('setValue','');
		$('#tb-questionScene').ZTable("reload", {});
    });
	
	CALLBACK.formatParamNm=function(rowData,index){
		if(!rowData.paramNm){
			return '';
		}else{
			return rowData.paramNm;
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
		
		return '<a href="javaScript:void(0)" onclick="delete"><button class="btn-blue">删除</button></a>';
	}
	
	CALLBACK.delete=function(index,rowData){
		$.ZMessage.question("警告", "确认删除？", function () {
			$(".zd-message").ZWindow("close");
			$.ajax({
                type: 'post',
                url: '<z:ukey key="com.zdsoft.finance.questionScene.delete" context="admin"/>',
                data: {questionSceneId:rowData.id},
                dataType: 'json',
                success: function (data) {
                    if (data.resultStatus == 0) {
                    	$('#tb-questionScene').ZTable("reload", {});
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
	$.ZUI.init();
});
</script>
</html>