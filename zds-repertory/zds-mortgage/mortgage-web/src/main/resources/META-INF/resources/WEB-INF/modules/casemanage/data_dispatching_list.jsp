<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../common/common_js.jsp' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>资调派单</title>
</head>
<body>
<div class="frm-content frm-bottom" id="dataDispatching">
    <div class="page-box">
        <h1 class="page-title">基本信息</h1>
        <div class="p5">
            <table class="table-detail">
                <tr>
                    <td class="td-title pct15">案件号</td>
                    <td class="pct15">${basicInfoVo.caseApplyCode}</td>
                    <td class="td-title pct15">接单日期</td>
                    <td class="pct15">${basicInfoVo.applyDateStr }</td>
                    <td class="td-title pct15">子产品</td>
                    <td class="pct15">${basicInfoVo.productSubtypeName}</td>
                </tr>
                <tr>
                    <td class="td-title pct15">拓展经理</td>
                    <td class="pct15">${basicInfoVo.developmentManagerName}</td>
                    <td class="td-title pct15">拓展部门</td>
                    <td class="pct15">${basicInfoVo.developmentDepartmentName}</td>
                    <td class="td-title pct15">机构</td>
                    <td class="pct15">${basicInfoVo.mechanismName}</td>
                </tr>
                <tr>
                    <td class="td-title pct15">申请金额(元)</td>
                    <td class="pct15">${basicInfoVo.applyAmount}</td>
                    <td class="td-title pct15">贷款期限</td>
                    <td class="pct15">${basicInfoVo.applyDeadline}</td>
                    <td class="td-title pct15">还款方式</td>
                    <td class="pct15">${basicInfoVo.repayMethod}</td>
                </tr>
                <tr>
                    <td class="td-title pct15">贷款利率</td>
                    <td class="pct15">${basicInfoVo.applyRate}</td>
                    <td class="td-title pct15">逾期利率</td>
                    <td class="pct15">${basicInfoVo.overdueRate}</td>
                    <td class="td-title pct15">终端</td>
                    <td class="pct15">
                    	${empty cooperatorTerminalVo?"":cooperatorTerminalVo.cooperatorName }
                    </td>
                </tr>
                <tr>
                    <td class="td-title pct15">资金来源</td>
                    <td class="pct15">${basicInfoVo.capitalSource}</td>
                    <td class="td-title pct15"></td>
                    <td class="pct15"></td>
                    <td class="td-title pct15"></td>
                    <td class="pct15"></td>
                </tr>
            </table>
        </div>
        <h1 class="page-title">客户信息</h1>
        <div class="p5">
            <table class="table-detail">
                <tr>
                    <td class="td-title pct15">姓名</td>
                    <td class="pct15">${beforePersonalCustomerVo.customerName }</td>
                    <td class="td-title pct15">曾用名</td>
                    <td class="pct15">${beforePersonalCustomerVo.formerName }</td>
                    <td class="td-title pct15" rowspan="4"></td>
                    <td class="pct15" rowspan="4"></td>
                </tr>
                <tr>
                    <td class="td-title pct15">证件类型</td>
                    <td class="pct15">${beforePersonalCustomerVo.credentialTypeName }</td>
                    <td class="td-title pct15">证件号码</td>
                    <td class="pct15">${beforePersonalCustomerVo.credentialNo }</td>
                </tr>
                <tr>
                    <td class="td-title pct15">性别</td>
                    <td class="pct15">${beforePersonalCustomerVo.genderName }</td>
                    <td class="td-title pct15">出生日期</td>
                    <td class="pct15">${beforePersonalCustomerVo.birthdayDateStr }</td>
                </tr>
                <tr>
                    <td class="td-title pct15">年龄</td><!-- 没找到对应字段 -->
                    <td class="pct15">${beforePersonalCustomerVo.age }</td>
                    <td class="td-title pct15">婚况</td>
                    <td class="pct15">${beforePersonalCustomerVo.maritalStatusName }</td>
                </tr>
                <tr>
                    <td class="td-title pct15">职业类型</td>
                    <td class="pct15">${beforePersonalCustomerVo.careerTypeName }</td>
                    <td class="td-title pct15">教育程度</td>
                    <td class="pct15">${beforePersonalCustomerVo.degreeName }</td>
                    <td class="td-title pct15">居住年限</td>
                    <td class="pct15">${beforePersonalCustomerVo.liveAgeName }</td>
                </tr>
                <tr>
                    <td class="td-title pct15">邮箱地址</td>
                    <td class="pct15">${beforePersonalCustomerVo.email }</td>
                    <td class="td-title pct15">是否实际用款人</td>
                    <td class="pct15">${beforePersonalCustomerVo.actualUsePersonName }</td>
                    <td class="td-title pct15">参与类型</td>
                    <td class="pct15">${beforePersonalCustomerVo.joinTypeName }</td>
                </tr>
                
                <tr>
                    <td class="td-title">家庭地址</td>
                    <td colspan="5">${homeAddress.fullAddress }</td>
                </tr>
                <tr>
                    <td class="td-title">户籍地址</td>
                    <td colspan="5">${homeHoldAddress.fullAddress }</td>
                </tr>
            </table>
        </div>
                <!-- 配偶信息 -->
        <c:if test="${!empty  spouseVo }">
	       	<div class="p5" id="spouseInfo">
		        <h1 class="page-title">配偶信息</h1>
	            <table class="table-detail">
	                <tr>
	                    <td class="td-title pct15">配偶姓名</td>
	                    <td class="pct15">${spouseVo.customerName }</td>
	                    <td class="td-title pct15">曾用名</td>
	                    <td class="pct15">${spouseVo.formerName }</td>
	                    <td class="td-title pct15" rowspan="4"></td>
	                    <td class="pct15" rowspan="4">配偶头像</td>
	                </tr>
	                <tr>
	                    <td class="td-title pct15">证件类型</td>
	                    <td class="pct15">${spouseVo.credentialTypeName }</td>
	                    <td class="td-title pct15">证件号码</td>
	                    <td class="pct15">${spouseVo.credentialNo }</td>
	                </tr>
	                <tr>
	                    <td class="td-title pct15">邮箱地址</td>
	                    <td class="pct15">${spouseVo.email }</td>
	                    <td class="td-title pct15">职业类型</td>
	                    <td class="pct15">${spouseVo.careerTypeName }</td>
	                </tr>
	                <tr>
	                    <td class="td-title pct15">参与类型</td>
	                    <td class="pct15">${spouseVo.joinTypeName }</td>
	                    <td class="td-title pct15"></td>
	                    <td class="pct15"></td>
	                </tr>
	            </table>
	        </div>
        </c:if>
        <h1 class="page-title">联系方式</h1>
		<div class="p5">
			<div id="contactInfo" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.casemanage.getContactInfo" context="admin"/>&jsoncallback=?&customerId=${beforePersonalCustomerVo.id }","singleSelect":true,"pagination":false,"idField":"id","tableCls":"table-index","isRowNum":false}'>
				<table>
	       			<tr>
	           			<th data-options="field:customerName">姓名</th>
	           			<th data-options="field:contactTypeName">联系类型</th>
	           			<th data-options="field:phoneNumber">电话号码</th>
			        </tr>
				</table>
			</div>
        </div>
        <h1 class="page-title">工作单位信息</h1>
		<div class="p5">
			<div id="workUnitInfo" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.casemanage.getWorkUnitInfo" context="admin"/>&jsoncallback=?&customerId=${beforePersonalCustomerVo.id }","singleSelect":true,"pagination":false,"idField":"id","tableCls":"table-index","isRowNum":false}'>
				<table>
	       			<tr>
	           			<th data-options="field:workUnitName">姓名</th>
	           			<th data-options="field:companyName">工作单位名称</th>
	           			<th data-options="field:industryTypeName">行业类型</th>
	           			<th data-options="field:industryName">行业</th>
	           			<th data-options="field:workUnitNatureName">单位性质</th>
	           			<th data-options="field:positionName">职务</th>
	           			<th data-options="field:workYears">工作年限</th>
	           			<th data-options="field:phoneNumber">工作单位电话</th>
	           			<th data-options="field:workUnitAddressName">单位地址</th>
			        </tr>
				</table>
			</div>
        </div>
        <h1 class="page-title">押品信息</h1>
        <div class="p5">
            <table class="table-detail">
                <tr>
                    <td class="td-title pct15">小区名称</td>
                    <td class="pct15">${housePropertyVo.communityName}</td>
                    <td class="td-title pct15">所在楼层</td>
                    <td class="pct15">${housePropertyVo.placeFloor}</td>
                    <td class="td-title pct15">总层数</td>
                    <td class="pct15">${housePropertyVo.sumFloor}</td>
                </tr>
                <tr>
                    <td class="td-title pct15">楼龄</td>
                    <td class="pct15">${housePropertyVo.floorAge}</td>
                    <td class="td-title pct15">面积</td>
                    <td class="pct15">${housePropertyVo.area}</td>
                    <td class="td-title pct15">房产性质</td>
                    <td class="pct15">${housePropertyVo.estatePropertiesName}</td>
                </tr>
                <tr>
                    <td class="td-title pct15">房产权属</td>
                    <td class="pct15">${housePropertyVo.estateOwnershipName}</td>
                    <td class="td-title pct15">中介询价</td>
                    <td class="pct15">${housePropertyVo.intermediaryInquiry}</td>
                    <td class="td-title pct15">网络询价</td>
                    <td class="pct15">${housePropertyVo.networkInquiry}</td>
                </tr>
                <tr>
                    <td class="td-title pct15">风控核定价</td>
                    <td class="pct15">${housePropertyVo.controlPrice}</td>
                    <td class="td-title pct15">评估价</td>
                    <td class="pct15">${housePropertyVo.evaluatingPrice}</td>
                    <td class="td-title pct15">是否有装修</td>
                    <td class="pct15">
                    	${housePropertyVo.isRenovation?"是":"否" }
                    </td>
                </tr>
                <tr>
                    <td class="td-title pct15">是否有电梯</td>
                    <td class="pct15">
                    	${housePropertyVo.isElevator?"是":"否" }
                    </td>
                    <td class="td-title pct15">不动产证号</td>
                    <td class="pct15">${housePropertyVo.houseNo}</td>
                    <td class="td-title pct15">综合评估价</td>
                    <td class="pct15">${housePropertyVo.synthesizePrice}</td>
                </tr>
                <tr>
                    <td class="td-title pct15">押品地址</td>
                    <td colspan="5">${housePropertyVo.provinceName}${housePropertyVo.cityName}${housePropertyVo.districtName}${housePropertyVo.mailingAddress}</td>
                </tr>
                 <tr>
                    <td class="td-title pct15">抵押情况(第N抵)</td>
                    <td class="pct15">${housePropertyVo.mortgageSituationName}</td>
                    <td class="td-title pct15"></td>
                    <td class="pct15"></td>
                    <td class="td-title pct15"></td>
                    <td class="pct15"></td>
                </tr>
            </table>
        </div>
        <h1 class="page-title">抵押情况</h1>
		<div class="p5">
			<div id="mortgageInfo" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.casemanage.getMortgageInfoList" context="admin"/>&jsoncallback=?&houseId=${housePropertyVo.id }","singleSelect":true,"pagination":false,"idField":"id","tableCls":"table-index","isRowNum":false}'>
				<table >
	       			<tr>
	           			<th data-options="field:pledgeTypeName">抵押顺位</th>
	           			<th data-options="field:loanBank">贷款银行</th>
	           			<th data-options="field:deadline">贷款期限</th>
	           			<th data-options="field:typeName">类型</th>
	           			<th data-options="field:loanBalance">现贷款余额</th>
	           			<th data-options="field:pledgeAmout">抵押金额</th>
	           			<th data-options="field:percentage">成数</th>
			        </tr>
				</table>
			</div>
        </div>
        <h1 class="page-title">征信信息</h1>
        <div class="p5">
            <table class="table-detail">
                <tr>
                    <td class="td-title pct15">贷款种类</td>
                    <td class="pct15">xxxxxx</td>
                    <td class="td-title pct15">账户状态</td>
                    <td class="pct15">xxxxxx</td>
                    <td class="td-title pct15">贷款发放日期</td>
   			        <td class="pct15">xxxxxx</td>
                </tr>
                <tr>
                    <td class="td-title pct15">贷款年限</td>
                    <td class="pct15">xxxxxx</td>
                    <td class="td-title pct15">贷款金额</td>
                    <td class="pct15">xxxxxx</td>
                    <td class="td-title pct15">贷款余额</td>
                    <td class="pct15">xxxxxx</td>
                </tr>
                <tr>
                    <td class="td-title pct15">当前逾期期数</td>
                    <td class="pct15">xxxxxx</td>
                    <td class="td-title pct15">当前逾期金额</td>
                    <td class="pct15">xxxxxx</td>
                    <td class="td-title pct15">累计逾期期数</td>
                    <td class="pct15">xxxxxx</td>
                </tr>
                <tr>
                    <td class="td-title pct15">最高逾期期数</td>
                    <td class="pct15">xxxxxx</td>
                    <td class="td-title pct15"></td>
                    <td class="pct15"></td>
                    <td class="td-title pct15"></td>
                    <td class="pct15"></td>
                </tr>
                <tr>
                    <td class="td-title pct15">最近12个月还款记录</td>
                    <td colspan="5">xxxxxx</td>
                </tr>
                <tr>
                    <td class="td-title pct15">最近5年内</td>
                    <td colspan="5">xxxxxx</td>
                </tr>
                <tr>
                    <td class="td-title pct15" style="height:50px">逾期情况及请他情况说明</td>
                    <td class="pct15" colspan="5">xxxxxx</td>
                </tr>
                <tr>
                    <td class="td-title pct15">人行报告打印日期</td>
                    <td class="pct15">xxxxxx</td>
                    <td class="td-title pct15">人行报告1个月内查询次数</td>
                    <td class="pct15">xxxxxx</td>
                    <td class="td-title pct15"></td>
                    <td class="pct15">xxxxxx</td>
                </tr>
                <tr>
                    <td class="td-title pct15" style="height:50px">征信情况综合评价</td>
                    <td class="pct15"  colspan="5">xxxxxx</td>
                </tr>
            </table>
        </div>
        <h1 class="page-title">本人信用卡情况</h1>
		<div class="p5">
			<div id="creditCardInfo" class="p5" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.casemanage.getCreditCardInfo" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":false,"idField":"id","tableCls":"table-index","isRowNum":false}'>
				<table class="table=index">
	       			<tr>
	           			<th data-options="field:loanBank">信用卡名称</th>
	           			<th data-options="field:period">信用额度(元)</th>
	           			<th data-options="field:amount">账户状态</th>
	           			<th data-options="field:pledgePick">当前逾期总额(元)</th>
	           			<th data-options="field:detailNames">当前透支额(元)</th>
	           			<th data-options="field:detailCollectionTypes">抵押金额</th>
			        </tr>
				</table>
			</div>
			<div class="zd-form">
				<table class="table-index">
					<tr>
			        	<td data-options="field:detailStocks" style="text-align: left;" colspan="6" >最近12个月还款状态记录:累计逾期0次,最高逾期0次</td>
			        </tr>
			        <tr>
			        	<td data-options="field:detailCostMonths" style="text-align: left;" colspan="6">信用卡情况综合评价:</td>
			        </tr>	
				</table>
			</div>
        </div>
        <h1 class="page-title">汇发网信息</h1>
        <div class="p5">
            <table class="table-detail">
                <tr>
                    <td class="td-title pct15">主借人名称</td>
                    <td class="pct15">xxxxxx</td>
                    <td class="td-title pct15">标题</td>
                    <td class="pct15">xxxxxx</td>
                    <td class="td-title pct15">被执行人姓名或名称</td>
                    <td class="pct15">xxxxxx</td>
                </tr>
                <tr>
                    <td class="td-title pct15">证件号码</td>
                    <td class="pct15">xxxxxx</td>
                    <td class="td-title pct15">执行法院</td>
                    <td class="pct15">xxxxxx</td>
                    <td class="td-title pct15">执行案号</td>
                    <td class="pct15">xxxxxx</td>
                </tr>
                <tr>
                    <td class="td-title pct15">执行内容</td>
                    <td class="pct15">xxxxxx</td>
                    <td class="td-title pct15">执行标的</td>
                    <td class="pct15">xxxxxx</td>
                    <td class="td-title pct15">立案时间</td>
                    <td class="pct15">xxxxxx</td>
                </tr>
                <tr>
                    <td class="td-title pct15">执行状态</td>
                    <td class="pct15">xxxxxx</td>
                    <td class="td-title pct15">异议备注</td>
                    <td class="pct15">xxxxxx</td>
                    <td class="td-title pct15">身份证号码</td>
                    <td class="pct15">xxxxxx</td>
                </tr>
                <tr>
                    <td class="td-title pct15">组织机构代码</td>
                    <td class="pct15">xxxxxx</td>
                    <td class="td-title pct15">案件状态</td>
                    <td class="pct15">xxxxxx</td>
                    <td class="td-title pct15">更新时间</td>
                    <td class="pct15">xxxxxx</td>
                </tr>
            </table>
        </div>
        <h1 class="page-title">房产评估信息</h1>
		<div class="p5">
			<div id="evaluatingInfo" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.casemanage.getEvaluatingInfo" context="admin"/>&jsoncallback=?&housePropertyId=${housePropertyVo.id}","singleSelect":true,"pagination":false,"idField":"id","tableCls":"table-index","isRowNum":false}'>
				<table class="table-index">
	       			<tr>
	           			<th data-options="field:evaluationCompany">评估公司</th>
	           			<th data-options="field:evaluationPrice">评估价格(元)</th>
	           			<th data-options="field:evaluationDate">评估时间</th>
			        </tr>
				</table>
			</div>
        </div>
        <h1 class="page-title">关联贷款信息</h1>
		<div class="p5">
			<div id="relatedLoanInfo" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.casemanage.getRelatedLoanInfo" context="admin"/>&jsoncallback=?&customerId=${beforePersonalCustomerVo.id}","singleSelect":true,"pagination":false,"idField":"id","tableCls":"table-index"}'>
				<table >
	       			<tr>
	           			<th data-options="field:caseApplyCode">案件号</th>
	           			<th data-options="field:customerName">客户姓名</th>
	           			<th data-options="field:joinType">参与类型</th>
	           			<th data-options="field:floorAge">楼龄(年)</th>
	           			<th data-options="field:area">面积(平米)</th>
	           			<th data-options="field:">购买总价(元)</th>
	           			<th data-options="field:pledgeType">抵押类型</th>
	           			<th data-options="field:estateProperties">房产性质</th>
	           			<th data-options="field:detailNames">贷款金额(元)</th>
	           			<th data-options="field:">逾期金额(元)</th>
	           			<th data-options="field:">逾期时间</th>
	           			<th data-options="field:">逾期天数(天)</th>
			        </tr>
				</table>
			</div>
        </div>
        <h1 class="page-title">工商信息</h1>
        <div class="p5">
            <table class="table-detail">
                <tr>
                    <td class="td-title pct15">统一社会信用代码/注册号</td>
                    <td class="pct15">xxxxxx</td>
                    <td class="td-title pct15">名称</td>
                    <td class="pct15">xxxxxx</td>
                    <td class="td-title pct15">类型</td>
                    <td class="pct15">xxxxxx</td>
                </tr>
                <tr>
                    <td class="td-title pct15">法定代表人</td>
                    <td class="pct15">xxxxxx</td>
                    <td class="td-title pct15">注册资本</td>
                    <td class="pct15">xxxxxx</td>
                    <td class="td-title pct15">成立日期</td>
                    <td class="pct15">xxxxxx</td>
                </tr>
                <tr>
                    <td class="td-title pct15">地址</td>
                    <td class="pct15">xxxxxx</td>
                    <td class="td-title pct15">营业期限自</td>
                    <td class="pct15">xxxxxx</td>
                    <td class="td-title pct15">营业期限至</td>
                    <td class="pct15">xxxxxx</td>
                </tr>
                <tr>
                    <td class="td-title pct15" style="height:50px">经营范围</td>
                    <td class="pct15" colspan="5" style="height:50px">xxxxxx</td>
                </tr>
                <tr>
                    <td class="td-title pct15">登记机关</td>
                    <td class="pct15">xxxxxx</td>
                    <td class="td-title pct15">核准日期</td>
                    <td class="pct15">xxxxxx</td>
                    <td class="td-title pct15">登记状态</td>
                    <td class="pct15">xxxxxx</td>
                </tr>
            </table>
        </div>
        <form id="chooseEmp" class="zui-form form-search" method="post" enctype="multipart/form-data">
       	<input type="hidden" id="caseApplyId" name="caseApplyId" value="${businessKey}" />
		<h1 class="page-title">选择资信员</h1>
        <div class="p5">
            <table class="table-detail">
                <tr>
                    <td class="td-title pct15">资信员</td>
                    <td class="pct15">
                    	<dl class="form-item form-auto">
                             <dd class="detail">
                                 <label>
                                 	<input id="empId" name="empId" type="hidden" value="${empty caseTaskVo?'':caseTaskVo.id }" />
                                 	<input id="empCode" name="empCode" type="hidden" value="${empty caseTaskVo?'':caseTaskVo.taskPersonnelCode }"/>
                                 	<input id="empName" name="empName" type="text" value="${empty caseTaskVo?'':caseTaskVo.taskPersonnelName}" class="zui-input" readonly/>
                                 	<button class="btn-blue" type="button" id="taskPersonnel">选择<tton>
                                 </label>
                             </dd>
                         </dl>
                    </td>
                    <td class="td-title pct15"></td>
                    <td class="pct15"></td>
                    <td class="td-title pct15"></td>
                    <td class="pct15"></td>
                </tr>
                <tr>
                    <td class="td-title pct15" rowspan="2">说明</td>
                    <td class="pct15" colspan="5">
	                   	<label>
                            <textarea id="remark" class="zui-area" name="remark" placeholder="最多可以输入200个字符" style="width:100%"></textarea>
	                    </label>
                    </td>
                </tr>
            </table>
        </div>
        </form>
    </div>
</div>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK,ZTOOLS) {
	// 人员选择器
	$("#taskPersonnel").Zseleter({
		title: '选择器',
		btnId: "select",
		width: 900,
		height: 400,
		key: "empCd",
		value: "empNm",
		singleSelect: true,
		columns: {
			test: [[
				{field: 'empNm', title: '姓名', width: 80},
				{
					field: 'empTypeNm', title: '岗位', width: 80, align: 'right', formatter: function (r, v) {
					return "<span class='c-blue'>" + v + "</span>";
				}
				},
				{field: 'gendar', title: '员工编号', width: 80},
				{
					field: 'orgNm', title: '部门名称', width: 60, align: 'center', formatter: function (r, v) {
					return v;
				}
				}
			]]
		},
		url:"<z:res resource='essential.comm.employees.select' isDefault='true'/>&jsoncallback=?",
		type: 'test',
		defSearchForm: {
			test: [
				{
					label: "姓名",
					type: "input",
					name: "empNm"
				}
			]
		},
		onOk:function(data){
			$('#empCode').val(data[0]._data.empCd);
			$('#empName').val(data[0]._data.empNm);
		}
	});
	$.ZUI.init();
	//数据校验 
	var validateApplayInfo = function(){
		if(!$('#empCode').val() && !$('#empName').val()){
			return false;
		}
		return true;
	}
	 //保存
    ZDS_WORKFLOW_CLIENT.saveFunction = function (datas) {
    	var WORKFLOW_FLAG=ZDS_WORKFLOW_PARAM._STATUS_VALIDATE_ERROR;//1、提交，需要，默认提交失败！
    	//验证数据
        if (validateApplayInfo()) {
        	var args = JSON.parse(datas);
        	var params ='&processInstanceId=' + args.processInstanceId;
	        	params += '&taskInstanceId=' + args.taskInstanceId;
	        	params += '&taskId=' + args.taskId;
	        	params += '&taskName=' + args.taskName;
	        	params += '&jobAppCd=' + args.jobAppCd;
	        	params += '&businessKey=${businessKey}';
	        	params += '&projectId=${projectId}';
	        	params +="&" + $('#chooseEmp').serialize();
              	$.ajax({
                   url: '<z:ukey key="com.zdsoft.finance.casemanage.saveDispatchInfo" context="admin"/>&jsoncallback=?',
                   type: "post",
                   dataType: "json",
                   async:false,
                   data: params,
                   success: function (msg) {
                       if (msg != null) {
                           if (msg.resultStatus == "0") {
                        	   $('#empId').val(${msg.id});
                        	   $('#empCode').val(${msg.optional.empCode});
                        	   $('#empName').val(${msg.optional.empName});
                               WORKFLOW_FLAG = ZDS_WORKFLOW_PARAM._STATUS_SUCCESS; 
                           } else {
                               $.ZMessage.warning("错误", "操作失败！" + msg.msg);
                           }
                       } else {
                           $.ZMessage.warning("错误", "操作失败！");
                       }
                   }
               });
        } else {
        	return;
        }
        return WORKFLOW_FLAG;
	}
    //提交方法
    ZDS_WORKFLOW_CLIENT.submitFunction = ZDS_WORKFLOW_CLIENT.saveFunction;
	
});
</script> 
</body>
</html>