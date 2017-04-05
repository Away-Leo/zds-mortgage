<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ include file='../../common/common_busi_selector.jsp' %>
<div class="frm-content" id="feeInfoDiv">
	<c:if test="${not empty hasResultMap and not hasResultMap }">无对应的费用配置信息！</c:if>
	<form id="feeInfoForm" class="zui-form " method="post" >
		<c:forEach items="${feeInfoMap}" var="listInfo" varStatus="status" >
		<div class="page-box">
			<div class="page-title" >${listInfo.key }</div>
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
	            <div>
				<table class="table-index scroll " id="table_content_${status.index}">
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
			                    </dl>
			                </td>
			                <!-- 收费对象 -->
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
				                        <label class="word">
				                        	<input type="hidden" name="feeInfos[${feeInfo.serialNum }].feeObjectId" local="feeObjectId" value="${feeInfo.feeObjectId }" />
				                            <input 
				                                   class="zui-input zui-validatebox zui-disabled nwidth2"
				                                   type="text"
				                                   data-toggle="validate"
				                                   validate-type="Require,Length[0-256]" name="feeInfos[${feeInfo.serialNum }].feeeObjectName" local="feeeObjectName" readonly="readonly" value="${feeInfo.feeeObjectName }" />
				                            <div name="feeeObjectNameSelect" class="btn-gray" style="cursor: pointer">选择</div>
				                        </label>
				                    </dd>
			                    </dl>
							</td>
							<!-- 预计应收 -->
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
				                        <label>
				                        	<input class="zui-input zui-validatebox nwidth2"
				                                   type="text"
				                                   validate-type="Require,Digital[13-6]" name="feeInfos[${feeInfo.serialNum }].expectedAmount" local="expectedAmount" value="${feeInfo.expectedAmount }" />
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
				                        	<span name="receivedAmountSpan" class="f12 amountSpan" >${empty feeInfo.receivedAmount ? 0.00 : feeInfo.receivedAmount }</span>
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
				                        <label class="word">
				                        	<input type="hidden" name="feeInfos[${feeInfo.serialNum }].payObjectId" local="payObjectId" value="${feeInfo.payObjectId }" />
				                            <input 
				                                   class="zui-input zui-validatebox zui-disabled nwidth2"
				                                   type="text"
				                                   data-toggle="validate"
				                                   validate-type="Require,Length[0-256]" name="feeInfos[${feeInfo.serialNum }].payObjectName" local="payObjectName" readonly="readonly" value="${feeInfo.payObjectName }" />
				                            <div name="payObjectNameSelect" class="btn-gray" style="cursor: pointer">选择</div>
				                        </label>
				                    </dd>
			                    </dl>
							</td>
							<!-- 预计应付 -->
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
				                        <label>
				                            <input class="zui-input zui-validatebox nwidth2"
				                                   type="text"
				                                   data-toggle="validate"
				                                   validate-type="Require,Digital[13-6]" name="feeInfos[${feeInfo.serialNum }].expectedPayableAmount" local="expectedPayableAmount" value="${feeInfo.expectedPayableAmount }" />
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
				                        	<span name="paidAmountSpan" class="f12 amountSpan" >${empty feeInfo.paidAmount ? 0.00 : feeInfo.paidAmount }</span>
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
				                        	<span name="balanceAmountSpan" class="f12 amountSpan" >${empty feeInfo.balanceAmount ? 0.00 : feeInfo.balanceAmount  }</span>
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
		</div>
		</c:forEach>
	</form>
</div>
<!-- JS 代码块 -->
<script type="text/javascript">
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
			function ($,CALLBACK,ZTools,Loading,Switch) {
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
					} else {
						$(selectEle).removeClass("btn-blue");
						$(selectEle).addClass("btn-gray");
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
								return false;
							} else {
								$(ele).parents("td:first").next(":first").find("input[local='feeObjectId']").val("");
								$(ele).parents("td:first").next(":first").find("input[local='feeeObjectName']").val("");
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
					} else {
						$(selectEle).removeClass("btn-blue");
						$(selectEle).addClass("btn-gray");
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
								return false;
							} else {
								$(ele).parents("td:first").next(":first").find("input[local='payObjectId']").val("");
								$(ele).parents("td:first").next(":first").find("input[local='payObjectName']").val("");
							}
					});
				}
			});
		});
		// 收费对象
		BUSI_SELECTOR_CUSTOMER.init(caseApplyId);
		// 选择收费对象
		$("div[name='feeeObjectNameSelect']").each(function(index, ele){
			$(ele).click(function(){
				if ($(ele).hasClass("btn-gray")) {
					// 通过样式控制是否可用
					return;
				}
				// 事前
				BUSI_SELECTOR_CUSTOMER.onBeforeOpen = function() {
					BUSI_SELECTOR_CUSTOMER.getObj().Zseleter("clearLabel");
					var arr = [$(ele).prevAll("input[local='feeObjectId']").val(),$(ele).prevAll("input[local='feeeObjectName']").val()];
		            BUSI_SELECTOR_CUSTOMER.getObj().Zseleter("setValue",arr);
				};
				// 回调
				BUSI_SELECTOR_CUSTOMER.onOk = function(data) {
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
				BUSI_SELECTOR_CUSTOMER.open();
			});
		});
		// 付费对象选择器对话框初始化
		BUSI_SELECTOR_COOPER.init();
		BUSI_SELECTOR_COOPER.onOk = function(rows) {
			// 拼装选中的记录
			var ids = "";
			var names = "";
			// 循环获取数据
			for (var i = 0; i < rows.length; i++) {
				if (ids.length > 0) {
					ids += "," + rows[i].id;
				} else {
					ids += rows[i].id;
				}
				if (names.length > 0) {
					names += "," + rows[i].name;
				} else {
					names += rows[i].name;
				}
			}
			if (currentPayObj != null) {
				$(currentPayObj).prevAll("input[local='payObjectId']").val(ids);
				$(currentPayObj).prevAll("input[local='payObjectName']").val(names);
			}
		}
		// 选择付费对象
		$("div[name='payObjectNameSelect']").each(function(index, ele){
			$(ele).click(function(){
				if ($(ele).hasClass("btn-gray")) {
					// 通过样式控制是否可用
					return;
				}
				// 存储当前付费对象
				currentPayObj = ele;
				BUSI_SELECTOR_COOPER.open();
			});
		});
		
		// 初始化时计算金额
		$("div[name='feeDetailContent']").each(function(index,ele){
			statisticAmount(ele);
		});
		// 初始化数据格式化
		$(".amountSpan").each(function(index,ele){
			$(ele).text(ZTools.formatCurrency(Number($(ele).text()).toFixed(2)+""));
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
				$(ele).find("span[name='totalShouldReceiveSpan']").text(ZTools.formatCurrency(Number(expectedAmount).toFixed(2)+""));
				// 实收合计
				$(ele).find("span[name='totalTrueReceiveSpan']").text(ZTools.formatCurrency(Number(receivedAmount).toFixed(2)+""));
				// 应付合计
				$(ele).find("span[name='totalShouldPaySpan']").text(ZTools.formatCurrency(Number(expectedPayableAmount).toFixed(2)+""));
				// 实付合计
				$(ele).find("span[name='totalTruePaySpan']").text(ZTools.formatCurrency(Number(paidAmount).toFixed(2)+""));
			});
		}
	    /*手风琴效果*/
	    window.showOrHideDetail=function(that,target){
	        if($(that).hasClass('icon-open-bot')){
	            $(that).removeClass('icon-open-bot').addClass('icon-open-top');
	        }else{
	            $(that).removeClass('icon-open-top').addClass('icon-open-bot');
	        }
	        $(target).toggle();
	    };
	
		$.ZUI.init("#feeInfoDiv");
		// 应收联动
		$("input[local='expectedAmount']").each(function(index,ele){
			$(ele).keyup(function(){
				statisticAmount($(ele).parents("div[name='feeDetailContent']"));
				var value = $(ele).val();
				// 输入为0，则对应收费对象和收费对象类型，添加非必须
				if (value == 0) {
					var nameObj = $(ele).parents("tr:first").find("input[local='feeeObjectName']");
					$(nameObj).attr("validate-type","Length[0-256]");
					$(nameObj).parents("td:first").find('span[class*="zd-validation"]').remove();
					$(nameObj).parents("td:first").find('.error').removeClass('error');
					var typeObj = $(ele).parents("tr:first").find("input[local='feeObjectType']");
					$(typeObj).attr("validate-type","Length[0-64]");
					$(typeObj).parents("td:first").find('span[class*="zd-validation"]').remove();
					$(typeObj).parents("td:first").find('.error').removeClass('error');
				} else {
					$(ele).parents("tr:first").find("input[local='feeeObjectName']").attr("validate-type","Require,Length[0-256]");
					$(ele).parents("tr:first").find("input[local='feeObjectType']").attr("validate-type","Require,Length[0-64]");
				}
			});
			// 初始化处理
			var valueFlg = $(ele).val();
			// 输入为0，则对应收费对象和收费对象类型，添加非必须
			if (valueFlg == 0) {
				var nameObj = $(ele).parents("tr:first").find("input[local='feeeObjectName']");
				$(nameObj).attr("validate-type","Length[0-256]");
				$(nameObj).parents("td:first").find('span[class*="zd-validation"]').remove();
				$(nameObj).parents("td:first").find('.error').removeClass('error');
				var typeObj = $(ele).parents("tr:first").find("input[local='feeObjectType']");
				$(typeObj).attr("validate-type","Length[0-64]");
				$(typeObj).parents("td:first").find('span[class*="zd-validation"]').remove();
				$(typeObj).parents("td:first").find('.error').removeClass('error');
			} else {
				$(ele).parents("tr:first").find("input[local='feeeObjectName']").attr("validate-type","Require,Length[0-256]");
				$(ele).parents("tr:first").find("input[local='feeObjectType']").attr("validate-type","Require,Length[0-64]");
			}
		}); 
		// 应付联动
		$("input[local='expectedPayableAmount']").each(function(index,ele){
			$(ele).keyup(function(){
				statisticAmount($(ele).parents("div[name='feeDetailContent']"));
				// 输入为0，则对应收费对象和收费对象类型，添加非必须
				var value = $(ele).val();
				if (value == 0) {
					var nameObj = $(ele).parents("tr:first").find("input[local='payObjectName']");
					$(nameObj).attr("validate-type","Length[0-256]");
					$(nameObj).parents("td:first").find('span[class*="zd-validation"]').remove();
					$(nameObj).parents("td:first").find('.error').removeClass('error');
					var typeObj = $(ele).parents("tr:first").find("input[local='payObjectType']");
					$(typeObj).attr("validate-type","Length[0-64]");
					$(typeObj).parents("td:first").find('span[class*="zd-validation"]').remove();
					$(typeObj).parents("td:first").find('.error').removeClass('error');
				} else {
					$(ele).parents("tr:first").find("input[local='payObjectName']").attr("validate-type","Require,Length[0-256]");
					$(ele).parents("tr:first").find("input[local='payObjectType']").attr("validate-type","Require,Length[0-64]");
				}
			});
			// 初始化处理
			var valueFlg = $(ele).val();
			if (valueFlg == 0) {
				var nameObj = $(ele).parents("tr:first").find("input[local='payObjectName']");
				$(nameObj).attr("validate-type","Length[0-256]");
				$(nameObj).parents("td:first").find('span[class*="zd-validation"]').remove();
				$(nameObj).parents("td:first").find('.error').removeClass('error');
				var typeObj = $(ele).parents("tr:first").find("input[local='payObjectType']");
				$(typeObj).attr("validate-type","Length[0-64]");
				$(typeObj).parents("td:first").find('span[class*="zd-validation"]').remove();
				$(typeObj).parents("td:first").find('.error').removeClass('error');
			} else {
				$(ele).parents("tr:first").find("input[local='payObjectName']").attr("validate-type","Require,Length[0-256]");
				$(ele).parents("tr:first").find("input[local='payObjectType']").attr("validate-type","Require,Length[0-64]");
			}
		});
});
</script>