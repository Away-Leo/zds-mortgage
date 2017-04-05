<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%> 
<!-- 案件信息 -->
	<div class="page-box">  
				<div class="page-title">案件信息</div>
				<div class="p5">       
				     <table class="table-detail">
				           <tr>
				               <td class="td-title pct10">案件号</td>
				               <td class="pct20">
				                   <dl class="form-item form-auto">
				                          <dd class="detail f12"><label id="caseApplyCode"></label></dd>
				                   </dl>
				               </td>
				               <td class="td-title pct10">机构</td>
				               <td class="pct20">
				                   <dl class="form-item form-auto">
				                       <dd class="detail f12"><label id="mechanismName"></label></dd>
				                   </dl>
				               </td>
				               <td class="td-title pct10">子产品</td>
				               <td class="pct20">
				                   <dl class="form-item form-auto">
				                       <dd class="detail f12"><label id="productName"></label></dd>
				                   </dl>
				               </td>
				           </tr>
				           <tr>
				               <td class="td-title pct10">放款金额</td>
				               <td class="pct20">
				                   <dl class="form-item form-auto">
				                          <dd class="detail f12"><label id="loanApplyAnount"></label></dd>
				                   </dl>
				               </td>
				               <td class="td-title pct10">贷款期限</td>
				               <td class="pct20">
				                   <dl class="form-item form-auto">
				                       <dd class="detail f12"><label id="applyTerm"></label></dd>
				                   </dl>
				               </td>
				               <td class="td-title pct10">本金余额</td>
				               <td class="pct20">
				                   <dl class="form-item form-auto">
				                       <dd class="detail f12"><label id="caseApplyBalance"></label></dd>
				                   </dl>
				               </td>
				           </tr>
				           <tr>
				               <td class="td-title pct10">逾期天数</td>
				               <td class="pct20">
				                   <dl class="form-item form-auto">
				                          <dd class="detail f12"><label id="day"></label></dd>
				                   </dl>
				               </td>
				               <td class="td-title pct10"></td>
				               <td class="pct20">
				                   <dl class="form-item form-auto">
				                       <dd class="detail f12"><label></label></dd>
				                   </dl>
				               </td>   
				               <td class="td-title pct10"></td>
				               <td class="pct20">
				                   <dl class="form-item form-auto">
				                       <dd class="detail f12"><label id="number"></label></dd>
				                   </dl>
				               </td>
				           </tr>
				          
				     </table>
				</div>
			</div>
    
<script type="text/javascript">  
	
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], 
		function($, CALLBACK) {   
		$.ajax({
            type: 'post',
            url: '<z:ukey key="com.zdsoft.finance.afterloan.afterSupervise.findCaseApplyBaseMsg" context="admin"/>',
            data: {caseApplyId:'${caseApplyId}'},
            dataType: 'json',
            success: function (data) {
                if (data.resultStatus == 0) {
                	//案件号
             	 $("#caseApplyCode").text(data.optional.caseApplyVo.caseApplyCode);
                	//机构名称
             	 $("#mechanismName").text(data.optional.caseApplyVo.mechanismName);
                	//产品
             	 $("#productName").text(data.optional.caseApplyVo.productSubtypeName);
                	//放款金额
             	 $("#loanApplyAnount").text(data.optional.caseApplyVo.loanApplyAnount+"元");
                	//贷款期限
             	 $("#applyTerm").text(data.optional.caseApplyVo.applyTerm+data.optional.caseApplyVo.applyTermUnitName);
                	//本金余额
             	 $("#caseApplyBalance").text(data.optional.caseApplyVo.caseApplyBalance+"元");
                	//逾期天数
                	if(data.optional.customerReceivable){   
             		var day = data.optional.customerReceivable.days;
                	if(day){
                		$("#day").text(day);
                	}
                	}
                }else{
                       	$.ZMessage.error("错误", "获取案件基本信息失败", function () {
                        });
                }
            },
            error: function () {
            	$.ZMessage.error("错误", "保存信息系统异常，请联系管理员", function () {
             });
            }
        });    
		$.ZUI.init();
	});
</script>
