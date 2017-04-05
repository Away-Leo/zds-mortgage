<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%> 
<div class="page-box">
			 <div class="page-title">联系人信息   
				 <input type="button" class="btn-search-blue" style="float:right" id="searchReceivablePlan" value="查看还款计划" />
			  </div>
			<div class="p10">
			<div id="caseList"></div>
</div>
</div>
    
<script type="text/javascript">  
	
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], 
		function($, CALLBACK) {   
		//查看还款计划  
		$("#searchReceivablePlan").click(function(){
			var receivablePlanUrl = '<z:ukey key="com.cnfh.rms.casemanage.receivablePlan.viewReceivablePlan" context="admin"/>&caseApplyId=${caseApplyId}';
	        ZDS_MESSAGE_CLIENT.openMenuLink('查看还款计划','查看还款计划',receivablePlanUrl + "&openMethod=tabs");
		});
		$('#caseList').ZTable({  
		       url : "<z:ukey key='com.zdsoft.finance.afterloan.afterSupervise.findAllContactPersonalByCaseApplyId' context='admin'/>&caseApplyId=${caseApplyId}",
		       isRowNum : false,
		       currentPage : 1, 
		       rows:100,
		       singleSelect: true,           
		       pagination:false,
		       idField: "customerId",         
		       tableCls : 'table-index',//table的样式  
		       isMergeCell:true,//默认为false,合并单元格列表
	           mergeColField: 'joinTypeName,customerId',//合并单元格合并数据分类标识符 
	           mergeCol: 'joinTypeName,customerId,customerName,relationshipName,actualUsePerson',//需要合并字段
		       columns:[[                                         
		    	  	{field : 'joinTypeName',title : '参与类型',align : 'center'},
					{field : 'customerId',title : 'customerId',align : 'center',hidden:true},
					{field : 'customerName',title : '姓名',align : 'center'},
					{field : 'relationshipName',title : '与主借人关系', align : 'center'},
					{field : 'actualUsePerson',title : '是否实际用款人', align : 'center',formatter:function(r,v){
						if('YWDM0049002' == v){return '是';}
  						return '否';  
					}},    
					{field : 'contactTypeName',title : '联系方式', align : 'center'},
					{field : 'phoneNumber',title : '电话号码', align : 'center'}    
			] ],
			onDelete:function(index, rowData) {
				//  添加判断
				return true;
			},
			onLoadSuccess:function() {
				$("td").each(function(){
					if($(this).text().trim()=='null'){
						$(this).text("");  
					}
				});     
			}
			});
		
		$.ZUI.init();
	});
</script>
