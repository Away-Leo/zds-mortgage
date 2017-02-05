<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>    
<!-------------客户信息》联系方式---------------------------------------->
   <div class="page-title">联系方式
   </div>
   <div class="p10">
      	<div id="contactWayInfoList">
		</div>
   </div>
	<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], 
			function($, CALLBACK) {
			
			
			// 联系方式列表
			$('#contactWayInfoList').ZTable({
		       url : "<z:ukey key='com.zdsoft.finance.beforeContact.listContactByCustomerId' context='admin'/>&jsoncallback=?&customerId="+$("#mainCustomerId").val(),
		       singleSelect : true,
		       isRowNum : false,
		       pagination : false,
		       currentPage : 1,
		       idField: "id",
		       tableCls : 'table-index',//table的样式
		       columns:[[
		    	  	{field : 'customerName',title : '姓名',align : 'center'},
					{field : 'contactType',title : '联系类型code',align : 'center',hidden:true},//隐藏字段
					{field : 'contactTypeNm',title : '联系类型',align : 'center'},
					{field : 'phoneNumber',title : '电话号码', align : 'center'},
			] ],
			onLoadSuccess:function() {
			}
			});
			
		});
	</script>
