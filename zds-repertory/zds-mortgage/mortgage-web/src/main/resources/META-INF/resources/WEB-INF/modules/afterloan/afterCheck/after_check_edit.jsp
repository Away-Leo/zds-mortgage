<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../common/common_js.jsp" %>
<title>贷后检查</title>
</head>
<body>
	<div class="save">
	    <button id="saveAfterCheck" class="btn-blue mr10">保存</button>
	    <button id="back" class="btn-blue mr10">返回</button>
	</div>

	<div  class="frm-content frm-bottom">
		<div class="page-box">
			<div class="p10">
				<div class="page-title">案件信息</div>
					<div class="p5">
						<table class="table-detail"> 
							<tr>
								<td class="td-title pct10">案件号</td>
								<td class="pct20">
									<dl class="form-item form-auto">
										<dd class="detail f12">
											${caseApplyVo.caseApplyCode}
										</dd>
									</dl>
								</td>
								<td class="td-title pct10">主借人</td>
								<td class="pct20">
									<dl class="form-item form-auto">
										<dd class="detail f12">
											${persCustomerVo.customerName}
										</dd>
									</dl>
								</td>
								<td class="td-title pct10">放款金额</td>
								<td class="pct20">
									<dl class="form-item form-auto">
										<dd class="detail  f12">
											<label>
											${caseApplyVo.loanApplyAnount }/元
											</label>
										</dd>
									</dl>
								</td>
							</tr>
							<tr>
								<td class="td-title pct10">放款期限</td>
								<td class="pct20">
									<dl class="form-item form-auto">
										<dd class="detail f12">
										${caseApplyVo.applyTerm }${caseApplyVo.applyTermUnitName }
										</dd>
									</dl>
								</td>
								<td class="td-title pct10"></td>
								<td class="pct20">
								</td>
								<td class="td-title pct10"></td>
								<td class="pct20">
								</td>
							</tr>
						</table>
					</div>
			</div>
		
			<!-- 客户信息》联系方式 -->
			<%@ include file="../../marketing/beforehandapply/client_contact_way_view.jsp"%>
		
			<form id="afterCheck_form" class="zui-form" method="post" enctype="multipart/form-data">
				<input type="hidden" id="caseApplyId" name="caseApplyId" value="${caseApplyVo.id }">
				<div class="page-box">
					<div class="page-title">检查内容</div>
					<div class="p5">
						<table class="table-detail">
							<tr>
								<td class="td-title pct10"><b class="c-red mr5">*</b>贷后检查动作</td>
								<td>
									<dl class="form-item" style="width:700px;">
										<dd class="detail">
											<input class="zui-checkbox zui-validatebox" type="hidden" name="actions"
				                                   data-multiple="false"
				                                   data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0071"
				                                   data-valuefield="fullcode" data-textfield="name" validate-type="Require">
										</dd>
									</dl>
								</td>
							</tr>
							<tr>
								<td class="td-title pct10">具体说明</td>
								<td colspan="5">
		                            <label>
		                           		 <textarea name="noteA" class="zui-area row-width zui-validatebox"  validate-type="Length[0-1500]"
		                                      placeholder="最多可以输入1500个字符"></textarea>
		                            </label>
		                            <div class="zd-area">
					                    <span class="zd-curval">0</span>/<span class="zd-maxval">1500</span>
					                 </div>
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
										<input class="zui-checkbox zui-validatebox" name="customerCondiction" type="hidden"
			                                   data-multiple="false"
			                                   data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0072"
			                                   data-valuefield="fullcode" data-textfield="name">
									</dd>
								</td>
							</tr>
							<tr>
								<td class="td-title pct10">具体说明</td>
								<td colspan="5">
		                            <label>
		                           		 <textarea name="noteB" class="zui-area row-width zui-validatebox" validate-type="Length[0-1500]"
		                                      placeholder="最多可以输入1500个字符"></textarea>
		                            </label>
		                            <div class="zd-area">
					                    <span class="zd-curval">0</span>/<span class="zd-maxval">1500</span>
					                 </div>
								</td>
							</tr>
						</table>
						<table class="table-detail">
							<tr>
								<td class="td-title pct10">执行查询结果</td>
								<td>
									<dl class="form-item">
										<dd class="detail">
											<input class="zui-checkbox zui-validatebox" name="searchResult" type="hidden"
				                                   data-multiple="false"
				                                   data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0073"
				                                   data-valuefield="fullcode" data-textfield="name">
										</dd>
									</dl>
								</td>
							</tr>
							<tr>
								<td class="td-title pct10">具体说明</td>
								<td colspan="5">
		                            <label>
		                           		 <textarea name="noteC" class="zui-area row-width zui-validatebox" validate-type="Length[0-1500]"
		                                      placeholder="最多可以输入1500个字符"></textarea>
		                            </label>
		                            <div class="zd-area">
					                    <span class="zd-curval">0</span>/<span class="zd-maxval">1500</span>
					                 </div>
								</td>
							</tr>
						</table>
						<table class="table-detail">
							<tr>
								<td class="td-title pct10">抵押物情况</td>
								<td>
									<dd class="detail">
										<input class="zui-checkbox zui-validatebox" name="mortCondiction" type="hidden"
			                                   data-multiple="false"
			                                   data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0074"
			                                   data-valuefield="fullcode" data-textfield="name">
									</dd>
								</td>
							</tr>
							<tr>
								<td class="td-title pct10">经营情况</td>
								<td colspan="5">
		                            <label>
		                           		 <textarea name="busiCondiction" class="zui-area row-width zui-validatebox" validate-type="Length[0-1500]"
		                                      placeholder="最多可以输入1500个字符"></textarea>
		                            </label>
		                            <div class="zd-area">
					                    <span class="zd-curval">0</span>/<span class="zd-maxval">1500</span>
					                 </div>
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
		                           		 <textarea name="payBackCheck" class="zui-area row-width zui-validatebox" validate-type="Length[0-1500]"
		                                      placeholder="最多可以输入1500个字符"></textarea>
		                            </label>
		                            <div class="zd-area">
					                    <span class="zd-curval">0</span>/<span class="zd-maxval">1500</span>
					                 </div>
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
										<input class="zui-checkbox zui-validatebox" name="riskAnalysis" type="hidden"
			                                   data-multiple="false"
			                                   data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0075"
			                                   data-valuefield="fullcode" data-textfield="name">
									</dd>
								</td>
							</tr>
							<tr>
								<td class="td-title">风控措施</td>
								<td colspan="5">
		                            <label>
		                           		 <textarea name="riskPrecaution" class="zui-area row-width zui-validatebox"  validate-type="Length[0-1500]" 
		                                      placeholder="最多可以输入1500个字符"></textarea>
		                            </label>
		                            <div class="zd-area">
					                    <span class="zd-curval">0</span>/<span class="zd-maxval">1500</span>
					                 </div>
								</td>
							</tr>
							<tr>
								<td class="td-title pct10" >贷后检查人员姓名</td>
								<td class="pct20">
									<dl class="form-item form-auto">
										<dd class="detail">
											<label> <input type="text" readonly  class="zui-input zui-disabled" name="trackerName" value="${trackerName }"/>
											</label>
										</dd>
									</dl>
								</td>
								<td class="td-title pct15">贷后检查时间</td>
								<td class="pct20">
									<dl class="form-item form-auto">
										<dd class="detail">
											<label> 
												<input class="zui-input  zui-disabled" type="text" id="isCheckedDate" readonly />
												<input type="hidden"  name="checkedDate" value="${currentDate}"/>
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
			</form>
			<div class="page-title">检查信息记录</div>
			<div class="p10">
		        <div id="tb-afterCheckList" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.afterloan.afterCheck.getAfterCheckPage" context="admin"/>&jsoncallback=?&caseApplyId=${caseApplyVo.id }","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","isScroll":true,"toolbar":"#btn-function"}'>
				    <table>
				        <thead>
				        <tr>
				            <th data-options="field:actionsName,width:20%">检查动作</th>
				            <th data-options="field:riskPrecaution,width:35%">风控措施</th>
				            <th data-options="field:trackerName,width:15%">处理人</th>
				            <th data-options="field:checkedDate,width:15%" formatter="formatterCheckedDate">处理时间</th>
				            <th data-options="field:id,width:15%" formatter="formatId">操作</th>
				        </tr>
				        </thead>
				    </table>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		seajs.use(['jquery','zd/tools','zd/jquery.zds.page.callback','zd/jquery.zds.dialog','zd/jquery.zds.form','zd/jquery.zds.table'], 
			function($, ZTOOL,CALLBACK) {
			$.ZUI.init();
			//回显系统时间
			$("#isCheckedDate").val(ZTOOL.strToTime('${currentDate}'));//Long转换为日期字符串201501011010==>2015-01-01 10:10
			 //时间转换
	        CALLBACK.formatterCheckedDate = function(row,value){
				return window.formatDate(row,value);
			};
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
	      	//保存检查信息
			$('#saveAfterCheck').click(function(){
				//检查信息
				var isValidateAfterCheck = $.ZUI.validateForm($('#afterCheck_form'));
				//校验
				if(!isValidateAfterCheck){
					$.ZMessage.info("提示", "请完善必填项信息！", function () {
                    });	 
					return false;
				}
				//获取检查信息
				var params = $('#afterCheck_form').serialize();
				 //保存
				$.ajax({
                       type: 'post',
                       url: '<z:ukey key="com.zdsoft.finance.afterloan.afterCheck.saveAfterCheck" context="admin"/>',
                       data: params,
                       dataType: 'json',
                       success: function (data) {
                           if (data.resultStatus == 0) {
	                           	$.ZMessage.success("成功", data.msg, function () {
	                           		$('#tb-afterCheckList').ZTable("reload", {isValid:true});
	                             });
                           }else{
	                           	$.ZMessage.error("错误", data.msg, function () {
		                        });
                           }
                       },
			           error: function () {
			            	$.ZMessage.error("错误", "保存信息系统异常，请联系管理员", function () {
			             	});
		               }
                });
	    	});
			
		});
	</script>
</body>
</html>