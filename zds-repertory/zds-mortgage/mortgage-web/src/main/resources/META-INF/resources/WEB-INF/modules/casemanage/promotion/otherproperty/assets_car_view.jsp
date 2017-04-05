<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>    
<!-------------其他资产->汽车情况-------------->
<div class="page-title">汽车情况</div>

<div class="p10">
   <div id="assetsCarInfoList">
   </div>
</div>
	
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.table','zd/jquery.zds.form'], 
		function($, CALLBACK, ZTOOLS) {
		
		//金额千分位
		var formatterAmount = function(r,value){
			if(value){
				return ZTOOLS.formatCurrency(value+"");
			}  
			return '';
		}
		// 汽车情况列表
		$('#assetsCarInfoList').ZTable({
	       url : "<z:ukey key='com.zdsoft.finance.casemanage.promotion.getAssetsCarList' context='admin'/>&jsoncallback=?&caseApplyId=${caseApplyId}",
	       singleSelect : true,
	       isRowNum : true,
	       rows : 5,
	       pagination : true,
	       currentPage : 1,
	       idField: "ID",
	       tableCls : 'table-index',//table的样式
	       columns:[[
	 	    	  	{field : 'CARTYPE',title : '型号',align : 'center'},
					{field : 'USEYEAR',title : '使用年限',align : 'center',hidden:true},//隐藏字段
					{field : 'WORTH',title : '价值(元)',align : 'center',formatter:formatterAmount},
					{field : 'OWNER',title : '权属人', align : 'center'},
					{field : 'ISPLEDGE',title : '是否在押', align : 'center',hidden:true},
					{field : 'ISPLEDGENM',title : '是否在押',align : 'center'},
					{field : 'PLEDGEPERSON',title : '抵押权人', align : 'center'},
					{field : 'PLEDGEAMOUNT',title : '抵押金额(元)', align : 'center',formatter:formatterAmount},
					{field : 'PLEDGEDEADLINE',title : '抵押期限', align : 'center',hidden:true},
					{field : 'PLEDGEDEADLINEUNIT',title : '抵押期限单位', align : 'center',hidden:true},
					{field : 'PLEDGEDEADLINEALL',title : '抵押期限', align : 'center'}
		] ],
		onLoadSuccess:function() {
			$("#assetsCarInfoList").find("td").each(function(){  
				if($(this).text().trim()=='null'){
					$(this).text("");  
				}
			});     
		}
		});
	
});
</script>

























