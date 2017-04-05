<%@ page language="java" contentType="text/html; charset=UTF-8" 	pageEncoding="UTF-8"%>
<%@ include file='../../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<div class="frm-content frm-bottom">

	 <div class="save">
	    <button id="btn-submit" class="btn-blue mr10">提交</button>
	</div>
	
	<div class="mr10" style="text-align: right; margin-right: 1px;">
		<button type="button" class="btn-search-blue" id="btn-print1" >打印收费明细</button>
		<button type="button" class="btn-search-blue" id="btn-print2" >打印收据</button>
	</div>
		
		<div class="page-box">
	            <div class="page-title">收款单</div>
	            <div class="p5">
	                <table class="table-detail">
	                    <tbody><tr>
	                        <td class="td-title pct10">单据号：</td>
	                        <td class="pct20">
	                             ${finIncomeVo.billCode}
	                        </td>
	                        <td class="td-title pct10">案件号：</td>
	                        <td class="pct20">
	                             ${finIncomeVo.caseApplyCode}
	                        </td>
	                        <td class="td-title pct10">主借人：</td>
	                        <td class="pct30">${caseApplyVo.customerName} </td>
	                    </tr>
	                    <tr>
	                        <td class="td-title">业务部门：</td>
	                        <td> ${finIncomeVo.orgName }</td>
	                        <td class="td-title">付款方：</td>
	                        <td> ${finIncomeVo.payerIdName} </td>
	                        <td class="td-title">付款人：</td>
	                        <td>${finIncomeVo.payerCustomerName} </td>
	                    </tr>
	                    <tr>
	                        <td class="td-title">收款日期：</td>
	                        <td  id="td_receiptsDate">${finIncomeVo.receiptsDate} </td>
	                        <td class="td-title" >收款总金额(元)：</td>
	                        <td id="totalExpectedAmount"></td>
	                        <td class="td-title" >收入总金额(元)：</td>
	                        <td id="totalReceivedAmount"> </td>
	                    </tr>
	                    <tr>
	                        <td class="td-title">代收总金额(元)：</td>
	                        <td id="totalCollectionAmount">0</td>
	                        <td class="td-title">预收总金额(元)：</td>
	                        <td id="totalpredictAmount"> </td>
	                        <td class="td-title">结算方式：</td>
	                        <td>
	                        	<dl class="form-item form-auto">
                                <dd class="detail">
                                	<input class="zui-checkbox" id="settlement_x" type="hidden"  data-multiple="true"   
                            		data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0086"
                                    data-valuefield="id" data-textfield="text" data-choose="disable"  value="${finIncomeVo.settlement}">
                                </dd>
                            </dl>
	                        </td>
	                    </tr>
	                    <tr>
	                       
	                        <td class="td-title">缴款人：</td>
	                        <td>
	                             ${finIncomeVo.payerPersonName }
	                        </td>
	                        <td class="td-title">摘要：</td>
	                        <td>
	                             ${finIncomeVo.summary }
	                        </td>
	                         <td class="td-title">收据号：</td>
	                        <td>
	                             ${finIncomeVo.receiptNo }&nbsp;&nbsp;&nbsp;&nbsp;
	                             ${finIncomeVo.invoiceFlag eq '0' ? '已':'未' }开票
	                        </td>
	                    </tr>
	                    <tr>
	                        <td class="td-title">备注：</td>
	                        <td colspan="6">
	                            <label>
				                 	${finIncomeVo.remark }
					             </label>
	                        </td>
	                    </tr>
	                </tbody>
	               </table>
	            </div>
	</div>
	
	
	<!-- 列表区域 -->
		<div class="page-box">
		  <div class="page-title">单据明细 </div>
			  <div class="p10">
			  	 <div id="zd-table-single">
				     <div id="receiptItem"> </div>
				 </div>
			  </div>
		</div>
	
	<div class="page-box">
			 <div class="page-title">审批结果</div>
				 <div class="p10">
					<form id="charge_confirm" class="zui-form" action="javascript:void(0);" zdata-options="{}">
						   			<table class="table-detail">
				                    	<tr>
					                        <td class="td-title pct10"><b class="c-red ">*</b>审批结果</td>
					                        <td class="pct20">
					                           <dl class="form-item ">
									                <dd class="detail">
									                   			 <input class="zui-checkbox zui-validatebox" name="status" type="hidden" data-multiple="false" data-data="[{'id':'2','text':'审批通过'},{'id':'3','text':'作废'},{'id':'4','text':'退回'}]" data-valuefield="id" data-textfield="text" validate-type="Require"  value="${(finIncomeVo.status=='1')?'':finIncomeVo.status}">
									                </dd>
									            </dl>
					                        </td>
				                        </tr>
			                       </table>
					</form>
				</div>
		</div>
	
</div>

<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.dialog','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.combobox','zd/jquery.zds.table'], function($, CALLBACK,ZTools) {
	
	var print_finIncome="<z:ukey key='com.zdsoft.finance.finIncome.printFinIncomeDetail' context='admin'/>";
	$("#btn-print1").on('click',function(){
		 ZDS_MESSAGE_CLIENT.openMenuLink('print_finIncome_tab', "打印收款单",print_finIncome+"&finIncomeId=${finIncomeVo.id}&caseApplyId=${finIncomeVo.caseApplyId}");
	});
	
	$("#btn-print2").on('click',function(){
		 $.ZMessage.info("提示", "未提供套打模板！");
    	  return;
	});
	
$("#td_receiptsDate").html(ZTools.strToDate("${finIncomeVo.receiptsDate}"));
	
	$.ZUI.init();
		//列表
		$('#receiptItem').ZTable({
		       url : "<z:ukey key='com.zdsoft.finance.finance.finIncomeDetail.list' context='admin'/>&finIncomeId|E|S=${finIncomeVo.id}",
		       singleSelect : true,
		       isRowNum : true,
		       pagination : false,
		       currentPage : 1,
		       idField: "id",
		       tableCls : 'table-index',//table的样式
		       columns:[[
		    	  	{field : 'feeTypeName',title : '收款类型',align : 'center'},
					{field : 'feeItemName',title : '收款项目',align : 'center'},
					{field : 'incomeSubjectText',title : '收款主体',align : 'center'},
					{field : 'expectedAmount',title : '应收金额(元)',align : 'center'},
					{field : 'receivedAmount',title : '已收金额(元)',align : 'center'},
					{field : 'paidAmount',title : '实收金额(元)',align : 'center'}
			] ],
    		onLoadSuccess:function(data) {
    			var totalExpectedAmount = 0;//收款总金额
    			var totalReceivedAmount = 0;//收入总金额
    			var totalCollectionAmount = 0;//代收总金额
    			var totalpredictAmount = 0;//预收总金额
    			
    			var rows=$('#receiptItem').ZTable("getRows");
     			if (typeof(rows)!="undefined"&&rows.length>0) {	
     				for(var e in rows){
     					totalExpectedAmount+=parseFloat(rows[e].paidAmount); 
     					if("YWDM0014608"==rows[e].feeType){
     						totalpredictAmount+=parseFloat(rows[e].paidAmount); 
     					}else if("YWDM0014606"==rows[e].feeType){
     						totalCollectionAmount+=parseFloat(rows[e].paidAmount); 
     					}else if("YWDM0014607"==rows[e].feeType){
     						totalReceivedAmount+=parseFloat(rows[e].paidAmount); 
     					}
     				}
     				
     				$("#totalExpectedAmount").html(totalExpectedAmount);
     				$("#totalReceivedAmount").html(totalReceivedAmount);
     				$("#totalCollectionAmount").html(totalCollectionAmount);
     				$("#totalpredictAmount").html(totalpredictAmount);
    			}
    		}
		});
		
		$("#btn-submit").click(function(){
				//校验
		        var validate = $.ZUI.validateForm($('#charge_confirm'));
		        if (!validate) {
		            return ;
		        }
		        
		        $.ajax({
					url:'<z:ukey key="com.zdsoft.finance.finance.finIncome.finIncomeFirmSave" context="admin"/>&businessKey=${finIncomeVo.id}',
					data: $('#charge_confirm').serialize(),
					type:"post",
					dataType:"json",
					success:function(data){
						if(data.resultStatus== 0){
		            		$.ZMessage.success("提示", "操作成功", function () {
			                	ZDS_MESSAGE_CLIENT.closeSelf();
		                    });
		             	}else{
		             		$.ZMessage.error("错误", "操作失败");
		             	}
					}
				});
		});
});
</script>
</body>
</html>