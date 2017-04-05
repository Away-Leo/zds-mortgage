<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/common_js.jsp" %>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>产品编辑</title>
</head>
<body id="body">
<div class="page-box">
    <div class="page-title"></div>
    <div class="p10">
	    <div class="info-tab">
	        <ul class="tabs" id="info-tabs">
	            <li class="tabs-on" formatter="basicInfo"><a href="javascript:void(0);">基本信息</a></li>
	            <li lang="editTab" formatter="approvalOpinion"><a href="javascript:void(0);">审批意见</a></li>
	            <li lang="editTab" formatter="materialList"><a href="javascript:void(0);">资料清单</a></li>
	            <li lang="editTab" formatter="archivesList"><a href="javascript:void(0);">档案清单</a></li>
	            <li lang="editTab" formatter="partRepay"><a href="javascript:void(0);">分段还款</a></li>
	            <li lang="editTab" formatter="orgContract"><a href="javascript:void(0);">机构合同模板</a></li>
	            <li lang="editTab" formatter="orgFee"><a href="javascript:void(0);">机构费用项</a></li>
	            <!-- <li lang="editTab" formatter="repayPlan"><a href="javascript:void(0);">还款计划</a></li> -->
	            <li lang="editTab" formatter="processConfig"><a href="javascript:void(0);">流程配置</a></li>
	            <li lang="editTab" formatter="creditEntrustPlanConfig"><a href="javascript:void(0);">资金计划配置</a></li>
	        </ul>
	        <div class="tabcontents" id="info-tabcontents">
	            <div id="showBasicInfo">
	            </div>
	            <div class="hide" id="showApprovalOpinion">
                </div>
                <div class="hide" id="showMaterialList">
                </div>
                <div class="hide" id="showArchivesList">
                </div>
                <div class="hide" id="showPartRepay">
                </div>
                <div class="hide" id="showOrgContract">
                </div>
                <div class="hide" id="showOrgFee">
                </div>
                <!-- <div class="hide" id="showRepayPlan">
                </div> -->
                <div class="hide" id="showProcessConfig">
                </div>
                <div class="hide" id="showCreditEntrustPlanConfig">
                </div>
	        </div>
	    </div>
	</div>
</div>

<script type="text/javascript">
var productId = "";
seajs.use(['jquery', 'zd/jquery.zds.page.callback',  'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.combobox','zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter']
	, function ($, CALLBACK, Loading, Switch) {
	//开关
	var basicInfo=true;
	var approvalOpinion=true;
	var materialList=true;
	var archivesList=true;
	var partRepay=true;
   	var orgContract=true;
    var orgFee=true;
	//var repayPlan=true;
    var processConfig=true;
    var creditEntrustPlanConfig=true;
    
    //判断是否可选
    productId = "${id}";
    if(productId == "" && "${categoryId}" != ""){
    	Switch.disableTab("li[lang='editTab']");
    }else{
    	Switch.enableTab("li[lang='editTab']");
    }
  	//初始化
    var url='<z:ukey key="com.zdsoft.finance.product.basicInfo" context="admin"/>&productId='+productId+'&categoryId=${categoryId}';
    $('#showBasicInfo').load(url,function(){
    	basicInfo=false;
    });     
	//回调
	CALLBACK.basicInfo=function(){
		var url='<z:ukey key="com.zdsoft.finance.product.basicInfo" context="admin"/>&productId=' + productId;
     	if(basicInfo){
	      	$('#showBasicInfo').load(url,function(){
	      		basicInfo=false;
	      	});
     	}
     };
     //审批意见
     CALLBACK.approvalOpinion=function(){
     	var url='<z:ukey key="com.zdsoft.finance.approvalOpinion.list" context="admin"/>&productId=' + productId;
     	if(approvalOpinion){
	      	$('#showApprovalOpinion').load(url,function(){
	      		approvalOpinion=false;
	      	});
     	}
     };
     //资料清单
     CALLBACK.materialList=function(){
     	var url='<z:ukey key="com.zdsoft.finance.MaterialListPage" context="admin"/>&jsoncallback=?&productId=' +  productId;
     	if(materialList){
	      	$('#showMaterialList').load(url,function(){
	      		materialList=false;
	      	});
     	}
     };
     //档案清单
     CALLBACK.archivesList=function(){
     	var url='<z:ukey key="com.zdsoft.finance.productArchivesBill.initArchivesBill" context="admin"/>&productId=' + productId;
     	if(archivesList){
	      	$('#showArchivesList').load(url,function(){
	      		archivesList=false;
	      	});
     	}
     };
     //分段还款
     CALLBACK.partRepay=function(){
     	var url='<z:ukey key="com.zdsoft.finance.partRepayment.init" context="admin"/>&productId=' + productId;
     	if(partRepay){
	      	$('#showPartRepay').load(url,function(){
	      		partRepay=false;
	      	});
     	}
     };
     //机构合同模版
     CALLBACK.orgContract=function(){
     	var url='<z:ukey key="com.zdsoft.finance.productContract.init" context="admin"/>&productId=' + productId;
     	if(orgContract){
	      	$('#showOrgContract').load(url,function(){
	      		orgContract=false;
	      	});
     	}
     };
     //机构费用项
     CALLBACK.orgFee=function(){
     	var url='<z:ukey key="com.zdsoft.finance.feeOptionListPage" context="admin" />&productId=' + productId;
     	if(orgFee){
	      	$('#showOrgFee').load(url,function(){
	      		orgFee=false;
	      	});
     	}
     };
     //还款计划
     /* CALLBACK.repayPlan=function(){
     	var url='<z:ukey key="com.zdsoft.finance.repayPlanConfig.list" context="admin"/>&productId=' + productId;
     	if(repayPlan){
	      	$('#showRepayPlan').load(url,function(){
	      		repayPlan=false;
	      	});
     	}
     }; */
     //流程配置
     CALLBACK.processConfig=function(){
     	var url='<z:ukey key="com.zdsoft.finance.processConfig.list" context="admin"/>&productId=' + productId;
     	if(processConfig){
	      	$('#showProcessConfig').load(url,function(){
	      		processConfig=false;
	      	});
     	}
     };
     //资金计划配置
     CALLBACK.creditEntrustPlanConfig=function(){
     	var url='<z:ukey key="com.zdsoft.finance.creditEntrustPlanConfig.list" context="admin"/>&productId=' + productId;
     	if(creditEntrustPlanConfig){
	      	$('#showCreditEntrustPlanConfig').load(url,function(){
	      		creditEntrustPlanConfig=false;
	      	});
     	}
     }
            
     $.ZUI.init();
});
</script>
</body>
</html>