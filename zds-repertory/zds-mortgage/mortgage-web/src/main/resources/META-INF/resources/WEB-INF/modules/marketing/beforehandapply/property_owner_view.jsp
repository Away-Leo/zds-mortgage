<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>    
<!-------------押品信息》产权人信息------------->
 <div class="page-box">
    <div class="page-title">产权信息
    </div>
    <div class="p10">
        <div id="propertyList">
		</div>
	</div>
</div>
	
<script type="text/javascript">
	seajs.use(['jquery','zd/tools','zd/jquery.zds.table','zd/jquery.zds.address'], 
		function($,ZTOOL) {
		// 产权人列表
		$('#propertyList').ZTable({
	       url : '<z:ukey key="com.zdsoft.finance.marketing.propertyOwner.getPropertyOwnerList" context="admin"/>&jsoncallback=?&housePropertyId=${housePropertyVo.id}',
	       singleSelect : true,
	       isRowNum : false,
	       pagination : false,
	       currentPage : 1,
	       idField: "id",
	       tableCls : 'table-index',//table的样式
	       columns:[[
				{field : 'ownerName',title : '产权人姓名', align : 'center'},
				{field : 'credentialNo',title : '产权人身份证号码', align : 'center'},
				{field : 'identityStartDate1',title : '证件有效期', align : 'center',formatter:function(r,v){
					return ZTOOL.strToDate(r.identityStartDate+"") +"至" +ZTOOL.strToDate(r.identityEndDate+"");
					}
				},
				{field : 'phoneNumber',title : '产权人手机号码', align : 'center'},
				{field : 'emailAddress',title : '产权人邮箱地址', align : 'center'},
				{field : 'comprehensiveAddress',title : '产权人居住地址', align : 'center'},
		] ],
		onLoadSuccess:function() {
		}
		});
	}); 
</script>