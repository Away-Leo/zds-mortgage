<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%> 
<!-- 单个押品列表信息 -->
<div class="page-box">
       <h1 class="page-title">押品信息</h1>
       <div class="p5">
       	<div id="houseProperty">
		</div>
	</div>
</div>
<script type="text/javascript">
seajs.use(['jquery'], function($) {
	// 押品列表
	$('#houseProperty').ZTable({
       url : '<z:ukey key="com.cnfh.rms.marketing.houseProperty.listSingleHouseProperty" context="admin"/>&jsoncallback=?&housePropertyId=${housePropertyVo.id}',
       singleSelect : true,
       isRowNum : false,
       pagination : false,
       currentPage : 1,
       idField: "id",
       tableCls : 'table-index',//table的样式
       columns:[[
			{field : 'communityName',title : '小区名称',align : 'center'},
			{field : 'totalMailingAddress',title : '押品地址', align : 'center'},
			{field : 'floorAge',title : '楼龄(年)', align : 'center'},
			{field : 'area',title : '面积(㎡)', align : 'center'},
			{field : 'estatePropertiesName',title : '房产性质', align : 'center'},
			{field : 'estateOwnershipName',title : '房产权属', align : 'center'},
			{field : 'isRenovationName',title : '是否有装修', align : 'center'},
			{field : 'synthesizePrice',title : '综合评估价(元)', align : 'center'},
		] ],
		onLoadSuccess:function() {
		}
	});
	
	$.ZUI.initGrid("#houseProperty");
}); 
</script>