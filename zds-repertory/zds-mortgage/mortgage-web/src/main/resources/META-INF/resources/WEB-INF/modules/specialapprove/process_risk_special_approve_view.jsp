<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../common/common_js.jsp'%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>风险特批审批</title>
</head>
<body>
	<!-- 引入案件基本信息 -->
	<%@ include file="public_case_apply_basic_info.jsp" %>
	<!-- 引入押品信息 -->
	<%@ include file="public_collateral_info.jsp" %>
	
	<!-- 特批风险特项 -->
	<div class="page-box">
		<h1 class="page-title">特批风险特项</h1>
		<div class="p5" id="bbb">
			<table class="table-detail" id="specialApproveE">
			</table>
		</div>
	</div>
	
	<!-- 备注 -->
	<div class="page-box">
		<h1 class="page-title">备注</h1>
		<div class="p5">
			<table class="table-detail">
				<tr>
					<td class="td-title pct10">申请的具体原因及内容(请详细列明)</td>
					<td colspan="5">
                         <label>
                       		 <textarea id="remark" class="zui-area row-width zui-validatebox"
                       		 placeholder="最多可以输入512个字符" disabled="disabled">${remark }</textarea>
                         </label>
					</td>
				</tr>		
			</table>
		</div>
	</div>
	
	<!-- 引入附件 -->
	<dvi class="page-box">
		<div id="attachment"></div>
	</dvi>
</body>

<script type="text/javascript">
	seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.form','zd/jquery.zds.table'],
		   function ($, callback,ZTOOL) {
		
		//金额分隔符
	    callback.formatCurrency=function(row, value) {
	        return ZTOOL.formatCurrency(value+""); 
	    }
		
		var html = "";
		<c:forEach items="${itemList }" var="riskItem" varStatus="sta">
			html +="<tr>";
			html +="<td class='td-title pct10'>${riskItem.typeName }</td>";
			html +="<td style='padding-left:10%;'>";
				var datas = "[";
				<c:forEach items="${riskItem.list }" var="item">
					datas += '{&quot;id&quot;:&quot;${item.exceptMattercode }&quot;,&quot;text&quot;:&quot;${item.exceptMatterName }&quot;},';
				</c:forEach>
				datas+="]";
				
				var values="";
				<c:forEach items="${riskItem.listThing }" var="thing">
					values += '${thing.itemCode },';
				</c:forEach>
				
				html +="<dd class='detail'>";
				html +='<input class="zui-checkbox zui-validatebox" data-defaultvalue="'+values+'" id="cccc_${sta.index}" type="hidden" data-toggle="checkbox" data-multiple="true" data-data="'+datas+'" data-valuefield="id" data-textfield="text" validate-type="Require">';
				html +="</dd>";
			html +="</td>";
			html +="</tr>";
		</c:forEach>
		
		$("#specialApproveE").append(html);
		
		$.ZUI.initDiv("#bbb");
		
		<c:forEach items="${itemList }" var="riskItem" varStatus="sta">
			$("#cccc_${sta.index }").ZCheckbox("disable");
		</c:forEach>
		
		$("#attachment").load('<z:ukey key="com.zdsoft.finance.fileStore.fileShowListPage" context="admin"/>'+'&productId=${fileStoreVo.productId }' +
		'&linkCode=${fileStoreVo.linkCode }&caseApplyId=${caseApplyId}&businessId=${fileStoreVo.businessId }');
		
		//勾选其它选项后的操作
		setTimeout(function(){
	 		var lis = $("#specialApproveE").find("ul [data-name=TPDM000006]");
			if(lis.length > 0){
				$(lis[0]).parent().append("<input type='text' disabled='disabled' style='width:700px' class='zui-validatebox zui-input' value='${otherInfo }' id='other_TPDM000006' value='123456'>");
			}
	 	},400)
	 	
		$.ZUI.init();
		
	});
</script>
</html>