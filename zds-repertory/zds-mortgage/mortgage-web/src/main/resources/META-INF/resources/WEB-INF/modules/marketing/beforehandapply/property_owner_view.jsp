<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>    
<!-------------押品信息》产权人信息---------------------------------------->
   <div class="page-box">
	    <div class="page-title">产权信息
	    </div>
	    <div class="p10">
	        <div id="propertyList">
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.dialog','zd/jquery.zds.table','zd/jquery.zds.address'], 
		function($, CALLBACK) {
		// 产权人列表
		$('#propertyList').ZTable({
	       url : '<z:ukey key="com.zdsoft.finance.marketing.getPropertyOwnerList" context="admin"/>&jsoncallback=?&housePropertyId=${housePropertyVo.id}',
	       singleSelect : true,
	       isRowNum : false,
	       pagination : false,
	       currentPage : 1,
	       idField: "id",
	       tableCls : 'table-index',//table的样式
	       columns:[[
				{field : 'ownerName',title : '产权人姓名', align : 'center'},
				{field : 'credentialNo',title : '产权人身份证号码', align : 'center'},
				{field : 'identityStartDate',title : '证件有效期', align : 'center'},
				{field : 'phoneNumber',title : '产权人手机号码', align : 'center'},
				{field : 'emailAddress',title : '产权人邮箱地址', align : 'center'},
				{field : 'comprehensiveAddress',title : '产权人居住地址', align : 'center'},
				{field : 'identityStartDate',title : '期限开始',align : 'center',hidden:true},
				{field : 'identityEndDate',title : '期限结束',align : 'center',hidden:true},
				{field : 'identityTerm',title : '期限年限',align : 'center',hidden:true},
				{field : 'province',title : '产权人居住地址省',align : 'center',hidden:true},
				{field : 'city',title : '产权人居住地址市',align : 'center',hidden:true},
				{field : 'district',title : '产权人居住地址县',align : 'center',hidden:true},
				{field : 'mailingAddress',title : '产权人居住地址详细地址',align : 'center',hidden:true}
		] ],
		onLoadSuccess:function() {
		}
		});
	}); 
	</script>
