<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>    
<!-------------押品信息》抵押情况---------------------------------------->
 <div class="page-box">
    <div class="page-title">抵押情况
    </div>
    <div class="p5">
        <div id="pledgeList">
		</div>
	</div>
</div>
	
<script type="text/javascript">
	seajs.use(['jquery','zd/tools','zd/jquery.zds.table'], 
		function($,ZTools) {
		// 抵押情况列表
		$('#pledgeList').ZTable({
	       url : '<z:ukey key="com.zdsoft.finance.marketing.pledgeInfo.getPledgeInfoList" context="admin"/>&jsoncallback=?&housePropertyId=${housePropertyVo.id}',
	       singleSelect : true,
	       isRowNum : false,
	       pagination : false,
	       currentPage : 1,
	       idField: "id",
	       tableCls : 'table-index',//table的样式
	       columns:[[
				{field : 'loanBank',title : '贷款银行', align : 'center'},
				{field : 'deadline',title : '贷款期限(月)', align : 'center'},
				{field : 'typeName',title : '类型', align : 'center'},
				{field : 'loanBalance',title : '现贷款余额(元)', align : 'center',formatter:function(index,value){
					return ZTools.formatCurrency(value+"");
				}},
				{field : 'pledgeAmout',title : '抵押金额(元)', align : 'center',formatter:function(index,value){
					return ZTools.formatCurrency(value+"");
				}}
				
			] ],
			onLoadSuccess:function() {
			}
		});
	}); 
</script>