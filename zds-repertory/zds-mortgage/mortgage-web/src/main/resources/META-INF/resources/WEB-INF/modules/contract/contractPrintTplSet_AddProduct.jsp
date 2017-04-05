<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file='../common/common_js.jsp'%>
<title>添加产品</title>

</head>
<body>
	<form id="form1" class="zui-form" method="post"
		enctype="multipart/form-data">

		<div class="power-box" style ="margin-top: 0;">
			<div class="power-side">
				
				<ul id="selectBox" class="power-side-list">
					<li class="selected"
						data-data="[{
					selectData: [
						{field: 's001', name: '项目申请',mark: '项目申请'},
						{field: 's002', name: '客户资料录入',mark: '项目申请'},
						{field: 's003', name: '申请审查',mark: '项目申请'},
						{field: 's004', name: '尽职调查',mark: '项目申请'},
						{field: 's005', name: '调查资料补充',mark: '项目申请'},
						{field: 's006', name: '项目审查',mark: '项目申请'},
						{field: 's007', name: '审查会议',mark: '项目申请'},
						{field: 's008', name: '项目表决',mark: '项目申请'}],
					unSelectData: [
						{field: 'us001', name: '项目变更',mark: '项目申请'},
						{field: 'us002', name: '法务审核',mark: '项目申请'},
						{field: 'us003', name: '风险审核',mark: '项目申请'},
						{field: 'us004', name: '财务部门审核',mark: '项目申请'},
						{field: 'us005', name: '草拟合同',mark: '项目申请'},
						{field: 'us006', name: '合同评审',mark: '项目申请'}]
				}]">
				</ul>
			</div>
			<div id="selectContent" class="power-content">
				<div class="power-buttom">
					<a id="saveAll" class="btn-blue mr10" href="javascript:void(0);">保存</a>
				</div>
			</div>

		</div>


	</form>
	<script type="text/javascript">
		seajs
				.use(
						[ 'jquery', 'zd/bothselecter', 'zd/jquery.zds.form' ],
						function($) {

							$('#selectBox')
									.children('li')
									.click(
											function() {
												$(this)
														.Zbothselecter(
																{
																	valueField : "field",//值字段
																	textField : "name",//文本字段
																	divTarget : $("#selectContent"),
																	/*data: [{
																	 selectData: [
																	 {field: 's001', name: '项目申请'},
																	 {field: 's002', name: '客户资料录入'},
																	 {field: 's003', name: '申请审查'},
																	 {field: 's004', name: '尽职调查'},
																	 {field: 's005', name: '调查资料补充'},
																	 {field: 's006', name: '项目审查'},
																	 {field: 's007', name: '审查会议'},
																	 {field: 's008', name: '项目表决'}],
																	 unSelectData: [
																	 {field: 'us001', name: '项目变更'},
																	 {field: 'us002', name: '法务审核'},
																	 {field: 'us003', name: '风险审核'},
																	 {field: 'us004', name: '财务部门审核'},
																	 {field: 'us005', name: '草拟合同'},
																	 {field: 'us006', name: '合同评审'}],
																	 selectTitle:'我的拥有权限',
																	 unSelectTitle:'我的可用权限'
																	 }],*/
																	title : '角色名称',
																	url : 'http://192.168.33.131:8080/zds-base-data-web/admin/employee/findEmployees?jsoncallback=?',
																	queryParams : {
																		param : 'xxx'
																	}
																});
												$(this)
														.addClass('selected')
														.siblings()
														.removeClass('selected');
											});

							$('#selectBox').find('.selected').trigger('click');
							$('#saveAll').ZButton(
									{
										id : 'saveAll',
										text : "保存",
										buttonCls : 'btn-blue mr10',
										handler : function() {
											//var opts = $('#selectBox').find('.selected').Zbothselecter('getOptions');
											//alert(opts.selectData.length);
											var opts = $('#selectBox')
													.Zbothselecter(
															'getOptions',
															'#selectContent');
											debugger;
											alert(opts.selectData.length);
										}
									})

						});
	</script>

</body>
</html>