<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">

<%@ include file='../../common/common_js.jsp'%>
<title>待发送清单列表</title>
</head>
<body>
<div class="save">  
	    <button id="submitBeforehandApply" class="btn-blue mr10">提交</button>
	</div>
	<div class="page-box">
	<div class="p10">
	<div class="page-box">  
		<!-- 查询区域 -->
		<div class="page-title">基本信息</div>
		<div class="p5">
		     <table class="table-detail">
		           <tr>
		               <td class="td-title pct10">发送人</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                          <dd class="detail f12">
		                              <label>
		                              ${sender}
		                              </label>
		                          </dd>
		                   </dl>
		               </td>
		               <td class="td-title pct10">发送时间</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                       <dd class="detail f12">
		                           <label>
		                               ${sendTime}
		                           </label>
		                       </dd>
		                   </dl>
		               </td>
		               <td class="td-title pct10">数据数</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                       <dd class="detail f12">
		                           <label id="number">
		                               ${param.number}
		                           </label>
		                       </dd>
		                   </dl>
		               </td>
		           </tr>
		           <tr>
		               <td class="td-title pct10">逾期总金额</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                          <dd class="detail f12">
		                              <label id="overdueAmount">
		                              ${param.overdueAmount}
		                              </label>元   
		                          </dd>
		                   </dl>
		               </td>
		               <td class="td-title pct10"></td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                          <dd class="detail f12">
		                              <label>
		                              </label>
		                          </dd>
		                   </dl>
		               </td>
		               <td class="td-title pct10"></td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                          <dd class="detail f12">
		                              <label>
		                              </label>
		                          </dd>
		                   </dl>
		               </td>
		           </tr>
		     </table>
		</div>
		</div>
		<!-- 案件列表 -->
		<!-- begin -->
		<div class="page-box">
			 <div class="page-title">发送清单(<a style="color: FF6600;">请仔细确认以下清单</a>)
			  </div>
			<div class="p10">
			<div id="caseList"></div>
		</div>
		<!-- end --> 
	</div>
	</div>
	</div>   
		<script type="text/javascript">
		var productUrl =  '<z:ukey key="com.cnfh.rms.businessProduct.findByCategoryId" context="admin"/>&jsoncallback=?';
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK,ZTOOL) {
				//金额千分位
				function formatterAmount(r,value){
					if(value){return ZTOOL.formatCurrency(value+"");}  
					return '';
				}
				
				//案件列表 begin
				$('#caseList').ZTable({
			       url : "<z:ukey key='com.cnfh.rms.loanMiddleMonitor.listCaseApply' context='admin'/>&jsoncallback=?&controlType=middle&ma|id|IN|S=${param.ids}",
			       isRowNum : false,
			       currentPage : 1, 
			       rows:10,
			       singleSelect: true,  
			       pagination:false,
			       idField: "id",
			       tableCls : 'table-index',//table的样式
			       columns:[[
			    	  	{field : 'caseApplyCode',title : '案件号',align : 'center'},
						{field : 'mechanismName',title : '机构名称',align : 'center'},
						{field : 'mainBorrower',title : '主借人', align : 'center'},
						{field : 'phoneNumber',title : '联系方式', align : 'center'},
						{field : 'productSubtypeName',title : '子产品', align : 'center'},
						{field : 'contractAmount',title : '合同金额(元)', align : 'center',formatter:formatterAmount},
						{field : 'overdueAmount',title : '逾期金额(元)', align : 'center',formatter:formatterAmount},
						{field : 'overdueDate',title : '逾期日期', align : 'center',formatter:function(r,v){
							if(v){return ZTOOL.strToDate(v+"");}       
							return '';
						}},
						{field : 'overdueDay',title : '逾期天数(天)', align : 'center'},
						
						{field : 'id',title : '操作', align : 'center',formatter:function(r,v){
							return '<a href="javaScript:void(0)" class="btn-blue" onclick="deleteContactHandle">删除</a>';
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
				//案件列表 end
				//删除行
				CALLBACK.deleteContactHandle=function(index, rowData){
					$("#number").text($("#number").text()-1);
					if(rowData.overdueAmount){
						$("#overdueAmount").text($("#overdueAmount").text()-rowData.overdueAmount);
					}
					//模拟点击选中事件
					$($('#caseList .datagrid-body').find("tr")[index]).trigger("click");
					$('#caseList').ZTable("deleteRow");
				}
				//保存或者提交
				function saveOrSubmit(isSubmit){
					var rows = $('#caseList').ZTable("getRows");
					if(rows.length==0){  
						$.ZMessage.info("提示", "请至少添加一条记录！", function () {});	 
						return false;
					}
					var ids=[];
					for(var i=0;i<rows.length;i++){
						ids.push(rows[i].id);
					}
					$.ajax({
	                       type: 'post',
	                       url: '<z:ukey key="com.cnfh.rms.loanMiddleMonitor.saveOrsubmitConnectLand" context="admin"/>',
	                       data: {ids:ids.join(","),isSubmit:isSubmit},
	                       dataType: 'json',
	                       success: function (data) {
	                           if (data.resultStatus == 0) {
	                        	   var msg = "保存成功！";
	                        	   if(isSubmit){
	                        		   msg = "提交成功！";
	                        	   }
	                        		$.ZMessage.success("成功", msg, function () {
	                               		if(isSubmit){
	                               			setTimeout(function(){
	        	     		                	ZDS_MESSAGE_CLIENT.refreshOpenner();
	        	     		                	ZDS_MESSAGE_CLIENT.closeSelf();
	        	     		                },200);
	                               		}
	                                 });
	                           }else{
		                           	$.ZMessage.error("错误", data.msg, function () {
			                        });
	                           }
	                       },
			            error: function () {
			            	$.ZMessage.error("错误", "保存信息系统异常，请联系管理员", function () {
			             });
			            }
	                   });
				}
				  //提交
				$("#submitBeforehandApply").click(function(){
					saveOrSubmit(true);
				});
				
				
				
				$.ZUI.init();
				});
		</script>
</body>
</html>