<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>案件额度申请</title>
</head>
<body>
<div class="frm-content">
	<!-- 查询区域 -->
	<div class="page-box">
		<div id="search" class="p5">
			<form id="search_from" class="zui-form form-search" method="post" enctype="multipart/form-data">
				<dl class="form-item">
					<dt class="title">案件号：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input zui-validatebox" validate-type="Length[0-32]" type="text" id="caseApplyCode"  name="ma|caseApplyCode|LK|S">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">主借人：</dt>
					<dd class="detail">  
						<label> 
							<input class="zui-input zui-validatebox" validate-type="Length[0-32]" type="text" id="customerName" name="cus|customername|LK|S" >
						</label>
					</dd>
				</dl>
		           <dl class="form-item">
		            	<dl class="form-item form-auto">  
	               		 <dt class="title">产品分类：</dt>
						<dd class="detail ">
							<input class="zui-combobox zui-validatebox" type="hidden" name="ma|productTypeId|LK|S" id="productType"
								data-width="94"
								data-url="<z:ukey key='com.zdsoft.finance.authGrade.getParentProduct' context='admin'/>&jsoncallback=?"
								 data-id="isAgriculture" 
								 data-callback="productTypeChange"
								data-valuefield="id" data-textfield="text">
						</dd>
						<dd class="detail">
							<input class=" zui-combobox zui-validatebox" name="ma|productSubtypeId|LK|S" type="hidden" id="productSubtype"
								data-width="94" >
						</dd>
					</dl>
           	    </dl>
				<dl class="form-item">
					<dt class="title">额度状态：</dt>
					<dd class="detail">
                    <input class="zui-combobox zui-validatebox" validate-type="Length[0-20]" id="actualLimitStatus" type="hidden" name="ma|actualLimitStatus|E|S" value=""
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0059"
			                           data-valuefield="fullcode" data-textfield="name" >
                	</dd>
				</dl>
			</form> 
			<div class="form-btn">
				<input type="button" class="btn-search-blue" id="btn-search" value="查询" />
				<input type="button" class="btn-gray" id="btn-reset" value="重置" />
			</div>
		</div>
	</div>
	<!-- 列表区域 -->
	<div class="page-box">
		<div class="p10">
			<div id="tb_caseLimitApply">  
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK,ZTools) {
	var productUrl =  '<z:ukey key="com.cnfh.rms.businessProduct.findByCategoryId" context="admin"/>&jsoncallback=?';  
	//产品分类级联 
	CALLBACK.productTypeChange = function(v,t){
		$("#productSubtype").ZCombobox({
   		 	valueField: "id",
            textField: "value",
            url: productUrl+"&categoryId="+v  
   		});
	}  
	$('#tb_caseLimitApply').ZTable({
	       url : "<z:ukey key='com.cnfh.rms.casemanage.caseLimitApplyList' context='admin'/>&jsoncallback=?",
	       isRowNum : false,
	       currentPage : 1, 
	       rows:10,
	       singleSelect: true,    
	       pagination:true,  
	       idField: "id", 
	       tableCls : 'table-index',//table的样式   
	       columns:[[
	    	  	{field : 'caseApplyCode',title : '案件号',align : 'center'},
				{field : 'customername',title : '主借人',align : 'center'},
				{field : 'productTypeName',title : '产品分类', align : 'center'},  
				{field : 'productSubtypeName',title : '子产品', align : 'center'},
				{field : 'pledgeType',title : '抵押情况', align : 'center'},
				{field : 'fundPlanName',title : '信托计划名称', align : 'center'},
				{field : 'developmentManagerName',title : '拓展经理', align : 'center'},
				{field : 'applyLimitAmount',title : '申请额度(元)', align : 'center',formatter:function(rowData,v){    
					if(v){
						return ZTools.formatCurrency(v+""); 
					}   
					return v;  
				}},  
				{field : 'actualLimitStatusName',title : '额度状态', align : 'center',formatter:function(rowData,v){    
					if(!v){return '未申请';}   
					return v;  
				}}, 
				{field : 'id',title : '操作', align : 'center',formatter:function(rowData,v){      
					var data ="";   
					if(rowData.actualLimitStatus=="YWDM0051057"||rowData.actualLimitStatus=="YWDM0051058"){
							if(rowData.caseStage>='YWDM009212' && rowData.caseStage<'YWDM009220'){//表示额度申请状态到放款机构放款请款之前
					    		data = '<a href="javaScript:void(0)" onclick="cancleApplyLimit"><button class="btn-blue">取消额度</button></a>&nbsp;&nbsp;'; 
							}
						data += '<a href="javaScript:void(0)" onclick="viewApplyLimit"><button class="btn-blue">详情</button></a>';
					}else if(rowData.caseStage>='YWDM009212' && rowData.caseStage<'YWDM009220'){
						 data = '<a href="javaScript:void(0)" onclick="quotaApplication"><button class="btn-blue">额度申请</button></a>';
					}
					  
					return data;
				}}
			
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
	//额度申请格式化  
	CALLBACK.quotaApplication = function(index,row){
		var pledgeType = row.pledgeType;   
		if(!row.pledgeType){
			pledgeType ='';   
		}
		var paramsUrl='&caseApplyId='+row.id+'&customerName='+row.customername+'&productName='+row.productTypeName+"/"+row.productSubtypeName
		+'&pledgeType='+pledgeType+'&actualLimitStatusName='+row.actualLimitStatusName+"&caseApplyCode="+row.caseApplyCode;
		
		var quotaApplication = '<z:ukey key="com.cnfh.rms.casemanage.quotaApplication" context="admin"/>&jsoncallback=?'+paramsUrl;
        ZDS_MESSAGE_CLIENT.openMenuLink('额度申请','额度申请',quotaApplication + "&openMethod=tabs"); 
	};
	//查看额度申请详情
	CALLBACK.viewApplyLimit = function(index,row){   
		var caseLimitApplyId = row.caseLimitApplyId;
		var pledgeType = row.pledgeType;   
		if(!row.pledgeType){
			pledgeType ='';   
		}
		var paramsUrl='&caseApplyId='+row.id+'&customerName='+row.customername+'&productName='+row.productTypeName+"/"+row.productSubtypeName
		+'&pledgeType='+pledgeType+'&actualLimitStatusName='+row.actualLimitStatusName+"&caseApplyCode="+row.caseApplyCode
		+"&caseLimitApplyId="+caseLimitApplyId;
		   
		var quotaApplication = '<z:ukey key="com.cnfh.rms.casemanage.caseLimitApplyView" context="admin"/>&jsoncallback=?'+paramsUrl;
        ZDS_MESSAGE_CLIENT.openMenuLink('额度详情','额度详情',quotaApplication + "&openMethod=tabs");
	};  
	//取消额度申请
	CALLBACK.cancleApplyLimit = function(index,row){
		var caseLimitApplyId = row.caseLimitApplyId; 
		var pledgeType = row.pledgeType;   
		if(!row.pledgeType){
			pledgeType ='';   
		}
		var paramsUrl='&caseApplyId='+row.id+'&customerName='+row.customername+'&productName='+row.productTypeName+"/"+row.productSubtypeName
		+'&pledgeType='+pledgeType+'&actualLimitStatusName='+row.actualLimitStatusName+"&caseApplyCode="+row.caseApplyCode+"&applyLimitAmount="+row.applyLimitAmount
		+"&caseLimitApplyId="+caseLimitApplyId+"&cancle=1";
		
		var quotaApplication = '<z:ukey key="com.cnfh.rms.casemanage.caseLimitApplyView" context="admin"/>&jsoncallback=?'+paramsUrl;
        ZDS_MESSAGE_CLIENT.openMenuLink('取消额度申请','取消额度申请',quotaApplication + "&openMethod=tabs"); 
	};   
	//重置回调
	$("#btn-reset").click(function(){  
		$("#search_from")[0].reset(); 
		$("#productType").val("");  
		$("#productSubtype").val("");    
		$("#actualLimitStatus").val("");    
		$("#actualLimitStatus").ZCombobox("setValue","");
		$("#productType").ZCombobox("setValue","");
		$("#productSubtype").ZCombobox("setValue","");
	});
	//点击查询
	$("#btn-search").click(function(){ 
		var formArray=$("#search_from").serializeArray();
		$("#tb_caseLimitApply").ZTable("reload",formArray);
	}); 
	
	//刷新
    function doSearch() {
    	$("#tb_caseLimitApply").ZTable("reload",{});
	}; 
   //页面回调
   ZDS_MESSAGE_CLIENT.refreshThis=function(){
		doSearch();
   }; 

	//初始化页面
	$.ZUI.init();
});
</script>
</html>