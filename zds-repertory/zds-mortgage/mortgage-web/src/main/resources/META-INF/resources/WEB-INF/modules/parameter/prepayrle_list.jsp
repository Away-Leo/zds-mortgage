<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file='../common/common_js.jsp'%>
</head>
<body>
		<!-- BEGIN CONTAINER -->
	<div class="frm-content">
		<div class="page-box">
			<div class="page-title">标签设置</div>
			<div class="p5">
				<form action="#" id="searchForm" class="zui-form form-search" method="post"
					enctype="multipart/form-data">
					<dl class="form-item">
						<dt class="title">编码</dt>
						<dd class="detail">
							<label>
								<input type="text" class="zui-input" name="code|LK|S"
									id="code" />
							</label>
						</dd>
					</dl>
					<dl class="form-item">
						<dt class="title">规则名</dt>
						<dd class="detail">
							<label>
								<input type="text" class="zui-input" name="name|LK|S"
									id="name" />
							</label>
						</dd>
					</dl>
					<div class="form-btn">
	                    <button type="button" class="btn-blue" id="searchButton">查询</button>
	                    <button type="button" class="btn-blue" id="resetButton">重置</button>
	                </div>
				</form>
				<!-- table演示 -->
				<div class="zui-datagrid" id="zd-table"
						zdata-options="{'idField':'id','url':'<z:ukey key="com.zdsoft.finance.parameter.getPrePayRule"  context="admin"/>&jsoncallback=?','singleSelect':true,'isRowNum':false,'pagination':true,'tableCls': 'table-index'}">
					<table >
						<thead>
							<tr>
								<th  data-options="field:name,width:20%">规则名</th>
								<th data-options="field:code,width:20%">编码</th>
								<th data-options="field:remark,width:60%,">提前还款规则</th>

							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	
	
	<script type="text/javascript">


		seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch','zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker','zd/jquery.zds.table', 'zd/jquery.zds.seleter','zd/jquery.zds.combotree','zd/jquery.zds.checkbox']
	            , function ($,CALLBACK, COMBOBOX,  Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
			$.ZUI.init();
			//查询
			$("#searchButton").click(function() {
				/*  var param={};
                 param=$("#name").val();
                 param.status=$("#status").ZCombobox('getValue');
                 param.type=$("#type").ZCombobox('getValue');
                 param.valueMethod=$("#form_valueMethod").ZCombobox('getValue'); */
             	var formArray=$("#searchForm").serialize();
 				formArray = decodeURIComponent(formArray, true);
				$('#zd-table').ZTable("reload", formArray);
			});
			//重置
			$("#resetButton").click(function() {
				$("#name").val("");
				$("#code").val("");
			});

	});
</script>
</body>
</html>