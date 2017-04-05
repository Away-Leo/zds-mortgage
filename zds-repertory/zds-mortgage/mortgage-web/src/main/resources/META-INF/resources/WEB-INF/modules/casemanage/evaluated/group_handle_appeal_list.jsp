<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>集团查看申诉</title>

<%@ include file="../../common/common_js.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <link rel="stylesheet" type="text/css" href="http://192.168.18.253:8080/dev/static/assets/css/style.css"> -->
</head>
<body>
<!-- 搜索框 -->
<div class="page-box">
	<div class="page-title">查询信息</div>
	<div id="search" class="p5">
		<form id="Form" class="zui-form mt15">
			<dl class="form-item">
				<dt class="title">案件号:</dt>
				<dd class="detail">
					<label> <input class="zui-input zui-validatebox"
						validate-type="Length[0-64]" type="text" id="caseApplyCode"
						name="ca|caseApplyCode|LK|S" value="">
					</label>
				</dd>
			</dl>

			<dl class="form-item">
				<dt class="title">机构:</dt>
				<dd class="detail">
					<input class="zui-input zui-validatebox"
						validate-type="Length[0-64]" type="text" id="mechanismName"
						name="ca|mechanismName|LK|S" value="">
				</dd>
			</dl>

			<dl class="form-item">
				<dt class="title">产品分类：</dt>
				<dd class="detail">
					<input class="zui-combobox zui-validatebox" type="hidden"
						id="productParentId" data-width="94" name="prc|id|E|S"
						data-url="<z:ukey key='com.zdsoft.finance.authGrade.getParentProduct' context='admin'/>&jsoncallback=?"
						data-callback="productParentIdChange" data-height="300"
						data-defaultvalue="" data-valuefield="id" data-textfield="text">
				</dd>
				<dd class="detail">
					<input class="zui-combobox zui-validatebox" type="hidden"
						id="productChildId" name="prp|id|E|S"" data-width="94"
						data-url="<z:ukey key='com.zdsoft.finance.authGrade.getProductByParentId' context='admin'/>&jsoncallback=?"
						data-callback="" data-height="300" data-defaultvalue=""
						data-valuefield="id" data-textfield="text">
				</dd>

			</dl>
			<dl class="form-item">
				<dt class="title">主借人:</dt>
				<dd class="detail">
					<input class="zui-input zui-validatebox"
						validate-type="Length[0-64]" type="text" id="customerName"
						name="cu|customerName|LK|S" value="">
				</dd>
			</dl>

		</form>
		<div class="form-btn">
			<button class="btn-blue" id="searchCaseTracking">查询</button>
			<button class="btn-gray" id="resetCaseTracking">重置</button>
		</div>
	</div>
</div>
<div class="page-box">
       <h1 class="page-title">集团查看申诉</h1>
       <div class="p5">
       	<div id="group_handle_appeal_list">
		</div>
	</div>
</div>

<script type="text/javascript">
seajs.use([ 'jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.combobox','zd/jquery.zds.button', 'zd/jquery.zds.table',
			'zd/jquery.zds.form', 'zd/jquery.zds.message'],function($, CALLBACK,ZTOOLS,ZUI_MESSAGE_CLIENT) {
	
	$('#group_handle_appeal_list').ZTable({
	       url : '<z:ukey key="com.zdsoft.finance.casemanage.evaluated.group.listGroupHandleList" context="admin"/>',
	       singleSelect : true,
	       isRowNum : true,
	       pagination : true,
	       currentPage : 1,
	       idField: "id",
	       tableCls : 'table-index',//table的样式
	       toolbar:[{
				id:'btn-add',
				text:'导出',
				//iconCls: 'icon-btn02',
				buttonCls: 'btn-blue',
				handler: function(){
						var url="<z:ukey key="com.zdsoft.finance.toExcel" context="admin"/>&jsoncallback=?&fileName=集团处理评估价申诉列表导出文档";
						var param=$("table").html();
						$("form").remove("#exportFrom");
					    $("body").append("<form id='exportFrom' class='zui-form mt15' method='post' action='"+url+"' accept-charset='utf-8'><input type='hidden' id='htmlContent' name='htmlContent' value='"+param+"' /></form>");
					    //console.log(param);
					    $("#exportFrom").submit();
				}
			}],
	       columns:[[
				{field : 'mechanismName',title : '机构',align : 'center'},
				{field : 'caseApplyCode',title : '案件号',align : 'center'},
				{field : 'customerName',title : '主借人', align : 'center'},
				{field : 'categoryName',title : '产品分类', align : 'center'},
				{field : 'childName',title : '子产品', align : 'center'},
				{field : 'applyAmount',title : '申请金额(元)', align : 'center',formatter:function(r,v){
				return ZTOOLS.formatCurrency(v+""); 
				}},
				{field : 'detailAddress',title : '押品地址', align : 'center'},
				{field : 'evaluatingPrice',title : '评估价(元)', align : 'center',formatter:function(r,v){
				return ZTOOLS.formatCurrency(v+""); 
				}},
				{field : 'housePropertyId',title : '房产id', align : 'center',hidden:true},
				{field : 'id',title : '操作', align : 'center', formatter:function(r,v){
					var data = '&nbsp;&nbsp;<a href="javaScript:void(0)" onclick="handleAppeal" class="btn-blue">处理</a>';
					return data;
					}
				}
			] ],
			onLoadSuccess:function() {
			}
		});
	//格式化时间
// 	CALLBACK.formatterDate = function(row, value) {
// 		return ZTOOlS.strToTime(value);
// 	};
	
    //下拉框初始化
//     $("#productParentId").ZCombobox();
//     $("#productChildId").ZCombobox();

	//搜索回调
	$('#searchCaseTracking').on('click',function() {
		var flag = $.ZUI.validateForm($('#Form'));
		if (flag) {
			var formArray = $("#Form").serializeArray();
			$('#group_handle_appeal_list').ZTable("reload",formArray);
		}
	});

	//重置回调
	$('#resetCaseTracking').on('click', function() {
		//清空搜索框数据,并重新加载列表
		$('#caseApplyCode').val('');
		$('#customerName').val('');
		$('#mechanismName').val('');
		$('#productChildId').ZCombobox('setValue', '');
		$('#productParentId').ZCombobox('setValue', '');
		var flag = $.ZUI.validateForm($('#Form'));
		if (flag) {
			var formArray = $("#Form").serializeArray();
			$('#group_handle_appeal_list').ZTable("reload",formArray);
		}
	});


	//详情页面
	CALLBACK.viewGroupHandleAppeal = function(index, row) {
		var viewAppealUrl = '<z:ukey key="com.zdsoft.finance.casemanage.evaluated.viewAppeal" context="admin"/>&housePropertyId='
			+ row.housePropertyId +"&caseApplyId="+row.caseApplyId;
		ZDS_MESSAGE_CLIENT.openMenuLink('详情', '详情',
				viewAppealUrl + "&openMethod=tabs");

	};
	CALLBACK.handleAppeal = function(index, row){
		
		var doAppealUrl = '<z:ukey key="com.zdsoft.finance.casemanage.evaluated.group.editHandleAppeal" context="admin"/>&housePropertyId='
			+ row.housePropertyId +"&caseApplyId="+row.caseApplyId;
		ZDS_MESSAGE_CLIENT.openMenuLink('处理', '处理',
				doAppealUrl + "&openMethod=tabs");
	};
	/**
     * 下拉框联动
     * */
    CALLBACK.productParentIdChange = function (index, rowData, row, thisobj) {
        var parentId = index;
       // debugger;
        loadProductChildId(parentId);
    };
   
    /**
     * 下拉数据
     * @param cataId
     */
    function loadProductChildId(pId) {
    	//debugger;
        var productChildId = $("#productChildId");
        productChildId.ZCombobox({queryParams: {"parentId": pId},value:''});
    }
    
  //刷新本页面
    ZDS_MESSAGE_CLIENT.refreshThis=function(){
    	var formArray = $("#Form").serializeArray();
		$('#group_handle_appeal_list').ZTable("reload",formArray);
   }; 
   //
	
  //初始化
	$.ZUI.init();
    
});
</script>
</body>

</html>