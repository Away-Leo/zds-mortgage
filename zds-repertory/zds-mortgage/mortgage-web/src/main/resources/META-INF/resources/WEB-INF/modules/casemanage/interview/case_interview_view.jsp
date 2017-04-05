<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<div class ="frm-content">
	<input type="hidden" id="id" name="id" value="${caseApplyId }">
	<div class="page-box">
        <h1 class="page-title">面签</h1>
		<div class="p5">
               <div id="tb_housePropertyInfo" class="zui-datagrid table-scroll" zdata-options='{"url":"<z:ukey key="com.cnfh.rms.casemanage.interview.findHousePropertyInfo" context="admin"/>&caseApplyId=${caseApplyId }","singleSelect":true,"pagination":false, "idField":"id","tableCls":"table-index","toolbar":"#ztoolbar"}'>
	                <table>
	                	<thead>
	                		<tr>
		                        <th data-options="field:COMMUNITYNAME" >小区名称</th>
		            			<th data-options="field:MAILINGADDRESS" >押品地址</th>
	            				<th data-options="field:SYNTHESIZEPRICE" >综合评估价（元）</th>
	            				<th data-options="field:HOUSENO" >房产证编号</th>
	            				<th data-options="field:MORTGAGEENAME" >抵押权人名称</th>
	            				<th data-options="field:MORTGAGEDATE" formatter="dateFormatterShow" >预计入押时间</th>
	            			</tr>
	            		</thead>
	               	</table>
           		</div>
		</div>
	</div>
	<form id="interview_form" class="zui-form " method="post" enctype="multipart/form-data">
		<div class="page-box">
			<div class="page-title"></div>
   			 <div class="p5">
				<table class="table-detail">
					<tr>
						<td class="td-title pct10" >预计强制执行公证办理时间</td>
						<td class="pct20">
								<span id="compulsoryNotaryDate"></span>
						</td>
						<td class="td-title pct10">是否仲裁</td>
						<td class="pct20">
								${interviewVo.isArbitrate==true?"是":"否"}
			             </td>
						<td class="td-title pct10" ></td>
				        <td class="pct30"></td>
					</tr>
					<tr >
						<td colspan="6">收款账户</td>
					</tr>
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
					<tr >
						<td colspan="6">还款(代扣)账户</td>
					</tr>
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
			<div class="page-title">第三方还款授权</div>
    		<div class="p5">
				<table class="table-detail">
					<tr>
						<td class="td-title pct10">第三方还款授权</td>
						<td class="pct20">
							${interviewVo.isThirdPartyRepayAccredit==true?'是':'否'}
						</td>
						<td class="td-title pct10 aa" >第三方还款授权人</td>
						<!-- 选人下拉框 -->  
						<td class="pct20 aa">
							${interviewVo.thirdPartyRepayAccreditName}
						<td class="td-title pct10 bb" ></td>
				        <td class="pct30 bb"></td>
					</tr>    
				</table>
			</div>
		</div>
	</form>
</div>
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/jquery.zds.form'], 
			function($, CALLBACK,ZTOOL, Loading) {
			
			CALLBACK.dateFormatterShow = function(index,value){
				return window.formatDate(index,value);
			}	
			$("#compulsoryNotaryDate").text(ZTOOL.strToDate(${interviewVo.compulsoryNotaryDate }+""));
			$.ZUI.init();			
		});	
</script>
