<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<%@ include file='../common/common_js.jsp'%>
<title>附件管理-附件列表</title>
</head>
<body>
<div class="frm-content">
	<div class="page-box">
		<div class="page-title">${not empty projectInfo.clientNm ? "客户名称：".concat(projectInfo.clientNm.concat(">>")) : "" }${not empty projectInfo.projectCd ? "项目案号：".concat(projectInfo.projectCd.concat(">>")) : "" }附件列表</div>
		<div class="p10">
			<div id="attachment-datagrid"></div>
		</div>
	</div>
</div>
<script type="text/javascript">
//下载的url
var ess_download_url = '<z:ukey key="public.upload.download"  context="admin"/>';
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.table','zd/jquery.zds.dialog', 'zd/jquery.zds.seleter'], function($, CALLBACK) {
	// TODO 查询url限定项目,限定当前业务Id和线下的资料
	$('#attachment-datagrid').ZTable({
		url:'<z:ukey key='com.zdsoft.finance.projectatta.pageProjectAttaInfos' context='admin'/>&proAtta|project.id|E|S=${projectId}',
		singleSelect:true,
		isRowNum:true,
		rows:10,
		currentPage:1,
		pagination:true,
		tableCls:'table-index',//table的样式
		columns:[[{field:'attaNm', title:'附件名称', align:'left'},
	          {field:'attaTypeNm', title:'附件类型', align:'center'},
	          {field:'operatorNm', title:'上传人', align:'center'},
	          {field:'operatorTime', title:'上传时间', align:'center',formatter:function(r,v){
	        	  return CALLBACK.formatDate(r,v);
	          }},
	          {field:'statusStr', title:'附件状态', align:'center'},
	          {field:'businessKey', title:'附件来源', align:'center',formatter:function(r,v){
	        	  if (v=="" || v == null) {
	        		  return "线下";
	        	  } else {
	        		  return "线上";
	        	  }
	          }},
	          {field:'remark', title:'备注', align:'left'},
	          {field:'id', title:'操作', align:'center',formatter:function(r,v){
	        	  var str = "<a href='javascript:void(0);' class='c-blue superlink' onclick='downloadFile' title='下载'>下载</a>";
	        	   str += "&nbsp;&nbsp;<a href='javascript:void(0);' class='c-blue superlink' onclick='viewFile' title='查看'>查看</a>";
	        	  return str;
	          }}]]
	});
	
	// 附件下载跳转
	CALLBACK.downloadFile = function(index,row){
		window.location.href = ess_download_url + "&attachmentId="+row.attachmentId;
	};
	
	// 附件查看跳转
	CALLBACK.viewFile = function(index,row){
		var FileType = "doc,xls,docx,xlsx,pdf"; 
    	var fileNm = row.attachmentLabel;
    	fileNm = fileNm.substring(fileNm.lastIndexOf('.')+1, fileNm.length).toLowerCase(); 
    	if (FileType.indexOf(fileNm) == -1){
    		$.ZMessage.warning("提示", "目前只支持Word、Excel、PDF的在线查看！", function () {});
			return;
    	}
		alert(fileNm);
		return;
		// TODO
	};
	$.ZUI.init();
});
</script>
</body>
</html>