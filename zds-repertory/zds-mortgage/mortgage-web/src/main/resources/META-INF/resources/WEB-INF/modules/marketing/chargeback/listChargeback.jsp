<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>退单管理</title>

<%@ include file="../../common/common_js.jsp" %>
</head>
<body>
<!-- 搜索框 -->
<div class="page-box">
	<div class="page-title">查询信息</div> 
	<div id="search" class="p5">
		<form id="Form" class="zui-form mt15">
			<dl class="form-item">  
				<dt class="title">案件号：</dt>
				<dd class="detail">
					<label> <input class="zui-input zui-validatebox"
						validate-type="Length[0-64]" type="text" id="caseApplyCode"
						name="ca|caseApplyCode|LK|S">
					</label>
				</dd>
			</dl>

			<dl class="form-item">
				<dt class="title">主借人：</dt>
				<dd class="detail">
					<input class="zui-input zui-validatebox"
						validate-type="Length[0-64]" type="text" id="customerName"
						name="cu|customerName|LK|S">
				</dd>
			</dl>
			<dl class="form-item">  
 				<dt class="title">案件状态：</dt>    
 				<dd class="detail"> 
 					<input class="zui-combobox zui-validatebox" type="hidden"           
 						id="caseApplyStage" name="ca|stage|E|S" 
 						data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0092"
 						data-callback="" data-height="300" data-defaultvalue="" 
 						data-valuefield="fullcode" data-textfield="name"> 
 				</dd>
 			</dl> 


			<dl class="form-item">
				<dt class="title">产品分类：</dt>
				<dd class="detail">
					<input class="zui-combobox zui-validatebox" type="hidden"
						id="productType" data-width="94" name="ca|productTypeId|LK|S"   
						data-url="<z:ukey key='com.zdsoft.finance.authGrade.getParentProduct' context='admin'/>&jsoncallback=?"
						data-callback="productParentIdChange" data-height="300"
						data-defaultvalue="" data-valuefield="id" data-textfield="text">
				</dd>
				<dd class="detail">   
					<input class="zui-combobox zui-validatebox" type="hidden"
						id="productSubtype" name="ca|productSubtypeId|E|S"" data-width="94"
						data-url="<z:ukey key='com.zdsoft.finance.authGrade.getProductByParentId' context='admin'/>&jsoncallback=?"
						data-callback="" data-height="300" data-defaultvalue=""
						data-valuefield="id" data-textfield="text">
				</dd>
			</dl>
			<dl class="form-item">
                   <dt class="title">申请日期：</dt>
                   <dd class="detail">
                       <label>
                           <input type="text" id="startTimeLocal" class="zui-input width2-1" readonly="readonly" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTimeLocal\')}',realDateFmt:'yyyyMMdd',vel:'startTime'})">
                           <input type="hidden" id="startTime" name="ca|applyDate|RE|L" />
                       </label>
                       <span class="word">至</span>
                       <label>
                           <input type="text" id="endTimeLocal" class="zui-input width2-1"  readonly="readonly" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTimeLocal\')}',realDateFmt:'yyyyMMdd',vel:'endTime'})">
                           <input type="hidden" id="endTime" name="ca|applyDate|LE|L"/>
                       </label>
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
			'zd/jquery.zds.form', 'zd/jquery.zds.message'],function($, CALLBACK,ZTOOLS,ZTable,ZUI_MESSAGE_CLIENT) {
	//案件列表 begin 
	$('#caseList').ZTable({  
       url : "<z:ukey key='com.cnfh.rms.marketing.chargeback.getChargebackList' context='admin'/>",
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
			{field : 'productTypeName',title : '产品分类', align : 'center'},
			{field : 'productSubtypeName',title : '子产品', align : 'center'},
			{field : 'applyDate',title : '申请时间', align : 'center',formatter:function(r,v){
				return window.formatDate(r,v);
			}}, 
			{field : 'applyAmount',title : '申请金额(元)', align : 'center', formatter:function(r,v){
				return ZTOOLS.formatCurrency(v+""); 
				}
			},
			{field : 'stageName',title : '案件状态', align : 'center'}, 
			{field : 'id',title : '操作', align : 'center',formatter:function(r,v){
				var anchor = '';
				if(r.oppositedChargeback){
					anchor += '<a href="javaScript:void(0)" class="btn-blue" onclick="opChargeback">反退单</a>'
				}else{
					anchor +=  ' &nbsp<a href="javaScript:void(0)" class="btn-blue" onclick="editChargeback">退单</a>'
					
				}
				
				return anchor;
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
		$("#startTime").val("");   
		$("#endTime").val("");   
		$("#productType").val("");   
		$("#productSubtype").val("");   
		$("#caseApplyStage").val(""); 
		$("#caseApplyStage").ZCombobox("setValue","");
		$("#productType").ZCombobox("setValue","");
		$("#productSubtype").ZCombobox("setValue","");
		var formArray = $("#Form").serializeArray();
		$('#caseList').ZTable("reload",formArray);
	});

	//反退单
	CALLBACK.opChargeback = function(index, row) {  
		$.ZMessage.confirm("确认反退单", "请确认是否反退单", function (r) {
			if(r=='confirm'){
				var opChargebackUrl = '<z:ukey key="com.cnfh.rms.marketing.chargeback.opChargeBack" context="admin"/>';
				$.ajax({
		            url: opChargebackUrl,
		            type: "post",
		            dataType: "json",
		            async:false,
		            data: {"caseApplyId":row.id},
		            success: function (data) {
		                if (data != null) {
		                    if (data.resultStatus == "0") {
		                    	//案件基本信息
		                    	$.ZMessage.success("提示",data.msg, function(){
		                    		setTimeout(function(){
		                    			var formArray = $("#Form").serializeArray();
		                    			$('#caseList').ZTable("reload",formArray);
		                            },200);
		                		});
		                    } else {
		                        $.ZMessage.warning("错误", "操作失败！" + msg.msg);
		                    }
		                } else {
		                    $.ZMessage.warning("错误", "操作失败！");
		                }
		            }
		        });
			}
			
		}, {
                'confirm': {id: 'confirm', text: '确定', buttonCls: 'btn-blue'},
            	'cancel':  {id: 'cancel', text: '取消', buttonCls: 'btn-gray'}
             });
	};
	//退单页面
	CALLBACK.editChargeback = function(index, row) {  
		var editClientUrl = '<z:ukey key="com.cnfh.rms.marketing.chargeback.editChargeBack" context="admin"/>&jsoncallback=?&caseApplyId='
				+ row.id;
		ZDS_MESSAGE_CLIENT.openMenuLink('退单申请', '退单申请',
				editClientUrl + "&openMethod=tabs");

	};
	/**
     * 下拉框联动
     * */
    CALLBACK.productParentIdChange = function (index, rowData, row, thisobj) {
        var parentId = index;
        loadProductChildId(parentId);
    };
    //刷新本页面
    ZDS_MESSAGE_CLIENT.refreshThis=function(){
    	var formArray = $("#Form").serializeArray();
		$('#caseList').ZTable("reload",formArray);
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
</script>
</body>

</html>