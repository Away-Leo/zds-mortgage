<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ include file="../../common/common_upload.jsp" %>
<!-- 征信信息 -->
	<div class="p10">
		 <table class="table-detail">
		 	<tr>
		 		<td colspan="6" class="td-title">
		 			${customerName }征信信息概览
		 			<span style="text-align:left;">
		 				<button type="button" class="btn-search-blue" id="btn-selectCreditInfo">查询征信报告</button>
		 			</span>
		 		</td>
		 	</tr>
		 	<!-- 贷款start -->
		 	<tr>
		 		<td colspan="6" class="td-title">贷款</td>
		 	</tr>
		 	<tr>
		 		<td class="td-title pct15">贷款总额</td>
		 		<td class="td-title pct15">贷款总笔数</td>
		 		<td class="td-title pct15">已结清贷款笔数</td>
		 		<td class="td-title pct15">已结清贷款总额</td>
		 		<td class="td-title pct15">未结清笔数</td>
		 		<td class="td-title pct15">未结清贷款总额</td>
		 	</tr>
		 	<tr>
		 		<td class="pct15">xxx</td>
		 		<td class="pct15">xxx</td>
		 		<td class="pct15">xxx</td>
		 		<td class="pct15">xxx</td>
		 		<td class="pct15">xxx</td>
		 		<td class="pct15">xxx</td>
		 	</tr>
		 	<!-- 贷款end -->
		 	<!-- 信用卡start -->
		 	<tr>
		 		<td colspan="6" class="td-title">信用卡</td>
		 	</tr>
		 	<tr>
		 		<td class="td-title pct15">信用卡发卡总额</td>
		 		<td class="td-title pct15">发放张数</td>
		 		<td class="td-title pct15">已使用额度</td>
		 		<td class="td-title pct15">信用卡使用率</td>
		 		<td class="td-title pct15"></td>
		 		<td class="td-title pct15"></td>
		 	</tr>
		 	<tr>
		 		<td class="pct15">xxx</td>
		 		<td class="pct15">xxx</td>
		 		<td class="pct15">xxx</td>
		 		<td class="pct15">xxx</td>
		 		<td class="pct15">xxx</td>
		 		<td class="pct15">xxx</td>
		 	</tr>
		 	<!-- 信用卡end -->
		 	<tr>
		 		<td colspan="6" class="td-title">逾期征信信息概览（最近12个月）</td>
		 	</tr>
		 	<!-- 贷款逾期start -->
		 	<tr>
		 		<td colspan="6" class="td-title">贷款逾期</td>
		 	</tr>
		 	<tr>
		 		<td class="td-title pct15">逾期总笔数</td>
		 		<td class="td-title pct15">未逾期笔数</td>
		 		<td class="td-title pct15">超标笔数</td>
		 		<td class="td-title pct15">单笔最高逾期期数</td>
		 		<td class="td-title pct15">单笔最高逾期金额</td>
		 		<td class="td-title pct15"></td>
		 	</tr>
		 	<tr>
		 		<td class="pct15">xxx</td>
		 		<td class="pct15">xxx</td>
		 		<td class="pct15">xxx</td>
		 		<td class="pct15">xxx</td>
		 		<td class="pct15">xxx</td>
		 		<td class="pct15">xxx</td>
		 	</tr>
		 	<!-- 贷款逾期end -->
		 	<!-- 贷记卡逾期start -->
		 	<tr>
		 		<td colspan="6" class="td-title">贷记卡逾期</td>
		 	</tr>
		 	<tr>
		 		<td class="td-title pct15">逾期总笔数</td>
		 		<td class="td-title pct15">未逾期笔数</td>
		 		<td class="td-title pct15">超标笔数</td>
		 		<td class="td-title pct15">单笔最高逾期期数</td>
		 		<td class="td-title pct15">单笔最高逾期金额</td>
		 		<td class="td-title pct15"></td>
		 	</tr>
		 	<tr>
		 		<td class="pct15">xxx</td>
		 		<td class="pct15">xxx</td>
		 		<td class="pct15">xxx</td>
		 		<td class="pct15">xxx</td>
		 		<td class="pct15">xxx</td>
		 		<td class="pct15">xxx</td>
		 	</tr>
		 	<!-- 贷记卡逾期end -->
		 	<!-- 准贷记卡逾期start -->
		 	<tr>
		 		<td colspan="6" class="td-title">准贷记卡逾期</td>
		 	</tr>
		 	<tr>
		 		<td class="td-title pct15">逾期总笔数</td>
		 		<td class="td-title pct15">未逾期笔数</td>
		 		<td class="td-title pct15">超标笔数</td>
		 		<td class="td-title pct15">单笔最高逾期期数</td>
		 		<td class="td-title pct15">单笔最高逾期金额</td>
		 		<td class="td-title pct15"></td>
		 	</tr>
		 	<tr>
		 		<td class="pct15">xxx</td>
		 		<td class="pct15">xxx</td>
		 		<td class="pct15">xxx</td>
		 		<td class="pct15">xxx</td>
		 		<td class="pct15">xxx</td>
		 		<td class="pct15">xxx</td>
		 	</tr>
		 	<!-- 准贷记卡逾期end -->
		 </table>
	</div>
	<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], 
			function($, CALLBACK,ZTOOLS) {	
			
			//查询征信报告 
			$("btn-selectCreditInfo").click(function() {
				
			}
			
		});
	</script>