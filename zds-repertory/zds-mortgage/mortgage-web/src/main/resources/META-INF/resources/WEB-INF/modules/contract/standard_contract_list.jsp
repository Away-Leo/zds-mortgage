<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">

<%@ include file='../common/common_js.jsp'%>
<title>标准合同设置</title>
</head>
<body>
	<div class="frm-content">
		<!-- 查询区域 -->
		<div class="page-box">
			<div class="p10">
				<form id="search_from" class="zui-form form-search" method="post"
					enctype="multipart/form-data">
					<dl class="form-item">
						<dt class="title">合同名称：</dt>
						<dd class="detail">
							<input class="zui-input" id="contractName" name="tpl|contractName|LK|S">
						</dd>
					</dl>
					<dl class="form-item">
						<dt class="title">合同类型：</dt>
						<dd class="detail">
							<input class="zui-combobox zui-validatebox" id="contractType" name="tpl|contractType|E|S" type="hidden"
	                           data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM0066"
	                           data-valuefield="fullcode" data-textfield="name" >
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
				<div id="tb-plan" class="zui-datagrid"
					zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.contract.standardContractList" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","toolbar":"#contacts_datagrid_applylist","tableCls":"table-index"}'>
					<table>
						<tr>
							<th data-options="field:capitalNm">资方</th>
				            <th data-options="field:capitalistTypeNm">资方类别</th>
				            <th data-options="field:contractName,width:300">合同名称</th>
				            <th data-options="field:attachName">附件</th>
				            <th data-options="field:contractTypeNm">合同类型</th>
				            <th data-options="field:contractTplState">合同状态</th>
				            <th data-options="field:id,width:200" formatter="contactFormat">操作</th>
						</tr>
					</table>
				</div>
				<div id="contacts_datagrid_applylist">
				    <a class="zui-toolbar"  id="btn-add" text="新增合同" iconCls="icon-add" buttonCls="btn-blue" handler="addStandardContract"></a>
				</div>
			</div>
		</div>
	</div>
		<div id="contactsDialog"  style="display: none">
		<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message',
		           'zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table',
		           'zd/jquery.zds.seleter'], function($, CALLBACK) {
			//操作
			CALLBACK.contactFormat = function(row,value){
				var html = '<a title="详情" onclick="contactView" class="btn-blue">详情</a>';
				html += '&nbsp;&nbsp;<a title="编辑" onclick="contactEdit" class="btn-blue">编辑</a>';
				html += '&nbsp;&nbsp;<a title="删除" onclick="deleteStandardContract" class="btn-blue">删除</a>';
				return html;
			};

			//查询
			$('#btn-search').click(function(){
				doSearch();
			});
			
			function doSearch(){
				var formArray=$("#search_from").serialize();
				formArray = decodeURIComponent(formArray, true);
				$('#tb-plan').ZTable("reload",formArray);
			}
			//重置
			$("#btn-reset").click(function() {
				/* $("#search_from")[0].reset();
				$("#contractType").ZCombobox("setValue",""); */
				$.ZUI.resetForms('#search_from');
				$('#tb-plan').ZTable("reload", {});
			});
			
			CALLBACK.addStandardContract=function(){
				var	url = '<z:ukey key="com.zdsoft.finance.contract.editStandardContract" context="admin"/>&operationType=add';
				$('#contactsDialog').load(url,function(){
					
				});
			}
			
			CALLBACK.contactEdit=function(index,data){
				var	url = '<z:ukey key="com.zdsoft.finance.contract.editStandardContract" context="admin"/>&operationType=mod&id='+data.id;
				$('#contactsDialog').load(url,function(){
					
				});
			}
			CALLBACK.contactView=function(index,data){
				var	url = '<z:ukey key="com.zdsoft.finance.contract.editStandardContract" context="admin"/>&operationType=view&id='+data.id;
				$('#contactsDialog').load(url,function(){
					
				});
			}
			
			//删除合同
	        CALLBACK.deleteStandardContract=function(index,row){
	        	$.ZMessage.question("提示", "是否删除", function (index) {
					$.ajax({
		            	type: 'post',
		                url: '<z:ukey key="com.zdsoft.finance.contract.standardContract.delStandardContract" context="admin"/>',
		                data: {id : row.id},
		                dataType: 'json',
		                success: function (data) {
		                	if (data.resultStatus == 0) {
		                		$.ZMessage.success("提示", data.msg, function () {});
		                		$('#tb-plan').ZTable("reload", {});
		                	}
		              		else{
		          				$.ZMessage.error("错误", data.msg, function () {});
		          			}
		            		$('#zds-table-notarize').ZTable("reload");
		                }
		            });
	            });
	    	};
			
			$.ZUI.init();
		});
		</script>
</body>
</html>