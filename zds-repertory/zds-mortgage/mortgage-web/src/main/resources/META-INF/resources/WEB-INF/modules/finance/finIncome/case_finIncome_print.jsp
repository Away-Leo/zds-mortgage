<%@ page language="java" contentType="text/html; charset=UTF-8" 	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file='../../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%--  	<script type="text/javascript" src="<%=localServer %>/print/jquery-1.11.2.min.js"></script> --%>
<%--     <script type="text/javascript" src="<%=localServer %>/print/jquery-migrate-1.2.1.min.js"></script> --%>
<%--     <script type="text/javascript" src="<%=localServer %>/print/jquery.jqprint-0.3.js"></script> --%>
    <meta charset="UTF-8">
    <title>print</title>
    <link rel="stylesheet" type="text/css" href="<%=localServer %>/print/style.css"/>
</head>
<body >
<div class="frm-content">
<div class="mr10" style="text-align: right; margin-right: 1px;;">
				<button type="button" class="btn-search-blue" id="btn-print" >打印</button>
			</div>
    <div style="margin: 0 auto;width:900px;height: 500px;" >
        <div style="width: 250px;margin: 0 auto;">
            <span style="font-size: 25px;">收费明细</span>
        </div>
        <div class="">
            <div style="height: 25px;"><div style=""><span style="padding-right: 150px;">收款主体名称:</span> <span style="padding-right: 10px;">收款日期:</span><span style="padding-left: 40px;">年</span><span style="padding-left: 30px;">月</span><span style="padding-left: 30px;">日</span></div></div>

            <table >
                <tr>
                    <td>客户编码</td>
                    <td>${caseApply.currentApplierName}</td>
                    <td>客户名称</td>
                    <td  >${caseApply.caseApplyCode}</td>
                    <td  ></td>
                    <td  ></td>
                    <td  >贷款期限</td>
                    <td colspan="2" >  ${caseApply.applyTerm} ${caseApply.applyTermUnitName}   </td>
                </tr>
                <tr>
                    <td>业务员</td>
                    <td > </td>
                    <td>贷款额</td>
                    <td> ${caseApply.applyAmount}</td>
                    <td>贷款类型</td>
                    <td>${caseApply.productSubtypeName}</td>
                    <td>贷款日期</td>
                    <td colspan="2"></td>
                </tr>
                <tr>
                    <td colspan="2">收费项目</td>
                    <td>收费项目性质</td>
                    <td>预计应收</td>
                    <td>实收</td>
                    <td>预计应付</td>
                    <td colspan="3">支付对象</td>
                </tr>
                
                <c:forEach items="${feeList}" var="dt" >
	                
	                 <tr>
	                    <td colspan="2">${dt.feeItem}</td>
	                    <td  >${dt.incomeType}</td>
	                    <td >${dt.paidAmount}</td>
	                    <td >${dt.paidAmount}</td>
	                    <td >${dt.paidAmount}</td>
	                    <td colspan="3">${dt.incomeSubject}</td>
	                </tr>
                
                </c:forEach>
                
                <tr>
                    <td colspan="2">合计</td>
                    <td  ></td>
                    <td ></td>
                    <td ></td>
                    <td ></td>
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
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.dialog','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.combobox','zd/jquery.zds.table'], function($, CALLBACK,ZTools) {
	$("#btn-print").on('click',function(){
		$("#btn-print").hide();
		 window.print();
		 $("#btn-print").show();
	});
});	
</script>
</html>