<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>


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
	                        <td id="p_td_receiptsDate">${finIncomeVo.receiptsDate} </td>
	                        <td class="td-title" >收款总金额(元)：</td>
	                        <td id="totalExpectedAmount"></td>
	                        <td class="td-title" >收入总金额(元)：</td>
	                        <td id="totalReceivedAmount"> </td>
	                    </tr>
	                    <tr>
	                        <td class="td-title">代收总金额(元)：</td>
	                        <td>0</td>
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

<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.dialog','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.table'], 
		function($, CALLBACK,ZTools) {
	
	$("#p_td_receiptsDate").html(ZTools.strToDate("${finIncomeVo.receiptsDate}"));
	
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
		
		
});

</script>
