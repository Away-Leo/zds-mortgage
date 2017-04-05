<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>    
<!-------------其他资产》房产信息---------------------------------------->
<div class="page-title">房产情况</div>

<div class="p10">
   <div id="customerHouseInfoList">
   </div>
</div>
	
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.table','zd/jquery.zds.address'], 
		function($, CALLBACK, ZTOOLS) {
			//金额千分位
			var formatterAmount = function(r,value){
				if(value){
					return ZTOOLS.formatCurrency(value+"");
				}  
				return '';
			}	
		
			// 房产情况列表
			$('#customerHouseInfoList').ZTable({
		       url : "<z:ukey key='com.zdsoft.finance.casemanage.promotion.getCustomerHouseList' context='admin'/>&jsoncallback=?&caseApplyId=${caseApplyId}",
		       singleSelect : true,
		       isRowNum : true,
		       rows : 5,
		       pagination : true,
		       currentPage : 1,
		       idField: "ID",
		       tableCls : 'table-index',//table的样式
		       columns:[[
		    	  	{field : 'OWNER',title : '权属人',align : 'center'},
					{field : 'HOUSEPROPERTY',title : '房产性质',align : 'center',hidden:true},
					{field : 'HOUSEPROPERTYNM',title : '房产性质',align : 'center'},
					{field : 'ISPLEDGE',title : '是否在押',align : 'center',hidden:true},
					{field : 'ISPLEDGENM',title : '是否在押',align : 'center'},
					{field : 'PLEDGEPERSON',title : '抵押权人', align : 'center'},
					{field : 'PLEDGEAMOUNT',title : '抵押金额(元)', align : 'center',formatter:formatterAmount},
					{field : 'PLEDGEDEADLINES',title : '抵押期限', align : 'center',hidden:true},
					{field : 'PLEDGEDEADLINEUNIT',title : '抵押期限单位', align : 'center',hidden:true},
					{field : 'PLEDGEDEADLINEUNITNM',title : '抵押期限单位名称', align : 'center',hidden:true},
					{field : 'MEASUREAREA',title : '面积', align : 'center'},
					{field : 'WORTH',title : '价值(元)', align : 'center',formatter:formatterAmount},
	 				{field : 'PROVINCE',title : '省code', align : 'center',hidden:true},
					{field : 'CITY',title : '市code', align : 'center',hidden:true},
					{field : 'DISTRICT',title : '区code', align : 'center',hidden:true},
					{field : 'ADDRESS',title : '地址', align : 'center',hidden:true},
					{field : 'HOUSEADDRESS',title : '地址', align : 'center'}
					
			] ],
			onLoadSuccess:function() {
			}
			});


	}); 
</script>