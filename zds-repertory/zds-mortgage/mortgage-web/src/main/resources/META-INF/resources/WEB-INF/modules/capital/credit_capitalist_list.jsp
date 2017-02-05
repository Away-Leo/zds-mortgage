
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<%@ include file='../common/common_js.jsp'%>
<title>资方列表</title>
</head>
<body>
	<div class="frm-content">
		<!-- 查询区域 -->
		<div class="page-box">
			<div class="page-title">资方管理</div>
			<div class="p10">
				<form id="search_from" class="zui-form form-search" method="post"
					enctype="multipart/form-data">
					<dl class="form-item">
						<dt class="title">资方名称：</dt>
						<dd class="detail">
							<label> <input class="zui-input" id="cooperatorName" name="capitalistName|LK|S">
							</label>
						</dd>
					</dl>
					<dl class="form-item">
						<dt class="title">资方类别：</dt>
						<dd class="detail">
							<input class="zui-combobox zui-validatebox" id="capitalistType" name="capitalistType|E|S" type="hidden"
                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=zflx"
                              data-valuefield="fullcode" data-callback="reloadMeetingProject" data-textfield="name" validate-type="Require">
						</dd>
					</dl>
					<dl class="form-btn">
						<button type="button" class="btn-search-blue" id="btn-search">查询</button>
						<button type="button" class="btn-search-gray" id="btn-reset">重置</button>
					</dl>
				</form>
			</div>
		</div>
		<div class="page-box">
			<div class="p10">
				<div id="capitalist_datagrid" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.cooperator.capitalist.getCapitalist" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index"}'>
					<table>
						<thead>
		        			<tr>
		            			<th data-options="field:cooperatorName">资方名称</th>
		            			<th data-options="field:capitalistTypeName">资方类型</th>
		            			<th data-options="field:id" formatter="operate">操作</th>
					        </tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div id="contactsDialog" style="display: none">
	</div>
	<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
			//操作
			CALLBACK.operate = function(row,value){
				if(row.capitalistType == 'trust'){
					var html =  "<a title='资金计划' class='btn-blue ' buttonCls='btn-blue' onclick='initCreditEntrust'>资金计划</a>";
					return html;	
				}else{
					return '无';
				}
				
			};
		
			// 初始化
			$.ZUI.init();
			
			// 初始化资金计划
			CALLBACK.initCreditEntrust = function(index,data){
				ZDS_MESSAGE_CLIENT.openMenuLink('credit_trust_list', '资金计划', '<z:ukey key="com.zdsoft.finance.capital.initCreditEntrust" context="admin"/>&capitalistId='+data.id);
			};
			
			// 查询按钮点击
			$('#btn-search').click(function(){
				doSearch();
			});
			// 重置按钮点击
			$('#btn-reset').click(function(){
				$('#capitalistType').val('');
				$('#cooperatorName').val('');
			});
			
			// 执行查询
			function doSearch(){
				var formArray=$("#search_from").serialize();
				formArray = decodeURIComponent(formArray, true);
				$('#capitalist_datagrid').ZTable("reload",formArray);
			}
			
			// 父页面刷新自己
			ZDS_MESSAGE_CLIENT.refreshThis=function(){
				doSearch();
	        };
	        
		});
	</script>
</body>
</html>