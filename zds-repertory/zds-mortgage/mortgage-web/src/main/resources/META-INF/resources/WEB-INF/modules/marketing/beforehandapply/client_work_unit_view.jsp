<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>    
<!-------------客户信息》工作单位信息---------------------------------------->
   <div class="page-box">
	    <div class="page-title">工作单位信息
	    </div>
	    <div class="p10">
	        <div id="workUnitList">
			</div>
		</div>
	</div>
	

	
	<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.dialog','zd/jquery.zds.table','zd/jquery.zds.address'], 
			function($, CALLBACK) {
			
			// 工作单位列表
			$('#workUnitList').ZTable({
		       url : "<z:ukey key='com.zdsoft.finance.beforeWorkUnit.listWorkUnitByCustomerId' context='admin'/>&jsoncallback=?&customerId="+$("#mainCustomerId").val(),
		       singleSelect : true,
		       isRowNum : false,
		       pagination : false,
		       currentPage : 1,
		       idField: "id",
		       tableCls : 'table-index',//table的样式
		       columns:[[
		    	  	{field : 'workUnitName',title : '姓名',align : 'center'},
					{field : 'companyName',title : '工作单位名称',align : 'center'},
					{field : 'industryType',title : '行业类型code',align : 'center',hidden:true},//隐藏字段
					{field : 'industryTypeNm',title : '行业类型',align : 'center'},
					{field : 'industry',title : '行业类型code',align : 'center',hidden:true},//隐藏字段
					{field : 'industryNm',title : '行业',align : 'center'},
					{field : 'workUnitNature',title : '单位性质code', align : 'center',hidden:true},
					{field : 'workUnitNatureNm',title : '单位性质', align : 'center'},
					{field : 'position',title : '职务code', align : 'center',hidden:true},
					{field : 'positionNm',title : '职务', align : 'center'},
					{field : 'workYears',title : '工作年限', align : 'center'},
					{field : 'phoneNumber',title : '工作单位电话', align : 'center'},
					{field : 'workUnitAddressName',title : '单位地址', align : 'center'},
					{field : 'province',title : '省code', align : 'center',hidden:true},
					{field : 'city',title : '市code', align : 'center',hidden:true},
					{field : 'district',title : '县code', align : 'center',hidden:true},
					{field : 'workUnitAddress',title : '详细地址', align : 'center',hidden:true}
			] ],
			onLoadSuccess:function() {
			}
			});
			
	}); 
	</script>
