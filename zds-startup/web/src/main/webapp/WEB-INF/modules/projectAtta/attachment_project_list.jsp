<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<%@ include file='../common/common_js.jsp'%>
<title>附件管理-项目列表</title>
</head>
<body>
<div class="frm-content">
	<!-- 查询区域 -->
	<div class="page-box">
		<div class="page-title">项目列表</div>
		<div class="p10">
			<form id="search_from" class="zui-form form-search" method="post">
			<!-- TODO 查询表单的所有name调整 -->
				<dl class="form-item">
					<dt class="title">项目案号：</dt>
					<dd class="detail">
						<label> <input class="zui-input"
							 id="meetingMinutesNo" name="meetingMinutesNo|LK|S">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">项目阶段：</dt>
					<dd class="detail">
						<!-- TODO 项目阶段的url -->
						 <input class="zui-combobox zui-validatebox" id="meetingType" name="meetType|E|S" type="hidden"
                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=mttp"
                              data-valuefield="fullcode" data-textfield="name" validate-type="Require">
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">客户名称：</dt>
					<dd class="detail">
						<label> <input class="zui-input"
							 id="meetingPlace" name="meetingPlace|LK|S">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">项目经理：</dt>
					<dd class="detail">
						<label> <input class="zui-input"
							 id="meetingPlace" name="meetingPlace|LK|S">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
                    <dt class="title">申请日期：</dt>
                    <dd class="detail">
                        <label>
                            <input type="text" id="startTimeLocal" class="zui-input width2-1" readonly="readonly" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'startTime'})">
                            <input type="hidden" id="startTime" />
                        </label>
                        <span class="word">~</span>
                        <label>
                            <input type="text" id="endTimeLocal" class="zui-input width2-1"  readonly="readonly" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'endTime'})">
                            <input type="hidden" id="endTime"/>
                        </label>
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">项目金额(万)：</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-input width2-1" id="meetingPlace" name="meetingPlace|LK|S">
                        </label>
                        <span class="word">~</span>
                        <label>
                            <input class="zui-input width2-1" id="meetingPlace" name="meetingPlace|LK|S">
                        </label>
                    </dd>
                </dl>
				<dl class="form-btn">
					<button type="button" class="btn-search-blue" id="btn-query">查询</button>
					<button type="button" class="btn-search-gray" id="btn-reset">重置</button>
				</dl>
			</form>
		</div>
		<div class="p10">
			<div id="project-datagrid"></div>
		</div>
	</div>
</div>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.table'], function($, CALLBACK) {
	$('#project-datagrid').ZTable({
		url:'<z:ukey key='com.zdsoft.finance.project.findProjectList' context='admin'/>',
		singleSelect:true,
		isRowNum:true,
		rows:10,
		currentPage:1,
		pagination:true,
		tableCls:'table-index',//table的样式
		columns:[[{field:'projectCd', title:'项目案号', align:'center'},
	          {field:'clientNm', title:'客户名称', align:'left'},
	          {field:'productNm', title:'产品名称', align:'left'},
	          {field:'status', title:'项目阶段', align:'left'},
	          {field:'currentAmount', title:'项目金额(万)', align:'left'},
	          {field:'acceptEmpNm', title:'项目经理', align:'left'},
	          {field:'belongOrgNm', title:'所属部门', align:'left'} ,
	          {field:'id', title:'操作', align:'center',formatter:function(r,v){
	        	  return "<a  href='javascript:void(0);' class='c-blue superlink' onclick='attachmentManage' title='附件管理'>附件管理</a>";
	          }}]]
	});
	//点击查询
	$("#btn-query").click(function() {
		doSearch();
	});
	
	//点击重置
	$("#btn-reset").click(function() {
		$("#search_from input").val("");
	});
	// 查询数据
	function doSearch() {
		// TODO
		var formArray=$("#search_from").serialize();
		formArray = decodeURIComponent(formArray, true);
		var startTime = $('#startTime').val();
		var endTime = $('#endTime').val();
		formArray += '&meetingDate|BT|S='+startTime+','+endTime;
		$('#project-datagrid').ZTable("reload",formArray);
	};
	ZDS_MESSAGE_CLIENT.refreshThis=function(){
		doSearch();
    };
	// 项目管理页面跳转
	CALLBACK.attachmentManage = function(index,row){
		var projectAttaInfosUrl = '<z:ukey key="com.zdsoft.finance.projectatta.projectAttaListPage" context="admin"/>&jsoncallback=?&projectId='+row.id;
         ZDS_MESSAGE_CLIENT.openMenuLink('附件管理','附件管理',projectAttaInfosUrl + "&openMethod=tabs");
	};
	$.ZUI.init();
});
</script>
</body>
</html>