<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>    
<!-------------客户信息》工作单位信息---------------------------------------->
<div class="page-box">
    <div class="page-title">工作单位信息
    </div>
    <div class="p5">
        <div id="workUnitList">
		</div>
	</div>
</div>
	
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.dialog','zd/jquery.zds.table','zd/jquery.zds.address'], 
		function($, CALLBACK) {
		
		// 工作单位列表
		$('#workUnitList').ZTable({
	       url : "<z:ukey key='com.zdsoft.finance.beforeWorkUnit.listWorkUnitByCustomerId' context='admin'/>&jsoncallback=?&customerId=${persCustomerVo.id }",
	       singleSelect : true,
	       isRowNum : false,
	       pagination : false,
	       currentPage : 1,
	       idField: "id",
	       tableCls : 'table-index',//table的样式
	       columns:[[
	    	  	{field : 'workUnitName',title : '姓名',align : 'center'},
				{field : 'companyName',title : '工作单位名称',align : 'center'},
				{field : 'industryTypeName',title : '行业类型',align : 'center'},
				{field : 'industryName',title : '行业',align : 'center'},
				{field : 'workUnitNatureName',title : '单位性质', align : 'center'},
				{field : 'positionName',title : '职务', align : 'center'},
				{field : 'workYears',title : '工作年限（年）', align : 'center'},
				{field : 'phoneNumber',title : '工作单位电话', align : 'center'},
				{field : 'workUnitAddressName',title : '单位地址', align : 'center'},
		] ],
		onLoadSuccess:function() {
		}
		});
	}); 
</script>