<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="busi_selector_div">
	<!-- 案件参与对象选择器 -->
	<div id="busi_selector_customer" style="display: none;"></div>
	<!-- 合作方选择器 -->
	<div id="busi_selector_cooper_dialog" style="display: none;">
		<div class="p10">
			<div class="info-tab">
				<div class="info-tab">
	                <ul class="tabs">
	                    <li id="terminal_tab_btn" class="tabs-on" formatter="terminalTab"><a href="javascript:void(0);">终端</a></li>
	                    <li formatter="evaluationTab"><a href="javascript:void(0);">评估公司</a></li>
	                    <li formatter="otherCooperTab"><a href="javascript:void(0);">其他合作单位</a></li>
	                </ul>
	                <div class="tabcontents">
	                    <div id="terminalInfo" name="terminalFullName" cooperType="TERMINAL">
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
						                 <input class="zui-combotree zui-validatebox" id="terminalType" name="terminalType|E|S"
				                               type="hidden"
				                               data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00103"
				                               data-valuefield="fullcode" data-callback="reloadMeetingProject" data-textfield="name">
						                </dd>
						            </dl>
						        </form>
					            <div class="form-btn">
						         	<a href="javaScript:void(0)" class="btn-blue queryBtn">查询</a>
					   		    </div>
						    </div>
						    <div class="p10" id="terminal-table-div">
								<div id="terminal-table" name="terminalFullName" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.cooperator.getCooperator" context="admin"/>&jsoncallback=?","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index"}'>
									<table>
					        			<tr>
					            			<th data-options="field:terminalFullName">终端名称</th>
					            			<th data-options="field:terminalTypeName">终端类别</th>
								        </tr>
									</table>
								</div>
							</div>
	                    </div>
	                    <div id="evaluationInfo" class="hide"  name="companyName" cooperType="EVALUATION">
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
						        </form>
					            <div class="form-btn">
						         	<a href="javaScript:void(0)" class="btn-blue queryBtn">查询</a>
					   		    </div>
						    </div>
						    <div class="p10" id="evaluation-table-div">
								<div id="evaluation-table" name="companyName" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.feeinfomation.findEvaluationInfos" context="admin"/>","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index"}'>
									<table>
					        			<tr>
					            			<th data-options="field:companyName">评估公司名称</th>
					            			<th data-options="field:companyTypeName">类别</th>
								        </tr>
									</table>
								</div>
							</div>
	                    </div>
	                    <div id="otherCooperaterInfo" class="hide" name="companyName" cooperType="OTHER">
	                        <div id="otherCooperaterSearchForm" class="p5">
						        <form id="otherCooperater_search" class="zui-form" action="javascript:void(0);" zdata-options="{}">
						            <dl class="form-item">
						                <dt class="title">合作单位名称</dt>
						                <dd class="detail">
						                    <label>
						                        <input class="zui-input" type="text"  name="companyName|LK|S">
						                    </label>
						                </dd>
						            </dl>
									<!-- <dl class="form-item">
						                <dt class="title">地址</dt>
						                <dd class="detail">
						                   <input type="hidden" id="detailedProvince" name="detailedProvince|E|S" />
				                           <input type="hidden" id="detailedCity" name="detailedCity|E|S"/>
				                           <input type="hidden" id="detailedDistrict" name="detailedDistrict|E|S"/>
				                           <div id="selectAddress_otherCoop">
				                               <input id="address_otherCoop_text" class="zui-input zui-validatebox" type="text" readonly="true" style="width: 260px;"/>
				                           </div>
						                </dd>
						            </dl> -->
						        </form>
					            <div class="form-btn">
					            	<a href="javaScript:void(0)" class="btn-blue queryBtn">查询</a>
					   		    </div>
						    </div>
						    <div class="p10" id="otherCooperater-table-div">
								<div id="otherCooperater-table" name="companyName" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.otherCooperater.getOtherCooperater" context="admin"/>&jsoncallback=?","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index"}'>
									<table>
					        			<tr>
					            			<th data-options="field:companyName">合作单位名称</th>
					            			<th data-options="field:companyTypeName">类别</th>
					            			<!-- <th data-options="field:clientNm">地址</th> -->
								        </tr>
									</table>
								</div>
							</div>
	                    </div>
	                </div>
	            </div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
// 参与对象选择器
var BUSI_SELECTOR_CUSTOMER = new Object();
// 合作方选择器
var BUSI_SELECTOR_COOPER = new Object();
// 合作方选择器对象
var currentCooperInfo = null;
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/switch', 'zd/jquery.zds.dialog',
	'zd/jquery.zds.address','zd/jquery.zds.table', 'zd/jquery.zds.form','zd/jquery.zds.seleter'],
		function ($,CALLBACK,Switch) {
	
	//////////////////////////////////////////案件参与方  START ///////////////////////////////////////////////////
	// 初始化
	BUSI_SELECTOR_CUSTOMER.init = function(caseApplyId) {
		// 收费对象
		$("#busi_selector_customer").Zseleter({
			title: '案件客户选择',
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
					{field: 'customerAge', title: '年龄', width: 80,formatter : function(rowData,value){
						if (value == null) {
							return "";
						}
						return value;
					}}
				]]
			},
			url:'<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.feeinfomation.findReceiveCustomer" context="admin"/>&caseApplyId=' + caseApplyId,
			type: 'test',
			onBeforeOpen: function () {
				if (BUSI_SELECTOR_CUSTOMER.onBeforeOpen != null && typeof(BUSI_SELECTOR_CUSTOMER.onBeforeOpen) == 'function') {
					BUSI_SELECTOR_CUSTOMER.onBeforeOpen();
				} 
	            return true;
	        },
			onOk:function(data){
				if (BUSI_SELECTOR_CUSTOMER.onOk != null && typeof(BUSI_SELECTOR_CUSTOMER.onOk) == 'function') {
					BUSI_SELECTOR_CUSTOMER.onOk(data);
				}
			},
			onClose:function(){
				// 处理事件清空
				BUSI_SELECTOR_CUSTOMER.onBeforeOpen = null;
				BUSI_SELECTOR_CUSTOMER.onOk = null;
			}
		});	
		$.ZUI.initDiv("busi_selector_customer");
	}
	// 获得选择器对象
	BUSI_SELECTOR_CUSTOMER.getObj = function() {
		return $("#busi_selector_customer");
	}
	// 打开选择器
	BUSI_SELECTOR_CUSTOMER.open = function() {
		$("#busi_selector_customer").Zseleter("open");
	}
	//////////////////////////////////////////案件参与方  END ///////////////////////////////////////////////////
	////////////////////////////////////////// 合作方选择器 START ///////////////////////////////////////////////////
	// 初始化
	BUSI_SELECTOR_COOPER.init = function() {
		// 查询方法
		CALLBACK.queryInfos = function () {
			if (currentCooperInfo != null) {
				var formArray = $(currentCooperInfo).find(".zui-form").serializeArray();
				$(currentCooperInfo).find(".zui-datagrid").ZTable("reload",formArray);
			}
	    }
		
		// 终端回调
		CALLBACK.terminalTab = function () {
			currentCooperInfo = $("#terminalInfo");
			CALLBACK.queryInfos();
	    }
		// 评估公司回调
		CALLBACK.evaluationTab = function () {
			currentCooperInfo = $("#evaluationInfo");
			CALLBACK.queryInfos();
	    }
		// 其他合作机构回调
		CALLBACK.otherCooperTab = function () {
			currentCooperInfo = $("#otherCooperaterInfo");
			CALLBACK.queryInfos();
	    }
		// 对话框初始化
	    $("#busi_selector_cooper_dialog").Zdialog({
			width: 800, 
			height: 500, 
			closed: true, 
			title:'合作方选择',
			buttons: [{
				id: 'message-btn',
				text: '确认', 
				buttonCls: 'btn-blue',
				handler: function () {
					// 拼装选中的记录
					if (currentCooperInfo != null) {
						// 有选择
						var rows = $(currentCooperInfo).find(".zui-datagrid").ZTable("getSelections");
						if (BUSI_SELECTOR_COOPER.onOk != null && typeof(BUSI_SELECTOR_COOPER.onOk) == 'function') {
							var rtnRows = [];
							for (var i = 0; i < rows.length; i++) {
								// 循环封装数据
								var rowData = {};
								rowData.id = rows[i].id;
								rowData.name = rows[i][$(currentCooperInfo).attr("name")];
								rowData.type = $(currentCooperInfo).attr("cooperType");
								rowData.data = rows[i];
								rtnRows[i] = rowData;
							}
							BUSI_SELECTOR_COOPER.onOk(rtnRows);
						}
					} else {
						BUSI_SELECTOR_COOPER.onOk([]);
					}
					$('#busi_selector_cooper_dialog').Zdialog('close');
				}
			},{
				id: 'cancel-btn',
				text: '取消',
				buttonCls: 'btn-gray',
				handler: function () {
					$('#busi_selector_cooper_dialog').Zdialog('close');
				}
			}],
			onOpen: function() {
				// 选择第一个tab
				$("#terminal_tab_btn").click();
			},
			onClose: function() {
				// 选择重置
				currentCooperInfo = null;
			}
		});
	    $("#terminalType").ZComboTree();
	  	//初始地址选择器
	   /*  $("#selectAddress_otherCoop").Address({
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
	    }); */
		$.ZUI.initDiv("#busi_selector_cooper_dialog");
		$.ZUI.initGrid("#terminal-table-div");
		$.ZUI.initGrid("#evaluation-table-div");
		$.ZUI.initGrid("#otherCooperater-table-div");
		// 查询点击
		$('.queryBtn').each(function(index,ele){
			$(ele).click(function(){
				CALLBACK['queryInfos']();
			});
		});
	}
	// 打开
	BUSI_SELECTOR_COOPER.open = function() {
		$("#busi_selector_cooper_dialog").Zdialog("open");
	}
	//////////////////////////////////////////合作方选择器 END ///////////////////////////////////////////////////
});
</script>
