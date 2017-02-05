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
			<input type="hidden" id="tempUuid" name="tempUuid" value="${tempUuid }"/>
			<div class="page-box">
				<div class="page-title">基本情况</div>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>资方：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-disabled zui-validatebox" validate-type="Require"
							 id="cooperatorName" value="${cooperatorName }">
							 <input type="hidden" id="capitalistId" name="capitalistId" value="${capitalistId }"/>
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>成立时间：</dt>
					<dd class="detail">
						<label>
							 <input type="text" id="establishmentDateLocal" class="zui-input  zui-validatebox" validate-type="Require" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'establishmentDate'})">
                             <input type="hidden" name="establishmentDate"  id="establishmentDate" />
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>是否主计划：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-combobox zui-validatebox" id="isMasterPlan" name="isMasterPlan" type="hidden" 
                              data-data="[{'id':'0','text':'否'},{'id':'1','text':'是'}]"
                              data-valuefield="id" data-textfield="text" validate-type="Require">
						</label>
					</dd>
				</dl>
				
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>信托类型：</dt>
					<dd class="detail">
						<input class="zui-combobox zui-validatebox" id="trustType" name="trustType" type="hidden"
                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=tttp"
                              data-valuefield="fullcode"  data-textfield="name" validate-type="Require">
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>计划名称：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require"
							 id="creditEntrustName" name="creditEntrustName">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>本金规模(元)：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-disabled zui-validatebox" validate-type="Require" 
							 id="principaScale" disabled name="principaScale">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>转让状态：</dt>
					<dd class="detail">
						<input class="zui-combobox zui-validatebox" id="assignmentState" name="assignmentState" type="hidden"
                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=tsst"
                              data-valuefield="fullcode" data-textfield="name" validate-type="Require">
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>转让规模(元)：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require"
							 id="assignmentScale" name="assignmentScale">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>最低限额(元)：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Digital[18-12]"
							 id="minQuota" name="minQuota">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>服务费率(%)：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Digital[18-12]"
							 id="severRate" name="severRate">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>保管费率(%)：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Digital[18-12]"
							 id="keepRate" name="keepRate">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>管理费率(%)：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Digital[18-12]"
							 id="managerRate" name="managerRate">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>待拨户开户行：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require"
							 id="waitApprBank" name="waitApprBank">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>待拨户账户名：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require"
							 id="waitApprName" name="waitApprName">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>待拨户账号：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Digital[18-12]"
							 id="waitApprNo" name="waitApprNo">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>归集户开户行：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require"
							 id="collectionAccountBank" name="collectionAccountBank">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>归集户账户名：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require"
							 id="collectionAccountName" name="collectionAccountName">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>归集户账号：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Digital[18-12]"
							 id="collectionAccountNo" name="collectionAccountNo">
						</label>
					</dd>
				</dl>	
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>专户开户行：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require"
							 id="specialAccountBank" name="specialAccountBank">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>专户账户名：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require"
							 id="specialAccountName" name="specialAccountName">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>专户账号：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Digital[18-12]"
							 id="specialAccountNo" name="specialAccountNo">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>备付户开户行：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require"
							 id="spareAccountBank" name="spareAccountBank">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>备付户账户名：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require"
							 id="spareAccountName" name="spareAccountName">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>备付户账号：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require,Digital[18-12]"
							 id="spareAccountNo" name="spareAccountNo">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>清分账号：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require"
							 id="clearingAccount" name="clearingAccount">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>商户ID：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require"
							 id="merchantID" name="merchantID">
						</label>
					</dd>
				</dl>
				<dl class="form-item block">
                	<dt class="title">备注：</dt>
	                <dd class="detail">
		                <label>
		                	<textarea class="zui-area zui-validatebox" id="remark" name="remark" validate-type="Length[0-200]" placeholder="最多可以输入200个字符"></textarea>
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
                              data-data="[{'id':'0','text':'是'},{'id':'1','text':'否'}]"
                              data-valuefield="id"  data-textfield="text" validate-type="Require">
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title sptitle"><b class="c-red mr5">*</b>是否资金自动匹配：</dt>
					<dd class="detail">
						<input class="zui-combobox zui-validatebox" id="isAutoMatch" name="isAutoMatch" type="hidden"
                             data-data="[{'id':'0','text':'是'},{'id':'1','text':'否'}]"
                              data-valuefield="id" data-textfield="text" validate-type="Require">
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>截留额度(元)：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require"
							 id="retain" name="retain">
						</label>
					</dd>
				</dl>
			</div>
			<div class="page-box">
				<div class="page-title">出资信息</div>
				<div class="p10">
					<div id="cost_datagrid" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.capital.investmentList' context='admin'/>&jsoncallback=?&tempUuid|E|S=${tempUuid }","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index"}'>
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
					<div id="fundTracking_datagrid" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.capital.loanCapitalList' context='admin'/>&jsoncallback=?&tempUuid|E|S=${tempUuid }","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#fundTrackingBar"}'>
						<table>
							<thead>
			        			<tr>
			            			<th data-options="field:lenderTypeName">贷方类型</th>
			            			<th data-options="field:lenderName">名称</th>
			            			<th data-options="field:totalAmount">发生总金额</th>
			            			<th data-options="field:happenDate">实际日期</th>
			            			<th data-options="field:completeEmpName">提交人</th>
			            			<th data-options="field:completeDate">提交日期</th>
			            			<th data-options="field:statusName">处理状态</th>
			            			<th data-options="field:capitalStateName">状态</th>
			            			<th data-options="field:remark">备注</th>
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
					<div id="tracking_datagrid" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.capital.costTrackingList' context='admin'/>&jsoncallback=?&tempUuid|E|S=${tempUuid }","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#trackingBar"}'>
						<table>
							<thead>
			        			<tr>
			            			<th data-options="field:expenditureTypeName">对象类型</th>
			            			<th data-options="field:costName">名称</th>
			            			<th data-options="field:totalAmount">应付总金额</th>
			            			<th data-options="field:payDate">应付日期</th>
			            			<th data-options="field:completeEmpName">提交人</th>
			            			<th data-options="field:completeDate">提交日期</th>
			            			<th data-options="field:statusName">状态</th>
			            			<th data-options="field:remark">备注</th>
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
					<div id="feeBar_datagrid" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.capital.debitTrackingList' context='admin'/>&jsoncallback=?&tempUuid|E|S=${tempUuid }","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#feeBar"}'>
						<table>
							<thead>
			        			<tr>
			            			<th data-options="field:debitTypeName">支出类型</th>
			            			<th data-options="field:objectName">名称</th>
			            			<th data-options="field:totalAmount">发生总金额</th>
		            				<th data-options="field:actualOutDate">实际日期</th>
		            				<th data-options="field:completeEmpName">提交人</th>
			            			<th data-options="field:completeDate">提交日期</th>
			            			<th data-options="field:statusName">处理状态</th>
			            			<th data-options="field:capitalStateName">状态</th>
			            			<th data-options="field:remark">备注</th>
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
					<div id="standbyTracking_datagrid" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.capital.spareCapitalList' context='admin'/>&jsoncallback=?&tempUuid|E|S=${tempUuid }","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#standbyTrackingBar"}'>
						<table>
							<thead>
			        			<tr>
			            			<th data-options="field:operationTypeName">操作类型</th>
			            			<th data-options="field:applyAmount">申请金额</th>
			            			<th data-options="field:actualAmount">到账金额</th>
			            			<th data-options="field:distributionAmount">未分配到账金额</th>
			            			<th data-options="field:completeDate">提交日期</th>
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
					<div id="assignment_datagrid" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.capital.attomCapitalList' context='admin'/>&jsoncallback=?&tempUuid|E|S=${tempUuid }","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#assignmentBar"}'>
						<table>
							<thead>
			        			<tr>
			            			<th data-options="field:acceptTypeName">类型</th>
			            			<th data-options="field:acceptName">受让方名称</th>
			            			<th data-options="field:acceptAmount">受让金额</th>
			            			<th data-options="field:contractProfitRate">合同收益率%</th>
			            			<th data-options="field:mobile">移动电话</th>
			            			<th data-options="field:attomEffect">转让日期</th>
			            			<th data-options="field:attomEndDate">截止日期</th>
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
					<div id="investment_datagrid" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.capital.principalInputList' context='admin'/>&jsoncallback=?&tempUuid|E|S=${tempUuid }","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#investmentBar","onLoadSuccess":"loadPrincipaScale"}'>
						<table>
							<thead>
			        			<tr>
			            			<th data-options="field:contribution">出资方名称</th>
			            			<th data-options="field:contributionTypeName">类型</th>
			            			<th data-options="field:principalAmount">本金金额</th>
			            			<th data-options="field:usedQuota">使用额度</th>
			            			<th data-options="field:useProp">使用比率%</th>
			            			<th data-options="field:profitRate">合同收益率%</th>
			            			<th data-options="field:addDate">追加日期</th>
			            			<th data-options="field:expectDate">预计到账日期</th>
			            			<th data-options="field:actualDate">到账日期</th>
			            			<th data-options="field:statusName">状态</th>
			            			<th data-options="field:remark">备注</th>
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
					<div id="redemPrincipal_datagrid" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.capital.redemePrinciList' context='admin'/>&jsoncallback=?&tempUuid|E|S=${tempUuid }","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#redemPrincipalBar","onLoadSuccess":"loadPrincipaScale"}'>
						<table>
							<thead>
			        			<tr>
			            			<th data-options="field:contribution">出资方名称</th>
			            			<th data-options="field:contributionTypeName">类型</th>
			            			<th data-options="field:redemptionAmount">赎回金额</th>
			            			<th data-options="field:statusName">状态</th>
			            			<th data-options="field:remark">备注</th>
			            			<th data-options="field:id" formatter="redemPrincipalFormat">操作</th>
						        </tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
            <div class="form-btn">
               	<button id="cancelBtn" type="button" class="btn-gray">取消</button>
               	<button id="saveBtn" type="button" class="btn-blue">保存</button>
            </div>
		</form>
	</div>
</div>
<div id="zds_btn_selecter"></div>
<div id="chooseMember"></div>
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
		
		// 专户贷方资金（非本金）跟踪打开menuLink
		CALLBACK.fundTrackingAdd = function(index,rowData){
			var tempUuid = $('#tempUuid').val();
			var id = '';
			if(rowData){
				id = rowData.id;
			}
			var addFundTrackingUrl = '<z:ukey key="com.zdsoft.finance.capital.initNonPrincipalTracking" context="admin"/>&jsoncallback=?&tempUuid='+tempUuid+'&id='+id;
            ZDS_MESSAGE_CLIENT.openMenuLink('fundTrakcingId','专户贷方资金（非本金）跟踪',addFundTrackingUrl + "&openMethod=tabs");
		};
		
		// 信托计划应付费用跟踪Menulink
		CALLBACK.trackingAdd = function(index,rowData){
			var tempUuid = $('#tempUuid').val();
			var id = '';
			if(rowData){
				id = rowData.id;
			}
			var trackingAddUrl = '<z:ukey key="com.zdsoft.finance.capital.initCostTracking" context="admin"/>&jsoncallback=?&tempUuid='+tempUuid+'&id='+id;
            ZDS_MESSAGE_CLIENT.openMenuLink('trackingId','信托计划应付费用跟踪',trackingAddUrl + "&openMethod=tabs");
		};
		
		// 信托计划借方资金（非放款）跟踪Menulink
		CALLBACK.feeTrackingAdd = function(index,rowData){
			var tempUuid = $('#tempUuid').val();
			var id = '';
			if(rowData){
				id = rowData.id;
			}
			var feeTrackingAddUrl = '<z:ukey key="com.zdsoft.finance.capital.initTrackingTrustPlan" context="admin"/>&jsoncallback=?&tempUuid='+tempUuid+'&id='+id;
            ZDS_MESSAGE_CLIENT.openMenuLink('feeTrackingId','信托计划借方资金（非放款）跟踪',feeTrackingAddUrl + "&openMethod=tabs");
		};
		
		// 备付资金跟踪Menulink
		CALLBACK.standbyTrackingAdd = function(index,rowData){
			var tempUuid = $('#tempUuid').val();
			var id = '';
			if(rowData){
				id = rowData.id;
			}
			var standbyTrackingAddUrl = '<z:ukey key="com.zdsoft.finance.capital.initReserveFundTracking" context="admin"/>&jsoncallback=?&tempUuid='+tempUuid+'&id='+id;
            ZDS_MESSAGE_CLIENT.openMenuLink('standbyTrackingId','备付资金跟踪',standbyTrackingAddUrl + "&openMethod=tabs");
		};
		
		// 信托计划转让信息Menulink
		CALLBACK.assignmentAdd = function(index,rowData){
			var tempUuid = $('#tempUuid').val();
			var id = '';
			if(rowData){
				id = rowData.id;
			}
			var assignmentAddUrl = '<z:ukey key="com.zdsoft.finance.capital.initTransferInformation" context="admin"/>&jsoncallback=?&tempUuid='+tempUuid+'&id='+id;
            ZDS_MESSAGE_CLIENT.openMenuLink('assignmentId','信托计划转让信息',assignmentAddUrl + "&openMethod=tabs");
		};
		
		// 信托计划本金投入Menulink
		CALLBACK.investmentAdd = function(index,rowData){
			var tempUuid = $('#tempUuid').val();
			var id = '';
			if(rowData){
				id = rowData.id;
			}
			var investmentAddUrl = '<z:ukey key="com.zdsoft.finance.capital.initPrincipalInvestment" context="admin"/>&jsoncallback=?&tempUuid='+tempUuid+'&id='+id;
            ZDS_MESSAGE_CLIENT.openMenuLink('investmentId','信托计划本金投入',investmentAddUrl + "&openMethod=tabs");
		};
		
		// 赎回本金Menulink
		CALLBACK.redemPrincipalAdd = function(index,rowData){
			var tempUuid = $('#tempUuid').val();
			var id = '';
			if(rowData){
				id = rowData.id;
			}
			var initRedemePrinciUrl = '<z:ukey key="com.zdsoft.finance.capital.initRedemePrinci" context="admin"/>&jsoncallback=?&tempUuid='+tempUuid+'&id='+id;
            ZDS_MESSAGE_CLIENT.openMenuLink('redemePrinciId','本金赎回',initRedemePrinciUrl + "&openMethod=tabs");
		};
		
		
		// 计算总金额
		CALLBACK.sumPrincipalTotal = function(index,rowData){
			return parseFloat(index.initialPrincipal) + parseFloat(index.principalAmount);
		};
		
		// 回调
		CALLBACK.fundTracking = function(index,rowData){
			return '<a href="javaScript:void(0)" onclick="fundTrackingAdd"><button type="button" class="btn-blue">查看/处理</button></a>';
		};
		CALLBACK.formatTracking = function(index,rowData){
			return '<a href="javaScript:void(0)" onclick="trackingAdd"><button type="button" class="btn-blue">查看/处理</button></a>';
		};
		CALLBACK.formatFeeTracking = function(index,rowData){
			return '<a href="javaScript:void(0)" onclick="feeTrackingAdd"><button type="button" class="btn-blue">查看/处理</button></a>';
		};
		CALLBACK.standbyTracking = function(index,rowData){
			return '<a href="javaScript:void(0)" onclick="standbyTrackingAdd"><button type="button" class="btn-blue">查看/处理</button></a>';
		};
		CALLBACK.formatAssignment = function(index,rowData){
			return '<a href="javaScript:void(0)" onclick="assignmentAdd"><button type="button" class="btn-blue">查看/处理</button></a>';
		};
		CALLBACK.investmentFormat = function(index,rowData){
			return '<a href="javaScript:void(0)" onclick="investmentAdd"><button type="button" class="btn-blue">查看/处理</button></a>';
		};
		CALLBACK.redemPrincipalFormat = function(index,rowData){
			return '<a href="javaScript:void(0)" onclick="redemPrincipalAdd"><button type="button" class="btn-blue">查看/处理</button></a>';
		};
		
		// 计算本金规模
		CALLBACK.loadPrincipaScale = function(index,rowData){
			var tempUuid = '${tempUuid}';
			$.ajax({
                type: 'post',
                url: '<z:ukey key="com.zdsoft.finance.capital.countPrincipaScale" context="admin"/>&jsoncallback=?',
                data: {'tempUuid':tempUuid},
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
		
		// 初始化
		$.ZUI.init();
		
		// 父页面刷新
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
	                url: '<z:ukey key="com.zdsoft.finance.capital.saveCreditEntrust" context="admin"/>',
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
			}
        });
        
        // 取消按钮
        $('#cancelBtn').click(function(){
        	 ZDS_MESSAGE_CLIENT.closeSelf();
        });
        
	});
</script>
</body>
</html>