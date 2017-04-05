<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%> 
<!-- 跟进催收单 -->
		<div class="page-title">最近跟催概况</div>  
		<div class="page-box p10">
		<input type="hidden" id="monitorId" value="${monitorInfoVo.id }"/>
		<input type="hidden" id="monitorId2" value="${monitorVo.id }"/>
		<input type="hidden" id="afterLoan" value="${afterLoanInfoVo.id }"/>
		<input type="hidden" id="afterLoan2" value="${afterLoanVo.id }"/>
		<input type="hidden" id="justice" value="${justiceInfoVo.id }"/>
		<input type="hidden" id="justice2" value="${justiceVo.id }"/>
		<input type="hidden" id="mechanism" value="${mechanismInfoVo.id }"/>
		<input type="hidden" id="mechanism2" value="${mechanismVo.id }"/>
		
		<table class="scroll table-index">
			<thead class="datagrid-header">
				<tr>
					<th colspan="2" style="text-align: center; display: table-cell;">跟踪部门</th>
					<th style="text-align: center; display: table-cell;">最后一次跟催时间</th>
					<th style="text-align: center; display: table-cell;">最后一次跟催内容</th> 
					<th style="text-align: center; display: table-cell;">最后一次督办时间</th>
					<th style="text-align: center; display: table-cell;">最后一次督办内容</th>
					<th style="text-align: center; display: table-cell;">操作</th>
				</tr>
			</thead>
			<tbody class="datagrid-body">
				<tr >
					<td rowspan="3" style="text-align: center; display: table-cell;">集团</td>
					<td style="text-align: center; display: table-cell;">监控</td>
					<td style="text-align: center; display: table-cell;" class="js_date">${monitorInfoVo.createTime }</td>
					<td style="text-align: center; display: table-cell;">${monitorInfoVo.remark }</td>
					<td style="text-align: center; display: table-cell;" class="js_date">${monitorVo.createTime }</td>
					<td style="text-align: center; display: table-cell;">${monitorVo.remark }</td>
					<td style="text-align: center; display: table-cell;" ><a href="javaScript:void(0)" id="monitorBtn" class="btn-blue details">详情</a></td>
				</tr>
				<tr >
					<td style="text-align: center; display: table-cell;">贷后</td>
					<td style="text-align: center; display: table-cell;" class="js_date">${afterLoanInfoVo.createTime }</td>
					<td style="text-align: center; display: table-cell;">${afterLoanInfoVo.remark }</td>
					<td style="text-align: center; display: table-cell;" class="js_date">${afterLoanVo.createTime }</td>
					<td style="text-align: center; display: table-cell;">${afterLoanVo.remark }</td>
					<td style="text-align: center; display: table-cell;"><a href="javaScript:void(0)" id="afterLoanBtn" class="btn-blue details">详情</a></td>
				</tr>
				<tr >
					<td style="text-align: center; display: table-cell;" >法务</td>
					<td style="text-align: center; display: table-cell;" class="js_date">${justiceInfoVo.createTime }</td>
					<td style="text-align: center; display: table-cell;">${justiceInfoVo.remark }</td>
					<td style="text-align: center; display: table-cell;" class="js_date">${justiceVo.createTime }</td>
					<td style="text-align: center; display: table-cell;">${justiceVo.remark }</td>
					<td style="text-align: center; display: table-cell;"><a href="javaScript:void(0)" id="justiceBtn" class="btn-blue details">详情</a></td>
				</tr>
				<tr >
					<td style="text-align: center; display: table-cell;" colspan="2">机构</td>
					<td style="text-align: center; display: table-cell;" class="js_date">${mechanismInfoVo.createTime }</td>
					<td style="text-align: center; display: table-cell;">${mechanismInfoVo.remark }</td>
					<td style="text-align: center; display: table-cell;" class="js_date">${mechanismVo.createTime }</td>   
					<td style="text-align: center; display: table-cell;">${mechanismVo.remark }</td>
					<td style="text-align: center; display: table-cell;"><a href="javaScript:void(0)" id="mechanismBtn" class="btn-blue details">详情</a></td>
				</tr>
			</tbody>
		</table>
	</div>
    
<script type="text/javascript">  
	
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], 
		function($, CALLBACK) {   
		$.ZUI.init();
		
		// 监控部门详情查看
		$('#monitorBtn').click(function(){
			var monitorId = $('#monitorId').val();
			var monitorId2 = $('#monitorId2').val();
			
			var monitorUrl = '<z:ukey key="com.zdsoft.finance.afterloan.base.initFollowList" context="admin"/>&jsoncallback=?&followUpId='+monitorId+'&afterSuperviseId='+monitorId2;
	            ZDS_MESSAGE_CLIENT.openMenuLink('monitorId','监控部门跟催信息',monitorUrl + "&openMethod=tabs");
		});
		
		// 贷后部门详情查看   
		$('#afterLoanBtn').click(function(){
			var afterLoanId = $('#afterLoan').val();
			var afterLoanId2 = $('#afterLoan2').val();
			
			var afterLoanUrl = '<z:ukey key="com.zdsoft.finance.afterloan.base.initFollowList" context="admin"/>&jsoncallback=?&followUpId='+afterLoanId+'&afterSuperviseId='+afterLoanId2;
	            ZDS_MESSAGE_CLIENT.openMenuLink('afterLoanId','贷后部门跟催信息',afterLoanUrl + "&openMethod=tabs");
		});
		
		// 法务部门详情查看
		$('#justiceBtn').click(function(){
			var justiceId = $('#justice').val();
			var justiceId2 = $('#justice2').val();
			
			var justiceUrl = '<z:ukey key="com.zdsoft.finance.afterloan.base.initFollowList" context="admin"/>&jsoncallback=?&followUpId='+justiceId+'&afterSuperviseId='+justiceId2;
	            ZDS_MESSAGE_CLIENT.openMenuLink('justiceId','法务部门跟催信息',justiceUrl + "&openMethod=tabs");
		});
		
		// 机构详情查看
		$('#mechanismBtn').click(function(){
			var mechanismId = $('#mechanism').val();
			var mechanismId2 = $('#mechanism2').val();
			
			var mechanismUrl = '<z:ukey key="com.zdsoft.finance.afterloan.base.initFollowList" context="admin"/>&jsoncallback=?&followUpId='+mechanismId+'&afterSuperviseId='+mechanismId2;
	            ZDS_MESSAGE_CLIENT.openMenuLink('mechanismId','机构跟催信息',mechanismUrl + "&openMethod=tabs");
		});
	});
</script>
