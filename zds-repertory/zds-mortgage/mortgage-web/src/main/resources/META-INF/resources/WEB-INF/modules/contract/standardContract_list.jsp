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
							<input class="zui-input" id="contractName" name="contractName|LK|S">

						</dd>
					</dl>

					<dl class="form-item">
						<dt class="title">合同类型：</dt>
						<dd class="detail">
					<input class="zui-combobox zui-validatebox" id="contractType" name="contractType|E|S" type="hidden"
	                           data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM0066"
	                           data-valuefield="fullcode" data-textfield="name" validate-type="Require">
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
							<th data-options="field:capitalName">资方</th>
		            <th data-options="field:capitalistType">资方类别</th>
		            <th data-options="field:contractName">合同名称</th>
		            <th data-options="field:attachmentName">附件</th>
		            <th data-options="field:contractType">合同类型</th>
		            <th data-options="field:contractState">合同状态</th>
		            <th data-options="field:id" formatter="contactFormat">操作</th>
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
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
			//操作
			CALLBACK.contactFormat = function(row,value){
				var html = '';
				if("${operationType }" != "view"){
					html = "<a title='编辑' class='icon-btn22 handler-icon c-green' onclick='contactEdit'></a>";
					html += "<a title='查看' class='icon-btn31 handler-icon c-orange' onclick='contactView'></a>";
					html += "<a title='删除' class='icon-btn12 handler-icon c-gray' onclick='deleteStandardContract'></a>";
				}
				return html;
			};
			if("${operationType }" == "view"){
				$("#btn-add").remove();
			}
								

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
									/*$("#contractType").val(''); */
									$("#search_from")[0].reset();
								});
								
								CALLBACK.addStandardContract=function(){
									var	url = '<z:ukey key="com.zdsoft.finance.contract.standardContract.dialog" context="admin"/>';
									$('#contactsDialog').load(url,function(){
										
									});
								}
								
								/* CALLBACK.evaluationEdit = function(index,rowData){
									 var changeInstitutionUrl = '<z:ukey key="com.zdsoft.finance.capital.initCasePlanDistribution" context="admin"/>&jsoncallback=?&id='+rowData.id;
						             ZDS_MESSAGE_CLIENT.openMenuLink('changeInstitutionId','修改信托计划调配',changeInstitutionUrl + "&openMethod=tabs");
								}; */
								
								CALLBACK.contactEdit=function(index,data){
									var	url = '<z:ukey key="com.zdsoft.finance.contract.standardContract.dialog" context="admin"/>&operationType=mod&id='+data.id;
									$('#contactsDialog').load(url,function(){
										
									});
								}
								CALLBACK.contactView=function(index,data){
									var	url = '<z:ukey key="com.zdsoft.finance.contract.standardContract.dialog" context="admin"/>&operationType=view&id='+data.id;
									$('#contactsDialog').load(url,function(){
										
									});
								}
								
								
								//删除公证格式化
						         CALLBACK.deleteStandardContract=function(index,row){
						             $.ZMessage.confirm("确认删除", "请确认是否删除此合同记录", function (r) {
						                
						            	 if (r == 'confirm') {
						                     $.ajax({
						                         type: 'post',
						                         url: '<z:ukey key="com.zdsoft.finance.contract.standardContract.deleteStandardContract" context="admin"/>',
						                         data: {id : row.id},
						                         dataType: 'json',
						                         success: function (data) {
						                         	if (data.resultStatus == 0) {
						                         		
						                         		$.ZMessage.info("提示", data.msg, function () {});
						                         		$('#tb-plan').ZTable("reload", {});
						                         	}
						                              
						                       		else{
						                   				$.ZMessage.error("错误", data.msg, function () {});
						                   			}
							                    		$('#zds-table-notarize').ZTable("reload");
						                         	}
						                         });
						                 }
						                 if (r == 'cancel') {
						                 }
						             }, {
						                'confirm': {id: 'confirm', text: '确定', buttonCls: 'btn-blue'},
						            	'cancel':  {id: 'cancel', text: '取消', buttonCls: 'btn-gray'}
						             });
						    	};
								
								/* CALLBACK.deleteStandardContractSetting = function(index,data){
									$.ZMessage.confirm("确认删除", "请确认是否删除此公证记录", function (r) {
						                
						            	 if (r == 'confirm') {
									$.ajax({
						                type: 'post',
						                url: '<z:ukey key="com.zdsoft.finance.contract.standardContractSetting.deleteStandardContractSetting" context="admin"/>',
						                data: {id : row.id},
						                dataType: 'json',
						                success: function (data) {
						                    if (data.resultStatus == 0) {
						                    	$.ZMessage.success("提示", "删除成功", function () {
						                    		$('#contacts_datagrid_view').ZTable("reload");
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
						                 if (r == 'cancel') {
						                 }
						             }, {
						                'confirm': {id: 'confirm', text: '确定', buttonCls: 'btn-blue'},
						            	'cancel':  {id: 'cancel', text: '取消', buttonCls: 'btn-gray'}
						             });
						            	 }
						    	}
								} */
								$.ZUI.init();

							});
		</script>
</body>
</html>