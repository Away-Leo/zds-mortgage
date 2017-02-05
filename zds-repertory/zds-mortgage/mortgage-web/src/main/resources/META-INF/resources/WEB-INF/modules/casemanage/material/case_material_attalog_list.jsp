<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<%@ include file='../../common/common_js.jsp'%>
<title>案件资料清单下载日志列表</title>
</head>
<body id="body">
	<div class="frm-content" id="personal_client_basic_edit_div">
		<!-- 查询区域 -->
		<div class="page-box">
			<div class="page-title">查询条件</div>
			<div class="p10">
				<form id="search_from" class="zui-form form-search" method="post" enctype="multipart/form-data">
					<input type="hidden" name="tag" value="tag"> 				
	                <dl class="form-item">
						<dt class="title">所属分类：</dt>
						<dd class="detail">
							<input class="zui-combobox zui-validatebox" id="where_materiaType" name="cml|materiaTypeName|E|S" type="hidden"
                               <%-- data-url="<z:ukey key='com.zdsoft.finance.crm.client.getClientStatus' context='admin'/>&jsoncallback=?" --%>
                               data-data="[{'fullcode':'个人信息类','name':'个人信息类'},{'fullcode':'合同类','name':'合同类'}]"
                               data-valuefield="fullcode" data-textfield="name">                               
						</dd>
					</dl>
					<dl class="form-item">
						<dt class="title">文件名：</dt>
						<dd class="detail">
							<label> <input class="zui-input" id="where_attachmentName" name="cmla|attachmentName|LK|S">
							</label>
						</dd>
					</dl>
					<dl class="form-item">
						<dt class="title">下载人：</dt>
						<dd class="detail">
							<label> <input class="zui-input" id="where_downOper" name="cmlal|downOper|LK|S">
							</label>
						</dd>
					</dl>
					<dl class="form-btn">
						<button type="button" class="btn-search-blue" id="btn-submit">查询</button>
						<button type="button" class="btn-search-gray" id="btn-reset">重置</button>
					</dl>
				</form>
			</div>
			<div class="p10">
				<div id="case-material-attalog-datagrid"></div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.combobox','zd/jquery.zds.table'], function($, CALLBACK) {

			$('#case-material-attalog-datagrid').ZTable({
				columns:[[{field:'materiaTypeName', title:'所属分类', align:'left'},
				          {field:'cmlid', title:'类别名称', align:'center'},
				          {field:'attachmentName', title:'文件名', align:'center'},
				          {field:'attachmentName', title:'文档名称', align:'left'},
				          {field:'downOper', title:'下载人', align:'left',formatter:function(r,v){
				        	  if(v==null) {
				        		  return "";
				        	  }
				        	  return v;
				          }},
				          {field:'downCount', title:'下载次数', align:'center',formatter:function(r,v){
				        	  if(v==null) {
				        		  return "";
				        	  }
				        	  return v;
				          }},
				          {field:'downLastTime', title:'最后下载时间', align:'center',formatter:function(r,v){
				        	  if(v==null) {
				        		  return "";
				        	  }
				        	  return v;
				          }},
				          {field:'source', title:'来源', align:'center'}
				       ]],
				url:'<z:ukey key='com.zdsoft.finance.caseMaterial.queryCaseMaterialAttaLogList' context='admin'/>',
				singleSelect:false,
				isRowNum:true,
				rows:10,
				currentPage:1,
				pagination:true,
				tableCls:'table-index'
			});
						
			$("#btn-submit").click(function() {
				doSearch();
			});
			
			$("#btn-reset").click(function() {
				$('#where_materiaType').ZCombobox("setValue","");
				$('#where_attachmentName').val("");
				$('#where_downOper').val("");
			});

			function doSearch() {
				var formArray=$("#search_from").serialize();
				formArray = decodeURIComponent(formArray, true);								
				$('#case-material-attalog-datagrid').ZTable("reload",formArray);
			};
			
			$.ZUI.init();
		});
	</script>
</body>
</html>	