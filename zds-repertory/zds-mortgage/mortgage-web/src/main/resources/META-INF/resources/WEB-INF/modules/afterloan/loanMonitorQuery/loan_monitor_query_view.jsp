<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../common/common_js.jsp" %>
<title>案件号详情</title>
</head>
<body>
	<div class="save">
	    <button id="back" class="btn-blue mr10">返回</button>
	</div>

	<div  class="frm-content frm-bottom">
		<div class="page-box">
			<div class="page-box">
				<div class="page-title">案件信息</div>
				<div class="p5">
					<table class="table-detail"> 
						<tr>
							<td class="td-title pct10">案件号</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail f12">
										<label>
											${caseApplyVo.caseApplyCode}
										</label>
									</dd>
								</dl>
							</td>
							<td class="td-title pct10">机构</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail f12">
										<label>
											${caseApplyVo.mechanismName}
										</label>
									</dd>
								</dl>
							</td>
							<td class="td-title pct10">主借人</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail  f12">
										<label>
											${persCustomerVo.customerName}
										</label>
									</dd>
								</dl>
							</td>
						</tr>
						<tr>
							<td class="td-title pct10">联系方式</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail f12">
									</dd>
								</dl>
							</td>
							<td class="td-title pct10">子产品</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail  f12">
										<label>
											${caseApplyVo.productSubtypeName}
										</label>
									</dd>
								</dl>
							</td>
							<td class="td-title pct10">贷款金额</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail  f12">
										<label>
										</label>
									</dd>
								</dl>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="page-box">
				<div class="page-title">汇法网反馈的预警信息</div>
				<div class="p5">
					<table class="table-detail">
						<tr>
							<td class="td-title pct10">信息类型 </td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
										<label> 
										</label>
									</dd>
								</dl>
							</td>
							<td class="td-title pct10">客户名称</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
										<label> 
										</label>
									</dd>
								</dl>
							</td>
							<td class="td-title pct10">立案时间</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
										<label> 
										</label>
									</dd>
								</dl>
							</td>
						</tr>
						<tr>
							<td class="td-title pct10">执行案号</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
										<label> 
										</label>
									</dd>
								</dl>
							</td>
							<td class="td-title pct10">执行法院</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
										<label> 
										</label>
									</dd>
								</dl>
							</td>
							<td class="td-title pct10">执行标的</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
										<label> 
										</label>
									</dd>
								</dl>
							</td>
						</tr>
						<tr>
							<td class="td-title pct10">案件状态 </td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
										<label> 
										</label>
									</dd>
								</dl>
							</td>
							<td class="td-title pct10"></td>
							<td>
							</td>
							<td class="td-title pct10"></td>
							<td>
							</td>
						</tr>
						<tr>
							<td class="td-title">信息详情</td>
							<td colspan="5">
							</td>
						</tr>
					</table>
				</div>				
			</div>
			<div class="page-box">
				<div class="page-title">工商预警信息</div>
				<div class="p5">
					<table class="table-detail">
						<tr>
							<td class="td-title pct10">统一社会信用代码/注册号 </td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
										<label> 
										</label>
									</dd>
								</dl>
							</td>
							<td class="td-title pct10">名称</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
										<label> 
										</label>
									</dd>
								</dl>
							</td>
							<td class="td-title pct10">类型</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
										<label> 
										</label>
									</dd>
								</dl>
							</td>
						</tr>
						<tr>
							<td class="td-title pct10">法定代表人 </td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
										<label> 
										</label>
									</dd>
								</dl>
							</td>
							<td class="td-title pct10">注册资本</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
										<label> 
										</label>
									</dd>
								</dl>
							</td>
							<td class="td-title pct10">成立日期</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
										<label> 
										</label>
									</dd>
								</dl>
							</td>
						</tr>
						<tr>
							<td class="td-title pct10">地址 </td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
										<label> 
										</label>
									</dd>
								</dl>
							</td>
							<td class="td-title pct10">营业期限自</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
										<label> 
										</label>
									</dd>
								</dl>
							</td>
							<td class="td-title pct10">营业期限至</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
										<label> 
										</label>
									</dd>
								</dl>
							</td>
						</tr>
						<tr>
							<td class="td-title">经营范围</td>
							<td colspan="5">
							</td>
						</tr>
						<tr>
							<td class="td-title pct10" >登记机关</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
									</dd>
								</dl>
							</td>
							<td class="td-title pct10">核准日期</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
										<label> 
										</label>
									</dd>
								</dl>
							</td>
							<td class="td-title pct10">登记状态</td>
							<td>
							</td>
						</tr>
					</table>
				</div>				
			</div>
			<div class="page-title">房产评估信息</div>
			<div class="p10">
		        <div id="tb-afterCheckList" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.afterloan.afterCheck.getAfterCheckPage" context="admin"/>&jsoncallback=?&caseApplyId=${caseApplyVo.id }","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","isScroll":true,"toolbar":"#btn-function"}'>
				    <table>
				        <thead>
				        <tr>
				            <th data-options="field:actionsName,width:30%">评估价格(元)</th>
				            <th data-options="field:riskPrecaution,width:40%">评估时间</th>
				            <th data-options="field:id,width:30%" formatter="formatId">操作</th>
				        </tr>
				        </thead>
				    </table>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		seajs.use(['jquery','zd/tools','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.table'], 
			function($, ZTOOL,CALLBACK) {
			//返回页面
			$('#back').click(function(){
	         	ZDS_MESSAGE_CLIENT.closeSelf();
	    	});
			//操作格式化
	        CALLBACK.formatId=function(rowData,index){
	        	var data='<a href="javaScript:void(0)" onclick="viewAfterCheck"><button class="btn-blue">详情</button></a>';
	        	return data;
	        }
	      	//详情
	        CALLBACK.viewAfterCheck=function(index,row){
	        	ZDS_MESSAGE_CLIENT.openMenuLink('viewAfterCheck'+index, '详情', '<z:ukey key="com.zdsoft.finance.afterloan.afterCheck.viewAfterCheck" context="admin"/>&id='+row.id);
		    }
			
			$.ZUI.init();
			
		});
	</script>
</body>
</html>