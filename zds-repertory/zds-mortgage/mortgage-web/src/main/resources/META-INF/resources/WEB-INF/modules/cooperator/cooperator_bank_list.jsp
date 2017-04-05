<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%@ include file='../common/common_js.jsp'%>
	<div class="frm-content" id="bankDiv">
		<div class="page-box">
			<div class="p10">
				<div id="bank_datagrid_view" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.cooperator.getCooperatorBank" context="admin"/>&cooperatorTerminal.id|E|S=${terminalId}&jsoncallback=?","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#btn-applylist_operat"}'>
					<table>
						<thead>
		        			<tr>
		            			<th data-options="field:bankName">银行名称</th>
		            			<th data-options="field:id" formatter="validateState">操作</th>
					        </tr>
						</thead>
					</table>
				</div>
				<div id="btn-applylist_operat">
				    <a class="zui-toolbar"  id="btn-add" text="新增" iconCls="icon-add" buttonCls="btn-blue" handler="doBank"></a>
				</div>
			</div>
		</div>
	</div>
	<div id="bankDialog" style="display: none">
	</div>
	<script type="text/javascript">
	seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.loading','zd/switch','zd/jquery.zds.dialog', 'zd/jquery.zds.message','zd/jquery.zds.combobox', 'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter']
    , function ($, CALLBACK, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
		//操作
		CALLBACK.validateState = function(row,value){
			var html = "<a title='编辑' class='btn-blue mr5' onclick='bankEdit'>编辑</a>";
			html += "<a title='删除' class='btn-blue  mr5' onclick='bankDel'>删除</a>";
			html += "<a title='查看' class='btn-blue' onclick='bankView'>查看</a>";
			return html;
		};

		//编辑对话框
		CALLBACK.doBank=function(index,data){
			var	url = '<z:ukey key="com.zdsoft.finance.cooperator.cooperatorBank.dialog" context="admin"/>&operationType=add&terminalId=${terminalId}';
			$('#bankDialog').load(url,function(){
				
			});	
		}
		CALLBACK.bankEdit = function(index,data){
			var	url = '<z:ukey key="com.zdsoft.finance.cooperator.cooperatorBank.dialog" context="admin"/>&operationType=mod&terminalId=${terminalId}&id='+data.id;
			$('#bankDialog').load(url,function(){
				
			});	
		};
		CALLBACK.bankView = function(index,data){
			var	url = '<z:ukey key="com.zdsoft.finance.cooperator.cooperatorBank.dialog" context="admin"/>&operationType=view&terminalId=${terminalId}&id='+data.id;
			$('#bankDialog').load(url,function(){
				
			});	
		};
		CALLBACK.bankDel = function(index,data){
			$.ZMessage.question("提示", "是否删除", function (index) {
					 $.ajax({
			                type: 'post',
			                url: '<z:ukey key="com.zdsoft.finance.cooperator.cooperatorBank.del" context="admin"/>',
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
			                    		$('#bank_datagrid_view').ZTable("reload");
			                },
			                error: function () {
			                	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
			                        $(".zd-message").ZWindow("close");
			                    });
			                }
			            });
			});
		};
		
		
		$.ZUI.initGrid("#bankDiv");
		$.ZUI.initForms("#bankDiv");
		$.ZUI.initForms("#bankDialog");
		});
	</script>
