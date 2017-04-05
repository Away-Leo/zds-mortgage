<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>新增预收款</title>
    <%@ include file="../../common/common_js.jsp" %>
</head>
<body id="body">
	
	<!-- 	案件基本信息 -->
	<jsp:include page="../../casemanage/case_base_info.jsp"></jsp:include>
	 
	 <div class="page-box">
		 <h1 class="page-title">收款单信息</h1>
			<div class="p10">
			 	<form id="saveOrupdateFrom" class="zui-form" action="javascript:void(0);" zdata-options="{}">	
			 		<input  type="hidden"  name="receiptType" value="2"/>	
			 		<input  type="hidden"  name="id" value="${receipt.id}"/>	
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
											
											 <td class="td-title pct10">预收款(元)：</td>
						                    <td>	
				                            ${receipt.collectionAmount}
											</td>
											
										</tr>
										
										<tr>
						                    <td class="td-title pct10">结算方式：</td>
						                    <td >
						                    	<c:if test="${receipt.settlementMethod == 'A'}" >现金</c:if>
				                    			<c:if test="${receipt.settlementMethod == 'B'}" >银行转账</c:if>
				                    			<c:if test="${receipt.settlementMethod == 'C'}" >银联代扣</c:if>
				                    			<c:if test="${receipt.settlementMethod == 'D'}" >其他</c:if>
						                    </td>
						                    
				               			 <td class="td-title pct10">收据号：</td>
						                    <td>	
				                            	${receipt.colloectionCode}
				                            	${receipt.isBill? '已':'未' }开票
											</td>
											
											<td class="td-title pct10"></td>
						                    <td>	
						                     <dl class="form-item form-auto">
				                                <dd class="detail">
				                                  
											   </dd>
				                            </dl>
											</td>
											
										</tr>
				            </table>
				</form>
			</div>
		</div>
		
		 <c:if test="${scan}">
		   <!-- 按钮区域 -->
			<div class="save">
				<button id="btn-back" class="btn-blue mr10">退回</button>
				<button id="btn-save" class="btn-blue mr10">确认</button>
			</div>
		</c:if>
	<script type="text/javascript">
	 seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/tools','zd/jquery.zds.combobox', 'zd/jquery.zds.seleter', 'zd/jquery.zds.form', 'zd/jquery.zds.table'],
	    	 function ($, CALLBACK,ZTOOL){
		 	
			var update_url = '<z:ukey key="com.zdsoft.finance.repayment.updateStatus"  context="admin"/>';
			
            $.ZUI.init();
            
            
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