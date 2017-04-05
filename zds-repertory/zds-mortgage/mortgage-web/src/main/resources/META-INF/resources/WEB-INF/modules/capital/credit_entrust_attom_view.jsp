<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<%@ include file='../common/common_js.jsp'%>

<title>信托计划转让信息</title>
</head>
<body>
<div class="page-box">
	<div class="p10">
		<form id="credit_entrust_form" class="zui-form " method="post"
			enctype="multipart/form-data">
			<input type="hidden" id="tempUuid" name="tempUuid" value="${tempUuid }"/>
			<input type="hidden" id="id" name="id" value="${creditEntrustAttomVo.id }"/>
			<input type="hidden" id="creditEntrustId" name="creditEntrustId" value="${creditEntrustId}"/>
			
			<div class="page-box">
		        <div class="page-title">受让方信息</div>
		        <div class="p5">
		            <table class="table-detail">
		                <tr>
		                    <td class="td-title pct10">受让方类型</td>
		                    <td class="pct20">${creditEntrustAttomVo.acceptTypeName }</td>
		                    <td class="td-title pct10">受让方名称</td>
		                    <td class="pct20">${creditEntrustAttomVo.acceptName }</td>
		                    <td class="td-title pct10">转让状态</td>
		                    <td class="pct20">${ creditEntrustAttomVo.attomStateName}</td>
		                </tr>
		                <tr>
		                	<td class="td-title pct10">组织机构代码</td>
		                    <td class="pct20">${creditEntrustAttomVo.orgCd }</td>
		                    <td class="td-title pct10">联系人</td>
		                    <td class="pct20">${creditEntrustAttomVo.contactName }</td>
		                    <td class="td-title pct10">身份证号码</td>
		                    <td class="pct20">${ creditEntrustAttomVo.cardNo}</td>
		                </tr>
		                <tr>
		                	<td class="td-title pct10">联系地址</td>
		                    <td class="pct20">${creditEntrustAttomVo.address }</td>
		                    <td class="td-title pct10">移动电话</td>
		                    <td class="pct20">${creditEntrustAttomVo.mobile }</td>
		                    <td class="td-title pct10">固定电话</td>
		                    <td class="pct20">${ creditEntrustAttomVo.phone}</td>
		                </tr>
		            </table>
	            </div>
            </div>
            <div class="page-box">
		        <div class="page-title">合同要素信息</div>
		        <div class="p5">
		            <table class="table-detail">
		                <tr>
		                    <td class="td-title pct10">受让金额</td>
		                    <td class="pct20">${creditEntrustAttomVo.acceptAmount }</td>
		                    <td class="td-title pct10">合同收益率</td>
		                    <td class="pct20">${creditEntrustAttomVo.contractProfitRate }</td>
		                    <td class="td-title pct10">转让生效日期</td>
		                    <td class="pct20">${ creditEntrustAttomVo.attomEffect}</td>
		                </tr>
		                <tr>
		                	<td class="td-title pct10">单一合同编号</td>
		                    <td class="pct20">${creditEntrustAttomVo.contractNo }</td>
		                    <td class="td-title pct10">转让合同编号</td>
		                    <td class="pct20">${creditEntrustAttomVo.attomContractNo }</td>
		                    <td class="td-title pct10">转让截止日期</td>
		                    <td class="pct20">${ creditEntrustAttomVo.attomEndDate}</td>
		                </tr>
		            </table>
	            </div>
            </div>
			<div class="page-box">
		        <div class="page-title">其他信息</div>
		        <div class="p5">
		            <table class="table-detail">
		                <tr>
		                    <td class="td-title pct10">受让人开户银行</td>
		                    <td class="pct20">${creditEntrustAttomVo.assigneeAccBank }</td>
		                    <td class="td-title pct10">受让人账户名</td>
		                    <td class="pct20">${creditEntrustAttomVo.assigneeAccName }</td>
		                    <td class="td-title pct10">受让人账号</td>
		                    <td class="pct20">${ creditEntrustAttomVo.assigneeAccNo}</td>
		                </tr>
		                <tr>
		                	<td class="td-title pct10">派息周期</td>
		                    <td class="pct20">${creditEntrustAttomVo.payoutPeriod }</td>
		                    <td class="td-title pct10">付息日</td>
		                    <td class="pct20">${creditEntrustAttomVo.termDay }</td>
		                    <td class="td-title pct10">转让截止日期</td>
		                    <td class="pct20">${ creditEntrustAttomVo.attomEndDate}</td>
		                </tr>
		                <tr>
		                    <td class="td-title">备注</td>
		                    <td colspan="5">${creditEntrustAttomVo.remark }</td>
		                </tr>
		            </table>
	            </div>
            </div>
            <div class="page-box">
				<div class="page-title">操作日志</div>
				<div class="p10">
					<div id="log_datagrid" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.capital.getCreditLogs' context='admin'/>&jsoncallback=?&businessId|E|S=${creditEntrustAttomVo.id }","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index"}'>
						<table>
							<thead>
			        			<tr>
			            			<th data-options="field:operationTypeName">操作类型</th>
			            			<th data-options="field:operationContent">操作内容</th>
			            			<th data-options="field:operationEmpName">处理人</th>
			            			<!-- <th data-options="field:remark">备注</th> -->
			            			<th data-options="field:operationDateName">操作时间</th>
						        </tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>
<div id="zds_btn_selecter"></div>
<div id="chooseMember"></div>
		<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
			
			// 控制页面显示
			CALLBACK.acceptTypeChange = function(index,rowData){
				if('tftp001' == index){
					$('#orgCdDl').hide();
					$('#contactDl').hide();
					$('#emptyDl').hide();
				}else if('tftp002' == index){
					$('#orgCdDl').show();
					$('#contactDl').show();
					$('#emptyDl').show();
				}
			};		
			
			// 初始化
			$.ZUI.init();
			
			// 保存按钮点击
			$('#saveBtn').click(function(){
				var isValidate = $.ZUI.validateForm($('#credit_entrust_form'));
				if(isValidate){
					var param = $('#credit_entrust_form').serialize();
					$.ajax({
	                    type: 'post',
	                    url: '<z:ukey key="com.zdsoft.finance.capital.saveEntrustAttom" context="admin"/>',
	                    data: param,
	                    dataType: 'json',
	                    success: function (data) {
	                        if (data.resultStatus == 0) {
	                        	$.ZMessage.success("成功", data.msg, function () {
	                        		setTimeout(function(){
	                                	ZDS_MESSAGE_CLIENT.refreshOpenner();
	                               	    ZDS_MESSAGE_CLIENT.closeSelf();
	                                },200);
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
				}
			});
		});
	</script>
</body>
</html>