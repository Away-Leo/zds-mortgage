<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 资调派单 -->
<!-- 查看案件基本信息 -->
<%@ include file="handleMortgage/common/case_apply_view.jsp" %>
<!-- 客户信息 -->
<%@ include file="../marketing/beforehandapply/client_info_view.jsp"%>
<!-- 客户信息》联系方式 -->
<%@ include file="../marketing/beforehandapply/client_contact_way_view.jsp"%>
<!-- 客户信息》工作单位信息 -->
<%@ include file="../marketing/beforehandapply/client_work_unit_view.jsp"%>
<!-- 房产信息 -->
<%@ include file="../casemanage/datasurvey/housePledgeInfoModules/collateral_info_view.jsp" %>
<!-- 抵押情况 -->
<%@ include file="../marketing/beforehandapply/pledge_info_view.jsp"%>
<div class="page-box">
    <!-- 征信信息 -->
    <div id="creditInfo"></div>
   	
    <!-- 房产评估信息 -->
    <div id="houseEvaluateList"></div>
   	
   	<!-- 企业法院信息 -->
   	<div id="huiFaCompanyInfo"></div>
   	
   	<!-- 个人法院信息 -->
   	<div id="huiFaPersonalInfo"></div>
   	
	<!-- 关联贷款信息 -->
	<div id="relatedCase"></div>
     
     <!-- 工商信息 -->
    <div id="BusinessInfo"></div>
    
    <!-- 选择资信员 -->
     <div id="div-detain" class="page-box">
		<div class="page-title" id="div-detain-form">选择资信员</div>
		<div class="p5">
	       <form id="chooseEmp" class="zui-form form-search" method="post" enctype="multipart/form-data">
	           <table class="table-detail">
	               <tr>
	                   <td class="td-title pct10"><b class="c-red mr5">*</b>资信员</td>
	                   <td class="pct20">
	                   	<dl class="form-item form-auto">
	                            <dd class="detail">
	                                <label>
	                                	<input id="caseTaskId" name="id" type="hidden" value="${caseTaskVo.id }" />
	                               	<input id="empCode" name="taskPersonnelCode" class="zui-validatebox" type="hidden" value="${caseTaskVo.taskPersonnelCode }" validate-type="Require"/>
	                               	<input id="empName" name="taskPersonnelName" type="text" value="${caseTaskVo.taskPersonnelName}" class="zui-input zui-validatebox" readonly validate-type="Require"/>
	                               	<button class="btn-blue" type="button" id="taskPersonnel">选择<tton>
	                               </label>
	                           </dd>
	                       </dl>
	                  </td>
	                  <td class="td-title pct10"></td>
	                  <td class="pct20"></td>
	                  <td class="td-title pct10"></td>
	                  <td class="pct20"></td>
	              </tr>
	              <tr>
	                  <td class="td-title pct10" rowspan="2">说明</td>
	                  <td class="pct20" colspan="5">
	                  	<label>
	                          <textarea id="remark" class="zui-area zui-validatebox" validate-type="Length[0-500]" name="remark" placeholder="最多可以输入500个字符" style="width:100%">${caseTaskVo.remark}</textarea>
	                   </label>
	                  </td>
	              </tr>
	          </table>
	      </form>
	   </div>
    </div>
</div>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message',
           'zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter',
           'common/zds-common-selecter'], function($, CALLBACK) {
		
	// 人员选择器
	$("#taskPersonnel").commonSelect({rowHeight: "30px", height:480, width: 750, type: "emp", singleSelect: true,defaultSearch:{orgCd:"${companyCd}",involveOrgCd:"true"},
        onOk: function (data) {
        	$('#empCode').val(data[0]._data.empCd);
			$('#empName').val(data[0]._data.empNm);
     },onBeforeOpen:function(){
         return true;
     }});
	
	//查看估价按钮
	$('#viewHousePrice').on('click',function() { 
		var	url = '<z:ukey key="com.zdsoft.finance.houseassessment.houseEvaluate.getPriceForCompany" context="admin"/>&bizid=${housePropertyVo.id}';
		ZDS_MESSAGE_CLIENT.openMenuLink('house_assessment_view', '房产评估详情', url);
	});
	
	//征信信息加载
	var creditInfoUrl='<z:ukey key="com.zdsoft.finance.creditManage.initCustomerCreditViews" context="admin"/>&caseApplyId=${caseApplyId}&customerIds=${customerIds}&mainCustomerId=${mainCustomerId}';
	$('#creditInfo').load(creditInfoUrl);
	
	//房产评估信息加载
	var houseEvaluateListUrl='<z:ukey key="com.zdsoft.finance.houseassessment.houseEvaluate.initHouseEvaluatedList" context="admin"/>&housePropertyId=${housePropertyVo.id}';
	$('#houseEvaluateList').load(houseEvaluateListUrl);
	
	//企业法院信息加载(汇法)
	var huiFaCompanyInfoUrl='<z:ukey key="com.zdsoft.finance.risk.findHuifaCompany" context="admin"/>&queryDatas=${huifaQueryParams}&caseApplyId=${caseApplyId}';
	$('#huiFaCompanyInfo').load(huiFaCompanyInfoUrl);
	
	//个人法院信息加载(汇法)
	var huiFaPersonalInfoUrl='<z:ukey key="com.zdsoft.finance.risk.findHuifaPersonal" context="admin"/>&codes=${huiFaPersonIdentities}&caseApplyId=${caseApplyId}';
	$('#huiFaPersonalInfo').load(huiFaPersonalInfoUrl);
	
	//关联贷款信息加载
	var relatedCaseUrl='<z:ukey key="com.zdsoft.finance.marketing.initCustomerRelationCaseView" context="admin"/>&customerId=${mainCustomerId}&exceptCaseIds=${caseApplyId}';
	$('#relatedCase').load(relatedCaseUrl);
	
	//工商信息加载
	var BusinessInfoUrl='<z:ukey key="com.zdsoft.finance.risk.findBusinessByName" context="admin"/>&companyNames=${companyNames}&caseApplyId=${caseApplyId}';
	$('#BusinessInfo').load(BusinessInfoUrl);
	
	$.ZUI.init("#div-detain");
	
    ZDS_WORKFLOW_CLIENT.saveFunction = function (datas) {
        //---------start------流程中有修改页面，需要提交业务数据操作------------------
        //校验
        var validate = $.ZUI.validateForm($('#chooseEmp'));
        if (!validate) {
            $.ZMessage.info("提示", "数据验证失败,请完善信息!");
            return false;
        }
        var params = $('#chooseEmp').serialize();
        var args = JSON.parse(datas.args);
        params += '&processInstanceId=' + args.processInstanceId;
        params += '&taskInstanceId=' + args.taskInstanceId;
        params += '&taskId=' + args.taskId;
        params += '&taskName=' + args.taskName;
        params += '&jobAppCd=' + args.jobAppCd;
        params += '&businessKey=${businessKey}';
    	params += '&projectId=${caseApplyId}';
        $.ajax({
        	url: '<z:ukey key="com.zdsoft.finance.casemanage.saveDispatchInfo" context="admin"/>&jsoncallback=?',
            data:params,
            type:"post",
            dataType:"json",
            success:function(rdata){
                if(rdata.resultStatus == 0){
               	    $('#caseTaskId').val(rdata.id);
                    ZDS_WORKFLOW_CLIENT.callBackFuntion(datas,ZDS_WORKFLOW_PARAM._STATUS_SUCCESS,rdata.msg);
                }else{
                    //执行回调函数
                    ZDS_WORKFLOW_CLIENT.callBackFuntion(datas,ZDS_WORKFLOW_PARAM._STATUS_ERROR,rdata.msg);
                }
            }
        });
        //---------end------流程中有修改页面，需要提交业务数据操作------------------
    };
    //提交方法
    ZDS_WORKFLOW_CLIENT.submitFunction = ZDS_WORKFLOW_CLIENT.saveFunction;
});
</script> 