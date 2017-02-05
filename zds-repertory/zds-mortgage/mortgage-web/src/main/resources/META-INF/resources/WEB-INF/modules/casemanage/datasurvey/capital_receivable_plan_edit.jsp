<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<div class="frm-content frm-bottom" id="opinionDiv">
	<form id="receivablePlanForm" class="zui-form" action="javascript:void(0);">
		<div class="page-box">
			<input type="hidden" value="${caseApply.id }" name="caseApplyId"/>
			<input type="hidden" value="${receivableInfo.id }" name="id"/>
			<div class="page-title">基本信息</div>
			<div class="p5">
				<table class="table-detail">
					<tr>
						<td class="td-title pct10">案件号</td>
						<td class="pct20">${caseApply.caseApplyCode }</td>
						<td class="td-title pct10">主借人</td>
						<td class="pct20">${caseApply.customerName }</td>
						<td class="td-title pct10">贷款金额(元)</td>
						<td class="pct30">${caseApply.applyAmount }</td>
					</tr>
					<tr>
						<td class="td-title pct10">贷款期限</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${caseApply.applyDeadline }"
										class="zui-input nwidth2 zui-validatebox" readonly
										validate-type="Require" />
									</label>
								</dd>
								<dd class="detail">
									<input class="zui-combobox zui-validatebox" type="hidden" name="applyDeadlineUnit"
										data-width="94" value="${caseApply.applyDeadlineUnit }"
										data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=rateUtil"
										data-valuefield="fullcode" data-textfield="name" data-choose="disable">
								</dd>
							</dl>
						</td>
						<td class="td-title pct10"><b class="c-red mr5">*</b>贷款利率</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${receivableInfo.loanMonthRate }"
										class="zui-input nwidth2 zui-validatebox" name="loanMonthRate"
										validate-type="Require" />
									</label>
								</dd>
								<dd class="detail">
									<input class="zui-combobox zui-validatebox" type="hidden" name="loanMonthRateUnit" value="${receivableInfo.loanMonthRateUnit }"
										data-width="94" validate-type="Require"
										data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=rateUtil"
										data-valuefield="fullcode" data-textfield="name">
								</dd>
							</dl>
						</td>
						<td class="td-title pct10"><b class="c-red mr5">*</b>还款方式</td>
						<td class="pct30">
							<dl class="form-item form-auto">
								<dd class="detail">
									<input class="zui-combobox" type="hidden" name="repaymentType" value="${receivableInfo.repaymentType }"
										data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0051"
										data-valuefield="fullcode" data-textfield="name">
								</dd>
							</dl>
							<dl class="form-item form-auto">
								<dd class="detail">
									<input class="zui-combobox" type="hidden" name="repaymentTypeTwo" value="${receivableInfo.repaymentTypeTwo }"
										data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0051"
										data-valuefield="fullcode" data-textfield="name">
								</dd>
							</dl>
						</td>
					</tr>
					<tr>
						<td class="td-title pct10"><b class="c-red mr5">*</b>利率性质</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<input class="zui-combobox zui-validatebox" type="hidden" name="rateNature" value="${receivableInfo.rateNature }"
										data-width="150"
										data-data="[{'id':'0','text':'自动转为银行利率','isDefault':'true'},{'id':'1','text':'填写固定利率'},{'id':'2','text':'%/日'}]"
										data-callback="change" data-id="isAgriculture"
										data-valuefield="id" data-textfield="text"
										validate-type="Require">
								</dd>
								<dd class="detail" id="">
									<input class="zui-combobox zui-validatebox" type="hidden"
										data-width="150"
										data-data="[{'id':'0','text':'放款即收第一期','isDefault':'true'}]"
										data-callback="change" data-id="isAgriculture"
										data-valuefield="id" data-textfield="text"
										validate-type="Require">
								</dd>
							</dl>
						</td>
						<td class="td-title pct10"><b class="c-red mr5">*</b>逾期利率</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text"
										class="zui-input nwidth2 zui-validatebox" name="overdueDailyRate" value="${receivableInfo.overdueDailyRate }"
										validate-type="Require" />
									</label>
								</dd>
								<dd class="detail">
									<input class="zui-combobox zui-validatebox" type="hidden" name="overdueDailyRateUnit" value="${receivableInfo.overdueDailyRateUnit }"
										data-width="94" validate-type="Require"
										data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=rateUtil"
										data-valuefield="fullcode" data-textfield="name">
								</dd>
							</dl>
						</td>
						<td class="td-title pct10"><b class="c-red mr5">*</b>预计放款日期</td>
						<td class="pct30">
							<dl class="form-item form-auto">
								<dd class="detail">
									 <label> 
										<input class="zui-date zui-validatebox strToDate" type="text" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'expectLoanDate',dateFmt:'yyyy-MM-dd'})" validate-type="Require" readonly value="${receivableInfo.expectLoanDate }"/>
										<input type="hidden" id="expectLoanDate" name="expectLoanDate" value="${receivableInfo.expectLoanDate }"/>
									</label>
								</dd>
							</dl>
						</td>
					</tr>
					<tr>
						<td class="td-title pct10"><b class="c-red mr5">*</b>还款日</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${receivableInfo.repaymentDate }"
										class="zui-input nwidth2 zui-validatebox" name="repaymentDate"
										validate-type="Require" />
									</label>
								</dd>
								<dd class="detail">
									<input class="zui-combobox zui-validatebox" type="hidden" 
										data-width="94"
										data-data="[{'id':'0','text':'日','isDefault':'true'}]"
										data-callback="change" data-id="isAgriculture"
										data-valuefield="id" data-textfield="text" data-choose="disable"
										validate-type="Require">
								</dd>
							</dl>
						</td>
						<td class="td-title pct10"><b class="c-red mr5">*</b>综合利率</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" name="syntheticalRate" value="${receivableInfo.syntheticalRate }"
										class="zui-input nwidth2 zui-validatebox"
										validate-type="Require" />
									</label>
								</dd>
								<dd class="detail">
									<input class="zui-combobox zui-validatebox" type="hidden" name="syntheticalRateUnit" validate-type="Require"
										data-width="94" value="${receivableInfo.syntheticalRateUnit }"
										data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=rateUtil"
										data-valuefield="fullcode" data-textfield="name">
								</dd>
							</dl>
						</td>
						<td class="td-title pct10">动态转换利率</td>
						<td class="pct30">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text"
										class="zui-input nwidth2 zui-validatebox"
										validate-type="Require" />
									</label>
								</dd>
								<dd class="detail">
									<input class="zui-combobox zui-validatebox" type="hidden"
										data-width="94"
										data-data="[{'id':'0','text':'%/年','isDefault':'true'},{'id':'1','text':'%/月'},{'id':'2','text':'%/日'}]"
										data-callback="change" data-id="isAgriculture"
										data-valuefield="id" data-textfield="text"
										validate-type="Require">
								</dd>
							</dl>
						</td>
					</tr>
					<tr>
						<td class="td-title pct10"><b class="c-red mr5">*</b>预计开始日期</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<dd class="detail">
										<label> 
											<input class="zui-date zui-validatebox strToDate" type="text" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'expectStartDate',dateFmt:'yyyy-MM-dd'})" validate-type="Require" readonly value="${receivableInfo.expectStartDate }"/>
											<input type="hidden" id="expectStartDate" name="expectStartDate" value="${receivableInfo.expectStartDate }"/>
										</label>
									</dd>
								</dd>
							</dl>
						</td>
						<td class="td-title pct10">逾期天数（天）</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text"
										class="zui-input nwidth2 zui-validatebox"
										validate-type="Require" />
									</label>
								</dd>
							</dl>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<form id="bankAccountForm">
		<div class="page-box">
			<div class="page-title">收款账户</div>
			<input type="hidden" name="loanAccountVo.id" value="${loanAccountVo.id }"/>
			<div class="p5">
				<table class="table-detail">
					<tr>
						<td class="td-title pct10"><b class="c-red mr5">*</b>开户行</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${loanAccountVo.bankAccount }" 
										class="zui-input zui-validatebox" validate-type="Require,Length[0-64]" name="loanAccountVo.bankAccount"/>
									</label>
								</dd>
							</dl>
						</td>
						<td class="td-title pct10"><b class="c-red mr5">*</b>银行代码</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${loanAccountVo.bankCode }"
										class="zui-input zui-validatebox" validate-type="Require,Length[0-32]" name="loanAccountVo.bankCode"/>
									</label>
								</dd>
							</dl>
						</td>
						<td class="td-title pct10"><b class="c-red mr5">*</b>账户名</td>
						<td class="pct30">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${loanAccountVo.cardholderName }"
										class="zui-input zui-validatebox" validate-type="Require,Length[0-64]" name="loanAccountVo.cardholderName"/>
									</label>
								</dd>
							</dl>
						</td>
					</tr>
					<tr>
						<td class="td-title"><b class="c-red mr5">*</b>账号</td>
						<td>
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${loanAccountVo.bankNo }"
										class="zui-input zui-validatebox" validate-type="Require,Length[0-50]" name="loanAccountVo.bankNo"/>
									</label>
								</dd>
							</dl>
						</td>
					</tr>
				</table>

			</div>
		</div>
		
		<div class="page-box">
			<div class="page-title">还款（代扣）账户</div>
			<input type="hidden" name="recAccountVo.id" value="${receivableAccountVo.id }"/>
			<div class="p5">
				<table class="table-detail">
					<tr>
						<td class="td-title pct10"><b class="c-red mr5">*</b>开户行</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${receivableAccountVo.bankAccount }"
										class="zui-input zui-validatebox" validate-type="Require,Length[0-64]" name="recAccountVo.bankAccount"/>
									</label>
								</dd>
							</dl>
						</td>
						<td class="td-title pct10"><b class="c-red mr5">*</b>银行代码</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${receivableAccountVo.bankCode }"
										class="zui-input zui-validatebox" validate-type="Require,Length[0-32]"  name="recAccountVo.bankCode"/>
									</label>
								</dd>
							</dl>
						</td>
						<td class="td-title pct10"><b class="c-red mr5">*</b>账户名</td>
						<td class="pct30">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${receivableAccountVo.cardholderName }"
										class="zui-input zui-validatebox" validate-type="Require,Length[0-64]"  name="recAccountVo.cardholderName"/>
									</label>
								</dd>
							</dl>
						</td>
					</tr>
					<tr>
						<td class="td-title"><b class="c-red mr5">*</b>账号</td>
						<td>
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${receivableAccountVo.bankNo }"
										class="zui-input zui-validatebox" validate-type="Require,Length[0-50]"  name="recAccountVo.bankNo"/>
									</label>
								</dd>
							</dl>
						</td>
					</tr>
				</table>

			</div>
		</div>
		
		<div class="page-box">
			<div id="receivableEditPage"></div>
		</div>
	</form>

</div>
<script>
	seajs.use([ 'jquery', 'zd/iframe', 'zd/tools','zd/jquery.zds.button', 'zd/jquery.zds.seleter','zd/jquery.zds.address','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/make-first-py', 'zd/jquery.zds.form' ], function($,
		IFRAME, ZTOOlS,CALLBACK, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
		$.ZUI.initForms('#opinionDiv');
		
		var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.receivablePlanGeneratePage" context="admin"/>&jsoncallback=?&caseApplyId=${caseApply.id }';
		$('#receivableEditPage').load(url);
		
		//保存还款计划
		window.saveData = function(){
			var status = false;
			var validationRe = $.ZUI.validateForm($('#receivablePlanForm'))
			var validationBank = $.ZUI.validateForm($('#bankAccountForm'))
			if(!validationRe || !validationBank){
				return false;
			}
			//获取所有还款计划
			var rowsData = $('#receivablePlanEdit').ZTable("getRows");
			var receivablePlan=$('#receivablePlanForm').serialize();
			var bankAccountForm=$('#bankAccountForm').serialize();
			bankAccountForm += "&" + receivablePlan;
			bankAccountForm += "&" + 'receivablePlanJson=' + JSON.stringify(rowsData);
			console.log(JSON.stringify(bankAccountForm));
			$.ajax({
	            type: 'post',
	            url: '<z:ukey key="com.zdsoft.finance.casemanage.receivablePlanManager.saveReceivableInfo" context="admin"/>&jsoncallback=?',
	            data : bankAccountForm,
	            dataType: 'json',
	            success: function (data) {
	            	console.log(data);
	                if (data.resultStatus == 0) {
	                	$.ZMessage.success("成功", "保存还款计划成功", function () {
	                		status = true;
	                  	 });
	                }else{
	                	status = false;
	                }
	            },
		        error: function () {
		           	status = false;
	            }
	        });
			
			return status;
		}
		
	});
</script>
