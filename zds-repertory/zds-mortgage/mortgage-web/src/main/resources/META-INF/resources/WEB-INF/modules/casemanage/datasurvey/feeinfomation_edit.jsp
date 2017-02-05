<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<div class="frm-content" id="feeInfoDiv">
	<c:if test="${not empty errorMsg }">页面初始化出现异常，请联系管理员！异常信息为：${errorMsg }</c:if>
	<c:if test="${not empty hasResultMap and not hasResultMap }">无对应的费用配置信息！</c:if>
	<form id="feeInfoForm" class="zui-form " method="post" >
		<c:forEach items="${feeInfoMap}" var="listInfo" varStatus="status" >
		<div class="page-box">
			<div class="page-title">${listInfo.key }</div>
			<div class="p10" name="feeDetailContent">
				<div class="mb5">
	                <span class="mr10">应收:<span class="c-orange" name="totalShouldReceiveSpan">0.00</span>元</span>
	                <span class="mr10">|</span>
	                <span class="mr10">实收:<span class="c-orange" name="totalTrueReceiveSpan">0.00</span>元</span>
	                <span class="mr10">|</span>
	                <span class="mr10">应付:<span class="c-orange" name="totalShouldPaySpan">0.00</span>元</span>
	                <span class="mr10">|</span>
	                <span class="mr10">实付:<span class="c-orange" name="totalTruePaySpan">0.00</span>元</span>
	                <a class="fr f14 c-gray icon-open-bot" onclick="showOrHideDetail(this,'#table_content_${status.index}')"></a>
	            </div>
				<table class="table-index scorll" id="table_content_${status.index}">
					<thead class="datagrid-header">
						<tr>
							<th>收费项目</th>
							<th>收费对象类别</th>
							<th>收费对象</th>
							<th>预计应收(元)</th>
							<th>实收(元)</th>
							<th>付费对象类别</th>
							<th>付费对象</th>
							<th>预计应付(元)</th>
							<th>实付(元)</th>
							<th>结余(元)</th>
						</tr>
					</thead>
					<tbody class="datagrid-body">
						<c:forEach items="${listInfo.value }" var="feeInfo">
						<tr>
							<!-- 收费项目 -->
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
				                        <label>
				                        	<input name="feeInfos[${feeInfo.serialNum }].feeItem" local="feeItem" value="${feeInfo.feeItem }" type="hidden" />
				                        	<input name="feeInfos[${feeInfo.serialNum }].feeItemName" local="feeItemName" value="${feeInfo.feeItemName }" type="hidden" />
				                        	<input name="feeInfos[${feeInfo.serialNum }].feeType" local="feeType" value="${feeInfo.feeType }" type="hidden" />
				                        	<input name="feeInfos[${feeInfo.serialNum }].feeTypeName" local="feeTypeName" value="${feeInfo.feeTypeName }" type="hidden" />
				                        	<span class="f12">${feeInfo.feeItemName }</span>
				                        </label>
				                    </dd>
			                    </dl>
							</td>
							<!-- 收费对象类型 -->
							<td>
								<dl class="form-item form-auto">
								<dd class="detail">
			                        <label>
			                        	<input class="zui-validatebox" type="hidden" name="feeInfos[${feeInfo.serialNum }].feeObjectType" local="feeObjectType" value="${feeInfo.feeObjectType }" validate-type="Require,Length[0-64]"/>
			                        </label>
			                    </dd>
			                </td>
			                <!-- 收费对象 -->
							<td>
								<dl class="form-item form-auto">
								<dd class="detail">
			                        <label>
			                        	<input type="hidden" name="feeInfos[${feeInfo.serialNum }].feeObjectId" local="feeObjectId" value="${feeInfo.feeObjectId }" />
			                            <input 
			                                   class="zui-input zui-validatebox zui-disabled"
			                                   type="text"
			                                   data-toggle="validate"
			                                   validate-type="Require,Length[0-256]" name="feeInfos[${feeInfo.serialNum }].feeeObjectName" local="feeeObjectName" readonly="readonly" value="${feeInfo.feeeObjectName }" />
			                            <div name="feeeObjectNameSelect" class="btn-gray nwidth3" style="cursor: pointer">选择</div>
			                        </label>
			                    </dd>
			                    </dl>
							</td>
							<!-- 预计应收 -->
							<td>
								<dl class="form-item form-auto">
								<dd class="detail">
			                        <label>
			                        	<input 
			                                   class="zui-input zui-validatebox nwidth2"
			                                   type="text"
			                                   validate-type="Require,Digital[13-2]" name="feeInfos[${feeInfo.serialNum }].expectedAmount" local="expectedAmount" value="${feeInfo.expectedAmount }" />
			                        </label>
			                    </dd>
			                    </dl>
							</td>
							<!-- 实收 -->
							<td>
								<dl class="form-item form-auto">
								<dd class="detail">
			                        <label>
			                            <input name="feeInfos[${feeInfo.serialNum }].receivedAmount" local="receivedAmount" value="${feeInfo.receivedAmount }" type="hidden" />
			                        	<span name="receivedAmountSpan" class="f12" >${empty feeInfo.receivedAmount ? 0.00 : feeInfo.receivedAmount }</span>
			                        </label>
			                    </dd>
			                    </dl>
							</td>
							<!-- 付费对象类型 -->
							<td>
								<dl class="form-item form-auto">
								<dd class="detail">
			                        <label>
			                        	<input class="zui-validatebox" type="hidden" name="feeInfos[${feeInfo.serialNum }].payObjectType" local="payObjectType" value="${feeInfo.payObjectType }" validate-type="Require,Length[0-64]"/>
			                        </label>
			                    </dd>
			                    </dl>
			                </td>
			                <!-- 付费对象 -->
							<td>
								<dl class="form-item form-auto">
								<dd class="detail">
			                        <label>
			                        	<input type="hidden" name="feeInfos[${feeInfo.serialNum }].payObjectId" local="payObjectId" value="${feeInfo.payObjectId }" />
			                            <input 
			                                   class="zui-input zui-validatebox zui-disabled"
			                                   type="text"
			                                   data-toggle="validate"
			                                   validate-type="Require,Length[0-256]" name="feeInfos[${feeInfo.serialNum }].payObjectName" local="payObjectName" readonly="readonly" value="${feeInfo.payObjectName }" />
			                            <div name="payObjectNameSelect" class="btn-gray nwidth3" style="cursor: pointer">选择</div>
			                        </label>
			                    </dd>
			                    </dl>
							</td>
							<!-- 预计应付 -->
							<td>
								<dl class="form-item form-auto">
								<dd class="detail">
			                        <label>
			                            <input
			                                   class="zui-input zui-validatebox nwidth2"
			                                   type="text"
			                                   data-toggle="validate"
			                                   validate-type="Require,Digital[13-2]" name="feeInfos[${feeInfo.serialNum }].expectedPayableAmount" local="expectedPayableAmount" value="${feeInfo.expectedPayableAmount }" />
			                        </label>
			                    </dd>
			                    </dl>
							</td>
							<!-- 实付 -->
							<td>
								<dl class="form-item form-auto">
								<dd class="detail">
			                        <label>
			                        	<input name="feeInfos[${feeInfo.serialNum }].paidAmount" value="${feeInfo.paidAmount }" local="paidAmount" type="hidden" vlaue="${feeInfo.paidAmount }" />
			                        	<span name="paidAmountSpan" class="f12" >${empty feeInfo.paidAmount ? 0.00 : feeInfo.paidAmount }</span>
			                        </label>
			                    </dd>
			                    </dl>
							</td>
							<!-- 结余 -->
							<td>
								<dl class="form-item form-auto">
								<dd class="detail">
			                        <label>
			                        	<input name="feeInfos[${feeInfo.serialNum }].balanceAmount" local="balanceAmount" value="${feeInfo.balanceAmount }" type="hidden" />
			                        	<span name="balanceAmountSpan" class="f12" >${empty feeInfo.balanceAmount ? 0.00 : feeInfo.balanceAmount  }</span>
			                        </label>
			                    </dd>
			                    </dl>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		</c:forEach>
	</form>
</div>
<!-- 收费对象选择器 -->
<div id="receive_user_selecter"></div>
<!-- 付费对象对话框 -->
<div id="pay_user_dialog" style="display: none;">
	<div class="p10">
		<div class="info-tab">
			<ul id="tabTitle" class="tabs">
				<li id="terminal" class="tabs-on"><a href="javascript:void(0);">终端</a></li>
				<li id="evaluation"><a href="javascript:void(0);">评估公司</a></li>
				<li id="otherCooperater"><a href="javascript:void(0);">其他合作单位</a></li>
			</ul>
			<div id="tabContent" class="tabcontents">
				<div id="terminalInfo">
					<div id="terminalSearchForm" class="p5">
				        <form id="terminal_search" class="zui-form" action="javascript:void(0);" zdata-options="{}">
				            <dl class="form-item">
				                <dt class="title">终端名称</dt>
				                <dd class="detail">
				                    <label>
				                        <input class="zui-input" id="terminalFullName" name="terminalFullName|LK|S">
				                    </label>
				                </dd>
				            </dl>
							<dl class="form-item">
				                <dt class="title">终端类别</dt>
				                <dd class="detail">
				                    <label>
				                        <input class="zui-combobox" id="terminalType" name="terminalType|E|S" type="hidden"
				                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=zdlb"
				                              data-valuefield="fullcode" data-textfield="name" >
				                    </label>
				                </dd>
				            </dl>
				        </form>
			            <div class="form-btn">
				         	<button class="btn-blue" id="terminalSearch">查询</button>
			   		    </div>
				    </div>
				    <div class="p10">
						<div id="terminal-table" class="tableArea" name="terminalFullName"></div>
					</div>
				</div>
				<!-- 评估机构 -->
				<div id="evaluationInfo" class="hide"> 
					<div id="evaluationSearchForm" class="p5">
				        <form id="evaluation_search" class="zui-form" action="javascript:void(0);" zdata-options="{}">
				            <dl class="form-item">
				                <dt class="title">评估公司名称</dt>
				                <dd class="detail">
				                    <label>
				                    	<input class="zui-input" id="companyName" name="zeov|companyName|LK|S">
				                    </label>
				                </dd>
				            </dl>
							<!-- <dl class="form-item">
				                <dt class="title">地址</dt>
				                <dd class="detail">
				                   <input type="hidden" id="evaluationProvince" name="evaluationProvince|E|S" />
		                           <input type="hidden" id="evaluationCity" name="evaluationCity|E|S"/>
		                           <input type="hidden" id="evaluationDistrict" name="evaluationDistrict|E|S"/>
		                           <div id="selectAddress_evaluation">
		                               <input id="address_evaluation_text" class="zui-input zui-validatebox" type="text" readonly="true" style="width: 260px;"/>
		                           </div>
				                </dd>
				            </dl> -->
				        </form>
			            <div class="form-btn">
				         	<button class="btn-blue" id="evaluationSearch">查询</button>
			   		    </div>
				    </div>
				    <div class="p10">
						<div id="evaluation-table" class="tableArea" name="companyName"></div>
					</div>
				</div>
				<div id="otherCooperaterInfo"class="hide">
					<div id="otherCooperaterSearchForm" class="p5">
				        <form id="otherCooperater_search" class="zui-form" action="javascript:void(0);" zdata-options="{}">
				            <dl class="form-item">
				                <dt class="title">合作单位名称</dt>
				                <dd class="detail">
				                    <label>
				                        <input class="zui-input" type="text"  name="contactCompanyName|LK|S">
				                    </label>
				                </dd>
				            </dl>
							<dl class="form-item">
				                <dt class="title">地址</dt>
				                <dd class="detail">
				                   <input type="hidden" id="detailedProvince" name="detailedProvince|E|S" />
		                           <input type="hidden" id="detailedCity" name="detailedCity|E|S"/>
		                           <input type="hidden" id="detailedDistrict" name="detailedDistrict|E|S"/>
		                           <div id="selectAddress_otherCoop">
		                               <input id="address_otherCoop_text" class="zui-input zui-validatebox" type="text" readonly="true" style="width: 260px;"/>
		                           </div>
				                </dd>
				            </dl>
				        </form>
			            <div class="form-btn">
				         	<button class="btn-blue" id="otherCooperaterSearch">查询</button>
			   		    </div>
				    </div>
				    <div class="p10">
						<div id="otherCooperater-table" class="tableArea" name="contactCompanyName" ></div>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>
<!-- JS 代码块 -->
<script type="text/javascript">
//收费对象选择前的处理函数
var receiveSelectBefore = null;
// 收费对象选择后的回调函数
var receiveCallback = null;
// 主借人类型
var mainBorrowType = "${mainBorrowType}";
// 其他类型
var otherType = "${otherType}";
// 参与客户数据集
var customerInfos = eval('(${customerInfos})');
// 当前点击的付费对象对话框
var currentPayObj = null;
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.loading','zd/switch', 'zd/jquery.zds.message','zd/jquery.zds.dialog',
	'zd/jquery.zds.address','zd/jquery.zds.validate','zd/jquery.zds.combobox',
	'zd/jquery.zds.table', 'zd/jquery.zds.form','zd/jquery.zds.seleter'],
		function ($,CALLBACK,ZTOOlS,Loading,Switch) {
	// 参与方json数据
	var datas = eval('(${joinTypeInfos})');
	// 收费对象类型初始化
	$("input[local='feeObjectType']").each(function(index, ele){
		$(ele).ZCombobox({
		    valueField: "fullCode",
		    textField: "name",
		    width:75,
		    data: datas,
			onSelect:function(value,text,index,data){
				var selectEle = $(ele).parents("td:first").next(":first").find("div[name='feeeObjectNameSelect']");
				if (value == otherType) {
					// 选择了其他
					$(ele).parents("td:first").next(":first").find("input[local='feeObjectId']").val("");
					$(ele).parents("td:first").next(":first").find("input[local='feeeObjectName']").val("");
					$(selectEle).removeClass("btn-gray");
					$(selectEle).addClass("btn-blue");
					return;
				}
				// 选择为空时
				if (value == null || value == "") {
					$(ele).parents("td:first").next(":first").find("input[local='feeObjectId']").val("");
					$(ele).parents("td:first").next(":first").find("input[local='feeeObjectName']").val("");
					$(selectEle).removeClass("btn-blue");
					$(selectEle).addClass("btn-gray");
					return;
				}
				// 遍历参与人设置对应的数据值
				$.each(customerInfos,function(key,name){
						if (key == value) {
							// 赋值
							$(ele).parents("td:first").next(":first").find("input[local='feeObjectId']").val(name.ids);
							$(ele).parents("td:first").next(":first").find("input[local='feeeObjectName']").val(name.names);
							$(selectEle).removeClass("btn-blue");
							$(selectEle).addClass("btn-gray");
						}
				});
			}
		});
		
		// 收费对象，默认为主借人
		var feeObjType = $(ele).ZCombobox("getValue");
		if (feeObjType == null || feeObjType == "" || feeObjType == "null") {
			$(ele).ZCombobox("setValue",mainBorrowType);
		}
	});
	// 付费对象类型初始化
	$("input[local='payObjectType']").each(function(index, ele){
		$(ele).ZCombobox({
		    valueField: "fullCode",
		    textField: "name",
		    width:75,
		    data: datas,
			onSelect:function(value,text,index,data){
				var selectEle = $(ele).parents("td:first").next(":first").find("div[name='payObjectNameSelect']");
				if (value == otherType) {
					// 选择了其他
					$(ele).parents("td:first").next(":first").find("input[local='payObjectId']").val("");
					$(ele).parents("td:first").next(":first").find("input[local='payObjectName']").val("");
					$(selectEle).removeClass("btn-gray");
					$(selectEle).addClass("btn-blue");
					return;
				}
				// 选择为空时
				if (value == null || value == "") {
					// 选择了其他
					$(ele).parents("td:first").next(":first").find("input[local='payObjectId']").val("");
					$(ele).parents("td:first").next(":first").find("input[local='payObjectName']").val("");
					$(selectEle).removeClass("btn-blue");
					$(selectEle).addClass("btn-gray");
					return;
				}
				// 遍历参与人设置对应的数据值
				$.each(customerInfos,function(key,name){
						if (key == value) {
							// 赋值
							$(ele).parents("td:first").next(":first").find("input[local='payObjectId']").val(name.ids);
							$(ele).parents("td:first").next(":first").find("input[local='payObjectName']").val(name.names);
							$(selectEle).removeClass("btn-blue");
							$(selectEle).addClass("btn-gray");
						}
				});
			}
		});
	});
	// 收费对象
	$("#receive_user_selecter").Zseleter({
		title: '选择收费对象',
		btnId: "select",
		width: 900,
		height: 400,
		key: "customerId",
		value: "customerName",
		singleSelect: false,
		type: 'test',
		defSearchForm: {
			test: ""
		},
		columns: {
			test: [[
				{field: 'customerName', title: '姓名', width: 80},
				{field: 'joinTypeName', title: '参与类型', width: 80,formatter : function(rowData,value){
					if (value == null) {
						return "";
					}
					return value;
				}},
				{field: 'customerAge', title: '年龄', width: 80}
			]]
		},
		url:'<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.feeinfomation.findReceiveCustomer" context="admin"/>&caseApplyId=${caseApplyId}',
		type: 'test',
		onBeforeOpen: function () {
			if (receiveSelectBefore != null && typeof(receiveSelectBefore) == 'function') {
				receiveSelectBefore();
			} 
            return true;
        },
		onOk:function(data){
			if (receiveCallback != null && typeof(receiveCallback) == 'function') {
				receiveCallback(data);
			}
		},
		onClose:function(){
			// 处理事件清空
			receiveSelectBefore = null;
			receiveCallback = null;
		}
	});	
	// 选择收费对象
	$("div[name='feeeObjectNameSelect']").each(function(index, ele){
		$(ele).click(function(){
			if ($(ele).hasClass("btn-gray")) {
				// 通过样式控制是否可用
				return;
			}
			// 事前
			receiveSelectBefore = function() {
				$("#receive_user_selecter").Zseleter("clearLabel");
				var arr = [$(ele).prevAll("input[local='feeObjectId']").val(),$(ele).prevAll("input[local='feeeObjectName']").val()];
	            $("#receive_user_selecter").Zseleter("setValue",arr);
			};
			// 回调
			receiveCallback = function(data) {
				var ids = "";
				var names = "";
				for (var i = 0; i < data.length; i++) {
					if (ids.length == 0 ) {
						ids += data[i].customerId;
						names += data[i].customerName;
					} else {
						ids += "," + data[i].customerId;
						names += "," + data[i].customerName;
					}
				}
				// 多个值
				$(ele).prevAll("input[local='feeObjectId']").val(ids);
				$(ele).prevAll("input[local='feeeObjectName']").val(names);
			};
			$("#receive_user_selecter").Zseleter("open");
		});
	});
	// 付费对象选择器对话框初始化
	$("#pay_user_dialog").Zdialog({
		width: 750, 
		height: 550, 
		closed: true, 
		title:'选择付费对象',
		buttons: [{
			id: 'message-btn',
			text: '确认', 
			buttonCls: 'btn-blue',
			handler: function () {
				// 拼装选中的记录
				var ids = "";
				var names = "";
				// 循环获取数据
				$("#tabContent").children("div").each(function(index,ele){
					var talbeObj = $(ele).find(".tableArea");
					if (talbeObj.length > 0) {
						var rows = $(talbeObj).ZTable("getSelections");
						for (var i = 0; i < rows.length; i++) {
							if (ids.length > 0) {
								ids += "," + rows[i].id;
							} else {
								ids += rows[i].id;
							}
							if (names.length > 0) {
								names += "," + rows[i][$(talbeObj).attr("name")];
							} else {
								names += rows[i][$(talbeObj).attr("name")];
							}
						}
					}
				});
				if (currentPayObj != null) {
					$(currentPayObj).prevAll("input[local='payObjectId']").val(ids);
					$(currentPayObj).prevAll("input[local='payObjectName']").val(names);
				}
				$('#pay_user_dialog').Zdialog('close');
			}
		},{
			id: 'cancel-btn',
			text: '取消',
			buttonCls: 'btn-gray',
			handler: function () {
				$('#pay_user_dialog').Zdialog('close');
			}
		}],
		onOpen: function() {
			// 选择第一个tab
			$("#terminal").click();
			$('#terminalSearch').click();
		},
		onClose: function() {
			// 清空付费对象
			currentPayObj = null;
		}
	});
	// 选择付费对象
	$("div[name='payObjectNameSelect']").each(function(index, ele){
		$(ele).click(function(){
			if ($(ele).hasClass("btn-gray")) {
				// 通过样式控制是否可用
				return;
			}
			// 存储当前付费对象
			currentPayObj = ele;
			$("#pay_user_dialog").Zdialog("open");
		});
	});
	
	// 初始化时计算金额
	$("div[name='feeDetailContent']").each(function(index,ele){
		statisticAmount(ele);
	});
	// 计算各个板块的费用合集
	function statisticAmount(divTarget) {
		$(divTarget).each(function(index,ele){
			var expectedAmount = 0.00;
			var receivedAmount = 0.00;
			var expectedPayableAmount = 0.00;
			var paidAmount = 0.00;
			// 应收合计
			$(ele).find("input[local='expectedAmount']").each(function(index,eleInput){
				var amount = $(eleInput).val();
				if (!isNaN(amount)) {
					expectedAmount += Number(amount);
				}
			});
			// 实收合计
			$(ele).find("input[local='receivedAmount']").each(function(index,eleInput){
				var amount = $(eleInput).val();
				if (!isNaN(amount)) {
					receivedAmount += Number(amount);
				}
			});
			// 应付合计
			$(ele).find("input[local='expectedPayableAmount']").each(function(index,eleInput){
				var amount = $(eleInput).val();
				if (!isNaN(amount)) {
					expectedPayableAmount += Number(amount);
				}
			});
			// 实付合计
			$(ele).find("input[local='paidAmount']").each(function(index,eleInput){
				var amount = $(eleInput).val();
				if (!isNaN(amount)) {
					paidAmount += Number(amount);
				}
			});
			// 应收合计
			$(ele).find("span[name='totalShouldReceiveSpan']").text(Number(expectedAmount).toFixed(2));
			// 实收合计
			$(ele).find("span[name='totalTrueReceiveSpan']").text(Number(receivedAmount).toFixed(2));
			// 应付合计
			$(ele).find("span[name='totalShouldPaySpan']").text(Number(expectedPayableAmount).toFixed(2));
			// 实付合计
			$(ele).find("span[name='totalTruePaySpan']").text(Number(paidAmount).toFixed(2));
		});
	}
	
	/////////////////////// -----------------------------------------  以下为付费对象选择器 START ---------------------------------//////////////////////////
	// 评估机构
	$("#evaluation").click(function(){
		$("#terminal").removeClass("tabs-on");
		$("#evaluation").addClass("tabs-on");
		$("#otherCooperater").removeClass("tabs-on");
		$("#terminalInfo").addClass("hide");
		$("#evaluationInfo").removeClass("hide");
		$("#otherCooperaterInfo").addClass("hide");
		$('#evaluationSearch').click();
	});
	// 评估机构
	var evaluation_list_url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.feeinfomation.findEvaluationInfos" context="admin"/>';
	$('#evaluation-table').ZTable({
	    url : evaluation_list_url,
        singleSelect : false,
        isRowNum : true,
        pagination : true,
        currentPage : 1,
        tableCls : 'table-index',//table的样式
        toolbar : [],
        columns:[[
     	  	{field : 'companyName',title : '评估公司名称',width : 40},
			{field : 'companyTypeName',title : '类别',width : 120}/* ,
			{field : 'contactName',title : '地区',width : 120} */
		]]
   	});
	// 评估机构查询
	$('#evaluationSearch').click(function(){
		var formArray=$("#evaluation_search").serializeArray();
		$('#evaluation-table').ZTable("reload",formArray);
	});
	//初始地址选择器
    /* $("#selectAddress_evaluation").Address({
    	showStreet:false,//不显示街道
        cityUrl:cityUrl,//真实数据源
        getDataCityUrl:getDataCityUrl,//根据子节点取同级及上级的数据
        callback:function(infos,selected_ids) {
            var str = '';
            for(var i=0;i<infos.length;i++) {
                if(str==""){
                    str = str+infos[i];
                }else{
                    str = str+" / "+infos[i];
                }
            }
            $('#address_evaluation_text').val(str);
            $('#evaluationProvince').val(selected_ids[0]);
            $('#evaluationCity').val(selected_ids[1]);
            $('#evaluationDistrict').val(selected_ids[2]);
        }
    }); */
	// 终端
	$("#terminal").click(function(){
		$("#terminal").addClass("tabs-on");
		$("#evaluation").removeClass("tabs-on");
		$("#otherCooperater").removeClass("tabs-on");
		$("#terminalInfo").removeClass("hide");
		$("#evaluationInfo").addClass("hide");
		$("#otherCooperaterInfo").addClass("hide");
		$('#terminalSearch').click();
	});
	// 终端设备
	var terminal_list_url = '<z:ukey key="com.zdsoft.finance.cooperator.getCooperator" context="admin"/>&jsoncallback=?';
	$('#terminal-table').ZTable({
	    url : terminal_list_url,
        singleSelect : false,
        isRowNum : true,
        pagination : true,
        currentPage : 1,
        tableCls : 'table-index',//table的样式
        toolbar : [],
        columns:[[
     	  	{field : 'terminalFullName',title : '终端名称',width : 40},
			{field : 'terminalTypeName',title : '终端类别',width : 120},
			{field : 'contactName',title : '主要联系人',width : 120}
		]]
   	});
	// 终端设备查询
	$('#terminalSearch').click(function(){
		var formArray=$("#terminal_search").serializeArray();
		$('#terminal-table').ZTable("reload",formArray);
	});
	// 其他合作机构
	$("#otherCooperater").click(function(){
		$("#terminal").removeClass("tabs-on");
		$("#evaluation").removeClass("tabs-on");
		$("#otherCooperater").addClass("tabs-on");
		$("#terminalInfo").addClass("hide");
		$("#evaluationInfo").addClass("hide");
		$("#otherCooperaterInfo").removeClass("hide");
		$('#otherCooperaterSearch').click();
	});
	// 其他合作机构 
	var otherCooperater_list_url = '<z:ukey key="com.zdsoft.finance.otherCooperater.getOtherCooperater" context="admin"/>&jsoncallback=?';
	$('#otherCooperater-table').ZTable({
	    url : otherCooperater_list_url,
        singleSelect : false,
        isRowNum : true,
        pagination : true,
        currentPage : 1,
        tableCls : 'table-index',//table的样式
        toolbar : [],
        columns:[[
     	  	{field : 'contactCompanyName',title : '合作单位名称',width : 40},
			{field : 'type',title : '类别',width : 120},
			{field : 'clientNm',title : '地址',width : 120}
		]]
   	});
	// 其他合作机构 
	$('#otherCooperaterSearch').click(function(){
		var formArray=$("#otherCooperater_search").serializeArray();
		$('#otherCooperater-table').ZTable("reload",formArray);
	});
	//初始地址选择器
    $("#selectAddress_otherCoop").Address({
    	showStreet:false,//不显示街道
        cityUrl:cityUrl,//真实数据源
        getDataCityUrl:getDataCityUrl,//根据子节点取同级及上级的数据
        callback:function(infos,selected_ids) {
            var str = '';
            for(var i=0;i<infos.length;i++) {
                if(str==""){
                    str = str+infos[i];
                }else{
                    str = str+" / "+infos[i];
                }
            }
            $('#address_otherCoop_text').val(str);
            $('#detailedProvince').val(selected_ids[0]);
            $('#detailedCity').val(selected_ids[1]);
            $('#detailedDistrict').val(selected_ids[2]);
        }
    });
	/////////////////////// -----------------------------------------  付费对象选择器 END  ---------------------------------//////////////////////////
    /*手风琴效果*/
    window.showOrHideDetail=function(that,target){
        if($(that).hasClass('icon-open-bot')){
            $(that).removeClass('icon-open-bot').addClass('icon-open-top');
        }else{
            $(that).removeClass('icon-open-top').addClass('icon-open-bot');
        }
        $(target).toggle();
    };

	$.ZUI.init();
	// 应收联动
	$("input[local='expectedAmount']").each(function(index,ele){
		$(ele).keyup(function(){
			statisticAmount($(ele).parents("div[name='feeDetailContent']"));
		});
	}); 
	// 应付联动
	$("input[local='expectedPayableAmount']").each(function(index,ele){
		$(ele).keyup(function(){
			statisticAmount($(ele).parents("div[name='feeDetailContent']"));
		});
	});
	// 校验页面数据是否正确
	function validateFeeInfo() {
		// 录入字段均保证有值
		var validate = $.ZUI.validateForm($('#feeInfoForm'));
		if (!validate) {
			$.ZMessage.warning("警告", "您有未录入或有误的信息，请修正！");
            return false;
        }
		return true;
	}
	
	// 保存数据
	var uri_save_fee_info = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.feeinfomation.save" context="admin"/>&caseApplyId=${caseApplyId}';
	window.saveData = function() {
		// 返回状态值
		var status = false;
		//验证数据
        if (validateFeeInfo()) {
            var params = $('#feeInfoForm').serialize();
               $.ajax({
                   url: uri_save_fee_info,
                   type: "post",
                   dataType: "json",
                   data: params,
                   async: false,
                   success: function (msg) {
                       if (msg != null) {
                           if (msg.resultStatus == "0") {
                               //将相关数据回写用于保存方法
                                $.ZMessage.success("成功", "保存成功!");
                                status = true;
                           } else {
//                                $.ZMessage.warning("错误", "保存失败！" + msg.msg);
                           }
                       } else {
//                            $.ZMessage.warning("错误", "保存失败");
                       }
                   }
               });
        }
		return status;
	}
});
</script>