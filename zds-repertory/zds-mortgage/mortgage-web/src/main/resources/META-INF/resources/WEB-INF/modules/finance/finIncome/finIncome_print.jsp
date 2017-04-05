<%@ page language="java" contentType="text/html; charset=UTF-8" 	pageEncoding="UTF-8"%>
<%@ include file='../../common/common_js.jsp' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <title>print</title>
</head>
<body  id="body">


<div class="mr10" style="text-align: right; margin-right: 1px;">
				<button type="button" class="btn-search-blue" id="btn-print" >打印</button>
			</div>
			
			<div class="page-box"  style="height: 500px;">
			<div div class="p10" >
								
        <div style="width: 250px;margin: 0 auto;">
            <span style="font-size: 20px;">收费明细</span>
        </div>
        <div class="">
            <div style="height: 25px;">
            <div style="">
	           		 <span style="padding-right: 150px;">收款主体名称:</span>
	            	 <span style="padding-right: 10px;">收款日期:</span>
		             <span style="padding-left: 40px;">年</span>
		             <span style="padding-left: 30px;">月</span>
		             <span style="padding-left: 30px;">日</span>
             </div>
            </div>
            <table class="table-detail">
                <tr style="text-align: center;">
                    <td>客户编码</td>
                    <td></td>
                    <td>客户名称</td>
                    <td   colspan="3">${caseApply.customerName}</td>
                    <td  >贷款期限</td>
                    <td colspan="2" >  ${caseApply.applyTerm} ${caseApply.applyTermUnitName}   </td>
                </tr>
                <tr style="text-align: center;"> 
                    <td>业务员</td>
                    <td  style="width: 150px;">${caseApply.developmentManagerName} </td>
                    <td>贷款金额(元)</td>
                    <td> ${caseApply.applyAmount}</td>
                    <td>贷款类型</td>
                    <td>${caseApply.productSubtypeName}</td>
                    <td>贷款日期</td>
                    <td colspan="2">${caseApply.applyDate}</td>
                </tr>
                <tr  style="text-align: center;background: #FFF5EE;">
                    <td colspan="2">收费项目</td>
                    <td>收费项目性质</td>
                    <td>预计应收(元)</td>
                    <td>实收(元)</td>
                    <td>预计应付(元)</td>
                    <td colspan="3">支付对象</td>
                </tr>
                
                <c:forEach items="${detailList}" var="dt" >
	                
	                 <tr style="text-align: center;background: #FFFFF0;">
	                    <td colspan="2">${dt.feeTypeName}</td>
	                    <td  >${dt.feeItemName}</td>
	                    <td >${dt.expectedAmount}</td>
	                    <td >${dt.receivedAmount}</td>
	                    <td >${dt.expectedPayableAmount}</td>
	                    <td colspan="3">${dt.payObjectName}</td>
	                </tr>
                
                </c:forEach>
                
                <tr>
                    <td colspan="2">合计</td>
                    <td ></td>
                    <td  style="text-align: center;background: #FFFFF0;">${total_expectedAmount}</td>
                    <td  style="text-align: center;background: #FFFFF0;">${total_receivedAmount}</td>
                    <td  style="text-align: center;background: #FFFFF0;">${total_expectedPayableAmount}</td>
                    <td colspan="3"></td>
                </tr>
            </table>
            <div>
                <label>制单:</label><span style="padding-left:200px;padding-right: 50px;"></span>
                <label>财务负责人:</label><span style="padding-left: 80px;"></span>
            </div>
        </div>
    </div>
</div>

</body>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools'], function($, CALLBACK,ZTools) {
	$("#btn-print").on('click',function(){
		$("#btn-print").hide();
		 window.print();
		 $("#btn-print").show();
	});
});	
</script>
</html>