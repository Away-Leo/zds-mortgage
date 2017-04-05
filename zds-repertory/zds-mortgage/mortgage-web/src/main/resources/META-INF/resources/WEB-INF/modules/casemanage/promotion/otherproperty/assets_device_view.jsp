<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>    
<!-------------其他资产》设备信息---------------------------------------->
<div class="page-title">设备情况</div>

<div class="p10">
   <div id="assetsDeviceInfoList">
   </div>
</div>
	
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.table','zd/jquery.zds.address', 'datepicker'], 
		function($, CALLBACK, ZTOOLS) {
			//金额千分位
			var formatterAmount = function(r,value){
				if(value){
					return ZTOOLS.formatCurrency(value+"");
				}  
				return '';
			}
			
			// 设备情况列表
			$('#assetsDeviceInfoList').ZTable({
		       url : "<z:ukey key='com.zdsoft.finance.casemanage.promotion.getAssetsDeviceList' context='admin'/>&jsoncallback=?&caseApplyId=${caseApplyId}",
		       singleSelect : true,
		       isRowNum : true,
		       rows : 5,
		       pagination : true,
		       currentPage : 1,
		       idField: "ID",
		       tableCls : 'table-index',//table的样式
		       columns:[[
		    	  	{field : 'DEVICENAME',title : '设备名称',align : 'center'},
					{field : 'EVALUATIONAMOUNT',title : '设备内部估价(元)',align : 'center',formatter:formatterAmount},
					{field : 'INVOICEDATE',title : '设备发票出具日期',align : 'center',hidden:true},
					{field : 'INVOICEDATEFMT',title : '设备发票出具日期',align : 'center'},
					{field : 'OWNERNAME',title : '设备权属人',align : 'center'},
					{field : 'ISPLEDGE',title : '是否在押',align : 'center',hidden:true},
					{field : 'ISPLEDGENM',title : '是否在押',align : 'center'},
					{field : 'PLEDGEAMOUNT',title : '抵押金额(元)', align : 'center',formatter:formatterAmount},
	 				{field : 'DEVICEPROVINCE',title : '省code', align : 'center',hidden:true},
					{field : 'DEVICECITY',title : '市code', align : 'center',hidden:true},
					{field : 'DEVICEDISTRICT',title : '区code', align : 'center',hidden:true},
					{field : 'DETAILADDRESS',title : '详细地址', align : 'center',hidden:true},
					{field : 'DEVICEADDRESS',title : '设备所在地', align : 'center'}
			] ],
			onLoadSuccess:function() {
			}
			});
	
	}); 
</script>