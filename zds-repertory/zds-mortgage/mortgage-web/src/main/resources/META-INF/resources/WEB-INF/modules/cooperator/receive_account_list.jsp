<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file='../common/common_js.jsp'%>
	<div class="frm-content" id="accountDiv">
		<div class="page-box">
			<div class="p10">
				<div id="account_datagrid_list" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.cooperator.getReceiveAccount" context="admin"/>&cooperatorTerminal.id|E|S=${terminalId}&jsoncallback=?","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#account-applylist"}'>
					<table>
						<thead>
		        			<tr>
		            			<th data-options="field:accountDescribe">账户描述</th>
		            			<th data-options="field:bankName">开户银行</th>
		            			<th data-options="field:accountName">开户名</th>
		            			<th data-options="field:accountNumber">帐号/卡号</th>
		            			<th data-options="field:receiverName">返佣收款人姓名</th>
		            			<th data-options="field:approveStateName">审批状态</th>
		            			<th data-options="field:aliveAccount">活动帐号</th>
		            			<th data-options="field:id" formatter="accountOperat">操作</th>
					        </tr>
						</thead>
					</table>
				</div>
				<div id="account-applylist">
				    <a class="zui-toolbar"  id="btn-add" text="增加" iconCls="icon-add" buttonCls="btn-blue" handler="doAccount"></a>
				</div>
			</div>
		</div>
	</div>
	<div id="accountDialog">
	    
	</div>
	<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
			//操作
			CALLBACK.accountOperat = function(row,value){
				var  html = "<a title='编辑' class='icon-btn22 handler-icon c-green' onclick='accountEdit'></a>";
				html += "<a title='查看' class='icon-btn31 handler-icon c-orange' onclick='accountView'></a>";
				html += "<a title='删除' class='icon-btn12 handler-icon c-gray' onclick='accountDel'></a>";
				return html;
			};
			
			//编辑对话框
			CALLBACK.doAccount=function(){
				var	url = '<z:ukey key="com.zdsoft.finance.cooperator.receiveAccount.dialog" context="admin"/>&operationType=add&terminalId=${terminalId}';
				$('#accountDialog').load(url,function(){
					
				});
			}
			CALLBACK.accountEdit = function(index,data){
				var	url = '<z:ukey key="com.zdsoft.finance.cooperator.receiveAccount.dialog" context="admin"/>&operationType=mod&terminalId=${terminalId}&id='+data.id;
				$('#accountDialog').load(url,function(){
					
				});
			};
			CALLBACK.accountView = function(index,data){
				var	url = '<z:ukey key="com.zdsoft.finance.cooperator.receiveAccount.dialog" context="admin"/>&operationType=view&terminalId=${terminalId}&id='+data.id;
				$('#accountDialog').load(url,function(){
					
				});
			};
			CALLBACK.accountDel = function(index,data){
				$.ZMessage.question("提示", "是否删除", function (index) {
						 $.ajax({
				                type: 'post',
				                url: '<z:ukey key="com.zdsoft.finance.cooperator.receiveAccount.del" context="admin"/>',
				                data: data,
				                dataType: 'json',
				                success: function (data) {
				                    if (data.resultStatus == 0) {
				                    	$.ZMessage.info("提示", "删除成功", function () {
				    	                });
				                    }else{
				                    	$.ZMessage.error("错误", data.msg, function () {
				                            $(".zd-message").ZWindow("close");
				                        });
				                    }
				                    $('#account_datagrid_list').ZTable("reload");
				                },
				                error: function () {
				                	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
				                        $(".zd-message").ZWindow("close");
				                    });
				                }
				            });
				});
			};
			$.ZUI.initGrid('#accountDiv');
			$.ZUI.initForms('#accountDiv');
			$.ZUI.initForms('#accountDialog');
		});
	</script>
