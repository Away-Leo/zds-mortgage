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
					<input type="hidden" id="capitallist_id" name="capitallist_id" value="${capitalistId }"/>
					<dl class="form-item">
						<dt class="title">信托计划名称：</dt>
						<dd class="detail">
							<label> <input class="zui-input"
								id="creditEntrustName"
								name="creditEntrustName">
							</label>
						</dd>
					</dl>
					<dl class="form-item">
						<dt class="title">状态：</dt>
						<dd class="detail">
							<input class="zui-combobox zui-validatebox" id="state" name="state" type="hidden"
                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=bust"
                              data-valuefield="fullcode" data-textfield="name" validate-type="Require">
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
				<table class="table-index scroll  " style="white-space: nowrap;"  >
					<tr><th rowspan="4">信托计划名称</th><th colspan="29">信托专户的资金情况</th><th colspan="3" rowspan="2">额度</th><th colspan="4" rowspan="2">备付资金情况</th><th colspan="2" rowspan="2">贷款回收</th><th rowspan="2" colspan="5">信托转让情况</th><th rowspan="4">创建日期</th><th rowspan="4" style="width:60px;">操作</th></tr>
					<tr><th colspan="14">资金流入</th><th colspan="16">资金流出</th></tr>
					<tr><th rowspan="2">优先本金</th><th colspan="2">劣后本金</th><th colspan="2">客户还款</th><th colspan="2">代偿</th><th colspan="2">回购</th><th rowspan="2">其他利息</th><th rowspan="2">暂待收款</th><th rowspan="2">信托保障基金</th><th rowspan="2">流入小计</th><th rowspan="2">退客户款</th><th colspan="2">优先资金</th><th colspan="2">劣后资金</th><th rowspan="2">信托保障基金(1%)</th><th rowspan="2">信托费用</th><th rowspan="2">银行托管费(0.05%)</th><th rowspan="2">信托服务费(0.8%)</th><th rowspan="2">展期服务费(0.15%)</th><th rowspan="2">印花税(0.05%)</th><th rowspan="2">深泛联管理费(8%)</th><th rowspan="2">代偿退回</th><th rowspan="2">回购退回</th><th rowspan="2">累计贷款投放</th><th rowspan="2">流出小计</th><th rowspan="2">截留额度</th><th rowspan="2">剩余可分配</th><th rowspan="2">已分配待放款</th><th rowspan="2">未分配备付金</th><th rowspan="2">账面余额</th><th rowspan="2">累计收回本金</th><th rowspan="2">累计收回利息</th><th rowspan="2">累计收回罚息</th><th rowspan="2">累计收回违约金</th><th rowspan="2">资金状态</th><th rowspan="2">信托本金</th><th rowspan="2">转让金额</th><th rowspan="2">转让日期</th><th rowspan="2">到期日期</th></tr>
					<tr><th>货币</th><th>债权转入</th><th>本金</th><th>利息</th><th>本金</th><th>利息</th><th>本金</th><th>利息</th><th>优先本金</th><th>优先利息</th><th>劣后本金</th><th>劣后收益</th></tr>
					<tbody id="appendHtml"></tbody>
					<tr><td colspan="46">
						<div class="page datagrid-pager" style="float:right;"> <span id="totalSize">共有0条记录</span><a href="javascript:void(0);" class="page-on" title="1">1</a><span style="margin-left: 10px;margin-right: 0;">到第</span><input id="currentPage" name="currentPage" type="text" class="zd-input page-input" value="1"><span>页</span> <button type="button" id="searchBtn" class="btn-blue" style="padding: 2px 5px 3px 5px;">跳转</button></div>
					</td></tr>
				</table>
				
			</div>
		</div>
	</div>
	<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
			
			// 查询按钮点击
			$('#btn-search').click(function(){
				doSearch();
			});
			
			// 重置按钮点击
			$('#btn-reset').click(function(){
				
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
				
				$('#creditTempNm').val(creditEntrustName);

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
                    url: '<z:ukey key="com.zdsoft.finance.capital.getCreditEntrusts" context="admin"/>&jsoncallback=?',
                    data: data,
                    dataType: 'json',
                    success: function (data) {
                    	var rows = data.rows;
                    	var appendHtml = "";
                    	for(var i = 0;i<rows.length; i++){
                   			appendHtml += '<tr><td>'+rows[i].NAME+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount1 == null ? 0 : rows[i].amount1)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount2 == null ? 0 : rows[i].amount2)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount3 == null ? 0 : rows[i].amount3)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount4 == null ? 0 : rows[i].amount4)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount5 == null ? 0 : rows[i].amount5)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount6 == null ? 0 : rows[i].amount6)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount7 == null ? 0 : rows[i].amount7)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount8 == null ? 0 : rows[i].amount8)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount9 == null ? 0 : rows[i].amount9)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount10 == null ? 0 : rows[i].amount10)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount11 == null ? 0 : rows[i].amount11)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount12 == null ? 0 : rows[i].amount12)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount13 == null ? 0 : rows[i].amount13)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount14 == null ? 0 : rows[i].amount14)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount15 == null ? 0 : rows[i].amount15)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount16 == null ? 0 : rows[i].amount16)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount17 == null ? 0 : rows[i].amount17)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount18 == null ? 0 : rows[i].amount18)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount19 == null ? 0 : rows[i].amount19)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount20 == null ? 0 : rows[i].amount20)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount21 == null ? 0 : rows[i].amount21)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount22 == null ? 0 : rows[i].amount22)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount23 == null ? 0 : rows[i].amount23)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount24 == null ? 0 : rows[i].amount24)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount25 == null ? 0 : rows[i].amount25)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount26 == null ? 0 : rows[i].amount26)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount27 == null ? 0 : rows[i].amount27)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount28 == null ? 0 : rows[i].amount28)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount29 == null ? 0 : rows[i].amount29)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount30 == null ? 0 : rows[i].amount30)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount31 == null ? 0 : rows[i].amount31)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount32 == null ? 0 : rows[i].amount32)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount33 == null ? 0 : rows[i].amount33)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount34 == null ? 0 : rows[i].amount34)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount35 == null ? 0 : rows[i].amount35)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount36 == null ? 0 : rows[i].amount36)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount37 == null ? 0 : rows[i].amount37)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount38 == null ? 0 : rows[i].amount38)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount39 == null ? 0 : rows[i].amount39)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount40 == null ? 0 : rows[i].amount40)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount41 == null ? 0 : rows[i].amount41)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount42 == null ? 0 : rows[i].amount42)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount43 == null ? 0 : rows[i].amount43)+'</td>';
                   			appendHtml += '<td>'+(rows[i].amount44 == null ? 0 : rows[i].amount44)+'</td>';
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