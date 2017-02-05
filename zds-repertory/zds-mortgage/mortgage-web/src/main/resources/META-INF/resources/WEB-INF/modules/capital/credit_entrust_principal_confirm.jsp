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
			<input type="hidden" id="id" name="id" value="${creditEntrustPrincipalVo.id }"/>
			<input type="hidden" id="creditEntrustId" name="creditEntrustId" value="${creditEntrustId}"/>
			<div class="page-box">
				<div class="page-title">本金投入信息</div>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>出资方：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require" value="${creditEntrustPrincipalVo.contribution }"
							 id="contribution" name="contribution">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>出资方类型：</dt>
					<dd class="detail">
						<input class="zui-combobox zui-validatebox" id="contributionType" name="contributionType" type="hidden" value="${creditEntrustPrincipalVo.contributionType }"
                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=ittp"
                              data-valuefield="fullcode" data-textfield="name" validate-type="Require">
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>本金金额(元)：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-disable zui-validatebox" disabled validate-type="Require,Digital[18-12]" value="${creditEntrustPrincipalVo.principalAmount }"
							 id="principalAmount" name="principalAmount">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>合同收益率(%)：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Digital[18-12]" value="${creditEntrustPrincipalVo.profitRate }"
							 id="profitRate" name="profitRate">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>追加日期：</dt>
					<dd class="detail">
						 <label>
                            <input type="text" id="addDateLocal" value="${creditEntrustPrincipalVo.addDate }" class="zui-input zui-disable  zui-validatebox" disabled validate-type="Require" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'addDate'})">
                            <input type="hidden" name="addDate" value="${creditEntrustPrincipalVo.addDate }" id="addDate" />
                         </label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>使用比率(%)：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Digital[18-12]" value="${creditEntrustPrincipalVo.useProp }"
							 id="useProp" name="useProp">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>预计到账日期：</dt>
					<dd class="detail">
						 <label>
                            <input type="text" id="expectDateLocal" class="zui-input zui-validatebox" validate-type="Require" value="${creditEntrustPrincipalVo.expectDate }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'expectDate'})">
                            <input type="hidden" name="expectDate" id="expectDate" value="${creditEntrustPrincipalVo.expectDate }" />
                         </label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>到账日期：</dt>
					<dd class="detail">
						 <label>
                            <input type="text" id="actualDateLocal" class="zui-input zui-validatebox" validate-type="Require" value="${creditEntrustPrincipalVo.actualDate }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'actualDate'})">
                            <input type="hidden" name="actualDate" id="actualDate" value="${creditEntrustPrincipalVo.actualDate }" />
                         </label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>使用额度(元)：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-disable zui-validatebox" validate-type="Require,Digital[18-12]" value="${creditEntrustPrincipalVo.usedQuota }"
							 id="usedQuota" name="usedQuota" disabled>
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>派息周期：</dt>
					<dd class="detail">
						<input class="zui-combobox zui-validatebox" id="payoutPeriod" name="payoutPeriod" type="hidden" value="${creditEntrustPrincipalVo.payoutPeriod }"
                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=popd"
                              data-valuefield="fullcode" data-textfield="name" validate-type="Require">
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>派息日：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Digital[18-12]" value="${creditEntrustPrincipalVo.termDay }"
							 id="termDay" name="termDay">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>到期日期：</dt>
					<dd class="detail">
						 <label>
                            <input type="text" id="maturityDateLocal" class="zui-input  zui-validatebox" validate-type="Require" value="${creditEntrustPrincipalVo.maturityDate }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'maturityDate'})">
                            <input type="hidden" name="maturityDate" id="maturityDate" value="${creditEntrustPrincipalVo.maturityDate }" />
                         </label>
					</dd>
				</dl>
				<dl class="form-item block">
                	<dt class="title">备注：</dt>
	                <dd class="detail">
		                <label>
		                	<textarea class="zui-area zui-validatebox" id="remark" name="remark" validate-type="Length[0-200]" placeholder="最多可以输入200个字符">${creditEntrustPrincipalVo.remark }</textarea>
		                </label>
	                </dd>
                </dl>	
			</div>
			<div class="page-box">
				<div class="page-title">操作日志</div>
				<div class="p10">
					<div id="log_datagrid" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.capital.getCreditLogs' context='admin'/>&jsoncallback=?&businessId|E|S=${creditEntrustPrincipalVo.id }","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index"}'>
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
            <div class="form-btn">
               	<button id="saveBtn" type="button" class="btn-blue">确认到账</button>
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
	                    url: '<z:ukey key="com.zdsoft.finance.capital.confirmEntrustPrincipal" context="admin"/>',
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
			
	
			// 添加操作事件
			$('#principalAmount').change(function(){
				var principalAmount = $('#principalAmount').val();
				var useProp = $('#useProp').val();
				
				if(principalAmount && useProp){
					$('#usedQuota').val(parseFloat(principalAmount) * parseFloat(useProp) / 100);
				}
			});
			$('#useProp').change(function(){
				var principalAmount = $('#principalAmount').val();
				var useProp = $('#useProp').val();
				
				if(principalAmount && useProp){
					$('#usedQuota').val(parseFloat(principalAmount) * parseFloat(useProp) / 100);
				}
			});
			
		});
	</script>
</body>
</html>