<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<%@ include file='../common/common_js.jsp'%>
<title>评估公司联系方式</title>
</head>
<body>
	<div class="frm-content" id="contactDiv">
		<div class="page-box">
			<div class="p10">
				<div id="evaluation_contact_datagrid" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.cooperator.evaluation.getContact" context="admin"/>&evaluationCompany.id|E|S=${evaluationId}&jsoncallback=?","singleSelect":false,"pagination":true,"idField":"id","toolbar":"#btn-applylist","tableCls":"table-index"}'>
					<table>
						<thead>
		        			<tr>
		            			<th data-options="field:contactName">姓名</th>
		            			<th data-options="field:contactTypeNm">联系类型</th>
		            			<th data-options="field:contactTelNumber">联系方式</th>
		            			<th data-options="field:id" formatter="contactOperate">操作</th>
					        </tr>
						</thead>
					</table>
				</div>
				<div id="btn-applylist">
				    <a class="zui-toolbar"  id="btn-add" text="新增" iconCls="icon-add" buttonCls="btn-blue" handler="doAdd"></a>
				</div>
			</div>
		</div>
	</div>
	<div id="contactsDialog" style="display: none">
	    
	</div>
	<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
			//操作
			CALLBACK.contactOperate = function(row,value){
				var html='';
				if("${operationType }" != "view"){
					 html = "<a title='编辑' class='icon-btn22 handler-icon c-green' onclick='contactEdit'></a>";
					html += "<a title='删除' class='icon-btn12 handler-icon c-gray' onclick='contactDel'></a>";
				}
				return html;
			};
			if("${operationType }" == "view"){
				$("#btn-add").remove();
			}
			CALLBACK.doAdd=function(){
				var	url = '<z:ukey key="com.zdsoft.finance.cooperator.evaluation.contact.dialog" context="admin"/>&evaluationId=${evaluationId}';
				$('#contactsDialog').load(url,function(){
					
				});
			}
			CALLBACK.contactEdit=function(index,data){
				var	url = '<z:ukey key="com.zdsoft.finance.cooperator.evaluation.contact.dialog" context="admin"/>&operationType=mod&evaluationId=${evaluationId}&id='+data.id;
				$('#contactsDialog').load(url,function(){
					
				});
			}
			CALLBACK.contactDel = function(index,data){
						$.ajax({
		                    type: 'post',
		                    url: '<z:ukey key="com.zdsoft.finance.cooperator.evaluation.contact.del" context="admin"/>',
		                    data: data,
		                    dataType: 'json',
		                    success: function (data) {
		                        if (data.resultStatus == 0) {
		                        	$.ZMessage.success("提示", "删除成功", function () {
		                        		$('#evaluation_contact_datagrid').ZTable("reload",{});
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
			
				
			ZDS_MESSAGE_CLIENT.refreshThis=function(){
				$('#evaluation_contact_datagrid').ZTable("reload",{});
	        };
	        $.ZUI.initGrid("#contactDiv");
			$.ZUI.initForms("#contactDiv");
		});
	</script>
</body>
</html>