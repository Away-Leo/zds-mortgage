<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>新增请款查看</title>
    <%@ include file="../../common/common_js.jsp" %>
</head>
<body>
	<div class="page-box">
       <h1 class="page-title">
			请款信息
	    </h1>
		<div class="p10">
						<table class="table-detail">
								<tr>   
				                    <td class="td-title pct10">单据号：</td>
				                    <td class="pct20">
		                                <dd class="detail">
		                                   <label>
		                                   			${funds.billCode}
									  	   </label>
									   </dd>
									</td>
									
									 <td class="td-title pct10">请款日期：</td>
				                    <td class="pct20">
		                                <dd class="detail">
		                                   <label id="reqFundsDate_label">
		                                   			${funds.reqFundsDate}
									  	   </label>
									   </dd>
									</td>
									
									 <td class="td-title pct10">摘要：</td>
				                     <td class="pct20">
		                                <dd class="detail">
		                                   <label>
		                                   			${funds.summary}
									  	   </label>
									   </dd>
									</td>
									
								</tr>
								
								<tr>   
				                    <td class="td-title pct10">收款银行：</td>
				                    <td>	
		                                <dd class="detail">
		                                   <label>
		                                   				${funds.accountName}
									  	   </label>
									   </dd>
									</td>
									 <td class="td-title pct10">收款开户行：</td>
				                    <td>	
		                                <dd class="detail">
		                                   <label>
		                                   ${funds.bankName}
									  	   </label>
									   </dd>
									</td>
									 <td class="td-title pct10">收款账号：</td>
				                    <td>	
		                                <dd class="detail">
		                                   <label>
		                                    ${funds.bankAccount}
									  	   </label>
									   </dd>
									</td>
								</tr>
								
								<tr>   
				                    <td class="td-title pct10">往来单位：</td>
				                    <td>	
		                                <dd class="detail">
		                                   <label>
		                                   				${funds.contactCompanyName}
									  	   </label>
									   </dd>
									</td>
									 <td class="td-title pct10">请款项目：</td>
				                    <td>	
		                               <dd class="detail">
		                                   <label id="requestFundsProject_label">
		                                   				${funds.requestFundsProjectName}
									  	   </label>
									   </dd>
									</td>
									 <td class="td-title pct10"></td>
				                    <td>	
		                                <dd class="detail">
		                                  
									   </dd>
									</td>
								</tr>
								
								<tr>
				                    <td class="td-title pct10">备注：</td>
				                    <td colspan="5">
				                   		<label>
				                   			 ${funds.remark}
				                        </label>
				                    </td>
		               			 </tr>
		            </table>
				</div>
	</div>
	
	<!-- datagrid列表区域 -->
	    <div class="page-box">
	        <div class="page-title">案件费用列表</div>
			<div class="p10">
				<div id="zd-table1"></div>
			</div>
		</div>
	    
	<script type="text/javascript">
	    seajs.use(
	    	['jquery', 'zd/tools', 'zd/jquery.zds.table'],function ($, ZTOOL){
	    		
	    		var url_data_list = '<z:ukey key="com.zdsoft.finance.requestFunds.getRequestFundsDetailList" context="admin"/>&reqFundsId|E|S=${funds.id}';//获取数据
	    		
	    		$("#reqFundsDate_label").html(ZTOOL.strToDate("${funds.reqFundsDate}"));
	    		
// 	    		 $.ZUI.init();
	             
	             $('#zd-table1').ZTable({
	  	    		 url : url_data_list,
	  	    		 singleSelect: true,//表格行是单选还是多选
	                 isRowNum: true,//是否显示行号
	               	 pagination: true,//表格是否分页
	               	 rows:10,
	               	 tableCls: 'table-index',//table的样式
	                toolbar : [ ],
	                columns:[[
// 	              	  		  {field: 'payObjectName', title: '往来单位',hidden:true},
	              	  		  {field: 'caseApplyCode', title: '案件号',width:100},
	  	                      {field: 'feeTypeName', title: '请款类型',width:100},
	  	                      {field: 'feeItemName', title: '请款项目',width:100},
	  	                      {field: 'payObjectName', title: '往来单位',width:100},
	  	                      {field: 'receivedAmount', title: '实收金额(元)',width:100},
	  	                      {field: 'expectedPayableAmount', title: '预收应付(元)',width:100},
	  	                      {field: 'paidAmount', title: '累计已付(元)',width:100},
	  	                      {field: 'reqFundsAmount', title: '请款金额(元)',width:100}
	                     ]]
	      		});	
          		
	    });
	</script>
</body>
</html>