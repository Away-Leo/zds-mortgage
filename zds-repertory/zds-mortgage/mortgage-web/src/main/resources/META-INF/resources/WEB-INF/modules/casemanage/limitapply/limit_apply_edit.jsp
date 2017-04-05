<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang=>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../common/common_js.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>额度申请</title>
</head>
<body>
	<div class="save">
	    <button id="caseLimitApplySave" class="btn-blue mr10">申请额度</button>
	    <button id="cancelLimitApplyReturn" class="btn-blue mr10">返回</button>
	</div>  
	<div  class="frm-content frm-bottom">
		<div class="page-box">  
			<div class="p10">
		<form id="client_form" class="zui-form" method="post" enctype="multipart/form-data">
				<!-- 案件id -->
				<input type="hidden" name="caseApplyId" value="${param.caseApplyId }">
				<!-- 资金计划id -->
				<input type="hidden" name="fundPlanId" value="${vo.fundPlanId }">
				<!-- 资金计划名称 -->
				<input type="hidden" name="fundPlanName" value="${vo.fundPlanName }">
				<!-- 贷款发放账户机构名称 -->
				<input type="hidden" name="loanOutAccountBranchName" value="${vo.loanOutAccountBranchName }">
				<!-- 贷款发放账户账户名 -->
				<input type="hidden" name="loanOutAccountName" value="${vo.loanOutAccountName }">
				<!-- 贷款发放账户 -->
				<input type="hidden" name="loanOutAccount" value="${vo.loanOutAccount }">
				<!-- 贷款回款账户机构名称 -->
				<input type="hidden" name="loanBackAccountBranchName" value="${vo.loanBackAccountBranchName }">
				<!-- 贷款回款账户账户名 -->
				<input type="hidden" name="loanBackAccountName" value="${vo.loanBackAccountName }">
				<!-- 贷款回款账户 -->
				<input type="hidden" name="loanBackAccount" value="${vo.loanBackAccount }">
				
				<!-- 资金来源（出资人信息） -->  
				<div class="page-box">
					<div class="page-title">资金来源(出资人信息)</div>
					<div class="p5">
						<table class="table-detail">
							<tr>
								<td class="td-title pct10">案件号</td>
								<td class="pct20">
									<dl class="form-item form-auto">
										<dd class="detail f12">
											<label>${param.caseApplyCode }</label> 
										</dd>
									</dl>
								</td>
								<td class="td-title pct10">主借人</td>
								<td class="pct20">
									<dl class="form-item form-auto">
										<dd class="detail f12">
											<label>${param.customerName }</label>
										</dd>
									</dl> 
								</td>
								<td class="td-title pct10">子产品</td>
								<td class="pct20"> 
									<dl class="form-item form-auto">
										<dd class="detail f12">
											<label>${param.productName }</label>
										</dd>
									</dl>
								</td>
							<tr>
								<td class="td-title pct10">抵押类型</td>
								<td class="pct20">
									<dl class="form-item form-auto">
										<dd class="detail f12">
											<label>${param.pledgeType }</label> 
										</dd>
									</dl>
								</td>
								<td class="td-title pct10">资金来源</td>
								<td class="pct20">
									<dl class="form-item form-auto">
										<dd class="detail f12">
											<label>${cooperatorName }</label>
										</dd>
									</dl> 
								</td>
								<td class="td-title pct10">资金计划名称</td>
								<td class="pct20"> 
									<dl class="form-item form-auto">
										<dd class="detail f12">
											<label>${vo.fundPlanName }</label>
										</dd>
									</dl>
								</td>
							</tr>
							<tr>
								<td class="td-title pct10">额度状态</td>
								<td class="pct20">
									<dl class="form-item form-auto">
										<dd class="detail f12">
											<label>未申请</label> 
										</dd> 
									</dl>
								</td>
								<td class="td-title pct10"></td>
								<td class="pct20">
									<dl class="form-item form-auto">
										<dd class="detail f12">
											<label></label>
										</dd>
									</dl> 
								</td>
								<td class="td-title pct10"></td>
								<td class="pct20"> 
									<dl class="form-item form-auto">
										<dd class="detail f12">
											<label></label>
										</dd>
									</dl>
								</td>
							</tr>
							</table>
						</div>
					</div>	
					<!-- 货款发放账户信息 -->
					<div class="page-box">
						<div class="page-title">货款发放账户信息</div>
						<div class="p5">
							<table class="table-detail">
								<tr>
									<td class="td-title pct10">机构名称</td>
									<td class="pct20">
										<dl class="form-item form-auto">
											<dd class="detail f12">
												<label>${vo.loanOutAccountBranchName }</label> 
											</dd>
										</dl>
									</td>
									<td class="td-title pct10">账户名</td>
									<td class="pct20">
										<dl class="form-item form-auto">
											<dd class="detail f12">
												<label>${vo.loanOutAccountName }</label>
											</dd>
										</dl> 
									</td>
									<td class="td-title pct10">账号</td>
									<td class="pct20"> 
										<dl class="form-item form-auto">
											<dd class="detail f12">
												<label>${vo.loanOutAccount }</label>
											</dd>
										</dl>
									</td>
								</table>
							</div>
						</div>	
					<!-- 货款发放账户信息 end -->
					<!-- 货款回款账户信息 -->
					<div class="page-box">
						<div class="page-title">货款回款账户信息</div>
						<div class="p5">
							<table class="table-detail">
								<tr>
									<td class="td-title pct10">机构名称</td>
									<td class="pct20">
										<dl class="form-item form-auto">
											<dd class="detail f12">
												<label>${vo.loanBackAccountBranchName }</label> 
											</dd>
										</dl>
									</td>
									<td class="td-title pct10">账户名</td>
									<td class="pct20">
										<dl class="form-item form-auto">
											<dd class="detail f12">
												<label>${vo.loanBackAccountName }</label>
											</dd>
										</dl>   
									</td>
									<td class="td-title pct10">账号</td>
									<td class="pct20"> 
										<dl class="form-item form-auto">
											<dd class="detail f12">
												<label>${vo.loanBackAccount }</label>
											</dd>
										</dl>
									</td>
								</table>
							</div>
						</div>	
					<!-- 货款回款账户信息 end -->
					<!-- 额度信息--> 
					<div class="page-box">
						<div class="page-title">额度信息</div>
						<div class="p5">
							<table class="table-detail">
								<tr>  
									<td class="td-title pct10"><b class="c-red mr5">*</b>当前额度申请</td> 
									<td class="pct20">     
										<dl class="form-item form-auto">        
											<dd class="detail"> 
											<label> 
													<input type="text" class="zui-input zui-validatebox" value="${applyAmount }" validate-type="Require,Amount,CompareAmount[<=-${applyAmount }]" name="applyLimitAmount" validate-false="|输入金额不正确|输入金额不得大于${applyAmount }" /><front class="word">元</front>
											</label>
											</dd> 
										</dl>
									</td>
									<td class="td-title pct10">是否已获得备付资金分配</td>
									<td class="pct20">
										<dl class="form-item form-auto">
											<dd class="detail f12">
												<label>否</label> 
											</dd>
										</dl> 
									</td>
									<td class="td-title pct10"></td>
									<td class="pct20"> 
										<dl class="form-item form-auto">
											<dd class="detail f12">
												<label></label>
											</dd>
										</dl>
									</td>
								</table>
							</div>
						</div>	
					<!-- 额度信息 end -->
					<!-- 当前申请信息-->
					<div class="page-box">
						<div class="page-title">当前申请信息</div>
						<div class="p5">
							<table class="table-detail">
								<tr>
									<td class="td-title pct10">申请人</td>
									<td class="pct20">
										<dl class="form-item form-auto">
											<dd class="detail f12">
												<label>${empNm }</label> 
											</dd>
										</dl>
									</td>
									<td class="td-title pct10">申请日期</td>
									<td class="pct20">
										<dl class="form-item form-auto">
											<dd class="detail f12">  
												<label>${currentDate }</label>
											</dd>
										</dl> 
									</td>
									<td class="td-title pct10"></td>
									<td class="pct20"> 
										<dl class="form-item form-auto">
											<dd class="detail f12">
												<label></label>
											</dd>
										</dl>
									</td>
								</table>
							</div>
						</div>	
					<!-- 当前申请信息 end -->
					
					</form>
					
					<!-- 本案件申请记录 -->
					<div class="page-box">
						 <div class="page-title">本案件申请记录
						  </div>
						<div class="p10">
						<div id="costitemList"></div>
					</div>
					<!-- 本案件申请记录 end -->
			</div>
		</div>
	</div>
</body>



<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.loading','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/jquery.zds.form'], 
			function($, CALLBACK, ZTOOL,Loading, ZUI_MESSAGE_CLIENT) {
			
			//金额千分位
			function formatterAmount(r,value){
				if(value){return ZTOOL.formatCurrency(value+"");}  
				return '';
			}
			
			//点击申请额度按钮之后的处理    
			$("#caseLimitApplySave").click(function(){
				var isValidateLimitApply = $.ZUI.validateForm($('#client_form'));
				if(!isValidateLimitApply){
					return false;  
				}
				var params = $("#client_form").serialize();  
				Loading.show();
				$.ajax({
                    type: 'post',
                    url: '<z:ukey key="com.cnfh.rms.casemanage.saveCaseLimitApply" context="admin"/>',
                    data: params,
                    dataType: 'json',  
                    success: function (data) {
                    	Loading.hide();
                        if (data.resultStatus == 0) {
                        	$.ZMessage.success("提示", data.msg, function () {
                        		setTimeout(function(){
                                 	ZDS_MESSAGE_CLIENT.refreshOpenner();
                                 	ZDS_MESSAGE_CLIENT.closeSelf();
                                 },200);
                          	 });
                        }else{
                        	$.ZMessage.error("错误", data.msg, function () {
	                        });
                        }
                   	 },
			            error: function () {
			            	Loading.hide();    
			            	$.ZMessage.error("错误", "额度申请信息系统异常，请联系管理员", function () {
			             });
			            }
                });
			});
			//本案件申请记录
			$('#costitemList').ZTable({
			       url : "<z:ukey key='com.cnfh.rms.casemanage.hostoryCaseLimitApplyList' context='admin'/>&caseApplyId=${param.caseApplyId}",
			       isRowNum : true,
			       currentPage : 1,  
			       rows:20,  
			       singleSelect: true,  
			       pagination:false,  
			       idField: "id", 
			       tableCls : 'table-index',//table的样式  
			       columns:[[ 
			    	  	{field : 'id',title : 'id',align : 'center',hidden:true},
			    	  	{field : 'applyLimitAmount',title : '申请额度（元）',align : 'center',formatter:formatterAmount},
						{field : 'fundPlanName',title : '分配信托计划',align : 'center'}, 
						{field : 'applyEmpName',title : '申请人', align : 'center'},
						{field : 'allotDate',title : '分配日期', align : 'center',formatter:function(r,v){
							if(v){return ZTOOL.strToDate(v+"");}
	  						return '';  
						}},    
						{field : 'cancelDate',title : '取消日期', align : 'center',formatter:function(r,v){
							if(v){return ZTOOL.strToDate(v+"");}
	  						return '';   
						}},    
						{field : 'cancelEmpName',title : '取消人', align : 'center'}
				] ],
				onDelete:function(index, rowData) {
					//  添加判断
					return true;
				},  
				onLoadSuccess:function() {
					$("td").each(function(){  
						if($(this).text()=='null'){
							$(this).text('');   
						}
					});
				}
				});
			
			
			//返回
			$("#cancelLimitApplyReturn").click(function(){
				ZDS_MESSAGE_CLIENT.refreshOpenner();
	         	ZDS_MESSAGE_CLIENT.closeSelf();
					
			});
			$.ZUI.init();
		});
		
		
	</script>

</html>