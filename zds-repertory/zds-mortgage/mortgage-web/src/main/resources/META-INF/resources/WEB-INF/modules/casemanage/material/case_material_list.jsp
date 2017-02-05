<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ include file="../../common/common_upload.jsp" %><!-- 上传专用 -->
	<div class="frm-content" id="case_materiallist_page_div">
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
					<dl class="form-btn">
						<button type="button" class="btn-search-blue" id="btn-submit">查询</button>
						<button type="button" class="btn-search-gray" id="btn-reset">重置</button>
					</dl>
				</form>
			</div>
			<div class="p10">
				<div id="case-material-datagrid"></div>
			</div>
		</div>
	</div>
	<%@ include file='attachment_add.jsp'%>
	<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], 
			function($, CALLBACK,ZTOOLS) {
			
			$.ZUI.initDiv("#case_materiallist_page_div");

			var queryClientUrl = '<z:ukey key='com.zdsoft.finance.caseMaterial.queryCaseMaterialProcessList' context='admin'/>';
			$('#case-material-datagrid').ZTable({
				columns:[[{field:'materiaTypeName', title:'所属分类', align:'left'},
				          {field:'materiaName', title:'类别名称', align:'center'},
				          {field:'attachmentName', title:'文件名', align:'center',formatter:function(r,v){
				        	  if(v==null||v=="") {
				        		  return "";
				        	  }
				        	  
				        	  return v;
				          }},
				          {field:'attachmentName', title:'文档名称', align:'left',formatter:function(r,v){
				        	  if(v==null||v=="") {
				        		  return "";
				        	  }
				        	  
				        	  return v;
				          }},
				          {field:'operatorNm', title:'上传人', align:'left',formatter:function(r,v){
				        	  if(v==null||v=="") {
				        		  return "";
				        	  }
				        	  
				        	  return v;
				          }},
				          {field:'operatorTime', title:'上传时间', align:'center',formatter:function(r,v){
				        	  if(v==null||v=="") {
				        		  return "";
				        	  } else {
				        		 return ZTOOLS.strToTime(v);
				        	  }
				          }},
				          {field:'position', title:'定位', align:'center',formatter:function(r,v){
				        	  if(v==null||v=="") {
				        		  return "";
				        	  }
				        	  
				        	  return v;
				          }},
				          {field:'source', title:'来源', align:'center',formatter:function(r,v){
				        	  if(v==null||v=="") {
				        		  return "";
				        	  }
				        	  
				        	  return v;
				          }},
				          {field:'id', title:'操作', align:'center',formatter:function(r,v){		
				        	  //cmlid 资料清单id,id资料清单附件id
				        	  var html = "<a onclick='doEdit'><button class='btn-blue' type='button'>编辑</button></a>";
				        	  html += "&nbsp;&nbsp;<a onclick='doDelete'><button class='btn-blue' type='button'>删除</button></a>";				        	  
				        	  html += "&nbsp;&nbsp;<a onclick='downFile("+r.attachmentId+")'><button class='btn-blue' type='button'>下载</button></a>";				        	  
				        	  return html;
				          }}]],
				url:queryClientUrl,
				singleSelect:false,
				isRowNum:true,
				rows:10,
				currentPage:1,
				pagination:true,
				tableCls:'table-index',
				toolbar:[{
					id:'add',
					text:'添加附件',
					iconCls: 'icon-btn08',
					buttonCls: 'btn-orange',
					handler: function (jq) {
						$("#attachmentId").val("");
						$('#attachmentUploadStatus').html('注：未上传附件');
						$('#uploadAttachment_case_material').Zdialog('open');
					}
				},{
					id:'preview',
					text:'预览图片',
					iconCls: 'icon-btn08',
					buttonCls: 'btn-orange',
					handler: function (jq) {
						var rows = $('#case-material-datagrid').ZTable('getSelections');
						if(rows.length == 0){
							$.ZMessage.error("操作错误", "请选择需要预览的附件", function () {
								
	                        });
						} else {
							//附件ids
							var ids = "";
							for(var i=0;i<rows.length;i++){
								if(i == (rows.length -1)){
									ids += rows[i].id;
								} else {
									ids += rows[i].id+',';
								}
							}
							alert(ids);
						}
					}
				},{
					id:'batchDownload',
					text:'批量下载',
					iconCls: 'icon-btn08',
					buttonCls: 'btn-orange',
					handler: function (jq) {
						var rows = $('#case-material-datagrid').ZTable('getSelections');
						if(rows.length == 0){
							$.ZMessage.error("操作错误", "请选择需要下载的附件", function () {
								
	                        });
						} else {
							//附件ids
							var ids = "";
							for(var i=0;i<rows.length;i++){
								if(i == (rows.length -1)){
									ids += rows[i].id;
								} else {
									ids += rows[i].id+',';
								}
							}
							alert(ids);
						}
					}
				},{
					id:'searchDownloadLog',
					text:'查看下载日志',
					iconCls: 'icon-btn08',
					buttonCls: 'btn-orange',
					handler: function (jq) {
						var editClientUrl = '<z:ukey key="com.zdsoft.finance.caseMaterial.initCaseMaterialAttaLogListPage" context="admin"/>';
			            ZDS_MESSAGE_CLIENT.openMenuLink('查看下载日志','查看下载日志',editClientUrl + "&openMethod=tabs");
					}
				}]
			});
						
			$("#btn-submit").click(function() {
				doSearch();
			});
			
			$("#btn-reset").click(function() {
				$('#where_materiaType').ZCombobox("setValue","");
			});

			function doSearch() {
				var formArray=$("#search_from").serialize();
				formArray = decodeURIComponent(formArray, true);								
				$('#case-material-datagrid').ZTable("reload",formArray);
			};
			
			$.ZUI.init();
		});
	</script>