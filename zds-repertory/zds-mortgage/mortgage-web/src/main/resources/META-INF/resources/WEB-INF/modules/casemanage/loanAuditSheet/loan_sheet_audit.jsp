<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../../common/common_js.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>贷审审核单</title>
</head>
<body>
	<div id="frm-content">
		<form class="zui-form form-search" action="javascript:void(0);"
			zdata-options={"url":""}>

			<div class="page-box">
				<h1 class="page-title">基本信息</h1>
				<div class="p10">
					<table class="table-detail">
						<tbody>
							<tr>
								<th class="pct10">案件号</th>
								<td>${caseApply.id }</td>
								<th class="pct10">机构</th>
								<td>${caseApply.mechanismName }</td>
								<th class="pct10">主借人</th>
								<td>${mainCustomerName}</td>
							</tr>
							<tr>
								<th class="pct10">贷款金额</th>
								<td>${caseApply.applyAmount }元</td>
								<th class="pct10">贷款期限</th>
								<td>${caseApply.applyDeadline }&nbsp;${caseApply.applyDeadlineUnitName }</td>
								<th class="pct10">子产品</th>
								<td>${caseApply.productSubtypeName }</td>
							</tr>
							<tr>
								<th class="pct10">综合利率</th>
								<td>${caseApply.applyRate }/年</td>
								<th class="pct10">贷款月供</th>
								<td></td>
								<th class="pct10"></th>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!-- 列表区域 -->
			<div class="page-box">
				<div class="page-title">主借人、共借人、担保人基本情况</div>
				<div class="p10">
					<div id="tb-customer" class="table-index">
						<table>
							<thead>
								<tr>
									<th>参与类型</th>
									<th>姓名</th>
									<th>性别</th>
									<th>年龄</th>
									<th>婚况</th>
									<th>户籍地址</th>
									<th>与主借人关系</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${beforeCustomerList}" var="d" varStatus="v">
									<tr>
										<td>${d.joinTypeName}</td>
										<td>${d.customerName}</td>
										<td>${d.genderName}</td>
										<td>${d.age}</td>
										<td>${d.maritalStatusName}</td>
										<td>${d.homeAddress}</td>
										<td>${d.relationshipName}</td>
									<tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>

			<div class="page-box">
				<div class="page-title">偿债能力</div>
				<div class="p10">
					<div id="tb-solvency">
						<table class="table-index">
							<thead>
								<tr>
									<th width="20%">类型</th>
									<th>详情</th>
								</tr>
								<tr>
									<td>经营情况/工作情况</td>
									<td></td>
								</tr>
								<tr>
									<td>资产情况</td>
									<td></td>
								</tr>
								<tr>
									<td>征信情况</td>
									<td></td>
								</tr>
								<tr>
									<td>其他</td>
									<td></td>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>

			<!-- 列表区域 -->
			<div class="page-box">
				<div class="page-title">抵押物情况</div>
				<div class="p10">
					<div id="tb-collateral-base" class="table-index">
						<table>
							<thead>
								<tr>
									<th>抵押物</th>
									<th>地址</th>
									<th>类型</th>
									<th>面积</th>
									<th>单价(元/㎡）</th>
									<th>综合评估价/元</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${housePropertyList}" var="item" varStatus="s">
									<tr>
										<td>抵押物${s.index+1 }</td>
										<td>${item.provinceName }${item.cityName }${item.districtName }${item.mailingAddress }</td>
										<td>${item.estatePropertiesName }</td>
										<td>${item.area }</td>
										<td>${(!empty item.evaluatingPrice && item.evaluatingPrice!=0 && !empty item.area && item.area>0)? item.evaluatingPrice/item.area:""  }</td>
										<td>${item.evaluatingPrice }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>

			<!-- 列表区域 -->
			<div class="page-box">
				<div class="p10">
					<div id="tb-mortgage-situation">
						<table class="table-index">
							<thead>
								<tr>
									<!--抵押物名称在界面显示为“抵押物+序号”，如“抵押物1”  -->
									<th rowspan="2">抵押物</th>
									<th colspan="2">一抵情况</th>
									<th colspan="2">二抵情况</th>
									<th rowspan="2">现抵押</th>
									<th rowspan="2">购买年份</th>
									<th rowspan="2">状态</th>
									<th rowspan="2">价格</th>
									<th rowspan="2">变现能力</th>
									<th rowspan="2">成数</th>
								</tr>
								<tr>
									<th>银行</th>
									<th>余额(元)</th>
									<th>银行</th>
									<th>余额(元)</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${casePledgeInfoList }" var="item"
									varStatus="s">
									<tr>
										<td>抵押物${s.index+1}</td>
										<td>${item.firstLoanBank }</td>
										<td>${item.firstLoanBalance }</td>
										<td>${item.secondLoanBank }</td>
										<td>${item.secondLoanBalance }</td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>



			<div class="page-box">
				<div class="page-title">偿债能力</div>
				<div class="p10">
					<div id="tb-mortgage-situation2">
						<table class="table-index">
							<thead>
								<tr>
									<td width="20%">抵押物综述</td>
									<td></td>
								</tr>
								<tr>
									<td>业务综析</td>
									<td></td>
								</tr>
								<tr>
									<td>特殊情况</td>
									<td></td>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>



			<div class="page-box">
				<div class="page-title">规则风险</div>
				<div class="p10">
					<div id="tb-rule-risk" class="zui-datagrid"
						zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.beforeCustomer.getParticipantList" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":false,"idField":"id","tableCls":"table-index","isRowNum":false}'>
						<table>
							<thead>
								<tr>
									<th data-options="field:companyName">编号</th>
									<th data-options="field:companyName">大类</th>
									<th data-options="field:gender">提示</th>
									<th data-options="field:birthdayDate">结果</th>
									<th data-options="field:companyName">特批状态</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>


			<div class="page-box">
				<div class="page-title">风险特批</div>
				<div class="p10">
					<div id="tb-risk-sp-approval" class="zui-datagrid"
						zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.beforeCustomer.getParticipantList" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":false,"idField":"id","tableCls":"table-index"}'>
						<table>
							<thead>
								<tr>
									<th data-options="field:companyName">主借人</th>
									<th data-options="field:companyName">申请事项</th>
									<th data-options="field:gender">风险措施</th>
									<th data-options="field:birthdayDate">申请时间</th>
									<th data-options="field:companyName">特批状态</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>


			<div class="page-box">
				<div class="page-title">费用特批</div>
				<div class="p10">
					<div id="tb-cost-sp-approval" class="table-index">
						<table>
							<thead>
								<tr>
									<th colspan="6">费用类型：手续费，调查费 应收：6,00000 实收：5,00000</th>
								</tr>
								<tr>
									<th>序号</th>
									<th>主借人</th>
									<th>费用类型</th>
									<th>应收金额</th>
									<th>实收金额</th>
									<th>特批状态</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>


			<div class="page-box">
				<div class="page-title">评分卡风险建议</div>
				<div class="p10">
					<div id="tb-risk-advise" class="table-index">
						<table>
							<thead>
								<tr>
									<th>产品类型</th>
									<th>评分卡得分</th>
									<th>风险分段</th>
									<th>建议最高抵押层数</th>
									<th>风控提示</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>兴业贷</td>
									<td>100</td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>


			<!-- 列表区域 -->
			<div class="page-box">
				<div class="page-title">审批</div>
				<div class="p10">
					<div id="tb-audit">
						<table class="table-index">
							<tr>
								<td rowspan="2">集团贷审会</td>
								<td width="10%">操作</td>
								<td width="10%">
									<dl class="form-item">
										<dd class="detail">
											<input class="zui-combobox zui-validatebox" type="hidden"
												validate-type="" id="isValid"
												data-data="[{'id':'true','text':'同意'},{'id':'false','text':'不同意'},{'id':'false','text':'退回'}]"
												value="true" data-valuefield="id" data-textfield="text"
												name="isValid">
										</dd>
									</dl>
								</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>审批意见</td>
								<td rowspan="5"><label> <textarea
											class="zui-area zui-validatebox" name="remark"
											validate-type="Require,Length[0-500]"
											placeholder="最多可以输入500个字符"></textarea>
								</label></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</form>
		<div class="form-btn">
			<button id="submitAudit" type="button" class="btn-blue">保存</button>
		</div>
	</div>

	<script type="text/javascript">
		seajs.use([ 'jquery', 'zd/jquery.zds.page.callback',
				'zd/jquery.zds.combobox', 'zd/jquery.zds.dialog',
				'zd/jquery.zds.button', 'zd/jquery.zds.message',
				'zd/jquery.zds.table', 'zd/jquery.zds.form',
				'zd/jquery.zds.message' ], function($, CALLBACK) {
			//格式化时间
			/* CALLBACK.formatterDate = function(row, value) {
				return window.formatDate(row, value);
			}; */

			$.ZUI.init();
			//$.ZUI.initGrid("#tb-mortgage-situation");
			//$.ZUI.initGrid("#tb-solvency");
		});
	</script>
</body>
</html>