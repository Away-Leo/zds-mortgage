<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>场景问题设置</title>
</head>
<body>
<div id="questionForm">
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
				<dl class="form-btn">
					<input type="button" class="btn-blue" id="search" value="查询" />
					<input type="button" class="btn-blue" id="reset" value="重置" />
				</dl>
			</form>
		</div>
	</div>
	<!-- 列表区域 -->
	<div class="page-box">
		<div class="page-title">问题库</div>
		<div class="p10">
			<div id="tb-question" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.question.getList' context='admin'/>","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#question_toolbar"}'>
				<table>
        			<tr>
            			<th data-options="field:title,width:70%">问题</th>
            			<th data-options="field:typeNm,width:30%">字段类型</th>
			        </tr>
				</table>
			</div>
			<div id="question_toolbar">
				<a class="zui-toolbar" iconCls="icon-btn08" text="添加" buttonCls="btn-blue" handler="saveQuestionScene"></a>
        	</div>
		</div>
	</div>
</div>

<div id="editQuestionDialog" style="display: none">
</div>

</body>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
	
	CALLBACK.saveQuestionScene=function(){
		var rowsData = $('#tb-question').ZTable("getSelections");
		if(!rowsData || rowsData.length<1){
			$.ZMessage.warning("警告", "未选中添加数据", function () {
                $(".zd-message").ZWindow("close");
            });
			return;
		}
		var jsonArray=[];
		for(var i=0;i<rowsData.length;i++){
			var jsonTemp={'id':rowsData[i].id};
			jsonArray.push(jsonTemp);
		}
		var	url = '<z:ukey key="com.zdsoft.finance.questionScene.dialog" context="admin"/>&jsonStr='+JSON.stringify(jsonArray);
		$('#editQuestionDialog').load(url,function(){
		});
	}

	$('#search').on('click',function(){
		var flag=$.ZUI.validateForm($('#searchForm'));
    	if(flag){
        	var formArray=$("#searchForm").serialize();
        	formArray=decodeURIComponent(formArray, true);
        	$('#tb-question').ZTable("reload", formArray);
    	}
	});
	
	$('#reset').on('click',function(){
    	$('#title').val('');
    	$('#typeCd').ZCombobox('setValue','');
		$('#tb-question').ZTable("reload", {});
    });
	
	//初始化页面
	$.ZUI.init();
});
</script>
</html>