<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<!-- 编辑还款计划 -->
<div class="frm-content frm-bottom">
	<div class="page-box">
		<div class="p10">
			<form id="receivablePlanForm" class="zui-form " method="post" enctype="multipart/form-data">
				<div class="page-box">
					<input type="hidden" value="${caseApplyVo.id }" name="caseApplyId"/>
					<input type="hidden" value="${receivableInfo.id }" name="id"/>
					<div class="page-title">基本信息</div>
					<div class="p5">
						<table class="table-detail">
							<tr>
								<td class="td-title pct10">案件号</td>
								<td class="pct20">${caseApplyVo.caseApplyCode }</td>
								<td class="td-title pct10">主借人</td>
								<td class="pct20">${caseApplyVo.customerName }</td>
								<td class="td-title pct10">贷款金额</td>
								<td class="pct20">${caseApplyVo.applyAmount }元</td>
							</tr>
							<tr>
								<td class="td-title pct10">贷款期限</td>
								<td class="pct20">
									<dl class="form-item form-auto">
										<dd class="detail">
											<label> <input type="text" value="${caseApplyVo.applyTerm }" style="width:130Px"
												class="zui-input  zui-disabled" readonly/>
											</label>
										</dd>
										<dd class="detail">
											<input class="zui-combobox" type="hidden" name="applyTermUnit"
												data-width="120" value="${caseApplyVo.applyTermUnit }"
												data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=0931"
												data-valuefield="fullcode" data-textfield="name" data-choose="disable">
										</dd>
									</dl>
								</td>
								<td class="td-title pct10"><b class="c-red mr5">*</b>贷款利率</td>
								<td class="pct20">
									<dl class="form-item form-auto">
										<dd class="detail">
											<label> <input type="text" value="${receivableInfo.loanMonthRate }" style="width:130Px"
												class="zui-input zui-validatebox" name="loanMonthRate" id="loanMonthRate"
												validate-type="Require,Digital[4-4]" validate-false="|请输入正确的利率" onblur="changeRate()" />
											</label>
										</dd>
										<dd class="detail">
											<input class="zui-combobox zui-validatebox" type="hidden" id="loanMonthRateUnit" name="loanMonthRateUnit" value="${receivableInfo.loanMonthRateUnit }"
												data-width="120" validate-type="Require"
												data-callback="changeLoanMonthRateUnit"
												data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00119"
												data-valuefield="fullcode" data-textfield="name">
										</dd>
									</dl>
								</td>
								<td class="td-title pct10"><b class="c-red mr5">*</b>还款方式</td>
								<td class="pct20">
									<dl class="form-item form-auto">
										<dd class="detail">  
											<input class="zui-combobox zui-validatebox" type="hidden" name="repaymentType" value="${receivableInfo.repaymentType }"
												data-width="150"        
												 data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0051"
												data-valuefield="fullcode" data-textfield="name"  validate-type="Require">
										</dd>
										<dd class="detail">
											<input class="zui-combobox zui-validatebox" type="hidden" name="repaymentTypeTwo" value="${receivableInfo.repaymentTypeTwo }"
												data-width="130"
												data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00161"
												data-valuefield="fullcode" data-textfield="name"  validate-type="Require">
										</dd>
									</dl>
								</td>
							</tr>
							<tr>
								<td class="td-title pct10"><b class="c-red mr5">*</b>利率性质</td>
								<td class="pct20">
									<dl class="form-item form-auto">
										<dd class="detail">
											<input class="zui-combobox zui-validatebox" type="hidden" name="rateNature" value="${receivableInfo.rateNature }"
												data-width="130"
												data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00159"
												data-callback="change" data-id="isAgriculture"
												data-valuefield="fullcode" data-textfield="name" validate-type="Require">
										</dd>
										<dd class="detail">
											<input class="zui-combobox zui-validatebox" type="hidden" name="rateNatureTwo" value="${receivableInfo.rateNatureTwo }"
												data-width="120"
												data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00160"
												data-callback="change" data-id="isAgriculture"
												data-valuefield="fullcode" data-textfield="name" validate-type="Require">
										</dd>
									</dl>
								</td>
								<td class="td-title pct10"><b class="c-red mr5">*</b>逾期利率</td>
								<td class="pct20">
									<dl class="form-item form-auto">
										<dd class="detail">
											<label> <input type="text" class="zui-input zui-validatebox" style="width:130Px" name="overdueDailyRate" value="${receivableInfo.overdueDailyRate }"
												validate-type="Require,Digital[4-4]" validate-false="|请输入正确的利率" />
											</label>
										</dd>
										<dd class="detail">
											<input class="zui-combobox zui-validatebox" type="hidden" name="overdueDailyRateUnit" value="${receivableInfo.overdueDailyRateUnit }"
												data-width="120" validate-type="Require"
												data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00119"
												data-valuefield="fullcode" data-textfield="name">
										</dd>
									</dl>
								</td>
								<td class="td-title pct10"><b class="c-red mr5">*</b>预计放款日期</td>
								<td class="pct20">
									<dl class="form-item form-auto">
										<dd class="detail">
											 <label> 
												<input class="zui-date zui-validatebox strToDate" type="text" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'expectLoanDate',dateFmt:'yyyy-MM-dd'})" validate-type="Require" readonly value="${receivableInfo.expectLoanDate }"/>
												<input type="hidden" id="expectLoanDate" name="expectLoanDate" value="${receivableInfo.expectLoanDate }"/>
											</label>
										</dd>
									</dl>
								</td>
							</tr>
							<tr>
								<td class="td-title pct10"><b class="c-red mr5">*</b>还款日</td>
								<td class="pct20">
									<dl class="form-item form-auto">
										<dd class="detail">
											<label> <input type="text" value="${receivableInfo.repaymentDate }" style="width:130Px"
												class="zui-input zui-validatebox" name="repaymentDate" validate-type="Require,Number,Length[1-31]"/>
											</label>
										</dd>
										<dd class="detail">
											<input class="zui-combobox" type="hidden" data-width="120"
												data-data="[{'id':'0','text':'日','isDefault':'true'}]"
												data-callback="change" data-id="isAgriculture"
												data-valuefield="id" data-textfield="text" data-choose="disable">
										</dd>
									</dl>
								</td>
								<td class="td-title pct10">综合利率</td>
								<td class="pct20">
									<dl class="form-item form-auto">
										<dd class="detail">
											<label> <input type="text" style="width:130Px" id="syntheticalRate" name="syntheticalRate" value="${receivableInfo.syntheticalRate }"
												class="zui-input  zui-disabled"  readonly/>
											</label>
										</dd>
										<dd class="detail">
											<<input class="zui-combobox zui-validatebox" type="hidden" name="syntheticalRateUnit" validate-type="Require"
												data-width="120" value="YWDM0011901"
												data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00119"
												data-valuefield="fullcode" data-textfield="name" data-choose="disable">
										</dd>
									</dl>
								</td>
								<td class="td-title pct10">动态转换利率</td>
								<td class="pct20">
									<dl class="form-item form-auto">
										<dd class="detail">
											<label> <input type="text" id="internalRateReturn" name="internalRateReturn" value="${receivableInfo.internalRateReturn }"
												class="zui-input  zui-disabled"  style="width:150Px" readonly/>
											</label>
										</dd>
										<dd class="detail">
											<input class="zui-combobox" type="hidden" name="internalRateReturnUnit"
												data-width="130" value="YWDM0011901"
												data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00119"
												data-valuefield="fullcode" data-textfield="name" data-choose="disable">	
										</dd>
									</dl>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</form>
			<!-- 编辑还款计划 -->
			<div class="page-box">
				<div id="receivableEdit"></div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'],
	  	function ($, CALLBACK) {
		
		//还款计划加载
		var receivableEditUrl = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.receivablePlanGeneratePage" context="admin"/>&jsoncallback=?&caseApplyId=${caseApplyVo.id }';
		$('#receivableEdit').load(receivableEditUrl);
		
		//利率变动事件
		window.changeRate=function(v){
			var loanMonthRateUnit=$("#loanMonthRateUnit").ZCombobox("getValue");
			calculateOtherRate(loanMonthRateUnit);
		}
		
		//回调函数
        CALLBACK.changeLoanMonthRateUnit = function (v,t) {
        	calculateOtherRate(v);
        };
      	//计算综合利率和实际利率
        function calculateOtherRate(v){
        	var loanMonthRate=$("#loanMonthRate").val();
			var loanMonthRateUnit=v;
			var caseApplyId = '${caseApplyVo.id }';
			if(loanMonthRate==null||loanMonthRate==""||loanMonthRate.length<=0){
				return
			}
			if(loanMonthRateUnit==null||loanMonthRateUnit==""||loanMonthRateUnit.length<=0){
				return
			}
			if(caseApplyId==null||caseApplyId==""||caseApplyId.length<=0){
				return
			}
			$.ajax({
	            type: 'post',
	            url: '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.calculateOtherRate" context="admin"/>&jsoncallback=?',
	            data : {"loanMonthRate":loanMonthRate,"loanMonthRateUnit":loanMonthRateUnit,"caseApplyId":caseApplyId},
	            dataType: 'json',
	            success: function (data) {
	            	console.log(data);
	                if (data.resultStatus == 0) {
	                	$("#syntheticalRate").val(data.optional.syntheticalRate);
	                	$("#internalRateReturn").val(data.optional.internalRateReturn);
	                }else{
	                	$.ZMessage.error("错误", data.msg, function () {
	                        $(".zd-message").ZWindow("close");
	                    });
	                }
	            },
		        error: function () {
		        	$.ZMessage.error("错误", "查询错误", function () {
	                    $(".zd-message").ZWindow("close");
	                });
	            }
	        });
        }
		
      	//保存还款计划基本信息
	  	ZDS_WORKFLOW_CLIENT.saveFunction = function (datas) {
			var WORKFLOW_FLAG=ZDS_WORKFLOW_PARAM._STATUS_VALIDATE_ERROR;//1、提交，需要，默认提交失败！
			//还款计划基本信息
			var receivablePlanForm = $.ZUI.validateForm($('#receivablePlanForm'));
			//校验
			if(!receivablePlanForm){
				$.ZMessage.info("提示", "请完善必填项信息！", function () {
                });	 
				return false;
			}
				
			var params = $('#receivablePlanForm').serialize();
			
			//获取所有还款计划
			var propertyList=$('#receivablePlanEdit').ZTable("getRows");
			params += '&receivablePlanJson=' + JSON.stringify(propertyList);
			params += '&submitStatus=false';
			
			 //保存
			$.ajax({
                   type: 'post',
                   url: '<z:ukey key="com.cnfh.rms.casemanage.receivablePlan.updateReceivableInfo" context="admin"/>',
                   data: params,
                   dataType: 'json',
                   success: function (data) {
                	   if(data.resultStatus == 0){
                           //执行回调函数
                           ZDS_WORKFLOW_CLIENT.callBackFuntion(datas,ZDS_WORKFLOW_PARAM._STATUS_SUCCESS,data.msg);
                       }else{
                           //执行回调函数
                           ZDS_WORKFLOW_CLIENT.callBackFuntion(datas,ZDS_WORKFLOW_PARAM._STATUS_ERROR,data.msg);
                       }
                   },
		            error: function () {
		            	$.ZMessage.error("错误", "保存信息系统异常，请联系管理员", function () {
		             	});
	           		 }
           }); 
		   //返回流程状态
	   	   return WORKFLOW_FLAG;
		}
	  	 //提交方法
	    ZDS_WORKFLOW_CLIENT.submitFunction = ZDS_WORKFLOW_CLIENT.saveFunction;
	  	 
		$.ZUI.init();
	});
</script>