<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../common/common_js.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查册入押</title>
</head>
<body>
	<!-- 押品按钮 -->
	<div class="frm-content">
		<div class="page-box">
			<div class="p5">
				<div class="info-tab" style="position: relative;">
					<ul class="tabs" id="page-tab">
						<c:forEach items="${housePropertyIdList}" var="hz" varStatus="status">
								<c:if test="${status.index == 0}">
									<li class="tabs-on" id="${hz}" ><a href="javascript:void(0);">押品信息${status.index +1}</a></li>
								</c:if>
								<c:if test="${status.index != 0 }">
									<li id="${hz}" ><a href="javascript:void(0);" >押品信息${status.index +1}</a></li>
								</c:if>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- 基本信息 -->
	<div class="page-box">
        <h1 class="page-title">基本信息</h1>
        <div class="p5">
            <table class="table-detail">
                <tr>
                    <td class="td-title pct15">案件号</td>
                    <td class="pct15">${basicInfoVo.caseApplyCode}</td>
                    <td class="td-title pct15">接单日期</td>
                    <td class="pct15" id="singleDate"></td>
                    <td class="td-title pct15">子产品</td>
                    <td class="pct15">${basicInfoVo.productSubtypeName}</td>
                </tr>
                <tr>
                    <td class="td-title pct15">拓展经理</td>
                    <td class="pct15">${basicInfoVo.developmentManagerName}</td>
                    <td class="td-title pct15">拓展部门</td>
                    <td class="pct15">${basicInfoVo.developmentDepartmentName}</td>
                    <td class="td-title pct15">机构</td>
                    <td class="pct15">${basicInfoVo.mechanismName}</td>
                </tr>
                <tr>
                    <td class="td-title pct15">申请金额(元)</td>
                    <td class="pct15">${basicInfoVo.applyAmount}</td>
                    <td class="td-title pct15">贷款期限</td>
                    <td class="pct15">${basicInfoVo.applyDeadline}</td>
                    <td class="td-title pct15">还款方式</td>
                    <td class="pct15">${basicInfoVo.repayMethod}</td>
                </tr>
                <tr>
                    <td class="td-title pct15">贷款利率</td>
                    <td class="pct15">${basicInfoVo.applyRate}</td>
                    <td class="td-title pct15">逾期利率</td>
                    <td class="pct15">${basicInfoVo.overdueRate}</td>
                    <td class="td-title pct15">终端</td>
                    <td class="pct15">${cooperatorTerminalVo.terminalFullName }</td>
                </tr>
                <tr>
                    <td class="td-title pct15">资金来源</td>
                    <td class="pct15">${basicInfoVo.capitalSource}</td>
                    <td class="td-title pct15"></td>
                    <td class="pct15"></td>
                    <td class="td-title pct15"></td>
                    <td class="pct15"></td>
                </tr>
            </table>
        </div>
	</div>
	
	<!-- 显示栏 -->
	<div class="tabcontents" id="showDetainAllInfo"></div>
	
		
<script type="text/javascript">
 	seajs.use([ 'jquery', 'zd/jquery.zds.page.callback', 'zd/switch'], function($, CALLBACK,tabSwitch) {
 		console.log('${housePropertyIdList}');
		//获取第一个tab的完整id
		var firstId = $("ul li:eq(0)").attr("id");
		//获取房产信息的controller的url
 		var url = '<z:ukey key="com.zdsoft.finance.casemanage.dealpledge.detain.showDetainAllInfo" context="admin"/>&housePropertyId=';
 		//第一次初始化的URL
 		var firstUrl = url + firstId;
 		//TODO 初始化押品1的所有信息,需修改Controller &housePropertyId=${housePropertyId}
		$("#showDetainAllInfo").load(firstUrl);

		//绑定页面所以li标签
 		$("#page-tab li").live("click", function(){
 			loadContent(this);
 		}); 
		
		/* CALLBACK.detainTabLoad=function(){
			alert("aa");
			loadContent(obj);
		} */
		
		//加载页面的公共方法
		function loadContent(obj){
			var thisObj = $(obj);
			//获取当前tab的id
			var id = thisObj.attr("id");
			//移除tab选中的样式
			thisObj.prevAll().removeClass("tabs-on");
			
			//清除当前tab的内容
			$("#showDetainAll").empty();
			//$("#contactEditDiv").closest(".zd-window").remove();
			//$("#workUnitEditDiv").closest(".zd-window").remove();
			//加载选中的tab的内容
			$("#showDetainAllInfo").load(url+id);
			//初始化tab
			tabSwitch.init();
		}
		
 	}); 
</script>
</body>

</html>