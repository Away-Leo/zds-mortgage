<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file='../common/common_js.jsp'%>
<%@ include file="../common/common_upload.jsp"%> 
	<div id="ideaDiv" style="height:500px;">
				<div id="idea_datagrid_table" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.cooperator.idea.getCooperateIdea" context="admin"/>&capitalist.id|E|S=${capitalistId}&jsoncallback=?","singleSelect":false,"pagination":true,"idField":"id","toolbar":"#idea_datagrid_applylist","tableCls":"table-index"}'>
					<table>
						<thead>
		        			<tr>
		            			<th data-options="field:agreementName">协议名称</th>
		            			<th data-options="field:attachName">附件</th>
		            			<th data-options="field:id" formatter="contactFormat">操作</th>
					        </tr>
						</thead>
					</table>
				</div>
				<div id="idea_datagrid_applylist">
				    <a class="zui-toolbar"  id="btn-add" text="增加" iconCls="icon-add" buttonCls="btn-blue" handler="ideaAdd"></a>
				</div>
	</div>
	<div id="ideaDialog">
	</div>
	<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
		
		//操作
		CALLBACK.contactFormat = function(row,value){
			var html = '';
			if("${operationType }" != "view"){
				html = "<a title='编辑' class='icon-btn22 handler-icon c-green'  onclick='ideaEdit'></a>";
				html += "<a title='下载' class='icon-btn37 handler-icon c-blue' onclick='attrDown'></a>";
				html += "<a title='删除' class='icon-btn12 handler-icon c-gray' onclick='ideaDel'></a>";
			}
			return html;
		};
		
		if("${operationType }" == "view"){
			$("#btn-add").remove();
		}
		//编辑对话框
		CALLBACK.ideaAdd=function(){
			var	url = '<z:ukey key="com.zdsoft.finance.cooperator.idea.dialog" context="admin"/>&operationType=add&capitalistId=${capitalistId}';
			$('#ideaDialog').load(url,function(){
				
			});
		}
		CALLBACK.ideaEdit=function(index,data){
			var	url = '<z:ukey key="com.zdsoft.finance.cooperator.idea.dialog" context="admin"/>&operationType=mod&capitalistId=${capitalistId}&id='+data.id;
			$('#ideaDialog').load(url,function(){
				
			});
		}
		CALLBACK.attrDown=function(index,data){
			if(data.attachmentId == ''){
				$.ZMessage.error("提示", "没有上传附件", function () {
                });
				return
			}
			var essdownUrl = '<z:ukey key="public.upload.download"  context="admin"/>';
			window.location.href = essdownUrl+"&attachmentId="+data.attachmentId;
		}
		CALLBACK.ideaDel = function(index,data){
			$.ZMessage.question("提示", "是否删除", function (index) {
						$.ajax({
			                type: 'post',
			                url: '<z:ukey key="com.zdsoft.finance.cooperator.idea.del" context="admin"/>',
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
			                    		$('#idea_datagrid_table').ZTable("reload");
			                },
			                error: function () {
			                	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
			                        $(".zd-message").ZWindow("close");
			                    });
			                }
			            });
            });
			
		};
		$.ZUI.initGrid("#ideaDiv");
		$.ZUI.initForms("#ideaDiv");
		$.ZUI.initForms("#ideaDialog");
	});
	
	</script>
