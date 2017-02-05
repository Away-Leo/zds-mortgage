<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.zdsoft.bpm.util.client.ProcessStoreHelper"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common_js.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主流程任务签处理页</title>
</head>
<body>
<div id="bpmDiv" data-spy="scroll" data-target="#nav" data-offset="0">
<div class="save">
	<button id="saveBt" class="btn-blue mr10" onclick="saveTaskOpinion();">保存</button>
	<button id="completeTaskBtn" class="btn-gray mr10" onclick="completeTask();">提交</button>
	<button id="printBtn" class="btn-blue mr10" style="display: none;" onclick="printPaper();">打印</button>
</div>
<div class="frm-content frm-bottom">
	<div class="ztree-box">
		<div id="ztree-sidebar" class="ztree-sidebar">
			<!--<h1 class="ztree-title">左侧菜单标题</h1>-->
			<ul class="sdu-nav" id="schedule">
				<li><a href="javascript:void(0);"  onclick="showMain();"><i class="icon-scedi"></i>必做事项</a>
					<ul class="sdu-list" id="compulsories">
					</ul>
				</li>
				<li><a href="javascript:void(0);"  onclick="showOption();"><i class="icon-scedit"></i>可做事项</a>
					<ul class="sdu-list" id="optionals">
					</ul>
				</li>
				<li><a href="javascript:void(0);"><i class="icon-scedit"></i>工作流</a>
					<ul class="sdu-list">
						<li><a href="javascript:void(0);" onclick="forwardTask();"><i class="icon-scedit"></i>转办</a></li>
						<li><a href="javascript:void(0);" onclick="inviteJoints();"><i class="icon-scedit"></i>协办</a></li>
						<li><a href="javascript:void(0);" onclick="showFlowChartView();"><i class="icon-scedit"></i>流程图</a></li>
						<li><a href="javascript:void(0);" onclick="showProcessLog();"><i class="icon-scedit"></i>流转日志</a></li>
					</ul>
				</li>
				<li><a href="javascript:void(0);" onclick="showProjectInfo();"><i class="icon-scedit"></i>项目资料</a>
					<ul class="sdu-list" id="projectInfoGroup">
						<!--<li><a href="javascript:void(0);"><i class="icon-scedit"></i>项目申请列表<b>3</b></a></li>
						<li><a href="javascript:void(0);"><i class="icon-scedit"></i>保证措施</a></li>
						<li><a href="javascript:void(0);"><i class="icon-scedit"></i>日常检查列表</a></li>-->
					</ul>
				</li>
			</ul>
		</div>
		<div id="ztree-content" class="ztree-content">
			<div class="m5">
				<div id="bpmContent"></div>
				<!--<iframe id="bpmIframe"  class="zui-iframe " frameborder="0" allowtransparency="true" src=""></iframe>-->
				<div class="page-box " id="showCommonBPM">
						<h1 class="page-title" id="titleName">审批意见</h1>
						<div id="opinionForm" class="p5">
							<form id="taskProcessOption" class="zui-form" action="javascript:void(0);"
								  zdata-options={"url":"http://XXXX/FindAllClints?jsoncallback=?","callBack":"saveCallBack"}>
	
								<input type="hidden" id="businessKey" name="businessId" value="${openTask.businessKey}">
								<input type="hidden" id="id" name="id" value="${opinionId}"/>
								<input type="hidden" id="taskCandidateId" name="taskCandidateId" value="${taskCandidateId}"/>
	
								<table class="table-flow" id="history" >
								</table>
								<table class="table-flow mb5 mt-1" id="qianpi">
									<tr >
										<th rowspan="3" style="width: 120px;"><strong>审批意见</strong></th>
										<th class="tr" style="width: 120px;">任务提醒方式</th>
										<td colspan="5" class="tl">
											<dl class="form-item block">
												<dd class="detail">
													<input class="zui-checkbox zui-validatebox" id="checkboxWarn" type="hidden"
														   data-multiple="true"
														   data-data="[{'id':'0','text':'任务提醒','isDefault':'true'},{'id':'1','text':'邮件提醒','isDefault':'true'},{'id':'2','text':'短信提醒'}]"
														   data-valuefield="id" data-textfield="text"
														   validate-type="Require">
												</dd>
											</dl>
										</td>
									</tr>
									<tr id="opinionLi">
										<th class="tr" id="opinionTaskName">操作</th>
										<td colspan="5" class="tl">
											<dl class="form-item">
												<dd class="detail" >
													<input id="opinion" name="opinion" class="zui-validatebox"  type="hidden"
														   validate-type="Require">
												</dd>
											</dl>
										</td>
									</tr>
									<tr id="adviceContentLi">
										<th class="tr">审批意见</th>
										<td colspan="5" class="tl">
											<dl class="form-item block">
												<dd class="detail">
													<label>
	                                        <textarea id="opinion_content" name="opinionContent" class="zui-area zui-validatebox" validate-type="Require"
													  placeholder="最多可以输入3000个字符" style="height: 100px;">${opinionContent }</textarea>
													</label>
												</dd>
											</dl>
										</td>
									</tr>
	
								</table>
							</form>
						</div>
				</div>
				<div class="zpage-box" style="display: none" id="showTaskOpinions">
					<div class="page-title" id="taskOpinionTitle"></div>
					<table class="table-flow mb5" id="taskOpinionContent"></table>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
<!-------------------------------------------------------转办任务弹窗（输入原因）------------------------------------------------------------------------>
<div id="forwardTaskDialog" style="display: none;">
	<form  id="forwardTaskContent" class="zui-form" method="post" enctype="multipart/form-data">

		<dl class="form-item" >
			<dt class="title"><b class="c-red mr5">*</b>任务接收人：</dt>
			<dd class="detail">
				<label>
					<input id="forwardTaskEmpNm" name="forwardTaskEmpNm" type="text"  class="zui-input zui-disabled zui-validatebox" style="width: 250px;" validate-type="Require"/>
					<input type="hidden" id="forwardTaskEmpCd" />
					<button type="button" class="btn-blue mr5" id="forwardTaskSelect">人员</button>
					<button type="button" class="btn-blue mr5" id="forwardTaskClear">清除</button>
				</label>
			</dd>
		</dl>
		<dl class="form-item block">
			<dt class="title">转办原因：</dt>
			<dd class="detail">
				<label>
					<textarea class="zui-area zui-validatebox" placeholder="最多可以输入200个字符" required="true" validate-type="Length[1-250]" maxlength="126" style="width: 420px;height:100px;" id="forwardTaskReason"></textarea>
				</label>
			</dd>
		</dl>
	</form>
</div>
<!-------------------------------------------------------协办任务弹窗（输入原因）------------------------------------------------------------------------>
<div id="inviteJointsDialog" style="display: none;">
	<form  id="inviteJointsContent" class="zui-form" method="post" enctype="multipart/form-data">
		<dl class="form-item">
			<dt class="title"><b class="c-red mr5">*</b>任务接收人：</dt>
			<dd class="detail">
				<textarea class="zui-area  zui-disabled easyui-validatebox" required="true" style="width: 250px;height: 40px;" id="inviteJointsEmpNm"></textarea>
				<input type="hidden" id="inviteJointsEmpCd" />
				<button type="button" class="btn-blue ml15" id="inviteJointsSelect">人员</button>
				<button type="button" class="btn-blue ml10 mt10" id="inviteJointsClear">清除</button>
			</dd>
		</dl>
		<dl class="form-item">
			<dt class="title">协办原因：</dt>
			<dd class="detail">
				<textarea class="zui-area easyui-validatebox" required="true" validType="length[1,250]" maxlength="126" style="width: 420px;height:100px;" id="inviteJointsReason" ></textarea>
			</dd>
		</dl>
	</form>
</div>

<script type="text/javascript" src="<%=resServer%>/assets/js/vendor-modules/process/zds-process-constant.js"></script>
<script type="text/javascript" src="<%=resServer%>/assets/js/vendor-modules/process/bpmclient_process_list.js"></script>
<script>
	seajs.use(['jquery','zd/jquery.zds.page.callback', 'zd/iframe', 'zd/tools', 'zd/jquery.zds.form', 'zd/jquery.zds.button', 'zd/jquery.zds.message'], function ($,CALLBACK, IFRAME, ZTOOlS) {
		//<ul class="sdu-nav" id="schedule"> 注意：id="schedule" 是必须的
		//============================================================
		//optionChange
		ZTOOlS.treeUpOrDown('#schedule');
		ZTOOlS.layoutResize('#ztree-sidebar', 60);
		ZTOOlS.layoutResize('#ztree-content', 60);
		
		$.ZUI.initForms('#opinionForm');
	});
	var uri_get_all_post = '<z:res resource="ess.post.find-all-page" isDefault="true"/>' + "&jsonCallBack=?";
    var uri_get_all_emp = '<z:res resource="essential.comm.employees.select" isDefault="true"/>' + "&jsoncallback=?";
    var uri_get_all_post_select = '<z:res resource="ess.post.find-all-select" isDefault="true"/>' + "&jsonCallBack=?";
    var uri_all_org_tree = '<z:res resource="enssential.org.findOrgToTree" isDefault="true"/>' + "&jsoncallback=?";
	//连接
	var retrieveResource_ = '<z:res resource="public.enss.common.select" isDefault="true"/>&jsoncallback=?';
	//是否签批页面，为true时，isShowConfirm、isShowOpinion有效
	var isOpinion_ = '${openTask.isOpinion}';
	//是否显示签批选项下拉框
	var isShowConfirm_='${openTask.isShowConfirm}';
	//是否显示意见框
	var isShowOpinion_='${openTask.isShowOpinion}';
	//是否手工选择下一任务处理人
	var isSelectAssignee_ = '${openTask.isSelectAssignee}';
	//签批选项对应的值
	var confirmOptionPage = '${openTask.confirmOptionPage}';
	//首页事项
	var homeJob_ = '${openTask.homeJobPage}';
	//必做事项
	var compulsories_='${openTask.compulsoriesPage}';
	//选做事项
	var optionals_='${openTask.optionalsPage}';
	//常用功能
	var usuals_='${openTask.usualsPage}';
	//首页事项的aurl
	var homeUrl ='${homeUrl}';
	var homeAppCd = '${openTask.homeJob.appCd}';
	var homeRes = '${openTask.homeJob.resource}';
	var homePar = '${openTask.homeJob.argsAsUrl}';
	var homeSaveArgs='${openTask.homeJob.saveArgsAsUrl}';
	//公共下拉
	var commonSelect = '<z:res resource="public.enss.common.select" isDefault="true"/>&jsoncallback=?';
	//提交任务的url
	var completeTask = '<z:ukey key="workflow.process.completeTask"  context="admin"/>&jsoncallback=?';
	//保存审批意见url
	var saveTaskOpinionUrl='<z:ukey key="workflow.process.saveTaskOpinion"  context="admin"/>&jsoncallback=?';
	// 并行处理状态 1：显示‘领取’菜单 0：不显示 -1：显示‘放弃’菜单
	var parallelStatus_ = '${openTask.parallelStatus}';
	//获取下一任务处理人
	var reckonNextTaskUsersUrl='<z:ukey key="workflow.process.reckonNextTaskUsers"  context="admin"/>&jsoncallback=?';
	//取得历史签批意见
	var findOtherTaskOptionsUrl='<z:ukey key="workflow.process.findOtherTaskOptions"  context="admin"/>&jsoncallback=?'; 
	//判断允许是否提交
	var checkCompulsoriesUrl='<z:ukey key="workflow.process.checkCompulsories"  context="admin"/>&jsoncallback=?';
	//提交任务
	var completeTaskUrl='<z:ukey key="workflow.process.completeTask"  context="admin"/>&jsoncallback=?';
	//任务的领取/放弃
	var taskManagerUrl='<z:ukey key="workflow.process.taskManager"  context="admin"/>&jsoncallback=?';
	//暂停任务
	var processManagerUrl='<z:ukey key="workflow.process.processManager"  context="admin"/>&jsoncallback=?';
	//任务转办
	var forwardTaskUrl='<z:ukey key="workflow.process.forwardTask"  context="admin"/>&jsoncallback=?';
	//任务转办
	var inviteJointsUrl='<z:ukey key="workflow.process.inviteJoints"  context="admin"/>&jsoncallback=?';
	//取得点击的连接 
	var getMenuUrl='<z:ukey key="workflow.process.getMenuUrl"  context="admin"/>&jsoncallback=?';
	//获取已完成事项链接
	var getFinishedJobUrl='<z:ukey key="workflow.process.getFinishedjob"  context="admin"/>&jsoncallback=?';
	//任务提醒方式
	var warnOptionPage = '[{"key":"0","value":"任务提醒"},{"key":"1","value":"邮件提醒"},{"key":"2","value":"短信提醒"}]';
	//项目文件夹
	var showProjectFolderGroupUrl = '';
	//项目文件夹 大类数据
	var showProjectFolderInfoUrl = '';
    //项目文件夹 某项数据
	var findProjectFolderInfoInnerUrl = '';
	//项目文件夹 的历史数据
	var showProjectFolderTaskOpinionsUrl = '';
	<%-------------------------------------- 工作流参数  --------------------------------------------%>
	//流程实例ID
	var processInstanceId = '${openTask.processInstanceId}';
	//任务实例ID
	var taskInstanceId='${openTask.taskInstId}';
	var businessKey = '${openTask.businessKey}';
	var opinionId = '${opinion}';
	var taskCandidateId = '${taskCandidateId}';
	var openTest ='<z:ukey key="openTest"  context="admin"/>&jsoncallback=?';
	var taskName='${openTask.taskName}';
	var testurl="<%=localServer%>";
	//父页面的id
	var parentIframeId = "${parentIframeId}";
	//页签打开方式
	var openMethod = "${openMethod}";
	//错误消息
	var errorMsg='${errorMsg}';
	//状态
	var resultStatus='${resultStatus}';
	//打开流程序图页面 
	var initBpmProgressViewPageUrl = '<z:res resource="workflow.initBpmProgressViewPage" isDefault="false" application="<%=ProcessStoreHelper.getWorkflowAppCd()%>"/>&jsoncallback=?'; 
/* var initBpmProgressViewPageUrl = '<z:res resource="workflow.initBpmProgressViewPage" isDefault="false" application="zds-workflow-web"/>&jsoncallback=?'; */
	//流程日志
	var initProcessLogUrl = '<z:res resource="workflow.process.loadProcessLogPage" isDefault="false" application="<%=ProcessStoreHelper.getWorkflowAppCd()%>"/>&jsoncallback=?'; 
	/* var initProcessLogUrl = '<z:res resource="workflow.process.loadProcessLogPage" isDefault="false" application="zds-workflow-web"/>&jsoncallback=?'; */

	//项目资料
	var projectInformationUrl ='';// '${projectInformationUrl}';
	//项目文件夹
	var showProjectCd  = '${showProjectCd}';
	
	//处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外
	function banBackSpace(e){
	  var ev = e || window.event;//获取event对象
	  var obj = ev.target || ev.srcElement;//获取事件源
	  var t = obj.type || obj.getAttribute('type');//获取事件源类型
	  //获取作为判断条件的事件类型
	  var vReadOnly = obj.getAttribute('readonly');
	  //处理null值情况
	  vReadOnly = (vReadOnly == "") ? false : vReadOnly;
	  //当敲Backspace键时，事件源类型为密码或单行、多行文本的，
	  //并且readonly属性为true或enabled属性为false的，则退格键失效
	  var flag1=(ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea")
	  && vReadOnly=="readonly")?true:false;
	  //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
	  var flag2=(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea")
	          ?true:false;
	
	  //判断
	  if(flag2){
	    return false;
	  }
	  if(flag1){
	    return false;
	  }
	}
	
	window.onload=function(){
	  //禁止后退键 作用于Firefox、Opera
	  document.onkeypress=banBackSpace;
	  //禁止后退键  作用于IE、Chrome
	  document.onkeydown=banBackSpace;
	}
	
	/**
    * 打印
    */
	window.printPaper=function (){
		//打印地址
		var printSrc="<z:ukey key='zf.projectfolder.projectInfoPrint' context='admin'/>&folderId="+folderId+"&groupNm="+folderGroupNm+"&projectCd="+showProjectCd+"&srcType=print";
		window.open(printSrc);
	}
</script>
	
</body>
</html>