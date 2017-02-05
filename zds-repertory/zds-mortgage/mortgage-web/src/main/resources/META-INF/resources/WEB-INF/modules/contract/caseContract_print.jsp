<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file='../common/common_js.jsp'%>
<title>合同套打</title>

</head>
<body>
	<div class="page-box">
		<div class="p10">
			<form id="contract_addoredit_form" class="zui-form" method="post"
				enctype="multipart/form-data">
				<div class="page-title">基本信息</div>

				<div class="p5">
					<table class="table-detail">
						<tbody>
							<tr>
								<td class="td-title pct10">案件号</td>
								<td>1501-2015090008</td>
								<td class="td-title pct10">主借人</td>
								<td>李鑫</td>
								<td class="td-title pct10">合同编号</td>
								<td>WMFHTJ150171</td>
							</tr>
							<tr>
								<td class="td-title">垫支金额</td>
								<td>38.00</td>
								<td class="td-title">申请日期</td>
								<td>2015-09-08</td>
								<td class="td-title">机构</td>
								<td>天津</td>
							</tr>
							<tr>
								<td class="td-title">放款期限</td>
								<td>1827天</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td class="td-title">抵押物（房产）</td>
								<td>津南区双港镇梨双公路西北侧普泰花园7-2-1001</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>


						</tbody>
					</table>
				</div>
				<div class="page-title">合同生成</div>
				<div class="p5">
					<div id="tb-plan" class="table-detail">
						<table>
							<tr>
								<td>合同名称</td>
								<td>合同名称</td>
								<td>合同名称</td>

							</tr>
							<tr>
								<td>信托贷款合同
									<button class="btn-blue">预览</button>
								</td>
								<td>信托贷款合同
									<button class="btn-blue">预览</button>
								</td>
								<td>信托贷款合同
									<button class="btn-blue">预览</button>
								</td>
							</tr>
							<tr>
								<td>信托贷款合同
									<button class="btn-blue">预览</button>
								</td>
								<td>信托贷款合同
									<button class="btn-blue">预览</button>
								</td>
								<td>信托贷款合同
									<button class="btn-blue">预览</button>
								</td>
							</tr>
							<tr>
								<td>信托贷款合同
									<button class="btn-blue">预览</button>
								</td>
								<td>信托贷款合同
									<button class="btn-blue">预览</button>
								</td>
								<td>信托贷款合同
									<button class="btn-blue">预览</button>
								</td>
							</tr>
						</table>
					</div>

					<div class="page-title">合同套打</div>
					<div class="p5">
						<div id="tb-plan" class="table-detail">
							<table>
								<tr>
									<td>合同名称</td>
									<td>合同名称</td>
									<td>合同名称</td>

								</tr>
								<tr>
									<td>信托贷款合同
										<button class="btn-blue">预览</button>
									</td>
									<td>信托贷款合同
										<button class="btn-blue">预览</button>
									</td>
									<td>信托贷款合同
										<button class="btn-blue">预览</button>
									</td>
								</tr>
								<tr>
									<td>信托贷款合同
										<button class="btn-blue">预览</button>
									</td>
									<td>信托贷款合同
										<button class="btn-blue">预览</button>
									</td>
									<td>信托贷款合同
										<button class="btn-blue">预览</button>
									</td>
								</tr>
								<tr>
									<td>信托贷款合同
										<button class="btn-blue">预览</button>
									</td>
									<td>信托贷款合同
										<button class="btn-blue">预览</button>
									</td>
									<td>信托贷款合同
										<button class="btn-blue" id="btn-preview">预览</button>
									</td>
								</tr>
							</table>
						</div>
					</div>
			</form>
		</div>
	</div>

	<script type="text/javascript">
		seajs.use([ 'jquery', 'zd/jquery.zds.page.callback',
				'zd/jquery.zds.form', 'zd/jquery.zds.message',
				'zd/jquery.zds.dialog', 'zd/jquery.zds.combobox',
				'zd/jquery.zds.table', 'zd/jquery.zds.seleter' ], function($,
				CALLBACK, ZUI_MESSAGE_CLIENT) {

			$('#btn-preview').click(function() {

				alert("成功");
			});
		});
	</script>
</body>
</html>