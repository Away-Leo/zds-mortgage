<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
	
	<!-- 费用减免特批申请 -->
	<div class="page-box">
		<h1 class="page-title">费用减免特批申请</h1>
		<div class="p5">
			<table class="table-detail">
				<tr>
					<td class="td-title pct10">案件号</td>
					<td class="pct20">
						${remissionBasicVo.caseApplyCode }
					</td>
					<td class="td-title pct10">编号</td>
					<td class="pct20">
						${remissionBasicVo.remissionCode }
					</td>
					<td class="td-title pct10">放款金额</td>
					<td class="pct30">
						${remissionBasicVo.loanAmount }
					</td>
				</tr>
				<tr>
					<td class="td-title pct10">申请人</td>
					<td class="pct20">
						${remissionBasicVo.applyUserName }
					</td>
					<td class="td-title pct10">申请人部门</td>
					<td class="pct20">
						${remissionBasicVo.applyDeptName }
					</td>
					<td class="td-title pct10">申请时间</td>
					<td class="pct30">
						${remissionBasicVo.applyDate }
					</td>
				</tr>
				<tr>
					<td class="td-title pct10">机构</td>
					<td class="pct20">
						${remissionBasicVo.orgName }
					</td>
					<td class="td-title pct10">主借人</td>
					<td class="pct20">
						${remissionBasicVo.mainBorrowName }
					</td>
					<td class="td-title pct10">子产品</td>
					<td class="pct30">
						${remissionBasicVo.productSubtypeName }
					</td>
				</tr>
				<tr>
					<td class="td-title pct10">本金余额</td>
					<td class="pct20">
						${remissionBasicVo.principalBalance }
					</td>
					<td class="td-title pct10">垫资天数</td>
					<td class="pct20">
						${remissionBasicVo.loaningDays }
					</td>
					<td class="td-title pct10">逾期天数</td>
					<td class="pct30">
						${remissionBasicVo.overdueDays }
					</td>
				</tr>
				<tr>
					<td class="td-title pct10"><b class="c-red mr5">*</b>罚息挂钩标准</td>
					<td class="pct20">
						<dl class="form-item">
				         	<dd class="detail">
				                <div id="us"> <!-- 校验罚息挂钩标准 -->
				                   	<input class="zui-combobox zui-validatebox" type="hidden" id="penaltyUseStandard"
			                             data-width="190" data-toggle="combobox" data-height="300" value="${remissionBasicVo.penaltyUseStandard }"
			                             data-data="[{'id':'0','text':'当前本金'},{id:'1','text':'剩余本金'}]"
			                             validate-type="Require" data-defaultvalue="0"
			                             data-valuefield="id" data-textfield="text" />
								</div>
				            </dd>
				       	 </dl>
					</td>
					<td class="td-title pct10">放款日期</td>
					<td class="pct20">
						${remissionBasicVo.loanDate }
					</td>
					<td class="td-title pct10">本次还款日期</td>
					<td class="pct30">
						${remissionBasicVo.currentReceiveDate }
					</td>
				</tr>
			</table>
		</div>
	</div>
	
	<!-- 本次减免费用明细 -->
	<div class="page-box">
		<h1 class="page-title">本次减免费用明细</h1>
		<div class="p5">
			<div id="remissionDetail"></div>
		</div>
	</div>
	
	<!-- 备注 -->
	<div class="page-box">
		<h1 class="page-title">备注</h1>
		<div class="p5">
			<table class="table-detail">
				<tr>
					<td class="td-title pct10">申请事由</td>
					<td colspan="5">
                         <label>
                       		 <textarea id="remark" class="zui-area row-width zui-validatebox"
                       		 validate-type="Length[0-512]" placeholder="最多可以输入512个字符" >${remark }</textarea>
                         </label>
					</td>
				</tr>		
			</table>
		</div>
	</div>
	
	<!-- 引入附件 -->
	<div id="freeAttachment"></div>
	
	<!-- 新增减免项弹窗 -->
	<div id="remissionAdd" style="display:none">
		 <div id="propertyEditDiv" class="p10">
			 <dl class="form-item">
				<dt class="title"><b class="c-red mr5">*</b>罚息挂钩标准：</dt>
	         	<dd class="detail">
	                <label>
	                	<input id="penaltyUseStandardText" class="zui-input" disabled="disabled" type="text">
	                </label>
	            </dd>
	       	  	<dt class="title"><b class="c-red mr5">*</b>减免项目：</dt>
	            <dd class="detail">
					<input class="zui-combobox zui-validatebox" type="hidden" id="remissionItem"
                            data-width="190" data-toggle="combobox" 
                            data-data="[{'code':'YWDM00120004','name':'罚息'},{'code':'YWDM00120009','name':'违约金'}]"
                            data-callback="getItemAmount"
                            data-height="300" validate-type="Require"
                            data-valuefield="code" data-textfield="name">
			    </dd>
	       	 </dl>
	       	
	       	 <dl class="form-item block">
	       		<dt class="title">应收金额（元）：</dt>
				<dd class="detail">
					<label>
						<input type="text"  class="zui-input zui-validatebox" id="receivedAmount" disabled="disabled" value="0"/>
					</label>
				</dd>
	       		 <dt class="title">实收金额（元）：</dt>
	             <dd class="detail">
	              	<label> 
	              		<input type="text" class="zui-input zui-validatebox" id="expectedAmount" validate-type="Require,Digital[7-4]" name="expectedAmount" value="0"/>
					</label>
	             </dd>
	       	</dl>
	       	<dl class="form-item">
	       	   	<dt class="title">减免金额（元）：</dt>
                <dd class="detail">
                    <input class="zui-input zui-validatebox" validate-type="Require" id="annulAmount" disabled="disabled" name="annulAmount" value="0">
                </dd>
	        </dl>
	    </div>
	</div>

<script type="text/javascript">
	seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.table', 'zd/jquery.zds.form','zd/jquery.zds.dialog'],
		   function ($, callback) {
		
		//应收金额
		var receivedAmount = 0;
		//实收表ID
		var repaymentAmountId = "",isAdd = true,missionIndex = "";
		
		
		callback.getItemAmount = function(v,t){
			if(!v){
				$("#receivedAmount").val("0");
				return;
			}
			
			var penaltyUseStandard = $("#penaltyUseStandard").ZCombobox("getValue");
			//查询费用应收金额
			$.ajax({
				method:"post",
				async:"false",
				dataType:"json",
				url:'<z:ukey key="com.zdsoft.finance.specialApprove.getReceiveAmount" context="admin"/>&jsoncallback=?',
				data:{"penaltyUseStandard":penaltyUseStandard,"amountTypeCode":v,"caseApplyId":"${caseApplyId }"},
				success:function(datas){
					if(datas.resultStatus == 0){
						if(datas.optional){
							//设置应收金额
							$("#receivedAmount").val(datas.optional.receiveAmount);
							receivedAmount = datas.optional.receiveAmount;
							repaymentAmountId = datas.optional.repaymentAmountId;
							//实收金额
							var expectedAmount = $("#expectedAmount").val();
							if(receivedAmount!=null && expectedAmount!=undefined && (datas.optional.receiveAmount - expectedAmount)>=0){
								$("#annulAmount").val(Subtr(datas.optional.receiveAmount , expectedAmount));
							}else{
								$("#annulAmount").val(0);
							}
						}
					}
				}
			})
		}
		
		 //初始化弹窗
		 $('#remissionAdd').Zdialog({ 
			title: '新增减免项',
	        width: 700,
	        height: 230,
	        closed: true,
	        buttons: [{
                 id: 'message-btn', text: '确定',
                 handler: function () {
	               	  var rowDatas = $("#remissionDetail").ZTable("getRows");
	               	  var status = true; 
	               	  
	               	  $(rowDatas).each(function(index,row){//防止重复添加项
	               		  if(row.beRemissionItemCode == $("#remissionItem").ZCombobox("getValue") && isAdd){
	               			  $.ZMessage.error("提示","'" + $("#remissionItem").ZCombobox("getText")+"'  不能重复添加",function(){});
	               			  status = false; 
	               		  }
	               	  });
	               	  if(!status){
	               		  return false;
	               	  }
	               	  
	               	  //验证
	               	  var isValidateForm = $.ZUI.validateForm('#propertyEditDiv'); 
	               	  if(!isValidateForm){
	               		  return false;
	               	  }
	               	  
	               	  var beRemissionItemName = $("#remissionItem").ZCombobox("getText");
	               	  var beRemissionItemCode = $("#remissionItem").ZCombobox("getValue");
	               	  var receivedAmount = $("#receivedAmount").val();
	               	  var expectedAmount = $("#expectedAmount").val();
	               	  if(Number(receivedAmount) == 0){
	               		 $.ZMessage.error("提示","应收为0",function(){});
	               		  return false;
	               	  }
	               	  if(Number(expectedAmount) > Number(receivedAmount)){
	               		  $.ZMessage.error("提示","实收金额大于应收金额，请检查！",function(){});
	               		  return false;
	               	  }
	               	  
	               	  //拼装参数
	               	  var row = {"beRemissionItemCode":beRemissionItemCode,"beRemissionItemName":beRemissionItemName,
	               			  "receivedAmount":receivedAmount,"expectedAmount":expectedAmount,"annulAmount":$("#annulAmount").val(),
	               			  "penaltyUseStandard":$("#penaltyUseStandard").ZCombobox("getValue"),"repaymentAmountId":repaymentAmountId};
	               	  if(isAdd){ //新增
		               	  $('#remissionDetail').ZTable("addOneRow", row);
	               	  }else{ //编辑
	               		 var arr = [];
                         arr[0] = missionIndex;
                         arr[1] = row;
                         $('#remissionDetail').ZTable("editOneRow", arr);
		              }
	               	  
	                  $("#remissionAdd").Zdialog("close");
                 }
             },
             {
                 id: 'message-btn', text: '取消', buttonCls: 'btn-gray',
                 handler: function () {
                     $("#remissionAdd").Zdialog("close");
                 }
             }]
		 });
		 
		 $('#remissionDetail').ZTable({
	         toolbar: [
	             {
	                 id: 'btncut09',
	                 text: '添加',
	                 iconCls: 'icon-btn08',
	                 buttonCls: 'btn-orange',
	                 handler: function () {
	                	isAdd = true;
	                	var penaltyUseStandard = $("#penaltyUseStandard").ZCombobox("getText");
	                	$("#penaltyUseStandardText").val(penaltyUseStandard);
	                	$("#expectedAmount").val(0);
	                	$('#remissionAdd').Zdialog("setTitle","新增减免项");
	                	$('#remissionAdd').Zdialog("open");
	                 }
	             },
	             {
	                 id: 'btncut4',
	                 text: '删除',
	                 iconCls: 'icon-btn12',
	                 buttonCls: 'btn-gray',
	                 handler: function () {
	                	 $.ZMessage.question("提示","确认删除当前记录",function(){
		                     $('#remissionDetail').ZTable("deleteRow");
	                	 });
	                 }
	             }
	         ],
	         columns: [[
	             {field: 'beRemissionItemCode', title: '收费项目code', align: 'center',hidden:true, width: '0%' },
	             {field: 'beRemissionItemName', title: '收费项目', align: 'center', width: '22%' },
	             {field: 'receivedAmount', title: '应收金额（元）', align: 'center', width: '22%' },
	             {field: 'expectedAmount', title: '实收金额（元）', align: 'center', width: '22%' },
	             {field: 'annulAmount', title: '减免金额（元）', align: 'center', width: '22%'},
	             {field: 'penaltyUseStandard', title: '罚息挂钩标准', align: 'center',hidden:true, width: '0%'},
	             {field: 'repaymentAmountId', title: '实收ID', align: 'center',hidden:true, width: '0%'},
	             {field: 'ids', title: '操作', align: 'center', width: '15%',formatter:function(){
	            	 return '<a href="javaScript:void(0)" onclick="editRemission" class="btn-blue">编辑</a>';
	             }}
	         ]],
	         columnsType: [
	             {
	            	 "beRemissionItemName": {
	                     "inputType": "text",
	                     "validateType": "Require"
	                 },
	                 "receiveAmount": {
	                     "inputType": "text"
	                 },
	                 "expectedAmount": {
	                     "inputType": "input"
	                 },
	                 "annulAmount": {
	                     "inputType": "text"
	                 }
	             },
	             {
	                 "inputWidth": 100,
	                 "inputHeight": 24,
	                 "areaWidth": 100,
	                 "areaHeight": 24
	             }
	         ],
	         idField: 'ids',//每行数据的，唯一标识符
	         //queryParams: {param: 'xxooxxooxxoo000000000000000000'},//分页业务参数
	         url: '<z:ukey key="com.zdsoft.finance.specialApprove.queryRemissionItemDetail" context="admin"/>&jsoncallback=?&specialApproveManageId=${specialApproveManageId }',
	         singleSelect: false,//表格行是单选还是多选
	         isRowNum: true,//是否显示行号
	         rows: 8,//分页情况下，显示的条数
	         currentPage: 1,//分页情况下的，当前页
	         pagination: false,//表格是否分页
	         tableCls: 'table-index',//table的样式
	         onEdit:false,
	         batchSave: false//默认为true，是否批量保存
	    });
		 
		 callback.editRemission=function(index,rowData){
			 isAdd = false;
			 missionIndex = index;
			 //赋值
			 var penaltyUseStandard = $("#penaltyUseStandard").ZCombobox("getText");
             $("#penaltyUseStandardText").val(penaltyUseStandard);
             
             $("#receivedAmount").val(rowData.receivedAmount);
             $("#expectedAmount").val(rowData.expectedAmount);
             $("#annulAmount").val(rowData.annulAmount);
             $("#remissionItem").ZCombobox("setValue",rowData.beRemissionItemCode);
             
             $('#remissionAdd').Zdialog("setTitle","编辑减免项");
             $('#remissionAdd').Zdialog("open");
		 }
		 
		//提交
		function saveData(isSubmit,datas){
			var url='<z:ukey key="com.zdsoft.finance.specialApprove.saveSpecialApproveRemissionApply" context="admin"/>&jsoncallback=?';
			//获取费用减免明细
			var remissionDetail = $('#remissionDetail').ZTable("getRows");
			if(remissionDetail.length == 0){
				$.ZMessage.error("提示", "减免明细不能为空", function(){});		
				return;
			}
			var param = "remissionDetail=" + encodeURIComponent(JSON.stringify(remissionDetail)) + "&caseApplyId=${caseApplyId }&remark="+$("#remark").val()+"&specialApproveManageId=${specialApproveManageId }";
			param += "&penaltyUseStandard="+$("#penaltyUseStandard").ZCombobox("getValue");
			$.ajax({
				method:'post',
				url:url + '&isSubmit='+ isSubmit,
				dataType: 'json',
				data:param,
				success:function(data){
					if (data.resultStatus == 0){
						//执行回调函数
                        ZDS_WORKFLOW_CLIENT.callBackFuntion(datas,ZDS_WORKFLOW_PARAM._STATUS_SUCCESS,data.msg);
					}else{
						//执行回调函数
						ZDS_WORKFLOW_CLIENT.callBackFuntion(datas,ZDS_WORKFLOW_PARAM._STATUS_ERROR,data.msg);
					}
				}
			});
		}
		
		//保存
	    ZDS_WORKFLOW_CLIENT.saveFunction = function (datas) {
			//流程中有修改页面，需要提交业务数据操作
			saveData(false,datas);
    	}
		
	  	//提交方法
	    ZDS_WORKFLOW_CLIENT.submitFunction = ZDS_WORKFLOW_CLIENT.saveFunction;
	  	
	    $.ZUI.initDiv();
	    $.ZUI.initDiv("#propertyEditDiv");
		
		/**
		 * 自动修改减免金额
		**/
		$("#expectedAmount").change(function(){
			var expectedAmount = $(this).val();
			if(expectedAmount <= receivedAmount){
				//减免金额
				$("#annulAmount").val(Subtr(receivedAmount,expectedAmount));
			}else
				$.ZMessage.error("提示","实收金额大于应收金额，请检查！",function(){});
		})
		
		//不损失精度的减免运算
		function Subtr(arg1,arg2){
			 var r1,r2,m,n; 
			 try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0} 
			 try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0} 
			 m=Math.pow(10,Math.max(r1,r2)); 
			 n=(r1>=r2)?r1:r2; 
			 return ((arg1*m-arg2*m)/m).toFixed(n); 
		}
	    
		$("#freeAttachment").load('<z:ukey key="com.zdsoft.finance.fileStore.fileShowListPage" context="admin"/>'+'&productId=${fileStoreVo.productId }' +
		'&linkCode=${fileStoreVo.linkCode }&caseApplyId=${caseApplyId }&businessId=${fileStoreVo.businessId }');
	});
</script>
