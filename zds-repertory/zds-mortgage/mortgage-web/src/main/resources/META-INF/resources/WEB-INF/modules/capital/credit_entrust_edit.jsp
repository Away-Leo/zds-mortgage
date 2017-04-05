 <!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<%@ include file='../common/common_js.jsp'%>
<title>信托计划详情</title>
</head>
<body>
<div class="page-box">
	<div class="p10">
		<form id="credit_entrust_form" class="zui-form " method="post"
			enctype="multipart/form-data">
			<input type="hidden" id="id" name="id" value="${creditEntrustVo.id }"/>
			<div class="page-box">
				<div class="page-title">基本情况</div>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>资方：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-disabled zui-validatebox" readonly="readonly" validate-type="Require"
							 id="cooperatorName" value="${creditEntrustVo.cooperatorName }">
							 <input type="hidden" id="capitalistId" name="capitalistId" value="${creditEntrustVo.capitalistId }"/>
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>成立时间：</dt>
					<dd class="detail">
						<label>
							 <input type="text" id="establishmentDateLocal" value="${creditEntrustVo.establishmentDate }" class="zui-input  zui-validatebox" validate-type="Require" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'establishmentDate',maxDate:'${maxDate}'})">
                             <input type="hidden" name="establishmentDate" value="${creditEntrustVo.establishmentDate }"  id="establishmentDate" />
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>是否主计划：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-combobox zui-validatebox" id="isMasterPlan" name="isMasterPlan" type="hidden" 
                           data-data="[{'id':'true','text':'是'},{'id':'false','text':'否'}]"
                              data-valuefield="id" data-textfield="text" value="${creditEntrustVo.isMasterPlan }" validate-type="Require">
						</label>
					</dd>
				</dl>
				
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>信托类型：</dt>
					<dd class="detail">
						<input class="zui-combobox zui-validatebox" id="trustType" name="trustType" type="hidden"
                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00134"
                              data-valuefield="fullcode"  data-textfield="name" value="${creditEntrustVo.trustType }" validate-type="Require">
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>计划名称：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Length[1-64]"
							 id="creditEntrustName" value="${creditEntrustVo.creditEntrustName }" name="creditEntrustName">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>本金规模(元)：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-disabled zui-validatebox" readonly="readonly" validate-type="Require,Length[1-16]"
							 id="principaScale" value="${creditEntrustVo.principaScale }"  name="principaScale">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>转让状态：</dt>
					<dd class="detail">
						<input class="zui-combobox zui-validatebox" data-width="94" id="assignmentState" name="assignmentState" type="hidden"
                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00135"
                              data-valuefield="fullcode" value="${creditEntrustVo.assignmentState }" data-textfield="name" validate-type="Require" data-callback="checkValue">
					</dd>
					<dd class="detail" id="transferPlanIdDd" style="display:none">
                        <input class="zui-combobox" id="transferPlanId" data-width="94" name="transferPlanId" type="hidden"
                              data-url="<z:ukey key="com.zdsoft.finance.capital.getCreditEntrustListByCapitalistId" context="admin"/>&jsoncallback=?&capitalistId=${creditEntrustVo.capitalistId}"
                              data-valuefield="id" value="${creditEntrustVo.transferPlanId }" data-textfield="creditEntrustName" >
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>转让规模(元)：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Length[1-32]"
							 id="assignmentScale" value="${creditEntrustVo.assignmentScale }" name="assignmentScale">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>最低限额(元)：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Digital[18-2]"
							 id="minQuota" value="${creditEntrustVo.minQuota }" name="minQuota">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>服务费率(%)：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Digital[18-4]" validate-false="|请输入正确的利率"
							 id="severRate" value="${creditEntrustVo.severRate }" name="severRate">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>保管费率(%)：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Digital[18-4]" validate-false="|请输入正确的利率"
							 id="keepRate" value="${creditEntrustVo.keepRate }" name="keepRate">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>管理费率(%)：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Digital[18-4]" validate-false="|请输入正确的利率"
							 id="managerRate" value="${creditEntrustVo.managerRate }" name="managerRate">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>待拨户开户行：</dt>
					<dd class="detail" id="ddwaitApprBank">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Length[1-64]"
							 id="waitApprBank" value="${creditEntrustVo.waitApprBank }" name="waitApprBank">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>待拨户账户名：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Length[1-32]"
							 id="waitApprName" value="${creditEntrustVo.waitApprName }" name="waitApprName">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>待拨户账号：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Number,Length[1-32]"
							 id="waitApprNo" value="${creditEntrustVo.waitApprNo }" name="waitApprNo">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>归集户开户行：</dt>
					<dd class="detail" id="ddcollectionAccountBank">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Length[1-64]"
							 id="collectionAccountBank" value="${creditEntrustVo.collectionAccountBank }" name="collectionAccountBank">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>归集户账户名：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Length[1-32]"
							 id="collectionAccountName" value="${creditEntrustVo.collectionAccountName }" name="collectionAccountName">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>归集户账号：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Number,Length[1-32]"
							 id="collectionAccountNo" value="${creditEntrustVo.collectionAccountNo }" name="collectionAccountNo">
						</label>
					</dd>
				</dl>	
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>专户开户行：</dt>
					<dd class="detail" id="ddspecialAccountBank">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Length[1-64]"
							 id="specialAccountBank" value="${creditEntrustVo.specialAccountBank }" name="specialAccountBank">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>专户账户名：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Length[1-32]"
							 id="specialAccountName" value="${creditEntrustVo.specialAccountName }" name="specialAccountName">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>专户账号：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Number,Length[1-32]"
							 id="specialAccountNo" value="${creditEntrustVo.specialAccountNo }" name="specialAccountNo">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>备付户开户行：</dt>
					<dd class="detail" id="ddspareAccountBank">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Length[1-64]"
							 id="spareAccountBank" value="${creditEntrustVo.spareAccountBank }" name="spareAccountBank">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>备付户账户名：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Length[1-32]"
							 id="spareAccountName" value="${creditEntrustVo.spareAccountName }" name="spareAccountName">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>备付户账号：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Number,Length[1-32]"
							 id="spareAccountNo" value="${creditEntrustVo.spareAccountNo }" name="spareAccountNo">
						</label>
					</dd>
				</dl>
				
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>清分账号：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Number,Length[1-32]"
							 id="clearingAccount" name="clearingAccount" value="${creditEntrustVo.clearingAccount }" />
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>商户ID：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Length[1-32]"
							 id="merchantID" name="merchantID" value="${creditEntrustVo.merchantID }" />
						</label>
					</dd>
				</dl>
				<dl class="form-item block">
                	<dt class="title">备注：</dt>
	                <dd class="detail">
		                <label>
		                	<textarea class="zui-area zui-validatebox" id="remark" name="remark" validate-type="Length[0-500]" placeholder="最多可以输入500个字符">${creditEntrustVo.remark }</textarea>
		                </label>
	                </dd>
                </dl>							
			</div>
			<div class="page-title">资金计划操作</div>
			<div class="form-item">
				<dl class="form-item">
					<dt class="title sptitle"><b class="c-red mr5">*</b>是否开放额度申请：</dt>
					<dd class="detail">
						<input class="zui-combobox zui-validatebox" id="isOpenApply" name="isOpenApply" type="hidden"
                              data-data="[{'id':'true','text':'是'},{'id':'false','text':'否'}]"
                              data-valuefield="id" value="${creditEntrustVo.isOpenApply }"  data-textfield="text" validate-type="Require">
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title sptitle"><b class="c-red mr5">*</b>是否资金自动匹配：</dt>
					<dd class="detail">
						<input class="zui-combobox zui-validatebox" id="isAutoMatch" name="isAutoMatch" type="hidden"
                         	 data-data="[{'id':'true','text':'是'},{'id':'false','text':'否'}]"
                             data-valuefield="id" data-textfield="text" value="${creditEntrustVo.isAutoMatch }" validate-type="Require">
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">截留额度(元)：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Digital[18-12]"
							 id="retain" value="${creditEntrustVo.retain }" onblur="countValue();" name="retain">
						</label>
					</dd>
				</dl>
			</div>
			<div class="form-btn">
				<button type="button" id="oneKeyFundMatching" class="btn-blue">一键资金匹配</button>
			</div>

			<div class="page-box">
				<div class="page-title">出资信息</div>
				<div class="p10">
					<div id="cost_datagrid" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.capital.investmentList' context='admin'/>&jsoncallback=?&creditEntrust.id|E|S=${creditEntrustVo.id }","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index"}'>
						<table>
							<thead>
			        			<tr>
			            			<th data-options="field:contribution">出资方名称</th>
			            			<th data-options="field:initialPrincipal">初始本金</th>
			            			<th data-options="field:principalAmount">追加本金</th>
			            			<th data-options="field:id" formatter="sumPrincipalTotal">合计金额</th>
						        </tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
			<div class="page-box">
				<div class="page-title">信托专户贷方资金（非本金）跟踪 </div>
				<div class="p10">
					<div id="fundTrackingBar">
			            <a class="zui-toolbar" id="btn-fundTrackingAdd" iconCls="icon-add" text="新增" buttonCls="btn-orange"
			               handler="fundTrackingAdd"></a>
		        	</div>
					<div id="fundTracking_datagrid" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.capital.loanCapitalList' context='admin'/>&jsoncallback=?&creditEntrust.id|E|S=${creditEntrustVo.id }","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#fundTrackingBar"}'>
						<table>
							<thead>
			        			<tr>
			            			<th data-options="field:lenderTypeName">贷方类型</th>
			            			<th data-options="field:lenderName">名称</th>
			            			<th data-options="field:totalAmount">发生总金额</th>
			            			<th data-options="field:happenDate" formatter="changeDate">实际日期</th>
			            			<th data-options="field:completeEmpName">提交人</th>
			            			<th data-options="field:completeDate" formatter="changeDate">提交日期</th>
			            			<th data-options="field:statusName">处理状态</th>
			            			<th data-options="field:capitalStateName">状态</th>
			            			<th data-options="field:remark,width:10%">备注</th>
			            			<th data-options="field:id" formatter="fundTracking">操作</th>
						        </tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
			<div class="page-box">
				<div class="page-title">应付费用跟踪</div>
				<div class="p10">
					<div id="trackingBar">
			            <a class="zui-toolbar" id="btn-trackingAdd" iconCls="icon-add" text="新增" buttonCls="btn-orange"
			               handler="trackingAdd"></a>
		        	</div>
					<div id="tracking_datagrid" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.capital.costTrackingList' context='admin'/>&jsoncallback=?&creditEntrust.id|E|S=${creditEntrustVo.id }","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#trackingBar"}'>
						<table>
							<thead>
			        			<tr>
			            			<th data-options="field:expenditureTypeName">对象类型</th>
			            			<th data-options="field:costName">名称</th>
			            			<th data-options="field:totalAmount">应付总金额</th>
			            			<th data-options="field:payDate" formatter="changeDate">应付日期</th>
			            			<th data-options="field:completeEmpName">提交人</th>
			            			<th data-options="field:completeDate" formatter="changeDate">提交日期</th>
			            			<th data-options="field:statusName">状态</th>
			            			<th data-options="field:remark,width:10%">备注</th>
			            			<th data-options="field:id" formatter="formatTracking">操作</th>
						        </tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
			<div class="page-box">
				<div class="page-title">借方资金（非放款）跟踪</div>
				<div class="p10">
					<div id="feeBar">
			            <a class="zui-toolbar" id="btn-feeTracking" iconCls="icon-add" text="新增" buttonCls="btn-orange"
			               handler="feeTrackingAdd"></a>
		        	</div>
					<div id="feeBar_datagrid" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.capital.debitTrackingList' context='admin'/>&jsoncallback=?&creditEntrust.id|E|S=${creditEntrustVo.id }","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#feeBar"}'>
						<table>
							<thead>
			        			<tr>
			            			<th data-options="field:debitTypeName">支出类型</th>
			            			<th data-options="field:objectName">名称</th>
			            			<th data-options="field:totalAmount">发生总金额</th>
		            				<th data-options="field:actualOutDate" formatter="changeDate">实际日期</th>
		            				<th data-options="field:completeEmpName">提交人</th>
			            			<th data-options="field:completeDate" formatter="changeDate">提交日期</th>
			            			<th data-options="field:statusName">处理状态</th>
			            			<th data-options="field:capitalStateName">状态</th>
			            			<th data-options="field:remark,width:10%">备注</th>
			            			<th data-options="field:id" formatter="formatFeeTracking">操作</th>
						        </tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
			<div class="page-box">
				<div class="page-title">备付资金跟踪</div>
				<div class="p10">
					<div id="standbyTrackingBar">
			            <a class="zui-toolbar" id="btn-standbyTracking" iconCls="icon-add" text="新增" buttonCls="btn-orange"
			               handler="standbyTrackingAdd"></a>
		        	</div>
					<div id="standbyTracking_datagrid" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.capital.spareCapitalList' context='admin'/>&jsoncallback=?&creditEntrust.id|E|S=${creditEntrustVo.id }","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#standbyTrackingBar"}'>
						<table>
							<thead>
			        			<tr>
			            			<th data-options="field:operationTypeName">操作类型</th>
			            			<th data-options="field:applyAmount">申请金额</th>
			            			<th data-options="field:actualAmount">到账金额</th>
			            			<th data-options="field:distributionAmount">未分配到账金额</th>
			            			<th data-options="field:completeDate" formatter="changeDate">提交日期</th>
			            			<th data-options="field:statusName">状态</th>
			            			<th data-options="field:id" formatter="standbyTracking">操作</th>
						        </tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
			<div class="page-box">
				<div class="page-title">信托计划转让</div>
				<div class="p10">
					<div id="assignmentBar">
			            <a class="zui-toolbar" id="btn-assignment" iconCls="icon-add" text="新增" buttonCls="btn-orange"
			               handler="assignmentAdd"></a>
		        	</div>
					<div id="assignment_datagrid" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.capital.attomCapitalList' context='admin'/>&jsoncallback=?&creditEntrust.id|E|S=${creditEntrustVo.id }","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#assignmentBar"}'>
						<table>
							<thead>
			        			<tr>
			            			<th data-options="field:acceptTypeName">类型</th>
			            			<th data-options="field:acceptName">受让方名称</th>
			            			<th data-options="field:acceptAmount">受让金额</th>
			            			<th data-options="field:contractProfitRate">合同收益率%</th>
			            			<th data-options="field:mobile">移动电话</th>
			            			<th data-options="field:attomEffect" formatter="changeDate">转让日期</th>
			            			<th data-options="field:attomEndDate" formatter="changeDate">截止日期</th>
			            			<th data-options="field:attomStateName">转让状态</th>
			            			<th data-options="field:id" formatter="formatAssignment">操作</th>
						        </tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
			<div class="page-box">
				<div class="page-title">本金投入</div>
				<div class="p10">
					<div id="investmentBar">
			            <a class="zui-toolbar" id="btn-investment" iconCls="icon-add" text="新增" buttonCls="btn-orange"
			               handler="investmentAdd"></a>
		        	</div>
					<div id="investment_datagrid" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.capital.principalInputList' context='admin'/>&jsoncallback=?&creditEntrust.id|E|S=${creditEntrustVo.id }","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#investmentBar","onLoadSuccess":"loadPrincipaScale"}'>
						<table>
							<thead>
			        			<tr>
			            			<th data-options="field:contribution">出资方名称</th>
			            			<th data-options="field:contributionTypeName">类型</th>
			            			<th data-options="field:principalAmount">本金金额</th>
			            			<th data-options="field:usedQuota">使用额度</th>
			            			<th data-options="field:useProp">信托保障基金比率%</th>
			            			<th data-options="field:profitRate">合同收益率%</th>
			            			<th data-options="field:addDate" formatter="changeDate">追加日期</th>
			            			<th data-options="field:expectDate" formatter="changeDate">预计到账日期</th>
			            			<th data-options="field:actualDate" formatter="changeDate">到账日期</th>
			            			<th data-options="field:statusName">状态</th>
			            			<th data-options="field:remark,width:10%">备注</th>
			            			<th data-options="field:id" formatter="investmentFormat">操作</th>
						        </tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
			<div class="page-box">
				<div class="page-title">本金赎回</div>
				<div class="p10">
					<div id="redemPrincipalBar">
			            <a class="zui-toolbar" id="btn-redemPrincipal" iconCls="icon-add" text="新增" buttonCls="btn-orange"
			               handler="redemPrincipalAdd"></a>
		        	</div>
					<div id="redemPrincipal_datagrid" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.capital.redemePrinciList' context='admin'/>&jsoncallback=?&creditEntrust.id|E|S=${creditEntrustVo.id }","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#redemPrincipalBar","onLoadSuccess":"loadPrincipaScale"}'>
						<table>
							<thead>
			        			<tr>
			            			<th data-options="field:contribution">出资方名称</th>
			            			<th data-options="field:contributionTypeName">类型</th>
			            			<th data-options="field:redemptionAmount">赎回金额</th>
			            			<th data-options="field:statusName">状态</th>
			            			<th data-options="field:remark,width:10%">备注</th>
			            			<th data-options="field:id" formatter="redemPrincipalFormat">操作</th>
						        </tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>
<div class="save">
   	<button id="cancelBtn" type="button" class="btn-gray">取消</button>
   	<button id="saveBtn" type="button" class="btn-blue">保存</button>
</div>
<div id="zds_btn_selecter"></div>
<div id="chooseMember"></div>
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter', 'zd/completer'], function($, CALLBACK, TOOL) {
		
		// 回调点击事件初始化
		CALLBACK.fundTrackingAdd = function(index,rowData){
			var tempUuid = $('#tempUuid').val();
			var id = '';
			if(rowData){
				id = rowData.id;
			}
			var creditEntrustId = $('#id').val();
			var addFundTrackingUrl = '<z:ukey key="com.zdsoft.finance.capital.initNonPrincipalTracking" context="admin"/>&jsoncallback=?&tempUuid='+tempUuid+'&id='+id+'&creditEntrustId='+creditEntrustId;
            ZDS_MESSAGE_CLIENT.openMenuLink('fundTrakcingId','专户贷方资金（非本金）跟踪',addFundTrackingUrl + "&openMethod=tabs");
		};
		CALLBACK.trackingAdd = function(index,rowData){
			var tempUuid = $('#tempUuid').val();
			var id = '';
			if(rowData){
				id = rowData.id;
			}
			var creditEntrustId = $('#id').val();
			var trackingAddUrl = '<z:ukey key="com.zdsoft.finance.capital.initCostTracking" context="admin"/>&jsoncallback=?&tempUuid='+tempUuid+'&id='+id+'&creditEntrustId='+creditEntrustId;
            ZDS_MESSAGE_CLIENT.openMenuLink('trackingId','信托计划应付费用跟踪',trackingAddUrl + "&openMethod=tabs");
		};
		CALLBACK.feeTrackingAdd = function(index,rowData){
			var tempUuid = $('#tempUuid').val();
			var id = '';
			if(rowData){
				id = rowData.id;
			}
			var creditEntrustId = $('#id').val();
			var feeTrackingAddUrl = '<z:ukey key="com.zdsoft.finance.capital.initTrackingTrustPlan" context="admin"/>&jsoncallback=?&tempUuid='+tempUuid+'&id='+id+'&creditEntrustId='+creditEntrustId;
            ZDS_MESSAGE_CLIENT.openMenuLink('feeTrackingId','信托计划借方资金（非放款）跟踪',feeTrackingAddUrl + "&openMethod=tabs");
		};
		CALLBACK.standbyTrackingAdd = function(index,rowData){
			var tempUuid = $('#tempUuid').val();
			var id = '';
			if(rowData){
				id = rowData.id;
			}
			var creditEntrustId = $('#id').val();
			var standbyTrackingAddUrl = '<z:ukey key="com.zdsoft.finance.capital.initReserveFundTracking" context="admin"/>&jsoncallback=?&tempUuid='+tempUuid+'&id='+id+'&creditEntrustId='+creditEntrustId;
            ZDS_MESSAGE_CLIENT.openMenuLink('standbyTrackingId','备付资金跟踪',standbyTrackingAddUrl + "&openMethod=tabs");
		};
		CALLBACK.assignmentAdd = function(index,rowData){
			var tempUuid = $('#tempUuid').val();
			var id = '';
			if(rowData){
				id = rowData.id;
			}
			var creditEntrustId = $('#id').val();
			var assignmentAddUrl = '<z:ukey key="com.zdsoft.finance.capital.initTransferInformation" context="admin"/>&jsoncallback=?&tempUuid='+tempUuid+'&id='+id+'&creditEntrustId='+creditEntrustId;
            ZDS_MESSAGE_CLIENT.openMenuLink('assignmentId','信托计划转让信息',assignmentAddUrl + "&openMethod=tabs");
		};
		CALLBACK.investmentAdd = function(index,rowData){
			var tempUuid = $('#tempUuid').val();
			var id = '';
			if(rowData){
				id = rowData.id;
			}
			var creditEntrustId = $('#id').val();
			var investmentAddUrl = '<z:ukey key="com.zdsoft.finance.capital.initPrincipalInvestment" context="admin"/>&jsoncallback=?&tempUuid='+tempUuid+'&id='+id+'&creditEntrustId='+creditEntrustId;
            ZDS_MESSAGE_CLIENT.openMenuLink('investmentId','信托计划本金投入',investmentAddUrl + "&openMethod=tabs");
		};
		
		
		CALLBACK.redemPrincipalAdd = function(index,rowData){
			var tempUuid = $('#tempUuid').val();
			var id = '';
			if(rowData){
				id = rowData.id;
			}
			var creditEntrustId = $('#id').val();
			var initRedemePrinciUrl = '<z:ukey key="com.zdsoft.finance.capital.initRedemePrinci" context="admin"/>&jsoncallback=?&tempUuid='+tempUuid+'&id='+id+'&creditEntrustId='+creditEntrustId;
            ZDS_MESSAGE_CLIENT.openMenuLink('redemePrinciId2','本金赎回编辑',initRedemePrinciUrl + "&openMethod=tabs");
		};
		
		// 编辑事件Menulink
		CALLBACK.fundTrackingAdd2 = function(index,rowData){
			var tempUuid = $('#tempUuid').val();
			var id = '';
			if(rowData){
				id = rowData.id;
			}
			var creditEntrustId = $('#id').val();
			var addFundTrackingUrl = '<z:ukey key="com.zdsoft.finance.capital.initNonPrincipalTracking" context="admin"/>&jsoncallback=?&tempUuid='+tempUuid+'&id='+id+'&creditEntrustId='+creditEntrustId;
            ZDS_MESSAGE_CLIENT.openMenuLink('fundTrakcingId2','专户贷方资金（非本金）跟踪编辑',addFundTrackingUrl + "&openMethod=tabs");
		};
		CALLBACK.trackingAdd2 = function(index,rowData){
			var tempUuid = $('#tempUuid').val();
			var id = '';
			if(rowData){
				id = rowData.id;
			}
			var creditEntrustId = $('#id').val();
			var trackingAddUrl = '<z:ukey key="com.zdsoft.finance.capital.initCostTracking" context="admin"/>&jsoncallback=?&tempUuid='+tempUuid+'&id='+id+'&creditEntrustId='+creditEntrustId;
            ZDS_MESSAGE_CLIENT.openMenuLink('trackingId2','信托计划应付费用跟踪编辑',trackingAddUrl + "&openMethod=tabs");
		};
		CALLBACK.feeTrackingAdd2 = function(index,rowData){
			var tempUuid = $('#tempUuid').val();
			var id = '';
			if(rowData){
				id = rowData.id;
			}
			var creditEntrustId = $('#id').val();
			var feeTrackingAddUrl = '<z:ukey key="com.zdsoft.finance.capital.initTrackingTrustPlan" context="admin"/>&jsoncallback=?&tempUuid='+tempUuid+'&id='+id+'&creditEntrustId='+creditEntrustId;
            ZDS_MESSAGE_CLIENT.openMenuLink('feeTrackingId2','信托计划借方资金（非放款）跟踪编辑',feeTrackingAddUrl + "&openMethod=tabs");
		};
		CALLBACK.standbyTrackingAdd2 = function(index,rowData){
			var tempUuid = $('#tempUuid').val();
			var id = '';
			if(rowData){
				id = rowData.id;
			}
			var creditEntrustId = $('#id').val();
			var standbyTrackingAddUrl = '<z:ukey key="com.zdsoft.finance.capital.initReserveFundTracking" context="admin"/>&jsoncallback=?&tempUuid='+tempUuid+'&id='+id+'&creditEntrustId='+creditEntrustId;
            ZDS_MESSAGE_CLIENT.openMenuLink('standbyTrackingId2','备付资金跟踪编辑',standbyTrackingAddUrl + "&openMethod=tabs");
		};
		CALLBACK.assignmentAdd2 = function(index,rowData){
			var tempUuid = $('#tempUuid').val();
			var id = '';
			if(rowData){
				id = rowData.id;
			}
			var creditEntrustId = $('#id').val();
			var assignmentAddUrl = '<z:ukey key="com.zdsoft.finance.capital.initTransferInformation" context="admin"/>&jsoncallback=?&tempUuid='+tempUuid+'&id='+id+'&creditEntrustId='+creditEntrustId;
            ZDS_MESSAGE_CLIENT.openMenuLink('assignmentId2','信托计划转让信息编辑',assignmentAddUrl + "&openMethod=tabs");
		};
		CALLBACK.investmentAdd2 = function(index,rowData){
			var tempUuid = $('#tempUuid').val();
			var id = '';
			if(rowData){
				id = rowData.id;
			}
			var creditEntrustId = $('#id').val();
			var investmentAddUrl = '<z:ukey key="com.zdsoft.finance.capital.initPrincipalInvestment" context="admin"/>&jsoncallback=?&tempUuid='+tempUuid+'&id='+id+'&creditEntrustId='+creditEntrustId;
            ZDS_MESSAGE_CLIENT.openMenuLink('investmentId2','信托计划本金投入编辑',investmentAddUrl + "&openMethod=tabs");
		};
		
		CALLBACK.redemPrincipalAdd2 = function(index,rowData){
			var tempUuid = $('#tempUuid').val();
			var id = '';
			if(rowData){
				id = rowData.id;
			}
			var creditEntrustId = $('#id').val();
			var initRedemePrinciUrl = '<z:ukey key="com.zdsoft.finance.capital.initRedemePrinci" context="admin"/>&jsoncallback=?&tempUuid='+tempUuid+'&id='+id+'&creditEntrustId='+creditEntrustId;
            ZDS_MESSAGE_CLIENT.openMenuLink('redemePrinciId2','本金赎回编辑',initRedemePrinciUrl + "&openMethod=tabs");
		};
		
		//  计算总金额
		CALLBACK.sumPrincipalTotal = function(index,rowData){
			return parseFloat(index.initialPrincipal) + parseFloat(index.principalAmount);
		};
		
		// 查看事件
		CALLBACK.fundTrackingView = function(index,rowData){
			var tempUuid = $('#tempUuid').val();
			var id = '';
			if(rowData){
				id = rowData.id;
			}
			var creditEntrustId = $('#id').val();
			var addFundTrackingUrl = '<z:ukey key="com.zdsoft.finance.capital.initNonPrincipalTrackingView" context="admin"/>&jsoncallback=?&tempUuid='+tempUuid+'&id='+id+'&creditEntrustId='+creditEntrustId;
            ZDS_MESSAGE_CLIENT.openMenuLink('fundTrakcingId','专户贷方资金（非本金）跟踪',addFundTrackingUrl + "&openMethod=tabs");
		};
		CALLBACK.trackingView = function(index,rowData){
			var tempUuid = $('#tempUuid').val();
			var id = '';
			if(rowData){
				id = rowData.id;
			}
			var creditEntrustId = $('#id').val();
			var trackingAddUrl = '<z:ukey key="com.zdsoft.finance.capital.initCostTrackingView" context="admin"/>&jsoncallback=?&tempUuid='+tempUuid+'&id='+id+'&creditEntrustId='+creditEntrustId;
            ZDS_MESSAGE_CLIENT.openMenuLink('trackingId','信托计划应付费用跟踪',trackingAddUrl + "&openMethod=tabs");
		};
		CALLBACK.feeTrackingView = function(index,rowData){
			var tempUuid = $('#tempUuid').val();
			var id = '';
			if(rowData){
				id = rowData.id;
			}
			var creditEntrustId = $('#id').val();
			var feeTrackingAddUrl = '<z:ukey key="com.zdsoft.finance.capital.initTrackingTrustPlanView" context="admin"/>&jsoncallback=?&tempUuid='+tempUuid+'&id='+id+'&creditEntrustId='+creditEntrustId;
            ZDS_MESSAGE_CLIENT.openMenuLink('feeTrackingId','信托计划借方资金（非放款）跟踪',feeTrackingAddUrl + "&openMethod=tabs");
		};
		CALLBACK.standbyTrackingView = function(index,rowData){
			var tempUuid = $('#tempUuid').val();
			var id = '';
			if(rowData){
				id = rowData.id;
			}
			var creditEntrustId = $('#id').val();
			var standbyTrackingAddUrl = '<z:ukey key="com.zdsoft.finance.capital.initReserveFundTrackingView" context="admin"/>&jsoncallback=?&tempUuid='+tempUuid+'&id='+id+'&creditEntrustId='+creditEntrustId;
            ZDS_MESSAGE_CLIENT.openMenuLink('standbyTrackingId','备付资金跟踪',standbyTrackingAddUrl + "&openMethod=tabs");
		};
		CALLBACK.assignmentView = function(index,rowData){
			var tempUuid = $('#tempUuid').val();
			var id = '';
			if(rowData){
				id = rowData.id;
			}
			var creditEntrustId = $('#id').val();
			var assignmentAddUrl = '<z:ukey key="com.zdsoft.finance.capital.initTransferInformationView" context="admin"/>&jsoncallback=?&tempUuid='+tempUuid+'&id='+id+'&creditEntrustId='+creditEntrustId;
            ZDS_MESSAGE_CLIENT.openMenuLink('assignmentId','信托计划转让信息',assignmentAddUrl + "&openMethod=tabs");
		};
		CALLBACK.investmentView = function(index,rowData){
			var tempUuid = $('#tempUuid').val();
			var id = '';
			if(rowData){
				id = rowData.id;
			}
			var creditEntrustId = $('#id').val();
			var investmentAddUrl = '<z:ukey key="com.zdsoft.finance.capital.initPrincipalInvestmentView" context="admin"/>&jsoncallback=?&tempUuid='+tempUuid+'&id='+id+'&creditEntrustId='+creditEntrustId;
            ZDS_MESSAGE_CLIENT.openMenuLink('investmentId','信托计划本金投入',investmentAddUrl + "&openMethod=tabs");
		};
		CALLBACK.redemPrincipalView = function(index,rowData){
			var tempUuid = $('#tempUuid').val();
			var id = '';
			if(rowData){
				id = rowData.id;
			}
			var creditEntrustId = $('#id').val();
			var initRedemePrinciUrl = '<z:ukey key="com.zdsoft.finance.capital.initRedemePrinciView" context="admin"/>&jsoncallback=?&tempUuid='+tempUuid+'&id='+id+'&creditEntrustId='+creditEntrustId;
            ZDS_MESSAGE_CLIENT.openMenuLink('redemePrinciId','本金赎回',initRedemePrinciUrl + "&openMethod=tabs");
		};
		
		CALLBACK.investmentConfirm = function(index,rowData){
			var tempUuid = $('#tempUuid').val();
			var id = '';
			if(rowData){
				id = rowData.id;
			}
			var creditEntrustId = $('#id').val();
			var investmentAddUrl = '<z:ukey key="com.zdsoft.finance.capital.initPrincipalInvestmentConfirm" context="admin"/>&jsoncallback=?&tempUuid='+tempUuid+'&id='+id+'&creditEntrustId='+creditEntrustId;
            ZDS_MESSAGE_CLIENT.openMenuLink('investmentId','信托计划本金投入到账确认',investmentAddUrl + "&openMethod=tabs");
		};
		
		CALLBACK.standbyTrackingConfirm = function(index,rowData){
			var tempUuid = $('#tempUuid').val();
			var id = '';
			if(rowData){
				id = rowData.id;
			}
			var creditEntrustId = $('#id').val();
			var standbyTrackingAddUrl = '<z:ukey key="com.zdsoft.finance.capital.initStandbyTrackingConfirm" context="admin"/>&jsoncallback=?&tempUuid='+tempUuid+'&id='+id+'&creditEntrustId='+creditEntrustId;
            ZDS_MESSAGE_CLIENT.openMenuLink('investmentId','备付金跟踪到账确认',standbyTrackingAddUrl + "&openMethod=tabs");
		};
		
		
		// 回调函数
		CALLBACK.fundTracking = function(index,rowData){
			return '<a href="javaScript:void(0)" onclick="fundTrackingView" class="icon-btn31 handler-icon c-orange" title="查看"></a><a href="javaScript:void(0)" onclick="fundTrackingAdd2" class="icon-btn22 handler-icon c-green" title="编辑"></a>';
		};
		CALLBACK.formatTracking = function(index,rowData){
			return '<a href="javaScript:void(0)" onclick="trackingView" class="icon-btn31 handler-icon c-orange" title="查看"></a><a href="javaScript:void(0)" onclick="trackingAdd2"  class="icon-btn22 handler-icon c-green" title="编辑"></a>';
		};
		CALLBACK.formatFeeTracking = function(index,rowData){
			return '<a href="javaScript:void(0)" onclick="feeTrackingView" class="icon-btn31 handler-icon c-orange" title="查看"></a><a href="javaScript:void(0)" onclick="feeTrackingAdd2"  class="icon-btn22 handler-icon c-green" title="编辑"></a>';
		};
		CALLBACK.standbyTracking = function(index,rowData){
			if(index.status == 1){
				return '<a href="javaScript:void(0)" onclick="standbyTrackingView" class="icon-btn31 handler-icon c-orange" title="查看"></a><a href="javaScript:void(0)" onclick="standbyTrackingConfirm" class="icon-btn30 handler-icon c-blue" title="到账确认"></a><a href="javaScript:void(0)" onclick="standbyTrackingAdd2"  class="icon-btn22 handler-icon c-green" title="编辑"></a>';
			}else{
				return '<a href="javaScript:void(0)" onclick="standbyTrackingView" class="icon-btn31 handler-icon c-orange" title="查看"></a><a href="javaScript:void(0)" onclick="standbyTrackingAdd2"  class="icon-btn22 handler-icon c-green" title="编辑"></a>';	
			}
			
		};
		CALLBACK.formatAssignment = function(index,rowData){
			return '<a href="javaScript:void(0)" onclick="assignmentView" class="icon-btn31 handler-icon c-orange" title="查看"></a><a href="javaScript:void(0)" onclick="assignmentAdd2"  class="icon-btn22 handler-icon c-green" title="编辑"></a>';
		};
		CALLBACK.investmentFormat = function(index,rowData){
			if(index.status == 1){
				return '<a href="javaScript:void(0)" onclick="investmentView" class="icon-btn31 handler-icon c-orange" title="查看"></a><a href="javaScript:void(0)" onclick="investmentConfirm" class="icon-btn30 handler-icon c-blue" title="到账确认"></a><a href="javaScript:void(0)" onclick="investmentAdd2"  class="icon-btn22 handler-icon c-green" title="编辑"></a>';
			}else{
				return '<a href="javaScript:void(0)" onclick="investmentView" class="icon-btn31 handler-icon c-orange" title="查看"></a><a href="javaScript:void(0)" onclick="investmentAdd2"  class="icon-btn22 handler-icon c-green" title="编辑"></a>';	
			}
			
		};
		CALLBACK.redemPrincipalFormat = function(index,rowData){
			return '<a href="javaScript:void(0)" onclick="redemPrincipalView" class="icon-btn31 handler-icon c-orange" title="查看"></a><a href="javaScript:void(0)" onclick="redemPrincipalAdd2"  class="icon-btn22 handler-icon c-green" title="编辑"></a>';
		};
		
		
		// 计算本金规模
		CALLBACK.loadPrincipaScale = function(index,rowData){
			var creditEntrustId = $('#id').val();		
			$.ajax({
                type: 'post',
                url: '<z:ukey key="com.zdsoft.finance.capital.countPrincipaScale" context="admin"/>&jsoncallback=?',
                data: {'creditEntrustId':creditEntrustId},
                dataType: 'json',
                success: function (data) {
                  	$('#principaScale').val(data);
                },
                error: function () {
                	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
                        $(".zd-message").ZWindow("close");
                    });
                }
            }); 
		};
		
		CALLBACK.checkValue = function(index,rowData){
			if('YWDM0013501' == index){
				$('#transferPlanIdDd').show();
			}else{
				$('#transferPlanIdDd').hide();
				$('#transferPlanId').ZCombobox('setValue', '');
			}
		};
		
		CALLBACK.changeDate = function(index,rowData){
			return TOOL.strToDate(index.completeDate);
		}
		
		// 初始化
		$.ZUI.init();
		
		
		
		// 本页面刷新方法
		ZDS_MESSAGE_CLIENT.refreshThis=function(){
			$('#cost_datagrid').ZTable('reload');
			$('#fundTracking_datagrid').ZTable('reload');
			$('#tracking_datagrid').ZTable('reload');
			
			$('#feeBar_datagrid').ZTable('reload');
			$('#standbyTracking_datagrid').ZTable('reload');
			$('#assignment_datagrid').ZTable('reload');
			$('#investment_datagrid').ZTable('reload');
			$('#redemPrincipal_datagrid').ZTable('reload');
        };
        
        // 保存按钮点击
        $('#saveBtn').click(function(){
        	var isValidate = $.ZUI.validateForm($('#credit_entrust_form'));
			if(isValidate){
	        	var param = $('#credit_entrust_form').serialize();
				$.ajax({
	                type: 'post',
	                url: '<z:ukey key="com.zdsoft.finance.capital.updateCreditEntrust" context="admin"/>',
	                data: param,
	                dataType: 'json',
	                success: function (data) {
	                    if (data.resultStatus == 0) {
	                    	$.ZMessage.success("成功", data.msg, function () {
	                            setTimeout(function(){
	                            	ZDS_MESSAGE_CLIENT.refreshOpenner();
	                           	    ZDS_MESSAGE_CLIENT.closeSelf();
	                            },200);
	                        });
	                    }else{
	                    	$.ZMessage.error("错误", data.msg, function () {
	                            $(".zd-message").ZWindow("close");
	                        });
	                    }
	                },
	                error: function () {
	                	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
	                        $(".zd-message").ZWindow("close");
	                    });
	                }
	            }); 
			}else{
				$.ZMessage.error("错误", "页面验证失败", function () {
                });
			}
        });
        
        
        var id = $('#id').val();
        $('#oneKeyFundMatching').click(function(){
			$.ajax({
                type: 'post',
                url: '<z:ukey key="com.zdsoft.finance.capital.oneKeyFundMatching" context="admin"/>',
                data: {'creditEntrustId':id},
                dataType: 'json',
                success: function (data) {
                	if (data.resultStatus == 0) {
                    	$.ZMessage.success("成功", data.msg, function () {
                        });
                    }else{
                    	$.ZMessage.error("错误", data.msg, function () {
                            $(".zd-message").ZWindow("close");
                        });
                    }
                },
                error: function () {
                	$.ZMessage.error("错误", data.msg, function () {
                        $(".zd-message").ZWindow("close");
                    });
                }
            }); 
        });
        
        // 取消按钮
        $('#cancelBtn').click(function(){
        	 ZDS_MESSAGE_CLIENT.closeSelf();
        });
        
        var data = '${data}';
        
        // 待拨户开户行模糊搜索
        $("#waitApprBank").completer({
            suggest: true,//默认false
            idField: 'code',//默认id,唯一标识字段
            nameField: 'name',//默认name,下拉列表展示数据的字段
            valueField: 'py',//默认value,根据值查询数据的字段
            source:data,
            writable: false,//默认false，是否可自定义输入
            placeObj:$("#ddwaitApprBank"),//悬浮框需要定位到的对象
            complete: function (data) {
                $('#waitApprBank').val(data.name);
            },
            filter: function (val) {
                return val;//过滤输入的value值
            }

        });
        
        // 归集户开户行模糊搜索
		$("#collectionAccountBank").completer({
            suggest: true,//默认false
            idField: 'code',//默认id,唯一标识字段
            nameField: 'name',//默认name,下拉列表展示数据的字段
            valueField: 'py',//默认value,根据值查询数据的字段
            source:data,
            writable: false,//默认false，是否可自定义输入
            placeObj:$("#ddcollectionAccountBank"),//悬浮框需要定位到的对象
            complete: function (data) {
                $('#collectionAccountBank').val(data.name);
            },
            filter: function (val) {
                return val;//过滤输入的value值
            }

        });
        
        // 专户开户行模糊搜索
		$("#specialAccountBank").completer({
            suggest: true,//默认false
            idField: 'code',//默认id,唯一标识字段
            nameField: 'name',//默认name,下拉列表展示数据的字段
            valueField: 'py',//默认value,根据值查询数据的字段
            source:data,
            writable: false,//默认false，是否可自定义输入
            placeObj:$("#ddspecialAccountBank"),//悬浮框需要定位到的对象
            complete: function (data) {
                $('#specialAccountBank').val(data.name);
            },
            filter: function (val) {
                return val;//过滤输入的value值
            }

        });
        
        // 备付户开户行模糊搜索
		$("#spareAccountBank").completer({
            suggest: true,//默认false
            idField: 'code',//默认id,唯一标识字段
            nameField: 'name',//默认name,下拉列表展示数据的字段
            valueField: 'py',//默认value,根据值查询数据的字段
            source:data,
            writable: false,//默认false，是否可自定义输入
            placeObj:$("#ddspareAccountBank"),//悬浮框需要定位到的对象
            complete: function (data) {
                $('#spareAccountBank').val(data.name);
            },
            filter: function (val) {
                return val;//过滤输入的value值
            }
        });
        
        $(function(){
        	var tempValue = $('#assignmentState').ZCombobox('getValue');
        	if('YWDM0013501' == tempValue){
				$('#transferPlanIdDd').show();
			}else{
				$('#transferPlanIdDd').hide();
				$('#transferPlanId').ZCombobox('setValue', '');
			}
        });
        
        window.countValue = function(){
        	var id = $('#id').val();
        	var retain = $('#retain').val();
        	$.ajax({
                type: 'post',
                url: '<z:ukey key="com.zdsoft.finance.capital.getPrincipalAmounts" context="admin"/>&jsoncallback=?',
                data: {'creditEntrustId':id},
                dataType: 'json',
                success: function (data) {
               		if(retain > data){
               			$.ZMessage.error("错误", '截留额度不能大于本金投入总金额', function () {
               				$('#retain').val('');
                        });
               		}
                },
                error: function () {
                	$.ZMessage.error("错误", '计算本金投入总金额失败', function () {
                        $(".zd-message").ZWindow("close");
                    });
                }
            }); 
        };
	});
</script>
</body>
</html>