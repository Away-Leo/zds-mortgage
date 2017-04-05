<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>新增收款单</title>
    <%@ include file="../../common/common_js.jsp" %>
    <style>
    	table.scroll th,table.scroll td{
    		white-space: nowrap;
    	}
    </style>
</head>
<body id="body">
	<div class="frm-content frm-bottom">
		<!-- 	案件基本信息 -->
		<jsp:include page="../../casemanage/case_base_info.jsp"></jsp:include>
		<div class="page-box" >
			 <h1 class="page-title">收款单信息</h1>
			<div div class="p10">
			 	<form id="saveOrupdateFrom" class="zui-form" action="javascript:void(0);" zdata-options="{}">		
			 		<input  type="hidden"  name="receiptType" value="1"/>	
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
						                    				<input class="zui-date zui-validatebox" type="text" id="paidRepayDate_date"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',realDateFmt:'yyyyMMdd',vel:'paidRepayDate'})" >
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
											
											 <td class="td-title pct10 sptitle"><b class="c-red ">*</b>是否提前结清还款:</td>
						                    <td>	
						                     <dl class="form-item form-auto">
				                                <dd class="detail">
											  				 <input class="zui-combobox zui-validatebox" type="hidden" data-multiple="false"  name="isRepayAdvance"
								                                   data-data="[{'id':'false','text':'否'},{'id':'true','text':'是'}]"
								                                   data-valuefield="id" data-textfield="text"   data-callback="changeRepayAdvance"  data-defaultvalue="${receipt.isRepayAdvance}"  validate-type="Require" >  
											   </dd>
				                            </dl>
											</td>
											</tr>
											
											<tr>
												<td class="td-title pct10 sptitle">是否收取提前还款违约金:</td>
							                    <td>	
							                     <dl class="form-item form-auto">
					                                <dd class="detail">
							                    				 <input class="zui-combobox zui-validatebox" type="hidden" data-multiple="false"  name="isRepayDamages"
								                                   data-data="[{'id':'false','text':'否'},{'id':'true','text':'是'}]"
								                                   data-valuefield="id" data-textfield="text"  data-defaultvalue="${receipt.isRepayDamages}"  >
												   </dd>
					                            </dl>
												</td>
												
												<td class="td-title pct10 sptitle">提前还款违约金比例(%):</td>
							                    <td>	
							                     <dl class="form-item form-auto">
					                                <dd class="detail">
					                                   <label>
							                    				<input type="text"  class="zui-input zui-validatebox"  id="breachProportion" name="breachProportion"  validate-type="Amount"  value="${receipt.breachProportion}"/>
												  	   </label>
												   </dd>
					                            </dl>
												</td>
												
												<td class="td-title pct10 sptitle">提前还款违约罚息:</td>
							                    <td>	
							                     <dl class="form-item form-auto">
					                                <dd class="detail">
					                                   <label>
							                    				<input type="text"  class="zui-input zui-validatebox"  id="breachPenalty" name="breachPenalty"  validate-type="Amount"  value="${receipt.breachPenalty}"/>
												  	   </label>
												   </dd>
					                            </dl>
												</td>	
											</tr>
											
											<tr>
											
											<td class="td-title pct10"><b class="c-red ">*</b>逾期计算方式：</td>
							                     <td>	
								                     <dl class="form-item form-auto">
						                                <dd class="detail">
								                                    <input class="zui-combobox zui-validatebox" type="hidden" data-multiple="false"  id="overdueWay"  name="overdueWay"
								                                   data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0095"
			                                   						data-valuefield="fullcode" data-textfield="name"
								                                   data-defaultvalue="${receipt.overdueWay}"  validate-type="Require">
													   </dd>
						                            </dl>
												  </td>
												  
												 <td class="td-title pct10">收据号：</td>
							                     <td>	
								                     <dl class="form-item form-auto">
						                                <dd class="detail">
						                                   <label>
								                    				<input type="text"  class="zui-input zui-validatebox"  id="colloectionCode" name="colloectionCode"   validate-type="Length[1-30]"  value="${receipt.colloectionCode}"/>
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
						                    <td class="td-title pct10">结算方式：</td>
						                    <td colspan="5">
						                   			<label><input  name="settlementMethod" type="radio"   <c:if test="${receipt.settlementMethod == 'A'}" >checked="checked"</c:if> value="A"/>A现金 </label>
						                        	<label><input  name="settlementMethod" type="radio"  <c:if test="${receipt.settlementMethod == 'B'}" >checked="checked"</c:if> value="B"/>B银行转账 </label>
						                        	<label><input  name="settlementMethod" type="radio"  <c:if test="${receipt.settlementMethod == 'C'}" >checked="checked"</c:if> value="C"/>C银联代扣 </label>
						                        	<label><input  name="settlementMethod" type="radio"  <c:if test="${receipt.settlementMethod == 'D'}" >checked="checked"</c:if> value="D" />D其他 </label>
						                        	<br/>
						                        	
						                        	<label style="margin-left: 47px;"><input  name="settlementMethod" type="radio"  <c:if test="${receipt.settlementMethod == 'B1'}" >checked="checked"</c:if> value="B1"/>B1转入方为客户关联人，如配偶、担保人、抵押物权属人等 </label>
						                        	<label><input  name="settlementMethod" type="radio"  <c:if test="${receipt.settlementMethod == 'B2'}" >checked="checked"</c:if> value="B2"/>B2未签署第三方还款授权书的受托人转账 </label>
						                        	<br/>
						                        	
						                        	<label style="margin-left: 47px;margin-top: 10px;"><input  name="settlementMethod" type="radio"  <c:if test="${receipt.settlementMethod == 'B3'}" >checked="checked"</c:if> value="B3"/>B3已签署第三方还款授权书的受托人转账(非公司员工) </label>
						                        	<label style="margin-left: 28px;"><input  name="settlementMethod" type="radio"  <c:if test="${receipt.settlementMethod == 'B4'}" >checked="checked"</c:if> value="B4"/>B4已签署第三方还款授权书的受托人转账(公司员工)</label>
						                        	<br/>
						                        	
						                        	<label style="margin-left: 47px;margin-top: 10px;"><input  name="settlementMethod" type="radio"  <c:if test="${receipt.settlementMethod == 'B5'}" >checked="checked"</c:if> value="B5"/>B5通过微信或支付宝等支付方式，或无法判断支付人 </label>
						                    </td>
				               			 </tr>
				               			 
				               			 <tr>   
						                    <td class="td-title pct10"><b class="c-red ">*</b>录款方式：</td>
						                    <td>	
						                     <dl class="form-item form-auto">
				                                <dd class="detail">
											  
											                   <input class="zui-combobox zui-validatebox" type="hidden" data-multiple="false"  id="recordMethod"  name="recordMethod"
				                                   data-url='<z:ukey key='com.zdsoft.finance.repayment.getRecordMethod'  context='admin'/>&caseApplyId=${caseApply.id}&isSettent=false'
				                                   data-valuefield="id" data-textfield="text" data-callback="changeRecordMethod" data-defaultvalue="${receipt.recordMethod}"  validate-type="Require">
											 
											   </dd>
				                            </dl>
											</td>
											
											 <td class="td-title pct10"><b class="c-red ">*</b>收款金额(元)：</td>
						                    <td>	
						                     <dl class="form-item form-auto">
				                                <dd class="detail">
				                                   <label>
						                    				<input type="text"  class="zui-input zui-validatebox"  id="collectionAmount" name="collectionAmount"  validate-type="Require,Amount"  value="${receipt.collectionAmount}"/>
											  	   </label>
											   </dd>
				                            </dl>
											</td>
											
											 <td class="td-title pct10"><b class="c-red ">*</b>剩余款项分配：</td>
						                    <td>	
						                     <dl class="form-item form-auto">
				                                <dd class="detail">
								                                    <input class="zui-combobox zui-validatebox" type="hidden" data-multiple="false"  id="residual" name="residual"
									                                   data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0093"
						                                   			   data-valuefield="fullcode" data-textfield="name"
									                                   data-callback="changeResidual" data-defaultvalue="${receipt.residual}"  validate-type="Require">
											   </dd>
				                            </dl>
											</td>
											
										</tr>
				            </table>
				</form>
			</div>
		</div>
			
		<!-- datagrid列表区域 -->
		 <div class="page-box">
	    <div class="page-title">还款计划列表</div>
				<div class="p10  ">
						<table class="table-index" >
							<tr>
								<th >还款计划</th>
								
								<th >待还</th>
								
								<th >本次实收</th>
							</tr>
						</table>
						<div id="zd-table" ></div>
				</div>
			   <!-- 按钮区域 -->
				<div class="save">
					<button id="btn-save" class="btn-blue mr10">保存</button>
					<button id="btn-submit" class="btn-blue mr10">提交</button>
				</div>
		</div>
	</div>
	<script type="text/javascript">
	 seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/tools','zd/jquery.zds.combobox', 'zd/jquery.zds.seleter', 'zd/jquery.zds.form', 'zd/jquery.zds.table'],
	    	 function ($, CALLBACK,ZTOOL){
		 	
		    var url_data_list = '<z:ukey key="com.zdsoft.finance.help.getRepaymentAmountAllotList" context="admin"/>';
			var save_url = '<z:ukey key="com.zdsoft.finance.repayment.saveOrUpdateRepaymentReceipt"  context="admin"/>';
			
			CALLBACK.changeRepayAdvance=function(value, text) {
					$("#recordMethod").ZCombobox({
					    valueField: "id",
					    textField: "text",
					   	url:"<z:ukey key='com.zdsoft.finance.repayment.getRecordMethod'  context='admin'/>&caseApplyId=${caseApply.id}&isSettent="+value
					});
			};
			
			CALLBACK.changeRecordMethod = function(value, text) {
				if(value == "YWDM009402") {
					 $("#zd-table input").removeClass("zui-disabled");
					 $("#zd-table input").attr("readonly",false);
				}else{
					 $("#zd-table input").removeClass("zui-disabled");
					 $("#zd-table input").addClass("zui-disabled");
					 $("#zd-table input").attr("readonly",true);
				}
				
				//保证金转供款
				if(value == "YWDM009403") {
					$.ajax({
		                type: 'post',
		                url: '<z:ukey key="com.zdsoft.finance.repayment.findByCaseApplyIdCash" context="admin"/>&caseApplyId=${caseApply.id}',
						dataType: "json",
		                success: function (data) {
		                	$("#collectionAmount").val(data);
		                }
		            });
				}
				
				//预收款转供款
				if(value == "YWDM009404") {
					$.ajax({
			                type: 'post',
			                url: '<z:ukey key="com.zdsoft.finance.repayment.findByCaseApplyIdAndReceiptTypeAndStatus" context="admin"/>&caseApplyId=${caseApply.id}&receiptType=2',
							dataType: "json",
			                success: function (data) {
			                	$("#collectionAmount").val(data);
			                }
			            });
				}
			};
			
			CALLBACK.changeResidual = function(value, text) {
				 if($("#overdueWay").ZCombobox("getValue")==""){
					 $.ZMessage.warning("警告", "请选择逾期计算方式!");
					 return;
				 }
				 $('#zd-table').ZTable("reload",{"recordMethod":$("#recordMethod").ZCombobox("getValue"),"caseApplyId":"${caseApply.id}","residual":value,"paidRepayDate":$("input[name=paidRepayDate]").val(),"collectionAmount":$("input[name=collectionAmount]").val(),"overdueWay":$("#overdueWay").ZCombobox("getValue")});
			};
			 
            $.ZUI.init();
			$("#paidRepayDate_date").val(ZTOOL.strToDate("${receipt.paidRepayDate}"));
			
            $('#zd-table').ZTable({
	    	      url : url_data_list,
                singleSelect : true,
                isRowNum : false,
                pagination : false,
                onEdit: true,
                isScroll:true,
                tableCls: 'table-index scroll',
                queryParams:{"recordMethod":"${receipt.recordMethod}","caseApplyId":"${caseApply.id}","residual":"${receipt.residual}","paidRepayDate":"${receipt.paidRepayDate}","collectionAmount":"${receipt.collectionAmount}","overdueWay":"${receipt.overdueWay}"},
                toolbar : [ ],
                columns:[[
                	{field : 'ovreDueDay',hidden:true},
					{field : 'periods',title : '期数',align : 'center'},
					{field : 'planRepayDate',title : '应还日期',align : 'center',
                        formatter: function (row, value) {
                            return ZTOOL.strToDate(value+""); 
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

					{field : 'paidServiceFee',title : '服务费',align : 'center'},
					{field : 'currentPaidPenalty',title : '当期罚息',align : 'center'},
					{field : 'paidInterestAmount',title : '利息',align : 'center'},
					{field : 'paidPrincipalAmount',title : '本金',align : 'center'},
					{field : 'paidDamages',title : '违约金',align : 'center'},
					{field : 'paidPenalty',title : '罚息',align : 'center'}
				] ],
				columnsType: [{
					"paidServiceFee": {
						"inputType": "input",
                        "defaultValue":"0",
                        "validateType": "Require,Amount,IsOverNumber,CompareAmount[<=-dplanServiceFee]",
                        "validateFalse":"该项为必填项|输入金额不正确|小数位数不能超过2位!|不能大于待还服务费"
					},"currentPaidPenalty": {
						"inputType": "input",
                        "defaultValue":"0",
                        "validateType": "Require,Amount,IsOverNumber",
                        "validateFalse":"该项为必填项|输入金额不正确|小数位数不能超过2位!"
					},"paidInterestAmount": {
						"inputType": "input",
                        "defaultValue":"0",
                        "validateType": "Require,Amount,IsOverNumber,CompareAmount[<=-dplanInterestAmount]",
                        "validateFalse":"该项为必填项|输入金额不正确|小数位数不能超过2位!|不能大于待还利息"
					},"paidPrincipalAmount": {
						"inputType": "input",
                        "defaultValue":"0",
                        "validateType": "Require,Amount,IsOverNumber,CompareAmount[<=-dplanPrincipalAmount]",
                        "validateFalse":"该项为必填项|输入金额不正确|小数位数不能超过2位!|不能大于待还本金"
					},"paidDamages": {
						"inputType": "input",
                        "defaultValue":"0",
                        "validateType": "Require,Amount,IsOverNumber,CompareAmount[<=-dplanDamages]",
                        "validateFalse":"该项为必填项|输入金额不正确|小数位数不能超过2位!|不能大于待还违约金"
					},"paidPenalty": {
						"inputType": "input",
                        "defaultValue":"0",
                        "validateType": "Require,Amount,IsOverNumber",
                        "validateFalse":"该项为必填项|输入金额不正确|小数位数不能超过2位!"
					},
				}, {
					"inputWidth": 85,
					"inputHeight": 24
				}],
				onLoadSuccess:function(data){
		            $("#zd-table input").removeClass("zui-disabled");
					$("#zd-table input").addClass("zui-disabled");
					$("#zd-table input").attr("readonly",true);
				}
      	});
          
			
            $("#btn-save").click(function (){
         	   saveOrSubmitForm(false);
 	 		});
            
            $("#btn-submit").click(function (){
         	   saveOrSubmitForm(true);
 	 		});
           
           function saveOrSubmitForm(submit){
        	  
        	   var validFlg = $.ZUI.validateForm($('#saveOrupdateFrom'));
        	   var row= $('#zd-table').ZTable("getRows");
        	   
 				 if (validFlg&&(typeof(row)!="undefined"&&row.length>0)) {	
 					 var param=$('#saveOrupdateFrom').serialize();
 					 if(submit){
 						param+="&status=1";
 					 }
 					$.ajax({
 			                type: 'post',
 			                url: save_url,
 			                data:param+"&receiptList="+JSON.stringify($('#zd-table').ZTable("getRows")),
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
 					$.ZMessage.warning("提示", "请确认录入完成以及对应的还款计划！");
 				 }
           }
    });
</script>
</body>
</html>