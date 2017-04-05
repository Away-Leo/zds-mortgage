<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="frm-content frm-bottom" id="receivablePlanDiv">
	<form id="receivablePlanForm" class="zui-form" action="javascript:void(0);">
		<div class="page-box">
			<input type="hidden" value="${caseApply.id }" name="caseApplyId"/>
			<input type="hidden" value="${receivableInfo.id }" name="id"/>
			<div class="page-title">基本信息</div>
			<div class="p5">
				<table class="table-detail">
					<tr>
						<td class="td-title pct10">案件号</td>
						<td class="pct20">${caseApply.caseApplyCode }</td>
						<td class="td-title pct10">主借人</td>
						<td class="pct20">${caseApply.customerName }</td>
						<td class="td-title pct10">贷款金额(元)</td>
						<td class="pct30"><fmt:formatNumber value="${caseApply.applyAmount }" pattern="#,##0.00#"/></td>
					</tr>
					<tr>
						<td class="td-title pct10"><b class="c-red mr5">*</b>贷款期限</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${caseApply.applyTerm }" name="applyTerm" style="width:130Px"
										class="zui-input zui-validatebox" validate-type="Require,Integer" />
									</label>
								</dd>
								<dd class="detail">
									<input class="zui-combobox zui-validatebox" type="hidden" name="applyTermUnit"
										data-width="120" value="${caseApply.applyTermUnit }"
										data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=0931"
										data-valuefield="fullcode" data-textfield="name" >
								</dd>
							</dl>
						</td>
						<td class="td-title pct10"><b class="c-red mr5">*</b>贷款利率</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${receivableInfo.loanMonthRate }" style="width:130Px"
										class="zui-input zui-validatebox" name="loanMonthRate" id="loanMonthRate"
										validate-type="Require,Digital[4-4]" validate-false="|请输入正确的利率" onchange="changeRate()" />
									</label>
								</dd> 
								<dd class="detail">
									<input class="zui-combobox zui-validatebox" type="hidden" id="loanMonthRateUnit" name="loanMonthRateUnit" validate-type="Require" data-width="120">
									
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
										data-width="130" data-callback="validateRepayment"
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
									<label> <input type="text"
										class="zui-input zui-validatebox"  style="width:130Px" name="overdueDailyRate" value="${receivableInfo.overdueDailyRate }"
										validate-type="Require,Digital[4-4]" validate-false="|请输入正确的利率" />
									</label>
								</dd>
								<dd class="detail">
									<input class="zui-combobox zui-validatebox" type="hidden" name="overdueDailyRateUnit" value="${receivableInfo.overdueDailyRateUnit }"
										data-width="120" validate-type="Require"
										data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00119"
										data-valuefield="fullcode" data-textfield="name" data-defaultValue='YWDM0011903'>
								</dd>
							</dl>
						</td>
						<td class="td-title pct10"><b class="c-red mr5">*</b>预计放款日期</td>
						<td class="pct30">
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
						<td class="td-title pct10"><b id="validateView" class="c-red mr5">*</b>还款日</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${receivableInfo.repaymentDate }"  style="width:130Px"
										class="zui-input zui-validatebox" id="repaymentDate" name="repaymentDate" validate-type="Require,Number,Size[1-31]" />
									</label>
								</dd>
								<dd class="detail">
									<input class="zui-combobox zui-validatebox" type="hidden" data-width="120"
										data-data="[{'id':'0','text':'日','isDefault':'true'}]"
										data-id="isAgriculture"
										data-valuefield="id" data-textfield="text" data-choose="disable"
										validate-type="Require">
								</dd>
							</dl>
						</td>
						<td class="td-title pct10">综合利率</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text"  style="width:130Px" id="syntheticalRate" name="syntheticalRate" value="${receivableInfo.syntheticalRate }"
										class="zui-input zui-validatebox zui-disabled" readonly/>
									</label>
								</dd>
								<dd class="detail">
									<input class="zui-combobox" type="hidden" name="syntheticalRateUnit"
										data-width="120" value="YWDM0011901"
										data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00119"
										data-valuefield="fullcode" data-textfield="name" data-choose="disable">
								</dd>
							</dl>
						</td>
						<td class="td-title pct10">动态转换利率</td>
						<td class="pct30">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" id="internalRateReturn" name="internalRateReturn" value="${receivableInfo.internalRateReturn }"
										class="zui-input zui-validatebox zui-disabled"  style="width:150Px" readonly/>
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
					<tr>
						<td class="td-title pct10"><b class="c-red mr5">*</b>预计开始日期</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<dd class="detail">
										<label> 
											<input class="zui-date zui-validatebox strToDate" type="text" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'expectStartDate',dateFmt:'yyyy-MM-dd'})" validate-type="Require" readonly value="${receivableInfo.expectStartDate }"/>
											<input type="hidden" id="expectStartDate" name="expectStartDate" value="${receivableInfo.expectStartDate }"/>
										</label>
									</dd>
								</dd>
							</dl>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<form id="bankAccountForm" class="zui-form">
		<div class="page-box">
			<div class="page-title">收款账户</div>
			<input type="hidden" name="loanAccountVo.id" value="${loanAccountVo.id }"/>
			<div class="p5">
				<table class="table-detail">
					<tr>
						<td class="td-title pct10"><b class="c-red mr5">*</b>开户行</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${loanAccountVo.bankAccount }" 
										class="zui-input zui-validatebox" validate-type="Require,Length[1-64]" name="loanAccountVo.bankAccount"/>
									</label>
								</dd>
							</dl>
						</td>
						<td class="td-title pct10">银行代码</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail" id="receivablesBankCode">
									<label> 
									<input  value="${loanAccountVo.bankCode }"
										class="zui-input" id="bankCode1"  name="loanAccountVo.bankCode"/>
									</label>
								</dd>
							</dl>
						</td>
						<td class="td-title pct10"><b class="c-red mr5">*</b>账户名</td>
						<td class="pct30">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${loanAccountVo.cardholderName }"
										class="zui-input zui-validatebox" validate-type="Require,Length[1-64]" name="loanAccountVo.cardholderName"/>
									</label>
								</dd>
							</dl>
						</td>
					</tr>
					<tr>
						<td class="td-title"><b class="c-red mr5">*</b>账号</td>
						<td>
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${loanAccountVo.bankNo }"
										class="zui-input zui-validatebox" validate-type="Require,Length[1-50]" name="loanAccountVo.bankNo"/>
									</label>
								</dd>
							</dl>
						</td>
					</tr>
				</table>

			</div>
		</div>
		
		<div class="page-box">
			<div class="page-title">还款（代扣）账户</div>
			<input type="hidden" name="recAccountVo.id" value="${receivableAccountVo.id }"/>
			<div class="p5">
				<table class="table-detail">
					<tr>
						<td class="td-title pct10">开户行</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${receivableAccountVo.bankAccount }"
										class="zui-input zui-validatebox" validate-type="Length[1-64]" name="recAccountVo.bankAccount"/>
									</label>
								</dd>
							</dl>
						</td>
						<td class="td-title pct10">银行代码</td>
						<td class="pct20">
							<dl class="form-item form-auto">
                                <dd class="detail" id="repaymentBankCode">
									<label> <input type="text" value="${receivableAccountVo.bankCode }"
										class="zui-input zui-validatebox" id="bankCode2" validate-type="Length[0-32]"  name="recAccountVo.bankCode"/>
									</label>
								</dd>
							</dl>
						</td>
						<td class="td-title pct10">账户名</td>
						<td class="pct30">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${receivableAccountVo.cardholderName }"
										class="zui-input zui-validatebox" validate-type="Length[1-64]"  name="recAccountVo.cardholderName"/>
									</label>
								</dd>
							</dl>
						</td>
					</tr>
					<tr>
						<td class="td-title">账号</td>
						<td>
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" value="${receivableAccountVo.bankNo }"
										class="zui-input zui-validatebox" validate-type="Length[1-50]"  name="recAccountVo.bankNo"/>
									</label>
								</dd>
							</dl>
						</td>
					</tr>
				</table>

			</div>
		</div>
		<div class="page-box">
			<div class="page-title">备注说明</div>
			<div class="p5">
				<table class="table-detail">
					<tr>
						<td class="td-title pct15">备注说明</td>
						<td colspan="5">
						<label> <textarea class="zui-area zui-validatebox row-width" validate-type="Length[0-500]" 
						name="remark" placeholder="最多可以输入500个字符">${receivableInfo.remark}</textarea>
						</label></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="page-box">
			<div id="receivableEditPage"></div>
		</div>
	</form>

</div>
<script>
	seajs.use([ 'jquery','zd/jquery.zds.page.callback','zd/jquery.zds.seleter','zd/completer','zd/jquery.zds.form' ], function($,CALLBACK) {
		CALLBACK.validateRepayment = function(val){
			validateRepaymentFun(val);
		}
		$.ZUI.init('#receivablePlanDiv');
		//根据还款方式中的是否对日对月更改还款日得验证
		function validateRepaymentFun(val){
			if("YWDM0016102"==val){
				$("#validateView").html("");
				$("#repaymentDate").removeClass("zui-validatebox").removeAttr("validate-type");
			}else{
				$("#validateView").html("*");
				$("#repaymentDate").addClass("zui-validatebox").attr({"validate-type": "Require,Number,Size[1-31]"});
			}
		}
		//初始化还款方式中的是否对日对月更改还款日得验证
		validateRepaymentFun("${receivableInfo.repaymentTypeTwo }");
		
		var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.receivablePlanGeneratePage" context="admin"/>&jsoncallback=?&caseApplyId=${caseApply.id }';
		$('#receivableEditPage').load(url);
		//第一次进入页面计算综合利率
		if('${receivableInfo.loanMonthRate }' && '${receivableInfo.syntheticalRate }'==''){ 
			calculateOtherRate('${receivableInfo.loanMonthRateUnit }');
		}
		//利率变动事件
		window.changeRate=function(v){
			var loanMonthRateUnit=$("#loanMonthRateUnit").ZCombobox("getValue");
			calculateOtherRate(loanMonthRateUnit);
		}
		
		//回调函数
        $("#loanMonthRateUnit").ZCombobox({       
			valueField: "fullcode",      
			textField: "name",         
			url: "<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00119",
			value:'${receivableInfo.loanMonthRateUnit }',
			onSelect: function(value, text, index, data) {
				calculateOtherRate(value);
			}  
		});  
        
      	//计算综合利率和实际利率
        function calculateOtherRate(v){
        	var loanMonthRate=$("#loanMonthRate").val();
			var loanMonthRateUnit=v;
			var caseApplyId = '${caseApply.id }';
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
      	
      	//联想银行数据
    	$("#bankCode1").completer({
            suggest: true,//默认false
            idField: 'bankCode',//默认id,唯一标识字段
            nameField: 'bankName',//默认name,下拉列表展示数据的字段
            valueField: 'bankName',//默认value,根据值查询数据的字段
            placeObj:$("#receivablesBankCode"),//悬浮框需要定位到的对象
            url:'<z:ukey key="com.cnfh.rms.casemanage.interview.findBankByLikeName" context="admin"/>' ,//请求数据地址
            writable: false,//默认false，是否可自定义输入
            complete: function (data) {
            	$("#bankCode1").val(data.bankCode);
            },
            filter: function (val) {
                return val;//过滤输入的value值
            }
		});
    	
    	//联想银行数据
    	$("#bankCode2").completer({
            suggest: true,//默认false
            idField: 'bankCode',//默认id,唯一标识字段
            nameField: 'bankName',//默认name,下拉列表展示数据的字段
            valueField: 'bankName',//默认value,根据值查询数据的字段
            placeObj:$("#repaymentBankCode"),//悬浮框需要定位到的对象
            url:'<z:ukey key="com.cnfh.rms.casemanage.interview.findBankByLikeName" context="admin"/>' ,//请求数据地址
            writable: false,//默认false，是否可自定义输入
            complete: function (data) {
            	$("#bankCode2").val(data.bankCode);
            },
            filter: function (val) {
                return val;//过滤输入的value值
            }
		});
	});
</script>