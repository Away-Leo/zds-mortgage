<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>  
<!-------------其他资产->存款情况-------------->
<div class="page-title">存款情况</div>

<div class="p10">
   <div id="assetsDepositInfoList">
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
		// 存款情况列表
		$('#assetsDepositInfoList').ZTable({
	       url : "<z:ukey key='com.zdsoft.finance.casemanage.promotion.getAssetsDepositList' context='admin'/>&jsoncallback=?&caseApplyId=${caseApplyId}",
	       singleSelect : true,
	       isRowNum : true,
	       rows : 5,
	       pagination : true,
	       currentPage : 1,
	       idField: "ID",
	       tableCls : 'table-index',//table的样式
	       columns:[[
	 	    	  	{field : 'DEPOSITTYPE',title : '存款类型',align : 'center',hidden:true},
		    	  	{field : 'DEPOSITTYPENM',title : '存款类型',align : 'center'},
					{field : 'DEPOSITDEADLINE',title : '期限',align : 'center',hidden:true},
					{field : 'DEPOSITDEADLINEUNIT',title : '期限单位',align : 'center',hidden:true},//隐藏字段
					{field : 'DEPOSITDEADLINEALL',title : '期限',align : 'center'},
					{field : 'DEPOSITAMOUNT',title : '存款金额(元)', align : 'center',formatter:formatterAmount}
		] ],
		onLoadSuccess:function() {
		}
		});
		
});
</script>
























