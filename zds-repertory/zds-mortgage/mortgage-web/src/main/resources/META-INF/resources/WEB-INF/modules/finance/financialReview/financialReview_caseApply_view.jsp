<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<div class ="frm-content">
<div class="page-box">
		<div class="page-title">基础信息</div>
 		<div class="p5">
		<table class="table-detail">
			<tr>
				<td class="td-title pct10" >案件号</td>
				<td class="pct20">
					${caseApplyVo.caseApplyCode}</td>
				<td class="td-title pct10" >主借人</td>
				<td class="pct20">
					${customerName}
				</td>
				<td class="td-title pct10" >贷款金额</td>
				<td class="pct30">
					${caseApplyVo.applyAmount} 元
				</td>
			</tr>
			<tr>
				<td class="td-title pct10" >贷款期限</td>
				<td class="pct20">
					${caseApplyVo.applyTerm}${caseApplyVo.applyTermUnitName}
				</td>
				<td class="td-title pct10" >贷款利率</td>
				<td class="pct20">${receivableInfoVo.loanMonthRate}${receivableInfoVo.loanMonthRateUnitName}</td>
				<td class="td-title pct10" >还款方式</td>
				<td class="pct30">${receivableInfoVo.repaymentTypeName} &nbsp ${receivableInfoVo.repaymentTypeTwoName}</td>
			</tr>
			<tr>
				<td class="td-title pct10" >利率性质</td>
				<td class="pct20">
					${receivableInfoVo.rateNatureName} &nbsp ${receivableInfoVo.rateNatureTwoName} 
				</td>
				<td class="td-title pct10" >逾期利率</td>
				<td class="pct20">
					${receivableInfoVo.overdueDailyRate}${receivableInfoVo.overdueDailyRateUnitName} 
				</td>
				<td class="td-title pct10" >预计放款日期</td>
				<td class="pct30">
					<span id="expectLoanDate"></span>
				</td>
			</tr>
			<tr>
				<td class="td-title pct10" >还款日</td>
				<td class="pct20">
					${receivableInfoVo.repaymentDate}
				</td>
				<td class="td-title pct10" >综合利率为</td>
				<td class="pct20">${receivableInfoVo.syntheticalRate}${receivableInfoVo.syntheticalRateUnitName}</td>
				<td class="td-title pct10" >动态转换利率</td>
				<td class="pct30">${receivableInfoVo.internalRateReturn}${receivableInfoVo.internalRateReturnUnitName}</td>
			</tr>
		</table>
	</div>
</div>
<div class="page-box">
		<div class="page-title">收款账户</div>
 		<div class="p5">
		<table class="table-detail">
			<tr>
				<td class="td-title pct10" >开户行</td>
				<td class="pct20">
					${bankAccountVo.loanAccountVo.bankAccount}</td>
				<td class="td-title pct10" >银行代码</td>
				<td class="pct20">
					${bankAccountVo.loanAccountVo.bankCode}
				</td>
				<td class="td-title pct10" >账户名</td>
				<td class="pct30">
					${bankAccountVo.loanAccountVo.cardholderName}
				</td>
			</tr>
			<tr>
				<td class="td-title pct10" >账号</td>
				<td class="pct20">
					${bankAccountVo.loanAccountVo.bankNo}
				</td>
				<td class="td-title pct10" ></td>
				<td class="pct20"></td>
				<td class="td-title pct10" ></td>
				<td class="pct30"></td>
			</tr>
		</table>
	</div>
</div>
<div class="page-box">
		<div class="page-title">还款(代扣)账户</div>
 		<div class="p5">
		<table class="table-detail">
			<tr>
				<td class="td-title pct10" >开户行</td>
				<td class="pct20">
					${bankAccountVo.recAccountVo.bankAccount}</td>
				<td class="td-title pct10" >银行代码</td>
				<td class="pct20">
					${bankAccountVo.recAccountVo.bankCode}</td>
				<td class="td-title pct10" >账户名</td>
				<td class="pct30">
					${bankAccountVo.recAccountVo.cardholderName}
				</td>
			</tr>
			<tr>
				<td class="td-title pct10" >账号</td>
				<td class="pct20">
					${bankAccountVo.recAccountVo.bankNo}
				</td>
				<td class="td-title pct10" ></td>
				<td class="pct20"></td>
				<td class="td-title pct10" ></td>
				<td class="pct30"></td>
			</tr>
		</table>
	</div>
</div>
<div class="page-box">
	<div class="page-title">贷款发放账户</div>
 	<div class="p5">
		<table class="table-detail">
			<tr>
				<td class="td-title pct10" >开户行</td>
				<td class="pct20">
					${creditEntrustVo.spareAccountBank}</td>
				<td class="td-title pct10" >银行代码</td>
				<td class="pct20">
					${bankCode}
				</td>
				<td class="td-title pct10" >账户名</td>
				<td class="pct30">
					${creditEntrustVo.spareAccountName}
				</td>
			</tr>
			<tr>
				<td class="td-title pct10" >账号</td>
				<td class="pct20">
					${creditEntrustVo.spareAccountNo}
				</td>
				<td class="td-title pct10" ></td>
				<td class="pct20"></td>
				<td class="td-title pct10" ></td>
				<td class="pct30"></td>
			</tr>
		</table>
	</div>
</div>
</div>
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.form'], 
			function($, CALLBACK,ZTOOL) {
		
			$("#expectLoanDate").text(ZTOOL.strToDate(${receivableInfoVo.expectLoanDate}+""));
			$("#expectStartDate").text(ZTOOL.strToDate(${receivableInfoVo.expectStartDate}+""));
			$.ZUI.init();			
		});	
</script>