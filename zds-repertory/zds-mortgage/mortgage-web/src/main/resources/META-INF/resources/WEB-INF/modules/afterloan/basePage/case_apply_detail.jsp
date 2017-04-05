<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../common/common_js.jsp" %>
<title>案件详情</title>
</head>
<body>
<div class="frm-content">
	<div class="page-box">
		<div class="page-title">案件信息</div>
		<div class="p10">
			<table class="table-detail">
				<tr>
					<th class="pct15">案件号：</th>
					<td>${caseApplyCode }</td>
					<th class="pct15">机构：</th>
					<td>${mechanismName }</td>
					<th class="pct15">子产品：</th>
					<td>${productSubtypeName }</td>
				</tr>
				<tr>
					<th >放款金额(元)：</th>
					<td>${loanApplyAmount }</td>
					<th >合同开始日期：</th>
					<td id="startDate">${contractStartDate }</td>
					<th >合同结束日期：</th>
					<td id="endDate">${contractEndDate }</td>
				</tr>
				<tr>
					<th >逾期金额(元)：</th>
					<td>${overdueAmount }</td>
					<th >逾期日期：</th>
					<td>${overdueDate }</td>
					<th >逾期天数：</th>
					<td>${overdueDay }</td>
				</tr>
			</table>		
		</div>
		<div>
			<input type="hidden" id="caseApplyId" value="${caseApplyId }"/>
		</div>
	</div>
	
	<div class="page-title">参与人信息</div>
	<div class="page-box p10">
		<div id="participantInformation"></div>
	</div>
	
	<div class="page-title">押品信息</div>
	<div class="page-box p10">
		<div id="chargeInformation"></div>
	</div>
	
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
</div>
	<script type="text/javascript">
		seajs.use(['jquery','zd/tools','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.table'], 
		function($, ZTOOL,CALLBACK) {
			
			// 参与人信息Table
			$('#participantInformation').ZTable({
			       url : "<z:ukey key='com.cnfh.rms.postLoanPersonal.listGuarantor' context='admin'/>&caseApplyId=${caseApplyId}",
			       isRowNum : false,
			       currentPage : 1, 
			       rows:10,
			       singleSelect: true,  
			       pagination:false,
			       idField: "id",
			       tableCls : 'table-index',//table的样式    
			       columns:[[
						{field : 'id',title : 'id',align : 'center',hidden:true},
			    	  	{field : 'customerName',title : '姓名',align : 'center'},
						{field : 'genderName',title : '性别',align : 'center'},
						{field : 'maritalStatusName',title : '婚姻情况', align : 'center'},
						{field : 'birthdayDateStr',title : '出生年月', align : 'center',formatter : function(r,v){
							if(!r.birthdayDateStr){
								return '';
							}else{
								return r.birthdayDateStr;
							}
						}},
						{field : 'credentialTypeName',title : '证件类型', align : 'center'},
						{field : 'credentialNo',title : '证件号码', align : 'center'},
						{field : 'defaultPhoneNumber',title : '移动电话', align : 'center',formatter:function(r,v){
							if(!r.defaultPhoneNumber){
								return '';
							}else{
								return r.defaultPhoneNumber;
							}
						}},
						{field : 'householdRegistrationStr',title : '户籍地址', align : 'center',formatter:function(r,v){
							if(!r.householdRegistrationStr){
								return '';
							}else{
								return r.householdRegistrationStr;
							}
						}},
						{field : 'homeAddressStr',title : '家庭地址', align : 'center',formatter:function(r,v){
							if(!r.homeAddressStr){
								return '';
							}else{
								return r.homeAddressStr;
							}
						}}
				] ],
				onDelete:function(index, rowData) {
					//  添加判断
					return true;
				},
				onLoadSuccess:function() {
				}
			});
			
			// 押品信息Table
			$('#chargeInformation').ZTable({   
			       url : "<z:ukey key='com.cnfh.rms.marketing.houseProperty.getHousePropertyList' context='admin'/>&jsoncallback=?&caseApplyId=${caseApplyId}",
			       isRowNum : false,
			       currentPage : 1, 
			       rows:20,
			       singleSelect: true,  
			       pagination:false,
			       idField: "id", 
			       tableCls : 'table-index',//table的样式  
			       columns:[[
			    	  	{field : 'id',title : 'id',align : 'center',hidden:true},
			    	  	{field : 'communityName',title : '小区名称',align : 'center'},
						{field : 'placeFloor',title : '所在楼层（层）',align : 'center'},
						{field : 'sumFloor',title : '总层楼（层）', align : 'center'},
						{field : 'floorAge',title : '楼龄（年）', align : 'center'},
						{field : 'area',title : '面积（㎡）', align : 'center'},
						{field : 'estatePropertiesName',title : '房产性质', align : 'center'},
						{field : 'estateOwnershipName',title : '房产权属', align : 'center'},
						{field : 'controlPrice',title : '风控核定价（元）', align : 'center'},
						{field : 'isElevator',title : '是否有电梯', align : 'center',formatter:function(r,v){
							if(v){return '是';}return '否';     
						}},  
						{field : 'isRenovation',title : '是否有装修', align : 'center',formatter:function(r,v){
	  						if(v){return '是';}return '否';     
						}},  
						{field : 'houseNo',title : '不动产证号', align : 'center'},
						{field : 'mailingAddress',title : '押品地址', align : 'center',formatter:function(r,v){
	  						return r.provinceName+" "+r.cityName+" "+r.districtName;    
						}},    
				] ],
				onDelete:function(index, rowData) {
					//  添加判断
					return true;
				},  
				onLoadSuccess:function() {
				}
			});
			
			
			$.ZUI.init();

			// 格式化时间
			var startDate = '${contractStartDate}';
			var endDate = '${contractEndDate}';
			
			if(startDate){
				var tempStartDate = startDate.substring(0,4) + '-' + startDate.substring(4,6) + '-'+ startDate.substring(6,8);
				$('#startDate').html(tempStartDate);
			}
			if(endDate){
				var tempEndDate = endDate.substring(0,4) + '-' + endDate.substring(4,6) + '-'+ endDate.substring(6,8);
				$('#endDate').html(tempEndDate);
			}
			
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
</body>
</html>