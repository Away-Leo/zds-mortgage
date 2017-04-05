<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file='../common/common_js.jsp'%>
	<div  id="contactsDiv"  style="height:500px;">
				<div id="contacts_datagrid_view" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.cooperator.capitalist.getCapitalistContact" context="admin"/>&partnerId|E|S=${capitalistId}&jsoncallback=?","singleSelect":false,"pagination":true,"idField":"id","toolbar":"#contacts_datagrid_applylist","tableCls":"table-index"}'>
					<table>
						<thead>
		        			<tr>
		            			<th data-options="field:linkman">联系人</th>
		            			<th data-options="field:contactTypeName">联系类型</th>
		            			<th data-options="field:contactNumber">电话号码</th>
		            			<th data-options="field:id" formatter="contactFormat">操作</th>
					        </tr>
						</thead>
					</table>
				</div>
				<div id="contacts_datagrid_applylist">
				    <a class="zui-toolbar"  id="btn-add" text="新增" iconCls="icon-add" buttonCls="btn-blue" handler="contactsAdd"></a>
				</div>
	</div>
	<div id="contactsDialog" style="display: none" >
	</div>
	<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
		
		//操作
		CALLBACK.contactFormat = function(row,value){
			var html = '';
			if("${operationType }" != "view"){
				html = "<a title='编辑' class='icon-btn22 handler-icon c-green' onclick='contactEdit'></a>";
				html += "<a title='查看' class='icon-btn31 handler-icon c-orange' onclick='contactView'></a>";
				html += "<a title='删除' class='icon-btn12 handler-icon c-gray' onclick='contactDel'></a>";
			}
			return html;
		};
		
		if("${operationType }" == "view"){
			$("#btn-add").remove();
		}
		CALLBACK.contactsAdd=function(){
			var	url = '<z:ukey key="com.zdsoft.finance.cooperator.capitalist.contact.dialog" context="admin"/>&operationType=add&capitalistId=${capitalistId}';
			$('#contactsDialog').load(url,function(){
				
			});
		}
		CALLBACK.contactEdit=function(index,data){
			var	url = '<z:ukey key="com.zdsoft.finance.cooperator.capitalist.contact.dialog" context="admin"/>&operationType=mod&capitalistId=${capitalistId}&id='+data.id;
			$('#contactsDialog').load(url,function(){
				
			});
		}
		CALLBACK.contactView=function(index,data){
			var	url = '<z:ukey key="com.zdsoft.finance.cooperator.capitalist.contact.dialog" context="admin"/>&operationType=view&capitalistId=${capitalistId}&id='+data.id;
			$('#contactsDialog').load(url,function(){
				
			});
		}
		CALLBACK.contactDel = function(index,data){
			$.ZMessage.question("提示", "是否删除", function (index) {
					 $.ajax({
			                type: 'post',
			                url: '<z:ukey key="com.zdsoft.finance.cooperator.capitalist.contact.del" context="admin"/>',
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
			                    		$('#contacts_datagrid_view').ZTable("reload");
			                },
			                error: function () {
			                	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
			                        $(".zd-message").ZWindow("close");
			                    });
			                }
			            });
			});
		};
		//初始化
        $.ZUI.init();
	});
	</script>
