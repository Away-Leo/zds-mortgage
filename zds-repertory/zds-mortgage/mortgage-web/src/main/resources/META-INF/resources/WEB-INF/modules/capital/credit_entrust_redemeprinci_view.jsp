<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<%@ include file='../common/common_js.jsp'%>
<title>本金投入信息</title>
</head>
<body>
<div class="page-box">
	<div class="p10">
		<form id="credit_entrust_form" class="zui-form " method="post"
			enctype="multipart/form-data">
			<input type="hidden" id="tempUuid" name="tempUuid" value="${tempUuid }"/>
			<input type="hidden" id="id" name="id" value="${creditEntrustRedemPrinciVo.id }"/>
			<input type="hidden" id="creditEntrustId" name="creditEntrustId" value="${creditEntrustId}"/>
			
			<div class="page-box">
		        <div class="page-title">本金投入信息</div>
		        <div class="p5">
		            <table class="table-detail">
		                <tr>
		                    <td class="td-title pct10">出资方</td>
		                    <td class="pct20">${creditEntrustRedemPrinciVo.contribution }</td>
		                    <td class="td-title pct10">出资方类型</td>
		                    <td class="pct20">${creditEntrustRedemPrinciVo.contributionTypeName }</td>
		                    <td class="td-title pct10">赎回金额</td>
		                    <td class="pct20">${creditEntrustRedemPrinciVo.redemptionAmount }</td>
		                </tr>
		                 <tr>
		                    <td class="td-title">备注</td>
		                    <td colspan="5">${creditEntrustRedemPrinciVo.remark }</td>
		                </tr>
		            </table>
	            </div>
	            <div class="page-box">
				<div class="page-title">操作日志</div>
				<div class="p10">
					<div id="log_datagrid" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.capital.getCreditLogs' context='admin'/>&jsoncallback=?&businessId|E|S=${creditEntrustRedemPrinciVo.id }","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index"}'>
						<table>
							<thead>
			        			<tr>
			            			<th data-options="field:operationTypeName">操作类型</th>
			            			<th data-options="field:operationContent">操作内容</th>
			            			<th data-options="field:operationEmpName">处理人</th>
			            			<!-- <th data-options="field:remark">备注</th> -->
			            			<th data-options="field:operationDate">操作时间</th>
						        </tr>
							</thead>
						</table>
					</div>
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
			
			// 初始化
			$.ZUI.init();
			
			// 保存按钮点击
			$('#saveBtn').click(function(){
				var isValidate = $.ZUI.validateForm($('#credit_entrust_form'));
				if(isValidate){
					var param = $('#credit_entrust_form').serialize();
					$.ajax({
	                    type: 'post',
	                    url: '<z:ukey key="com.zdsoft.finance.capital.saveRedemPrinci" context="admin"/>',
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