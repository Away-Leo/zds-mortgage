<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<%@ include file='../common/common_js.jsp'%>
<title>备付资金跟踪</title>
</head>
<body>
<div class="page-box">
	<div class="p10">
		<form id="credit_entrust_form" class="zui-form " method="post"
			enctype="multipart/form-data">
			<input type="hidden" id="tempUuid" name="tempUuid" value="${tempUuid }"/>
			<input type="hidden" id= "id" name="id" value="${spareCapitalVo.id }"/>
			<input type="hidden" id="creditEntrustId" name="creditEntrustId" value="${creditEntrustId}"/>
			<div class="page-box">
				<div class="page-title">请款信息</div>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>操作类型：</dt>
					<dd class="detail">
						<input class="zui-combobox zui-validatebox" id="operationType" name="operationType" type="hidden" value="${spareCapitalVo.operationType }"
                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00144"
                              data-valuefield="fullcode" data-textfield="name" validate-type="Require">
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>请款金额：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Digital[18-12]" value="${spareCapitalVo.applyAmount }"
							 id="applyAmount" name="applyAmount">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>用款日期：</dt>
					<dd class="detail">
						 <label>
                            <input type="text" id="useDateLocal" class="zui-input  zui-validatebox" validate-type="Require" value="${spareCapitalVo.useDate }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'useDate'})">
                            <input type="hidden" name="useDate" id="useDate" value="${spareCapitalVo.useDate }" />
                         </label>
					</dd>
				</dl>
				<dl class="form-item block">
                	<dt class="title">备注：</dt>
	                <dd class="detail">
		                <label>
		                	<textarea class="zui-area zui-validatebox" id="applyRemark" name="applyRemark" validate-type="Length[0-200]" placeholder="最多可以输入200个字符">${spareCapitalVo.applyRemark }</textarea>
		                </label>
	                </dd>
                </dl>
			</div>
			<div class="page-box">
				<div class="page-title">确认到账信息</div>
				<dl class="form-item">
					<dt class="title sptitle">实际到账金额：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Digital[18-12]"
							 id="actualAmount" value="${spareCapitalVo.actualAmount }" name="actualAmount">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title sptitle">实际到账日期：</dt>
					<dd class="detail">
						 <label>
                            <input type="text" id="actualArrivalDateLocal"  value="${spareCapitalVo.actualArrivalDate }" class="zui-input  "  onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'actualArrivalDate'})">
                            <input type="hidden" name="actualArrivalDate"  value="${spareCapitalVo.actualArrivalDate }" id="actualArrivalDate" />
                         </label>
					</dd>
				</dl>
				<dl class="form-item"></dl>
				<dl class="form-item block">
                	<dt class="title">备注：</dt>
	                <dd class="detail">
		                <label>
		                	<textarea class="zui-area zui-validatebox" id="actualRemark" name="actualRemark" validate-type="Length[0-200]" placeholder="最多可以输入200个字符">${spareCapitalVo.actualRemark }</textarea>
		                </label>
	                </dd>
                </dl>
			</div>
			<div class="page-box">
				<div class="page-title">操作日志</div>
				<div class="p10">
					<div id="log_datagrid" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.capital.getCreditLogs' context='admin'/>&jsoncallback=?&businessId|E|S=${spareCapitalVo.id }","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index"}'>
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
            <div class="form-btn">
               	<button id="saveBtn" type="button" class="btn-blue">提交</button>
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
			
			// 保存按钮事件
			$('#saveBtn').click(function(){
				var isValidate = $.ZUI.validateForm($('#credit_entrust_form'));
				if(isValidate){
					var param = $('#credit_entrust_form').serialize();
					$.ajax({
	                    type: 'post',
	                    url: '<z:ukey key="com.zdsoft.finance.capital.saveSpareCapital" context="admin"/>',
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