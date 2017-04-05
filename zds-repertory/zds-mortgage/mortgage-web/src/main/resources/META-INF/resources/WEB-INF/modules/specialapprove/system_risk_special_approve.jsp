<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../common/common_js.jsp'%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>风险特批审批</title>
</head>
<body>
	<c:if test="${process == false }">
		<div class="save">
			<button id="btn-save" class="btn-blue mr10" >保存</button>
			<button id="btn-commit" class="btn-blue mr10">提交</button>
		</div>
	</c:if>
	<input type="hidden" id="specialApproveManageId" value="${businessKey }"/>
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
                       		 validate-type="Require,Length[0-512]" placeholder="最多可以输入512个字符" >${remark }</textarea>
                         </label>
					</td>
				</tr>		
			</table>
		</div>
	</div>
	
	<!-- 引入附件 -->
	<div id="attachment"></div>
</body>

<script type="text/javascript">
	seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.form','zd/jquery.zds.table'],
		   function ($, callback,ZTOOL) {
		
		//金额分隔符
	    callback.formatCurrency=function(row, value) {
	        return ZTOOL.formatCurrency(value+""); 
	    }
		
		var html = "";
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
				html +='<input class="zui-checkbox zui-validatebox" data-defaultvalue="'+values+'" id="item_${sta.index}" type="hidden" data-toggle="checkbox" data-multiple="true" data-data="'+datas+'" data-valuefield="id" data-textfield="text" validate-type="Require">';
				html +="</dd>";
			html +="</td>";
			html +="</tr>";
		</c:forEach>
		
		$("#specialApproveE").append(html);
		
		$.ZUI.initDiv("#bbb");
		
		<c:forEach items="${itemList }" var="riskItem" varStatus="sta">
			$("#item_${sta.index }").ZCheckbox("disable");
		</c:forEach>
		
		var remark;
		//提交
		function saveData(submitStatus,datas){
			var url='<z:ukey key="com.zdsoft.finance.specialApprove.saveOrCommitRiskSpecialApproveApply" context="admin"/>&jsoncallback=?';
			var id = "";
			var len = ${fn:length(itemList) };
			var param = "{";
			//获取被选中风险项
			var i=1;
			<c:forEach items="${itemList }" var="riskItem" varStatus="sta">
				id = 'item_${sta.index }';
				param += "'${riskItem.itemType }':'" + $("#"+id).ZCheckbox('getValue')+"'";
				if(i != len){
					param +=　",";
				}
				i++;
			</c:forEach>
			param+="}";       
			param = encodeURI(param);
			$.ajax({
				method:'post',
				url:url + '&submitStatus='+ submitStatus + "&rickItem="+param,
				dataType: 'json',
				data:{'caseApplyId':"${caseApplyId }",'remark':$("#remark").val(),'isSystem':true,'specialApproveManageId':$("#specialApproveManageId").val(),'triggerType':0},
				success:function(data){
					<c:if test="${process == false }">
						if (data.resultStatus == 0){
								$("#specialApproveManageId").val(data.optional.specialApproveManageId);
								if(submitStatus){
									$.ZMessage.success("成功", data.msg, function () {
			                   			setTimeout(function(){
			     		                	ZDS_MESSAGE_CLIENT.refreshOpenner();
			     		                	ZDS_MESSAGE_CLIENT.closeSelf();
			     		                },200);
										
				                     });
								}else{
									$.ZMessage.success("成功", "保存成功", function(){});
								}
						}else{
							$.ZMessage.error("失败", data.msg , function(){});
						}
					</c:if>
					<c:if test="${process == true }">
						if (data.resultStatus == 0){
							//执行回调函数
	                        ZDS_WORKFLOW_CLIENT.callBackFuntion(datas,ZDS_WORKFLOW_PARAM._STATUS_SUCCESS,data.msg);
						}else{
							//执行回调函数
							ZDS_WORKFLOW_CLIENT.callBackFuntion(datas,ZDS_WORKFLOW_PARAM._STATUS_ERROR,data.msg);
						}
					</c:if>
				}
			});
		}
		
		<c:if test="${process == true }">
			//保存
		    ZDS_WORKFLOW_CLIENT.saveFunction = function (datas) {
				//流程中有修改页面，需要提交业务数据操作
				saveData(false,datas);
	    	}
			
		  	//提交方法
		    ZDS_WORKFLOW_CLIENT.submitFunction = ZDS_WORKFLOW_CLIENT.saveFunction;
		</c:if>
		
		<c:if test="${process == false }">
			$("#btn-commit").click(function(){
				saveData(true,null);
			});
			$("#btn-save").click(function(){
				saveData(false,null);
			});
		</c:if>
		
		$("#attachment").load('<z:ukey key="com.zdsoft.finance.fileStore.fileShowListPage" context="admin"/>'+'&productId=${fileStoreVo.productId }' +
		'&linkCode=${fileStoreVo.linkCode }&caseApplyId=${caseApplyId }&businessId=${fileStoreVo.businessId }');
		
		$.ZUI.initDiv(".page-box");
		$.ZUI.init();
		
		//勾选其它选项后的操作
		setTimeout(function(){
	 		var lis = $("#specialApproveE").find("ul [data-name=TPDM000006]");
			if(lis.length > 0){
				$(lis[0]).parent().append("<input type='text' disabled='disabled' style='width:700px' class='zui-validatebox zui-input' value='${otherInfo }' id='other_TPDM000006' value='123456'>");
			}
	 	},400)
		
	});
</script>
</html>