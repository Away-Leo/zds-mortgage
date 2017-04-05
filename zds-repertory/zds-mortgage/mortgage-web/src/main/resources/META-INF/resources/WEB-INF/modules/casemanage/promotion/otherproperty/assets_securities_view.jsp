<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>  
<!-------------其他资产->有价证券-------------->
<div class="page-title">有价证券情况</div>

<div class="p10">
   <div id="assetsSecuritiesInfoList">
   </div>
</div>
	
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.table'], 
		function($, CALLBACK, ZTOOLS) {
		
		//金额千分位
		var formatterAmount = function(r,value){
			if(value){
				return ZTOOLS.formatCurrency(value+"");
			}  
			return '';
		}
		// 有价证券情况列表
		$('#assetsSecuritiesInfoList').ZTable({
	       url : "<z:ukey key='com.zdsoft.finance.casemanage.promotion.getAssetsSecuritiesList' context='admin'/>&jsoncallback=?&caseApplyId=${caseApplyId}",
	       singleSelect : true,
	       isRowNum : true,
	       rows : 5,
	       pagination : true,
	       currentPage : 1,
	       idField: "ID",
	       tableCls : 'table-index',//table的样式
	       columns:[[
	    	  	{field : 'SECURITIESNAME',title : '证劵名称',align : 'center'},
				{field : 'WORTH',title : '价值(元)',align : 'center',formatter:formatterAmount},
				{field : 'OWNER',title : '权属人(受益人)', align : 'center'}
		] ],
		onLoadSuccess:function() {
		}
		});
		
 	
});
</script>
























