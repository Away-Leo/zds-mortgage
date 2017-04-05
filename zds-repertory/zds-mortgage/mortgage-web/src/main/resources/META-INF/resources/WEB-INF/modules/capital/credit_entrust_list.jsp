<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<%@ include file='../common/common_js.jsp'%>
<title>信托计划管理</title>
</head>
<body>
	<div class="frm-content">
		<!-- 查询区域 -->
		<div class="page-box">
			<div class="page-title">查询参数</div>
			<input type="hidden" id="capitalistId" value="${capitalistId }"/>
			<div class="p10">
				<form id="search_from" class="zui-form form-search" method="post"
					enctype="multipart/form-data">
					<input type="hidden" id="capitallist_id" name="capitallist.id|E|S" value="${capitalistId }"/>
					<dl class="form-item">
						<dt class="title">信托计划名称：</dt>
						<dd class="detail">
							<label> <input class="zui-input"
								id="creditEntrustName"
								name="creditEntrustName|LK|S">
							</label>
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
			<div class="mt20 ml10" id="btnDiv" >
				<button class="mr10 mb15 btn-blue" type="button"  id="openBtn">展开</button>
				<button class="mr10 mb15 btn-blue" type="button" style="display: none" id="closeBtn">收起</button>
				<button class="mr10 mb15 btn-blue" type="button" style="display: none" id="exportAll">导出</button>
				<button class="mr10 mb15 btn-blue" type="button" style="display: none" id="principalOutput">本金投入明细导出</button>
				<button class="mr10 mb15 btn-blue" type="button" style="display: none" id="nonPrincipalTraceOutput">非本金跟踪明细导出</button>
				<button class="mr10 mb15 btn-blue" type="button" style="display: none" id="costOfExchangeOutput">往来对象应付费导出</button>
				<button class="mr10 mb15 btn-blue" type="button" style="display: none" id="unionFundsOutput">银联资金备付资金跟踪明细导出</button>
				<button class="mr10 mb15 btn-blue" type="button" style="display: none" id="trustPlanTransferOutput">信托计划转让明细导出</button>
				<button class="mr10 mb15 btn-blue" type="button" style="display: none" id="debitDetailsOutput">借方资金跟踪明细导出</button>
			</div>
		</div>
		<form id="export_from" class=" form-search" method="post" action=""
					enctype="multipart/form-data">
					<input type="hidden" id="creditTempName" name = "creditTempName"/>
					<input type="hidden" id="capitalistTempId" name="capitalistTempId" />
					
					<input type="hidden" name="t08|capitallist_id|E|S" value="${capitalistId }" />
				    <input type="hidden" name="t01|creditEntrustName|LK|S" id="creditTempNm" />
		</form>
		<div class="page-box">
			<div class="mt20 ml10">
				<button class="mr10 mb15 btn-orange" type="button" id="addCreditEntrust">新增</button>
				<button class="mr10 mb15 btn-blue" type="button">刷新放款金额</button>
			</div>
			<div class="table-scroll" style="width:100%;">
				<table class="table-detail scroll  " style="white-space: nowrap;"  >
					<tr><th align="center" rowspan="4">信托计划名称</th><th align="center" align="center" colspan="29">信托专户的资金情况</th><th colspan="3" align="center" rowspan="2">额度</th><th align="center" colspan="2" rowspan="2">备付资金情况</th><th align="center" colspan="4" rowspan="2">贷款回收</th><th align="center" rowspan="2" colspan="5">信托转让情况</th><th align="center" rowspan="4">创建日期</th><th align="center" rowspan="4" style="width:60px;">操作</th></tr>
					<tr><th align="center" colspan="13">资金流入</th><th align="center" colspan="16">资金流出</th></tr>
					<tr><th align="center" rowspan="2">优先本金</th><th align="center" colspan="2">劣后本金</th><th align="center" colspan="2">客户还款</th><th align="center" colspan="2">代偿</th><th align="center" colspan="2">回购</th><th align="center" rowspan="2">其他利息</th><th align="center" rowspan="2">暂待收款</th><th align="center" rowspan="2">信托保障基金</th><th align="center" rowspan="2">流入小计</th><th align="center" rowspan="2">退客户款</th><th align="center" colspan="2">优先资金</th><th align="center" colspan="2">劣后资金</th><th align="center" rowspan="2">信托保障基金(1%)</th><th align="center" rowspan="2">信托费用</th><th align="center" rowspan="2">银行托管费(0.05%)</th><th align="center" rowspan="2">信托服务费(0.8%)</th><th align="center" rowspan="2">展期服务费(0.15%)</th><th align="center" rowspan="2">印花税(0.05%)</th><th align="center" rowspan="2">深泛联管理费(8%)</th><th align="center" rowspan="2">代偿退回</th><th align="center"  rowspan="2">回购退回</th><th align="center" rowspan="2">累计贷款投放</th><th align="center" rowspan="2">流出小计</th><th align="center" rowspan="2">截留额度</th><th align="center" rowspan="2">剩余可分配</th><th align="center" rowspan="2">已分配待放款</th><th align="center" rowspan="2">未分配备付金</th><th align="center" rowspan="2">账面余额</th><th align="center" rowspan="2">累计收回本金</th><th align="center" rowspan="2">累计收回利息</th><th align="center" rowspan="2">累计收回罚息</th><th align="center" rowspan="2">累计收回违约金</th><th align="center" rowspan="2">资金状态</th><th align="center" rowspan="2">信托本金</th><th align="center" rowspan="2">转让金额</th><th align="center" rowspan="2">转让日期</th><th align="center" rowspan="2">到期日期</th></tr>
					<tr><th align="center">货币</th ><th align="center" align="center">债权转入</th><th align="center">本金</th><th align="center">利息</th><th align="center">本金</th><th align="center">利息</th><th align="center">本金</th><th align="center">利息</th><th align="center">优先本金</th><th align="center">优先利息</th><th align="center">劣后本金</th><th align="center">劣后收益</th></tr>
					<tbody id="appendHtml"></tbody>
					<tr><td colspan="46">
						<div class="page datagrid-pager" style="float:right;"> <span id="totalSize">共有0条记录</span><span style="margin-left: 10px;margin-right: 0;">到第</span><input id="currentPage" name="currentPage" type="text" class="zd-input page-input" value="1"><span>页</span> <button type="button" id="searchBtn" class="btn-blue" style="padding: 2px 5px 3px 5px;">跳转</button></div>
					</td></tr>
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
			
			// 查询按钮点击
			$('#btn-search').click(function(){
				doSearch();
			});
			
			// 重置按钮点击
			$('#btn-reset').click(function(){
				$('#creditEntrustName').val('');
				$('#state').ZCombobox('setValue','');
			});
			
			// 初始化页面
			$.ZUI.init();
			
			$('#exportAll').click(function(){
				var url = '<z:ukey key="com.zdsoft.finance.capital.exportAllData" context="admin"/>&jsoncallback=?';
				var rows = 10;
				var currentPage = $('#currentPage').val();
				
				var capitallist_id = $('#capitallist_id').val();
				var creditEntrustName = $('#creditEntrustName').val();
				var state = $('#state').ZCombobox('getValue');
				
				url += '&rows='+rows;
				url += '&page='+currentPage;
				
				$('#creditTempName').val(creditEntrustName);
				$('#capitalistTempId').val(capitallist_id);

				$('#export_from').attr('action',url);
				
				$('#export_from').submit();
			});
			
			$('#principalOutput').click(function(){
				var capitallist_id = $('#capitallist_id').val();
				var creditEntrustName = $('#creditEntrustName').val();
				$('#creditTempName').val(creditEntrustName);
				$('#capitalistTempId').val(capitallist_id);
				var url = '<z:ukey key="com.zdsoft.finance.capital.exportPrincipalData" context="admin"/>&jsoncallback=?';
				$('#export_from').attr('action',url);
				$('#export_from').submit();
			});
			
			$('#nonPrincipalTraceOutput').click(function(){
				
				var capitallist_id = $('#capitallist_id').val();
				var creditEntrustName = $('#creditEntrustName').val();
				$('#creditTempName').val(creditEntrustName);
				$('#capitalistTempId').val(capitallist_id);
				
				var url = '<z:ukey key="com.zdsoft.finance.capital.exportLoanCapitalData" context="admin"/>&jsoncallback=?';
				$('#export_from').attr('action',url);
				$('#export_from').submit();
			});
			
			$('#costOfExchangeOutput').click(function(){
				
				var capitallist_id = $('#capitallist_id').val();
				var creditEntrustName = $('#creditEntrustName').val();
				$('#creditTempName').val(creditEntrustName);
				$('#capitalistTempId').val(capitallist_id);
				
				var url = '<z:ukey key="com.zdsoft.finance.capital.exportCostTracking" context="admin"/>&jsoncallback=?';
				$('#export_from').attr('action',url);
				$('#export_from').submit();
			});
			
			$('#unionFundsOutput').click(function(){
				var capitallist_id = $('#capitallist_id').val();
				var creditEntrustName = $('#creditEntrustName').val();
				$('#creditTempName').val(creditEntrustName);
				$('#capitalistTempId').val(capitallist_id);
				
				var url = '<z:ukey key="com.zdsoft.finance.capital.exportSpareCapital" context="admin"/>&jsoncallback=?';
				$('#export_from').attr('action',url);
				$('#export_from').submit();
			});
			
			$('#trustPlanTransferOutput').click(function(){
				var capitallist_id = $('#capitallist_id').val();
				var creditEntrustName = $('#creditEntrustName').val();
				$('#creditTempName').val(creditEntrustName);
				$('#capitalistTempId').val(capitallist_id);
				
				var url = '<z:ukey key="com.zdsoft.finance.capital.exportCreditAttom" context="admin"/>&jsoncallback=?';
				$('#export_from').attr('action',url);
				$('#export_from').submit();
			});
			
			$('#debitDetailsOutput').click(function(){
				var capitallist_id = $('#capitallist_id').val();
				var creditEntrustName = $('#creditEntrustName').val();
				$('#creditTempName').val(creditEntrustName);
				$('#capitalistTempId').val(capitallist_id);
				
				var url = '<z:ukey key="com.zdsoft.finance.capital.exportCreditDebit" context="admin"/>&jsoncallback=?';
				$('#export_from').attr('action',url);
				$('#export_from').submit();
			});
			
			
			// 执行查询
			function doSearch(){
				var data = $('#search_from').serialize();
				var rows = 10;
				var currentPage = $('#currentPage').val();
				data += '&rows='+rows;
				data += '&page='+currentPage;
				$.ajax({
                    type: 'post',
                    url: '<z:ukey key="com.zdsoft.finance.capital.getCreditEntrustsByHql" context="admin"/>&jsoncallback=?',
                    data: data,
                    dataType: 'json',
                    success: function (data) {
                    	var rows = data.rows;
                    	var appendHtml = "";
                    	$('#totalSize').html("共有"+data.total+"条记录");
                    	for(var i = 0;i<rows.length; i++){
                   			appendHtml += '<tr><td>'+rows[i].creditEntrustName+'</td>';
                   			appendHtml += '<td>'+(rows[i].priorityPrincipal == null ? 0 : rows[i].priorityPrincipal)+'</td>';
                   			appendHtml += '<td>'+(rows[i].currency == null ? 0 : rows[i].currency)+'</td>';
                   			appendHtml += '<td>'+(rows[i].bondTransfer == null ? 0 : rows[i].bondTransfer)+'</td>';
                   			appendHtml += '<td>'+(rows[i].repaymentPrincipal == null ? 0 : rows[i].repaymentPrincipal)+'</td>';
                   			appendHtml += '<td>'+(rows[i].repaymentInterest == null ? 0 : rows[i].repaymentInterest)+'</td>';
                   			appendHtml += '<td>'+(rows[i].compensatoryPrincipal == null ? 0 : rows[i].compensatoryPrincipal)+'</td>';
                   			appendHtml += '<td>'+(rows[i].compensatoryInterest == null ? 0 : rows[i].compensatoryInterest)+'</td>';
                   			appendHtml += '<td>'+(rows[i].backPrincipal == null ? 0 : rows[i].backPrincipal)+'</td>';
                   			appendHtml += '<td>'+(rows[i].backInterest == null ? 0 : rows[i].backInterest)+'</td>';
                   			appendHtml += '<td>'+(rows[i].otherInterest == null ? 0 : rows[i].otherInterest)+'</td>';
                   			appendHtml += '<td>'+(rows[i].pendingPayment == null ? 0 : rows[i].pendingPayment)+'</td>';
                   			appendHtml += '<td>'+(rows[i].trustGuaranteeFund == null ? 0 : rows[i].trustGuaranteeFund)+'</td>';
                   			appendHtml += '<td>'+(rows[i].inflowSubtotal == null ? 0 : rows[i].inflowSubtotal)+'</td>';
                   			appendHtml += '<td>'+(rows[i].refund == null ? 0 : rows[i].refund)+'</td>';
                   			appendHtml += '<td>'+(rows[i].fundsPriorityPrincipal == null ? 0 : rows[i].fundsPriorityPrincipal)+'</td>';
                   			appendHtml += '<td>'+(rows[i].fundsPriorityInterest == null ? 0 : rows[i].fundsPriorityInterest)+'</td>';
                   			appendHtml += '<td>'+(rows[i].badPrincipal == null ? 0 : rows[i].badPrincipal)+'</td>';
                   			appendHtml += '<td>'+(rows[i].inferiorPostYield == null ? 0 : rows[i].inferiorPostYield)+'</td>';
                   			appendHtml += '<td>'+(rows[i].trustGuaranteeFundPercent == null ? 0 : rows[i].trustGuaranteeFundPercent)+'</td>';
                   			appendHtml += '<td>'+(rows[i].trustExpense == null ? 0 : rows[i].trustExpense)+'</td>';
                   			appendHtml += '<td>'+(rows[i].bankCustodianFee == null ? 0 : rows[i].bankCustodianFee)+'</td>';
                   			appendHtml += '<td>'+(rows[i].trustServiceFee == null ? 0 : rows[i].trustServiceFee)+'</td>';
                   			appendHtml += '<td>'+(rows[i].extensionServiceFee == null ? 0 : rows[i].extensionServiceFee)+'</td>';
                   			appendHtml += '<td>'+(rows[i].stampDuty == null ? 0 : rows[i].stampDuty)+'</td>';
                   			appendHtml += '<td>'+(rows[i].sflManagementFee == null ? 0 : rows[i].sflManagementFee)+'</td>';
                   			appendHtml += '<td>'+(rows[i].compensatory == null ? 0 : rows[i].compensatory)+'</td>';
                   			appendHtml += '<td>'+(rows[i].buyBack == null ? 0 : rows[i].buyBack)+'</td>';
                   			appendHtml += '<td>'+(rows[i].cumulativeLoan == null ? 0 : rows[i].cumulativeLoan)+'</td>';
                   			appendHtml += '<td>'+(rows[i].outflowSubtotal == null ? 0 : rows[i].outflowSubtotal)+'</td>';
                   			appendHtml += '<td>'+(rows[i].retain == null ? 0 : rows[i].retain)+'</td>';
                   			appendHtml += '<td>'+(rows[i].residualDistribution == null ? 0 : rows[i].residualDistribution)+'</td>';
                   			appendHtml += '<td>'+(rows[i].allocatedLoan == null ? 0 : rows[i].allocatedLoan)+'</td>';
                   			appendHtml += '<td>'+(rows[i].notEquippedPay == null ? 0 : rows[i].notEquippedPay)+'</td>';
                   			appendHtml += '<td>'+(rows[i].bookBalance == null ? 0 : rows[i].bookBalance)+'</td>';
                   			appendHtml += '<td>'+(rows[i].cumulativeRecoveryPrincipal == null ? 0 : rows[i].cumulativeRecoveryPrincipal)+'</td>';
                   			appendHtml += '<td>'+(rows[i].cumulativeRecoveryInterest == null ? 0 : rows[i].cumulativeRecoveryInterest)+'</td>';
                   			appendHtml += '<td>'+(rows[i].cumulativeRecoveryPenalty == null ? 0 : rows[i].cumulativeRecoveryPenalty)+'</td>';
                   			appendHtml += '<td>'+(rows[i].cumulativeRecoveryLiqDamages == null ? 0 : rows[i].cumulativeRecoveryLiqDamages)+'</td>';
                   			appendHtml += '<td>'+(rows[i].capitalStatusName)+'</td>';
                   			appendHtml += '<td>'+(rows[i].trustPrincipal == null ? 0 : rows[i].trustPrincipal)+'</td>';
                   			appendHtml += '<td>'+(rows[i].transferAmount == null ? 0 : rows[i].transferAmount)+'</td>';
                   			appendHtml += '<td>'+(rows[i].transferDate == 0 ? '' : rows[i].transferDate)+'</td>';
                   			appendHtml += '<td>'+(rows[i].maturityDate == 0 ? '' : rows[i].maturityDate)+'</td>';
                   			appendHtml += '<td>'+(rows[i].createDate.toString().substring(0,4)+'-'+rows[i].createDate.toString().substring(4,6)+'-'+rows[i].createDate.toString().substring(6,8))+'</td>';
                   			appendHtml += '<td onclick=changeValue("'+rows[i].id+'")><button class="btn-blue" style="white-space: nowrap;">处理</button></th></tr>';
                    		 
                    	}
                    	$('#appendHtml').html(appendHtml);
                    },
                    error: function () {
                    	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
                            $(".zd-message").ZWindow("close");
                        });
                    }
                }); 
			};
			
			// 跳转按钮
			$('#searchBtn').click(function(){
				doSearch();
			});
			
			
			// 按钮点击
			$('#addCreditEntrust').click(function(){
				 var capitalistId = $('#capitalistId').val();
				 var addCreditEntrustUrl = '<z:ukey key="com.zdsoft.finance.capital.initCreditEntrustAdd" context="admin"/>&jsoncallback=?&capitalistId='+capitalistId;
  	             ZDS_MESSAGE_CLIENT.openMenuLink('addCreditEntrustId','信托计划详情',addCreditEntrustUrl + "&openMethod=tabs");
			});
			
			// 回调函数
			window.changeValue = function(id){
				 var addCreditEntrustUrl = '<z:ukey key="com.zdsoft.finance.capital.initCreditEntrustAdd" context="admin"/>&jsoncallback=?&id='+id;
  	             ZDS_MESSAGE_CLIENT.openMenuLink('addCreditEntrustId','信托计划详情',addCreditEntrustUrl + "&openMethod=tabs");
			};
			
			
			$(function(){
				// 初始化执行查询
				doSearch();
			});
			
			// 父页面刷新自己
			ZDS_MESSAGE_CLIENT.refreshThis=function(){
				doSearch();
	        };
			
			
			$('#openBtn').click(function(){
				$('#exportAll').fadeIn('slow');
				$('#principalOutput').fadeIn('slow');
				$('#nonPrincipalTraceOutput').fadeIn('slow');
				$('#costOfExchangeOutput').fadeIn('slow');
				$('#unionFundsOutput').fadeIn('slow');
				$('#trustPlanTransferOutput').fadeIn('slow');
				$('#debitDetailsOutput').fadeIn('slow');
				$('#openBtn').hide();
				$('#closeBtn').show();
			});
			$('#closeBtn').click(function(){
				$('#exportAll').fadeOut('slow');
				$('#principalOutput').fadeOut('slow');
				$('#nonPrincipalTraceOutput').fadeOut('slow');
				$('#costOfExchangeOutput').fadeOut('slow');
				$('#unionFundsOutput').fadeOut('slow');
				$('#trustPlanTransferOutput').fadeOut('slow');
				$('#debitDetailsOutput').fadeOut('slow');
				$('#openBtn').show();
				$('#closeBtn').hide();
			});
		});
	</script>
</body>
</html>