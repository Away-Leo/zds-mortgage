<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>    
<!-- 根据押品id获取单个押品信息 -->
	<div class="page-box">
	       <h1 class="page-title">押品信息</h1>
		<div class="p10">
		    <div id="zds-table-house" class="zui-datagrid table-scroll" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.casemanage.dealpledge.detain.listSingleHouse" context="admin"/>&housePropertyId=${housePropertyId }","singleSelect":true,"pagination":false,"idField":"id","tableCls":"table-index"}'>
	      		<table>
			       	<thead>
			             <tr>
			                 <th data-options="field:communityName,align: center,width:200">小区名称</th>
			                 <th data-options="field:mailingAddress">押品地址</th>
			             	<th data-options="field:floorAge">楼龄</th>
			                 <th data-options="field:area">面积</th>
			                 <th data-options="field:estateProperties">房产性质</th>
			                 <th data-options="field:estateOwnership">房产权属</th>
			                 <th data-options="field:isRenovation">是否有装修</th>
			                 <th data-options="field:synthesizePrice">综合评估价</th>
			             </tr>
			          </thead>
	     		</table>
			</div>
		</div>
	</div>
