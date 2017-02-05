<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<%@ include file='../common/common_js.jsp'%>
<%@ include file="../common/common_upload.jsp"%> 
<title>附件管理-附件列表</title>
</head>
<body>
<div class="frm-content">
	<!-- 查询区域 -->
	<div class="page-box">
		<div class="page-title">${not empty projectInfo.clientNm ? "客户名称：".concat(projectInfo.clientNm.concat(">>")) : "" }${not empty projectInfo.projectCd ? "项目案号：".concat(projectInfo.projectCd.concat(">>")) : "" }附件列表</div>
		<div class="p10">
			<form id="search_from" class="zui-form form-search" method="post">
			<!-- TODO 查询表单的所有name调整 -->
				<dl class="form-item">
					<dt class="title">附件名称：</dt>
					<dd class="detail">
						<label> <input class="zui-input"
							 id="attaNm" name="proAtta|attaNm|LK|S">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">附件类型：</dt>
					<dd class="detail">
						 <input class="zui-combobox zui-validatebox" id="attaType" name="proAtta|attaType|E|S" type="hidden"
                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM0008"
                              data-valuefield="fullcode" data-textfield="name" validate-type="Require">
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">上传人：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" id="operatorNm" name="proAtta|operatorNm|LK|S">
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
			<div id="attachment-datagrid"></div>
		</div>
	</div>
</div>
<!-- 编辑项目附件对话框 -->
<jsp:include page="project_attachment_add.jsp"></jsp:include>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.table','zd/jquery.zds.dialog', 'zd/jquery.zds.seleter'], function($, CALLBACK) {
	// TODO 查询url限定项目
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
	          {field:'remark', title:'备注', align:'left'},
	          {field:'id', title:'操作', align:'center',formatter:function(r,v){
	        	  var str = "<a href='javascript:void(0);' class='c-blue superlink' onclick='downloadFile' title='下载'>下载</a>";
	        	   str += "&nbsp;&nbsp;<a href='javascript:void(0);' class='c-blue superlink' onclick='viewFile' title='查看'>查看</a>";
	        	   if (r.status == '00' || ((r.businessKey=="" || r.businessKey==null) && r.status=='01')) {
		        	   str += "&nbsp;&nbsp;<a href='javascript:void(0);' class='c-blue superlink' onclick='deletedFile' title='删除'>删除</a>";
	        	   }
	        	  return str;
	          }}]],
         toolbar:[{
			id:'add',
			text:'新增',
			iconCls: 'icon-blue06',
			buttonCls: 'btn-blue',
			handler: function (jq) {
				$('#uploadAttachment').Zdialog('open');
			}
		}]
	});
	
	//点击查询
	$("#btn-query").click(function() {
		doSearch();
	});
	
	//点击重置
	$("#btn-reset").click(function() {
		$("#search_from input").val("");
	});
	
	ZDS_MESSAGE_CLIENT.refreshThis=function(){
		doSearch();
    };
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
	
	// 项目附件删除
	CALLBACK.deletedFile = function(index,row){
		$.ZMessage.confirm("确认", "是否删除选中的附件?", function (r) {
            if (r == 'recovery') {
               // 删除方法
            	$.ajax({
                    type: 'post',
                    url: '<z:ukey key="com.zdsoft.finance.projectatta.deleteProjectAttaByIds" context="admin"/>',
                    data: {ids:row.id},
                    dataType: 'json',
                    success: function (data) {
                        if (data.resultStatus == 0) {
 	                       	$.ZMessage.success("成功", "删除附件成功！", function () {
 	                       		doSearch();
 	                        });	
                        }else{
                        	$.ZMessage.error("错误", data.msg);
                        }
                    },
                    error: function () {
                    	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {});
                    }
                });
            }
        }, {
            'recovery': {id: 'recovery', text: '确认', buttonCls: 'btn-blue'},
            'cancel': {id: 'cancel', text: '取消', buttonCls: 'btn-gray'}
        });
	};
	$.ZUI.init();
});
//查询数据
function doSearch() {
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message'], function($, CALLBACK) {
		var formArray=$("#search_from").serialize();
		formArray = decodeURIComponent(formArray, true);
		$('#attachment-datagrid').ZTable("reload",formArray);
	});
	
};
</script>
</body>
</html>