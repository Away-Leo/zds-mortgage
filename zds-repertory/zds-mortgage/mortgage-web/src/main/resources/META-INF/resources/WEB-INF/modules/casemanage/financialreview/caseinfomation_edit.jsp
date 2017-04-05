<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<c:if test="${not empty errorMsg }">页面初始化出现异常，请联系管理员！异常信息为：${errorMsg }</c:if>
<c:if test="${empty errorMsg }">
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
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd>
									<input type="text" value="${caseApply.caseApplyCode }"
										class="zui-input" readonly validate-type="Require"
										disabled="disabled" />
								</dd>
							</dl>
						</td>
						<td class="td-title pct10">主借人</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd>
									<input type="text" value="张三" class="zui-input" readonly
										validate-type="Require" disabled="disabled" />
								</dd>
							</dl>
						</td>
						<td class="td-title pct10">贷款金额(万元)</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd>
									<input type="text" value="${caseApply.applyAmount }"
										class="zui-input" readonly validate-type="Require"
										disabled="disabled" />
								</dd>
							</dl>
						</td>
					</tr>
					<tr>
						<td class="td-title pct10">贷款期限</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text"
										value="${caseApply.applyTerm }" class="zui-input nwidth2"
										readonly validate-type="Require" disabled="disabled" />
									</label>
								</dd>
								<dd class="detail">
									<input class="zui-combobox " type="hidden"
										name="applyTermUnit" data-width="94"
										value="${caseApply.applyTermUnit }"
										data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0003"
										data-valuefield="fullcode" data-textfield="name"
										data-choose="disable">
								</dd>
							</dl>
						</td>
						<td class="td-title pct10"><b class="c-red mr5">*</b>贷款利率(%)</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text"
										value="${receivableInfo.loanMonthRate }"
										class="zui-input nwidth2 " name="loanMonthRate"
										disabled="disabled" />
									</label>
								</dd>
								<dd class="detail">
									<input class="zui-combobox " type="hidden"
										name="loanMonthRateUnit"
										value="${receivableInfo.loanMonthRateUnit }" data-width="94"
										data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0003"
										data-valuefield="fullcode" data-textfield="name"
										data-choose="disable">
								</dd>
							</dl>
						</td>
						<td class="td-title pct10"><b class="c-red mr5">*</b>还款方式</td>
						<td class="pct30">
							<dl class="form-item form-auto">
								<dd class="detail">
									<input class="zui-combobox" type="hidden" name="repaymentType"
										value="${receivableInfo.repaymentType }"
										data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0051"
										data-valuefield="fullcode" data-textfield="name"
										data-choose="disable">
								</dd>
							</dl>
							<dl class="form-item form-auto">
								<dd class="detail">
									<input class="zui-combobox" type="hidden"
										name="repaymentTypeTwo"
										value="${receivableInfo.repaymentTypeTwo }"
										data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0051"
										data-valuefield="fullcode" data-textfield="name"
										data-choose="disable">
								</dd>
							</dl>
						</td>
					</tr>
					<tr>
						<td class="td-title pct10"><b class="c-red mr5">*</b>利率性质</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<input class="zui-combobox " type="hidden" name="rateNature"
										value="${receivableInfo.rateNature }" data-width="150"
										data-data="[{'id':'0','text':'自动转为银行利率','isDefault':'true'},{'id':'1','text':'填写固定利率'},{'id':'2','text':'%/日'}]"
										data-callback="change" data-id="isAgriculture"
										data-valuefield="id" data-textfield="text"
										validate-type="Require" data-choose="disable">
								</dd>
								<dd class="detail" id="">
									<input class="zui-combobox " type="hidden" data-width="150"
										data-data="[{'id':'0','text':'放款即收第一期','isDefault':'true'}]"
										data-callback="change" data-id="isAgriculture"
										data-valuefield="id" data-textfield="text"
										validate-type="Require" data-choose="disable">
								</dd>
							</dl>
						</td>
						<td class="td-title pct10"><b class="c-red mr5">*</b>逾期利率(%)</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" class="zui-input nwidth2 "
										name="overdueDailyRate"
										value="${receivableInfo.overdueDailyRate }"
										validate-type="Require" disabled="disabled" />
									</label>
								</dd>
								<dd class="detail">
									<input class="zui-combobox " type="hidden"
										name="overdueDailyRateUnit"
										value="${receivableInfo.overdueDailyRateUnit }"
										data-width="94"
										data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0003"
										data-valuefield="fullcode" data-textfield="name"
										data-choose="disable">
								</dd>
							</dl>
						</td>
						<td class="td-title pct10"><b class="c-red mr5">*</b>预计放款日期</td>
						<td class="pct30">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input class="zui-date  strToDate"
										disabled="disabled" type="text" name="expectLoanDate"
										onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'changeExpectLoanDate',dateFmt:'yyyy-MM-dd'})"
										validate-type="Require" readonly
										value="${receivableInfo.expectLoanDate }" /> <input
										type="hidden" name="changeExpectLoanDate"
										value="${receivableInfo.expectLoanDate }" />
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
									<label> <input type="text"
										value="${receivableInfo.repaymentDate }"
										class="zui-input nwidth2 " name="repaymentDate"
										validate-type="Require" disabled="disabled" />
									</label>
								</dd>
								<dd class="detail">
									<input class="zui-combobox " type="hidden" data-width="94"
										data-data="[{'id':'0','text':'日','isDefault':'true'}]"
										data-callback="change" data-id="isAgriculture"
										data-valuefield="id" data-textfield="text"
										validate-type="Require" data-choose="disable">
								</dd>
							</dl>
						</td>
						<td class="td-title pct10"><b class="c-red mr5">*</b>综合利率(%)</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" name="syntheticalRate"
										value="${receivableInfo.syntheticalRate }"
										class="zui-input nwidth2 " validate-type="Require"
										disabled="disabled" />
									</label>
								</dd>
								<dd class="detail">
									<input class="zui-combobox " type="hidden"
										name="syntheticalRateUnit" data-width="94"
										value="${receivableInfo.syntheticalRateUnit}"
										data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0003"
										data-valuefield="fullcode" data-textfield="name"
										data-choose="disable">
								</dd>
							</dl>
						</td>
						<td class="td-title pct10">动态转换利率</td>
						<td class="pct30">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" class="zui-input nwidth2"
										disabled="disabled" />
									</label>
								</dd>
								<dd class="detail">
									<input class="zui-combobox" type="hidden" data-width="94"
										data-data="[{'id':'0','text':'%/年','isDefault':'true'},{'id':'1','text':'%/月'},{'id':'2','text':'%/日'}]"
										data-callback="change" data-id="isAgriculture"
										data-valuefield="id" data-textfield="text"
										data-choose="disable">
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
									<label> <input
										class="zui-date zui-validatebox strToDate" disabled="disabled"
										type="text"
										onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'changeExpectStartDate'})"
										value="${receivableInfo.expectStartDate }" /> <input
										type="hidden" name="changeExpectStartDate"
										value="${receivableInfo.expectStartDate }" />
									</label>
								</dd>
								</dd>
							</dl>
						</td>
						<td class="td-title pct10">逾期天数（天）</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" class="zui-input nwidth2"
										disabled="disabled" />
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
									<label> <input type="text" value="${loanAccountVo.bankAccount }" class="zui-input" disabled="disabled"/>
									</label>
								</dd>
							</dl>
						</td>
						<td class="td-title pct10"><b class="c-red mr5">*</b>银行代码</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${loanAccountVo.bankCode }" class="zui-input"  name="loanAccountVo.bankCode" disabled="disabled"/>
									</label>
								</dd>
							</dl>
						</td>
						<td class="td-title pct10"><b class="c-red mr5">*</b>账户名</td>
						<td class="pct30">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${loanAccountVo.cardholderName }" class="zui-input" disabled="disabled"/>
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
									<label> <input type="text" value="${loanAccountVo.bankNo }" class="zui-input" disabled="disabled"/>
									</label>
								</dd>
							</dl>
						</td>
					</tr>
				</table>

			</div>
		</div>
		
		<div class="page-box">
			<div class="page-title">还款(代扣)账户 </div>
			<input type="hidden" name="recAccountVo.id" value="${receivableAccountVo.id }"/>
			<div class="p5">
				<table class="table-detail">
					<tr>
						<td class="td-title pct10"><b class="c-red mr5">*</b>开户行</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${receivableAccountVo.bankAccount }" class="zui-input" disabled="disabled"/>
									</label>
								</dd>
							</dl>
						</td>
						<td class="td-title pct10"><b class="c-red mr5">*</b>银行代码</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${receivableAccountVo.bankCode }" class="zui-input" disabled="disabled"/>
									</label>
								</dd>
							</dl>
						</td>
						<td class="td-title pct10"><b class="c-red mr5">*</b>账户名</td>
						<td class="pct30">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${receivableAccountVo.cardholderName }" class="zui-input"  disabled="disabled"/>
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
									<label> <input type="text" value="${receivableAccountVo.bankNo }" class="zui-input" disabled="disabled"/>
									</label>
								</dd>
							</dl>
						</td>
					</tr>
				</table>

			</div>
		</div>
		
		<div class="page-box">
			<div class="page-title">贷款发放账户 </div>
			<input type="hidden" name="recAccountVo.id" value="${receivableAccountVo.id }"/>
			<div class="p5">
				<table class="table-detail">
					<tr>
						<td class="td-title pct10"><b class="c-red mr5">*</b>开户行</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${cooperatorBankVo.bankName }" class="zui-input" disabled="disabled"/>
									</label>
								</dd>
							</dl>
						</td>
						<td class="td-title pct10"><b class="c-red mr5">*</b>银行代码</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${cooperatorBankVo.bankCode }" class="zui-input" disabled="disabled"/>
									</label>
								</dd>
							</dl>
						</td>
						<td class="td-title pct10"><b class="c-red mr5">*</b>账户名</td>
						<td class="pct30">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${cooperatorBankVo.accountName }" class="zui-input" disabled="disabled"/>
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
									<label> <input type="text" value="${cooperatorBankVo.bankAccount }" class="zui-input" disabled="disabled" />
									</label>
								</dd>
							</dl>
						</td>
					</tr>
				</table>

			</div>
		</div>
		
	</form>

</div>
</c:if>
<script>
	seajs.use([ 'jquery', 'zd/iframe', 'zd/tools','zd/jquery.zds.button', 'zd/jquery.zds.seleter','zd/jquery.zds.address','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/make-first-py', 'zd/jquery.zds.form' ], function($,
		IFRAME, ZTOOlS,CALLBACK, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
		$.ZUI.initForms('#opinionDiv');
		
	});
	
</script>
