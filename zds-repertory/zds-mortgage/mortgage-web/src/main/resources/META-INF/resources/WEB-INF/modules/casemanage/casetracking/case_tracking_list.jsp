<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公证</title>

<%@ include file="../../common/common_js.jsp" %>
</head>
<body>
<!-- 搜索框 -->
<div class="page-box">
	<div class="page-title">查询信息</div> 
	<div id="search" class="p5">
		<form id="Form" class="zui-form mt15">
			<dl class="form-item">  
				<dt class="title">案件号</dt>
				<dd class="detail">
					<label> <input class="zui-input zui-validatebox"
						validate-type="Length[0-64]" type="text" id="caseApplyCode"
						name="ma|caseApplyCode|LK|S">
					</label>
				</dd>
			</dl>

			<dl class="form-item">
				<dt class="title">主借人</dt>
				<dd class="detail">
					<input class="zui-input zui-validatebox"
						validate-type="Length[0-64]" type="text" id="customerName"
						name="bcust|customerName|LK|S">
				</dd>
			</dl>
			<dl class="form-item">
				<dt class="title">主借人证件号</dt>
				<dd class="detail">
					<label> <input class="zui-input zui-validatebox"
						validate-type="Length[0-64]" type="text" id="credentialNo"
						name="bcust|credentialNo|LK|S"> 
					</label>
				</dd>
			</dl>


			<!-- <dl class="form-item">
				<dt class="title">当前处理人</dt>
				<dd class="detail">
					<label> <input class="zui-input zui-validatebox"
						validate-type="Length[0-64]" type="text" id="operatorName"
						name="rec|operatorName|LK|S" value="">
					</label>
				</dd>
			</dl> -->        
			<dl class="form-item">  
				<dt class="title">资信员</dt>  
				<dd class="detail">
					<label> <input class="zui-input zui-validatebox"
						validate-type="Length[0-64]" type="text" id="creditMember"
						name="ma|creditMember|LK|S">
					</label> 
				</dd>
			</dl>
			<dl class="form-item">
				<dt class="title">产品分类</dt>
				<dd class="detail">
					<input class="zui-combobox zui-validatebox" type="hidden"
						id="productType" data-width="94" name="ma|productTypeId|LK|S"   
						data-url="<z:ukey key='com.zdsoft.finance.authGrade.getParentProduct' context='admin'/>&jsoncallback=?"
						data-callback="productParentIdChange" data-height="300"
						data-defaultvalue="" data-valuefield="id" data-textfield="text">
				</dd>
				<dd class="detail">   
					<input class="zui-combobox zui-validatebox" type="hidden"
						id="productSubtype" name="ma|productSubtypeId|E|S"" data-width="94"
						data-url="<z:ukey key='com.zdsoft.finance.authGrade.getProductByParentId' context='admin'/>&jsoncallback=?"
						data-callback="" data-height="300" data-defaultvalue=""
						data-valuefield="id" data-textfield="text">
				</dd>

			</dl>             
     
 			<dl class="form-item">  
 				<dt class="title">案件状态</dt>    
 				<dd class="detail"> 
 					<input class="zui-combobox zui-validatebox" type="hidden"           
 						id="caseApplyStage" name="ma|stage|E|S" 
 						data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0092"
 						data-callback="" data-height="300" data-defaultvalue="" 
 						data-valuefield="fullcode" data-textfield="name"> 
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
	<div class="p10">    
	<div id="caseList"></div> 
	</div>
</div>
<script type="text/javascript">
seajs.use([ 'jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.combobox','zd/jquery.zds.button', 'zd/jquery.zds.table',
			'zd/jquery.zds.form', 'zd/jquery.zds.message'],function($, CALLBACK,ZTOOlS) {
	//案件列表 begin 
	$('#caseList').ZTable({  
       url : "<z:ukey key='com.cnfh.rms.casemanage.casetracking.getCaseTracking' context='admin'/>",
       isRowNum : false,
       currentPage : 1,    
       rows:10,
       singleSelect: true,  
       pagination:true,  
       idField: "id",
       tableCls : 'table-index',//table的样式
       columns:[[
    	  	{field : 'caseApplyCode',title : '案件号',align : 'center'},
			{field : 'customerName',title : '主借人',align : 'center'},  
			{field : 'credentialNo',title : '证件号码', align : 'center'},
			{field : 'productTypeName',title : '产品分类', align : 'center'},
			{field : 'productSubtypeName',title : '子产品', align : 'center'},
			{field : 'applyAmount',title : '申请金额(元)', align : 'center',formatter:function(r,v){
			
			    return  fmoney(v, 2);
		}},
			{field : 'creditMember',title : '资信员', align : 'center'},
			{field : 'currentHandler',title : '当前处理人', align : 'center'},
			{field : 'currentNode',title : '当前节点', align : 'center'},
			{field : 'stageName',title : '案件状态', align : 'center'}, 
			{field : 'developmentManagerName',title : '拓展经理', align : 'center'},
			{field : 'applyDate',title : '申请时间', align : 'center',formatter:function(r,v){
				return window.formatDate(r,v);
			}},    
			{field : 'id',title : '操作', align : 'center',width:'10%',formatter:function(r,v){
				return '<a href="javaScript:void(0)" class="btn-blue" onclick="detail">详情</a>';
			}}
	] ],
	onDelete:function(index, rowData) {
		//  添加判断
		return true;
	},
	onLoadSuccess:function() {  
		$("td").each(function(){
			if($(this).text().trim()=='null'){
				$(this).text('');
			}
		})
	}
	});
	//案件列表 end  
	//搜索回调
	$('#searchCaseTracking').on('click',function() { 
			var formArray = $("#Form").serializeArray();
			$('#caseList').ZTable("reload",formArray);
	});

	//重置回调
	$('#resetCaseTracking').click(function(){  
		$("#Form")[0].reset();  
		$("#productType").val("");   
		$("#productSubtype").val("");   
		$("#caseApplyStage").val(""); 
		$("#caseApplyStage").ZCombobox("setValue","");
		$("#productType").ZCombobox("setValue","");
		$("#productSubtype").ZCombobox("setValue","");
	});

	//详情页面
	CALLBACK.detail = function(index, row) {  
		var editClientUrl = '<z:ukey key="com.zdsoft.finance.casemanage.riskAuditView" context="admin"/>&jsoncallback=?&projectId='
				+ row.id;
		ZDS_MESSAGE_CLIENT.openMenuLink('案件详情', '案件详情',
				editClientUrl + "&openMethod=tabs");

	};
	
	/**
     * 下拉框联动
     * */
    CALLBACK.productParentIdChange = function (index, rowData, row, thisobj) {
        var parentId = index;
        loadProductChildId(parentId);
    };
   
    /**
     * 下拉数据
     * @param cataId
     */
    function loadProductChildId(pId) { 
        var productChildId = $("#productSubtype");
        productChildId.ZCombobox({queryParams: {"parentId": pId}});
    }
  //初始化   
	$.ZUI.init();
    
});
function fmoney(s, n) {  
    n = n > 0 && n <= 20 ? n : 2;  
    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";  
    var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];  
    t = "";  
    for (i = 0; i < l.length; i++) {  
        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");  
    }  
    return t.split("").reverse().join("") + "." + r;  
}  
</script>
</body>

</html>