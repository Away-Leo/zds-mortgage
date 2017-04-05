<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../../common/common_js.jsp'%>
<!-- 资调查看 -->
<div class="info-tab">
	<ul class="tabs">
		<li class="tabs-on" formatter="applicantInfo"><a href="javascript:void(0);">申请人信息</a></li>
		<li formatter="houseInfo"><a href="javascript:void(0);">押品信息</a></li>
		<li formatter="questionnaireInfo"><a href="javascript:void(0);">现场问卷</a></li>
		<li formatter="riskInfo"><a href="javascript:void(0);">风险信息</a></li>
		<li formatter="feeInfo"><a href="javascript:void(0);">费用信息</a></li>
		<li formatter="creditInfo"><a href="javascript:void(0);">征信信息</a></li>
		<li formatter="receivablePlan"><a href="javascript:void(0);">还款计划</a></li>
		<li formatter="otherProperty"><a href="javascript:void(0);">其他资产</a></li>
		<li formatter="familyInComeInfo"><a href="javascript:void(0);">家庭收入</a></li>
		<li formatter="attachment"><a href="javascript:void(0);">附件</a></li>
	</ul>
	<div class="tabcontents">
		<div id="showApplicantInfo">申请人信息</div>
		<div class="hide" id="showHouseInfo">押品信息</div>
		<div class="hide" id="questionnaireInfo">现场问卷</div>
		<div class="hide" id="showRiskInfo">风险信息</div>
		<div class="hide" id="showFeeInfo">费用信息</div>
		<div class="hide" id="creditInfo">征信信息</div>
		<div class="hide" id="showReceivablePlan">还款计划</div>
		<div class="hide" id="showOtherProperty">其他资产</div>
		<div class="hide" id="showFamilyInComeInfo">家庭收入</div>
		<div class="hide" id="attachment">附件</div>
	</div>
</div>
<script type="text/javascript">
	var caseApplyId = "${caseApplyId}";
	seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.loading', 'zd/switch'],
	   function ($, callback, Loading, Switch) {
			var applicantInfo = true;
			var riskInfo = true;
			var feeInfo = true;
			var houseInfo=true;
			var creditInfo = true;
			var questionnaireInfo = true;
			var otherProperty = true;
			var receivablePlan = true;
			var familyInComeInfo = true;
			
			//初始化申请人信息
			var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.loadView" context="admin"/>&caseApplyId=${caseApplyId}';
			if (applicantInfo) {
				$('#showApplicantInfo').load(url, function() {
					applicantInfo = false;
				});
			}
			//押品信息
			callback.houseInfo = function() {
				var url = '<z:ukey key="com.cnfh.rms.marketing.houseProperty.viewHousePropertyTab" context="admin"/>&caseApplyId=${caseApplyId}';
				if (riskInfo) {
					$('#showHouseInfo').load(url,function() {
						houseInfo = false;
					});
				}
			}
			//现场问卷
			callback.questionnaireInfo = function() {
				var url = '<z:ukey key="com.zdsoft.finance.question.editQuestionnaire" context="admin"/>&caseApplyId=${caseApplyId}&sceneTypeCode=YWDM0010202';
				if (questionnaireInfo) {
					$('#questionnaireInfo').load(url,function() {
						questionnaireInfo = false;
					});
				}
			}
			//风险信息
			callback.riskInfo = function() {
				var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.riskinformation.viewRiskInformation" context="admin"/>&caseApplyId=${caseApplyId}';
				if (riskInfo) {
					$('#showRiskInfo').load(url,function() {
						riskInfo = false;
					});
				}
			}
			//征信信息
			callback.creditInfo = function() {
				var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.creditinfomation.view" context="admin"/>&jsoncallback=?&caseApplyId=${caseApplyId}';
				if (creditInfo) {
					$('#creditInfo').load(url,function() {
						creditInfo = false;
					});
				}
			}
			//费用信息
			callback.feeInfo = function() {
				var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.feeinfomation.view" context="admin"/>&caseApplyId=${caseApplyId}';
				if (feeInfo) {
					$('#showFeeInfo').load(url,function() {
						feeInfo = false;
					});
				}
			}
			//还款计划
			callback.receivablePlan = function() {
				var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.viewReceivablePlanDetail" context="admin"/>&jsoncallback=?&caseApplyId=${caseApplyId}';
				if (receivablePlan) {
					$('#showReceivablePlan').load(url,function() {
						receivablePlan = false;
					});
				}
			}
			//其他资产
			callback.otherProperty = function(){
				var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.otherPropertyView" context="admin"/>&jsoncallback=?&caseApplyId=${caseApplyId}';
				if (otherProperty) {
					$('#showOtherProperty').load(url,function() {
						otherProperty = false;
					});
				}
			}
			//家庭收入信息
			callback.familyInComeInfo = function() {
				var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.viewFamilyincomeinfo" context="admin"/>&caseApplyId=${caseApplyId}';
				if (familyInComeInfo) {
					$('#showFamilyInComeInfo').load(url,function() {
						familyInComeInfo = false;
					});
				}
			}
			//附件
			callback.attachment = function() {
				//删除现场问卷调查样式
				var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.attachment.load" context="admin"/>&caseApplyId=${caseApplyId}&businessKey=${businessKey}';
				if (attachment) {
					$('#attachment').load(url,function() {
						attachment = false;
					});
				}
			}
			
			/**
			 * 保存方法
			 * @param datas 传入的参数
			 */
			ZDS_WORKFLOW_CLIENT.saveFunction = function (datas) {
				ZDS_WORKFLOW_CLIENT.callBackFuntion(datas,ZDS_WORKFLOW_PARAM._STATUS_SUCCESS);
			};
			ZDS_WORKFLOW_CLIENT.submitFunction = ZDS_WORKFLOW_CLIENT.saveFunction;
		});
</script>