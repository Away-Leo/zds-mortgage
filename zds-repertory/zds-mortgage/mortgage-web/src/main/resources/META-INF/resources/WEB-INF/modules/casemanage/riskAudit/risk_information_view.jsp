<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<div class="page-box">
	<h1 class="page-title">汇法网信息</h1>
	<table class="table-detail">
		<tr>
			<th class="pct10">主借人:</th>
			<td class="pct20"></td>
			<th class="pct10">标题:</th>
			<td class="pct20"></td>
			<th class="pct10">被执行人姓名或名称:</th>
			<td class="pct20"></td>
		</tr>
		<tr>
			<th class="pct10">证件号码:</th>
			<td class="pct20"></td>
			<th class="pct10">执行法院:</th>
			<td class="pct20"></td>
			<th class="pct10">执行案号:</th>
			<td class="pct20"></td>
		</tr>
		<tr>
			<th class="pct10">执行内容:</th>
			<td class="pct20"></td>
			<th class="pct10">执行标的:</th>
			<td class="pct20"></td>
			<th class="pct10">立案时间:</th>
			<td class="pct20"></td>
		</tr>
		<tr>
			<th class="pct10">执行状态:</th>
			<td class="pct20"></td>
			<th class="pct10">异议备注:</th>
			<td class="pct20"></td>
			<th class="pct10">身份证号码:</th>
			<td class="pct20"></td>
		</tr>
		<tr>
			<th class="pct10">组织机构代码:</th>
			<td class="pct20"></td>
			<th class="pct10">案件状态:</th>
			<td class="pct20"></td>
			<th class="pct10">更新时间:</th>
			<td class="pct20"></td>
		</tr>
	</table>
</div>
<div class="page-box">
	<h1 class="page-title">关联贷款信息</h1>
	<div>
		<div id="relatedLoanInfo" class="p5" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.casemanage.getRelatedLoanInfo" context="admin"/>&jsoncallback=?&customerId=${customerId}","singleSelect":true,"pagination":false,"idField":"id","tableCls":"table-index"}'>
			<table class="table-index">
       			<tr>
           			<th data-options="field:product">案件号</th>
           			<th data-options="field:period">客户姓名</th>
           			<th data-options="field:amount">参与类型</th>
           			<th data-options="field:pledgePick">楼龄(年)</th>
           			<th data-options="field:detailNames">面积(平米)</th>
           			<th data-options="field:detailCollectionTypes">购买总价(元)</th>
           			<th data-options="field:amount">抵押类型</th>
           			<th data-options="field:pledgePick">房产性质</th>
           			<th data-options="field:detailNames">贷款金额(元)</th>
           			<th data-options="field:detailCollectionTypes">逾期金额(元)</th>
           			<th data-options="field:pledgePick">逾期时间</th>
           			<th data-options="field:detailNames">逾期天数(天)</th>
		        </tr>
			</table>
		</div>
    </div>
</div>
<div class="page-box">
	<h1 class="page-title">工商信息</h1>
	<table class="table-detail">
    	<tr>
	   		<th class="pct10">统一社会信用代码/注册号:</th>
			<td class="pct20"></td>
			<th class="pct10">名称:</th>
			<td class="pct20"></td>
			<th class="pct10">类型:</th>
			<td class="pct20"></td>
	  	</tr>
	  	<tr>
	   		<th class="pct10">法定代表人:</th>
			<td class="pct20"></td>
			<th class="pct10">注册资本:</th>
			<td class="pct20"></td>
			<th class="pct10">成立日期:</th>
			<td class="pct20"></td>
	  	</tr>
	  	<tr>
	   		<th class="pct10">地址:</th>
			<td class="pct20"></td>
			<th class="pct10">营业期限自:</th>
			<td class="pct20"></td>
			<th class="pct10">营业期限至:</th>
			<td class="pct20"></td>
	  	</tr>
	  	<tr>
	   		<th class="pct10">经营范围:</th>
			<td class="pct20" colspan="5"></td>
	  	</tr>
	  	<tr>
	   		<th class="pct10">登记机关:</th>
			<td class="pct20"></td>
			<th class="pct10">核准日期:</th>
			<td class="pct20"></td>
			<th class="pct10">登记状态:</th>
			<td class="pct20"></td>
	  	</tr>
    </table>
</div>
<script type="text/javascript">
seajs.use(['jquery', ['jquery', 'zd/jquery.zds.page.callback', 'zd/switch','zd/jquery.zds.loading', 'zd/jquery.zds.dialog', 'zd/jquery.zds.combobox', 'zd/jquery.zds.form', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'], function ($, CALLBACK, Switch,Loading) {
    $.ZUI.init();
});
</script>
