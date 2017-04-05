<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<!-- 后补资料承诺 -->
<!-- 查看案件基本信息 -->
  <%@ include file="common/case_apply_view.jsp" %>
<!-- 后补资料承诺 -->	
<div class="page-box">
	<div id="materialPromiseList">
	</div>
</div>

<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.table'], function($, CALLBACK) {
			
			//后补资料承诺
			$('#materialPromiseList').ZTable({
		       url : '<z:ukey key="com.cnfh.rms.casemanage.handleMortgage.getMaterialPromiseList" context="admin"/>&jsoncallback=?&caseApplyId=${caseApplyVo.id}',
		       singleSelect : true,
		       isRowNum : false,
		       pagination : false,
		       currentPage : 1,
		       idField: "id",
		       tableCls : 'table-index',//table的样式
		       onEdit: true, //启用所有行编辑
		       columns:[[
					{field : 'id',title : 'id',align : 'center',hidden:true},//隐藏字段
					{field : 'materialTypeCode',title : '后补资料类型code',align : 'center',hidden:true},//隐藏字段
					{field : 'caseApplyId',title : '案件ID',align : 'center',hidden:true},//隐藏字段
					{field : 'materialTypeName',title : '后补资料类型',align : 'center',formatter:function(r,v){
						return "<b class='c-red mr5'>*</b>"+v;    
						}
					},
					{field : 'predictDate',title : '预计后补时间', align : 'center',formatter:function(r,v){
						if(v==0){return "";} return window.formatDate(r,v);
						}
					},
					{field : 'operatorCode',title : '操作人code', align : 'center',hidden:true},//隐藏字段
					{field : 'operatorName',title : '操作人', align : 'center'},
					{field : 'promiseDate',title : '承诺时间', align : 'center',formatter:function(r,v){
							if(v==0){return "";} return window.formatDate(r,v);
						}
					},
				] ],
				columnsType: [{
					"predictDate": {
	                "inputType": "date",
	                "validateType": "Require"
	            	},
				}, {
					"inputWidth": 140,
					"inputHeight": 24,
					"areaWidth": 200,
					"areaHeight": 24
				}],
				onLoadSuccess:function() {
				}
			});
			
			$.ZUI.initGrid("#materialPromiseList");
	 }); 
</script>