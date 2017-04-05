<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../common/common_js.jsp'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<link rel="stylesheet" type="text/css"
	href="${localWebServer}/homePage/css/fanhuahoutai.css">
<title>泛华首页</title>
</head>
<body>
	<!--ll-part1开始-->
	<div class="ll-part1">
		<div id="process_frame" style="width: 100%;"></div>
		<div id="caseRemind" class="caseReminder "></div>
	</div>
	<!--ll-part2开始-->
	<div class="ll-part2">
		<div class="topNav">
			<p>逾期案件</p>
			<ul>
				<li data-type="first"><span class="current">60天及以上</span><em>${sumOverdueCase60.CASENUM}</em></li>
				<li data-type="second"><span>30及59天</span><em>${sumOverdueCase59.CASENUM}</em></li>
				<li data-type="third"><span>20及29天</span><em>${sumOverdueCase30.CASENUM}</em></li>
				<li data-type="fourth"><span>1-19天</span><em>${sumOverdueCase19.CASENUM}</em></li>
			</ul>
			
		</div>
		<div class="tableBox">
			<p>
				总计：<span>${sumOverdueCase.CASENUM}</span><em>单</em>已垫资：<span>${sumOverdueCase.SUMLOANANOUNT}</span><em>万元</em>未收回：<span>${sumOverdueCase.SUMPAIDPRINCIPALAMOUNT}</span><em>万元</em><a href="##" onclick="overdueCaseMore();" style="width: 90px;height: 28px;line-height: 28px;text-align: center;margin-top: 14px;color: #fff;background: #10a4ea;margin-right: 3%;float:right;
">更多</a>
			</p>
			
			<table data-type="first" class="tabActive">
				<thead>
					<tr>
						<th>序号</th>
						<th>机构</th>
						<th>主借人</th>
						<th>案件号</th>
						<th>产品分类</th>
						<th>子产品</th>
						<th>申请天数</th>
						<th>放款金额（万）</th>
						<th>本金余额（万）</th>
						<th>逾期天数</th>
						<th>放款日期</th>
						<th>贷后状态</th>
						<th>贷后方案</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<!-- 数据 -->
					<c:forEach items="${overdueCase60list}" var="basicVo">
						<tr>
							<td>${basicVo.XH }</td>
							<td>${basicVo.MECHANISMNAME }</td>
							<td>${basicVo.CUSTOMERNAME }</td>
							<td class="caseNum">${basicVo.CASEAPPLYCODE }</td>
							<td>${basicVo.PRODUCTTYPENAME }</td>
							<td>${basicVo.PRODUCTSUBTYPENAME }</td>
							<td>${basicVo.SQTS }</td>
							<td class="money">${basicVo.LOANANOUNT }</td>
							<td class="money">${basicVo.PAIDPRINCIPALAMOUNT }</td>
							<td>${basicVo.DAYS }</td>
							<td class="loanDate">${basicVo.ACTUALDATE }</td>
							<td>${basicVo.CASEAPPLYSTATUS }</td>
							<td>${basicVo.CASEAPPLYSTATUS }</td>
							<td class="operate"><a href="javaScript:void(0)" onclick="handle('${basicVo.ID }')"><img
									src="${localWebServer}/homePage/images/operate.jpg"
									alt="operate"></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<table data-type="second">
				<thead>
					<tr>
						<th>序号</th>
						<th>机构</th>
						<th>主借人</th>
						<th>案件号</th>
						<th>产品分类</th>
						<th>子产品</th>
						<th>申请天数</th>
						<th>放款金额（万）</th>
						<th>本金余额（万）</th>
						<th>逾期天数</th>
						<th>放款日期</th>
						<th>贷后状态</th>
						<th>贷后方案</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<!-- 数据 -->
					<c:forEach items="${overdueCase59list}" var="basicVo">
						<tr>
							<td>${basicVo.XH }</td>
							<td>${basicVo.MECHANISMNAME }</td>
							<td>${basicVo.CUSTOMERNAME }</td>
							<td class="caseNum">${basicVo.CASEAPPLYCODE }</td>
							<td>${basicVo.PRODUCTTYPENAME }</td>
							<td>${basicVo.PRODUCTSUBTYPENAME }</td>
							<td>${basicVo.SQTS }</td>
							<td class="money">${basicVo.LOANANOUNT }</td>
							<td class="money">${basicVo.PAIDPRINCIPALAMOUNT }</td>
							<td>${basicVo.DAYS }</td>
							<td class="loanDate">${basicVo.ACTUALDATE }</td>
							<td>${basicVo.CASEAPPLYSTATUS }</td>
							<td>${basicVo.CASEAPPLYSTATUS }</td>
							<td class="operate"><a href="javaScript:void(0)" onclick="handle('${basicVo.ID }')"><img
									src="${localWebServer}/homePage/images/operate.jpg"
									alt="operate"></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<table data-type="third">
				<thead>
					<tr>
						<th>序号</th>
						<th>机构</th>
						<th>主借人</th>
						<th>案件号</th>
						<th>产品分类</th>
						<th>子产品</th>
						<th>申请天数</th>
						<th>放款金额（万）</th>
						<th>本金余额（万）</th>
						<th>逾期天数</th>
						<th>放款日期</th>
						<th>贷后状态</th>
						<th>贷后方案</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<!-- 数据 -->
					<c:forEach items="${overdueCase30list}" var="basicVo">
						<tr>
							<td>${basicVo.XH }</td>
							<td>${basicVo.MECHANISMNAME }</td>
							<td>${basicVo.CUSTOMERNAME }</td>
							<td class="caseNum">${basicVo.CASEAPPLYCODE }</td>
							<td>${basicVo.PRODUCTTYPENAME }</td>
							<td>${basicVo.PRODUCTSUBTYPENAME }</td>
							<td>${basicVo.SQTS }</td>
							<td class="money">${basicVo.LOANANOUNT }</td>
							<td class="money">${basicVo.PAIDPRINCIPALAMOUNT }</td>
							<td>${basicVo.DAYS }</td>
							<td class="loanDate">${basicVo.ACTUALDATE }</td>
							<td>${basicVo.CASEAPPLYSTATUS }</td>
							<td>${basicVo.CASEAPPLYSTATUS }</td>
							<td class="operate"><a href="javaScript:void(0)" onclick="handle('${basicVo.ID }')"><img
									src="${localWebServer}/homePage/images/operate.jpg"
									alt="operate"></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<table data-type="fourth">
				<thead>
					<tr>
						<th>序号</th>
						<th>机构</th>
						<th>主借人</th>
						<th>案件号</th>
						<th>产品分类</th>
						<th>子产品</th>
						<th>申请天数</th>
						<th>放款金额（万）</th>
						<th>本金余额（万）</th>
						<th>逾期天数</th>
						<th>放款日期</th>
						<th>贷后状态</th>
						<th>贷后方案</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<!-- 数据 -->
					<c:forEach items="${overdueCase19list}" var="basicVo">
						<tr>
							<td>${basicVo.XH }</td>
							<td>${basicVo.MECHANISMNAME }</td>
							<td>${basicVo.CUSTOMERNAME }</td>
							<td class="caseNum">${basicVo.CASEAPPLYCODE }</td>
							<td>${basicVo.PRODUCTTYPENAME }</td>
							<td>${basicVo.PRODUCTSUBTYPENAME }</td>
							<td>${basicVo.SQTS }</td>
							<td class="money">${basicVo.LOANANOUNT }</td>
							<td class="money">${basicVo.PAIDPRINCIPALAMOUNT }</td>
							<td>${basicVo.DAYS }</td>
							<td class="loanDate">${basicVo.ACTUALDATE }</td>
							<td>${basicVo.CASEAPPLYSTATUS }</td>
							<td>${basicVo.CASEAPPLYSTATUS }</td>
							<td class="operate"><a href="javaScript:void(0)" onclick="handle('${basicVo.ID }')"><img
									src="${localWebServer}/homePage/images/operate.jpg"
									alt="operate"></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
<script type="text/javascript">
	// var taskUrl= '<z:res application="${application}" resource="workflow.process.initTaskInstancePage" isDefault="false"/>';
	var taskUrl = '<z:ukey key="com.zdsoft.finance.homePage.findMyPendingTasks" context="admin"/>';
	var taskUrl1 = '<z:ukey key="com.zdsoft.finance.homePage.findMyRemindCase" context="admin"/>';
	seajs
			.use(
					[ 'jquery', 'zd/jquery.zds.page.callback',
							'zd/jquery.zds.form', 'zd/jquery.zds.message',
							'zd/jquery.zds.dialog', 'zd/jquery.zds.combobox',
							'zd/jquery.zds.table', 'zd/jquery.zds.seleter' ],
					function($, CALLBACK) {
						var thisIframeId = window.name;
						//案件提醒
						$(".caseReminder dl").find("p").on(
								"click",
								function() {
									if ($(this).hasClass("active")) {
										$(this).removeClass("active").siblings(
												"ul").stop(true, true).slideUp(
												500);
									} else {
										$(this).addClass("active").siblings(
												"ul").slideDown(500).parent(
												"dd").siblings().find("p")
												.removeClass("active")
												.siblings().slideUp(500);
									}
								});
						//表格
						$(".tableBox").find("table[data-type=first]").show();
						$(".topNav ul").find("li").on(
								"click",
								function() {
									//var type=$(this).data("type");ie8不兼容
									var type = $(this).attr("data-type");
									$(this).find("span").addClass("current")
											.parent().siblings("li").find(
													"span").removeClass(
													"current");
									$(".tableBox").find(
											"table[data-type=" + type + "]")
											.show().siblings("table").hide();
								});
						$(function() {
							//    		setInterval("refreshPage()",30000);
							window.setInterval(function () {
								 $("#process_frame").load(taskUrl);
                			}, 30000);
							 $("#process_frame").load(taskUrl);
							 $("#caseRemind").load(taskUrl1);
						});
						//代办任务 更多
						window.pendingMore = function() {
							//var viewMyPendingTasksUrl = '<z:res application="${application}" resource="workflow.process.initTaskInstancePage" isDefault="false"/>';
							var viewMyPendingTasksUrl = '<z:ukey key="com.zdsoft.finance.workbench.toDo.init" context="admin"/>';
							ZDS_MESSAGE_CLIENT
									.openMenuLink(
											'pendingTasks',
											'我的待办任务',
											viewMyPendingTasksUrl
													+ "&openMethod=tabs&parentIframeId="
													+ thisIframeId);
						};
						//提醒
						window.remindClick = function(_url) {
							ZDS_MESSAGE_CLIENT.openMenuLink('remindTasks',
									'提醒',
									_url + "&openMethod=tabs&parentIframeId="
											+ thisIframeId);
						};
						//更多提醒
						window.remindMore = function() {
							var url = '<z:ukey key="com.zdsoft.finance.workbench.remindCase.init" context="admin"/>';
							ZDS_MESSAGE_CLIENT.openMenuLink('remind_case',
									'案件提醒',
									url + "&openMethod=tabs&parentIframeId="
											+ thisIframeId);
						};
						//处理任务
						window.viewTasks = function(taskName, url,
								taskCandidateId, taskCandidateId) {
							ZDS_MESSAGE_CLIENT.openMenuLink('myPendingTask',
									taskName,
									url + "&openMethod=tabs&parentIframeId="
											+ thisIframeId);
							//ZDS_MESSAGE_CLIENT.openMenuLink('myPendingTask'+taskCandidateId, taskName, url+"?taskCandidateId=" + taskCandidateId + "&taskCandidateId=" + taskCandidateId + "&openMethod=tabs&parentIframeId="+thisIframeId);
						}
						window.refreshPage = function() {
							$("#process_frame").load(
									taskUrl + '&_t=' + new Date().getTime());
						}

						window.refreshPage1 = function() {
							$("#caseRemind").load(taskUrl1);
						}
						
						//更多提醒
						window.overdueCaseMore = function() {
							var url = '<z:ukey key="com.zdsoft.finance.workbench.overdueCase.init" context="admin"/>';
							ZDS_MESSAGE_CLIENT.openMenuLink('overdue_case',
									'逾期案件',
									url + "&openMethod=tabs&parentIframeId="
											+ thisIframeId);
						}
						
						window.handle=function(id){
					    	
							if(!id){
								$.ZMessage.error("错误", "未获取到主键", function () {
					                $(".zd-message").ZWindow("close");
					            });
								return ;
							}
							var eidtHandleUrl = '<z:ukey key="com.zdsoft.finance.afterloan.loanAfterHandle" context="admin"/>&jsoncallback=?&caseApplyId='+id;
							ZDS_MESSAGE_CLIENT.openMenuLink('loanAfterHandle','处理',eidtHandleUrl + "&openMethod=tabs"); 
					    }
						

					});
</script>
</html>