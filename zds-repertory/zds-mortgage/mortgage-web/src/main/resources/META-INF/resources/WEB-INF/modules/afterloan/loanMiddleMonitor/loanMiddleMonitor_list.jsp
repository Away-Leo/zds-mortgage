<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">

<%@ include file='../../common/common_js.jsp'%>
<title>贷中监控</title>
</head>
<body>
	<div class="frm-content">
		<!-- 查询区域 -->
		<div class="page-box">
			<div class="p10">
				<form id="search_from" class="zui-form form-search" method="post"
					enctype="multipart/form-data">
					<dl class="form-item">
						<dt class="title">案件号：</dt>
						<dd class="detail">
							<input class="zui-input" id="contractName" name="ma|caseApplyCode|LK|S">
						</dd>
					</dl>
					<dl class="form-item">
						<dt class="title">主借人：</dt>
						<dd class="detail">
							<input class="zui-input" id="contractName" name="cafc|customername|LK|S">
						</dd>
					</dl>
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
					
					<dl class="form-item">
		                <dt class="title">上次监控时间:</dt>
		                <dd class="detail">
		                     <label>
		                         <input type="text" id="startTimeLocal" class="zui-input width2-1" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTimeLocal\')}',realDateFmt:'yyyyMMdd',vel:'applyDates',dateFmt:'yyyy-MM-dd'})">
		                         <input type="hidden" id="applyDates" name="mr|monitorDate|RE|S" />
		                     </label>
		                     <span class="word">至</span>
		                     <label>
		                         <input type="text" id="endTimeLocal" class="zui-input width2-1" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTimeLocal\')}',realDateFmt:'yyyyMMdd',vel:'applyDatee',dateFmt:'yyyy-MM-dd'})">
		                         <input type="hidden" id="applyDatee" name="mr|monitorDate|LE|S"/>
		                     </label>
		                </dd>
	            	</dl>
					<dl class="form-item">
						<dt class="title">机构名称：</dt>
						<dd class="detail">
							<input class="zui-input" id="contractName" name="ma|mechanismName|LK|S">
						</dd>
					</dl>
					<dl class="form-btn"> 
						<button type="button" class="btn-search-blue" id="btn-search">查询</button>
						<button type="button" class="btn-search-gray" id="btn-reset">重置</button>
					</dl>
				</form>
			</div>
		</div>
		<!-- 案件列表 -->
		<!-- begin -->
		<div class="page-box">
			<div class="p10">
			<div id="caseList"></div>
		</div>
		<!-- end -->
	</div>
		<div id="contactsDialog"  style="display: none">
		<script type="text/javascript">
		var productUrl =  '<z:ukey key="com.cnfh.rms.businessProduct.findByCategoryId" context="admin"/>&jsoncallback=?';
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK,ZTOOL) {
				CALLBACK.productTypeChange = function(v,t){
					$("#productSubtype").ZCombobox({
		       		 	valueField: "id",
		                textField: "value",
		                url: productUrl+"&categoryId="+v
		       		});
				}
				//金额千分位
				function formatterAmount(r,value){
					if(value){return ZTOOL.formatCurrency(value+"");}  
					return '';
				}
				//案件列表 begin
				$('#caseList').ZTable({
			       url : "<z:ukey key='com.cnfh.rms.loanMiddleMonitor.listCaseApply' context='admin'/>&jsoncallback=?&controlType=middle",
			       isRowNum : false,
			       currentPage : 1, 
			       rows:10,
			       singleSelect: false,  
			       pagination:true,
			       idField: "id",
			       tableCls : 'table-index',//table的样式
			       columns:[[
			    	  	{field : 'caseApplyCode',title : '案件号',align : 'center'},
						{field : 'mechanismName',title : '机构名称',align : 'center'},
						{field : 'mainBorrower',title : '主借人', align : 'center'},
						{field : 'phoneNumber',title : '联系方式', align : 'center'},
						{field : 'productTypeName',title : '产品分类', align : 'center'},
						{field : 'productSubtypeName',title : '子产品', align : 'center'},
						{field : 'contractAmount',title : '合同金额(元)', align : 'center',formatter:formatterAmount},
						{field : 'overdueAmount',title : '逾期金额(元)', align : 'center',formatter:formatterAmount},
						{field : 'overdueDate',title : '逾期日期', align : 'center',formatter:function(r,v){
							if(v){return ZTOOL.strToDate(v+"");}  
							return '';
						}},
						{field : 'overdueDay',title : '逾期天数(天)', align : 'center'},
						{field : 'lastMonitorDate',title : '上次监控时间', align : 'center',formatter:function(r,v){
							if(v){ 
								return ZTOOL.strToDate(v+"");
							}
							return '';
						}},
						{field : 'id',title : '操作', align : 'center',formatter:function(r,v){
							return '<a href="javaScript:void(0)" class="btn-blue" onclick="viewCaseApply">详情</a>';
						}}
				] ],
				toolbar:[{
					id:'add',
					text:'导入汇发', 
					iconCls: 'icon-blue06', 
					buttonCls: 'btn-blue',
					handler: function (jq) {
						var rows = $('#caseList').ZTable("getSelections");
						var overdueAmount = 0.0;
						if(!rows || rows.length==0){
							$.ZMessage.info("提示", "请至少选择一条记录！", function () {
		                   });	 
							return false;
						}
						var ids=[];
						for (var k = 0; k < rows.length; k++) {
							ids.push(rows[k].id);
							if(rows[k].overdueAmount){   
								overdueAmount+=rows[k].overdueAmount;
							}
						}
						overdueAmount = overdueAmount.toFixed(2);        
						var importConnectLand = '<z:ukey key="com.cnfh.rms.loanMiddleMonitor.importConnectLand" context="admin"/>&ids='+ids.join(",")+"&number="+rows.length+"&overdueAmount="+overdueAmount;
	    	            ZDS_MESSAGE_CLIENT.openMenuLink('待发送清单','待发送清单',importConnectLand + "&openMethod=tabs");
					}
				}],
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
				//案件列表 end 
				  
				//新增详情
		        CALLBACK.viewCaseApply=function(v,t){   
					var pathParam = "&caseApplyId="+t.id+"&controlType=middle";  
                	ZDS_MESSAGE_CLIENT.openMenuLink('viewCaseApply', '案件详情', '<z:ukey key="com.zdsoft.finance.afterloan.base.initCaseApplyDetail" context="admin"/>'+pathParam);
                }
				//刷新
                //页面回调
                ZDS_MESSAGE_CLIENT.refreshThis=function(){
                	$('#caseList').ZTable("reload",{});
                };
				$.ZUI.init();  
				//点击查询
					$("#btn-search").click(function(){ 
						var formArray=$("#search_from").serializeArray();
						$("#caseList").ZTable("reload",formArray);
					}); 
				//点击重置
					$("#btn-reset").click(function() {
						$("#search_from")[0].reset();
						$("#applyDates").val("");     
						$("#applyDatee").val("");  
						$("#productType").val("");  
						$("#productSubtype").val("");
						$("#productType").ZCombobox("setValue","");
						$("#productSubtype").ZCombobox("setValue","");
					});
				});
		</script>
</body>
</html>