<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 押品列表信息 -->
<div class="page-box">
       <h1 class="page-title">押品信息</h1>
       <div class="p5">
       	<div id="housePropertyList">
		</div>
	</div>
</div>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.table','zd/tools'], function($,ZTable,ZTOOLS) {
	//金额千分位
	var formatterAmount = function(r,value){
		if(value){
			return ZTOOLS.formatCurrency(value+"");
		}  
		return '';
	}
	// 押品列表
	$('#housePropertyList').ZTable({
       url : '<z:ukey key="com.cnfh.rms.marketing.houseProperty.getHousePropertyList" context="admin"/>&jsoncallback=?&caseApplyId=${caseApplyVo.id}',
       singleSelect : true,
       isRowNum : false,
       pagination : false,
       currentPage : 1,
       idField: "id",
       tableCls : 'table-index',
       columns:[[
			{field : 'communityName',title : '小区名称',align : 'center'},
			{field : 'totalMailingAddress',title : '押品地址', align : 'center'},
			{field : 'floorAge',title : '楼龄(年)', align : 'center'},
			{field : 'area',title : '面积(㎡)', align : 'center'},
			{field : 'estatePropertiesName',title : '房产性质', align : 'center'},
			{field : 'estateOwnershipName',title : '房产权属', align : 'center'},
			{field : 'isRenovation',title : '是否有装修', align : 'center',hidden:true},
			{field : 'isRenovationName',title : '是否有装修', align : 'center'},
			{field : 'synthesizePrice',title : '综合评估价(元)', align : 'center',formatter:formatterAmount},
		] ],
		onLoadSuccess:function() {
		}
	});
}); 
</script>