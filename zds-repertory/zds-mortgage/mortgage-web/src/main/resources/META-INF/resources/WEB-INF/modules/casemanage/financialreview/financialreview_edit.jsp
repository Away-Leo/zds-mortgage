<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../../common/common_js.jsp'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>财务复核</title>
</head>
<body>
	<div class="save">
		<button id="btn-save" class="btn-blue mr10">保存</button>
		<button id="btn-cancel" class="btn-blue mr10">提交</button>
	</div>
	<div class="frm-content">
		<div class="page-box">
			<div class="p10">
				<div class="info-tab">
					<ul class="tabs">
						<li class="tabs-on" formatter="caseInfo"><a
							href="javascript:void(0);">案件信息</a></li>
						<li formatter="feeInfo"><a href="javascript:void(0);">费用信息</a></li>
						<li><a href="javascript:void(0);">档案信息</a></li>
						<li><a href="javascript:void(0);">还款计划</a></li>
						<li><a href="javascript:void(0);">影像资料</a></li>
					</ul>
					<div class="tabcontents">
						<div id="showCaseInfo">
						</div>

						<div class="hide" id="showFeeInfo">费用信息</div>
						<div class="hide">档案信息</div>
						<div class="hide">还款计划</div>
						<div class="hide" >影像资料</div>
					</div>

				</div>
			</div>

		</div>
	</div>
	<script type="text/javascript">
	seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'],
			   function ($, callback, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
					var caseInfo = true;
					var feeInfo = true;
					callback.caseInfo = function() {
							var url = '<z:ukey key="com.zdsoft.finance.casemanage.financialreview.caseinfomation.edit" context="admin"/>&caseApplyId=${caseApplyId}';
							if (caseInfo) {
								$('#showCaseInfo').load(url, function() {
									caseInfo = false;
								});
							}
						}
					//费用信息
					callback.feeInfo = function() {
						var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.feeinfomation.view" context="admin"/>&jsoncallback=?&caseApplyId=${caseApplyId}';
						if (feeInfo) {
							$('#showFeeInfo').load(url,function() {
										feeInfo = false;
									});
						}
					}

					//初始化
					var url = '<z:ukey key="com.zdsoft.finance.casemanage.financialreview.caseinfomation.edit" context="admin"/>&jsoncallback=?&caseApplyId=${caseApplyId}';
					$('#showCaseInfo').load(url, function() {
						caseInfo = false;
					});
				});
	</script>
</body>

</html>