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
    <div class="page-title">功能列表</div>
    <div class="p10">
	    <div class="info-tab">
	        <ul class="tabs" id="info-tabs">
	            <li class="tabs-on" formatter="basicInfo"><a href="javascript:void(0);">基本信息</a></li>
	            <li formatter="approvalOpinion" class=""><a href="javascript:void(0);">审批意见</a></li>
	            <li formatter="materialList"><a href="javascript:void(0);">资料清单</a></li>
	            <li formatter="archivesList"><a href="javascript:void(0);">档案清单</a></li>
	            <li formatter="partRepay"><a href="javascript:void(0);">分段还款</a></li>
	            <li formatter="orgContract"><a href="javascript:void(0);">机构合同模板</a></li>
	            <li formatter="orgFee"><a href="javascript:void(0);">机构费用项</a></li>
	            <li formatter="repayPlan"><a href="javascript:void(0);">还款计划</a></li>
	            <li formatter="processConfig"><a href="javascript:void(0);">流程配置</a></li>
	            <li formatter="creditEntrustPlanConfig"><a href="javascript:void(0);">资金计划配置</a></li>
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
                <div class="hide" id="showRepayPlan">
                </div>
                <div class="hide" id="showProcessConfig">
                </div>
                <div class="hide" id="showCreditEntrustPlanConfig">
                </div>
	        </div>
	    </div>
	</div>
</div>

<script type="text/javascript">
    seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'
            ]
            , function ($, CALLBACK, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
                //开关
                var basicInfo=true;
                var approvalOpinion=true;
                var materialList=true;
                var archivesList=true;
                var partRepay=true;
                var orgContract=true;
                var orgFee=true;
                var repayPlan=true;
                var processConfig=true;
                var creditEntrustPlanConfig=true;
                
                //回调
				CALLBACK.basicInfo=function(){
                	var url='<z:ukey key="com.zdsoft.finance.product.basicInfo" context="admin"/>&id=${product.id}';
                	if(basicInfo){
	                	$('#showBasicInfo').load(url,function(){
	                		basicFlag=false;
	                	});
                	}
                }     
                CALLBACK.approvalOpinion=function(){
                	var url='<z:ukey key="com.zdsoft.finance.approvalOpinion.list" context="admin"/>&productId=${product.id}';
                	if(approvalOpinion){
	                	$('#showApprovalOpinion').load(url,function(){
	                		approvalOpinion=false;
	                	});
                	}
                }
                CALLBACK.materialList=function(){
					//com.zdsoft.finance.MaterialListPage
                	var url='<z:ukey key="com.zdsoft.finance.MaterialListPage" context="admin"/>&jsoncallback=?&productCode=${product.id}&productName=${product.productName}';
                	if(materialList){
	                	$('#showMaterialList').load(url,function(){
	                		materialList=false;
	                	});
                	}
                }
                //档案清单
                CALLBACK.archivesList=function(){
                	var url='<z:ukey key="com.zdsoft.finance.productArchivesBill.initArchivesBill" context="admin"/>&productId=${product.id}';
                	if(archivesList){
	                	$('#showArchivesList').load(url,function(){
	                		archivesList=false;
	                	});
                	}
                }
                //分段还款
                CALLBACK.partRepay=function(){
                	var url='<z:ukey key="com.zdsoft.finance.partRepayment.init" context="admin"/>&productId=${product.id}';
                	if(partRepay){
	                	$('#showPartRepay').load(url,function(){
	                		feeFlag=false;
	                	});
                	}
                }
                //机构合同模版
                CALLBACK.orgContract=function(){
                	var url='<z:ukey key="com.zdsoft.finance.productContract.init" context="admin"/>&productId=${product.id}';
                	if(orgContract){
	                	$('#showOrgContract').load(url,function(){
	                		orgContract=false;
	                	});
                	}
                }
                CALLBACK.orgFee=function(){
                	var url='<z:ukey key="com.zdsoft.finance.feeOptionListPage" context="admin" />&productCode=${product.id}&productName=${product.productName}';
                	if(orgFee){
	                	$('#showOrgFee').load(url,function(){
	                		orgFee=false;
	                	});
                	}
                }
                CALLBACK.repayPlan=function(){
                	var url='<z:ukey key="com.zdsoft.finance.repayPlanConfig.list" context="admin"/>&productId=${product.id}';
                	if(repayPlan){
	                	$('#showRepayPlan').load(url,function(){
	                		repayPlan=false;
	                	});
                	}
                }
                CALLBACK.processConfig=function(){
                	var url='<z:ukey key="com.zdsoft.finance.processConfig.list" context="admin"/>&productId=${product.id}';
                	if(processConfig){
	                	$('#showProcessConfig').load(url,function(){
	                		processConfig=false;
	                	});
                	}
                }
                CALLBACK.creditEntrustPlanConfig=function(){
                	var url='<z:ukey key="com.zdsoft.finance.creditEntrustPlanConfig.list" context="admin"/>&productId=${product.id}';
                	if(creditEntrustPlanConfig){
	                	$('#showCreditEntrustPlanConfig').load(url,function(){
	                		creditEntrustPlanConfig=false;
	                	});
                	}
                }
                
                //初始化
                var url='<z:ukey key="com.zdsoft.finance.product.basicInfo" context="admin"/>&id=${product.id}';
                $('#showBasicInfo').load(url,function(){
                	basicInfo=false;
                });
                
                $.ZUI.init();
            });
</script>
</body>
</html>