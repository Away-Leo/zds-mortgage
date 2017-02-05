<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
    
    <div id="edit-form" class="user-defined clearfix">
		<div class="user-defined-box fl" style="height: 250px; margin-top: 10px;margin-left: 30px;">
				<h1 class="power-content-title">添加资金项</h1>
				<!-- <form id = "form_addCapital" > -->
				<dl class="form-item block">
					<dt class="title">
						<b class="c-red ">*</b>期数:
					</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input zui-disabled zui-validatebox" type="text" id="periodsNo" name="periodsNo" readonly="readonly" validate-type="Require" />
						</label>
					</dd>
				</dl>
				<dl class="form-item block">
					<dt class="title">
						<b class="c-red ">*</b>天数:
					</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input  zui-validatebox" type="text" id="days" name="days" validate-type="Require,Number" />
						</label>
					</dd>
				</dl>
				<dl class="form-item block">
					<dt class="title">
						<b class="c-red ">*</b>还款日:
					</dt>
					<dd class="detail">
						<label> 
							<input type="hidden" name="planDueDt" id="planDueDt"/>
							<input id="showPlanDueDt" class="zui-date strToDate zui-validatebox" validate-type="Require" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',realDateFmt:'yyyyMMdd',vel:'planDueDt'})"/>
						</label>
					</dd>
				</dl>
				<dl class="form-item block">
					<dt class="title">
						<b class="c-red ">*</b>本金(元):
					</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input  zui-validatebox" type="text" id="planPrincipalAmount" name="planPrincipalAmount" validate-type="Require,Amount,size[0-999999999.9999]" />
						</label>
					</dd>
				</dl>
				<dl class="form-item block">
					<dt class="title">
						<b class="c-red ">*</b>利息(元):
					</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input  zui-validatebox" type="text" id="planInterestAmount" name="planInterestAmount" validate-type="Require,Amount,size[0-999999999.9999]" />
						</label>
					</dd>
				</dl>
				<dl class="form-item block" style="display: none;">
					<dt class="title">
						<b class="c-red ">*</b>咨询费:
					</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input  zui-validatebox" type="text" id="addCapital_ConsultRateAmount" name="addCapital_ConsultRateAmount" validate-type="Require,Amount,size[0-999999999.9999]" />
						</label>
					</dd>
				</dl>
				<dl class="form-item block" style="display: none;">
					<dt class="title">
						<b class="c-red ">*</b>财务费:
					</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input  zui-validatebox" type="text" id="addCapital_FinanceRateAmount" name="addCapital_FinanceRateAmount" validate-type="Require,Amount,size[0-999999999.9999]" />
						</label>
					</dd>
				</dl>
				<dl class="form-item block" style="display: none;">
					<dt class="title">
						<b class="c-red ">*</b>财务费:
					</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input  zui-validatebox" type="text" id="addCapital_OtherRateAmount" name="addCapital_OtherRateAmount" validate-type="Require,Amount,size[0-999999999.9999]" />
						</label>
					</dd>
				</dl>
				<dl class="form-item block" >
					<dt class="title">
						备注:
					</dt>
					<dd class="detail">
						<label> 
							<textarea class="zui-area zui-validatebox" id="addCapital_Mo" name="textarea"  validate-type="Require" placeholder="最多可以输入200个字符" style="width: 190px;"></textarea>
						</label>
					</dd>
				</dl>
				<!-- </form> -->
		</div>
			
		<div class="user-defined-box fl" style="height: 250px;margin-left: 20px;margin-top: 10px;">
			<h1 class="power-content-title">辅助计息工具</h1>
				<!-- <form id="from_tool_calc"> -->
			<dl class="form-item block">
				<dt class="title">
					备注：
				</dt>
				<dd class="detail" title="利息=本金*利率(日)*天数"  style="font-size: 12px;">
						利息=本金*利率(日)*天数				 
				</dd>
			</dl>
			<dl class="form-item block" >
				<dt class="title">
					本金(元):
				</dt>
				<dd class="detail">
					<label> 
						<input class="zui-input  zui-validatebox" type="text" id="tool_Capital" name="tool_Capital" validate-type="Amount,size[0-999999999.9999]" />
					</label>
				</dd>
			</dl>
			<dl class="form-item block">
                 <dt class="title">利率：</dt>
                 <dd class="detail">
                        <label>
                           <input type="text" class="zui-input zui-validatebox" validate-type="Amount,size[0-999.9999]" id="tool_Ratio" name="tool_Ratio"
                            style="width:125px;">
                        </label>
                 </dd>
                 <dd class="detail">
                         <input type="hidden" class="zui-combobox  zui-validatebox " validate-type="Require" name="tool_e_RateUnitx" id="tool_e_RateUnitx" data-toggle="combobox"
                            data-data="[{'id':'12','text':'年'},{'id':'1','text':'月'}]"
                            data-id="interestRateCycle" data-defaultvalue="1"
                            data-width="62" data-valuefield="id" data-textfield="text"  >
                 </dd>
             </dl>
             <dl class="form-item block">
					<dt class="title">
						天数:
					</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input  zui-validatebox" type="text" id="tool_IntervalDays" name="tool_IntervalDays" validate-type="Number" />
						</label>
					</dd>
				</dl>
				  <dl class="form-item block">
				  	<dt class="title">
					</dt>
					<dd class="detail">
                         <button type="button" class="btn-blue "  id="tool_calc" ><i class="icon-btn21"></i>试算</button>
					</dd>
				</dl>
				
				<div style="font-size: 12px;border-top: 1px solid #ccc;text-align: right;" id="tool_result">合计：0.00元</div>
		 </div>		
	</div>
	
	<script type="text/javascript">
		seajs.use(
			['jquery', 'zd/jquery.zds.page.callback','zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch', 
	    	 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form',
	    	 'datepicker',  'zd/jquery.zds.table', 'zd/jquery.zds.seleter'],
	    	 function ($, CALLBACK, Loading, Switch, DropDown, Filter, Check, Zdialog, ZUI_MESSAGE_CLIENT){
		   
		   //编辑还款计划
           $("#edit-form").Zdialog({
               width: 700,
               height: 400,
               closed: true,
               isDestroy:true,
               title: "添加资金项",
               buttons: [{
                   id: 'message-btn', text: '确定', buttonCls: 'btn-blue',
                   handler: function () {
                       //$("#role_form").submit();
                       $("#edit-form").Zdialog("close");
                   }
               }, {
                   id: 'message-btn', text: '取消', buttonCls: 'btn-gray',
                   handler: function () {
                       $("#edit-form").Zdialog("close");
                   }
               }]
           });
    		
           var selectRow = $('#zd-table').ZTable("getSelections");
           var selectDate=selectRow[0];
           if(selectDate){
        	   $('#periodsNo').val(selectDate.periodsNo);
        	   $('#days').val(selectDate.days);
        	   $('#showPlanDueDt').val(selectDate.planDueDt);
        	   $.ZUI.strToDate();
        	   $('#planPrincipalAmount').val(selectDate.planPrincipalAmount);
        	   $('#planInterestAmount').val(selectDate.planInterestAmount);
           }
           
           $("#edit-form").Zdialog("open");
    });
</script>
</body>
</html>