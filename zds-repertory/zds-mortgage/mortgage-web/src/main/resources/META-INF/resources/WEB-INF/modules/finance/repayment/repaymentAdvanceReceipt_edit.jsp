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
			 		<input  type="hidden"  name="caseApplyId" value="${caseApply.id}"/>		
			 			
								<table class="table-detail">
										<tr>   
						                    <td class="td-title pct10"><b class="c-red ">*</b>单据号：</td>
						                    <td class="pct25">
				                                <dd class="detail">
				                                   <label>
				                                   <input type="text"  class="zui-input zui-disabled"  id="billCode" name="billCode"   value="${receipt.billCode}"  readonly="readonly"/>
											  	   </label>
											   </dd>
											</td>
											
											 <td class="td-title pct10"><b class="c-red ">*</b>实际收款日期：</td>
						                    <td class="pct25">	
						                     <dl class="form-item form-auto">
				                                <dd class="detail">
				                                   <label>
						                    				<input class="zui-date zui-validatebox" type="text" id="paidRepayDate_date"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',realDateFmt:'yyyyMMdd',vel:'paidRepayDate'})"  validate-type="Require">
					                         				<input type="hidden" id="paidRepayDate" name="paidRepayDate"  value="${receipt.paidRepayDate}">
											  	   </label>
											   </dd>
				                            </dl>
											</td>
											
											 <td class="td-title pct10"><b class="c-red ">*</b>摘要：</td>
						                    <td class="pct25">	
						                     <dl class="form-item form-auto">
				                                <dd class="detail">
				                                   <label>
						                    				<input type="text"  class="zui-input zui-validatebox"  id="summary" name="summary"  validate-type="Require,Length[1-50]"  value="${receipt.summary}"/>
											  	   </label>
											   </dd>
				                            </dl>
											</td>
											
										</tr>
										
										<tr>   
						                    <td class="td-title pct10"><b class="c-red ">*</b>缴款人：</td>
						                    <td>	
						                     <dl class="form-item form-auto">
				                                <dd class="detail">
				                                   <label>
						                    				<input type="text"  class="zui-input zui-validatebox"  id="payer" name="payer"  validate-type="Require,Length[1-50]"  value="${receipt.payer}"/>
											  	   </label>
											   </dd>
				                            </dl>
											</td>
											 <td class="td-title pct10"><b class="c-red ">*</b>收据经手人：</td>
						                    <td>	
						                     <dl class="form-item form-auto">
				                                <dd class="detail">
				                                   <label>
						                    				<input type="text"  class="zui-input zui-validatebox"  id="receipts" name="receipts"  validate-type="Require,Length[1-50]"  value="${receipt.receipts}"/>
											  	   </label>
											   </dd>
				                            </dl>
											</td>
											
											 <td class="td-title pct10"><b class="c-red ">*</b>预收款(元)：</td>
						                    <td>	
						                     <dl class="form-item form-auto">
				                                <dd class="detail">
				                                   <label>
						                    				<input type="text"  class="zui-input zui-validatebox"  id="collectionAmount" name="collectionAmount"  validate-type="Require,Amount"  value="${receipt.collectionAmount}"/>
											  	   </label>
											   </dd>
				                            </dl>
											</td>
											
										</tr>
										
											
										<tr>
						                    <td class="td-title pct10">结算方式：</td>
						                    <td >
						                   			<label><input  name="settlementMethod" type="radio" 
						                   				<c:if test="${receipt.settlementMethod == 'A'}" >checked="checked"</c:if>
						                   			value="A" />A现金 </label>
						                        	<label><input  name="settlementMethod" type="radio"   
						                        		<c:if test="${receipt.settlementMethod == 'B'}" >checked="checked"</c:if>
						                        	 value="B" />B银行转账 </label>
						                        	<label><input  name="settlementMethod" type="radio"   
						                        		<c:if test="${receipt.settlementMethod == 'C'}" >checked="checked"</c:if>
													 value="C" />C银联代扣 </label>
						                        	<label><input  name="settlementMethod" type="radio"   
						                        		<c:if test="${receipt.settlementMethod == 'D'}" >checked="checked"</c:if>
						                        	  value="D"  />D其他 </label>
						                    </td>
						                    
				               			 <td class="td-title pct10">收据号：</td>
						                    <td>	
						                     <dl class="form-item form-auto">
				                                <dd class="detail">
				                                   <label>
						                    				<input type="text"  class="zui-input zui-validatebox"  id="colloectionCode" name="colloectionCode"  validate-type="Length[1-30]"  value="${receipt.colloectionCode}"/>
											  	   </label>
											   </dd>
				                            </dl>
											</td>
											
											 <td class="td-title pct10">是否已开票：</td>
					                     <td>	
						                     <dl class="form-item form-auto">
				                                <dd class="detail">
											  	  			         <input class="zui-combobox zui-validatebox" type="hidden" data-multiple="false"  name="isBill"
						                                   data-data="[{'id':'false','text':'否'},{'id':'true','text':'是'}]"
						                                   data-valuefield="id" data-textfield="text"  data-defaultvalue="${receipt.isBill}"  >
											   </dd>
				                            </dl>
										  </td>
										</tr>
										
										<tr>
		                    <td class="td-title pct10">备注：</td>
		                    <td colspan="5">
		                   		<label>
		                         	<textarea class="zui-area row-width zui-validatebox"  placeholder="最多可以输入200个字符" id="remark" name="remark" validate-type="Length[0-200]">${receipt.remark}</textarea>
		                        </label>
		                        <div class="zd-area">
		                            <span class="zd-curval">0</span>/<span class="zd-maxval">200</span>
		                        </div>
		                    </td>
		                </tr>
				            </table>
				</form>
			</div>
		</div>
		
	<div class="page-box">
	   <!-- 按钮区域 -->
		<div class="save">
			<button id="btn-save" class="btn-blue mr10">保存</button>
			<button id="btn-submit" class="btn-blue mr10">提交</button>
		</div>
	</div>
	
	<script type="text/javascript">
	 seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/tools','zd/jquery.zds.combobox', 'zd/jquery.zds.seleter', 'zd/jquery.zds.form', 'zd/jquery.zds.table'],
	    	 function ($, CALLBACK,ZTOOL){
		 	
			var save_url = '<z:ukey key="com.zdsoft.finance.repayment.saveOrUpdateRepaymentReceipt"  context="admin"/>';
			
            $.ZUI.init();
            
            $("#paidRepayDate_date").val(ZTOOL.strToDate("${receipt.paidRepayDate}"));
            
           $("#btn-save").click(function (){
        	   saveOrSubmitForm(false);
	 		});
           
           $("#btn-submit").click(function (){
        	   saveOrSubmitForm(true);
	 		});
           
           function saveOrSubmitForm(submit){
        	  
        	   var validFlg = $.ZUI.validateForm($('#saveOrupdateFrom'));
 				 if (validFlg) {	
 					 var param=$('#saveOrupdateFrom').serialize();
 					 if(submit){
 						param+="&status=1";
 					 }
 					$.ajax({
 			                type: 'post',
 			                url: save_url,
 			                data:param,
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
 				 } else {
 					$.ZMessage.error("错误", "数据验证失败");
 				 } 
           }
       
    });
</script>
</body>
</html>