<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>

<div class="frm-content"  id="settlement_div" style="display: none;">
	<!-- 表单 -->
	<form id="receiptForm" class="zui-form" action="javascript:void(0);" zdata-options="{}">
        <div class="page-box">
<!--             <div class="page-title">收款单</div> -->
            <div class="mr10" style="text-align: right; margin-right: 1px;;">
				<button type="button" class="btn-search-blue" id="btn-print"  >打印</button>
			</div>
            <div class="p5">
                <table class="table-detail">
                    <tbody>
                    <tr>
                        <td class="td-title pct10">案件号：</td>
                        <td class="pct20">
                            ${caseApply.caseApplyCode }
                        </td>
                        <td class="td-title pct10">主借人：</td>
                        <td class="pct30">
                            ${caseApply.customerName}
                        </td>
                    </tr>
                    <tr>
                        <td class="td-title pct10">子产品：</td>
                        <td class="pct20">
                             ${caseApply.productSubtypeName }
                        </td>
                        <td class="td-title pct10">拓展部门：</td>
                        <td class="pct30">
                            ${caseApply.developmentDepartmentName }
                        </td>
                    </tr>
                    <tr>
                        <td class="td-title pct10">贷款金额：</td>
                        <td class="pct20">
                            ${caseApply.applyAmount }
                        </td>
                        <td class="td-title pct10">贷款期限：</td>
                        <td class="pct30">
                            ${caseApply.applyTerm }  ${caseApply.applyTermUnitName}
                        </td>
                    </tr>
<!--                     <tr> -->
<!--                         <td class="td-title pct10">贷款日期</td> -->
<!--                         <td class="pct20"> -->
<%--                              ${caseApplyVo.applyDateStr } --%>
<!--                         </td> -->
<!--                         <td class="td-title pct10">结算日期</td> -->
<!--                         <td class="pct30"> -->
                            
<!--                         </td> -->
<!--                     </tr> -->
<!--                     <tr> -->
<!--                         <td class="td-title pct10">预计回款款日期</td> -->
<!--                         <td class="pct20"> -->
                            
<!--                         </td> -->
<!--                         <td class="td-title pct10">实际回款日期</td> -->
<!--                         <td class="pct30"> -->
                            
<!--                         </td> -->
<!--                     </tr> -->
                    <tr>
                        <td class="td-title pct10">实际垫资天数：</td>
                        <td class="pct20">0</td>
                        <td class="td-title pct10">逾期天数：</td>
                        <td class="pct30">0</td>
                    </tr>
                </tbody>
               </table>
            </div>
        </div>
    </form>
    
	<!-- 列表区域 -->
	<div class="page-box">
	  <div class="page-title">各项费用明细
	  </div>
	  <div class="p10">
	  	<div id="zd-table-single">
	      <div id="receiptItemdd">
	      	
	      </div>
	  </div>
	</div>
</div>

<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.dialog','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.combobox','zd/jquery.zds.table'], function($, CALLBACK,ZTools) {
	
	$("#settlement_div").Zdialog({
		width : 800,
		height : 600,
		closed : true,
		isDestroy: true,
		title:"业务结算单",
		buttons : [{
			id : 'message-btn',
			text : '确定',
			buttonCls: 'btn-blue',
			handler : function() {
					var caseApplyId = $("#caseApplayId").val();
					$.ajax({
			            type: 'post',
			            url: '<z:ukey key="com.zdsoft.finance.finance.case.costSettlemenetSave" context="admin"/>',
			            data: 'caseApplyId=${caseApply.id}&status=0',
			            dataType: 'json',
			            success: function (data) {
			            	if(data.resultStatus== 0){
			            		$.ZMessage.success("提示", data.msg, function () {});
			            	}else{
			            		$.ZMessage.error("提示", data.msg, function () {});
			            	}
			            	
			            }
			        });
			}
		},{
			id : 'message-btn',
			text : '取消',
			buttonCls : 'btn-gray',
			handler : function() {
				$("#settlement_div").Zdialog("close");
			}
		} ]
	});
	$("#settlement_div").Zdialog("open");
	
	//列表
	$('#receiptItemdd').ZTable({
	       url : "<z:ukey key='com.zdsoft.finance.casemanage.findFeeInfomationByCaseApplyId' context='admin'/>&caseApplyId=${caseApply.id}",
	       singleSelect : true,
	       isRowNum : true,
	       pagination : false,
	       idField: "id",
	       tableCls : 'table-index',//table的样式
	       columns:[[
	    	  	{field : 'feeTypeName',title : '费用大类',align : 'center'},
				{field : 'feeItemName',title : '费用类型',align : 'center'},
				{field : 'receivedAmount',title : '实收费用',align : 'center'},
				{field : 'paidAmount',title : '实付金额',align : 'center'},
				{field : 'balanceAmount',title : '结余',align : 'center'}
		] ]
	});
 

	
	$("#btn-print").click(function(){
		 var print_caseApply_fee="<z:ukey key='com.zdsoft.finance.finIncome.printFinIncome' context='admin'/>";
		 var rows=$('#receiptItemdd').ZTable("getRows");
		 var feeIds="";
     	 for(var e in rows){
     		feeIds+=rows[e].id+",";
     	 }
		 ZDS_MESSAGE_CLIENT.openMenuLink('print_caseApply_fee_tab1', "打印案件费用明细",print_caseApply_fee+"&caseApplyId=${caseApply.id}&feeIds="+feeIds);
		$("#settlement_div").Zdialog("close");
	});
});
</script>
