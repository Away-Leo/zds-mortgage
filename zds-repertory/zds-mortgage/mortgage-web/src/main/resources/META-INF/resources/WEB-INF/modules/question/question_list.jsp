<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>问题定义</title>
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
							<input class="zui-input zui-validatebox" type="text" validate-type="Length[0-50]" id="title" name="title">
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
					<input type="button" class="btn-gray" id="reset" value="重置" />
				</dl>
			</form>
		</div>
	</div>
	<!-- 列表区域 -->
	<div class="page-box">
		<div class="page-title">问题库</div>
		<div class="p10">
			<div id="tb-question" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.question.getList' context='admin'/>","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#question_toolbar"}'>
				<table>
        			<tr>
            			<th data-options="field:title,width:40%">问题</th>
            			<th data-options="field:typeNm,width:10%">字段类型</th>
            			<th data-options="field:paramNm,width:20%" formatter="formatParamNm">参数值</th>
            			<th data-options="field:hitRule,width:20%">命中规则</th>
            			<th data-options="field:id,width:10%" formatter="formatId">操作</th>
			        </tr>
				</table>
			</div>
			<div id="question_toolbar">
				<a class="zui-toolbar" iconCls="icon-btn08" text="新增" buttonCls="btn-blue" handler="addQuestion"></a>
        	</div>
		</div>
	</div>
</div>

<div id="editQuestionDialog" style="display: none">
</div>

</body>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
	
	CALLBACK.addQuestion=function(){
		var	url = '<z:ukey key="com.zdsoft.finance.question.dialog" context="admin"/>';
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
		
		return '<a href="javaScript:void(0)" onclick="edit"><button class="btn-blue">编辑</button></a>'
    	+
    	'&nbsp;&nbsp;'+'<a href="javaScript:void(0)" onclick="delete"><button class="btn-blue">删除</button></a>';
	}
	
	CALLBACK.edit=function(index,rowData){
		var	url = '<z:ukey key="com.zdsoft.finance.question.dialog" context="admin"/>&questionId='+rowData.id;
		$('#editQuestionDialog').load(url,function(){
			
		});
	}
	
	CALLBACK.delete=function(index,rowData){
		$.ZMessage.question("警告", "确认删除？", function () {
			$(".zd-message").ZWindow("close");
			$.ajax({
                type: 'post',
                url: '<z:ukey key="com.zdsoft.finance.question.delete" context="admin"/>',
                data: {questionId:rowData.id},
                dataType: 'json',
                success: function (data) {
                    if (data.resultStatus == 0) {
                    	$('#tb-question').ZTable("reload", {});
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