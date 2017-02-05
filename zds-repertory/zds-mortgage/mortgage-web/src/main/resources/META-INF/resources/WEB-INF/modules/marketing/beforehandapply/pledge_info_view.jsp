<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>    
<!-------------押品信息》抵押情况---------------------------------------->
   <div class="page-box">
	    <div class="page-title">抵押情况
	    </div>
	    <div class="p10">
	        <div id="pledgeList">
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.dialog','zd/jquery.zds.table'], 
		function($, CALLBACK) {
		// 抵押情况列表
		$('#pledgeList').ZTable({
	       url : '<z:ukey key="com.zdsoft.finance.marketing.getPledgeInfoList" context="admin"/>&jsoncallback=?&housePropertyId=${housePropertyVo.id}',
	       singleSelect : true,
	       isRowNum : false,
	       pagination : false,
	       currentPage : 1,
	       idField: "id",
	       tableCls : 'table-index',//table的样式
	       columns:[[
				{field : 'pledgeType',title : '行业类型code',align : 'center',hidden:true},//隐藏字段
				{field : 'pledgeTypeNm',title : '抵押类型',align : 'center',hidden:true},//隐藏字段
				{field : 'loanBank',title : '贷款银行', align : 'center'},
				{field : 'deadline',title : '贷款期限', align : 'center'},
				{field : 'type',title : '类型code', align : 'center',hidden:true},
				{field : 'typeNm',title : '类型', align : 'center'},
				{field : 'loanBalance',title : '现贷款余额', align : 'center'},
				{field : 'pledgeAmout',title : '抵押金额', align : 'center'},
				{field : 'percentage',title : '成数', align : 'center'}
		] ],
		onLoadSuccess:function() {
		}
		});
		
		 
	}); 
	</script>
