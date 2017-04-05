<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%> 
<!-------------客户信息------------------------------------>
<div class="page-box">
			 <div class="page-title">担保人信息
			  </div>
			<div class="p10">
			<div id="caseList"></div>
</div>
</div>
    
<script type="text/javascript">
	
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/jquery.zds.address'], 
		function($, CALLBACK) {
		$('#caseList').ZTable({
		       url : "<z:ukey key='com.cnfh.rms.postLoanPersonal.listGuarantor' context='admin'/>&caseApplyId=${param.id}",
		       isRowNum : false,
		       currentPage : 1, 
		       rows:10,
		       singleSelect: true,  
		       pagination:false,
		       idField: "id",
		       tableCls : 'table-index',//table的样式    
		       columns:[[
					{field : 'id',title : 'id',align : 'center',hidden:true},
		    	  	{field : 'customerName',title : '姓名',align : 'center'},
					{field : 'genderName',title : '性别',align : 'center'},
					{field : 'maritalStatusName',title : '婚姻情况', align : 'center'},
					{field : 'birthdayDateStr',title : '出生年月', align : 'center'},
					{field : 'credentialTypeName',title : '证件类型', align : 'center'},
					{field : 'credentialNo',title : '证件号码', align : 'center'},
					{field : 'defaultPhoneNumber',title : '移动电话', align : 'center'},
					{field : 'householdRegistrationStr',title : '户籍地址', align : 'center'},
					{field : 'homeAddressStr',title : '家庭地址', align : 'center'}
			] ],
			onDelete:function(index, rowData) {
				//  添加判断
				return true;
			},
			onLoadSuccess:function() {
			}
			});
		
		$.ZUI.init();
	});
</script>
