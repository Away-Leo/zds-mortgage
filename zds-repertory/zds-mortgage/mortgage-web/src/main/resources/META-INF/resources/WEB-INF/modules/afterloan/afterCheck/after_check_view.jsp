<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../common/common_js.jsp" %>
<title>检查详情</title>
</head>
<body>
<div class="save">
    <button id="back" class="btn-blue mr10">返回</button>
</div>

<div  class="frm-content frm-bottom">
	<form id="afterCheck_form" class="zui-form" method="post" enctype="multipart/form-data">
		<div class="page-box">
			<div class="page-box">
				<div class="page-title">检查内容</div>
				<div class="p5">
					<table class="table-detail">
						<tr>
							<td class="td-title pct10">贷后检查动作</td>
							<td>
								<dd class="detail">
									<input class="zui-checkbox" type="hidden" data-multiple="false"
		                                   data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0071"
		                                   data-valuefield="fullcode" data-textfield="name" data-defaultvalue="${afterCheckVo.actions}" data-choose="disable">
								</dd>
							</td>
						</tr>
						<tr>
							<td class="td-title pct10">具体说明</td>
							<td colspan="5">
	                            <label>
	                           		 <textarea class="zui-area row-width zui-validatebox" readonly>${afterCheckVo.noteA}</textarea>
	                            </label>
							</td>
						</tr>
					</table>
				</div>				
			</div>
			<div class="page-box">
				<div class="page-title">贷后检查结果</div>
				<div class="p5">
					<table class="table-detail">
						<tr>
							<td class="td-title pct10">客户联系情况</td>
							<td>
								<dd class="detail">
									<input class="zui-checkbox" type="hidden" data-multiple="false"
		                                   data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0072"
		                                   data-valuefield="fullcode" data-textfield="name" data-defaultvalue="${afterCheckVo.customerCondiction}" data-choose="disable">
								</dd>
							</td>
						</tr>
						<tr>
							<td class="td-title pct10">具体说明</td>
							<td colspan="5">
	                            <label>
	                           		 <textarea class="zui-area row-width zui-validatebox" readonly>${afterCheckVo.noteB}</textarea>
	                            </label>
							</td>
						</tr>
					</table>
					<table class="table-detail">
						<tr>
							<td class="td-title pct10">执行查询结果</td>
							<td>
								<dd class="detail">
									<input class="zui-checkbox" type="hidden" data-multiple="false"
		                                   data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0073"
		                                   data-valuefield="fullcode" data-textfield="name" data-defaultvalue="${afterCheckVo.searchResult}" data-choose="disable">
								</dd>
							</td>
						</tr>
						<tr>
							<td class="td-title pct10">具体说明</td>
							<td colspan="5">
	                            <label>
	                           		 <textarea class="zui-area row-width zui-validatebox" readonly>${afterCheckVo.noteC}</textarea>
	                            </label>
							</td>
						</tr>
					</table>
					<table class="table-detail">
						<tr>
							<td class="td-title pct10">抵押物情况</td>
							<td>
								<dd class="detail">
									<input class="zui-checkbox" type="hidden" data-multiple="false"
		                                   data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0074"
		                                   data-valuefield="fullcode" data-textfield="name" data-defaultvalue="${afterCheckVo.mortCondiction}" data-choose="disable">
								</dd>
							</td>
						</tr>
						<tr>
							<td class="td-title pct10">经营情况</td>
							<td colspan="5">
	                            <label>
	                           		 <textarea class="zui-area row-width zui-validatebox" readonly>${afterCheckVo.busiCondiction}</textarea>
	                            </label>
							</td>
						</tr>
					</table>
				</div>				
			</div>
			<div class="page-box">
				<div class="page-title">贷后检查结果</div>
				<div class="p5">
					<table class="table-detail">
						<tr>
							<td class="td-title pct10">客户还款能力分析</td>
							<td colspan="5">
	                            <label>
	                           		 <textarea class="zui-area row-width zui-validatebox" readonly>${afterCheckVo.payBackCheck}</textarea>
	                            </label>
							</td>
						</tr>
					</table>
				</div>				
			</div>
			<div class="page-box">
				<div class="page-title">风险分析及相应风控措施</div>
				<div class="p5">
					<table class="table-detail">
						<tr>
							<td class="td-title pct10">风险分析</td>
							<td colspan="5">
								<dd class="detail">
									<input class="zui-checkbox" type="hidden" data-multiple="false"
		                                   data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0075"
		                                   data-valuefield="fullcode" data-textfield="name" data-defaultvalue="${afterCheckVo.riskAnalysis}" data-choose="disable">
								</dd>
							</td>
						</tr>
						<tr>
							<td class="td-title">风控措施</td>
							<td colspan="5">
	                            <label>
	                           		 <textarea class="zui-area row-width zui-validatebox" readonly>${afterCheckVo.riskPrecaution}</textarea>
	                            </label>
							</td>
						</tr>
						<tr>
							<td class="td-title pct10" >贷后检查人员姓名</td>
							<td class="pct20">
								<dl class="form-item form-auto">
									<dd class="detail">
									 	<input class="zui-input  zui-disabled" type="text" value="${afterCheckVo.trackerName }" readonly />
									</dd>
								</dl>
							</td>
							<td class="td-title pct15">贷后检查时间</td>
							<td class="pct20">
								<dl class="form-item form-auto">
									<dd class="detail">
										<label> 
											<input class="zui-input  zui-disabled" type="text" id="isCheckedDate" readonly />
										</label>
									</dd>
								</dl>
							</td>
							<td class="td-title pct10"></td>
							<td class="pct20">
							</td>
						</tr>
					</table>
				</div>				
			</div>
		</div>
	</form>
</div>
<script type="text/javascript">
seajs.use(['jquery','zd/tools','zd/jquery.zds.page.callback','zd/jquery.zds.form'],function($, ZTOOL) {
		//回显系统时间
		$("#isCheckedDate").val(ZTOOL.strToTime('${afterCheckVo.checkedDate}'));//Long转换为日期字符串201501011010==>2015-01-01 10:10
		//返回页面
		$('#back').click(function(){
         	ZDS_MESSAGE_CLIENT.closeSelf();
    	});
		
		$.ZUI.init();
	});
</script>
</body>
</html>