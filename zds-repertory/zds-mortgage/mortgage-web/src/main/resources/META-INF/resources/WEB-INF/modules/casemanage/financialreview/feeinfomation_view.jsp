<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<div class="frm-content" id="feeInfoDiv">
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
				<table class="table-index scroll" id="table_content_${status.index}">
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
			                        	<span class="f12">${feeInfo.feeObjectTypeName }</span>
			                        </label>
			                    </dd>
			                </td>
			                <!-- 收费对象 -->
							<td>
								<dl class="form-item form-auto">
								<dd class="detail">
			                        <label>
			                        	<span class="f12">${feeInfo.feeeObjectName }</span>
			                        </label>
			                    </dd>
			                    </dl>
							</td>
							<!-- 预计应收 -->
							<td>
								<dl class="form-item form-auto">
								<dd class="detail">
			                        <label>
			                        	<span class="f12" local="expectedAmount">${empty feeInfo.expectedAmount ? 0.00 : feeInfo.expectedAmount }</span>
			                        </label>
			                    </dd>
			                    </dl>
							</td>
							<!-- 实收 -->
							<td>
								<dl class="form-item form-auto">
								<dd class="detail">
			                        <label>
			                        	<span class="f12" local="receivedAmount">${empty feeInfo.receivedAmount ? 0.00 : feeInfo.receivedAmount }</span>
			                        </label>
			                    </dd>
			                    </dl>
							</td>
							<!-- 付费对象类型 -->
							<td>
								<dl class="form-item form-auto">
								<dd class="detail">
			                        <label>
			                        	<span class="f12">${feeInfo.payObjectTypeName }</span>
			                        </label>
			                    </dd>
			                    </dl>
			                </td>
			                <!-- 付费对象 -->
							<td>
								<dl class="form-item form-auto">
								<dd class="detail">
			                        <label>
			                        	<span class="f12">${feeInfo.payObjectName }</span>
			                        </label>
			                    </dd>
			                    </dl>
							</td>
							<!-- 预计应付 -->
							<td>
								<dl class="form-item form-auto">
								<dd class="detail">
			                        <label>
			                        	<span class="f12" local="expectedPayableAmount">${empty feeInfo.expectedPayableAmount ? 0.00 : feeInfo.expectedPayableAmount}</span>
			                        </label>
			                    </dd>
			                    </dl>
							</td>
							<!-- 实付 -->
							<td>
								<dl class="form-item form-auto">
								<dd class="detail">
			                        <label>
			                        	<span class="f12" local="paidAmount">${empty feeInfo.paidAmount ? 0.00 : feeInfo.paidAmount}</span>
			                        </label>
			                    </dd>
			                    </dl>
							</td>
							<!-- 结余 -->
							<td>
								<dl class="form-item form-auto">
								<dd class="detail">
			                        <label>	
			                        	<span class="f12">${empty feeInfo.balanceAmount ? 0.00 : feeInfo.balanceAmount }</span>
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
<!-- JS 代码块 -->
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.loading','zd/jquery.zds.message',
	'zd/jquery.zds.validate','zd/jquery.zds.combobox',
	'zd/jquery.zds.table', 'zd/jquery.zds.form'],
		function ($,CALLBACK,ZTOOlS,Loading) {
	
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
			$(ele).find("span[local='expectedAmount']").each(function(index,eleInput){
				var amount = $(eleInput).text();
				if (!isNaN(amount)) {
					expectedAmount += Number(amount);
				}
			});
			// 实收合计
			$(ele).find("span[local='receivedAmount']").each(function(index,eleInput){
				var amount = $(eleInput).text();
				if (!isNaN(amount)) {
					receivedAmount += Number(amount);
				}
			});
			// 应付合计
			$(ele).find("span[local='expectedPayableAmount']").each(function(index,eleInput){
				var amount = $(eleInput).text();
				if (!isNaN(amount)) {
					expectedPayableAmount += Number(amount);
				}
			});
			// 实付合计
			$(ele).find("span[local='paidAmount']").each(function(index,eleInput){
				var amount = $(eleInput).text();
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
});
</script>
