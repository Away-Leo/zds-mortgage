<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../../common/common_js.jsp'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div class="save">
		<button id="btn-save" class="btn-blue mr10" style="display: none">保存</button>
	</div>
	<div class="frm-content">
		<div class="page-box">
			<div class="p10">
				<div class="info-tab">
					<ul class="tabs">
						<li class="tabs-on" formatter="receivable"><a href="javascript:void(0);">查看还款计划</a></li>
						<li formatter="followUpInfo"><a href="javascript:void(0);">跟进信息</a></li>
						<li formatter="receivablePlanEdit"><a href="javascript:void(0);">编辑还款计划</a></li>
					</ul>
					<div class="tabcontents">
						<div id="showReceivable"></div>
						<div class="hide" id="showFollowUpInfo"></div>
						<div class="hide" id="showReceivablePlanEdit"></div>
					</div>
					
				</div>
				
			</div>

		</div>
	</div>
	<script type="text/javascript">
	seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'],
	  	function ($, callback, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
			var receivable = true;
			var followUpInfo = true;
			var receivablePlanEdit = true;
			//跟进信息
			callback.followUpInfo = function() {
				var url = '<z:ukey key="com.zdsoft.finance.caseManager.initFollowUp" context="admin"/>&caseApplyId=${caseApplyId }';
				$("#btn-save").hide();
				if (followUpInfo) {
					$('#showFollowUpInfo').load(url,function() {
						followUpInfo = false;
					});
				}
			}
			//编辑还款计划
			callback.receivablePlanEdit = function() {
				var receivableInfoId = "${receivableInfoId }";
				if(!receivableInfoId){
					$.ZMessage.error("错误", "该案件尚未生成还款计划");
					return false;
				}
				var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.receivablePlanGeneratePage" context="admin"/>&jsoncallback=?&caseApplyId=${caseApplyId }';
				$("#btn-save").show();
				if (receivablePlanEdit) {
					$('#showReceivablePlanEdit').load(url,function() {
						receivablePlanEdit = false;
					});
				}
			}
			callback.receivable = function(){
				$("#btn-save").hide();
			}
			
			//查看还款计划(初始化)
			var url = '<z:ukey key="com.zdsoft.finance.caseManager.receivable" context="admin"/>&caseApplyId=${caseApplyId }';
			$('#showReceivable').load(url, function() {
				receivable = false;
			});
			
			$("#btn-save").click(function(){
				var rowsData = $('#receivablePlanEdit').ZTable("getRows");
				$.ajax({
		            type: 'post',
		            url: '<z:ukey key="com.zdsoft.finance.casemanage.receivablePlanManager.saveReceivablePlan" context="admin"/>&jsoncallback=?&receivableInfoId=${receivableInfoId }',
		            data : {"receivablePlanJson":JSON.stringify(rowsData)},
		            dataType: 'json',
		            success: function (data) {
		            	console.log(data);
		                if (data.resultStatus == 0) {
		                	$.ZMessage.success("成功", "保存还款计划成功", function () {
		                		
		                  	 });
		                }else{
		                	$.ZMessage.error("错误", data.msg, function () {
		                    });
		                }
		            },
			        error: function () {
			           	$.ZMessage.error("错误", "保存信息系统异常，请联系管理员", function () {
			            });
		            }
		        });
			});
		});
				
	</script>
</body>

</html>