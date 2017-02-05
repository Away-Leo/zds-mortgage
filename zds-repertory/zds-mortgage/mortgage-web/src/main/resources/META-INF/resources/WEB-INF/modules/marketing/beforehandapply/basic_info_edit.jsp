<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
	<!-- 基本信息 -->
	<form id="beforehandApply_form" class="zui-form " method="post" enctype="multipart/form-data">
		<input type="hidden" id="beforehandApplyId" name="beforehandApplyVo.id" value="${beforehandApplyId }"> 
		<div class="page-box">
			<div class="page-title">基本信息</div>
			<div class="p5">
				<table class="table-detail"> 
					<tr>
						<td class="td-title pct10"><b class="c-red mr5">*</b>产品</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<input class="zui-combobox zui-validatebox" type="hidden" name="beforehandApplyVo.productTypeId"
										data-width="94"
										data-url="<z:ukey key='com.zdsoft.finance.getParentProduct' context='admin'/>&jsoncallback=?"
										data-callback="productTypeChange" data-id="isAgriculture" 
										data-defaultvalue="${beforehandApplyVo.productTypeId}"
										data-valuefield="id" data-textfield="text"
										validate-type="Require">
								</dd>
								<dd class="detail">
									<input class=" zui-combobox zui-validatebox" type="hidden" id="productSubtypeId" name="beforehandApplyVo.productSubtypeId"
										data-width="94" 
										validate-type="Require" value="${beforehandApplyVo.productSubtypeId}" >
								</dd>
							</dl>
						</td>
						<td class="td-title pct10">接单日期</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> 
									<input class="zui-date zui-validatebox" type="text" id="isApplyDate"  value="" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'applyDate'})"  readonly />
									<input type="hidden" id="applyDate"  name="beforehandApplyVo.applyDate" value="${beforehandApplyVo.applyDate }"/>
								</label>
								</dd>
							</dl>
						</td>
						<td class="td-title pct10"><b class="c-red mr5">*</b>贷款用途</td>
						<td class="pct30">
							<dl class="form-item form-auto">
								<dd class="detail">
									<input class="zui-combobox zui-validatebox" type="hidden" name="beforehandApplyVo.capitalUseFor"
										 data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0055"
										 data-defaultvalue="${beforehandApplyVo.capitalUseFor }"
										data-valuefield="fullcode" data-textfield="name" validate-type="Require">
								</dd>
							</dl>
						</td>
					</tr>
					<tr>
						<td class="td-title pct10"><b class="c-red mr5">*</b>终端</td>
						<td>
							<dl class="form-item form-auto">
								<dd class="detail">
									<input class="zui-combobox zui-validatebox" type="hidden" name="beforehandApplyVo.terminalId"
										data-url="<z:ukey key='com.zdsoft.finance.cooperator.cooperatorSimpleCode' context='admin'/>&jsoncallback=?&createOrgCd=${empDto.orgCd}"
										data-valuefield="id" data-textfield="terminalFullName" 
										data-defaultvalue="${beforehandApplyVo.terminalId}"   
										validate-type="Require">
								</dd>
							</dl>
						</td>
						<td class="td-title pct10">拓展经理</td>
						<td>
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" readonly  class="zui-input zui-disabled" name="beforehandApplyVo.developmentManager" value="${developmentManagerName }"/>
									</label>
								</dd>
							</dl>
						</td>
						<td class="td-title pct10">拓展部门</td>
						<td>
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text"  readonly class="zui-input zui-disabled" name="beforehandApplyVo.developmentDepartment" value="${developmentDepartmentName }"/>
									</label>
								</dd>
							</dl>
						</td>
					</tr>
					<tr>
						<td class="td-title pct10">资信员</td>
						<td>
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${beforehandApplyVo.creditMember }" class="zui-input" name="beforehandApplyVo.creditMember"/>
									</label>
								</dd>
							</dl>
						</td>
						<td class="td-title pct10">评估价抵押成数</td>
						<td>
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${beforehandApplyVo.assessedPriceMortgage }" class="zui-input" name="beforehandApplyVo.assessedPriceMortgage"/>
									</label>
								</dd>
							</dl>
						</td>
						<td class="td-title pct10">贷款成数</td>
						<td>
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${beforehandApplyVo.loanNumber }" class="zui-input" name="beforehandApplyVo.loanNumber"/>
									</label>
								</dd>
							</dl>
						</td>
					</tr>
				</table>
			</div>
			</div>
			<div class="page-box">
				<div class="page-title">贷款信息</div>
				<div class="p5">
					<table class="table-detail">
						<tr>
							<td class="td-title pct10"><b class="c-red mr5">*</b>申请金额</td>
							<td class="pct20">
								<dl class="form-item form-auto">
									<dd class="detail">
										<label> 
											<input type="text" class="zui-input zui-validatebox" value="${beforehandApplyVo.applyAmount }" name="beforehandApplyVo.applyAmount" validate-type="Require,Digital[10-2]"/><front style="font-size: 14px;">元</front>
										</label>
									</dd>
								</dl>
							</td>
							<td class="td-title pct10"><b class="c-red mr5">*</b>贷款期限</td>
							<td class="pct20">
								<dl class="form-item form-auto">
									<dd class="detail">
											<label> <input type="text" class="zui-input nwidth2 zui-validatebox" value="${beforehandApplyVo.applyDeadline }" name="beforehandApplyVo.applyDeadline" validate-type="Require,Integer"/>
											</label>
										</dd>
									<dd class="detail">
											<input class="zui-combobox zui-validatebox"  type="hidden" name="beforehandApplyVo.applyDeadlineUnit" value=""
												data-width="94"
					                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=0931"
					                           data-valuefield="fullcode" data-textfield="name" data-defaultvalue="${beforehandApplyVo.applyDeadlineUnit }" validate-type="Require" >
									</dd>
								</dl>
							</td>
							<td class="td-title pct10">资金来源</td>
							<td class="pct30">  
								<dl class="form-item form-auto">
									<dd class="detail">
										<input class="zui-combobox" type="hidden" name="beforehandApplyVo.capitalSource"
											data-url="<z:ukey key='com.zdsoft.finance.capital.getCapitalistList' context='admin'/>&jsoncallback=?"
											data-defaultvalue="${beforehandApplyVo.capitalSource}"     
											data-valuefield="id" data-textfield="cooperatorName">  
									</dd>
								</dl>
							</td>
						</tr>
					</table>
				</div>				
			</div>	
		</form>
	
	<script type="text/javascript">
	var productUrl =  '<z:ukey key="com.zdsoft.finance.businessProduct.findByCategoryIdAndOrgCd" context="admin"/>&jsoncallback=?';
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/make-first-py'], 
		function($, CALLBACK) {
		//回显接单时间
		var isApplyDate = $("#applyDate").val();
		if(isApplyDate){
			$("#isApplyDate").val(isApplyDate.substring(0,4)+"-"+isApplyDate.substring(4,6)+"-"+isApplyDate.substring(6,8));
		}
		CALLBACK.productTypeChange = function(v,t){
			$("#productSubtypeId").ZCombobox({
       		 	valueField: "id",
                textField: "value",
                url: productUrl+"&categoryId="+v
       		});
		}
		if('${beforehandApplyVo.productSubtypeId}'){
			setTimeout(function(){
			$("#productSubtypeId").ZCombobox({
       		 	valueField: "id",
                textField: "value",
                url: productUrl+"&categoryId=${beforehandApplyVo.productTypeId}"
       		});
			},300);
		}
	});
	
	</script>
</body>
</html>