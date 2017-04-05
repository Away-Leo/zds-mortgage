<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>跟催</title>
<%@ include file="../../common/common_js.jsp" %>
</head> 
<body>
<div class="save">
    <button id="btn-save" class="btn-blue mr10">保存</button>
    <button id="btn-submit" class="btn-blue mr10">提交</button>
</div>
	<div class="frm-content frm-bottom" id="opinionDiv">
	<form id="search" class="zui-form" action="javascript:void(0);" 
            zdata-options={"url":'<z:ukey key="com.zdsoft.finance.loan.submitLoanApply"  context="admin"/>',"callBack":"saveCallBack"}>
   <!-- 最近跟催信息 -->       
   <%@ include file="../loanMiddleMonitor/lately_followInfo_list.jsp"%>  
            
		<!-- 跟进催收单 -->
	    <div class="page-box">
	        <div class="page-title">跟进催收单</div>
	        <div class="p5">
	            <table class="table-detail">
	                <tr>
	                    <td class="td-title pct20"><b class="c-red mr5">*</b>跟催方式</td>
	                    <td class="pct20">
	                    	<dl class="form-item">
		                        <dd class="detail">
		                            	<input type="hidden" name="customerId" value="${customerId}">
		                            	<input type="hidden" name="caseApplyId" value="${caseApplyId}">
		                            	<input type="hidden" name="days" value="${days}" id="days">
		                                <input id="followType" class="zui-combobox zui-validatebox" type="hidden" name="followType" validate-type="Require" validate-false="请选择跟催方式"
                              				 data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00151"
											 data-valuefield="id" data-textfield="text">
		                        </dd>
			                </dl>
	                    </td>
	                    <td class="td-title pct20">跟催状态</td>
	                    <td class="pct20">
	                    	<dl class="form-item">
		                        <dd class="detail">
			                            <input id="radio1" class="zui-checkbox zui-validatebox" type="hidden" name="followStatus" id="followStatus"
			                               data-multiple="false" data-defaultvalue="1" 
			                               data-data="[{'id':'1','text':'有效'},{'id':'0','text':'无效'}]"
			                               data-valuefield="id" data-textfield="text" validate-type="Require" data-toggle="checkbox" data-callback="changeFollowStatus">
		                        </dd>
			                </dl>
	                    </td>
	                    <td class="td-title pct20" id="callOutObjectTd1">外呼对象</td>
	                    <td class="pct20" id="callOutObjectTd2">
	                    	<dl class="form-item">
		                        <dd class="detail">
		                            <label>
		                                <input type="text" class="zui-input" name="callOutObject" id="callOutObject"/>
		                            </label>
		                        </dd>
			                </dl>
	                    </td>
	                     <td class="td-title pct20" id="invalidReasonTd1" style="display:none"><b class="c-red mr5">*</b>无效原因</td>
	                    <td class="pct20" id="invalidReasonTd2" style="display:none">
	                    	<dl class="form-item">
		                        <dd class="detail">
		                            <input id="invalidReason" class="zui-combobox" type="hidden" name="invalidReason" validate-type="Require" validate-false="请选择无效原因"
                              				 data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00162"
											 data-valuefield="id" data-textfield="text">
		                        </dd>
			                </dl>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="td-title" id="czya">处置预案</td>
	                    <td class="pct20">
	                    	<dl class="form-item">
		                        <dd class="detail">
		                                <input id="handlePlan" class="zui-combobox" type="hidden" name="handlePlan" validate-type="Require" validate-false="请选择处置预案"
                              				 data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00152"
											 data-valuefield="id" data-textfield="text" data-callback="changeChildHandlePlan">
		                        </dd>
			                </dl>
	                    </td>
	                    <td class="td-title">子目录</td>
	                    <td class="pct20">
	                    	<dl class="form-item">
		                        <dd class="detail" id="handlePlan1">
		                                <input id="childHandlePlan1" class="zui-combobox" type="hidden" validate-type="Require" validate-false="请选择子目录"
                              				 data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00153"
											 data-valuefield="id" data-textfield="text">
		                        </dd>
		                        <dd class="detail" id="handlePlan2" style="display:none">
		                            <label>
		                               <input type="text" class="zui-input"  id="childHandlePlan2"/>
		                            </label>
		                        </dd>
		                        
			                </dl>
	                    </td>
	                    <td class="td-title">跟踪部门</td>
	                    <td class="pct20">
	                   		<dl class="form-item">
		                    	<dd class="detail">
		                            <label>
		                               <input type="text" class="zui-input" name="departmentName" id="departmentNam">
		                            </label>
			                    </dd>
		                    </dl>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="td-title pct20"><b class="c-red mr5">*</b>预计还款日期</td>
	                    <td class="pct20">
	                    	<dl class="form-item">
		                    	<dd class="detail">
		                            <label>
		                               	 <input class="zui-date zui-validatebox" id="predictRepayDate" name="predictRepayDate" type="text" onclick="WdatePicker()" validate-type="Require" readonly/>
		                            </label>
			                    </dd>
		                    </dl>
	                    </td>
	                    <td class="td-title pct20">预计下次跟进日期</td>
	                    <td class="pct20">
	                    	<dl class="form-item">
		                    	<dd class="detail">
		                            <label>
	                    				<input type="text" class="zui-input" name="pretNextFlUpDate" id="pretNextFlUpDate" value="${pretNextFlUpDate}" readonly>
		                            </label>
			                    </dd>
		                    </dl>
	                    </td>
	                    <td class="td-title pct20"><b class="c-red mr5">*</b>是否发起督办</td>
	                    <td class="pct20">
	                    	<dl class="form-item">
		                    	<dd class="detail"> 
										<input id="supervisd " class="zui-combobox zui-validatebox"  type="hidden" name="supervisd" data-defaultvalue="0" 
													data-data="[{'id':'1','text':'是'},{'id':'0','text':'否'}]" validate-type="Require" validate-false="请选择是否发起督办"
													data-valuefield="id" data-textfield="text" data-callback="changeSupervisd">		                            
			                    </dd>
		                    </dl>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="td-title"><b class="c-red mr5">*</b>跟催情况</td>
	                    <td class="pct20" colspan="5">
	                    	<dl class="form-item">
		                    	<dd class="detail">
		                            <label>
										<textarea id="followCondiction" name="followCondiction" class="zui-area zui-validatebox" validate-type="Require" validate-false="请选择跟催方式" ></textarea>
									</label>
			                    </dd>
		                    </dl>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="td-title">跟催人</td>
	                    <td class="pct20">
	                    	<dl class="form-item">
		                    	<dd class="detail">
		                            <label>
	                    				<input type="text" class="zui-input" name="employeeName" id="employeeName" value="${employeeName}" readonly/>
									</label>
			                    </dd>
		                    </dl>
	                    </td>
	                    <td class="td-title">跟催日期</td>
	                    <td class="pct20">
	                    	<dl class="form-item">
		                    	<dd class="detail">
		                            <label>
	                    				<input type="text" class="zui-input" name="followDate" id="followDate" value="${followDate}" readonly>
									</label>
			                    </dd>
		                    </dl>
	                    </td>
	                    <td class="td-title"></td>
	                    <td class="pct20"></td>
	                </tr>
	            </table>
	        </div>
	    </div>
	    
	    
	    <div class="page-box" id="superviseDetail">
		    <!-- 督办事项 -->
			<div class="page-title">督办事项</div> 
			<div class="p5">
			     <table class="table-detail">
			           <tr>
			               <td class="td-title pct20">督办类型</td>
			               <td class="pct20">
			                   <dl class="form-item form-auto">
			                          <dd class="detail f12">
			                              <label>
			                              </label>
			                          </dd>
			                   </dl>
			               </td>
			               <td class="td-title pct20">督办子类型</td>
			               <td class="pct20">
			                   <dl class="form-item form-auto">
			                       <dd class="detail f12">
			                           <label>
			                           </label>
			                       </dd>
			                   </dl>
			               </td>
			               <td class="td-title pct20"></td>
			               <td class="pct20">
			                   <dl class="form-item form-auto">
			                       <dd class="detail f12">
			                           <label id="number">
			                           </label>
			                       </dd>
			                   </dl>
			               </td>
			           </tr>
			           <tr>
			               <td class="td-title pct20">督办接收人</td>
			               <td class="pct20">
			                   <dl class="form-item form-auto">
			                          <dd class="detail f12">
			                              <label id="overdueAmount">
			                              </label>
			                          </dd>
			                   </dl>
			               </td>
			               <td class="td-title pct20">督办抄送人</td>
			               <td class="pct20">
			                   <dl class="form-item form-auto">
			                          <dd class="detail f12">
			                              <label>
			                              </label>
			                          </dd>
			                   </dl>
			               </td>
			               <td class="td-title pct20">处理时限</td>
			               <td class="pct20">
			                   <dl class="form-item form-auto">
			                          <dd class="detail f12">
			                              <label>
			                              </label>
			                          </dd>
			                   </dl>
			               </td>
			           </tr>
			           <tr>
			               <td class="td-title pct20">督办人</td>
			               <td class="pct20">
			                   <dl class="form-item form-auto">
			                          <dd class="detail f12">
			                              <label id="overdueAmount">
			                              </label>
			                          </dd>
			                   </dl>
			               </td>
			               <td class="td-title pct20">跟踪部门</td>
			               <td class="pct20">
			                   <dl class="form-item form-auto">
			                          <dd class="detail f12">
			                              <label>
			                              </label> 
			                          </dd>
			                   </dl>
			               </td>
			               <td class="td-title pct20"></td>
			               <td class="pct20">
			                   <dl class="form-item form-auto">
			                          <dd class="detail f12">
			                              <label>
			                              </label>
			                          </dd>
			                   </dl>
			               </td>
			           </tr>  
			           <tr>
			               <td class="td-title pct20">备注</td> 
			               <td class="pct20" colspan="5">   
			                   <dl class="form-item form-auto">
			                          <dd class="detail f12">  
			                              <label id="overdueAmount">      
			                              <textarea style="width:600%;" class="zui-area zui-validatebox"  disabled>这里是文本域</textarea>
			                              </label>
			                          </dd>
			                   </dl>
			               </td> 
			           </tr>
			           
			     </table>
			</div>    
			<!--  督办事项end -->
		</div>
	</form>
	</div> 
	 
<script>
seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter', 'zd/jquery.zds.combotree', 'zd/jquery.zds.checkbox']
, function ($, CALLBACK, COMBOBOX, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
	//逾期30天，处置预案必填
	if(parseInt($("#days").val())>30){
		$("#czya").html('<b class="c-red mr5">*</b>跟催方式');
		$("#handlePlan").attr("class","zui-combobox zui-validatebox");
		$("#childHandlePlan1").attr("class","zui-combobox zui-validatebox");
	}
	//处理预案选择事件
	CALLBACK.changeChildHandlePlan=function(data){
		if(data=="YWDM0015201"){
			$("#childHandlePlan1").ZCombobox({
				url : "<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00153"
			});
			$("#handlePlan1").css("display","");
			$("#handlePlan2").css("display","none");
		}
		else if(data=="YWDM0015202"){
			$("#childHandlePlan1").ZCombobox({
				url : "<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00156"
			});
			$("#handlePlan1").css("display","");
			$("#handlePlan2").css("display","none");
		}
		else if(data=="YWDM0015203"){
			$("#childHandlePlan1").ZCombobox({
				url : "<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00154"
			});
			$("#handlePlan1").css("display","");
			$("#handlePlan2").css("display","none");
		}
		else if(data=="YWDM0015204"){
			$("#childHandlePlan1").ZCombobox({
				url : "<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00155"
			});
			$("#handlePlan1").css("display","");
			$("#handlePlan2").css("display","none");
		}
		//如果处置预案为其他，显示文本框
		else{
			$("#handlePlan1").css("display","none");
			$("#handlePlan2").css("display","");
			$("#childHandlePlan1").attr("class","zui-combobox");
		}
	}
	//是否发起督办选择事件
	CALLBACK.changeSupervisd=function(data){
		if(data=="1"){
			$("#superviseDetail").css("display","");
		}
		else{
			$("#superviseDetail").css("display","none");
		}
	}
	//跟催状态选择事件
	CALLBACK.changeFollowStatus=function(data){
		if(data=="1"){
			$("#callOutObjectTd1").css("display","");
			$("#callOutObjectTd2").css("display","");
			$("#invalidReasonTd1").css("display","none");
			$("#invalidReasonTd2").css("display","none");
			$("#invalidReason").attr("class","zui-combobox");
			$("#invalidReason").ZCombobox();
		}
		else{
			$("#invalidReasonTd1").css("display","");
			$("#invalidReasonTd2").css("display","");
			$("#callOutObjectTd1").css("display","none");
			$("#callOutObjectTd2").css("display","none");
			$("#invalidReason").attr("class","zui-combobox zui-validatebox");
			$("#callOutObject").val("");
		}
	}
	//提交
	$("#btn-submit").click(function(){
		var isValidate = $.ZUI.validateForm($('#search'));
        if (isValidate) {
        	var param={};
        	var formParam=$("#search").serializeArray();
        	for(var i=0;i<formParam.length;i++){
        		param[formParam[i].name]=formParam[i].value;
        	}
           	param.operate="submit";
           	param.pretNextFlUpDate=Date.parse(new Date(param.pretNextFlUpDate));
           	param.predictRepayDate=Date.parse(new Date(param.predictRepayDate));
           	param.followDate=Date.parse(new Date(param.followDate));
           	if($("#handlePlan1").css("display")=="none"){
           		param.childHandlePlan=$("#childHandlePlan2").val();
           	}
           	else{
           		param.childHandlePlan=$("#childHandlePlan1").ZCombobox("getValue");
           	}
           	$.ajax({
                   type: 'post',
                   url: '<z:ukey key="com.zdsoft.finance.afterloan.submitFollowInfo" context="admin"/>&param='+encodeURIComponent(JSON.stringify(param)),
                   success: function (data) {
                       if (data.resultStatus == 0) {
                       	$.ZMessage.success("提示", "申请已提交审批", function () {
                        		setTimeout(function(){
                                	ZDS_MESSAGE_CLIENT.closeSelf();
                                },200);
                         	 });
                       }else{
                       	$.ZMessage.error("错误", data.msg, function () {
                        });
                       }
                   },
		            error: function () {
		            	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
		             });
		            }
               }); 
        }
	});
	
	
	
    	$.ZUI.init();

    });


</script>
</body>
</html>