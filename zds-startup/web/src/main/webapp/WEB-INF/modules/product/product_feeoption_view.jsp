<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/common_js.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>机构产品费用项详情查看</title>
</head>
<body>
<div class="frm-content">
    <div class="page-box">
        <h1 class="page-title">详情</h1>
        <div class="p10">
            <table class="table-detail">
                <tr>
                    <td class="td-title pct10">收费类型：</td>
                    <td>${feeOption.feeTypeName}</td>
                    <td class="td-title pct10">收费项目：</td>
                    <td>${feeOption.feeItemName}</td>
                </tr>
                <tr>
                    <td class="td-title">收款计算方式：</td>
                    <td>${feeOption.chargeCalculateWayName}</td>
                    <c:if test="${feeOption.chargeCalculateWay == 'chargeCalculateWay1'}">
	                    <td class="td-title">收款金额(元)：</td>
	                    <td>${feeOption.chargeAmount}</td>
                    </c:if>
                    <c:if test="${feeOption.chargeCalculateWay == 'chargeCalculateWay2'}">
	                    <td class="td-title">收款比例：</td>
	                    <td>${feeOption.chargeRatio}${feeOption.chargeRatio == null ? '':'%' }</td>
                    </c:if>
                </tr>
                <tr>
                    <td class="td-title">付款计算方式：</td>
                    <td>${feeOption.payCalculateWayName}</td>
                    <c:if test="${feeOption.payCalculateWay == 'payCalculateWay1'}">
                    	<td class="td-title">付款金额(元)：</td>
                    	<td>${feeOption.payAmount}</td>
                    </c:if>
                    <c:if test="${feeOption.payCalculateWay == 'payCalculateWay2'}">
                    	<td class="td-title">付款比例：</td>
                    	<td>${feeOption.payRatio}${feeOption.payRatio == null ? '':'%' }</td>
                    </c:if>
                </tr>
                <tr>
                    <td class="td-title">营业收支：</td>
                    <td>${feeOption.isOperateIncome=="true"?"是":"否"}</td>
                    <td class="td-title">先请再付：</td>
                    <td>${feeOption.isPayFirst=="true"?"是":"否"}</td>
                </tr>
                <tr>
                    <td class="td-title">支付条件：</td>
                    <td>${feeOption.paymentTermsName}</td>
                    <td class="td-title">是否支佣：</td>
                    <td>${feeOption.isPayCommissionName=="true"?"是":"否"}</td>
                </tr>
                <tr>
                    <td class="td-title">佣金支付条件：</td>
                    <td>${feeOption.payConditionName}</td>
                    <td class="td-title">支佣节点：</td>
                    <td>${feeOption.point}</td>
                </tr>
                <tr>
                    <td class="td-title">是否停用：</td>
                    <td>${feeOption.isEnable=="true"?"是":"否"}</td>
                    <td class="td-title"></td>
                    <td></td>
                </tr>
                <tr>
                    <td class="td-title">备注：</td>
                    <td colspan="3">${feeOption.remark}</td>
                </tr>
            </table>
        </div>
    </div>
</div>

<script>
seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox', 'zd/jquery.zds.checkbox', 'zd/jquery.zds.loading', 'zd/switch',
	'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form',
	'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter', 'zd/bothselecter', 'zd/jquery.zds.button'],
	function ($, CALLBACK, Loading, Switch, DropDown, Filter, Check, Zdialog, ZUI_MESSAGE_CLIENT) {
	
	
	});
</script>
</body>
</html>