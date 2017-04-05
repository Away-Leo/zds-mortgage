<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>案件征信录入</title>
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
							<input class="zui-input zui-validatebox" validate-type="Length[0-32]" type="text" id="caseApplyCode"  name="ca|caseApplyCode|LK|S">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">参与人：</dt>
					<dd class="detail">  
						<label> 
							<input class="zui-input zui-validatebox" validate-type="Length[0-32]" type="text" id="customerName" name="cacr|customername|LK|S" >
						</label>
					</dd>
				</dl>  
		           <dl class="form-item">
		            	<dl class="form-item form-auto">  
	               		 <dt class="title">产品：</dt>
						<dd class="detail ">
							<input class="zui-combobox zui-validatebox" type="hidden" name="ca|productTypeId|LK|S" id="productType"
								data-width="94"
								data-url="<z:ukey key='com.zdsoft.finance.authGrade.getParentProduct' context='admin'/>&jsoncallback=?"
								 data-id="isAgriculture" 
								 data-callback="productTypeChange"
								data-valuefield="id" data-textfield="text">  
						</dd>
						<dd class="detail">
							<input class=" zui-combobox zui-validatebox" name="ca|productSubtypeId|LK|S" type="hidden" id="productSubtype"
								data-width="94" >
						</dd>  
					</dl>
           	    </dl>
				<dl class="form-item">
					<dt class="title">录入事项：</dt>
					<dd class="detail">
                    <input class="zui-combobox zui-validatebox" validate-type="Length[0-20]" id="creditLinkCode" type="hidden" name="cacr|creditLinkCode|E|S" value=""
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0091"
			                           data-valuefield="fullcode" data-textfield="name" >
                	</dd>
				</dl>
				<dl class="form-item"> 
		                <dt class="title">创建时间:</dt>
		                <dd class="detail">
		                     <label>
		                         <input type="text" id="startTimeLocal" class="zui-input width2-1" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTimeLocal\')}',realDateFmt:'yyyyMMddHHmmss',vel:'applyDates',dateFmt:'yyyy-MM-dd'})">
		                         <input type="hidden" id="applyDates" name="cacr|uploadDate|RE|S" />
		                     </label>
		                     <span class="word">至</span>
		                     <label>
		                         <input type="text" id="endTimeLocal" class="zui-input width2-1" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTimeLocal\')}',realDateFmt:'yyyyMMddHHmmss',vel:'applyDatee',dateFmt:'yyyy-MM-dd'})">
		                         <input type="hidden" id="applyDatee" name="cacr|uploadDate|LE|S"/>
		                     </label>
		                </dd>
	            	</dl>           
				<dl class="form-item">
					<dt class="title">状态：</dt>
					<dd class="detail">
                    <input class="zui-combobox zui-validatebox" validate-type="Length[0-20]" id="inputStatus" type="hidden" name="cacr|inputStatus|E|S" value=""
			                        	 data-data="[{'id':'0','text':'未录入'},{'id':'1','text':'已录入'}]"
			                           data-valuefield="id" data-textfield="text" >   
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
	       url : "<z:ukey key='com.cnfh.rms.casemanage.credit.getCreditList' context='admin'/>&jsoncallback=?",
	       isRowNum : false,
	       currentPage : 1, 
	       rows:10,
	       singleSelect: true,    
	       pagination:true,  
	       idField: "id", 
	       tableCls : 'table-index',//table的样式   
	       columns:[[
			    	  	{field : 'productSubtypeId',title : 'productSubtypeId',align : 'center',hidden:true},
			    	  	{field : 'caseApplyCode',title : '案件号',align : 'center'},
						{field : 'customername',title : '姓名',align : 'center'},
						{field : 'credentialNo',title : '身份证', align : 'center'},
						{field : 'jointypeName',title : '参与类型', align : 'center'},
						{field : 'productTypeName',title : '产品分类', align : 'center'},
						{field : 'productSubtypeName',title : '产品', align : 'center'},   
						{field : 'applyAmount',title : '申请金额（元）', align : 'center',     
							formatter: function (r, v) {    
								if(v){
									return ZTools.formatCurrency(v+"");
								}
								return v;
							}},
						{field : 'applyTermName',title : '贷款期限（月）', align : 'center'},
						{field : 'uploadDate',title : '创建时间', align : 'center',     
							formatter: function (r, v) {      
								if(v && v>0){return window.formatDate(r,v);}else{return '';}
					                    
							}},
						{field : 'inputDate',title : '录入时间', align : 'center',     
							formatter: function (r, v) {      
								if(v && v>0){return window.formatDate(r,v);}else{return '';}
					                    
							}},
						{field : 'creditLinkName',title : '录入事项', align : 'center'},
						{field : 'inputStatus',title : '状态', align : 'center',        
							formatter: function (r, v) {      
								if(v=='1'){
									return '已录入'     
								}else{return '未录入'}
					                    
							}},      
						{field : 'id',title : '操作', align : 'center',formatter:function(r,v){
							var enterV='录入';
							var data ="";    
							if(r.inputStatus=='1'){
								enterV='修改';
							}
							data += '<a href="javaScript:void(0)" onclick="entering"><button class="btn-blue">'+enterV+'</button></a>&nbsp;&nbsp;';
							if(r.inputStatus=='1'){
								data += '<a href="javaScript:void(0)" onclick="details"><button class="btn-blue">详情</button></a>&nbsp;&nbsp;';  
							}
							data += '<a href="javaScript:void(0)" onclick="attachment"><button class="btn-blue">附件</button></a>';
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
	//录入  
	 CALLBACK.entering=function(v,r){ 
		var caseApplyId = r.caseApplyId;
		var customerId = r.customerId;
		var creditId = r.id;
		var customername = r.customername;
     	ZDS_MESSAGE_CLIENT.openMenuLink('entering', '征信录入', '<z:ukey key="com.zdsoft.finance.credit.creditSituation.edit" context="admin"/>&caseApplyId='+caseApplyId+'&customerId='+customerId+'&creditId='+creditId+'&customerName='+customername);
     }  
	//详情
	 CALLBACK.details=function(v,r){   
		var caseApplyId = r.caseApplyId;   
		var customerId = r.customerId;
		var creditId = r.id;
		var customername = r.customername;
     	ZDS_MESSAGE_CLIENT.openMenuLink('details', '录入详情', '<z:ukey key="com.zdsoft.finance.credit.creditSituation.view" context="admin"/>&caseApplyId='+caseApplyId+'&customerId='+customerId+'&creditId='+creditId+'&customerName='+customername);
     }
	//附件
	 CALLBACK.attachment=function(v,r){  
		var productId = r.productSubtypeId; 
		var caseApplyId = r.caseApplyId; 
		var businessId = r.customerId;//model by liuhuan 
     	ZDS_MESSAGE_CLIENT.openMenuLink('attachment', '附件', '<z:ukey key="com.cnfh.rms.casemanage.credit.attachmentUpload" context="admin"/>&productId='+productId+'&caseApplyId='+caseApplyId+'&businessId='+businessId);
     }   
	//重置回调
	$("#btn-reset").click(function(){
		$("#applyDates").val("");  
		$("#applyDatee").val("");  
		$("#search_from")[0].reset(); 
		$("#productType").val("");  
		$("#productSubtype").val("");    
		$("#productType").ZCombobox("setValue","");
		$("#productSubtype").ZCombobox("setValue","");
		$("#creditLinkCode").val("");    
		$("#creditLinkCode").ZCombobox("setValue","");
		$("#inputStatus").val("");    
		$("#inputStatus").ZCombobox("setValue","");      
	});
	//点击查询
	$("#btn-search").click(function(){ 
		var formArray=$("#search_from").serializeArray();    
		var customerName = $("#customerName").val();       
		$("#tb_caseLimitApply").ZTable("reload",formArray);
	}); 
	
	//刷新
    function doSearch() {
    	var formArray=$("#search_from").serializeArray();    
		var customerName = $("#customerName").val();       
		$("#tb_caseLimitApply").ZTable("reload",formArray);
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