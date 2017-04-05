<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<%@ include file='../common/common_js.jsp'%>
<title>评估公司</title>
</head>
<body>
	<div class="frm-content">
		<!-- 查询区域 -->
		<div class="page-box">
			<div class="page-title">评估公司</div>
			<div class="p10">
				<form id="search_from" class="zui-form form-search" method="post"
					enctype="multipart/form-data">
					<dl class="form-item">
						<dt class="title">评估公司：</dt>
						<dd class="detail">
							<label> <input class="zui-input" id="companyName" name="companyName|LK|S">
							</label>
						</dd>
					</dl>
					<dl class="form-item">
						<dt class="title">状态：</dt>
						<dd class="detail">
							<input class="zui-combobox zui-validatebox" id="isStop" name="isStop|E|S" type="hidden"
                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=yorn"
                              data-valuefield="fullcode" data-callback="reloadMeetingProject" data-textfield="name" validate-type="Require">
						</dd>
					</dl>
					
					<dl class="form-item">
						<dt class="title">类别：</dt>
	                    <dd class="detail">
	                    	<input class="zui-combobox zui-validatebox" id="companyType" name="companyType|E|S" type="hidden"
                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=pgqylb"
                              data-valuefield="fullcode" data-callback="reloadMeetingProject" data-textfield="name" validate-type="Require">
						</dd>
					</dl>
					<dl class="form-btn">
						<button type="button" class="btn-search-blue" id="btn-search">查询</button>
						<button type="button" class="btn-search-gray" id="btn-reset">重置</button>
					</dl>
				</form>
			</div>
		</div>
		<div class="page-box">
			<div class="p10">
				<div id="evaluation_datagrid" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.cooperator.evaluation.getEvaluationCompany" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","toolbar":"#btn-applylist","tableCls":"table-index"}'>
					<table>
						<thead>
		        			<tr>
		            			<th data-options="field:companyName">评估公司名称</th>
		            			<th data-options="field:evaluateTypeNm">类别</th>
		            			<th data-options="field:fatherName">上级</th>
		            			<th data-options="field:isStopNm">状态</th>
		            			<th data-options="field:id" formatter="operate">操作</th>
					        </tr>
						</thead>
					</table>
				</div>
				<div id="btn-applylist">
				    <a class="zui-toolbar"  id="btn-add" text="新增" iconCls="icon-add" buttonCls="btn-blue" handler="doAdd"></a>
				    <a class="zui-toolbar"  id="btn-export" text="导出" iconCls="icon-delete" buttonCls="btn-gray" handler="doExport"></a>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
			//操作
			CALLBACK.operate = function(row,value){
				var html = "<a title='编辑' class='icon-btn22 handler-icon c-green' onclick='evaluationEdit'></a>";
				html += "<a title='查看' class='icon-btn31 handler-icon c-orange' onclick='evaluationView'></a>";
				html += "<a title='删除' class='icon-btn12 handler-icon c-gray' onclick='evaluationDel'></a>";
				return html;
			};
		
			$.ZUI.init();
			CALLBACK.evaluationEdit = function(index,data){
				ZDS_MESSAGE_CLIENT.openMenuLink('avaluation_edit', '评估公司编辑', '<z:ukey key="com.zdsoft.finance.cooperator.evaluation.tab" context="admin"/>&operationType=mod&evaluationId='+data.id);
			};
			CALLBACK.doAdd = function(index,data){
				ZDS_MESSAGE_CLIENT.openMenuLink('avaluation_add', '新增评估公司', '<z:ukey key="com.zdsoft.finance.cooperator.evaluation.tab" context="admin"/>&operationType=add');
			};
			CALLBACK.evaluationView = function(index,data){
				ZDS_MESSAGE_CLIENT.openMenuLink('avaluation_add', '新增评估查看', '<z:ukey key="com.zdsoft.finance.cooperator.evaluation.tab" context="admin"/>&operationType=view&evaluationId='+data.id);
			};
			CALLBACK.evaluationDel = function(index,data){
						$.ajax({
		                    type: 'post',
		                    url: '<z:ukey key="com.zdsoft.finance.cooperator.evaluation.company.del" context="admin"/>',
		                    data: data,
		                    dataType: 'json',
		                    success: function (data) {
		                        if (data.resultStatus == 0) {
		                        	$.ZMessage.success("提示", "删除成功", function () {
		                    				doSearch();
		        	                });
		                        }else{
		                        	$.ZMessage.error("错误", data.msg, function () {
			                            $(".zd-message").ZWindow("close");
			                        });
		                        }
		                    },
		                    error: function () {
		                    	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
		                            $(".zd-message").ZWindow("close");
		                        });
		                    }
		                });
					};
			CALLBACK.doExport = function(index,data){
				alert("ok");
			};
			
			
			$('#btn-search').click(function(){
				doSearch();
			});
			$('#btn-reset').click(function(){
				$('#companyName').val('');
				$('#isStop').val('');
				$('#companyType').val('');
			});
			
			function doSearch(){
				var formArray=$("#search_from").serialize();
				formArray = decodeURIComponent(formArray, true);
				$('#evaluation_datagrid').ZTable("reload",formArray);
			}
			
			ZDS_MESSAGE_CLIENT.refreshThis=function(){
				doSearch();
	        };
	        
		});
	</script>
</body>
</html>