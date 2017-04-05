<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 押品信息 -->
<div class="page-box">
	<h1 class="page-title">押品信息</h1>
	<div class="p5">
		<div class="zui-datagrid" id="collId"
			zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.specialApprove.publicCaseApplyBasicInfo" context="admin"/>&caseApplyId=${caseApplyId }&type=1&jsoncallback=?","singleSelect":true,"pagination":false,"idField":"id","tableCls":"table-index"}'>
			<table>
				<tr>
					<th data-options="field:communityName">小区名称</th>
					<th data-options="field:mailingAddress">押品地址</th>
					<th data-options="field:floorAge">楼龄（年）</th>
					<th data-options="field:area">面积（㎡）</th>
					<th data-options="field:estatePropertiesName">房产性质</th>
					<th data-options="field:estateOwnershipName">房产权属</th>
					<th data-options="field:isRenovationName">是否有装修</th>
					<th data-options="field:synthesizePrice" formatter="formatCurrency">综合评估价（元）</th>
				</tr>
			</table>
		</div>
	</div>
</div>
