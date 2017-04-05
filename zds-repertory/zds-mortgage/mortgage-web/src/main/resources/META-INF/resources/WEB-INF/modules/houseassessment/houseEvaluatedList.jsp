<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<!-- 列表区域 -->
<div id="houseEvaluatedDiv" class="page-box">
	<div class="page-title">房产评估信息</div>
	<div class="p10">
		<table data-type="first" class="scroll table-index">
                <thead class="datagrid-header">
                    <tr>
                    	<th>评估公司</th>
						<th>评估价格(元)</th>
						<th>评估时间</th>
                    </tr>
                </thead>
                <c:if test="${not empty resultVoList}">
                <tbody class="datagrid-body">
                	<c:forEach items="${resultVoList}" var="voInfo">
                		<tr>
                			<td>${voInfo.evaluateCompany }</td>
                        	<td><span class="f12 amountSpan">${voInfo.evaluatePrice }</span></td>
                        	<td>
								<c:if test="${voInfo.onlineFlg eq 0 }">
									${voInfo.resultTimeStr}
								</c:if>
								<c:if test="${voInfo.onlineFlg eq 1 }">
									${voInfo.createTimeStr}
								</c:if>
							</td>
                        </tr>
            		</c:forEach>
                </tbody>
           		</c:if>
            </table>
		<div id="houseEvaluatedDiv"></div>
	</div>
</div>
<script type="text/javascript">
seajs.use(['jquery','zd/tools','zd/jquery.zds.table'], 
	 	function ($,ZTools) {
	
		// 初始化数据格式化
		$(".amountSpan").each(function(index,ele){
			$(ele).text(ZTools.formatCurrency($(ele).text()+""));
		});
	/* $('#houseEvaluatedDiv').ZTable({
	       url : '<z:ukey key="com.zdsoft.finance.houseassessment.houseEvaluate.listHouseEvaluatedRecord" context="admin"/>&housePropertyId=${housePropertyId}',
	       singleSelect : true,
	       isRowNum : false,
	       pagination : false,
	       idField: "id",
	       tableCls : 'table-index',//table的样式
	       columns:[[
				{field : 'evaluateCompany',title : '评估公司',align : 'center'},
				{field : 'evaluatePrice',title : '评估价格', align : 'center'},
				{field : 'evaluatedTime',title : '评估时间', align : 'center'}
			] ],
			onLoadSuccess:function() {
			}
		}); */
	$.ZUI.initDiv('#houseEvaluatedDiv');
	//初始化页面
});
</script>