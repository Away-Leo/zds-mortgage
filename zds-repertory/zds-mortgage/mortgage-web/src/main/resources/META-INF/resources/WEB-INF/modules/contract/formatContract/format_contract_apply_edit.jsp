<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../../common/common_js.jsp'%>
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>格式化合同申请</title>
</head>
<body>
	<div class="page-box">
		<div class="p10">
			<form id="edit_form" class="zui-form " method="post" enctype="multipart/form-data">
				<div class="page-title">
					<h1 class="page-title">基本信息</h1>
				</div>
				<div class="page-box">
					<div class="p5">
			            <table class="table-detail">
							<tr>
			                    <td class="td-title pct10">申请单</td>
			                    <td class="pct20">
			                    	${vo.applyNo }
			                    	<input type="hidden" name="applyNo" value="${vo.applyNo}"/>
			                    </td>
			                    <td class="td-title pct10">申请人状态</td>
			                    <td class="pct20">
			                    	未申请
			                    </td>
							</tr>
						</table>
					</div>
				</div>
				
				<div class="page-title">
					<h1 class="page-title">打印资料明细清单</h1>
				</div>
				<div class="page-box">
					<div class="p10">
						<div id="tb-plan" class="zui-datagrid"
							zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.contract.formatContractDetailList" context="admin"/>&jsoncallback=?&formatContractApplyId|E|S=${vo.id}","singleSelect":true,"pagination":true,"idField":"id","toolbar":"#btn-function","tableCls":"table-index"}'>
							<table>
								<tr>
									<th data-options="field:capitalistName">资方</th>
						            <th data-options="field:contractTypeName">合同类型</th>
						            <th data-options="field:contractName">合同名称</th>
						            <th data-options="field:contractCopies">份数</th>
						            <th data-options="field:id" formatter="operateFormat">操作</th>
								</tr>
							</table>
						</div>
						<div id="btn-function">
							<a class="zui-toolbar" id="btn-add" text="新增合同" iconCls="icon-add" buttonCls="btn-blue" handler="doAdd"></a>
						</div>
					</div>
				</div>
				
				<div class="page-title">
					<h1 class="page-title">其他信息</h1>
				</div>
				<div class="page-box">
					<div class="p5">
			            <table class="table-detail">
							<tr>
			                    <td class="td-title pct10">申请人</td>
			                    <td class="pct20">
			                    	<input class="zui-input zui-validatebox" disabled="disabled" value="${emp.empNm}">
			                    	<input type="hidden" name="applyEmpCode" value="${emp.empCd}"/>
			                    </td>
			                    <td class="td-title pct10">申请时间</td>
			                    <td class="pct20">
			                    	<label>
			                            <input type="zui-input zui-validatebox" id ="showApplyData" disabled="disabled" value="${vo.applyDate}"/>
			                            <input type="hidden" name="applyDate" value="${vo.applyDate}"/>
				                    </label>
								</td>
							</tr>
							<tr>
				         		<td class="td-title pct10">备注</td>
				                <td colspan="3">
				                    <label>
				                    	<textarea class="zui-area row-width" id="remark" name="remark" validate-type="Require,Length[0-200]" placeholder="最多可以输入200个字符">${vo.remark }</textarea>
				                    </label>
			                   	</td>
							</tr>
						</table>
					</div>
				</div>
				<input type="hidden" name="id" id="formatContractApplyId" value="${vo.id }">
			</form>
			<div class="form-btn">
				<button id="btn-save" class="btn-blue mr10" type="button">保存</button>
				<button id="btn-sub" class="btn-blue mr10" type="button">提交</button>
			</div>
		</div>
	</div>
	<div id="contactsDialog"  style="display: none">
	<script type="text/javascript">
		seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.combobox',
		           'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 
		           'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'
		           ],function($, CALLBACK,ZTOOlS, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {

			//操作
			CALLBACK.operateFormat = function(index, row) {
				var html = '<a title="编辑" onclick="doEdit" class="btn-blue">编辑</a>';
					html += '&nbsp;&nbsp;<a title="删除" onclick="doDel" class="btn-blue">删除</a>';
	            return html;
			};
			
			//新增弹出框打开
			CALLBACK.doAdd = function(index, row){
		  		var formatContractApplyId = $("#formatContractApplyId").val();
		  		if(formatContractApplyId==null || formatContractApplyId==""){
		  			$.ZMessage.error("错误", "请先保存格式化合同申请信息", function(){});
		  		}else{
		  			var	url = '<z:ukey key="com.zdsoft.finance.contract.editFormatContractDetail" context="admin"/>&operationType=add&formatContractApplyId='+formatContractApplyId;
					$('#contactsDialog').load(url,function(){});
		  		}
			};
			//编辑弹出框打开
			CALLBACK.doEdit = function(index, row){
				var formatContractApplyId = $("#formatContractApplyId").val();
		  		var id = row.id;
		  		var	url = '<z:ukey key="com.zdsoft.finance.contract.editFormatContractDetail" context="admin"/>&operationType=edit&id='+id+'&formatContractApplyId='+formatContractApplyId;
				$('#contactsDialog').load(url,function(){});
			};
			
			//删除
			CALLBACK.doDel = function(index,row){
				$.ZMessage.question("提示", "是否删除", function (index) {
					$.ajax({
			            type: 'post',
			            data: {id : row.id},
			            url: '<z:ukey key="com.zdsoft.finance.contract.delFormatContractDetail" context="admin"/>',
			            dataType: 'json',
			            success: function (data) {
			                if (data.resultStatus == 0) {
			                	$.ZMessage.success("提示", "删除成功", function () {
			                		$("#tb-plan").ZTable("reload");
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
				});
			};
			
			//保存
			$("#btn-save").click(function(){
				save(0);
			});
			
			//提交
			$("#btn-sub").click(function(){
				save(1);
			});
			
			function save(isSubmit){
				var flag = $('#edit_form').ZDSValidatebox('validateAll', $('#edit_form'));
				if(flag){
					var params = $("#edit_form").serialize();
					$.ajax({
	                    type: 'post',
	                    url: '<z:ukey key="com.zdsoft.finance.contract.saveFormatContractApply" context="admin"/>&isSubmit='+isSubmit,
	                    data: params,
	                    dataType: 'json',
	                    success: function (data) {
	                        if (data.resultStatus == 0) {
	                            $("#formatContractApplyId").val(data.id);
	                        	$.ZMessage.success("提示", data.msg, function () {
                         			//刷新父页面
                                 	if(isSubmit==1){
                                        ZDS_MESSAGE_CLIENT.refreshOpenner();
                                        //关闭本页面
                                        ZDS_MESSAGE_CLIENT.closeSelf();
									}
	                          	 });
	                        }else{
	                        	$.ZMessage.error("错误", data.msg, function () {
		                        });
	                        }
	                    },
				        error: function () {
				        	$.ZMessage.error("错误", "格式化合同保存系统异常，请联系管理员", function () {});
				        }
	                });
				}
			}
            $("#showApplyData").val(ZTOOlS.strToDate("${vo.applyDate}"));
		 	$.ZUI.init();
		 });
	</script>
</body>
</html>