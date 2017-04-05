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
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>受让方类型：</dt>
					<dd class="detail">
						<input class="zui-combobox zui-validatebox" id="acceptType" name="acceptType" type="hidden" value="${creditEntrustAttomVo.acceptType }"
                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00138"
                              data-valuefield="fullcode" data-callback="acceptTypeChange" data-textfield="name" validate-type="Require">
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>受让方名称：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Length[1-64]" value="${creditEntrustAttomVo.acceptName }"
							 id="acceptName" name="acceptName">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>转让状态：</dt>
					<dd class="detail">
						<input class="zui-combobox zui-validatebox" id="attomState" name="attomState" type="hidden" value="${creditEntrustAttomVo.attomState }"
                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00135"
                              data-valuefield="fullcode" validate-type="Require">
					</dd>
				</dl>
				<dl class="form-item" style="display:none" id="orgCdDl">
					<dt class="title">组织机构代码：</dt>
					<dd class="detail">
						<label> <input class="zui-input " value="${creditEntrustAttomVo.orgCd }"
							 id="orgCd" name="orgCd">
						</label>
					</dd>
				</dl>
				<dl class="form-item" style="display:none" id="contactDl">
					<dt class="title">联系人：</dt>
					<dd class="detail">
						<label> <input class="zui-input "  value="${creditEntrustAttomVo.contactName }"
							 id="contactName" name="contactName">
						</label>
					</dd>
				</dl>
				<dl class="form-item" style="display:none" id="emptyDl"></dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>身份证号码：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,IDCard" value="${creditEntrustAttomVo.cardNo }"
							 id="cardNo" name="cardNo">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>联系地址：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Length[1-256]" value="${creditEntrustAttomVo.address }"
							 id="address" name="address">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>移动电话：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Mobile" value="${creditEntrustAttomVo.mobile }"
							 id="mobile" name="mobile">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>固定电话：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Phone" value="${creditEntrustAttomVo.phone }"
							 id="phone" name="phone">
						</label>
					</dd>
				</dl>
			</div>
			<div class="page-box">
				<div class="page-title">合同要素信息</div>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>受让金额：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Digital[18-12]" value="${creditEntrustAttomVo.acceptAmount }"
							 id="acceptAmount" name="acceptAmount">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title  sptitle"><b class="c-red mr5">*</b>合同收益率(%)：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Digital[18-12]" value="${creditEntrustAttomVo.contractProfitRate }"
							 id="contractProfitRate" name="contractProfitRate">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>转让生效日期：</dt>
					<dd class="detail">
						 <label>
                            <input type="text" id="attomEffectLocal" class="zui-input zui-validatebox" validate-type="Require" value="${creditEntrustAttomVo.attomEffect }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'attomEffect'})">
                            <input type="hidden" name="attomEffect" value="${creditEntrustAttomVo.attomEffect }" id="attomEffect" />
                         </label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>单一合同编号：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Length[1-32]" value="${creditEntrustAttomVo.contractNo }"
							 id="contractNo" name="contractNo">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>转让合同编号：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Length[1-32]" value="${creditEntrustAttomVo.attomContractNo }"
							 id="attomContractNo" name="attomContractNo">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>转让截止日期：</dt>
					<dd class="detail">
						 <label>
                            <input type="text" id="attomEndDateLocal" class="zui-input zui-validatebox" validate-type="Require" value="${creditEntrustAttomVo.attomEndDate }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'attomEndDate'})">
                            <input type="hidden" name="attomEndDate" value="${creditEntrustAttomVo.attomEndDate }" id="attomEndDate" />
                         </label>
					</dd>
				</dl>
			</div>
			<div class="page-box">
				<div class="page-title">其他信息</div>
				<dl class="form-item">
					<dt class="title sptitle"><b class="c-red mr5">*</b>受让人开户银行：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Length[1-32]" value="${creditEntrustAttomVo.assigneeAccBank }"
							 id="assigneeAccBank" name="assigneeAccBank">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>受让人账户名：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Length[1-64]" value="${creditEntrustAttomVo.assigneeAccName }"
							 id="assigneeAccName" name="assigneeAccName">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>受让人账号：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Digital[18-12]" value="${creditEntrustAttomVo.assigneeAccNo }"
							 id="assigneeAccNo" name="assigneeAccNo">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>派息周期：</dt>
					<dd class="detail">
						<input class="zui-combobox zui-validatebox" id="payoutPeriod" name="payoutPeriod" type="hidden" value="${creditEntrustAttomVo.payoutPeriod }"
                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00136"
                              data-valuefield="fullcode" validate-type="Require">
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>付息日：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Number,Size[1-31]"  value="${creditEntrustAttomVo.termDay }"
							 id="termDay" name="termDay">
						</label>
					</dd>
				</dl>
				<dl class="form-item block">
                	<dt class="title">备注：</dt>
	                <dd class="detail">
		                <label>
		                	<textarea class="zui-area zui-validatebox" id="remark" name="remark" validate-type="Length[0-200]" placeholder="最多可以输入200个字符">${creditEntrustAttomVo.remark }</textarea>
		                </label>
	                </dd>
                </dl>
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