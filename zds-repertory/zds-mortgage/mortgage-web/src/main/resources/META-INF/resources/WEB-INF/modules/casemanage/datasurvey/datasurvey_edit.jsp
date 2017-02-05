<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../../common/common_js.jsp'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资调</title>
</head>
<body>
	<div class="frm-content">
		<div class="page-box">
			<div class="p10">
				<div class="info-tab">
					<ul class="tabs">
						<li class="tabs-on" formatter="applicantInfo"><a
							href="javascript:void(0);">申请人信息</a></li>
						<li formatter="houseInfo"><a href="javascript:void(0);">押品信息</a></li>
						<li formatter="riskInfo"><a href="javascript:void(0);">风险信息</a></li>
						<li formatter="save"><a href="javascript:void(0);">现场问卷</a></li>
						<li formatter="feeInfo"><a href="javascript:void(0);">费用信息</a></li>
						<li formatter="save"><a href="javascript:void(0);">征信信息</a></li>
						<li formatter="receivablePlan"><a href="javascript:void(0);">还款计划</a></li>
						<li formatter="save"><a href="javascript:void(0);">其他资产</a></li>
						<li formatter="save"><a href="javascript:void(0);">家庭收入</a></li>
						<li formatter="save"><a href="javascript:void(0);">附件</a></li>
					</ul>
					<div class="tabcontents" id="showTabcontents">
						<div id="showApplicantInfo">
						</div>

						<div class="hide" id="showHouseInfo">押品信息</div>
						<div class="hide" id="showRiskInfo">
						</div>
						<div class="hide">现场问卷</div>
						<div class="hide" id="showFeeInfo">
						</div>
						<div class="hide">征信信息</div>
						<div class="hide" id="showReceivablePlan">还款计划</div>
						<div class="hide">其他资产</div>
						<div class="hide">家庭收入</div>
						<div class="hide">附件</div>
					</div>

				</div>
			</div>

		</div>
	</div>
	<script type="text/javascript">
	seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.loading', 'zd/switch'],
		   function ($, callback, Loading, Switch) {
				//$.ZUI.init();
				var applicantInfo = true;
				var riskInfo = true;
				var feeInfo = true;
				var houseInfo=true
				var receivablePlan = true;
				callback.applicantInfo = function() {
						var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.load" context="admin"/>&caseApplyId=${caseApplyId}';
						if (applicantInfo) {
							$('#showApplicantInfo').load(url, function() {
								applicantInfo = true;
							});
						}
					}
				//押品信息
				callback.houseInfo = function() {
					var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.pledgeInfoMain" context="admin"/>&caseApplyId=${caseApplyId}';
					if (riskInfo) {
						$('#showHouseInfo').load(url,function() {
									houseInfo = true;
								});
					}
				}
				//风险信息
				callback.riskInfo = function() {
					var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.riskinformation.edit" context="admin"/>&caseApplyId=${caseApplyId}';
					if (riskInfo) {
						$('#showRiskInfo').load(url,function() {
									riskInfo = true;
								});
					}
				}
				//费用信息
				callback.feeInfo = function() {
					var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.feeinfomation.edit" context="admin"/>&jsoncallback=?&caseApplyId=${caseApplyId}';
					if (feeInfo) {
						$('#showFeeInfo').load(url,function() {
									feeInfo = true;
								});
					}
				}
				//还款计划
				callback.receivablePlan = function() {
					var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.caseApplyDetail" context="admin"/>&jsoncallback=?&caseApplyId=${caseApplyId}';
					if (receivablePlan) {
						$('#showReceivablePlan').load(url,function() {
							receivablePlan = true;
								});
					}
				}
				//初始化
				var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.load" context="admin"/>&caseApplyId=${caseApplyId}';
				$('#showApplicantInfo').load(url, function() {
					applicantInfo = true;
				});
				//保存
			    ZDS_WORKFLOW_CLIENT.saveFunction = function (datas) {
					 var WORKFLOW_FLAG=ZDS_WORKFLOW_PARAM._STATUS_VALIDATE_ERROR;//1、提交，需要，默认提交失败！
					//---------start------流程中有修改页面，需要提交业务数据操作------------------
				var status=window.saveData();
				if(status){
					//流程参数
					var args = JSON.parse(datas);
					var params = '&processInstanceId=' + args.processInstanceId;
			        params += '&taskInstanceId=' + args.taskInstanceId;
			        params += '&taskId=' + args.taskId;
			        params += '&taskName=' + args.taskName;
			        params += '&jobAppCd=' + args.jobAppCd;
			        params += '&projectId=${caseApplyId}';
			        params += '&businessKey=${businessKey}';
					$.ajax({
						url:'<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.editJobSave" context="admin"/>',
						data:params,
						type:"post",
						async: false,
						dataType:"json",
						traditional:true,
						success:function(rdata){
							if(rdata.resultStatus == 0){
								WORKFLOW_FLAG=ZDS_WORKFLOW_PARAM._STATUS_SUCCESS;
							}else{
								$.ZMessage.error("错误", rdata.msg, function () {
				                });
							}
						}
					});
			    	//---------end------流程中有修改页面，需要提交业务数据操作------------------
			    	
			    	//4、返回流程状态
			    	return WORKFLOW_FLAG;
				}
			    };
			    
			    //提交方法
			    ZDS_WORKFLOW_CLIENT.submitFunction = ZDS_WORKFLOW_CLIENT.saveFunction;
			});

	</script>
</body>

</html>