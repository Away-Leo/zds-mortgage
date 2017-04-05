<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../common/common_js.jsp'%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
	
	<!-- 引入案件基本信息 -->
	<%@ include file="public_case_apply_basic_info.jsp" %>
	<!-- 引入押品信息 -->
	<%@ include file="public_collateral_info.jsp" %>
	
	<!-- 特批风险特项 -->
	<div class="page-box">
		<h1 class="page-title">特批风险特项</h1>
		<div class="p5">
			<table class="table-detail" id="specialApproveRisk">
			</table>
		</div>
	</div>
	
	<!-- 备注 -->
	<div class="page-box">
		<div class="p5">
			<div class="page-title">备注</div>
			<table class="table-detail">
				<tr>
					<td class="td-title pct10">申请的具体原因及内容(请详细列明)</td>
					<td colspan="5">
                         <label>
                       		 <textarea id="remark" class="zui-area row-width zui-validatebox" validate-type="Require,Length[1-512]"
                                  placeholder="最多可以输入512个字符">${remark }</textarea>
                         </label>
					</td>
				</tr>		
			</table>
		</div>
	</div>
	
	<!-- 引入附件 -->
	<div id="attachment"></div>

<script type="text/javascript">
	seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.form','zd/jquery.zds.table'],
		   function ($, callback,ZTOOL) {
		
		//金额分隔符
	    callback.formatCurrency=function(row, value) {
	        return ZTOOL.formatCurrency(value+""); 
	    }
		
		var html = "";
		<c:forEach items="${itemList }" var="riskItem">
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
				html +='<input class="zui-checkbox zui-validatebox" data-callback="otherHandler" data-defaultvalue="'+values+'" id="${riskItem.itemType }" type="hidden" data-toggle="checkbox" data-multiple="true" data-data="'+datas+'" data-valuefield="id" data-textfield="text" validate-type="Require">';
				html +="</dd>";
			html +="</td>";
			html +="</tr>";
		</c:forEach>
		
		$("#specialApproveRisk").append(html);
		
		var otherid = "";
		var otherInfo = "${otherInfo }";
		//处理选择其他后的操作
		callback.otherHandler=function(v,t){
			var isSelected = false;//其它是否选中状态
			$.each(this.target.parents(".detail").find("li"),function(index,li){
				if("TPDM000006" == $(li).attr("data-name") && "" == li.className){
					isSelected = false;
				}else{
					isSelected = true;
				}
			});
			if(v.indexOf("TPDM000006") >= 0 && otherid == ""){
				this.target.next().append("<input type='text' style='width:700px' class='zui-validatebox zui-input' value='"+otherInfo+"' id='other_TPDM000006' >");
				otherid = "other_TPDM000006";
			}else if(!isSelected){
				$("#other_TPDM000006").remove();
				otherid = "";
			}
		}
		
		//提交
		function saveData(submitStatus,datas){
			var url='<z:ukey key="com.zdsoft.finance.specialApprove.saveOrCommitRiskSpecialApproveApply" context="admin"/>&jsoncallback=?';
			var id = "";
			var otherInfo = $("#"+otherid).val();//其它
			var len = ${fn:length(itemList) };
			var param = "{";
			//获取被选中风险项
			var i=1;
			<c:forEach items="${itemList }" var="riskItem">
				id = '${riskItem.itemType }';
				param += "'" + id + "':'" + $("#"+id).ZCheckbox('getValue')+"'";
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
				data:{'caseApplyId':"${caseApplyId }",'remark':$("#remark").val(),'otherInfo':otherInfo==undefined?"":otherInfo,
					'specialApproveManageId':'${businessKey }','triggerType':1},
				success:function(data){
					if (data.resultStatus == 0){
						//执行回调函数
                        ZDS_WORKFLOW_CLIENT.callBackFuntion(datas,ZDS_WORKFLOW_PARAM._STATUS_SUCCESS,data.msg);
					}else{
						//执行回调函数
						ZDS_WORKFLOW_CLIENT.callBackFuntion(datas,ZDS_WORKFLOW_PARAM._STATUS_ERROR,data.msg);
					}
				}
			});
		}
		
		//保存
	    ZDS_WORKFLOW_CLIENT.saveFunction = function (datas) {
			//流程中有修改页面，需要提交业务数据操作
			saveData(false,datas);
    	}
		
	  	//提交方法
	    ZDS_WORKFLOW_CLIENT.submitFunction = ZDS_WORKFLOW_CLIENT.saveFunction;
		
		//初始化页面
		$.ZUI.initDiv(".page-box");
		$.ZUI.init();
		
	 	$("#attachment").load('<z:ukey key="com.zdsoft.finance.fileStore.fileShowListPage" context="admin"/>'+'&productId=${fileStoreVo.productId }' +
			'&linkCode=${fileStoreVo.linkCode }&caseApplyId=${caseApplyId }&businessId=${fileStoreVo.businessId }');
	 	
	 	//勾选其它选项后的操作
	 	setTimeout(function(){
	 		var lis = $("#specialApproveRisk").find("ul [data-name=TPDM000006]");
			if(lis.length > 0){
				$(lis[0]).parent().append("<input type='text' style='width:700px' class='zui-validatebox zui-input' value='${otherInfo }' id='other_TPDM000006' value='123456'>");
				otherid = "other_TPDM000006";
			}
	 	},400)
	 	
	 });