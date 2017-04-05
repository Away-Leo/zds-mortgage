<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<div class="page-box">
 		<!-- 关联贷款信息 -->
 		<div id="relatedCase"></div>
       	<!-- 企业法院信息 -->
       	<div id="huiFaCompanyInfo"></div>
       	
       	<!-- 个人法院信息 -->
       	<div id="huiFaPersonalInfo"></div>
       	
        
        <!-- 工商信息 -->
        <div id="BusinessInfo"></div>
</div>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.form','zd/jquery.zds.message',
           'zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter',
           'common/zds-common-selecter'], function ($, CALLBACK,ZTOOLS) {
   
	//企业法院信息加载(汇法)
	var huiFaCompanyInfoUrl='<z:ukey key="com.zdsoft.finance.risk.findHuifaCompany" context="admin"/>&queryDatas=${huifaQueryParams}&caseApplyId=${projectId}';
	$('#huiFaCompanyInfo').load(huiFaCompanyInfoUrl);
	
	//个人法院信息加载(汇法)
	var huiFaPersonalInfoUrl='<z:ukey key="com.zdsoft.finance.risk.findHuifaPersonal" context="admin"/>&codes=${huiFaPersonIdentities}&caseApplyId=${projectId}';
	$('#huiFaPersonalInfo').load(huiFaPersonalInfoUrl);
	
	//关联贷款信息加载
	var relatedCaseUrl='<z:ukey key="com.zdsoft.finance.marketing.initCustomerRelationCaseView" context="admin"/>&customerId=${mainCustomerId}&exceptCaseIds=${projectId}';
	$('#relatedCase').load(relatedCaseUrl);
	
	//工商信息加载	
	var BusinessInfoUrl='<z:ukey key="com.zdsoft.finance.risk.findBusinessByName" context="admin"/>&companyNames=${companyNames}&caseApplyId=${projectId}';
	$('#BusinessInfo').load(BusinessInfoUrl);
	
	$.ZUI.init();
});
</script>
