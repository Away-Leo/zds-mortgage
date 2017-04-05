<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>新增收款单</title>
    <%@ include file="../../common/common_js.jsp" %>
</head>
<body id="body">
	<div class="frm-content frm-bottom">
	<!-- 	案件基本信息 -->
	<jsp:include page="../../casemanage/case_base_info.jsp"></jsp:include>
	<div class="page-box">
	 <h1 class="page-title">收款单信息</h1>
	<div div class="p10">
	 	<form id="saveOrupdateFrom" class="zui-form" action="javascript:void(0);" zdata-options="{}">		
	 		<input  type="hidden"  name="receiptType" value="1"/>	
	 		<input  type="hidden"  name="id" id="id" value="${receipt.id}"/>	
						<table class="table-detail">
								<tr>   
				                    <td class="td-title pct10">单据号：</td>
				                    <td class="pct25">
		                                ${receipt.billCode}
									</td>
									
									 <td class="td-title pct10">实际收款日期：</td>
				                    <td class="pct25">	
				                     ${receipt.paidRepayDate}
									</td>
									
									 <td class="td-title pct10">摘要：</td>
				                    <td class="pct25">	
		                            	${receipt.summary}
									</td>
									
								</tr>
								
								<tr>   
				                    <td class="td-title pct10">缴款人：</td>
				                    <td>	
		                            	${receipt.payer}
									</td>
									 <td class="td-title pct10">收据经手人：</td>
				                    <td>	
		                            	${receipt.receipts}
									</td>
									
									<td class="td-title pct10">是否提前结清还款：</td>
				                    <td>	
		                            	${receipt.isRepayAdvance?'是':'否'}
									</td>
							</tr>
								
								<tr>   
								 <td class="td-title pct10">是否收取提前还款违约金：</td>
				                    <td>	
		                            	${receipt.isRepayDamages?'是':'否'}
									</td>
									 <td class="td-title pct10">提前还款违约金比例(%)：</td>
				                    <td>	
		                            	${receipt.breachProportion}
									</td>
									
									<td class="td-title pct10">提前还款违约罚息：</td>
				                    <td>	
		                            	${receipt.breachPenalty}
									</td>
							</tr>
							<tr>   
									<td class="td-title pct10">逾期计算方式：</td>
				                    <td>	
				                     		${receipt.overdueWayName}
									</td> 
									 <td class="td-title pct10">收据号：</td>
				                    <td>	
				                     		${receipt.colloectionCode}
				                            ${receipt.isBill  ? '已':'未' }开票
									</td>
								</tr>
								
								<tr>
				                    <td class="td-title pct10">结算方式：</td>
				                    <td colspan="5">
				                    		<c:if test="${receipt.settlementMethod == 'A'}" >现金</c:if>
				                    		<c:if test="${receipt.settlementMethod == 'B'}" >银行转账</c:if>
				                    		<c:if test="${receipt.settlementMethod == 'C'}" >银联代扣</c:if>
				                    		<c:if test="${receipt.settlementMethod == 'D'}" >其他</c:if>
				                    		<c:if test="${receipt.settlementMethod == 'B1'}" >转入方为客户关联人，如配偶、担保人、抵押物权属人等</c:if>
				                    		<c:if test="${receipt.settlementMethod == 'B2'}" >未签署第三方还款授权书的受托人转账</c:if>
				                    		<c:if test="${receipt.settlementMethod == 'B3'}" >已签署第三方还款授权书的受托人转账(非公司员工)</c:if>
				                    		<c:if test="${receipt.settlementMethod == 'B4'}" >已签署第三方还款授权书的受托人转账(公司员工)</c:if>
				                    		<c:if test="${receipt.settlementMethod == 'B5'}" >通过微信或支付宝等支付方式，或无法判断支付人</c:if>
				                    </td>
		               			 </tr>
		               			 
		               			 <tr>   
				                    <td class="td-title pct10">录款方式：</td>
				                    <td>	
				                    	${receipt.recordMethodName}
									</td>
									 <td class="td-title pct10">剩余款项分配：</td>
				                    <td>	
		                            	${receipt.residualName}
									</td>
									 <td class="td-title pct10">收款金额(元)：</td>
				                    <td>	
				                     ${receipt.collectionAmount}
									</td>
								</tr>
		            </table>
		</form>
		</div>
	</div>
		
		<!-- datagrid列表区域 -->
	    <div class="page-box">
		        <div class="page-title">还款计划列表</div>
					<div class="p10">
						<table class="table-index" >
								<tr>
									<th >还款计划</th>
									
									<th >待还</th>
									
									<th >本次实收</th>
								</tr>
							</table>
							<div id="zd-table"></div>
					</div>
				</div>
		
			   <!-- 按钮区域 -->
			   <c:if test="${scan}">
				   <div class="save">
						<button id="btn-back" class="btn-blue mr10">退回</button>
						<button id="btn-save" class="btn-blue mr10">确认</button>
					</div>
			   </c:if>
		</div>
</div>		
	<script type="text/javascript">
	 seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/tools','zd/jquery.zds.combobox', 'zd/jquery.zds.seleter', 'zd/jquery.zds.form', 'zd/jquery.zds.table'],
	    	 function ($, CALLBACK,ZTOOL){
		 	var url_data_list = '<z:ukey key="com.zdsoft.finance.repayment.findRepaymentAmountAllotByReceiptId" context="admin"/>&receiptId=${receipt.id}';
			var update_url = '<z:ukey key="com.zdsoft.finance.repayment.updateStatus"  context="admin"/>';
			
			
            $.ZUI.init();
			
            $('#zd-table').ZTable({
	    	    url : url_data_list,
                singleSelect : true,
                isRowNum : false,
                pagination : false,
                tableCls : 'table-index',//table的样式
                toolbar : [ ],
                columns:[[
					{field : 'periods',title : '期数',align : 'center'},
					{field : 'planRepayDate',title : '应还日期',align : 'center',
                        formatter: function (row, value) {
                            return ZTOOL.strToDate(value+""); //时间转换
                        }},
					{field : 'planPrincipalAmount',title : '本金',align : 'center',
                            formatter: function (row, value) {
                                return ZTOOL.formatCurrency(value+""); 
                            }},
					{field : 'planServiceFee',title : '服务费',align : 'center',
                                formatter: function (row, value) {
                                    return ZTOOL.formatCurrency(value+""); 
                                }},
					{field : 'planInterestAmount',title : '利息',align : 'center',
                                    formatter: function (row, value) {
                                        return ZTOOL.formatCurrency(value+""); 
                                    }},
					{field : 'planPenalty',title : '罚息',align : 'center',
                                        formatter: function (row, value) {
                                            return ZTOOL.formatCurrency(value+""); 
                                        }},
					{field : 'currentPlanPenalty',title : '当期罚息',align : 'center',
                                            formatter: function (row, value) {
                                                return ZTOOL.formatCurrency(value+""); 
                                            }},
					{field : 'planDamages',title : '违约金',align : 'center',
                                                formatter: function (row, value) {
                                                    return ZTOOL.formatCurrency(value+""); 
                                                }},
					
					{field : 'dplanPrincipalAmount',title : '本金',align : 'center',
                                                    formatter: function (row, value) {
                                                        return ZTOOL.formatCurrency(value+""); 
                                                    }},
					{field : 'dplanServiceFee',title : '服务费',align : 'center',
                                                        formatter: function (row, value) {
                                                            return ZTOOL.formatCurrency(value+""); 
                                                        }},
					{field : 'dplanInterestAmount',title : '利息',align : 'center',
                                                            formatter: function (row, value) {
                                                                return ZTOOL.formatCurrency(value+""); 
                                                            }},
					{field : 'dplanPenalty',title : '罚息',align : 'center',
                                                                formatter: function (row, value) {
                                                                    return ZTOOL.formatCurrency(value+""); 
                                                                }},
					{field : 'dcurrentPlanPenalty',title : '当期罚息',align : 'center',
                                                                    formatter: function (row, value) {
                                                                        return ZTOOL.formatCurrency(value+""); 
                                                                    }},
					{field : 'dplanDamages',title : '违约金',align : 'center',
                                                                        formatter: function (row, value) {
                                                                            return ZTOOL.formatCurrency(value+""); 
                                                                        }},

					{field : 'paidServiceFee',title : '服务费',align : 'center',
                                                                            formatter: function (row, value) {
                                                                                return ZTOOL.formatCurrency(value+""); 
                                                                            }},
					{field : 'currentPaidPenalty',title : '当期罚息',align : 'center',
                                                                                formatter: function (row, value) {
                                                                                    return ZTOOL.formatCurrency(value+""); 
                                                                                }},
					{field : 'paidInterestAmount',title : '利息',align : 'center',
                                                                                    formatter: function (row, value) {
                                                                                        return ZTOOL.formatCurrency(value+""); 
                                                                                    }},
					{field : 'paidPrincipalAmount',title : '本金',align : 'center',
                                                                                        formatter: function (row, value) {
                                                                                            return ZTOOL.formatCurrency(value+""); 
                                                                                        }},
					{field : 'paidDamages',title : '违约金',align : 'center',
                                                                                            formatter: function (row, value) {
                                                                                                return ZTOOL.formatCurrency(value+""); 
                                                                                            }},
					{field : 'paidPenalty',title : '罚息',align : 'center',
                                                                                                formatter: function (row, value) {
                                                                                                    return ZTOOL.formatCurrency(value+""); 
                                                                                                }}
				] ]
      	});
            
            
           
			 $("#btn-back").click(function (){
	        	   $.ZMessage.question("确认", "确认退回？", function (r) {
	        		   updateThis("${receipt.id}",3);
	        	   });
		 		});
			 
	           $("#btn-save").click(function (){
	        	   $.ZMessage.question("确认", "确认通过？", function (r) {
	        	   		updateThis("${receipt.id}",2);
	        	   });
		 		});
	           
	           function updateThis(id,status){
 					$.ajax({
 			                type: 'post',
 			                url: update_url,
 			                data:"&id="+id+"&status="+status,
 							dataType: "json",
 			                success: function (data) {
 			                	if(data.resultStatus== 0){
 			                		$.ZMessage.success("提示", "操作成功",function(){
	 			                			ZDS_MESSAGE_CLIENT.refreshOpenner();
				                    		setTimeout(function(){
			                                	 ZDS_MESSAGE_CLIENT.closeSelf();
			                                },200);
	 			                		});
 			                	}else{
 			                		$.ZMessage.error("错误", "操作失败");
 			                	}
 			                }
 			            });
 				 } 
    });
</script>
</body>
</html>